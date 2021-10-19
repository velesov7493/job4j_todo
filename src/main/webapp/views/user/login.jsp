<%@ page contentType="text/html; charset=UTF-8" %>
<%
    request.setAttribute("pageTitle", "Вход в систему");
%>
<%@ include file="../../template/layouts/pageHeaderSimple.jsp" %>

<div class="row">
    <div class="card vert-center">
        <div class="card-header"><h2>Авторизация</h2></div>
        <div class="card-body">
            <% if (request.getAttribute("error") != null) { %>
            <div class="error-panel">
                <h3>Ошибка</h3>
                <p><%=request.getAttribute("error")%></p>
            </div>
            <% } %>
            <form action="<%=request.getContextPath()%>/auth.do" method="post">
                <div class="form-group">
                    <label>Логин:</label>
                    <input type="text" class="form-control" name="nLogin">
                </div>
                <div class="form-group">
                    <label>Пароль:</label>
                    <input type="password" class="form-control" name="nPassword">
                </div>
                <button type="submit" class="btn btn-primary pull-right">Войти</button>
            </form>
        </div>
    </div>
</div>

<%@ include file="../../template/layouts/pageFooter.jsp" %>