package cn.itcast.bookStore.book.Dao;

import cn.itcast.bookStore.book.domain.Book;
import cn.itcast.bookStore.category.domain.Category;
import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.TxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class BookDao {
    private  QueryRunner queryRunner = new TxQueryRunner();
    //查询所有图书
    public List<Book> findAll(){
        String sql = "select * from book where del='false'";
        try {
            return  queryRunner.query(sql,new BeanListHandler<Book>(Book.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //按分类查询
    public List<Book> findByCategory(String cid) {
        String sql = "select * from book where cid=? AND del='false'";
        try {
            return queryRunner.query(sql, new BeanListHandler<Book>(Book.class),cid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /*
    * 加载指定图书
    * 注意： 当表之间有关联关系时，使用 多表 查询和 Map<String,Object> 实现两个对象的封装
    * */
    public Book load(String bid) {
        String sql = "select * from book where bid=?";
        try {
            Map<String,Object> map = queryRunner.query(sql, new MapHandler(),bid);
            Category category = CommonUtils.toBean(map,Category.class);
            Book book = CommonUtils.toBean(map,Book.class);
            book.setCategory(category);
            return book;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*
    * 查询指定分类的图书数量
    * */
    public int getCountByCid(String cid) {
        String sql = "select count(*) from book where cid=? AND del='false'";
        try {
            Number count = (Number)queryRunner.query(sql,new ScalarHandler<>(),cid);
            return count.intValue();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*
    * 添加图书
    * */
    public void add(Book book) {
        String sql = "insert into book values(?,?,?,?,?,?,?)";
        Object[] objects = {book.getBid(),book.getBname(),book.getPrice(),book.getAuthor(),book.getImage(),book.getCategory().getCid(),"false"};
        try {
            queryRunner.update(sql,objects);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*
     * 删除一本图书
     * */
    public void delete(String bid) {
        String sql = "update book set del='true' where bid=?";
        try {
            queryRunner.update(sql,bid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*
    * 修改一本图书
    * */
    public void edit(Book book) {
        String sql = "update book set bname=?,price=?,author=?,image=?,cid=? where bid=?";
        Object[] objects = {book.getBname(),book.getPrice(),book.getAuthor(),book.getImage(),book.getCategory().getCid(),book.getBid()};
        try {
            queryRunner.update(sql,objects);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
