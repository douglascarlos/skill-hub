<%@page import="java.util.List" %>
<%@page import="br.feevale.dao.PersonDAO" %>
<%@page import="br.feevale.model.Person" %>
<%
    PersonDAO dao = new PersonDAO();
    List<Person> people = dao.list();
%>
<html>
<body>
<h2>Hello World!</h2>
<table border="1">
    <thead>
    <tr>
        <th>Id</th>
        <th>Nome</th>
        <th>Ações</th>
    </tr>
    </thead>
    <tbody>
    <%
        for (Person person : people) {
    %>
    <tr>
        <td><%= person.getId() %>
        </td>
        <td><%= person.getName() %>
        </td>
        <td>
            <a href="#">Editar</a>
            <a href="#">Deletar</a>
        </td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
</body>
</html>
