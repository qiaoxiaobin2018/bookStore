package cn.itcast.bookStore.order.domain;

import cn.itcast.bookStore.book.domain.Book;

public class OrderItem {
    private String iid;
    private int count;
    private double subtotal;
    private Order order;
    private Book book;

    @Override
    public String toString() {
        return "OrderItem{" +
                "iid='" + iid + '\'' +
                ", count=" + count +
                ", subtotal=" + subtotal +
                ", order=" + order +
                ", book=" + book +
                '}';
    }

    public String getIid() {
        return iid;
    }

    public void setIid(String iid) {
        this.iid = iid;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public OrderItem() {
    }

    public OrderItem(String iid, int count, double subtotal, Order order, Book book) {
        this.iid = iid;
        this.count = count;
        this.subtotal = subtotal;
        this.order = order;
        this.book = book;
    }
}
