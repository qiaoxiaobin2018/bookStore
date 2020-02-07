package cn.itcast.bookStore.book.service;

import cn.itcast.bookStore.book.Dao.BookDao;
import cn.itcast.bookStore.book.domain.Book;

import java.util.List;

public class BookService {
    private BookDao bookDao = new BookDao();
    //查询所有图书
    public List<Book> findAll(){
        return bookDao.findAll();
    }

    //按分类查询
    public List<Book> findByCategory(String cid) {
        return bookDao.findByCategory(cid);
    }

    //查找一本书的明细
    public Book load(String bid) {
        return bookDao.load(bid);
    }

    /*
     * 添加一本图书
     * */
    public void add(Book book) {
        bookDao.add(book);
    }

    /*
     * 删除一本图书
     * */
    public void delete(String bid) {
        bookDao.delete(bid);
    }

    public void edit(Book book) {
        bookDao.edit(book);
    }
}
