<%@ page import="static com.oyf.servlet.StudentRegisterServlet.students" %>
<%@ page import="com.oyf.bean.Student" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: 欧阳
  Date: 2019/1/25
  Time: 16:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>管理员界面</title>
    <style>
        table{
            margin: auto;
            margin-top: 150px;
            text-align: center;
        }
        table tr:nth-child(even){
            background: #ccc;
        }
        td{
            width: 300px;
        }

    </style>
</head>
<body>
    <div style="font-weight: bold;font-size: large;color:black;">

        <!--jstl欢迎语 第一种-->
<%--        <c:if test="${sessionScope.name==null}" var="result">
            <a href="/adminLogin.jsp" style="color: deeppink">立即登录</a>
        </c:if>
        <c:if test="${sessionScope.name!=null}" var="result">
            欢迎，${sessionScope.name}。
        </c:if>--%>

        <!--jstl欢迎语 第二种-->
        <c:choose>
            <c:when test="${sessionScope.name==null}">
                <a href="/adminLogin.jsp" style="color: deeppink;">立即登录</a>
            </c:when>
            <c:otherwise>
                欢迎，${sessionScope.name}。
            </c:otherwise>
        </c:choose>
    </div>

    <div style="width: 400px;margin: auto;text-align: center">
        <input type="text" class="form-control" placeholder="输入学生姓名" id="search" width="250px">
        <input type="button" class="btn btn-info" value="查询" onclick="search()">
    </div>

    <table class="table">
        <tr>
            <td rowspan="2" style="font-size: 40px">查询结果</td>
            <td>头像</td>
            <td>用户名</td>
            <td>密码</td>
            <td>真实姓名</td>
            <td>性别</td>
            <td>上次登录时间</td>
        </tr>


        <%
            ArrayList<Student> list = students;
            pageContext.setAttribute("list",list);
            request.getParameter("username");
            pageContext.setAttribute("username",request.getParameter("username"));
        %>
        <tr>
            <!--通过传回的username 在当前页面遍历集合查找-->

            <!--jstl版本-->
            <c:forEach var="look" items="${list}">
                <c:if test="${look.username.equals(username)}" var="result">
                    <td><img src="/aaa/${look.img}" width="35px" height="35px"></td>
                    <td>${look.username}</td>
                    <td>${look.password}</td>
                    <td>${look.name}</td>
                    <td>${look.sex}</td>
                    <td>${look.date}</td>
                </c:if>
            </c:forEach>

<%--            <%
                String username = request.getParameter("username");
                if(username==null)username="";
                String img = "";
                String password = "";
                String name = "";
                String sex = "";
                String date = "";

                for (int i = 0; i < students.size(); i++) {
                    if (students.get(i).getUsername().equals(username)){
                        img = students.get(i).getImg();
                        password = students.get(i).getPassword();
                        name = students.get(i).getName();
                        sex = students.get(i).getSex();
                        date = students.get(i).getDate();
                    }
                }
            %>
            <td><img src="/aaa/<%=img%>" width="35px" height="35px"></td>
            <td><%=username%></td>
            <td><%=password%></td>
            <td><%=name%></td>
            <td><%=sex%></td>
            <td><%=date%></td>--%>
        </tr>
    </table>

    <table class="table table-striped">
        <tr>
            <td>null</td>
            <td><input type="text" id="username" name="username" class="form-control"></td>
            <td><input type="text" id="password" name="password" class="form-control"></td>
            <td><input type="text" id="name" name="name" class="form-control"></td>
            <td>
                <label class="radio-inline">
                    <input type="radio" name="sex" value="男" checked="checked">男
                </label>
                <label class="radio-inline">
                    <input type="radio"  name="sex" value="女">女
                </label>
            </td>
            <td>null</td>
            <td><input type="button" value="添加新学生" class="btn btn-success" onclick="add()"></td>
        </tr>

        <tr>
            <td>头像</td>
            <td>用户名</td>
            <td>密码</td>
            <td>真实姓名</td>
            <td>性别</td>
            <td>上次登录时间</td>
            <td>操作</td>
        </tr>

        <!--jstl版本动态表格-->
        <c:forEach var="s" items="${list}">
            <tr>
                <td><img src="/aaa/${s.img}" width="35px" height="35px"></td>
                <td>${s.username}</td>
                <td id="tpassword">${s.password}</td>
                <td>${s.name}</td>
                <td id="tsex">${s.sex}</td>
                <td>${s.date}</td>
                <td>
                    <input type="button" value="修改" class="btn btn-warning"  onclick="update(${s.username},this)">
                    <input type="button" value="删除" class="btn btn-danger"  onclick="del(${s.username})">
                </td>
            </tr>
        </c:forEach>

<%--        <% for (int i = 0; i < students.size(); i++) {%>
            <tr>
                <td><img src="/aaa/<%=students.get(i).getImg()%>" width="35px" height="35px"></td>
                <td><%=students.get(i).getUsername()%></td>
                <td id="tpassword"><%=students.get(i).getPassword()%></td>
                <td><%=students.get(i).getName()%></td>
                <td id="tsex"><%=students.get(i).getSex()%></td>
                <td><%=students.get(i).getDate()%></td>
                <td>
                    <input type="button" value="修改" class="btn btn-warning"  onclick="update(<%=students.get(i).getUsername()%>,this)">
                    <input type="button" value="删除" class="btn btn-danger"  onclick="del(<%=students.get(i).getUsername()%>)">
                </td>
            </tr>
        <%} %>--%>

    </table>

    <script>
        function add(){
            var username=document.getElementById("username").value;
            var password=document.getElementById("password").value;
            var name=document.getElementById("name").value;
            var sex=document.getElementsByName("sex");
            var sexValue;

            for (var i=0;i<sex.length;i++){
                if (sex[i].checked){
                    sexValue=sex[i];
                }
            }
            location.href="/addServlet?username="+username+"&password="+password+"&name="+name+"&sex="+sexValue.value ;
        }

        function del(username){
            location.href="/delServlet?username="+username;
        }

        var flag=false;
        function update(username,that){
            tr=that.parentNode.parentNode;
            var tpassword=tr.cells[2];
            var tsex=tr.cells[4];
            if(!flag){
                var btn=that;
                btn.value="完成修改";
                tpassword.innerHTML="<input type='text' value='"+tpassword.innerHTML+"' id='t1' />";
                tsex.innerHTML="<input type='radio' name='sex2' value='男' checked='checked'/>男"+"<input type='radio' name='sex2' value='女' />女";
                flag=true;
            }else {
                var btn=that;
                btn.value="修改";
                var t1=document.getElementById("t1").value;
                var t2=document.getElementsByName("sex2");
                var selectValue;
                for (var i=0;i<t2.length;i++){
                    if (t2[i].checked){
                        selectValue=t2[i];
                    }
                }
                tpassword.innerHTML=t1;
                tsex.innerHTML=selectValue.value;
                flag=false;
                location.href="/updateServlet?password="+t1+"&sex="+selectValue.value+"&username="+username;
            }
        }

        function search(){
            var keyWord=document.getElementById("search").value;
            location.href="/searchServlet?name="+keyWord;
        }

    </script>
</body>
</html>
