</div>
<% if (request.getAttribute("pageScript") != null) { %>
<script src="<%=request.getContextPath()%>/template/js/<%=request.getAttribute("pageScript")%>"></script>
<% } %>
</body>
</html>
