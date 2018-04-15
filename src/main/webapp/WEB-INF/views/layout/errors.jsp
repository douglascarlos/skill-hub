<%@ page import="java.util.List" %>
<%
    List<String> errors = (List) request.getAttribute("errors");
    boolean requestInvalid = errors != null && !errors.isEmpty();
%>
<% if(requestInvalid){ %>
<ul>
    <% for(String error:errors){ %>
    <li><%= error %></li>
    <% } %>
</ul>
<% } %>