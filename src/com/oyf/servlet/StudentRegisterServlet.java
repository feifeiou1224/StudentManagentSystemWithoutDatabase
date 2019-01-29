package com.oyf.servlet;

import com.oyf.bean.Student;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "StudentRegisterServlet",value = "/studentRegisterServlet")
public class StudentRegisterServlet extends HttpServlet {

    public static ArrayList<Student> students = new ArrayList<>();
    //设置数据存储路径
    public static File file = new File("F:\\data.txt");

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String imgName;
        String username="";
        String password="";
        String name="";
        String sex="";
        //1、设置编码格式
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //2、创建文件上传的核心对象
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //3、文件上传对象
        ServletFileUpload upload = new ServletFileUpload(factory);
        //4、设置上传相关参数
        upload.setHeaderEncoding("utf-8");//防止上传文件名中文乱码
        upload.setFileSizeMax(1024*1024*5);//最大文件大小 单位:b
        try {
            List<FileItem> list = upload.parseRequest(request);//解析request，将请求中的数据封装成Item
            int count = 0;
            for (FileItem fileItem : list) {
                if (fileItem.isFormField()) {//如果是普通字段
                    //获取value值
                    String value = fileItem.getString("utf-8");
                    if (count == 0) {
                        username = value;
                        count++;
                    } else if (count == 1) {
                        password = value;
                        count++;
                    } else if (count == 2) {
                        name = value;
                        count++;
                    } else if (count == 3) {
                        sex = value;
                        count = 0;
                    }
                }
            }
            //文件中没有数据时 直接注册
            if(file.length()==0){

                Student student = new Student(username, password, name, sex);
                System.out.println(student.toString());

                imgName = student.getId() + "_" + username;
                for (FileItem fileItem : list) {
                    if (!fileItem.isFormField()) {//如果是文件
                        //获取文件全名
                        String filename = fileItem.getName();
                        //获取文件后缀名
                        int i = filename.lastIndexOf(".");
                        filename = filename.substring(i);
                        //确定文件存储路径
                        String filePath = "F:\\StudentsImg";
                        File file = new File(filePath);
                        if (!file.exists()) {
                            file.mkdirs();
                        }
                        //拼接文件路径 文件名 后缀名
                        filePath = filePath + File.separator + imgName + filename;
                        student.setImg(imgName+filename);
                        System.out.println(filePath);
                        students.add(student);
                        //将图片上传到一个文件夹
                        InputStream in = fileItem.getInputStream();
                        FileOutputStream fos = new FileOutputStream(filePath);
                        IOUtils.copy(in, fos);
                        fos.close();
                        in.close();
                    }
                }
                //将用户数据写入另一个文件
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
                oos.writeObject(students);
                oos.close();
                response.getWriter().print("注册成功...你是第一个用户");
                response.setHeader("refresh","2;url=adminLogin.jsp");


            }else{//文件中有数据时 先读出到集合 再进行判断
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                students = (ArrayList<Student>)ois.readObject();
                ois.close();
                //判断是否重复用户名 注册失败 不写数据
                int flag = 0;
                for (int i = 0; i < students.size(); i++) {
                    if (students.get(i).getUsername().equals(username)) {
                        flag = 1;
                        response.getWriter().print("账号已存在！...2s后返回");
                        response.setHeader("refresh", "2;url=studentRegister.jsp");
                    }
                }
                //如果用户名不重复 注册成功 写入数据到文件
                if (flag == 0) {
                    Student student = new Student(username, password, name, sex);
                    System.out.println(student.toString());
                    imgName = student.getId() + "_" + username;
                    for (FileItem fileItem : list) {
                        if (!fileItem.isFormField()) {//如果是文件
                            //获取文件全名
                            String filename = fileItem.getName();
                            //获取文件后缀名
                            int i = filename.lastIndexOf(".");
                            filename = filename.substring(i);
                            //确定文件存储路径
                            String filePath = "F:\\StudentsImg";
                            File file = new File(filePath);
                            if (!file.exists()) {
                                file.mkdirs();
                            }
                            //拼接文件路径 文件名 后缀名
                            filePath = filePath + File.separator + imgName + filename;
                            student.setImg(imgName+filename);
                            System.out.println(filePath);
                            students.add(student);
                            //将文件上传到服务器指定路径
                            InputStream in = fileItem.getInputStream();
                            FileOutputStream fos = new FileOutputStream(filePath);
                            IOUtils.copy(in, fos);
                            fos.close();
                            in.close();
                        }
                    }
                    //将用户数据写入文件
                    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
                    oos.writeObject(students);
                    oos.close();
                    response.getWriter().print("注册成功..即将前往登录页面");
                    response.setHeader("refresh","2;url=adminLogin.jsp");
                }
            }
            } catch(FileUploadException e){
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
