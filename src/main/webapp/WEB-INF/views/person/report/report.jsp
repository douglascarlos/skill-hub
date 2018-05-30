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
<div class="row">
    <canvas id="canvas_report"></canvas>
</div>
<script type="text/javascript">
    var ctx = $('#canvas_report');
    var chart = new Chart(ctx, {
        type: 'radar',
        data: {
            labels:[
                "Eating",
                "Drinking",
                "Sleeping",
                "Designing",
                "Coding",
                "Cycling",
                "Running"
            ],
            datasets:[
                {
                    "label":"My First Dataset",
                    "data":[
                        65,
                        59,
                        90,
                        81,
                        56,
                        55,
                        40
                    ],
                    "fill":true,
                    "backgroundColor":"rgba(255, 99, 132, 0.2)",
                    "borderColor":"rgb(255, 99, 132)",
                    "pointBackgroundColor":"rgb(255, 99, 132)",
                    "pointBorderColor":"#fff",
                    "pointHoverBackgroundColor":"#fff",
                    "pointHoverBorderColor":"rgb(255, 99, 132)"
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