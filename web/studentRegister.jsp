<%--
  Created by IntelliJ IDEA.
  User: 欧阳
  Date: 2019/1/25
  Time: 15:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
    <link rel="stylesheet" href="css/bootstrap-theme.min.css">
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="js/jquery-3.3.1.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="js/bootstrap.min.js" ></script>
    <script src="js/01-js.js" type="text/javascript" charset="utf-8"></script>
    <link rel="stylesheet" type="text/css" href="css/01-css.css"/>
    <title>学生注册页面</title>
</head>
<body>
<div id="showdiv" class="container" >
    <form class="form-horizontal" onsubmit="return check()" method="post" enctype="multipart/form-data" action="/studentRegisterServlet" >
        <!--左div-->
        <div style="margin-top: 20px;" class="col-md-4 col-lg-4 col-xs-12 col-sm-12">
            <div >
                <img src="img/logo-a.png" />
                <ul>
                    <li style="color: #4A6672;font-size: 18px;">学生注册</li>
                    <li style="color: gray;font-size: 12px;">STUDENT REGISTER</li>
                </ul>
            </div>
        </div>

        <!--中div-->
        <div style="margin-top: 70px;" class="col-md-6 col-lg-6 col-xs-12 col-sm-12" >
            <div class="form-group" >
                <label class="col-sm-2 control-label">用户名</label>
                <div class="col-sm-10">
                    <label><input type="text" class="form-control" name="username" id="username"  placeholder="用户名" onblur="reg_username()"></label>
                    <label id="reg_username"></label>
                </div>
            </div>

            <div class="form-group" >
                <label class="col-sm-2 control-label">密码</label>
                <div class="col-sm-10">
                    <label><input type="password" class="form-control" name="password" id="password"  placeholder="密码" onblur="reg_pw()"></label>
                    <label id="reg_pw"></label>
                </div>
            </div>

            <div class="form-group" >
                <label class="col-sm-2 control-label">姓名</label>
                <div class="col-sm-10">
                    <label><input type="text" class="form-control" name="name" id="name"  placeholder="姓名" onblur="reg_name()"></label>
                    <label id="reg_name"></label>
                </div>
            </div>

            <div class="form-group" >
                <label  class="col-sm-2 control-label ">性别</label>
                <div class="col-sm-10">
                    <label class="radio-inline">
                        <input type="radio" name="sex" value="男" checked="checked"> 男
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="sex" value="女"> 女
                    </label>
                </div>
            </div>

            <div class="form-group" >
                <label class="col-sm-2 control-label">上传头像</label>
                <div class="col-sm-10">
                    <label><input type="file" name="img" id="img" onblur="reg_img()"></label>
                    <label id="reg_img"></label>
                </div>
            </div>

            <div class="form-group" >
                <label class="col-sm-2 control-label ">验证码</label>
                <div class="col-sm-10">
                    <label><input type="text" class="form-control" id="ccc" placeholder="点击显示验证码" /></label>
                    <span><img src="img/verify_code.jpg" width="85px" id="code" style="display:none"></span>
                </div>
            </div>

            <div class="form-group" style="margin-left: 50px;" >
                <input type="submit" class="btn btn-success" style="width: 160px;" value="注册">
                <button type="reset" class="btn btn-danger" style="width: 80px;">重置</button>
            </div>
        </div>

        <!--右div-->
        <div  class="col-md-2 col-lg-2 col-xs-12 col-sm-12"  >
            <div >
                管理员？<br />
                <a href="adminLogin.jsp" style="color: deeppink">点击登录</a>
            </div>
        </div>
    </form>
</div>

<script>
    function reg_username(){
        var username=document.getElementById("username").value;
        var label=document.getElementById("reg_username")
        var reg=new RegExp("^.{5,12}$");
        if(reg.test(username)){
            label.innerHTML="✅";
            return true;
        }else{
            label.innerHTML="<img src='img/wrong.jpg' width='18px'>"+"长度5~12位";
            return false;
        }
    }

    function reg_pw() {
        var password=document.getElementById("password").value;
        var label=document.getElementById("reg_pw");
        var reg=new RegExp("^.{6,15}$");
        if(reg.test(password)){
            label.innerHTML="✅";
            return true;
        }else{
            label.innerHTML="<img src='img/wrong.jpg' width='18px'>"+"长度6~15位";
            return false;
        }
    }

    function reg_name(){
        var name=document.getElementById("name").value;
        var label=document.getElementById("reg_name");
        var reg=new RegExp("^[\u4e00-\u9fa5]{2,5}$");
        if(reg.test(name)){
            label.innerHTML="✅";
            return true;
        }else{
            label.innerHTML="<img src='img/wrong.jpg' width='18px'>"+"2~5个汉字";
            return false;
        }
    }

    function reg_img(){
        var img=document.getElementById("img").value;
        var label=document.getElementById("reg_img");
        if(img!=0){
            label.innerHTML="✅";
            return true;
        }else{
            label.innerHTML="<img src='img/wrong.jpg' width='15px'>";
            return false;
        }
    }


    function check(){
        var username=reg_username();
        var password=reg_pw();
        var name=reg_name();
        var img=reg_img();
        if(username&&password&&name&&img){
            return true;
        }else{
            return false;
        }
    }

</script>
</body>
</html>