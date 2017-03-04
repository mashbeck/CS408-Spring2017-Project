<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>BoilerHungry</title>
    <script src="static/js/jquery-3.1.1.min.js"></script>
    <script src="static/js/jquery.vticker.js"></script>
    <link href="static/css/bootstrap.min.css" rel="stylesheet">
    <link href="static/css/home.css" rel="stylesheet">
    <link href="static/css/default.css" rel="stylesheet">
    <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
</head>
<body>

<nav class="navbar navbar-default" role="navigation">
    <div class="container">

        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-collapsible">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">BoilerHungry</a>
        </div>

        <div class="navbar-collapse collapse" id="navbar-collapsible">

            <ul class="nav navbar-header navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-gear fa-fw"></i> Settings <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">My Foods</a></li>
                        <li><a href="#">Dietary Preferences</a></li>
                    </ul>
                </li>
            </ul>

            <form class="navbar-form">
                <div class="form-group" style="display:inline;">
                    <div class="input-group">
                        <form method="get" action ="ViewMenu">
                            <input type="Search" placeholder="Search..." class="form-control" />
                        </form>
                        <div class="input-group-btn">
                            <button class="btn btn-info">
                                <span class="glyphicon glyphicon-search"></span>
                            </button>
                        </div>
                    </div>
                </div>
            </form>

        </div>
    </div>
</nav>

<h4 id="notificationTitle">Notifications:</h4>
<div id="container">
    <div id="ticker">
        <ul style="text-align:center">
            <c:forEach items="${notifications}" var="notification" varStatus="loop">
                <li>${notification}</li>
            </c:forEach>
        </ul>
    </div>
</div>

<script>

       $('#ticker').vTicker({
            speed: 2000,
            pause: 100,
            showItems: 1,
            animation:'fade',
            mousePause:true,
            height: 24,
            direction:'down'
      });

</script>

<div class="container">
    <div id="carousel" class="carousel slide" data-ride="carousel">
        <ol class="carousel-indicators">
            <c:forEach items="${diningCourts}" var="diningCourt" varStatus="loop">
                <li data-target="#carousel" data-slide-to="${loop.index}" class="${loop.index == 0 ? 'active' : ''}"></li>
            </c:forEach>
        </ol>

        <div class="carousel-inner">
            <c:forEach items="${diningCourts}" var="diningCourt" varStatus="loop">
                <div class="${loop.index == 0 ? 'item active' : 'item' }">
                    <div class="hero">
                        <hgroup>
                            <h2>${diningCourt.getName()}</h2>
                        </hgroup>
                        <a class="btn btn-hero btn-lg" href="/menu/${diningCourt.getName()}" role="button">View Menu</a>
                    </div>
                    <img src="http://lorempixel.com/1500/600/food/${loop.index}" alt="Earhart" />
                </div>
            </c:forEach>
        </div>

        <a href="#carousel" class="left carousel-control" id="prev" data-slide="prev">
            <span class="glyphicon glyphicon-chevron-left"></span>
        </a>
        <a href="#carousel" class="right carousel-control" id="next" data-slide="next">
            <span class="glyphicon glyphicon-chevron-right"></span>
        </a>
    </div>
</div>
<script src="static/js/jquery.min.js"></script>
<script src="static/js/bootstrap.min.js"></script>
</body>
</html>