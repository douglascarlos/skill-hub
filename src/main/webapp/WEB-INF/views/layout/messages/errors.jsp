<%@ page import="java.util.List" %>
<%
    List<String> errors = (List) request.getAttribute("errors");
    boolean requestInvalid = errors != null && !errors.isEmpty();
%>
<% if(requestInvalid){ %>
<div class="row">
    <div class="card-panel red lighten-4 red-text">
        <ul>
            <% for(String error:errors){ %>
            <li><%= error %></li>
            <% } %>
        </ul>
    </div>
</div>
<% } %>