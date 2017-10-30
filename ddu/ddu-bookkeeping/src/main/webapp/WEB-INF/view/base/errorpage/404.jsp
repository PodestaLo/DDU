<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="zh">
<head>
<base href="<%=basePath%>"/>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<link href="resources/style/style_404.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<div id="main">
		<!-- header -->
		<div id="header">
			<h1>Page Gone To Heaven And Doesn't Want To Come Back<span>404 Error - Not Found </span></h1>
		</div>
		<!-- content -->
		<div id="content">
			<ul class="nav">
         	<li class="home"><a href="#">Home Page</a></li>
            <li class="site_map"><a href="#">Site Map</a></li>
            <li class="search"><a href="#">Website Search</a></li>
         </ul>
         <p>Hey, you're early! You don't belong here - at least not today. Besides, what you're looking for is not here anyways.<br />
         So why don't you go to our <a href="#">homepage</a>, check out our <a href="#">sitemap</a> or try using the <a href="#">website search</a>.</p>
		</div>
		<!-- footer -->
		<div id="footer">
      	Designed by TemplateMonster - all <a href="http://www.cssmoban.com" target="_blank">!!!</a> found and safe!
      </div>
	</div>
</body>
</html>