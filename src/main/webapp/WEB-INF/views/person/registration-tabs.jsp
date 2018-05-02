<%@ page import="br.feevale.model.Person" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Person person = (Person) request.getAttribute("person");
    boolean isCreate = !person.exists();
%>
<jsp:include page="../layout/header.jsp" />
<div class="section">
    <h5><%= isCreate ? "Criar" : "Editar" %> Pessoa</h5>
</div>
<jsp:include page="../layout/messages/messages.jsp" />
<div class="row">
    <ul class="tabs">
        <li class="tab col s6"><a href="#general-data">Dados Gerais</a></li>
        <li class="tab col s6 <%= isCreate ? "disabled" : "" %>"><a href="#skills">Competências</a></li>
    </ul>
    <div id="general-data" class="col s12 tab-content">
        <jsp:include page="./general-data.jsp" />
    </div>
    <div id="skills" class="col s12 tab-content">
        <% if(!isCreate){ %>
        <jsp:include page="skills.jsp" />
        <% } %>
    </div>
</div>
<jsp:include page="../layout/footer.jsp" />
