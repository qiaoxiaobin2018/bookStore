package cn.itcast.bookStore.category.Service;

import cn.itcast.bookStore.book.Dao.BookDao;
import cn.itcast.bookStore.book.domain.Book;
import cn.itcast.bookStore.category.Dao.CategoryDao;
import cn.itcast.bookStore.category.domain.Category;
import cn.itcast.bookStore.category.servlet.admin.CategoryException;

import java.util.List;

public class CategoryService {
    private CategoryDao categoryDao = new CategoryDao();
    private BookDao bookDao = new BookDao();
    //查询所有分类
    public List<Category> findAll() {
        return categoryDao.findAll();
    }

    /*
     * 添加分类
     * */
    public void add(Category category) {
       categoryDao.add(category);
    }

    public void delete (String cid) throws CategoryException{
        //获取该分类下的图书数量
        int count = bookDao.getCountByCid(cid);
        //如果该分类下存在图书，不能删除，抛出异常
        if(count > 0){
            throw new CategoryException("抱歉，该分类下存在图书，不能删除！");
        }
        //删除
        categoryDao.delete(cid);
    }

    /*
    * 加载分类详情
    * */
    public Category load(String cid) {
        return categoryDao.load(cid);
    }

    /*
    * 编辑指定分类
    * */
    public void edit(Category category) {
        categoryDao.edit(category);
    }
}
