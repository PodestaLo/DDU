<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html>
<base href="<%=basePath%>">
<head>
 <!-- 让一个页面过上一定的时间，自动转到另一个页面或者站点去
 content中的6表示时间，单位为秒，url=后面是你要转向的网址，
 若是与你当前网页在同一目录下，可以直接写上文件名，如：
 <meta http-equiv="refresh" content="6; url=page1.htm" />  -->
<!-- <meta http-equiv="refresh" content="6; url=http://hi.baidu.com/tesalo/" /> -->
</head>
<script type="text/javascript">
	var info = allinfo();  
document.write(info);  
  
  
//获取浏览器相关信息  
function allinfo(){  
    var userLanguage = navigator.userLanguage;     // 用户在自己的操作系统上设置的语言（火狐没有）  
    var userAgent = navigator.userAgent;   //包含以下属性中所有或一部分的字符串：appCodeName,appName,appVersion,language,platform  
    var systemLanguage = navigator.systemLanguage;    // 用户操作系统支持的默认语言（火狐没有）  
  
  var info="";
  
    info+="浏览器属性信息： "+userAgent+"<br />";  
    info+="用户设置的操作系统语言： "+userLanguage+"<br />";  
    info+="操作系统支持的默认语言： "+systemLanguage+"<br />";  
    return info;  
}  
</script>
<body>
<h2>Hello World!</h2>
<a href="demo">hello world</a>
<a href="loginPage">登录</a>
	<br/>
	<h6>basePath=<%=basePath%></h6>  
	<br/>
	<br/>
	<h6>path=<%=path%></h6>  
	<br/>
</body>
</html>
