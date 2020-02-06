package cn.itcast.bookStore.order.servlet;

import cn.itcast.bookStore.cart.domain.Cart;
import cn.itcast.bookStore.cart.domain.CartItem;
import cn.itcast.bookStore.order.domain.Order;
import cn.itcast.bookStore.order.domain.OrderItem;
import cn.itcast.bookStore.order.service.OrderException;
import cn.itcast.bookStore.order.service.OrderService;
import cn.itcast.bookStore.user.domain.User;
import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "orderServlet",urlPatterns = "/orderServlet")
public class orderServlet extends BaseServlet {
    private OrderService orderService  = new OrderService();
    //添加订单
    public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        * 使用 session 中的购物车 cart 生成订单 Order
        * */
        //从 session 中获取购物车 cart
        Cart cart = (Cart)request.getSession().getAttribute("cart");
        /*
        * 使用 cart 生成 订单 order
        * */
        //创建 order 对象，并设置属性
        Order order = new Order();
        order.setOid( CommonUtils.uuid());
        order.setOrdertime(new Date());

        System.out.println("cart Total:" + cart.getTotal());


        order.setTotal(cart.getTotal());
        order.setState(1);
        order.setUser((User)request.getSession().getAttribute("user"));
//        order.setAddress(request.getParameter("address"));
        //给订单添加 orderitem
        List<OrderItem> orderItemList = new ArrayList<OrderItem>();
        for(CartItem cartItem:cart.getCartItems()){
            OrderItem orderItem = new OrderItem();
            orderItem.setIid(CommonUtils.uuid());
            orderItem.setBook(cartItem.getBook());
            orderItem.setCount(cartItem.getCount());
            orderItem.setOrder(order);
            orderItem.setSubtotal(cartItem.getSubTotal());

            orderItemList.add(orderItem);
        }
        // 把所有的订单条目添加到订单中
        order.setOrderItemList(orderItemList);

        //清空购物车
        cart.clear();

        //使用 orderService 添加订单
        try {
            orderService.add(order);
            //保存并转发
             request.setAttribute("order",order);
             return "f:/order/desc.jsp";
        } catch (Exception e) {
            request.setAttribute("msg",e.getMessage());
            return "f:/msg.jsp";
        }
    }
    //查找我的订单
    public String myOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        /*
        * 1. 从 session 中得到 user
        * 2. 使用 uid 调用 orderService#myOrders() 方法得到 List<Order>
        * 3. 将 List<Order> 保存到 request 中
        * 4. 转发到 /order/list.jsp 中
        * */
        User user = (User)request.getSession().getAttribute("user");
        List<Order> orderList = orderService.myOrders(user.getUid());
        request.setAttribute("orderList",orderList);

        return "f:/order/list.jsp";
    }
    //加载指定订单
    public String load(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        /*
        * 1. 获取 oid 参数
        * 2. 使用 oid 调用 Service 方法得到 Order
        * 3. 将 Order 保存到  request 域中，转发
        * */
        request.setAttribute("order",orderService.load(request.getParameter("oid")));
        return "f:/order/desc.jsp";
    }

    /*
    * 确认收货
    * */
    public String confirm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {
            orderService.confirm(request.getParameter("oid"));
            request.setAttribute("msg","确认收货成功，订单已完成！");
            return "f:/qian/msg.jsp";
        } catch (OrderException e) {
            request.setAttribute("msg",e.getMessage());
            return "f:/qian/msg.jsp";
        }
    }

    /*
    * 支付
    * */
    public String pay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        /*
        * 1. 获取订单 oid
        * 2. 改变订单状态 orderService#pay()
        * */
        String oid = request.getParameter("oid");
        orderService.pay(oid);
        request.setAttribute("msg","支付成功！");
        return "f:/qian/msg.jsp";
    }

}
