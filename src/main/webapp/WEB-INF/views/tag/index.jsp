<%@ page import="br.feevale.model.Tag" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String name = (String) request.getAttribute("name");
    List<Tag> tags = (List<Tag>) request.getAttribute("tags");

    String successMessage = (String) request.getAttribute("successMessage");
%>
<jsp:include page="../layout/header.jsp" />
<div class="section">
    <h5>Tags</h5>
</div>
<jsp:include page="../layout/messages/messages.jsp" />
<div class="row">
    <form action="/tag" method="get">
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
            <a href="/tag" class="btn waves-effect waves-light right btn-mr">
                Limpar<i class="material-icons right">clear_all</i>
            </a>
            <a href="/tag?action=Create" class="btn waves-effect waves-light right btn-mr">
                Criar Novo<i class="material-icons right">add</i>
            </a>
        </div>
    </form>
</div>

<% if(!tags.isEmpty()){ %>
<div class="row">
    <table class="responsive-table highlight">
        <thead>
        <tr>
            <th>Nome</th>
            <th class="center-align">Ações</th>
        </tr>
        </thead>
        <tbody>
        <% for(Tag tag : tags){ %>
        <tr>
            <td><%= tag.getName() %></td>
            <td class="center-align">
                <i class="material-icons dropdown-trigger pointer" data-target='dropdown_action_<%= tag.getId() %>'>more_horiz</i>
                <ul id='dropdown_action_<%= tag.getId() %>' class='dropdown-content'>
                    <li><a href="/tag?action=Edit&id=<%= tag.getId() %>">Editar</a></li>
                    <li><a href="#modal_delete_<%= tag.getId() %>" class="modal-trigger">Excluir</a></li>
                </ul>
            </td>
        </tr>
        <div id="modal_delete_<%= tag.getId() %>" class="modal">
            <div class="modal-content">
                <p>Você tem certeza que deseja <strong>excluir</strong> esta tag do sistema?</p>
            </div>
            <div class="modal-footer">
                <button class="btn modal-action modal-close waves-effect waves-red btn-mr">Cancelar<i class="material-icons right">cancel</i></button>
                <a href="/tag?action=Delete&id=<%= tag.getId() %>" class="btn modal-action modal-close waves-effect waves-green">Confirmar<i class="material-icons right">check</i></a>
            </div>
        </div>
        <% } %>

        </tbody>
    </table>
</div>
<% }else{ %>
<div class="roe">
    <div class="card-panel blue lighten-4 blue-text"><strong>Não há resultados.</strong></div>
</div>
<% } %>
<jsp:include page="../layout/footer.jsp" />
