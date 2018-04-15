<%@ page import="br.feevale.model.Tag" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Tag tag = (Tag) request.getAttribute("tag");
%>
<jsp:include page="../layout/header.jsp" />
<div class="section">
    <h5>Criar Tag</h5>
</div>
<div class="row">
    <form action="/tag?action=Save" method="post">
        <input name="id" type="hidden" value="<%= tag.getId() == 0 ? "" : tag.getId() %>" />
        <div class="row">
            <div class="input-field col s6">
                <input name="name" id="name" type="text" required minlength="3" maxlength="255" class="validate" value="<%= tag.getName() == null ? "" : tag.getName() %>">
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
