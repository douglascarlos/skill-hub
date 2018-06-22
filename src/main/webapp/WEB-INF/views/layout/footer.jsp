<%@ page import="java.util.List" %>
<%
    List<String> errors = (List) request.getAttribute("errors");
    boolean requestInvalid = errors != null && !errors.isEmpty();
%>
    </div>
</main>
<footer class="page-footer">
    <div class="container">
        <div class="row">
            <div class="col l5 s12">
                <p>
                    <img class="" height="50" src="/images/logo.png" alt="Skill Hub">
                </p>
                <p>
                    Desenvolvido por <a target="_blank" href="https://github.com/douglascarlos">Douglas Carlos</a> para disciplina de Projeto Integrador da Universidade Feevale.
                </p>
            </div>
            <div class="col l5 offset-l2 s12">
                <h5>Links</h5>
                <ul>
                    <li><a target="_blank" href="https://github.com/douglascarlos/skill-hub">Github</a></li>
                    <li><a target="_blank" href="https://www.feevale.br/">Feevale</a></li>
                </ul>
            </div>
        </div>
    </div>
    <div class="footer-copyright">
        <div class="container">
            Copyright &copy; 2018
            <span class="right">v1.0.0-alpha.1</span>
        </div>
    </div>
</footer>
</body>
</html>
