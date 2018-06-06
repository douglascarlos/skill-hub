<%@ page import="br.feevale.model.Model" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String filter = (String) request.getAttribute("filter");
    List<Model> models = (List<Model>) request.getAttribute("models");
%>
<jsp:include page="../layout/header.jsp" />
<jsp:include page="../layout/add-button-fixed.jsp" />
<jsp:include page="../layout/messages/messages.jsp" />

<form action="/search" method="get" class="mt25">
    <div class="row">
        <div class="input-field col s12">
            <input name="filter" id="filter" type="text" class="validate" value="<%= filter %>">
            <%--required minlength="3" maxlength="255"--%>
            <label for="filter">Filtro</label>
        </div>
        <div class="col s12">
            <button class="btn waves-effect waves-light right" type="submit">
                Pesquisar<i class="material-icons right">search</i>
            </button>
        </div>
    </div>
</form>

<% if(!models.isEmpty()){ %>
<%--------------------------------------------------------%>

<%--<ul class="collection">--%>
    <%--<li class="collection-item avatar">--%>
        <%--<div class="row">--%>
            <%--<div class="col s12 m6">--%>
                <%--<i class="material-icons circle black-text amber">person</i>--%>
                <%--<strong class="title">Douglas Carlos</strong>--%>
                <%--<p>--%>
                    <%--0147969 <br>--%>
                    <%--dnevescarlos@gmail.com--%>
                <%--</p>--%>
            <%--</div>--%>
            <%--<div class="col s12 m6">--%>
                <%--<div class="chip">7 Cometências</div>--%>
                <%--<div class="chip">3 Projetos</div>--%>
            <%--</div>--%>
        <%--</div>--%>
        <%--<a href="#!" class="secondary-content"><i class="material-icons">more_vert</i></a>--%>
    <%--</li>--%>
    <%--<li class="collection-item avatar">--%>
        <%--<div class="row">--%>
            <%--<div class="col s12 m6">--%>
                <%--<i class="material-icons circle black-text amber">label</i>--%>
                <%--<strong class="title">PHP</strong>--%>
            <%--</div>--%>
            <%--<div class="col s12 m6">--%>
                <%--<div class="chip">5 Sub-Tags</div>--%>
                <%--<div class="chip">7 Pessoas</div>--%>
                <%--<div class="chip">19 Projetos</div>--%>
            <%--</div>--%>
        <%--</div>--%>
        <%--<a href="#!" class="secondary-content"><i class="material-icons">more_vert</i></a>--%>
    <%--</li>--%>
    <%--<li class="collection-item avatar">--%>
        <%--<div class="row">--%>
            <%--<div class="col s12 m6">--%>
                <%--<i class="material-icons circle black-text amber">description</i>--%>
                <%--<strong class="title">Defesa contra as artes das trevas</strong>--%>
            <%--</div>--%>
            <%--<div class="col s12 m6">--%>
                <%--<div class="chip">7 Pessoas</div>--%>
                <%--<div class="chip">19 Competências</div>--%>
            <%--</div>--%>
        <%--</div>--%>
        <%--<a href="#!" class="secondary-content"><i class="material-icons">more_vert</i></a>--%>
    <%--</li>--%>
<%--</ul>--%>

<%--------------------------------------------------------%>
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
