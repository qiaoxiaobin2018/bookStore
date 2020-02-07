package cn.itcast.bookStore.book.servlet.admin;

import cn.itcast.bookStore.book.domain.Book;
import cn.itcast.bookStore.book.service.BookService;
import cn.itcast.bookStore.category.Service.CategoryService;
import cn.itcast.bookStore.category.domain.Category;
import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;
import org.hamcrest.core.CombinableMatcher;

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
     * 修改一本图书
     * */
    public String edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //封装 Book 对象
        Book book = CommonUtils.toBean(request.getParameterMap(),Book.class);
        Category category = CommonUtils.toBean(request.getParameterMap(),Category.class);
        book.setCategory(category);
        //调用 BookService#edit()方法
        bookService.edit(book);
        return load(request,response);
    }

    /*
     * 删除一本图书
     * */
    public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(request.getParameter("bid"));
        bookService.delete(request.getParameter("bid"));
        return findAll(request,response);
    }

    /*
     * 添加图书之前 返回 所有分类
     * */
    public String addPre(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("categoryList",categoryService.findAll());
        return "f:/admin/book/add.jsp";
    }

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
