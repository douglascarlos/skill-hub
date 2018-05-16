<%@ page import="br.feevale.model.Project" %>
<%@ page import="br.feevale.model.Member" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="br.feevale.model.Person" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Project project = (Project) request.getAttribute("project");
    String name = (String) request.getAttribute("name");
    ArrayList<Person> people = (ArrayList<Person>) request.getAttribute("people");
%>
<jsp:include page="../../layout/header.jsp" />
<div class="section">
    <h5>Selecionar novo membro do projeto <%= project.getName() %></h5>
</div>
<jsp:include page="../../layout/messages/messages.jsp" />
<div class="row">
    <form action="/member" method="get">
        <input type="hidden" name="action" value="Create">
        <input type="hidden" name="project_id" value="<%= project.getId() %>">
        <div class="row">
            <div class="input-field col s12">
                <input name="name" id="name" type="text" class="validate" value="<%= name %>">
                <label for="name">Nome</label>
            </div>
        </div>
        <div class="row">
            <button class="btn waves-effect waves-light right" type="submit">
                Buscar<i class="material-icons right">search</i>
            </button>
            <a href="/member?action=Create&project_id=<%= project.getId() %>" class="btn waves-effect waves-light right btn-mr">
                Limpar<i class="material-icons right">clear_all</i>
            </a>
        </div>
    </form>
</div>
<% if(!people.isEmpty()){ %>
<div class="row">
    <table class="responsive-table highlight">
        <thead>
        <tr>
            <th>Matricula</th>
            <th>Nome</th>
            <th class="center-align">Selecionar</th>
        </tr>
        </thead>
        <tbody>
        <% for(Person person : people){ %>
        <tr>
            <td><%= person.getEnrollmentNumberFormatted() %></td>
            <td><%= person.getName() %></td>
            <td class="center-align">
                <label>
                    <input class="with-gap" name="person_id" type="radio"  />
                    <span></span>
                </label>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>
<% }else{ %>
<div class="row">
    <div class="card-panel blue lighten-4 blue-text"><strong>Não há pessoas para selecionar.</strong></div>
</div>
<% } %>
<div class="row">
    <button class="btn waves-effect waves-light right" type="submit">
        Avançar<i class="material-icons right">send</i>
    </button>
    <a href="/project?action=Edit&id=<%= project.getId() %>#members" class="btn waves-effect waves-light right btn-mr">
        Voltar<i class="material-icons right">arrow_back</i>
    </a>
</div>
<jsp:include page="../../layout/footer.jsp" />
