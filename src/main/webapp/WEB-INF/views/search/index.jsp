<%@ page import="br.feevale.model.Model" %>
<%@ page import="java.util.List" %>
<%@ page import="br.feevale.presenter.collectionItem.CollectionItemPresenter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String filter = (String) request.getAttribute("filter");
    List<Model> models = (List<Model>) request.getAttribute("models");
    CollectionItemPresenter collectionItemPresenter = (CollectionItemPresenter) request.getAttribute("collectionItemPresenter");
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

<ul class="collection">
    <% for(Model model : models){ %>
        <%= collectionItemPresenter.generate(model) %>
    <% } %>
</ul>

<% }else{ %>
<div class="row">
    <div class="card-panel blue lighten-4 blue-text"><strong>Não há resultados.</strong></div>
</div>
<% } %>
<jsp:include page="../layout/footer.jsp" />
