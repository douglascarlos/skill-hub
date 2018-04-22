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
    <ul class="tabs">
        <li class="tab col s6"><a href="#general-data">Dados Gerais</a></li>
        <li class="tab col s6"><a href="#attach-tags">Tags</a></li>
    </ul>
    <div id="general-data" class="col s12 tab-content">
        <jsp:include page="./general-data.jsp" />
    </div>
    <div id="attach-tags" class="col s12 tab-content">
        <jsp:include page="./attach-tags.jsp" />
    </div>
</div>
<jsp:include page="../layout/footer.jsp" />
