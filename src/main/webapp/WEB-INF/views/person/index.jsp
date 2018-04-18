<%@ page import="br.feevale.model.Person" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String name = (String) request.getAttribute("name");
    List<Person> people = (List<Person>) request.getAttribute("people");
%>
<jsp:include page="../layout/header.jsp" />
<div class="section">
    <h5>Pessoas</h5>
</div>
<jsp:include page="../layout/messages/messages.jsp" />
<div class="row">
    <form action="/person" method="get">
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
            <a href="/person" class="btn waves-effect waves-light right btn-mr">
                Limpar<i class="material-icons right">clear_all</i>
            </a>
            <a href="/person?action=Create" class="btn waves-effect waves-light right btn-mr">
                Criar Novo<i class="material-icons right">add</i>
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
            <th>E-mail</th>
            <th class="center-align">Ações</th>
        </tr>
        </thead>
        <tbody>
        <% for(Person person : people){ %>
            <tr>
                <td><%= person.getEnrollmentNumberFormatted() %></td>
                <td><%= person.getName() %></td>
                <td><%= person.getEmail() %></td>
                <td class="center-align">
                    <i class="material-icons dropdown-trigger pointer" data-target='dropdown_action_<%= person.getId() %>'>more_horiz</i>
                    <ul id='dropdown_action_<%= person.getId() %>' class='dropdown-content'>
                        <li><a href="/person?action=Edit&id=<%= person.getId() %>">Editar</a></li>
                        <li><a href="#modal_delete_<%= person.getId() %>" class="modal-trigger">Excluir</a></li>
                    </ul>
                </td>
            </tr>
            <div id="modal_delete_<%= person.getId() %>" class="modal">
                <div class="modal-content">
                    <p>Você tem certeza que deseja <strong>excluir</strong> esta pessoa do sistema?</p>
                </div>
                <div class="modal-footer">
                    <button class="btn modal-action modal-close waves-effect waves-red btn-mr">Cancelar<i class="material-icons right">cancel</i></button>
                    <a href="/person?action=Delete&id=<%= person.getId() %>" class="btn modal-action modal-close waves-effect waves-green">Confirmar<i class="material-icons right">check</i></a>
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
