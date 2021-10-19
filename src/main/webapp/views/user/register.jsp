<%@ page contentType="text/html; charset=UTF-8" %>
<%
    request.setAttribute("pageTitle", "Регистрация");
%>
<%@ include file="../../template/layouts/pageHeaderSimple.jsp" %>

<div class="row">
    <div class="card vert-center">
        <div class="card-header"><h2>Регистрация</h2></div>
        <div class="card-body">
            <% if (request.getAttribute("error") != null) { %>
            <div class="error-panel">
                <h3>Ошибка</h3>
                <p><%=request.getAttribute("error")%></p>
            </div>
            <% } %>
            <form action="<%=request.getContextPath()%>/register.do" method="post">
                <div class="form-group">
                    <label>Ф.И.О:</label>
                    <input type="text" class="form-control" name="nName" required>
                    <label>Логин:</label>
                    <input type="text" class="form-control" name="nLogin" required>
                    <label>Пароль:</label>
                    <input type="password" class="form-control" name="nPassword" required>
                    <label>Проверка пароля (пароль еще раз):</label>
                    <input type="password" class="form-control" name="nCheckPassword" required>
                </div>
                <button type="submit" class="btn btn-primary pull-right">Зарегистрироваться</button>
            </form>
        </div>
    </div>
</div>

<%@ include file="../../template/layouts/pageFooter.jsp" %>