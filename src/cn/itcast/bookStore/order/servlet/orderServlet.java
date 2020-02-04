package cn.itcast.bookStore.order.servlet;

import cn.itcast.bookStore.order.service.OrderService;
import cn.itcast.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "orderServlet",urlPatterns = "/orderServlet")
public class orderServlet extends BaseServlet {
    private OrderService orderService  = new OrderService();
    public String fun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return null;
    }
}
