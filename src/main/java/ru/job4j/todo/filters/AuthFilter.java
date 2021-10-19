package ru.job4j.todo.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter implements Filter {

    private static final Logger LOG = LoggerFactory.getLogger(AuthFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOG.info("AuthFilter.init()");
    }

    @Override
    public void destroy() {
        LOG.info("AuthFilter.destroy()");
    }

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain)
        throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String uri = req.getRequestURI();
        String method = req.getMethod().toUpperCase();
        if (
            !"GET".equals(method)
            && (uri.endsWith("tasks.do") || uri.endsWith("roles.do"))
            && req.getSession().getAttribute("user") == null
        ) {
            if ("POST".equals(method)) {
                LOG.info("Исходный адрес = " + uri + " перенаправляется на /auth.do");
                resp.sendRedirect(req.getContextPath() + "/auth.do");
            } else {
                LOG.info("Запрет неавторизованного изменения данных по адресу: " + uri);
                resp.setStatus(403);
            }
            return;
        }
        filterChain.doFilter(req, resp);
    }
}