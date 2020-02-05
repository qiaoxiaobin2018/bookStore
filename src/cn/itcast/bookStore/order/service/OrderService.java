package cn.itcast.bookStore.order.service;

import cn.itcast.bookStore.order.Dao.OrderDao;
import cn.itcast.bookStore.order.domain.Order;
import cn.itcast.jdbc.JdbcUtils;

import java.sql.SQLException;
import java.util.List;

public class OrderService {
    private OrderDao orderDao = new OrderDao();
    /*
    * 添加订单 （使用事务）
    *   添加订单项
    *   添加订单子项 （批处理）
    * */
    public void add(Order order) throws Exception {
        try{
            //开启事务
            JdbcUtils.beginTransaction();

            orderDao.addOrder(order);
            orderDao.addOrderItemList(order.getOrderItemList());

            //提交事务
            JdbcUtils.commitTransaction();
        }catch (Exception e){
            //出现异常，回滚
            try {
                JdbcUtils.rollbackTransaction();
            } catch (SQLException ex) {
                throw new RuntimeException("创建订单出错之后回滚出错！");
            }
            throw new RuntimeException("创建订单出错，并成功回滚！");
        }

    }

    /*
    * 我的订单
    * */
    public List<Order> myOrders(String uid) {
        return orderDao.findByUid(uid);

    }

    /*
    * 加载指定订单
    * */
    public Order load(String oid) {
        return orderDao.load(oid);
    }

    /*
    * 确认收货
    * */
    public void confirm(String oid) throws OrderException {
        //校验订单状态
        int state = orderDao.getStateByOid(oid);
        if(state != 3){
            throw new OrderException("确认收货失败，不要耍小聪明！");
        }
        //设置订单状态为 4 ，已完成状态
        orderDao.updateState(oid,4);

    }
}
