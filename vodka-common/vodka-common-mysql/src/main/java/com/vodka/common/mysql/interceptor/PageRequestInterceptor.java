package com.vodka.common.mysql.interceptor;

import com.vodka.common.mysql.page.VodkaPageContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author Breeze
 * @date 2023/9/10 12:48
 * @description 分页拦截器-用于拦截前端参数中是否传有pageNum, pageSize等参数， 判断是否需要进行分页
 */
public class PageRequestInterceptor extends HandlerInterceptorAdapter {

    /**
     * 拦截请求中是否包含(pageNum,pageSize)等参数， 以此判断是否需要进行分页
     *
     * @param request  request
     * @param response response
     * @param handler  handler
     * @return true/false
     * @throws Exception Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String pageNumParam = "pageNum";
        String pageSizeParam = "pageSize";

        String pageNumStr = request.getParameter(pageNumParam);
        String pageSizeStr = request.getParameter(pageSizeParam);

        if (pageNumStr != null && pageSizeStr != null) {
            // 进行拦截
            try {
                int pageNum = Integer.parseInt(pageNumStr);
                int pageSize = Integer.parseInt(pageSizeStr);
                VodkaPageContext.setPage(pageNum, pageSize);
            } catch (Exception e) {
                return true;
            }
        }
        return true;
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 清理ThreadLocal
        VodkaPageContext.clear();
    }

}
