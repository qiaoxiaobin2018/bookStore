package cn.itcast.bookStore.category.servlet;

import cn.itcast.bookStore.category.Service.CategoryService;
import cn.itcast.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "categoryServlet",urlPatterns = "/categoryServlet")
public class categoryServlet extends BaseServlet {
    private CategoryService categoryService = new CategoryService();
    //查询所有分类
    public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setAttribute("categoryList",categoryService.findAll());
        return "f:/left.jsp";
    }
}
