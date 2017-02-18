<html>
<head>
    <title>Menu</title>
        <link href="static/css/bootstrap.min.css" rel="stylesheet" />
        <link href="static/css/navbar-fixed-side.css" rel="stylesheet" />
        <link href="static/css/menu.css" rel = "stylesheet"/>
</head>
<body>

    <nav class="navbar navbar-default">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#">Boilerhungry</a>
                </div>
                <ul class="nav navbar-nav">
                    <li class="active"><a href="#">Home</a></li>
                    <li><a href="#">Page 1</a></li>
                    <li><a href="#">Page 2</a></li>
                    <li><a href="#">Page 3</a></li>
                </ul>
            </div>
            <div class="container-fluid">
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
                                </div>
                                <div>
                                    <h4>Wait time range:</h4>
                                    
                                </div>
                            </div>
                        </nav>
                    </div>
                    <div class="col-sm-9 col-lg-10">
                        <!-- your page content -->
    
                    </div>
                </div>
            </div>
        </nav>

