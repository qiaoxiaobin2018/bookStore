package cn.itcast.bookStore.book.servlet.admin;

import cn.itcast.bookStore.book.service.BookService;
import cn.itcast.bookStore.category.Service.CategoryService;
import cn.itcast.servlet.BaseServlet;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "adminBookServlet",urlPatterns = "/admin/adminBookServlet")
public class adminBookServlet extends BaseServlet {
    private BookService bookService = new BookService();
    private CategoryService categoryService = new CategoryService();
    /*
    * 查询所有图书
    * */
    public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("bookList",bookService.findAll());
        return "f:/admin/book/list.jsp";
    }

    /*
     * 查询指定图书
     * 1. 获取 bid 参数
     * 2. 调用 BookService#load() 方法，得到 Book 对象
     * 3. 调用 CategoryService#findAll() 方法，得到 List<Category>，存到 request 中
     * 4. 将 Book 设置到 request 中，转发到 /admin/book/desc.jsp 中
     * */
    public String load(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bid = request.getParameter("bid");
        request.setAttribute("book",bookService.load(bid));
        request.setAttribute("categoryList",categoryService.findAll());

        return "f:/admin/book/desc.jsp";
    }
}
