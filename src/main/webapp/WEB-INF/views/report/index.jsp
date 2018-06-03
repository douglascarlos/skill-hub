<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../layout/header.jsp" />
<jsp:include page="../layout/add-button-fixed.jsp" />
<%
    String radarReportGeneralTagsCount = (String) request.getAttribute("radarReportGeneralTagsCount");
    String radarReportGeneralTagsAvarage = (String) request.getAttribute("radarReportGeneralTagsAvarage");
%>
<div class="section container">
    <h5>Relatórios</h5>
</div>
<jsp:include page="../layout/messages/messages.jsp" />
<div class="row">
    <div class="col s12 m6">
        <h6>Quantidade de pessoas utilizando as tags</h6>
        <canvas id="canvas_polar_general_tags_count"></canvas>
    </div>
    <div class="col s12 m6">
        <h6>Média do nível das tags utilizadas</h6>
        <canvas id="canvas_polar_general_tags_avarage"></canvas>
    </div>
</div>
<script type="text/javascript">
    $("#container").removeClass("container");
    var canvasPolarGeneralTagsCount = $("#canvas_polar_general_tags_count");
    var canvasPolarGeneralTagsAvarage = $("#canvas_polar_general_tags_avarage");
    var radarReportGeneralTagsCount = new Chart(canvasPolarGeneralTagsCount, <%= radarReportGeneralTagsCount %>);
    var radarReportGeneralTagsAvarage = new Chart(canvasPolarGeneralTagsAvarage, <%= radarReportGeneralTagsAvarage %>);
</script>
<jsp:include page="../layout/footer.jsp" />
