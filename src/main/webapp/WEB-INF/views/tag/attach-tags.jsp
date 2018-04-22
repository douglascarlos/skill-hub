<%@ page import="br.feevale.model.Tag" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Tag> tagsToAttach = (List<Tag>) request.getAttribute("tagsToAttach");

    Map<String, String> input = (Map) request.getAttribute("input");
    List<String> errors = (List) request.getAttribute("errors");
    boolean requestInvalid = errors != null && !errors.isEmpty();
%>
<% if(!tagsToAttach.isEmpty()){ %>
<div class="row">
    <form action="/tag?action=AttachTags" method="post">
        <div class="row">
            <table class="responsive-table highlight">
                <thead>
                <tr>
                    <th>Nome</th>
                    <th class="center-align">Vincular</th>
                </tr>
                </thead>
                <tbody>
                <% for(Tag tagToAttach : tagsToAttach){ %>
                <tr>
                    <td><%= tagToAttach.getName() %></td>
                    <td class="center-align">
                        <label>
                            <input name="attach[]" type="checkbox" value="<%= tagToAttach.getId() %>"  />
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
    </form>
</div>
<% }else{ %>
<div class="roe">
    <div class="card-panel blue lighten-4 blue-text"><strong>Não há tags para vincular.</strong></div>
</div>
<% } %>