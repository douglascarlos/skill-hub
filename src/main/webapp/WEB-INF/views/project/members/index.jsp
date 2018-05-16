<%@ page import="br.feevale.model.Project" %>
<%@ page import="br.feevale.model.Member" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Project project = (Project) request.getAttribute("project");
%>
<div class="row">
    <a href="/member?action=Create&project_id=<%= project.getId() %>" class="btn waves-effect waves-light right btn-mr">
        Novo Membro<i class="material-icons right">add</i>
    </a>
</div>
<% if(!project.getMembers().isEmpty()){ %>
<div class="row">
    <table class="responsive-table highlight">
        <thead>
        <tr>
            <th>Membro</th>
            <th>Função</th>
            <th>Período</th>
            <th class="center-align">Ações</th>
        </tr>
        </thead>
        <tbody>
        <% for(Member member : project.getMembers()){ %>
        <tr>
            <td><%= member.getPerson().getName() %></td>
            <td><%= member.getRole() %></td>
            <td><%= member.getStartDateFormatted() %> - <%= member.getEndDateFormatted() %></td>
            <td class="center-align">
                <i class="material-icons dropdown-trigger pointer" data-target='dropdown_action_<%= member.getId() %>'>more_horiz</i>
                <ul id='dropdown_action_<%= member.getId() %>' class='dropdown-content'>
                    <li><a href="/member?action=Edit&id=<%= project.getId() %>&member_id=<%= member.getId() %>">Editar</a></li>
                    <li><a href="#modal_delete_<%= member.getId() %>" class="modal-trigger">Excluir</a></li>
                </ul>
            </td>
        </tr>
        <div id="modal_delete_<%= member.getId() %>" class="modal">
            <div class="modal-content">
                <p>Você tem certeza que deseja <strong>excluir</strong> este membro?</p>
            </div>
            <div class="modal-footer">
                <button class="btn modal-action modal-close waves-effect waves-red btn-mr">Cancelar<i class="material-icons right">cancel</i></button>
                <a href="/member?action=Delete&member_id=<%= member.getId() %>&project_id=<%= project.getId() %>" class="btn modal-action modal-close waves-effect waves-green">Confirmar<i class="material-icons right">check</i></a>
            </div>
        </div>
        <% } %>

        </tbody>
    </table>
</div>
<% }else{ %>
<div class="row">
    <div class="card-panel blue lighten-4 blue-text"><strong>Não há membros.</strong></div>
</div>
<% } %>
<div class="row">
    <a href="/project" class="btn waves-effect waves-light right btn-mr">
        Voltar<i class="material-icons right">arrow_back</i>
    </a>
</div>