<%@ page import="br.feevale.model.Person" %>
<%@ page import="br.feevale.model.Skill" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Person person = (Person) request.getAttribute("person");
%>
<% if(!person.getSkills().isEmpty()){ %>
<div class="row">
    <table class="responsive-table highlight">
        <thead>
        <tr>
            <th>Competência</th>
            <th>Nível</th>
            <th class="center-align">Ações</th>
        </tr>
        </thead>
        <tbody>
        <% for(Skill skill : person.getSkills()){ %>
        <tr>
            <td><%= skill.getTag().getName() %></td>
            <td><%= skill.getLevel().getName() %></td>
            <td class="center-align">
                <i class="material-icons dropdown-trigger pointer" data-target='dropdown_action_<%= skill.getId() %>'>more_horiz</i>
                <ul id='dropdown_action_<%= skill.getId() %>' class='dropdown-content'>
                    <li><a href="/person?action=Edit&id=<%= person.getId() %>&skill_id=<%= skill.getId() %>">Editar</a></li>
                    <li><a href="#modal_delete_<%= skill.getId() %>" class="modal-trigger">Excluir</a></li>
                </ul>
            </td>
        </tr>
        <div id="modal_delete_<%= skill.getId() %>" class="modal">
            <div class="modal-content">
                <p>Você tem certeza que deseja <strong>excluir</strong> esta competência da pessoa <%= person.getName() %>?</p>
            </div>
            <div class="modal-footer">
                <button class="btn modal-action modal-close waves-effect waves-red btn-mr">Cancelar<i class="material-icons right">cancel</i></button>
                <a href="/skill?action=Delete&skill_id=<%= skill.getId() %>&person_id=<%= person.getId() %>" class="btn modal-action modal-close waves-effect waves-green">Confirmar<i class="material-icons right">check</i></a>
            </div>
        </div>
        <% } %>

        </tbody>
    </table>
</div>
<% }else{ %>
<div class="row">
    <div class="card-panel blue lighten-4 blue-text"><strong>Não há competências.</strong></div>
</div>
<% } %>
<div class="row">
    <a href="/person" class="btn waves-effect waves-light right btn-mr">
        Voltar<i class="material-icons right">arrow_back</i>
    </a>
</div>