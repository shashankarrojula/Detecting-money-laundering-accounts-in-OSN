<!DOCTYPE html>
<%@page import="java.util.Date"%>
<%@page import="com.voidmain.progaurd.dao.GetConnection"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.TreeSet"%>
<%@page import="java.util.Set"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>

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
<body style="background-color: powderblue;">

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
							<li><a href="home.jsp" class="menu-top-active">HOME</a></li>
							<li><a href="findfriends.jsp">Find Friends</a></li>
							<li><a href="viewfriendrequest.jsp">View Request</a></li>
							<li><a href="viewmyfriends.jsp">My Friends</a></li>
							<li><a href="consumeservice.jsp">Consume Service</a></li>
							<li><a href="shareevents.jsp">View Events</a></li>
							<li><a href="editprofile.jsp"><%=request.getSession().getAttribute("username")%></a></li>
							<li>
								<%
									String username = (String) session.getAttribute("username");
								
									float amount=0.0f;
									
									ResultSet rs11 = GetConnection
											.getConnection()
											.createStatement()
											.executeQuery(
													"select amount from user where username='"
															+ username + "'");

									while (rs11.next()) {
										amount= rs11.getFloat("amount");
									}
								%>
								
								<b>Virtual Points</b> :<font color="red"><%=(int)amount%></font> 
							</li>
							<li><a href="index.html">logout</a></li>

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

					<div class="row">

						<div class="just-txt-div">

							<p align=center>

								<%
									String status = request.getParameter("status");
							
									if (status != null) {
								%>
								<font color="green" size="3"><%=status%></font>
								<%
									}
								%>
							</p>

							<br /> <font size="20px">All Services</font>
							<table align="center" style="text-align: center;" border="2"
								cellspacing="20" width="100%">
								<tr>
									<th style="text-align: center;">ID</th>
									<th style="text-align: center;">Name</th>
									<th style="text-align: center;">Description</th>
									<th style="text-align: center;">Points</th>
									<th style="text-align: center;">Add</th>
								</tr>

								<%
										ResultSet rs3 = GetConnection
												.getConnection()
												.createStatement()
												.executeQuery(
														"SELECT * FROM service");
										while(rs3.next())
										{
								%>
								<tr>
									<td><%=rs3.getString(1)%></td>
									<td><%=rs3.getString(2)%></td>
									<td><%=rs3.getString(3)%></td>
									<td><%=rs3.getFloat(4)%></td>
									<%
											ResultSet rs = GetConnection.getConnection().createStatement().executeQuery(
											"SELECT count(*) FROM user_service where uid='"+username+"' and sid='"+rs3.getString(1)+"'");
										
											int result=0;
											
											while(rs.next())
											{
												result=rs.getInt(1);
											}
											
											System.out.println("result is \t"+result);
										
											if(result==0)
											{
									%>
												<td><a href="consumeservice.jsp?id=<%=rs3.getString(1)%>&amount=<%=rs3.getFloat(4)%>">Add</a>
									<%
											}
											else
											{
									%> 			<td>Service Consumed</tr>
									<%
											}
										}
								%>
							</table>

					
								<%
							 	String id= request.getParameter("id");
								String amount1=request.getParameter("amount");
								
								if (id != null && amount1!=null) {
									
										Date today=new Date();
										today.setDate(today.getDate()+30);
									
										PreparedStatement ps =GetConnection.getConnection()
												.prepareStatement("insert into user_service values(null,?,?,?,?)");
										
										ps.setInt(1,Integer.parseInt(id));
										ps.setString(2,username);
										ps.setDate(3,new java.sql.Date(new java.util.Date().getTime()));
										ps.setDate(4,new java.sql.Date(today.getTime()));

										int result = ps.executeUpdate();

										if (result == 1) {
											
											PreparedStatement ps1 =GetConnection.getConnection()
													.prepareStatement("update user set amount=amount-? where username=?");
											
											ps1.setFloat(1,Float.parseFloat(amount1));
											ps1.setString(2,username);
											
											ps1.executeUpdate();
											
											response.sendRedirect("consumeservice.jsp?status=success");
											
										} else {
											response.sendRedirect("consumeservice.jsp?status=failed");
										}
									}
								
							%>
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
