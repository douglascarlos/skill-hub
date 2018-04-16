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
<script type="text/javascript">
    $(document).ready(function() {
        M.toast({
            html: "Ocorreu um erro, preencha corretamente os campos",
            classes: 'red lighten-1 rounded'
        });
    });
</script>
<% } %>