package cn.itcast.bookStore.user.filter;

import cn.itcast.bookStore.user.domain.User;
import com.sun.deploy.net.HttpRequest;
import sun.awt.SunGraphicsCallback;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "loginFilter",urlPatterns = {"/cart/*","/order/*","/cartServlet","/orderServlet"})
public class loginFilter implements Filter {
//    private FilterConfig config ;

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        /*
        * 1. 从 session 中获取 user
        * 2. 如果 user 不为 null ,放行
        * 3. 否则，保存错误信息，转发到 login.jsp 中
        * */
        HttpServletRequest request = (HttpServletRequest)req;
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        if(user != null){
            //已经登录，放行
            chain.doFilter(req, resp);
        }
        else{
            request.setAttribute("msg","请先登录吧~~~");
            request.getRequestDispatcher("/qian/user/login.jsp").forward(request,resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {
//        this.config = config;
    }

}
