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

<nav class="navbar navbar-default" style="margin-bottom: 0" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" >BoilerHungry</a>
        </div>
        <ul class="nav navbar-nav">
            <li class="active"><a href="/home">Home</a></li>
            <li><a href="/menu/Earhart">Hillenbrand</a></li>
            <li><a href="/menu/Hillenbrand">Earhart</a></li>
            <li><a href="/menu/Wiley">Wiley</a></li>
            <li><a href="/menu/Windsor">Windsor</a></li>
            <li><a href="/menu/Ford">Ford</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li></b><a href="/myfoods" style="font-weight: bold">MyFoods</a></li>
            <li><a href="/preferences" style="font-weight: bold">Preferences</a></li>
        </ul>
    </div>
</nav>

<h4 id="notificationTitle">Notifications:</h4>
<div id="container">
    <div id="ticker">
        <ul style="text-align:center;font-size: 10px" type="disc">
            <c:forEach items="${notifications}" var="notification" varStatus="loop">
                <li>${notification}</li>
            </c:forEach>
        </ul>
    </div>
</div>

<script>

       window.onload=$('#ticker').vTicker({
            speed: 3000,
            pause: 400,
            showItems: 1,
            animation:'fade',
            mousePause:true,
            height: 30,
            direction:'down'
      });
      $('#ticker').vTicker('stop');

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
                            <h2>${ diningCourt.getName().replace("e", "f") }</h2>
                        </hgroup>
                        <a class="btn btn-hero btn-lg" href="/menu/${diningCourt.getName()}" role="button">View Menu</a>
                    </div>
                    <img src="http://lorempixel.com/1500/600/food/${loop.index}" alt="Earhart" />
                </div>
            </c:forEach>
        </div>

        <a href="#carousel" class="lef carousel-control" id="prev" data-slide="prev">
            <span class="glyphicon glyphicon-chevron-left"></span>
        </a>
        <a href="#carousel" class="righ carousel-control" id="next" data-slide="next">
            <span class="glyphicon glyphicon-chevron-right"></span>
        </a>
    </div>
</div>
<script src="static/js/jquery.min.js"></script>
<script src="static/js/bootstrap.min.js"></script>
</body>
</html>