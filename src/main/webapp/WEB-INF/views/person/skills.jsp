<%@ page import="br.feevale.model.Person" %>
<%@ page import="br.feevale.model.Skill" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="br.feevale.model.Level" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Person person = (Person) request.getAttribute("person");
    ArrayList<Level> levels = (ArrayList<Level>) request.getAttribute("levels");
    Skill skill = (Skill) request.getAttribute("skill");

    Map<String, String> input = (Map) request.getAttribute("input");
    List<String> errors = (List) request.getAttribute("errors");
    boolean requestInvalid = errors != null && !errors.isEmpty();
%>
<div class="row">
    <form action="/skill?action=Save" method="post">
        <input name="id" type="hidden" value="<%= skill.exists() ? skill.getId() : "" %>" />
        <input name="person_id" type="hidden" value="<%= person.getId() %>" />
        <div class="row">
            <div class="input-field col s12 m6">
                <select name="tag">
                    <option value="" disabled selected>Selecione</option>
                    <option value="1">Option 1</option>
                    <option value="2">Option 2</option>
                    <option value="3">Option 3</option>
                </select>
                <label for="tag">Tag</label>
            </div>
            <div class="input-field col s12 m6">
                <select name="level">
                    <option value="" disabled selected>Selecione</option>
                    <% for(Level level : levels){ %>
                    <option value="<%= level.getId() %>"><%= level.getName() %></option>
                    <% } %>
                </select>
                <label for="level">Nível</label>
            </div>
        </div>
        <div class="row">
            <button class="btn waves-effect waves-light right" type="submit">
                Salvar<i class="material-icons right">send</i>
            </button>
            <a href="/person" class="btn waves-effect waves-light right btn-mr">
                Voltar<i class="material-icons right">arrow_back</i>
            </a>
        </div>
    </form>
</div>
<div class="row">
    lista de skills
</div>