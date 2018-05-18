<%@ page import="br.feevale.model.Project" %>
<%@ page import="br.feevale.model.Member" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="br.feevale.model.Person" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Member member = (Member) request.getAttribute("member");
    String name = (String) request.getAttribute("name");
    ArrayList<Person> people = (ArrayList<Person>) request.getAttribute("people");
%>
<jsp:include page="../../layout/header.jsp" />
<div class="section">
    <h5>Selecionar novo membro do projeto <%= member.getProject().getName() %></h5>
</div>
<jsp:include page="../../layout/messages/messages.jsp" />
<div class="row">
    <form action="/member" method="get">
        <input type="hidden" name="action" value="SelectPerson">
        <input name="id" type="hidden" value="<%= member.exists() ? member.getId() : "" %>" />
        <input type="hidden" name="project_id" value="<%= member.getProject().getId() %>">
        <input type="hidden" name="role" value="<%= member.getRole() %>">
        <input type="hidden" name="start_date" value="<%= member.getStartDateFormatted() %>">
        <input type="hidden" name="end_date" value="<%= member.getEndDateFormatted() %>">
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
            <a href="/member?action=SelectPerson&id=<%= member.exists() ? member.getId() : "" %>&project_id=<%= member.getProject().getId() %>&role=<%= member.getRole() %>&start_date=<%= member.getStartDateFormattedUrl() %>&end_date=<%= member.getEndDateFormattedUrl() %>" class="btn waves-effect waves-light right btn-mr">
                Limpar<i class="material-icons right">clear_all</i>
            </a>
        </div>
    </form>
</div>
<form action="/member" method="get">
    <input type="hidden" name="action" value="SelectSkill">
    <input type="hidden" name="project_id" value="<%= member.getProject().getId() %>">
    <input type="hidden" name="role" value="<%= member.getRole() %>">
    <input type="hidden" name="start_date" value="<%= member.getStartDateFormatted() %>">
    <input type="hidden" name="end_date" value="<%= member.getEndDateFormatted() %>">
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
                        <input class="with-gap" name="person_id" type="radio" value="<%= person.getId() %>"  />
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
        <a href="/member?action=Create&project_id=<%= member.getProject().getId() %>&role=<%= member.getRole() %>&start_date=<%= member.getStartDateFormattedUrl() %>&end_date=<%= member.getEndDateFormattedUrl() %>" class="btn waves-effect waves-light right btn-mr">
            Voltar<i class="material-icons right">arrow_back</i>
        </a>
        <a href="/project?action=Edit&id=<%= member.getProject().getId() %>#members" class="btn waves-effect waves-light right btn-mr">
            Cancelar<i class="material-icons right">cancel</i>
        </a>
    </div>
</form>
<jsp:include page="../../layout/footer.jsp" />
