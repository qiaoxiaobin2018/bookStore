package cn.itcast.bookStore.category.servlet.admin;

import cn.itcast.bookStore.category.Service.CategoryService;
import cn.itcast.bookStore.category.domain.Category;
import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "adminCategoryServlet",urlPatterns = "/admin/adminCategoryServlet")
public class adminCategoryServlet extends BaseServlet {
    private CategoryService categoryService = new CategoryService();
    /*
    *  查询所有分类
    *  1. 调用 CategoryService#findAll(),得到所有分类 List<Category>
    *  2. 保存至 request 中，并转发到 /admin/category/list.jsp 中
    * */
    public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("categoryList",categoryService.findAll());
        return "/admin/category/list.jsp";
    }

    /*
    * 添加分类
    * */
    public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        * 1. 封装 Category
        * 2. 补全 cid
        * 3. 调用 CategoryService#add()
        * 4. 调用 findAll() 返回到 List.jsp 中
        * */
        Category category = CommonUtils.toBean(request.getParameterMap(),Category.class);
        category.setCid(CommonUtils.uuid());

        categoryService.add(category);
        return findAll(request,response);
    }

    /*
    * 删除一个分类
    * 1. 获取 cid 参数
    * 2. 调用 CategoryService#delete() ,进行删除
    *       保存异常信息到 request 中，转发到 msg.jsp 中
    * 3. 调用 findAll() 方法
    * */
    public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            categoryService.delete(request.getParameter("cid"));
            return findAll(request,response);
        }catch (CategoryException e){
            request.setAttribute("msg",e.getMessage());
            return "f:/admin/main/msg.jsp";
        }
    }

    /*
    * 加载分类详情
    * 1. 通过 cid 加载 category
    * 2. 将 category 保存到 request 中
    * 3. 转发到详情页 mod.jsp 中
    * */
    public String load(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cid = request.getParameter("cid");
        request.setAttribute("category",categoryService.load(cid));
        return "f:/admin/category/mod.jsp";
    }

    /*
    * 修改分类
    * 1. 封装 Category
    * 2. 调用  CategoryService#edit() 方法
    * 3. 转发到 list.jsp 中
    * */
    public String edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Category category = CommonUtils.toBean(request.getParameterMap(),Category.class);
        categoryService.edit(category);

        return  findAll(request,response);
    }
}
