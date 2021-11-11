package ru.job4j.todo.controllers;

import ru.job4j.todo.persistence.Category;
import ru.job4j.todo.services.CategoryService;
import ru.job4j.todo.services.JsonService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CategoriesAjaxServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        CategoryService service = CategoryService.getInstance();
        resp.setContentType("application/json; charset=utf-8");
        List<Category> records = service.findAll();
        if (records != null && !records.isEmpty()) {
            JsonService js = JsonService.getInstance();
            js.jsonWriteToStream(records, resp.getOutputStream());
            resp.setStatus(200);
        } else {
            resp.setStatus(204);
        }

    }
}
