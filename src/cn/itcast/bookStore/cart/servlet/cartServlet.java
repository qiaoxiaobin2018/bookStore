package cn.itcast.bookStore.cart.servlet;

import cn.itcast.bookStore.book.Dao.BookDao;
import cn.itcast.bookStore.book.domain.Book;
import cn.itcast.bookStore.book.service.BookService;
import cn.itcast.bookStore.cart.domain.Cart;
import cn.itcast.bookStore.cart.domain.CartItem;
import cn.itcast.servlet.BaseServlet;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;

@WebServlet(name = "cartServlet",urlPatterns = "/cartServlet")
public class cartServlet extends BaseServlet {
    /*
     * 添加购物条目
     * */
    public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //得到车
        Cart myCart = (Cart) request.getSession().getAttribute("cart");
        if(myCart == null){
            //用户未登录
            request.setAttribute("msg","请先登录！");
            return "f:/qian/msg.jsp";
        }
        /*
         * 1. 从表单中获取 bid  和 count
         * 2. 创建条目
         *       3.通过 bid 查询数据库得到 Book
         *       4.直接创建条目
         * 5. 把条目添加到车中
         *  */
        String bid = request.getParameter("bid");
        int count = Integer.parseInt(request.getParameter("count"));
        Book book = new BookService().load(bid);
        CartItem cartItem = new CartItem(book, count);
        //将条目添加到车中
        myCart.add(cartItem);

        return "f:/cart/list.jsp";

    }

    /*
     * 清空购物车
     * */
    public String clear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //得到车
        Cart myCart = (Cart) request.getSession().getAttribute("cart");
        if(myCart == null){
            //用户未登录
            request.setAttribute("msg","请先登录！");
            return "r:/qian/msg.jsp";
        }
        myCart.clear();

        return "f:/cart/list.jsp";
    }

    /*
     * 删除指定条目
     * */
    public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //得到车
        Cart myCart = (Cart) request.getSession().getAttribute("cart");
        if(myCart == null){
            //用户未登录
            request.setAttribute("msg","请先登录！");
            return "r:/qian/msg.jsp";
        }
        String bid = request.getParameter("bid");
        myCart.delete(bid);

        return "f:/cart/list.jsp";
    }
}
