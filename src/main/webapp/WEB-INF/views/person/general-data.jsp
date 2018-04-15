<%@ page import="br.feevale.model.Person" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Person person = (Person) request.getAttribute("person");

    System.out.println("--general--name--"+request.getAttribute("name")+"--");
    System.out.println("--general--errors--"+request.getAttribute("errors")+"--");

    String name = (String) request.getAttribute("name");
    List<String> errors = (List) request.getAttribute("errors");
    boolean requestInvalid = errors != null && !errors.isEmpty();
%>
<jsp:include page="../layout/header.jsp" />
<div class="section">
    <h5>Criar Pessoa</h5>

    <% if(requestInvalid){ %>
    <ul>
    <% for(String error:errors){ %>
        <li><%= error %></li>
    <% } %>
    </ul>
    <% } %>
</div>
<div class="row">
    <div class="card-panel">
        <form action="/person?action=Save" method="post">
            <input name="id" type="hidden" value="<%= person.getId() == 0 ? "" : person.getId() %>" />
            <div class="row">
                <div class="input-field col s6">
                    <%--<input name="enrollment_number" id="enrollment_number" type="number" required min="0" class="validate" value="<%= person.getEnrollmentNumber() == 0 ? "" : person.getEnrollmentNumber() %>">--%>
                    <input name="enrollment_number" id="enrollment_number" type="text" class="validate" value="<%= person.getEnrollmentNumber() == 0 ? "" : person.getEnrollmentNumber() %>">
                    <label for="enrollment_number">Matrícula</label>
                </div>
                <div class="input-field col s6">
                    <%--<input name="name" id="name" type="text" required minlength="3" maxlength="255" class="validate" value="<%= person.getName() == null ? "" : person.getName() %>">--%>
                    <input name="name" id="name" type="text" class="validate" value="<%= person.getName() == null ? "" : person.getName() %>">
                    <label for="name">Nome</label>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s6">
                    <%--<input name="email" id="email" type="email" required minlength="3" maxlength="255" class="validate" value="<%= person.getEmail() == null ? "" : person.getEmail() %>">--%>
                    <input name="email" id="email" type="text" class="validate" value="<%= person.getEmail() == null ? "" : person.getEmail() %>">
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
</div>
<jsp:include page="../layout/footer.jsp" />
