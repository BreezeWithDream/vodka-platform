package com.vodka.common.mysql.plugin;

import com.vodka.common.mysql.page.VodkaPage;
import com.vodka.common.mysql.page.VodkaPageContext;
import com.vodka.common.mysql.utils.MyBatisProxyUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

/**
 * @author Breeze
 * @date 2023/9/6 22:29
 * @description SQL分页插件
 */
@Data
@Slf4j
@Intercepts({
        // 在sql进行预编译之前进行拦截, 预编译之后再拦截就来不及了
        @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class SQLPagePlugin implements Interceptor {

    /**
     * 拦截之后进行对原sql进行处理, 说白了就是变更sql, 加上分页条件限制
     *
     * @param invocation invocation
     * @return result
     * @throws Throwable throwable
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // 获取原sql
        StatementHandler statementHandler = (StatementHandler) MyBatisProxyUtil.getNoProxyObject(invocation.getTarget());

        log.info("分页插件中的 statementHandler: {}", statementHandler.getClass().getSimpleName());
        BoundSql boundSql = statementHandler.getBoundSql();
        String originSql = boundSql.getSql().toLowerCase(Locale.ROOT).replaceAll("\n", "");
        Connection connection = (Connection) invocation.getArgs()[0];

        VodkaPage page = VodkaPageContext.getPage();
        if (page == null) {
            return invocation.proceed();
        }
        Integer pageSize = page.getPageSize();
        Integer pageNum = page.getPageNum();

        // 根据原sql获取总条数, 存入分页上下文中
        Integer total = getTotal(connection, statementHandler, originSql);

        // calc pages
        Integer pages = total % pageSize == 0 ? (total / pageSize) : (total / pageSize + 1);
        page.setTotal(total).setPages(pages);

        // 变更sql, 新增limit分页
        // TODO 可以采用策略模式, 根据数据库的类型, 进行不同的分页变更
        int startNum = (pageNum - 1) * pageSize;
        String newSql = originSql + " limit " + startNum + "," + pageSize;

        // 进行sql替换, 将新sql替换掉之前的sql

        MetaObject metaObject = SystemMetaObject.forObject(boundSql);
        String sqlField = "sql";
        metaObject.setValue(sqlField, newSql);

        return invocation.proceed();
    }

    /**
     * 根据原sql获取count(*)总数
     *
     * @param connection       connection
     * @param statementHandler statementHandler
     * @param sql              sql
     * @return count
     * @throws SQLException sqlException
     */
    private Integer getTotal(Connection connection, StatementHandler statementHandler, String sql) throws SQLException {
        String countAlias = "total";
        String selectPrefix = "select count(*) as " + countAlias + " ";
        String from = " from ";

        String getCountSql = selectPrefix + sql.substring(getMainFromIndex(0, sql));
        log.info("get count sql: {}", getCountSql);
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        // 此时就要执行sql(携带参数),
        try {
            preparedStatement = connection.prepareStatement(getCountSql);

            // 此时需要对sql设置相关参数
            // eg: select count(*) as total from stu where xxx=? and xxx=?
            // 本质是通过statementHandler.parameterize(ps) 来填充参数的
            statementHandler.parameterize(preparedStatement);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(countAlias);
            }
        } catch (SQLException e) {
            log.info("获取对应sql总记录数失败");
            throw e;
        } finally {
            // close resources
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
        return 0;
    }

    private Integer getMainFromIndex(int beginIndex, String sql) {
        if (sql == null || sql.length() == 0) {

            return -1;
        }
        String from = " from ";
        int fromIndex = sql.indexOf(from, beginIndex);
        if (fromIndex == -1) {
            return -1;
        }
        String sqlSub = sql.substring(0, fromIndex);

        int count = 0;
        char[] chars = sqlSub.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            if (ch == '(') {
                count++;
            }
            if (ch == ')') {
                count--;
            }
        }
        if (count == 0) {
            return fromIndex;
        } else {
            return getMainFromIndex(fromIndex + 6, sql);
        }
    }

}
