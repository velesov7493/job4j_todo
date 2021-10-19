<%@ page import="ru.job4j.todo.persistence.User" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%
    User user = (User) request.getSession().getAttribute("user");
%>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><%=request.getAttribute("pageTitle")%></title>
    <link href="<%=request.getContextPath()%>/template/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/template/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/template/favicon.png" rel="shortcut icon" type="image/png">
    <link href="<%=request.getContextPath()%>/template/css/main.css" rel="stylesheet">
    <script src="<%=request.getContextPath()%>/template/js/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/template/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <header id="header">
        <div class="row header-top">
            <div class="col-8"><p>job4j.todo-0.1</p></div>
            <div class="col-4"><p>Приветствуем, <%= user == null ? "Гость" : user.getName()%></p></div>
        </div>
        <div class="row header-bottom">
            <ul class="menu">
                <li class="item-left"><a class="btn btn-outline-dark" href="<%=request.getContextPath()%>/index.do"><i class="fa fa-tasks"></i> Задачи</a></li>
                <li class="item-left"><a class="btn btn-outline-dark" href="<%=request.getContextPath()%>/roles.do"><i class="fa fa-chain"></i> Роли</a></li>
                <% if (user == null) { %>
                <li class="item-right"><a class="btn btn-outline-dark" href="<%=request.getContextPath()%>/auth.do"><i class="fa fa-lock"></i> Вход</a></li>
                <li class="item-right"><a class="btn btn-outline-dark" href="<%=request.getContextPath()%>/register.do"><i class="fa fa-check-circle-o"></i> Регистрация</a></li>
                <% } else { %>
                <li class="item-right"><a class="btn btn-outline-dark" href="<%=request.getContextPath()%>/logout.do"><i class="fa fa-unlock"></i> Выход</a></li>
                <% } %>
            </ul>
        </div>
    </header>