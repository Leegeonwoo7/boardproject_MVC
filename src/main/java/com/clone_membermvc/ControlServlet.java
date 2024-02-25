package com.clone_membermvc;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;


import bean.MemberDTO;
import com.control.CommandProcess;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(
        urlPatterns = {"*.do"}
        ,initParams = {
                @WebInitParam(name="propertyConfig",value="command.properties")
        })
public class ControlServlet extends HttpServlet {
    private Map<String, Object> map = new HashMap<>();

    public void init(ServletConfig config) {
        String propertyConfig = config.getInitParameter("propertyConfig");

        String realFolder = config.getServletContext().getRealPath("/WEB-INF");
        String realPath = realFolder + "/" + propertyConfig;
        // /WEB-INF/command.properties

        FileInputStream fin = null;
        Properties properties = new Properties();

        try {
            fin = new FileInputStream(realPath);
            properties.load(fin);
        } catch (IOException e) {
            System.out.println("realPath를 읽어오는데 실패햇3");
            e.printStackTrace();
        }finally {
            try {
                fin.close();
            } catch (IOException e) {
                System.out.println("fin닫기 실패했3");
                e.printStackTrace();
            }
        }

        Iterator<Object> it = properties.keySet().iterator();
        while(it.hasNext()) {
            String key = (String)it.next();

            String className = properties.getProperty(key);

            try {
                Class<?> classType = Class.forName(className);
                Object ob = classType.getConstructor().newInstance();

                map.put(key, ob);

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (SecurityException e) {
                e.printStackTrace();
            }
        }//while
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        excute(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        excute(request, response);
    }

    protected void excute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if(request.getMethod().equals("POST")){
            request.setCharacterEncoding("UTF-8");
        }

        //member/loginForm.do
        String category = request.getServletPath();
        CommandProcess commandProcess = (CommandProcess) map.get(category);

        String view = null;
        try {
            view = commandProcess.requestPro(request, response);
        } catch (Throwable e) {
            System.out.println("view를 가져오지못했3 map과 category를 확인해야함");
            e.printStackTrace();
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(view);
        dispatcher.forward(request, response);
    }
}