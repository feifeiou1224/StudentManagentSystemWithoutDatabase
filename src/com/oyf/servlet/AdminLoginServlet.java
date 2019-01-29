package com.oyf.servlet;

import com.oyf.bean.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.oyf.servlet.StudentRegisterServlet.file;
import static com.oyf.servlet.StudentRegisterServlet.students;

@WebServlet(name = "AdminLoginServlet",value = "/adminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //文件中没有数据时 登录失败
        if(file.length()==0){
            response.getWriter().print("登录失败..");
            response.setHeader("refresh","1;url=adminLogin.jsp");
        }else{//文件不为空时 读文件判断
            ObjectInputStream oos = new ObjectInputStream(new FileInputStream(file));
            try {
                students = (ArrayList<Student>)oos.readObject();
                oos.close();
                //如果匹配正确 登录成功
                int flag=0;
                for (int i = 0; i < students.size(); i++) {
                    if(students.get(i).getUsername().equals(username)&&students.get(i).getPassword().equals(password)){
                        Date date = new Date();
                        DateFormat df = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                        String loginDate = df.format(date);
                        students.get(i).setDate(loginDate);
                        HttpSession session = request.getSession();
                        session.setAttribute("name",students.get(i).getName());
                        response.getWriter().print("登录成功...即将跳转到管理页面");
                        response.setHeader("refresh","2;url=adminIndex.jsp");
                        flag++;
                    }
                }
                //匹配失败 登录失败
                if(flag==0){
                    response.getWriter().print("登录失败...即将返回");
                    response.setHeader("refresh","3;url=adminLogin.jsp");
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
