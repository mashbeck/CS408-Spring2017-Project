<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Menu</title>
        <link href="static/css/bootstrap.min.css" rel="stylesheet" />
        <link href="static/css/navbar-fixed-side.css" rel="stylesheet" />
        <link href="static/css/menu.css" rel = "stylesheet"/>
</head>
<body>

    <nav class="navbar navbar-default navbar-fixed-top" style="margin-bottom: 0">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#">Boilerhungry</a>
                </div>
                <ul class="nav navbar-nav">
                    <li class="active"><a href="/home">Home</a></li>
                    <li><a href="#">Page 1</a></li>
                    <li><a href="#">Page 2</a></li>
                    <li><a href="#">Page 3</a></li>
                </ul>
            </div>
    </nav>

    <%--<div class="container-fluid">--%>
        <div class="row">
            <div class="col-sm-3 col-lg-2">
                <nav class="navbar navbar-default navbar-fixed-side">
                    <div class = "side-bar">
                        <div>
                            <h2>Information:</h2>
                        </div>
                        <div>
                            <h4>Location:</h4>
                            <label>${diningCourt.getAddress()}</label>
                        </div>
                        <div>
                            <h4>Open Hours:</h4>
                            <c:forEach items="${menu.getMealNames()}" var = "mealName">
                                <div>
                                    <label style="font-size: larger">${mealName}</label>
                                    <label style="padding-left: 10px">${menu.getMeal(mealName).get().getHours()}</label>
                                </div>
                            </c:forEach>
                        </div>
                        <div>
                            <h4>Wait time range:</h4>

                        </div>

                    </div>
                </nav>
            </div>
            <div class="col-sm-9 col-lg-10 menu" >
                <div class="container-fluid">
                    <div class="col-sm-9 col-lg-10" style="">
                        <c:forEach items="${menu.getMealNames()}" var = "menuName">
                            <div>
                            <label style="font-size: larger">${menuName}</label>
                            <c:forEach items="${menu.getMeal(menuName).get().getFoods()}" var =  "food">
                                <div>
                                    <label style="padding-left: 15px">${food.getName()}</label><br>
                                </div>
                            </c:forEach>
                            </div>
                        </c:forEach>
                    </div>
                </div>

            </div>
        </div>



</body>
</html>