<%@ page contentType="text/html; charset=UTF-8" %>

<%
    if (request.getAttribute("isServlet") == null) {
        response.sendRedirect(request.getContextPath() + "/index.do");
    }
    HttpSession s = request.getSession();
    String error = (String) s.getAttribute("error");
    s.removeAttribute("error");
%>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><%=request.getAttribute("pageTitle")%></title>
    <link href="template/css/bootstrap.min.css" rel="stylesheet">
    <link href="template/css/font-awesome.min.css" rel="stylesheet">
    <link href="template/favicon.png" rel="shortcut icon" type="image/png">
    <link href="template/css/main.css" rel="stylesheet">
    <script src="template/js/jquery.min.js"></script>
    <script src="template/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-12">
            <div class="<%=error == null ? "hidden error-panel" : "error-panel"%>">
                <h2>Ошибка</h2>
                <p id="err-text"><%=error == null ? "" : error%></p>
                <pre id="err-data"></pre>
            </div>
            <h3 class="text-center">Редактирование задачи</h3>
            <form id="task-edit-form" method="post" action="<%=request.getContextPath()%>/tasks.do">
                <input id="task-id" type="hidden" name="nId" value="" />
                <div class="form-group">
                    <label for="task-description">Описание задания:</label>
                    <textarea name="nDescription" class="form-control" id="task-description" required></textarea>
                </div>
                <div class="form-check">
                    <input name="nDone" class="form-check-input" id="task-done" type="checkbox" />
                    <label class="form-check-label" for="task-done">Задание выполнено</label>
                </div>
                <div class="form-group">
                    <button type="submit" class="pull-right btn btn-primary">Сохранить</button>
                </div>
            </form>
        </div>
    </div>
    <div class="row">
        <div class="col-12">
            <h4 class="text-center">Фильтры задач</h4>
            <ul id="filter" class="menu">
                <li class="item-left"><button id="show-opened" class="btn btn-secondary"><i class="fa fa-question-circle"></i> Незавершенные</button></li>
                <li class="item-left"><button id="show-all" class="btn btn-primary"><i class="fa fa-tasks"></i> Все</button></li>
            </ul>
        </div>
    </div>
    <div class="row">
        <div class="col-12 pt-3">
            <div class="card-header">
                <h3 class="text-center">Задачи</h3>
            </div>
            <div class="card-body">
                <table class="table">
                    <thead>
                        <tr>
                            <th>Статус</th>
                            <th class="big-col">Задание</th>
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
</div>
<script src="template/js/tasks.js"></script>
</body>
</html>