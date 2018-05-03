<%@ page import="br.feevale.model.Person" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="br.feevale.model.Skill" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Person person = (Person) request.getAttribute("person");
    boolean isCreate = !person.exists();
    Skill skill = (Skill) request.getAttribute("skill");
    boolean isEditSkill = skill instanceof Skill && skill.exists();
    boolean cancelSkill = request.getParameter("cancel") instanceof String && request.getParameter("cancel").equals("true");
    boolean shouldActiveSkills = isEditSkill || cancelSkill;
%>
<jsp:include page="../layout/header.jsp" />
<div class="section">
    <h5><%= isCreate ? "Criar" : "Editar" %> Pessoa</h5>
</div>
<jsp:include page="../layout/messages/messages.jsp" />
<div class="row">
    <ul class="tabs">
        <li class="tab col s6"><a href="#general-data">Dados Gerais</a></li>
        <li class="tab col s6 <%= isCreate ? "disabled" : "" %>"><a class="<%= shouldActiveSkills ? "active" : "" %>" href="#skills">Competências</a></li>
    </ul>
    <div id="general-data" class="col s12 tab-content">
        <jsp:include page="./general-data/form.jsp" />
    </div>
    <div id="skills" class="col s12 tab-content">
        <% if(!isCreate){ %>
        <jsp:include page="./skills/form.jsp" />
        <jsp:include page="./skills/index.jsp" />
        <% } %>
    </div>
</div>
<jsp:include page="../layout/footer.jsp" />
