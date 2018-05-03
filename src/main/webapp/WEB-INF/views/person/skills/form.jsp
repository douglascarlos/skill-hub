<%@ page import="br.feevale.model.Person" %>
<%@ page import="br.feevale.model.Skill" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="br.feevale.model.Level" %>
<%@ page import="br.feevale.model.Tag" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Person person = (Person) request.getAttribute("person");
    ArrayList<Tag> tagsToAttach = (ArrayList<Tag>) request.getAttribute("tagsToAttach");
    ArrayList<Level> levels = (ArrayList<Level>) request.getAttribute("levels");
    Skill skill = (Skill) request.getAttribute("skill");

    Map<String, String> input = (Map) request.getAttribute("input");
    List<String> errors = (List) request.getAttribute("errors");
    boolean requestInvalid = errors != null && !errors.isEmpty();
%>
<div class="row">
    <form action="/skill?action=Save" method="post">
        <input name="skill_id" type="hidden" value="<%= skill.exists() ? skill.getId() : "" %>" />
        <input name="person_id" type="hidden" value="<%= person.getId() %>" />
        <div class="row">
            <div class="input-field col s12 m9">
                <input type="text" id="tag_name" class="autocomplete">
                <label for="tag_name">Selecione uma tag</label>
            </div>
            <div class="input-field col s12 m3">
                <input type="text" name="tag_id" id="tag_id" value="">
                <label for="tag_id">Tag ID</label>
            </div>
            <div class="input-field col s12 m2">
                <label>Selecione o nível</label>
            </div>
            <% for(Level level : levels){ %>
            <div class="input-field col s12 m2">
                <label>
                    <input class="with-gap" name="level_id" type="radio" value="<%= level.getId() %>" checked="checked" />
                    <span><%= level.getName() %></span>
                </label>
            </div>
            <% } %>
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
<script type="text/javascript">
    var tagName = $('#tag_name').val();

    <% String jsonTagsToAttach = new Gson().toJson(tagsToAttach); %>
    var tags = <%= jsonTagsToAttach %>;

    var data = {
        <% for(Tag tag : tagsToAttach){ %>
        "<%= tag.getName() %>": null,
        <% } %>
    };
    console.log(data)

    $('input.autocomplete').autocomplete({
        data: data,
        onAutocomplete: function(selected){
            tagName = selected;
            selectTag(selected);
        },
        minLength: 2
    });

    $('input.autocomplete').focusout(function(){
        if(this.value != tagName){
            $('#tag_id').val('');
            $('#tag_name').val('');
        }
    });

    function selectTag(selected){
        tags.forEach(function(current, index){
            if(current.name == selected){
                console.log(current);
                $('#tag_id').val(current.id);
            }
        });
    }
</script>