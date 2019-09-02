package com.newer.web.servlet;


import com.google.gson.JsonObject;
import com.newer.domain.TeaOrder;
import com.newer.service.TeaOrderService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;

@WebServlet("/tea")
public class TeaOrderServlet extends HttpServlet {

    private TeaOrderService teaOrderService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TeaOrder teaOrder=new TeaOrder();
        try {
            //封装请求参数，实例化参数对象
            BeanUtils.populate(teaOrder,req.getParameterMap());
            int result=teaOrderService.addOrder(teaOrder);
            JsonObject jsonObject=new JsonObject();
            jsonObject.addProperty("result",result);
            PrintWriter out=resp.getWriter();
            out.print(jsonObject);
            out.close();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init() throws ServletException {
        teaOrderService=new TeaOrderService();
    }
}