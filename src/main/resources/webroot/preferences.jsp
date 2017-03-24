<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>BoilerHungry</title>
        <script src="static/js/jquery-3.1.1.min.js"></script>
        <link href="static/css/bootstrap.min.css" rel="stylesheet">
        <link href="static/css/preferences.css" rel="stylesheet">
        <link href="static/css/default.css" rel="stylesheet">
    </head>
    <body>
        <nav class="navbar navbar-default" style="margin-bottom: 0">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" >Boilerhungry</a>
                </div>
                <ul class="nav navbar-nav">
                    <li class="active"><a href="/home">Home</a></li>
                    <li><a href="/menu/Earhart">Earhart</a></li>
                    <li><a href="/menu/Hillenbrand">Hillenbrand</a></li>
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
        <div>
            ${preferences}
            ${exclusions}
            <h1>D  A  N  K    M  E  M  E  S</h1>
        </div>
    </body>
</html>