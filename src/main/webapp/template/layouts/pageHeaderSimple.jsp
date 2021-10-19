<%@ page import="ru.job4j.todo.persistence.User" %>
<%@ page contentType="text/html; charset=UTF-8" %>

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