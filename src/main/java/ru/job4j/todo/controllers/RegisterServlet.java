package ru.job4j.todo.controllers;

import ru.job4j.todo.persistence.User;
import ru.job4j.todo.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {

    private void errorDispatch(String error, HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setAttribute("error", error);
        req.getRequestDispatcher("views/user/register.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        req.getRequestDispatcher("views/user/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        UserService service = UserService.getInstance();
        String error;
        String pass = req.getParameter("nPassword");
        String check = req.getParameter("nCheckPassword");
        if (pass == null || !pass.equals(check)) {
            error = "Пароль отсутствует или повторён неверно.";
            errorDispatch(error, req, resp);
            return;
        }
        User usr = new User();
        usr.setName(req.getParameter("nName"));
        usr.setLogin(req.getParameter("nLogin"));
        usr.setPassword(req.getParameter("nPassword"));
        if (!service.save(usr)) {
            error =
                    "Ошибка создания пользователя."
                    + " Скорее всего пользователь с таким логином уже существует.";
            errorDispatch(error, req, resp);
            return;
        }
        usr = service.login(usr.getLogin(), pass);
        if (usr == null) {
            error =
                    "Ошибка входа нового пользователя."
                    + " Пользователь не найден.";
            errorDispatch(error, req, resp);
            return;
        }
        HttpSession sc = req.getSession();
        sc.setAttribute("user", usr);
        resp.sendRedirect(req.getContextPath() + "/index.do");
    }
}
