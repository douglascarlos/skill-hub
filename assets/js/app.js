$(document).ready(function(){
    $('.sidenav').sidenav();
    $('.dropdown-trigger').dropdown();
    $('select').formSelect();
    $('.datepicker').datepicker({
        format: "dd/mm/yyyy"
    });
    $('.modal').modal();
    $('.fixed-action-btn').floatingActionButton();
    $('.tooltipped').tooltip();
    $('.character-counter').characterCounter();
    $('.tabs').tabs();
    $('.collapsible').collapsible();
});
