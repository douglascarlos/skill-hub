<%@ page import="br.feevale.model.Person" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Person person = (Person) request.getAttribute("person");

    Map<String, String> input = (Map) request.getAttribute("input");
    List<String> errors = (List) request.getAttribute("errors");
    boolean requestInvalid = errors != null && !errors.isEmpty();
%>
<jsp:include page="../layout/header.jsp" />
<div class="section">
    <h5>Criar Pessoa</h5>
</div>
<jsp:include page="../layout/messages/messages.jsp" />
<div class="row">
    <form action="/person?action=Save" method="post">
        <input name="id" type="hidden" value="<%= person.getId() == 0 ? "" : person.getId() %>" />
        <div class="row">
            <div class="input-field col s12">
                <input name="name" id="name" type="text" required minlength="3" maxlength="255" class="validate" value="<%= requestInvalid ? input.get("name") : person.getName() == null ? "" : person.getName() %>">
                <label for="name">Nome</label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s6">
                <input name="enrollment_number" id="enrollment_number" type="number" required min="0" minlength="7" maxlength="7" class="validate" value="<%= requestInvalid ? input.get("enrollment_number") : person.getEnrollmentNumber() == 0 ? "" : person.getEnrollmentNumber() %>">
                <label for="enrollment_number">Matrícula</label>
            </div>
            <div class="input-field col s6">
                <input name="email" id="email" type="email" required minlength="3" maxlength="255" class="validate" value="<%= requestInvalid ? input.get("email") : person.getEmail() == null ? "" : person.getEmail() %>">
                <label for="email">E-mail</label>
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
<jsp:include page="../layout/footer.jsp" />
