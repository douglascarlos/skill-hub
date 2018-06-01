<%@ page import="br.feevale.model.Person" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Person person = (Person) request.getAttribute("person");

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
    var chart = new Chart(ctx, {
        type: 'radar',
        data: {
            //tags
            labels:[
                "Eating", //nome da tag 1
                "Drinking",
                "Sleeping",
                "Designing",
                "Coding",
                "Cycling",
                "Running"
            ],
            datasets:[
                {
                    "label":"My First Dataset", //nome da pessoa
                    "data":[
                        65, //pontuação da pessoa na tag 1
                        59,
                        90,
                        81,
                        56,
                        55,
                        40
                    ],
                    "fill":true,
                    "backgroundColor":"rgba(255, 193, 7, 0.2)",
                    "borderColor":"rgb(255, 193, 7)",
                    "pointBackgroundColor":"rgb(255, 193, 7)",
                    "pointBorderColor":"#000",
                    "pointHoverBackgroundColor":"#000",
                    "pointHoverBorderColor":"rgb(255, 193, 7)"
                },
            ]
        },
        options: {
            "elements":{
                "line":{
                    "tension":0,
                    "borderWidth":3
                }
            }
        }
    });
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