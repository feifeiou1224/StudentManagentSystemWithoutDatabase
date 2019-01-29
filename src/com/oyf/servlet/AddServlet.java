package com.oyf.servlet;

import com.oyf.bean.Student;

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

@WebServlet(name = "AddServlet",value = "/addServlet")
public class AddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //登陆后 已经将文件中的数据读出到students集合 可以直接操作

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String sex = request.getParameter("sex");
        Student student = new Student(username,password,name,sex);

        if(username.equals("")||password.equals("")||name.equals("")){
            response.getWriter().println("添加失败！不能为空");
            response.setHeader("refresh","1;url=adminIndex.jsp");
        }else {
            //添加失败 不进行任何操作
            int flag=0;
            for (int i = 0; i < students.size(); i++) {
                if(students.get(i).getUsername().equals(username)){
                    response.getWriter().print("添加失败！用户名重复！");
                    response.setHeader("refresh","1;url=adminIndex.jsp");
                    flag=1;
                }
            }
            //添加成功 将新集合数据写入文件 返回jsp页面时 集合已有新数据
            if(flag==0) {
                students.add(student);
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
                oos.writeObject(students);
                oos.close();
                response.getWriter().print("添加成功！");
                response.setHeader("refresh", "1;url=adminIndex.jsp");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
