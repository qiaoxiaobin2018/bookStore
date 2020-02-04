package cn.itcast.bookStore.category.Service;

import cn.itcast.bookStore.book.domain.Book;
import cn.itcast.bookStore.category.Dao.CategoryDao;
import cn.itcast.bookStore.category.domain.Category;

import java.util.List;

public class CategoryService {
    private CategoryDao categoryDao = new CategoryDao();
    //查询所有分类
    public List<Category> findAll() {
        return categoryDao.findAll();
    }
}
