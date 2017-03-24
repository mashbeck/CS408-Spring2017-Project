<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Menu</title>
        <script src="/static/js/jquery-3.1.1.min.js"></script>
        <link href="/static/css/bootstrap.min.css" rel="stylesheet" />
        <link href="/static/css/navbar-fixed-side.css" rel="stylesheet" />
        <link href="/static/css/menu.css" rel = "stylesheet"/>
</head>
<body>

    <nav class="navbar navbar-default navbar-fixed-top" style="margin-bottom: 0">
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
        <script>
            $(document).ready(function(){
                var x = document.getElementsByName('foodItem');
                var myFoodsList = [];
                <c:forEach items="${myFoods}" var = "item">
                    myFoodsList.push("${item}");
                </c:forEach>
                for(var i = 0; i <x.length;i++){
                    if($.inArray(x[i].value,myFoodsList)>=0){
                        x[i].checked = false;
                    }
                }
            });
            function addFood(food) {
                $.ajax({
                    type: "POST",
                    url: '/menu',
                    dataType:'text',
                    data: {food: food},
                    error: function (thrownError) {
                        alert("error with: "+thrownError);
                    }
                });
            }
        </script>
        <div class="col-sm-9 col-lg-10 menu" >
            <div class="container-fluid">
                <div class="col-sm-9 col-lg-10">
                    <c:forEach items="${menu.getMealNames()}" var = "menuName">
                        <div>
                        <label style="font-size: larger">${menuName}</label>
                        <c:forEach items="${menu.getMeal(menuName).get().getFoods()}" var = "food">
                            <div>
                                <label style="padding-left: 15px">${food.getName()}</label>
                                <input class="star" type="checkbox" onclick='addFood("${food.getName()}")' name = "foodItem" value ="${food.getName()}" title="add to myFoods" checked>
                                </form>
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