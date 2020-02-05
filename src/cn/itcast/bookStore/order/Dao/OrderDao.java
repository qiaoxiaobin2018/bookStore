package cn.itcast.bookStore.order.Dao;

import cn.itcast.bookStore.book.domain.Book;
import cn.itcast.bookStore.order.domain.Order;
import cn.itcast.bookStore.order.domain.OrderItem;
import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.TxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class OrderDao {
    private QueryRunner queryRunner = new TxQueryRunner();
    /*
    * 添加订单
    * */
    public void addOrder(Order order){
        String sql = "insert into orders values(?,?,?,?,?,?)";
        /*
        *  处理 utils 的  Data 转化成 sql 的 Timestamp
        * */
        Timestamp timestamp = new Timestamp(order.getOrdertime().getTime());
        Object[] params = {order.getOid(),timestamp,order.getTotal(),
                order.getState(),order.getUser().getUid(),order.getAddress()};
        try {
            queryRunner.update(sql,params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /*
    * 添加订单项
    * */
    public void addOrderItemList(List<OrderItem> orderItemList){
        /*
        * 使用 queryRunner.batch(String sql,Object[][] params)
        *   其中 params 是多个一维数组，每一个一维数组都与 sql 配合执行一次
        * */
        String sql = "insert into orderitem values(?,?,?,?,?)";
        /*
        * 创建二维数组
        * */
        //将 orderItemList 转化为 二维数组
        Object[][] params = new Object[orderItemList.size()][];
        for(int i = 0;i<orderItemList.size();i ++){
            OrderItem item = orderItemList.get(i);
            params[i] = new Object[]{item.getIid(),item.getCount(),item.getSubtotal(),item.getOrder().getOid(),item.getBook().getBid()};
        }
        try {
            //执行批处理
            queryRunner.batch(sql,params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /*
    * 我的订单
    * */
    public List<Order> findByUid(String uid) {
        /*
        * 1. 使用 uid 查询出当前用户的所有 order --> List<Order>
        * 2. 循环遍历所有的 order ，为其加载它所有的 orderItem
        * */
        //先查出所有的订单
        String sql = "select * from orders where uid=?";
        try {
            List<Order> orderList = queryRunner.query(sql,new BeanListHandler<Order>(Order.class),uid);
            /**
             * 循环遍历所有订单，加载其所有的订单子项
             */
            for(Order order:orderList){
                loadOrderItemList(order);
            }

            return orderList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*
    * 为每一个 order 添加 orderItemList
    * */
    private void loadOrderItemList(Order order) {
        //查询两张表，联合查询
        String sql = "select * from orderitem o,book b where o.bid=b.bid and oid=?";
        try {
            List<Map<String,Object>> mapList = queryRunner.query(sql,new MapListHandler(),order.getOid());
            /*
            * 每一个 Map 表示一行结果
            * */
            /**
             * 我们需要使用 一个 Map 生成两个对象 OrderItem 和 Book，并将 Book 添加到 OrderItem 中
             */
            //遍历 mapList，生成两个对象，并保存 OrderItem 到 List 中
            List<OrderItem> orderItemList = toOrderItemList(mapList);
            order.setOrderItemList(orderItemList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 将 mapList 转化为 orderItemList
     */
    private List<OrderItem> toOrderItemList(List<Map<String, Object>> mapList) {
        List<OrderItem> orderItemList = new ArrayList<OrderItem>();
        for(Map<String,Object> map:mapList){
            OrderItem orderItem = toOrderItem(map);
            orderItemList.add(orderItem);
        }
        return orderItemList;
    }

    /**
     * 将一个 map 转化为 一个 OrderItem
     */
    private OrderItem toOrderItem(Map<String, Object> map) {
        OrderItem orderItem = CommonUtils.toBean(map,OrderItem.class);
        Book book = CommonUtils.toBean(map,Book.class);
        orderItem.setBook(book);
        return orderItem;
    }

    /*
    * 加载指定订单
    * */
    public Order load(String oid) {
        String sql = "select * from orders where oid=?";
        try {
            Order order = queryRunner.query(sql,new BeanHandler<Order>(Order.class),oid);
            /**
             * 循环遍历所有订单，加载其所有的订单子项
             */
            loadOrderItemList(order);
            return order;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*
    * 通过 oid 查询订单状态
    * */
    public int getStateByOid(String oid){
        String sql = "select state from orders where oid=?";
        try {
            Number num = (Number)queryRunner.query(sql,new ScalarHandler<>(),oid);
            return num.intValue();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /*
    * 修改订单状态
    * */
    public void updateState(String oid,int state){
        String sql = "update orders set state=? where oid=?";
        try {
            queryRunner.update(sql,state,oid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
