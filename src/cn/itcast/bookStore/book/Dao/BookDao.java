package cn.itcast.bookStore.book.Dao;

import cn.itcast.bookStore.book.domain.Book;
import cn.itcast.jdbc.TxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class BookDao {
    private  QueryRunner queryRunner = new TxQueryRunner();
    //查询所有图书
    public List<Book> findAll(){
        String sql = "select * from book";
        try {
            return  queryRunner.query(sql,new BeanListHandler<Book>(Book.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //按分类查询
    public List<Book> findByCategory(String cid) {
        String sql = "select * from book where cid=?";
        try {
            return queryRunner.query(sql, new BeanListHandler<Book>(Book.class),cid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Book load(String bid) {
        String sql = "select * from book where bid=?";
        try {
            return queryRunner.query(sql, new BeanHandler<Book>(Book.class),bid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}