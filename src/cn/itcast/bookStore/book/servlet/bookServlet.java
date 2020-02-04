package cn.itcast.bookStore.book.servlet;

import cn.itcast.bookStore.book.service.BookService;
import cn.itcast.bookStore.category.Service.CategoryService;
import cn.itcast.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "bookServlet",urlPatterns = "/bookServlet")
public class bookServlet extends BaseServlet {
    private BookService bookService = new BookService();
    //查询所有分类
    public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("bookList",bookService.findAll());

        return "f:/book/list.jsp";
    }
    //按类别查询
    public String findByCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cid = request.getParameter("cid");
        request.setAttribute("bookList", bookService.findByCategory(cid));

        return "f:/book/list.jsp";
    }
    //查找一本书的明细
    public String load(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bid = request.getParameter("bid");
        request.setAttribute("book", bookService.load(bid));

        return "f:/book/desc.jsp";
    }

}
