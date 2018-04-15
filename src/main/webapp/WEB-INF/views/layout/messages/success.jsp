<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String successMessage = (String) request.getAttribute("successMessage");
%>
<% if(successMessage instanceof String && !successMessage.isEmpty()){ %>
<div class="row">
    <div class="card-panel green lighten-4 green-text"><strong><%= successMessage %></strong></div>
</div>
<% } %>