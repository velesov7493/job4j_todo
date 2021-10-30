package ru.job4j.todo.controllers;

import ru.job4j.todo.persistence.Task;
import ru.job4j.todo.persistence.User;
import ru.job4j.todo.services.TaskService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class TasksServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        req.setAttribute("pageTitle", "Список задач");
        req.setAttribute("isServlet", true);
        req.getRequestDispatcher("views/task/list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession s = req.getSession();
        req.setCharacterEncoding("UTF-8");
        TaskService service = TaskService.getInstance();
        short done = "on".equals(req.getParameter("nDone")) ? (short) 1 : 0;
        Task t = new Task();
        t.setDescription(req.getParameter("nDescription"));
        t.setAuthor((User) req.getSession().getAttribute("user"));
        t.setDone(done);
        if (!service.save(t)) {
            s.setAttribute("error", "Ошибка создания задачи!");
        }
        resp.sendRedirect(req.getContextPath() + "/tasks.do");
    }
}
