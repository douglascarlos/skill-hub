<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <!--Import Google Icon Font-->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!--Import materialize.css-->
    <link type="text/css" rel="stylesheet" href="css/materialize.min.css" media="screen,projection"/>
    <link type="text/css" rel="stylesheet" href="css/app.css" media="screen,projection"/>

    <!--Let browser know website is optimized for mobile-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0" charset="UTF-8"/>
</head>

<body>
<header>
    <nav>
        <div class="nav-wrapper">
            <a href="#!" class="brand-logo">Skill Hub</a>
            <a href="#" data-target="mobile-nav" class="sidenav-trigger"><i class="material-icons">menu</i></a>
            <ul class="right hide-on-med-and-down">
                <li><a href="/person">Pessoas</a></li>
                <li><a href="#">Projetos</a></li>
                <li><a href="#">Tags</a></li>
            </ul>
        </div>
    </nav>
    <ul class="sidenav" id="mobile-nav">
        <li><a href="/person">Pessoas</a></li>
        <li><a href="#">Projetos</a></li>
        <li><a href="#">Tags</a></li>
    </ul>
</header>
<main>
    <div class="container">
        <div class="section">
            <h5>Pessoas</h5>
        </div>
        <!-- <div class="divider"></div> -->
        <!--  -->

        <div class="row">
            <div class="card-panel">
                <!--  -->

                <form>
                    <div class="row">
                        <div class="input-field col s6">
                            <input id="first_name" type="text" class="validate">
                            <label for="first_name">Nome</label>
                        </div>

                        <div class="input-field col s6">
                            <select>
                                <option value="" disabled selected>Selecione</option>
                                <option value="1">Option 1</option>
                                <option value="2">Option 2</option>
                                <option value="3">Option 3</option>
                            </select>
                            <label>Materialize Select</label>
                        </div>

                    </div>
                    <div class="row">
                        <button class="btn waves-effect waves-light right" type="submit">
                            Buscar<i class="material-icons right">send</i>
                        </button>
                        <a href="./" class="btn waves-effect waves-light right btn-mr">
                            Limpar<i class="material-icons right">clear_all</i>
                        </a>
                        <a href="./cadastro.html" class="btn waves-effect waves-light right btn-mr">
                            Criar Novo<i class="material-icons right">add</i>
                        </a>
                    </div>
                </form>

                <!--  -->
            </div>
        </div>

        <!--  -->
        <div class="row">
            <table class="responsive-table highlight">
                <thead>
                <tr>
                    <th>Matricula</th>
                    <th>Nome</th>
                    <th>E-mail</th>
                    <th>Ações</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach var="person" items="${people}">
                <tr>
                    <td>${person.enrollmentNumber}</td>
                    <td>${person.name}</td>
                    <td>${person.email}</td>
                    <td>
                        <i class="material-icons dropdown-trigger pointer" data-target='dropdown1'>more_horiz</i>
                        <!-- <a class='dropdown-trigger btn' href='#' data-target='dropdown1'>Drop Me!</a> -->
                        <ul id='dropdown1' class='dropdown-content'>
                            <li><a href="#!">Editar</a></li>
                            <li><a href="#modal_delete" class="modal-trigger">Excluir</a></li>
                        </ul>
                    </td>
                </tr>
                </c:forEach>
                </tbody>
            </table>
            <!--  -->
        </div>
        <!--  -->
        <div id="modal_delete" class="modal">
            <div class="modal-content">
                <h4>Modal Header</h4>
                <p>A bunch of text</p>
            </div>
            <div class="modal-footer">
                <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat">Agree</a>
            </div>
        </div>
        <!--  -->
    </div>
</main>
<footer class="page-footer">
    <div class="container">
        <div class="row">
            <div class="col l6 s12">
                <h5 class="white-text">Footer Content</h5>
                <p class="grey-text text-lighten-4">You can use rows and columns here to organize your footer
                    content.</p>
            </div>
            <div class="col l4 offset-l2 s12">
                <h5 class="white-text">Links</h5>
                <ul>
                    <li><a class="grey-text text-lighten-3" href="#!">Link 1</a></li>
                    <li><a class="grey-text text-lighten-3" href="#!">Link 2</a></li>
                    <li><a class="grey-text text-lighten-3" href="#!">Link 3</a></li>
                    <li><a class="grey-text text-lighten-3" href="#!">Link 4</a></li>
                </ul>
            </div>
        </div>
    </div>
    <div class="footer-copyright">
        <div class="container">
            © 2014 Copyright Text
            <a class="grey-text text-lighten-4 right" href="#!">More Links</a>
        </div>
    </div>
</footer>

<!--JavaScript at end of body for optimized loading-->
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/materialize.min.js"></script>
<script type="text/javascript" src="js/app.js"></script>
</body>
</html>