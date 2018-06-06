<%@ page import="br.feevale.model.Project" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String name = (String) request.getAttribute("name");
    List<Project> projects = (List<Project>) request.getAttribute("projects");
%>
<jsp:include page="../layout/header.jsp" />
<jsp:include page="../layout/add-button-fixed.jsp" />
<div class="section">
    <h5>Projetos</h5>
</div>
<jsp:include page="../layout/messages/messages.jsp" />
<form action="/project" method="get">
    <div class="row">
        <div class="input-field col s12">
            <input name="name" id="name" type="text" class="validate" value="<%= name %>">
            <label for="name">Nome</label>
        </div>
        <div class="col s12">
            <button class="btn waves-effect waves-light right" type="submit">
                Buscar<i class="material-icons right">search</i>
            </button>
            <a href="/project" class="btn waves-effect waves-light right btn-mr">
                Limpar<i class="material-icons right">clear_all</i>
            </a>
        </div>
    </div>
</form>

<% if(!projects.isEmpty()){ %>
<div class="row">
    <table class="responsive-table highlight">
        <thead>
        <tr>
            <th>Nome</th>
            <th class="center-align">Ações</th>
        </tr>
        </thead>
        <tbody>
        <% for(Project project : projects){ %>
            <tr>
                <td><%= project.getName() %></td>
                <td class="center-align">
                    <i class="material-icons dropdown-trigger pointer" data-target='dropdown_action_<%= project.getId() %>'>more_horiz</i>
                    <ul id='dropdown_action_<%= project.getId() %>' class='dropdown-content-index-project'>
                        <li><a href="/project?action=Edit&id=<%= project.getId() %>#general-data">Dados Gerais</a></li>
                        <li><a href="/project?action=Edit&id=<%= project.getId() %>#members">Membros</a></li>
                        <li><a href="/member?action=Create&project_id=<%= project.getId() %>">Adicionar Membro</a></li>
                        <li><a href="/project?action=Edit&id=<%= project.getId() %>#report">Relatório Gráfico</a></li>
                        <li><a href="#modal_delete_<%= project.getId() %>" class="modal-trigger">Excluir</a></li>
                    </ul>
                </td>
            </tr>
            <div id="modal_delete_<%= project.getId() %>" class="modal">
                <div class="modal-content">
                    <p>Você tem certeza que deseja <strong>excluir</strong> este projeto do sistema?</p>
                </div>
                <div class="modal-footer">
                    <button class="btn modal-action modal-close waves-effect waves-red btn-mr">Cancelar<i class="material-icons right">cancel</i></button>
                    <a href="/project?action=Delete&id=<%= project.getId() %>" class="btn modal-action modal-close waves-effect waves-green">Confirmar<i class="material-icons right">check</i></a>
                </div>
            </div>
        <% } %>

        </tbody>
    </table>
</div>
<% }else{ %>
<div class="row">
    <div class="card-panel blue lighten-4 blue-text"><strong>Não há resultados.</strong></div>
</div>
<% } %>
<jsp:include page="../layout/footer.jsp" />
