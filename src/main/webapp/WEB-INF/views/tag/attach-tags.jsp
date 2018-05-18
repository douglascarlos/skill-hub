<%@ page import="br.feevale.model.Tag" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Tag tag = (Tag) request.getAttribute("tag");
    List<Tag> tagsToAttach = (List<Tag>) request.getAttribute("tagsToAttach");

    Map<String, String> input = (Map) request.getAttribute("input");
    List<String> errors = (List) request.getAttribute("errors");
    boolean requestInvalid = errors != null && !errors.isEmpty();
%>

<% if(!tag.getChildren().isEmpty() || !tagsToAttach.isEmpty()){ %>
<form action="/tag?action=AttachTags" method="post">
    <input name="id" type="hidden" value="<%= tag.exists() ? tag.getId() : "" %>" />
    <div class="row">
        <div class="row">
            <table class="responsive-table highlight">
                <thead>
                <tr>
                    <th>Nome</th>
                    <th class="center-align">Selecionar</th>
                </tr>
                </thead>
                <tbody>
                <% for(Tag child : tag.getChildren()){ %>
                <tr>
                    <td>
                        <a href="/tag?action=Edit&id=<%= child.getId() %>">
                            <%= child.getName() %>
                        </a>
                    </td>
                    <td class="center-align">
                        <label>
                            <input name="children[]" type="checkbox" value="<%= child.getId() %>" checked="checked" />
                            <span></span>
                        </label>
                    </td>
                </tr>
                <% } %>
                <% for(Tag tagToAttach : tagsToAttach){ %>
                <tr>
                    <td>
                        <a href="/tag?action=Edit&id=<%= tagToAttach.getId() %>">
                            <%= tagToAttach.getName() %>
                        </a>
                    </td>
                    <td class="center-align">
                        <label>
                            <input name="children[]" type="checkbox" value="<%= tagToAttach.getId() %>"  />
                            <span></span>
                        </label>
                    </td>
                </tr>
                <% } %>
                </tbody>
            </table>
        </div>
        <div class="row">
            <button class="btn waves-effect waves-light right" type="submit">
                Salvar<i class="material-icons right">send</i>
            </button>
            <a href="/tag" class="btn waves-effect waves-light right btn-mr">
                Voltar<i class="material-icons right">arrow_back</i>
            </a>
        </div>
    </div>
</form>
<% }else{ %>
<div class="row">
    <div class="card-panel blue lighten-4 blue-text"><strong>Não há tags vinculadoas ou tags para vincular.</strong></div>
</div>
<% } %>
