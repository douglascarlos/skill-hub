<%@ page import="br.feevale.model.Model" %>
<%@ page import="java.util.List" %>
<%@ page import="br.feevale.presenter.collectionItem.CollectionItemPresenter" %>
<%@ page import="br.feevale.model.Level" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String filter = (String) request.getAttribute("filter");
    List<Model> models = (List<Model>) request.getAttribute("models");
    CollectionItemPresenter collectionItemPresenter = (CollectionItemPresenter) request.getAttribute("collectionItemPresenter");
    List<Level> levels = (List<Level>) request.getAttribute("levels");
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
            <ul class="collapsible" id="filters">
                <li>
                    <div class="collapsible-body">

                        <div class="row">
                            <div class="input-field col s12 m6">
                                <select name="by_model[]" multiple>
                                    <option value="Tag" selected>Tag</option>
                                    <option value="Person" selected>Pessoa</option>
                                    <option value="Project" selected>Projeto</option>
                                </select>
                                <label>O que você está procurando?</label>
                            </div>
                            <div class="input-field col s12 m6">
                                <select name="by_level[]" multiple>
                                    <% for(Level level : levels){ %>
                                    <option value="<%= level.getId() %>" selected><%= level.getName() %></option>
                                    <% } %>
                                </select>
                                <label>Qual nível de conhecimento?</label>
                            </div>
                        </div>

                    </div>
                </li>
            </ul>
        </div>
        <div class="col s12">
            <button class="btn waves-effect waves-light right" type="submit">
                Pesquisar<i class="material-icons right">search</i>
            </button>
            <button class="btn waves-effect waves-light right btn-mr" type="button" id="btn_open">
                Pesquisa Avançada<i class="material-icons right">filter_list</i>
            </button>
            <button class="btn waves-effect waves-light right btn-mr" type="button" id="btn_close">
                Remover Filtros Avançados <i class="material-icons right">filter_list</i>
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
<script type="text/javascript">
    $(document).ready(function(){
        $('#btn_close').hide();
        // $('#filters').hide();

        $('#btn_open').on('click', function(){
            // $('#filters').show();
            $('#btn_close').show();
            $('#btn_open').hide();
            $('.collapsible').collapsible('open');
        });

        $('#btn_close').on('click', function(){
            $('#btn_close').hide();
            $('#btn_open').show();
            $('.collapsible').collapsible('close');
            // $('#filters').hide();
        });

    });
</script>
<jsp:include page="../layout/footer.jsp" />
