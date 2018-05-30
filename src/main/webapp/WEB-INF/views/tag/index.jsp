<%@ page import="br.feevale.model.Tag" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String name = (String) request.getAttribute("name");
    List<Tag> tags = (List<Tag>) request.getAttribute("tags");

    String successMessage = (String) request.getAttribute("successMessage");
%>
<jsp:include page="../layout/header.jsp" />
<jsp:include page="../layout/add-button-fixed.jsp" />
<div class="section">
    <h5>Tags</h5>
</div>
<jsp:include page="../layout/messages/messages.jsp" />
<div class="row">
    <form action="/tag" method="get">
        <div class="row">
            <div class="input-field col s12">
                <input name="name" id="name" type="text" class="validate" value="<%= name %>">
                <label for="name">Nome</label>
            </div>
        </div>
        <div class="row">
            <button class="btn waves-effect waves-light right" type="submit">
                Buscar<i class="material-icons right">search</i>
            </button>
            <a href="/tag" class="btn waves-effect waves-light right btn-mr">
                Limpar<i class="material-icons right">clear_all</i>
            </a>
        </div>
    </form>
</div>

<% if(!tags.isEmpty()){ %>
<div class="row">
    <div id="tree"></div>
    <% for(Tag tag : tags){ %>
        <%= tag.toModalOptions() %>
        <%= tag.toModalDelete() %>
    <% } %>
</div>
<script type="text/javascript">
    $(document).ready(function(){
        var tree = [
            <% for(Tag tag : tags){ %>
            <%= tag.toItemTreeView() %>
            <% } %>
        ];

        $('#tree').treeview({
            data: tree,
            levels: 1,
            showTags: true,
            enableLinks: false,
            onNodeSelected: function(event, node){
                $('#modal_options_' + node.id).modal('open');
            },
        });

        $('.btn_delete').on('click', function(event){
            var modalDelete = $(this).data('modal');
            setTimeout(function(){
                $('#'+modalDelete).modal('open');
            }, 350);
        });
    });
</script>
<% }else{ %>
<div class="row">
    <div class="card-panel blue lighten-4 blue-text"><strong>Não há resultados.</strong></div>
</div>
<% } %>
<jsp:include page="../layout/footer.jsp" />
