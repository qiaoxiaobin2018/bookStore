package cn.itcast.bookStore.cart.domain;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
    private Map<String,CartItem> map = new LinkedHashMap<String,CartItem>();
    /*
    * 添加条目到购物车中
    * */
    public void add(CartItem cartItem){
        //判断条目是否已经存在
        String bid = cartItem.getBook().getBid();
        if(map.containsKey(bid)){
            //包含,数量相加
            CartItem _cartItem = map.get(bid);
            _cartItem.setCount(_cartItem.getCount() + cartItem.getCount());
            map.put(bid,_cartItem);
        }
        else{
            //不包含，直接存
            map.put(bid,cartItem);
        }
    }
    /*
    * 清空购物车
    * */
    public void clear(){
        map.clear();
    }
    /*
    *  删除指定条目
    * */
    public void delete(String bid){
        map.remove(bid);
    }
    /*
    * 获取我的购物车
    * */
    public Collection<CartItem> getCartItems(){
        return map.values();
    }
    /*
    * 获取购物车总计
    * 使用 BigDecimal 处理二进制计算误差问题
    * */
    public double getTotal(){
        BigDecimal total = new BigDecimal(0.0 + "");
        for(CartItem cartItem:map.values()){
            BigDecimal subTotal = new BigDecimal(cartItem.getSubTotal() + "");
            total = total.add(subTotal);
        }
        return total.doubleValue();
    }
}
