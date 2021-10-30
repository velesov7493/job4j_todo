package ru.job4j.todo.controllers;

import ru.job4j.todo.persistence.Role;
import ru.job4j.todo.services.RoleService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RolesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        req.setAttribute("pageTitle", "Список ролей");
        req.setAttribute("isServlet", true);
        req.getRequestDispatcher("views/role/list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        HttpSession s = req.getSession();
        req.setCharacterEncoding("UTF-8");
        if (s.getAttribute("user") == null) {
            s.setAttribute("error", "Доступ запрещен! Авторизуйтесь, чтобы редактировать данные.");
        } else {
            RoleService service = RoleService.getInstance();
            Role r = Role.of(req.getParameter("nName"));
            if (!service.save(r)) {
                s.setAttribute("error", "Ошибка создания роли!");
            }
        }
        resp.sendRedirect(req.getContextPath() + "/roles.do");
    }
}
