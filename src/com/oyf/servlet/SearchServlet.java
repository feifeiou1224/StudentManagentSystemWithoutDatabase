package com.oyf.servlet;

import com.oyf.bean.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.oyf.servlet.StudentRegisterServlet.students;

@WebServlet(name = "SearchServlet",value = "/searchServlet")
public class SearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //登录后集合中有数据 可以直接查询

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");



        String names = request.getParameter("name");
        int flag = 0;
        for (Student s: students) {
            if(s.getName().equals(names)){
                flag = 1;
                String img = s.getImg();
                String username = s.getUsername();
                String password = s.getPassword();
                String name = s.getName();
                String sex = s.getSex();
                String date = s.getDate();

                System.out.println(img+" "+name+"");

                request.setAttribute("username",username);

                //request.getRequestDispatcher("/adminIndex.jsp").forward(request,response );
                response.getWriter().println("正在查询....");
                response.setHeader("Content-type", "text/html;charset=UTF-8");
                response.setHeader("refresh","2;url=/adminIndex.jsp?username="+username);
                //response.sendRedirect("/adminIndex.jsp");
            }
        }
        if(flag == 0){
            response.getWriter().println("查无此人...正在返回");
            response.setHeader("refresh","1;url=adminIndex.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
