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
import java.util.List;

public class TasksServlet extends HttpServlet {

    private void getSingleTask(String taskId, HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        TaskService service = TaskService.getInstance();
        int id = taskId == null ? 0 : Integer.parseInt(taskId);
        Task result = service.getById(id);
        resp.setContentType("application/json; charset=utf-8");
        if (result != null) {
            service.jsonWriteToStreamSingleTask(result, resp.getOutputStream());
            resp.setStatus(200);
        } else {
            resp.setStatus(404);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        String scope = req.getParameter("scope");
        String sid = req.getParameter("id");
        if (sid != null) {
            getSingleTask(sid, req, resp);
            return;
        }
        resp.setContentType("application/json; charset=utf-8");
        List<Task> records;
        TaskService service = TaskService.getInstance();
        if (scope == null || "all".equals(scope)) {
            records = service.findAll();
        } else {
            records = service.findAllOpened();
        }
        if (records != null && !records.isEmpty()) {
            service.jsonWriteToStream(records, resp.getOutputStream());
            resp.setStatus(200);
        } else {
            resp.setStatus(204);
        }
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
        resp.sendRedirect(req.getContextPath() + "/index.do");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        TaskService service = TaskService.getInstance();
        String sid = req.getParameter("id");
        int id = sid == null ? 0 : Integer.parseInt(sid);
        resp.setStatus(service.deleteById(id) ? 200 : 404);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        TaskService service = TaskService.getInstance();
        Task t = service.jsonReadFromStream(req.getInputStream());
        t.setAuthor((User) req.getSession().getAttribute("user"));
        resp.setStatus(service.patch(t) ? 200 : 406);
    }
}