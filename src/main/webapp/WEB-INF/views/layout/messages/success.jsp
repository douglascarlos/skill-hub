<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String successMessage = (String) request.getAttribute("successMessage");
%>
<% if(successMessage instanceof String && !successMessage.isEmpty()){ %>
<script type="text/javascript">
    $(document).ready(function() {
        M.toast({html: "<%= successMessage %>", classes: 'green lighten-1 rounded'});
    });
</script>
<% } %>