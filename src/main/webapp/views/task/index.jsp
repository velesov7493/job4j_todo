<%@ page contentType="text/html; charset=UTF-8" %>

<%
    if (request.getAttribute("isServlet") == null) {
        response.sendRedirect(request.getContextPath() + "/index.do");
    }
    HttpSession s = request.getSession();
    String error = (String) s.getAttribute("error");
    s.removeAttribute("error");
    request.setAttribute("pageScript", "tasks.js");
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
            <h3>Редактирование задачи</h3>
        </div>
        <div class="card-body">
            <form id="task-edit-form" method="post" action="<%=request.getContextPath()%>/tasks.do">
                <input id="task-id" type="hidden" name="nId" value="0" />
                <div class="form-group">
                    <label for="task-description">Описание задания:</label>
                    <textarea name="nDescription" class="form-control" id="task-description" required></textarea>
                </div>
                <div class="form-check">
                    <input name="nDone" class="form-check-input" id="task-done" type="checkbox" />
                    <label class="form-check-label" for="task-done">Задание выполнено</label>
                </div>
                <div class="form-group">
                    <button id="task-submit" type="submit" class="pull-right btn btn-primary">Сохранить</button>
                </div>
            </form>
        </div>
    </div>
</div>
<div class="row">
    <div class="card">
        <div class="card-header">
            <h3>Задачи</h3>
        </div>
        <div class="card-body">
            <ul id="filter" class="menu">
                <li class="item-left"><button id="show-opened" class="btn btn-secondary"><i class="fa fa-question-circle"></i> Незавершенные</button></li>
                <li class="item-left"><button id="show-all" class="btn btn-primary"><i class="fa fa-tasks"></i> Все</button></li>
            </ul>
            <table id="datatable" class="table">
                <thead>
                    <tr>
                        <th>Статус</th>
                        <th class="big-col">Задание</th>
                        <th>Автор</th>
                        <th>Дата создания</th>
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