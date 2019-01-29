package com.oyf.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import static com.oyf.servlet.StudentRegisterServlet.file;
import static com.oyf.servlet.StudentRegisterServlet.students;

@WebServlet(name = "UpdateServlet",value = "/updateServlet")
public class UpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //登录后已经将数据读出来到集合中 直接操作集合

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String password = request.getParameter("password");
        String sex= request.getParameter("sex");
        String username= request.getParameter("username");

        //修改集合后 将新集合写入文件 返回jsp页面时也是已经修改好的集合
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getUsername().equals(username)){
                students.get(i).setPassword(password);
                students.get(i).setSex(sex);
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
                oos.writeObject(students);
                oos.close();
                response.getWriter().print("修改成功！");
                response.setHeader("refresh","1;url=adminIndex.jsp");
                break;
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
