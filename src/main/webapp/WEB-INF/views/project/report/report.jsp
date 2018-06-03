<%@ page import="br.feevale.model.Project" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Project project = (Project) request.getAttribute("project");
    String radarReportData = (String) request.getAttribute("radarReportData");

    Map<String, String> input = (Map) request.getAttribute("input");
    List<String> errors = (List) request.getAttribute("errors");
    boolean requestInvalid = errors != null && !errors.isEmpty();
%>
<% if(!project.getMembers().isEmpty()){ %>
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
    <div class="card-panel blue lighten-4 blue-text"><strong>Não há membros para gerar relatório.</strong></div>
</div>
<% } %>
<div class="row">
    <a href="/project" class="btn waves-effect waves-light right btn-mr">
        Voltar<i class="material-icons right">arrow_back</i>
    </a>
</div>