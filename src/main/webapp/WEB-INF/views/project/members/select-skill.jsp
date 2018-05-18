<%@ page import="br.feevale.model.Member" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="br.feevale.model.Person" %>
<%@ page import="br.feevale.model.Skill" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Member member = (Member) request.getAttribute("member");
%>
<jsp:include page="../../layout/header.jsp" />
<div class="section">
    <h5>Selecionar competências aplicadas pelo(a) <%= member.getPerson().getName() %></h5>
</div>
<jsp:include page="../../layout/messages/messages.jsp" />
<form action="/member" method="post">
    <input type="hidden" name="action" value="Save">
    <input type="hidden" name="project_id" value="<%= member.getProject().getId() %>">
    <input type="hidden" name="role" value="<%= member.getRole() %>">
    <input type="hidden" name="start_date" value="<%= member.getStartDateFormatted() %>">
    <input type="hidden" name="end_date" value="<%= member.getEndDateFormatted() %>">
    <input type="hidden" name="person_id" value="<%= member.getPerson().getId() %>">
    <% if(!member.getPerson().getSkills().isEmpty()){ %>
    <div class="row">
        <table class="responsive-table highlight">
            <thead>
            <tr>
                <th>Competência</th>
                <th>Nível</th>
                <th class="center-align">Selecionar</th>
            </tr>
            </thead>
            <tbody>
            <% for(Skill skill : member.getPerson().getSkills()){ %>
            <tr>
                <td><%= skill.getTag().getName() %></td>
                <td><%= skill.getLevel().getName() %></td>
                <td class="center-align">
                    <label>
                        <input name="skill_id[]" type="checkbox" value="<%= skill.getId() %>"  />
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
        <div class="card-panel blue lighten-4 blue-text">
            <strong>Não há competências para selecionar.</strong>
            <a href="/person?action=Edit&id=<%= member.getPerson().getId() %>" class="blue-text underline">Clique aqui</a> para acessar o cadastro desta pessoa.
        </div>
    </div>
    <% } %>
    <div class="row">
        <button class="btn waves-effect waves-light right" type="submit">
            Salvar<i class="material-icons right">send</i>
        </button>
        <a href="/member?action=SelectPerson&id=<%= member.getId() %>&project_id=<%= member.getProject().getId() %>&role=<%= member.getRole() %>&start_date=<%= member.getStartDateFormattedUrl() %>&end_date=<%= member.getEndDateFormattedUrl() %>" class="btn waves-effect waves-light right btn-mr">
            Voltar<i class="material-icons right">arrow_back</i>
        </a>
        <a href="/project?action=Edit&id=<%= member.getProject().getId() %>#members" class="btn waves-effect waves-light right btn-mr">
            Cancelar<i class="material-icons right">cancel</i>
        </a>
    </div>
</form>
<jsp:include page="../../layout/footer.jsp" />
