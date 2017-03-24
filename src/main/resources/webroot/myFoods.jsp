<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>BoilerHungry</title>
        <script src="static/js/jquery-3.1.1.min.js"></script>
        <link href="static/css/bootstrap.min.css" rel="stylesheet">
        <link href="static/css/myFoods.css" rel="stylesheet">
        <link href="static/css/default.css" rel="stylesheet">
    </head>
    <body>

        <nav class="navbar navbar-default" style="margin-bottom: 0" role="navigation">
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

                    type: "POST",//or POST
                    url: '/myfoods',
                    dataType:'text',
                    data: {food: food},
                    error: function (thrownError) {
                        alert("error with: "+thrownError);
                    }
                });
            }
        </script>
        <div style="margin-left:25px">
           <h4>MyFoods:</h4>
                <c:forEach items="${myFoods}" var = "item">
                    <div style="padding-left:10px">
                       <label>${item}</label>
                        <input class="star" type="checkbox" onclick='addFood("${item}")' name = "foodItem" value ="${item}" title="add to myFoods" checked>
                    </div>
                </c:forEach>

        </div>
    </body>
</html>