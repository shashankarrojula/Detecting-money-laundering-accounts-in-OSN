<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
<meta name="description" content="" />
<meta name="author" content="" />
<!--[if IE]>
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <![endif]-->
<title>Detecting and analyzing money laundering accounts in OSN</title>

<!-- BOOTSTRAP CORE STYLE  -->
<link href="assets/css/bootstrap.css" rel="stylesheet" />
<!-- FONT AWESOME STYLE  -->
<link href="assets/css/font-awesome.css" rel="stylesheet" />
<!-- ANIMATE STYLE  -->
<link href="assets/css/animate.css" rel="stylesheet" />
<!-- FLEXSLIDER STYLE  -->
<link href="assets/css/flexslider.css" rel="stylesheet" />
<!-- CUSTOM STYLE  -->
<link href="assets/css/style.css" rel="stylesheet" />
<!-- GOOGLE FONTS  -->
<link
	href='http://fonts.googleapis.com/css?family=Open+Sans+Condensed:300'
	rel='stylesheet' type='text/css' />
<link href='http://fonts.googleapis.com/css?family=Open+Sans'
	rel='stylesheet' type='text/css' />
<link href='http://fonts.googleapis.com/css?family=Lobster'
	rel='stylesheet' type='text/css' />

</head>
<body style="background-color:powderblue;">

	<div class="navbar navbar-inverse set-radius-zero">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<BR>
				<center>
					<b style="font-size: xx-large;">Detecting and analyzing money laundering accounts in OSN</b>
				</center>
			</div>


		</div>
	</div>
	<!-- LOGO HEADER END-->
	<section class="menu-section">
		<div class="container">
			<div class="row ">
			
				<div class="col-md-12">
					<div class="navbar-collapse collapse ">
						<ul id="menu-top" class="nav navbar-nav navbar-right">
							<li><a href="adminhome.jsp" class="menu-top-active">HOME</a></li>
							<li><a href="addservices.jsp">Add Services</a></li>
							<li><a href="viewservices.jsp">View Services</a></li>
							<li><a href="addevents.jsp">Add Events</a></li>
							<li><a href="viewevents.jsp">View Events</a></li>
							<li><a href="viewmalicioususers.jsp">Malicious Users</a></li>
							<li><a href="index.html">Logout</a></li>

						</ul>
					</div>
				</div>

			</div>
		</div>
	</section>
	<!-- MENU SECTION END-->
	<div id="slideshow-sec">
		<div id="carousel-div" class="carousel slide" data-ride="carousel">

			<div class="just-sec">
			
				<div class="container">


					<div class="just-txt-div">


						<p align=center>

							<%
								String status = (String) session.getAttribute("status");

								if (status != null) {
							%>
							<script>alert("<%=status%>")</script>
							<%
								}
							%>
						</p>

						<form name="regform" action="AddServiceServlet" method="post">
						
							<div class="form-group">
								<label>Name</label> <input class="form-control"
									name="name" type="text" style="width: 50%;" />
							</div>
							<div class="form-group">
								<label>Description</label> <input class="form-control"
									name="description" type="text" style="width: 50%;" />
							</div>
							<div class="form-group">
								<label>No Of Points</label> 
								<input class="form-control"
									name="price" type="text" style="width: 50%;" />
							</div>
							
							<button type="submit" class="btn btn-success btn-lg"
								onclick="return validate()">Add Service</button>

						</form>

						<!-- <form name="regform" action="regaction">
		<table align="center">

			<tr>
				<td>Enter Username:</td><td><input type="text" name="username"></td>
			</tr>
			
			<tr>
				<td>Enter password:</td><td><input type="password" name="password"></td>
			</tr>

			<tr>
				<td>Gender:</td>
				
				<td>
					<input type="radio" name="sex" value="male">male
					<input type="radio" name="sex" value="female">female
				</td>
			</tr>
			<tr>
				<td>Email:</td><td><input type="text" value="" name="Eid"></td>
			</tr>

			<tr>
				<td>Date Of Birth:</td><td><input type="date" value="" name="DOB" style="width:97%;"></td>
			</tr>

			<tr>
				<td>Mobile:</td><td><input type="number" value="" name="mob"></td>
			</tr>

			<tr>
				<td colspan="2" align="center"><input type="submit" value="REGISTER" onclick="return validate()"><a href="index.jsp">back</a></td>
			</tr>
		</table>
	</form> -->
					</div>
				</div>

			</div>

		</div>
	</div>
	<div class="row">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
			<hr />

		</div>
	</div>


	<!--FOOTER SECTION END-->
	<!-- WE PUT SCRIPTS AT THE END TO LOAD PAGE FASTER-->
	<!--CORE SCRIPTS PLUGIN-->
	<script src="assets/js/jquery-1.11.1.min.js"></script>
	<!--BOOTSTRAP SCRIPTS PLUGIN-->
	<script src="assets/js/bootstrap.js"></script>
	<!--WOW SCRIPTS PLUGIN-->
	<script src="assets/js/wow.js"></script>
	<!--FLEXSLIDER SCRIPTS PLUGIN-->
	<script src="assets/js/jquery.flexslider.js"></script>
	<!--CUSTOM SCRIPTS -->
	<script src="assets/js/custom.js"></script>
</body>

</html>
