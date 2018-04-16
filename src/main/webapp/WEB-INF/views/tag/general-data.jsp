<%@ page import="br.feevale.model.Tag" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Tag tag = (Tag) request.getAttribute("tag");

    Map<String, String> input = (Map) request.getAttribute("input");
    List<String> errors = (List) request.getAttribute("errors");
    boolean requestInvalid = errors != null && !errors.isEmpty();
%>
<jsp:include page="../layout/header.jsp" />
<div class="section">
    <h5><%= tag.exists() ? "Editar" : "Criar" %> Tag</h5>
</div>
<jsp:include page="../layout/messages/messages.jsp" />
<div class="row">
    <form action="/tag?action=Save" method="post">
        <input name="id" type="hidden" value="<%= tag.exists() ? tag.getId() : "" %>" />
        <div class="row">
            <div class="input-field col s12">
                <input name="name" id="name" type="text" required minlength="3" maxlength="255" class="validate" value="<%= requestInvalid ? input.get("name") : tag.getName() == null ? "" : tag.getName() %>">
                <label for="name">Nome</label>
            </div>
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
<jsp:include page="../layout/footer.jsp" />
