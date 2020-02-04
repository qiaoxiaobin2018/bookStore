package cn.itcast.bookStore.order.domain;

import cn.itcast.bookStore.user.domain.User;
import sun.awt.SunGraphicsCallback;

import java.util.Date;
import java.util.List;

public class Order {
    private String oid;
    private Date ordertime;
    private double total;
    private int state;//订单状态 4 种，1：未付款；2：已付款未发货；3：已发货未确认收获；4：已确认收货
    private User user;
    private String address;
    private List<OrderItem> orderItemList;//当前订单下所有的条目

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    @Override
    public String toString() {
        return "Order{" +
                "oid='" + oid + '\'' +
                ", ordertime=" + ordertime +
                ", total=" + total +
                ", state=" + state +
                ", user=" + user +
                ", address='" + address + '\'' +
                '}';
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public Date getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(Date ordertime) {
        this.ordertime = ordertime;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Order() {
    }

    public Order(String oid, Date ordertime, double total, int state, User user, String address) {
        this.oid = oid;
        this.ordertime = ordertime;
        this.total = total;
        this.state = state;
        this.user = user;
        this.address = address;
    }
}
