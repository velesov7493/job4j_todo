package ru.job4j.todo.controllers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class IndexServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        req.setAttribute("pageTitle", "Список задач");
        req.setAttribute("isServlet", true);
        req.getRequestDispatcher("views/task/list.jsp").forward(req, resp);
    }
}
