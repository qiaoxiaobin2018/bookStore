package cn.itcast.bookStore.user.service;

import cn.itcast.bookStore.user.Dao.UserDao;
import cn.itcast.bookStore.user.UserException;
import cn.itcast.bookStore.user.domain.User;

/*
* User 服务层
* */
public class UserService {
    UserDao userDao = new UserDao();

    //注册
    public void regist(User form) throws UserException {
        //校验用户名是否已被注册
        User user1 = userDao.findByUsername(form.getUsername());
        if(user1 != null){
            //返回异常信息
            throw new UserException("用户名已被注册！");
        }
        //校验email是否已被注册
        User user2 = userDao.findByEmail(form.getEmail());
        if(user2 != null){
            //返回异常信息
            throw new UserException("该邮箱已被注册！");
        }
        //添加用户
        userDao.add(form);

    }
    //激活
    public void activation(String code) throws UserException {
        //使用 code 查询用户
        User user = userDao.findByCode(code);
        //如果用户不存在，抛出异常
        if(user == null){
            throw new UserException("激活码无效！");
        }
        //用户存在，判断用户是否已经激活
        String state = user.getState();
        if(state != null && state.equals("true")){
            throw new UserException("您的账户已经激活！");
        }
        // 用户存在并且还没有激活
        userDao.updateState(user.getUid(),"true");
    }
    //登录
    public User login(User form) throws UserException {
        /*
        * 1. 使用 Username 查询得到 user;
        * 2.   如果 user 为 null,抛出异常（用户不存在！）
        * 3. 校验密码
        * 4. 校验激活状态
        * */
        User user = userDao.findByUsername(form.getUsername());
        //如果用户不存在
        if(user == null){
            throw new UserException("无此用户！");
        }
        //校验密码
        if(!form.getPassword().equals(user.getPassword())){
            throw new UserException("密码错误！");
        }
        //校验登陆状态
        if(user.getState() == null){
            throw new UserException("账户未激活！");
        }
        return  user;
    }
}
