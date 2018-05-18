<%@ page import="br.feevale.model.Project" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="br.feevale.model.Person" %>
<%@ page import="br.feevale.model.Member" %>
<%@ page import="br.feevale.model.Skill" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Member member = (Member) request.getAttribute("member");

    Map<String, String> input = (Map) request.getAttribute("input");
    List<String> errors = (List) request.getAttribute("errors");
    boolean requestInvalid = errors != null && !errors.isEmpty();
%>
<jsp:include page="../../layout/header.jsp" />
<div class="section">
    <h5>Novo membro</h5>
</div>
<jsp:include page="../../layout/messages/messages.jsp" />
<div class="row">
    <form action="/member" method="get">
        <input type="hidden" name="action" value="SelectPerson">
        <input name="id" type="hidden" value="<%= member.exists() ? member.getId() : "" %>" />
        <input name="project_id" type="hidden" value="<%= member.getProject().exists() ? member.getProject().getId() : "" %>" />
        <div class="row">
            <div class="input-field col s12 m12">
                <input disabled id="project_name" type="text" value="<%= member.getProject().getName() %>">
                <label for="project_name">Projeto</label>
            </div>
        </div>
        <% if(member.exists()){ %>
        <div class="row">
            <div class="input-field col s12 m12">
                <input disabled id="person_name" type="text" value="<%= member.getPerson().getName() %>">
                <label for="person_name">Pessoa</label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12 m12">
                <label for="role" class="active">Competências</label>
                <div class="chips chips-initial input-field">
                <% for(Skill skill : member.getSkills()){ %>
                    <div class="chip" tabindex="0">
                        <%= skill.getTag().getName() + " - " + skill.getLevel().getName() %>
                    </div>
                <% } %>
                </div>
            </div>
        </div>
        <% } %>
        <div class="row">
            <div class="input-field col s12 m12">
                <input name="role" id="role" type="text" required minlength="3" maxlength="255" class="validate" value="<%= requestInvalid ? input.get("role") : member.getRole() == null ? "" : member.getRole() %>">
                <label for="role">Função</label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12 m6">
                <input name="start_date" id="start_date" type="text" required pattern="(0[1-9]|1[0-9]|2[0-9]|3[01])/(0[1-9]|1[012])/[0-9]{4}" class="validate datepicker" value="<%= requestInvalid ? input.get("start_date") : member.getStartDate() == null ? "" : member.getStartDateFormatted() %>">
                <label for="start_date">Início da participação</label>
            </div>
            <div class="input-field col s12 m6">
                <input name="end_date" id="end_date" type="text" required pattern="(0[1-9]|1[0-9]|2[0-9]|3[01])/(0[1-9]|1[012])/[0-9]{4}" class="validate datepicker" value="<%= requestInvalid ? input.get("end_date") : member.getEndDate() == null ? "" : member.getEndDateFormatted() %>">
                <label for="end_date">Fim da participação</label>
            </div>
        </div>
        <div class="row">
            <button class="btn waves-effect waves-light right" type="submit">
                <% if(member.exists()) { %>
                    Salvar<i class="material-icons right">send</i>
                <% } else { %>
                    Avançar<i class="material-icons right">send</i>
                <% } %>
            </button>
            <a href="/project?action=Edit&id=<%= member.getProject().getId() %>#members" class="btn waves-effect waves-light right btn-mr">
                Voltar<i class="material-icons right">arrow_back</i>
            </a>
        </div>
    </form>
</div>
<jsp:include page="../../layout/footer.jsp" />
