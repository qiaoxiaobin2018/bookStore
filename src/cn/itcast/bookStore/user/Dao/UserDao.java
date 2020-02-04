package cn.itcast.bookStore.user.Dao;

import cn.itcast.bookStore.user.domain.User;
import cn.itcast.jdbc.TxQueryRunner;
import com.mysql.cj.Query;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

/*
* User 持久层
* */
public class UserDao {
    private QueryRunner queryRunner = new TxQueryRunner();

    //通过用户名查询
    public User findByUsername(String username){
        String sql = "select * from user where username = ?";
        try {
             return  queryRunner.query(sql,new BeanHandler<User>(User.class),username);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    //通过 email 查询
    public User findByEmail(String email){
        String sql = "select * from user where email = ?";
        try {
            return  queryRunner.query(sql,new BeanHandler<User>(User.class),email);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    //通过 code 查询
    public User findByCode(String code){
        String sql = "select * from user where code = ?";
        try {
            return  queryRunner.query(sql,new BeanHandler<User>(User.class),code);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    //更新 state
    public void updateState(String uid,String state){
        String sql = "update user set state=? where uid=?";
        try {
            queryRunner.update(sql,state,uid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    //添加 User
    public void add(User user){
        String sql = "insert into user values(?,?,?,?,?,?)";
        Object[] params = {user.getUid(),user.getUsername(),user.getPassword(),user.getEmail(),user.getCode(),user.getState()};
        try {
            queryRunner.update(sql,params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
