<%@ page import="br.feevale.model.Model" %>
<%@ page import="java.util.List" %>
<%@ page import="br.feevale.presenter.collectionItem.CollectionItemPresenter" %>
<%@ page import="br.feevale.model.Level" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String filter = (String) request.getAttribute("filter");
    String[] filterByModelInput = (String[]) request.getAttribute("filter_by_model");
    String[] filterByLevelInput = (String[]) request.getAttribute("filter_by_level");

    List<Model> models = (List<Model>) request.getAttribute("models");
    CollectionItemPresenter collectionItemPresenter = (CollectionItemPresenter) request.getAttribute("collectionItemPresenter");
    List<Level> levels = (List<Level>) request.getAttribute("levels");

    Map<String, String> input = (Map) request.getAttribute("input");
    List<String> errors = (List) request.getAttribute("errors");
    boolean requestInvalid = errors != null && !errors.isEmpty();
%>
<jsp:include page="../layout/header.jsp" />
<jsp:include page="../layout/add-button-fixed.jsp" />
<jsp:include page="../layout/messages/messages.jsp" />

<form action="/search" method="get" class="mt25">
    <div class="row">
        <div class="input-field col s12">
            <input name="filter" id="filter" type="text" class="validate" value="<%= requestInvalid ? input.get("filter") : filter == null ? "" : filter %>" required minlength="3" maxlength="255">
            <label for="filter">Filtro</label>
        </div>
        <div class="col s12">
            <ul class="collapsible" id="filters">
                <li>
                    <div class="collapsible-body">

                        <div class="row">
                            <div class="input-field col s12 m6">
                                <select id="filter_by_model" name="by_model[]" multiple>
                                    <option value="Tag"
                                        <% if(filterByModelInput != null) for(int index = 0; index < filterByModelInput.length; index++) {
                                            if(filterByModelInput[index].equals("Tag")){ %>
                                                selected
                                            <% }
                                        } %>>Tag</option>

                                    <option value="Person"
                                        <% if(filterByModelInput != null) for(int index = 0; index < filterByModelInput.length; index++) {
                                            if(filterByModelInput[index].equals("Person")){ %>
                                        selected
                                        <% }
                                        } %>>Pessoa</option>

                                    <option value="Project"
                                        <% if(filterByModelInput != null) for(int index = 0; index < filterByModelInput.length; index++) {
                                            if(filterByModelInput[index].equals("Project")){ %>
                                        selected
                                        <% }
                                        } %>>Projeto</option>
                                </select>
                                <label>O que você está procurando?</label>
                            </div>
                            <div class="input-field col s12 m6">
                                <select name="by_level[]" multiple>
                                    <% for(Level level : levels){ %>
                                    <option value="<%= level.getId() %>"
                                            <% if(filterByLevelInput != null) for(int index = 0; index < filterByLevelInput.length; index++) {
                                                if(filterByLevelInput[index].equals(""+level.getId())){ %>
                                            selected
                                            <% }
                                            } %>><%= level.getName() %></option>
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
                Mostrar Filtros <i class="material-icons right">filter_list</i>
            </button>
            <button class="btn waves-effect waves-light right btn-mr" type="button" id="btn_close">
                Ocultar Filtros <i class="material-icons right">filter_list</i>
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

<% }else if(!requestInvalid) { %>
<div class="row">
    <div class="card-panel blue lighten-4 blue-text"><strong>Não há resultados.</strong></div>
</div>
<% } %>
<script type="text/javascript">
    $(document).ready(function(){
        $('#btn_close').hide();

        $('#btn_open').on('click', function(){
            $('#btn_close').show();
            $('#btn_open').hide();
            $('.collapsible').collapsible('open');
        });

        $('#btn_close').on('click', function(){
            $('#btn_close').hide();
            $('#btn_open').show();
            $('.collapsible').collapsible('close');
        });

    });
</script>
<jsp:include page="../layout/footer.jsp" />
