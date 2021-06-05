package com.bjpowernode.crm.settings.web.controller;

import com.bjpowernode.crm.settings.dao.UserDao;
import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.settings.services.UserService;
import com.bjpowernode.crm.settings.services.impl.UserServiceImpl;
import com.bjpowernode.crm.utils.MD5Util;
import com.bjpowernode.crm.utils.PrintJson;
import com.bjpowernode.crm.utils.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入用户登录模块");

        String path = request.getServletPath();
        if ("/settings/user/login.do".equals(path)){
            login(request,response);
        }else if("/settings/user/xxx.do".equals(path)){

        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进行登陆验证");
        User user = null;

        String loginAct = request.getParameter("loginAct");
        String loginPwd = request.getParameter("loginPwd");
        //将密码转换为MD5的密文形式
        loginPwd = MD5Util.getMD5(loginPwd);
        //接收ip地址
        String ip = request.getRemoteAddr();
        System.out.println("-------------ip:" + ip);
        UserService us = (UserService) ServiceFactory.getService(new UserServiceImpl());
        try {
            user = us.login(loginAct,loginPwd,ip);
            request.getSession().setAttribute("user",user);
            //登陆成功
            PrintJson.printJsonFlag(response,true);
        } catch (Exception e) {
            //登陆失败
            e.printStackTrace();
            String msg = e.getMessage();
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("success",false);
            map.put("msg",msg);
            PrintJson.printJsonObj(response,map);

        }


        request.getSession().setAttribute("user",user);
    }
}
