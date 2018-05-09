<%@ page import="br.feevale.model.Project" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Project project = (Project) request.getAttribute("project");

    boolean isCreate = !project.exists();

    Map<String, String> input = (Map) request.getAttribute("input");
    List<String> errors = (List) request.getAttribute("errors");
    boolean requestInvalid = errors != null && !errors.isEmpty();
%>
<jsp:include page="../layout/header.jsp" />
<div class="section">
    <h5><%= project.exists() ? "Editar" : "Criar" %> Projeto</h5>
</div>
<jsp:include page="../layout/messages/messages.jsp" />
<div class="row">
    <ul class="tabs">
        <li class="tab col s6"><a href="#general-data">Dados Gerais</a></li>
        <li class="tab col s6 <%= isCreate ? "disabled" : "" %>"><a href="#members">Membros</a></li>
    </ul>
    <div id="general-data" class="col s12 tab-content">
        <jsp:include page="./general-data/form.jsp" />
    </div>
    <div id="members" class="col s12 tab-content">
        <% if(!isCreate){ %>
        <jsp:include page="./members/index.jsp" />
        <% } %>
    </div>
</div>
<jsp:include page="../layout/footer.jsp" />
