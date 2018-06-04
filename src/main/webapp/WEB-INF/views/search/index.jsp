<%@ page import="br.feevale.model.Model" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String filter = (String) request.getAttribute("filter");
    List<Model> models = (List<Model>) request.getAttribute("models");
%>
<jsp:include page="../layout/header.jsp" />
<jsp:include page="../layout/add-button-fixed.jsp" />
<div class="section">
    <h5>Pesquisa</h5>
</div>
<jsp:include page="../layout/messages/messages.jsp" />
<div class="row">
    <form action="/search" method="get">
        <div class="row">
            <div class="input-field col s12">
                <input name="filter" id="filter" type="text" class="validate" value="<%= filter %>">
                <label for="filter">Filtro</label>
            </div>
        </div>
        <div class="row">
            <button class="btn waves-effect waves-light right" type="submit">
                Buscar<i class="material-icons right">search</i>
            </button>
            <a href="/search" class="btn waves-effect waves-light right btn-mr">
                Limpar<i class="material-icons right">clear_all</i>
            </a>
        </div>
    </form>
</div>

<% if(!models.isEmpty()){ %>
<div class="row">
    <table class="responsive-table highlight">
        <thead>
        <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>Classe</th>
        </tr>
        </thead>
        <tbody>
        <% for(Model model : models){ %>
            <tr>
                <td><%= model.getId() %></td>
                <td><%= model.getName() %></td>
                <td><%= model.getClass() %></td>
            </tr>
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
