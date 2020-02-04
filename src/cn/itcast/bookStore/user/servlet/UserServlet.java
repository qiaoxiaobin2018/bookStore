package cn.itcast.bookStore.user.servlet;

import cn.itcast.bookStore.cart.domain.Cart;
import cn.itcast.bookStore.user.UserException;
import cn.itcast.bookStore.user.domain.User;
import cn.itcast.bookStore.user.service.UserService;
import cn.itcast.commons.CommonUtils;
import cn.itcast.mail.Mail;
import cn.itcast.mail.MailUtils;
import cn.itcast.servlet.BaseServlet;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@WebServlet(name = "UserServlet",urlPatterns = "/userServlet")
public class UserServlet extends BaseServlet {
    private UserService userService = new UserService();
    //注册
    public String regist(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        /*
         * 1. 封装表单数据到 form 对象中；
         * 2. 补全 ： uid,code
         * 3. 输入校验；
         *       如果出错，保存错误信息，form 到 request 域中，转发到 msg.jsp 中
         * 4. 调用 service 方法完成注册
         *       如果出错，保存错误信息，form 到 request 域中，转发到 msg.jsp 中
         * 5. 发邮件
         * 6. 保存成功信息转发到 msg.jsp 中
         * */
        //封装
        User form = CommonUtils.toBean(request.getParameterMap(),User.class);
        //补全
        form.setUid(CommonUtils.uuid());
        form.setCode(CommonUtils.uuid() + CommonUtils.uuid());
        //校验 username,password,email
        Map<String,String> errorMap = new HashMap<String,String>();

        String username = form.getUsername();
        if(username == null || username.trim().isEmpty()){
            errorMap.put("usernameError","用户名不能为空！");
        }
        else if(username.length() < 3 || username.length() > 10){
            errorMap.put("usernameError","用户名的长度在 3~10 之间！");
        }

        String password = form.getPassword();
        if(password == null || password.trim().isEmpty()){
            errorMap.put("passwordError","密码不能为空！");
        }
        else if(password.length() < 3 || password.length() > 10){
            errorMap.put("passwordError","密码的长度在 3~10 之间！");
        }

        String email = form.getEmail();
        if(email == null || email.trim().isEmpty()){
            errorMap.put("emailError","电子邮件不能为空！");
        }
        else if(!email.matches("\\w+@\\w+\\.\\w+")){
            errorMap.put("emailError","电子邮件格式错误！");
        }
        //判断是否存在错误信息
        if(errorMap.size() > 0){
            /*
            * 1. 保存错误信息到 request 中
            * 2. 保存  form 到 request 中
            * 3. 转发到  regist.jsp 中
            * */

            System.out.println("信息校验错误：\n");
            System.out.println(errorMap.toString());

            request.setAttribute("errorMap",errorMap);
            request.setAttribute("form",form);
            return "f:/qian/user/regist.jsp" ;
        }

        //调用 service 的 regist 方法
        try {
            userService.regist(form);
        } catch (UserException e) {
            /*
            * 1. 保存错误信息到 request 中
            * 2. 保存 form
            * 3. 转发到 regist.jsp 中
            * */
            request.setAttribute("msg",e.getMessage());
            request.setAttribute("form",form);
            return "f:/qian/user/regist.jsp";
        }

        //发送激活邮件
        //准备配置文件
        Properties properties = new Properties();
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("mail_template.properties");
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
        properties.load(inputStreamReader);
        String host = properties.getProperty("host");
        String uname = properties.getProperty("uname");
        String pswd = properties.getProperty("pswd");
        String from = properties.getProperty("from");
        String to = form.getEmail();
        String subject = properties.getProperty("subject");
        String content = properties.getProperty("content");
        //替换 code , 将 form.code 添加到 content 中
        content = MessageFormat.format(content,form.getCode());

        System.out.println(subject);

        //得到 Session
        Session session = MailUtils.createSession(host,uname,pswd);
        //创建邮件
        Mail mail = new Mail(from,to,subject,content);
        //发
        try {
            MailUtils.send(session,mail);
        } catch (MessagingException e) {

            System.out.println("邮件发送失败！\n");

            request.setAttribute("msg","邮件发送失败，请重新注册！");
            //转发到 msg.jsp 中
            return "f:/qian/msg.jsp";
        }

        System.out.println("注册成功！\n");

        /**
         * 1. 保存成功信息
         * 2. 转发到 msg.jsp 中
         * */
        request.setAttribute("msg","注册成功，请在 1 分钟内到邮箱激活！");
        return "f:/qian/msg.jsp";

    }
    //激活
    public String activation(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        /*
        * 1. 获取激活码 code
        * 2. 调用 service 方法进行激活
        * 3.    抛出异常，保存异常信息到 request 中，转发到 msg.jsp 中
        * 4.  保存成功信息到 request 中，转发到 msg.jsp 中
        * */
        String code = request.getParameter("code");
        try {
            userService.activation(code);
            request.setAttribute("msg","激活成功，请登录！");
        } catch (UserException e) {
            request.setAttribute("msg",e.getMessage());
        }
        return "f:/qian/msg.jsp";
    }
    //登录
    public String login(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        /*
        * 1. 封装表单数据 到 form.jsp 中
        * 2. 调用 service 中的方法进行激活
        * 3.    保存错误信息、form到 request 中，转发到  login.jsp 中
        * 4. 保存用户信息到 session 中，转发到  index.jsp 中
        * */
        // 封装表单数据
        User form = CommonUtils.toBean(request.getParameterMap(),User.class);
        try {
            User user = userService.login(form);
            //保证安全，创建 session 之前，先让之前的 session 失效！
            request.getSession().invalidate();
            //保存登录信息到 session 中
            request.getSession().setAttribute("user",user);
            //为用户添加购物车
            request.getSession().setAttribute("cart",new Cart());
            return "r:/index.jsp";
        } catch (UserException e) {
            request.setAttribute("msg",e.getMessage());
            request.setAttribute("form",form);
            return "f:/qian/user/login.jsp";
        }

    }
    //退出
    public String quit(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        request.getSession().invalidate();
        return "r:/index.jsp";
    }


}
