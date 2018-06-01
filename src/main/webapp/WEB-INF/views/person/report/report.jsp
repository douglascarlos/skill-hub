<%@ page import="br.feevale.model.Person" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Person person = (Person) request.getAttribute("person");
    String radarReportData = (String) request.getAttribute("radarReportData");

    Map<String, String> input = (Map) request.getAttribute("input");
    List<String> errors = (List) request.getAttribute("errors");
    boolean requestInvalid = errors != null && !errors.isEmpty();
%>
<% if(!person.getSkills().isEmpty()){ %>
<div class="row">
    <h6>Gráfico do Tipo Radar de Competências</h6>
    <canvas id="canvas_report"></canvas>
</div>
<script type="text/javascript">
    var ctx = $('#canvas_report');
    var chart = new Chart(ctx, <%= radarReportData %>);
</script>
<% }else{ %>
<div class="row">
    <div class="card-panel blue lighten-4 blue-text"><strong>Não há competências para gerar relatório.</strong></div>
</div>
<% } %>
<div class="row">
    <a href="/person" class="btn waves-effect waves-light right btn-mr">
        Voltar<i class="material-icons right">arrow_back</i>
    </a>
</div>