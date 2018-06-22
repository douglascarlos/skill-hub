<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="./layout/header.jsp" />

<div class="row center search-logo">
    <img src="/images/logo.png" alt="Skill Hub">
</div>

<div class="search-form">
    <form action="/search" method="get" class="col s12">
        <input type="checkbox" name="by_model[]" value="Tag" checked>
        <input type="checkbox" name="by_model[]" value="Person" checked>
        <input type="checkbox" name="by_model[]" value="Project" checked>
        <input type="checkbox" name="by_level[]" value="1" checked>
        <input type="checkbox" name="by_level[]" value="2" checked>
        <input type="checkbox" name="by_level[]" value="3" checked>
        <div class="row">
            <div class="row">
                <div class="input-field col s12 m12">
                    <input name="filter" id="filter" type="text" minlength="3" maxlength="255" required>
                    <label for="filter">Pesquisar no Skill Hub</label>
                </div>
            </div>
            <div class="col s12 center">
                <button class="btn waves-effect waves-light" type="submit">
                    Pesquisar<i class="material-icons right">search</i>
                </button>
            </div>
        </div>
    </form>
</div>

<script type="text/javascript">
    $('#filter').focus();
</script>

<jsp:include page="./layout/footer.jsp" />