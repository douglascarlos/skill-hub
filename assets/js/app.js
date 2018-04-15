var ToastSH = function (message) {
    // var toastHTML = message + '<button class="btn-flat toast-action" onclick="toastInstance.dismiss()"><i class="material-icons right">close</i></button>';
    M.toast({html: message, classes: 'toast-sh'});
    // var toastElement = document.querySelector('.toast-sh');
    // var toastInstance = M.Toast.getInstance(toastElement);
};

$(document).ready(function(){
    $('.sidenav').sidenav();
    $('.dropdown-trigger').dropdown();
    $('select').formSelect();
    $('.datepicker').datepicker();
    $('.modal').modal();
});
