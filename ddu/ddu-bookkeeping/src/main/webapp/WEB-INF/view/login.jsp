<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="zh-CN">
  <head>
  <base href="<%=basePath%>">
  
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    
    <%@include file="base/base.jsp" %>
    <title>登录</title>
  </head>
  
  <body>
    <div class="container">
      <form class="form-signin">
        <h2 class="form-signin-heading">请登录</h2>
        <label for="username" class="sr-only">账号</label>
        <input type="text" id="username" class="form-control" placeholder="账号" required autofocus>
        <label for="password" class="sr-only">密码</label>
        <input type="password" id="password" class="form-control" placeholder="密码" required>
        <div class="checkbox">
          <label>
            <input type="checkbox" value="remember-me"> 下次自动登录
          </label>
        </div>
        <button id="login" class="btn btn-lg btn-primary btn-block" >登录</button>
      </form>
    </div> <!-- /container -->
  </body>
  <script type="text/javascript">
$("#login").click(function(){
var name=$("#username").val();
var pw=$("#password").val();
$.ajax({
    url:'login.do',
    type:'POST', //GET
    async:true,    //或false,是否异步
    data:{
        username:name,password:pw
    },
    timeout:5000,    //超时时间
    dataType:'text',    //返回的数据格式：json/xml/html/script/jsonp/text
    beforeSend:function(xhr){
        console.log(xhr)
        console.log('发送前')
    },
    success:function(data,textStatus,jqXHR){
        console.log(data)
        console.log(textStatus)
        console.log(jqXHR)
    },
    error:function(xhr,textStatus){
        console.log('错误')
        console.log(xhr)
        console.log(textStatus)
    },
    complete:function(){
        console.log('结束')
    }
})
});

  </script>
</html>

