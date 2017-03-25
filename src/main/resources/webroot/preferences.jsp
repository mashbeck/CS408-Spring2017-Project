<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>BoilerHungry</title>
        <script src="static/js/jquery-3.1.1.min.js"></script>
        <link href="static/css/bootstrap.min.css" rel="stylesheet">
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
                    <li></b><a href="/home" style="font-weight: bold">MyFoods</a></li>
                    <li><a href="/home" style="font-weight: bold">Preferences</a></li>
                </ul>
            </div>
        </nav>
        <h4>Preferences</h4>
        <table id="preferenceTable" class="table">
            <tbody>
                <c:forEach items="${preferences}" var="preference">
                    <tr>
                        <div class="checkbox">
                            <label>
                                <input class="checked" type="checkbox" onclick='update("${preference}", "preference")' value="${preference}" checked>${preference}</input>
                            </label>
                        </div>
                    </tr>
                </c:forEach>
                <tr>
                     <div id="container">
                        <input id="addPreferenceInput" type="search" name="chosen" placeholder="Add an exclusion">
                        <input id="addPreferenceButton" type="submit" value="Button">
                     </div>
                </tr>
            </tbody>
        </table>
        <h4>Exclusions</h4>
        <table id="exclusionTable" class="table">
            <tbody>
                <c:forEach items="${exclusions}" var="preference">
                    <tr>
                        <div class="checkbox">
                            <label>
                                <input class="checked" type="checkbox" onclick='update("${preference}", "exclusion")' value="${preference}" checked>${preference}</input>
                            </label>
                        </div>
                    </tr>
                </c:forEach>
                <tr>
                     <div id="container">
                        <input id="addExclusionInput" type="search" name="chosen" placeholder="Add an exclusion">
                        <input id="addExclusionButton" type="submit" value="Button">
                     </div>
                </tr>
            </tbody>
        </table>
        <script>
            $("#addPreferenceButton").click(function() {
                var inp = $("#addPreferenceInput");
                var pref = inp.val();
                update(pref, "preference");
            });
            $("#addExclusionButton").click(function() {
                var inp = $("#addExclusionInput");
                var pref = inp.val();
                update(pref, "exclusion");
            });
            function update(food, foodType) {
                $.ajax({
                    type: "POST",
                    url: '/preferences',
                    dataType: 'text',
                    data: { food, foodType },
                    error: function (thrownError) {
                        alert("error with: "+thrownError);
                    },
                    success: function(json){
                        if(!json.error) location.reload(true);
                    }
                });
            }
        </script>
    </body>
</html>