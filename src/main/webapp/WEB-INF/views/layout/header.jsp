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

    <link rel="icon" href="images/icon.png" />
    <title>Skill Hub</title>
</head>

<body>
<header>
    <nav>
        <div class="nav-wrapper">
            <a href="/" class="brand-logo"><img height="64" src="/images/logo.png" alt="Skill Hub"></a>
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