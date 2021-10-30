package ru.job4j.todo.controllers;

import ru.job4j.todo.persistence.Role;
import ru.job4j.todo.services.RoleService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class RolesAjaxServlet extends HttpServlet {

    private void getSingleRole(String roleId, HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        RoleService service = RoleService.getInstance();
        int id = roleId == null ? 0 : Integer.parseInt(roleId);
        Role result = service.getById(id);
        resp.setContentType("application/json; charset=utf-8");
        if (result != null) {
            service.jsonWriteToStreamSingleRole(result, resp.getOutputStream());
            resp.setStatus(200);
        } else {
            resp.setStatus(404);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        String sid = req.getParameter("id");
        if (sid != null) {
            getSingleRole(sid, req, resp);
            return;
        }
        RoleService service = RoleService.getInstance();
        resp.setContentType("application/json; charset=utf-8");
        List<Role> records = service.findAll();
        if (records != null && !records.isEmpty()) {
            service.jsonWriteToStream(records, resp.getOutputStream());
            resp.setStatus(200);
        } else {
            resp.setStatus(204);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        RoleService service = RoleService.getInstance();
        Role r = service.jsonReadFromStream(req.getInputStream());
        resp.setStatus(service.save(r) ? 200 : 406);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        RoleService service = RoleService.getInstance();
        String sid = req.getParameter("id");
        int id = sid == null ? 0 : Integer.parseInt(sid);
        resp.setStatus(service.deleteById(id) ? 200 : 404);
    }
}