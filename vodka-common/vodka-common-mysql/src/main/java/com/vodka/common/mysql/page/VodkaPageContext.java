package com.vodka.common.mysql.page;

import com.vodka.common.base.page.VodkaPage;

/**
 * @author Breeze
 * @date 2023/9/6 22:35
 * @description 分页上下文
 */
public class VodkaPageContext {

    private static ThreadLocal<VodkaPage> vodkaPageThreadLocal = new ThreadLocal<>();

    /**
     * 向当前线程上下文中设置 分页信息对象
     *
     * @param pageNum  当前页码
     * @param pageSize 当前页大小
     */
    public static void setPage(Integer pageNum, Integer pageSize) {
        VodkaPage vodkaPage = new VodkaPage();
        // TODO 可以在设置的时候对pagenNum, pageSize 进行校验
        vodkaPage.setPageNum(pageNum);
        vodkaPage.setPageSize(pageSize);
        vodkaPageThreadLocal.set(vodkaPage);
    }

    /**
     * 从线程上下文中获取 分页信息对象
     *
     * @return VodkaPage
     */
    public static VodkaPage getPage() {
        return vodkaPageThreadLocal.get();
    }

    /**
     * 数据清理, 避免内存泄漏
     */
    public static void clear() {
        vodkaPageThreadLocal.remove();
    }

}
