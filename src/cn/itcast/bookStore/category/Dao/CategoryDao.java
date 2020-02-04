package cn.itcast.bookStore.category.Dao;

import cn.itcast.bookStore.book.domain.Book;
import cn.itcast.bookStore.category.domain.Category;
import cn.itcast.jdbc.TxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
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
}
