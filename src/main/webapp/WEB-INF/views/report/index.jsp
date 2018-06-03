<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../layout/header.jsp" />
<jsp:include page="../layout/add-button-fixed.jsp" />
<%
    String radarReportGeneralTags = (String) request.getAttribute("radarReportGeneralTags");
%>
<div class="section">
    <h5>Relatórios</h5>
</div>
<jsp:include page="../layout/messages/messages.jsp" />
<div class="row">
    <h6>Gráfico do Tipo Polar de Competências</h6>
    <canvas id="canvas_polar_general_tags"></canvas>
</div>
<script type="text/javascript">
    var canvasPolarGeneralTags = $("#canvas_polar_general_tags");
    var polarReportGeneralTags = new Chart(canvasPolarGeneralTags, <%= radarReportGeneralTags %>);
</script>
<jsp:include page="../layout/footer.jsp" />
