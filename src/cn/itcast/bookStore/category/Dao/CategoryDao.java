package cn.itcast.bookStore.category.Dao;

import cn.itcast.bookStore.book.domain.Book;
import cn.itcast.bookStore.category.domain.Category;
import cn.itcast.jdbc.TxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class CategoryDao {
    private  QueryRunner queryRunner = new TxQueryRunner();
    //查询所有分类
    public List<Category> findAll() {
        String sql = "select * from category";
        try {
            return  queryRunner.query(sql,new BeanListHandler<Category>(Category.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /*
    * 添加分类
    * */
    public void add(Category category) {
        String sql = "insert into category values(?,?)";
        try {
            queryRunner.update(sql,category.getCid(),category.getCname());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*
    * 删除指定分类
    * */
    public void delete(String cid) {
        String sql = "delete from category where cid=?";
        try {
            queryRunner.update(sql,cid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*
    * 加载指定分类
    * */
    public Category load(String cid) {
        String sql = "select * from category where cid=?";
        try {
            return queryRunner.query(sql,new BeanHandler<Category>(Category.class),cid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*
    * 编辑指定分类
    * */
    public void edit(Category category) {
        String sql = "update category set cname=? where cid=?";
        try {
            queryRunner.update(sql,category.getCname(),category.getCid());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
