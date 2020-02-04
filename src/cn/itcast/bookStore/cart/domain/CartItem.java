package cn.itcast.bookStore.cart.domain;

import cn.itcast.bookStore.book.domain.Book;

import java.math.BigDecimal;

public class CartItem {
    private Book book;//商品
    private int count;//数量

    public CartItem(Book book, int count) {
        this.book = book;
        this.count = count;
    }

    //获取小计
    public double getSubTotal(){
        /*
        * 使用 BigDecimal 进行运算以处理二进制运算误差
        * */
        BigDecimal price = new BigDecimal(book.getPrice() + "");// 必须使用 String 进行初始化
        BigDecimal _count = new BigDecimal(count + "");
        return price.multiply(_count).doubleValue();
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "book=" + book +
                ", count=" + count +
                '}';
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public CartItem() {
    }
}
