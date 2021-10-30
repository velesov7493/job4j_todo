<%@ page contentType="text/html; charset=UTF-8" %>

<%
    if (request.getAttribute("isServlet") == null) {
        response.sendRedirect(request.getContextPath() + "/roles.do");
    }
    HttpSession s = request.getSession();
    String error = (String) s.getAttribute("error");
    s.removeAttribute("error");
    request.setAttribute("pageScript", "roles.js");
%>
<%@ include file="../../template/layouts/pageHeader.jsp" %>

<div class="row">
    <div class="<%=error == null ? "hidden error-panel" : "error-panel"%>">
        <h3>Ошибка</h3>
        <p id="err-text"><%=error == null ? "" : error%></p>
        <pre id="err-data"></pre>
    </div>
    <div class="card">
        <div class="card-header">
            <h3>Редактирование роли</h3>
        </div>
        <div class="card-body">
            <form id="role-edit-form" method="post" action="<%=request.getContextPath()%>/roles.do">
                <input id="role-id" type="hidden" name="nId" value="0" />
                <div class="form-group">
                    <label for="role-name">Наименование:</label>
                    <input name="nName" class="form-control" id="role-name" type="text" />
                </div>
                <div class="form-group">
                    <button id="role-submit" type="submit" class="pull-right btn btn-primary">Сохранить</button>
                </div>
            </form>
        </div>
    </div>
</div>
<div class="row">
    <div class="card">
        <div class="card-header">
            <h3>Роли</h3>
        </div>
        <div class="card-body">
            <table id="datatable" class="table">
                <thead>
                <tr>
                    <th>Наименование</th>
                    <th>Операции</th>
                </tr>
                </thead>
                <tbody>

                </tbody>
            </table>
        </div>
    </div>
</div>

<%@ include file="../../template/layouts/pageFooter.jsp" %>