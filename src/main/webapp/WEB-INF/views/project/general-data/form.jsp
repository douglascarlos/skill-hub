<%@ page import="br.feevale.model.Project" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Project project = (Project) request.getAttribute("project");

    Map<String, String> input = (Map) request.getAttribute("input");
    List<String> errors = (List) request.getAttribute("errors");
    boolean requestInvalid = errors != null && !errors.isEmpty();
%>
<div class="row">
    <form action="/project?action=Save" method="post">
        <input name="id" type="hidden" value="<%= project.exists() ? project.getId() : "" %>" />
        <div class="row">
            <div class="input-field col s12 m12">
                <input name="name" id="name" type="text" required minlength="3" maxlength="255" class="validate" value="<%= requestInvalid ? input.get("name") : project.getName() == null ? "" : project.getName() %>">
                <label for="name">Nome</label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12 m12">
                <textarea name="description" id="description" class="materialize-textarea character-counter validate" data-length="500" maxlength="500"><%= requestInvalid ? input.get("description") : project.getDescription() == null ? "" : project.getDescription() %></textarea>
                <label for="description">Descrição</label>
            </div>
        </div>
        <div class="row">
            <button class="btn waves-effect waves-light right" type="submit">
                Salvar<i class="material-icons right">send</i>
            </button>
            <a href="/project" class="btn waves-effect waves-light right btn-mr">
                Voltar<i class="material-icons right">arrow_back</i>
            </a>
        </div>
    </form>
</div>
