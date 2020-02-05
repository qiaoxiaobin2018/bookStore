package cn.itcast.bookStore.order.service;

public class OrderException extends Exception {
    public OrderException() {
    }

    public OrderException(String message) {
        super(message);
    }
}
