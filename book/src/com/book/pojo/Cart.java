package com.book.pojo;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
    public Cart() {
    }

    public Cart(Map<Integer, CartItem> items) {
        this.items = items;
    }

    /*** key 是商品编号， * value，是商品信息 */
    private Map<Integer,CartItem> items = new LinkedHashMap<Integer,CartItem>();

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }
    public Integer getTotalCount() {
        Integer totalCount = 0;
        for (Map.Entry<Integer,CartItem>entry : items.entrySet()) {
            totalCount += entry.getValue().getCount();
        }
        return totalCount;
    }
    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);
        for (Map.Entry<Integer,CartItem>entry : items.entrySet()) {
            totalPrice = totalPrice.add(entry.getValue().getTotalPrice());

        }
        return totalPrice;
    }
    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }

    public void addItem(CartItem cartItem){
        CartItem item = items.get(cartItem.getId());
        if(item==null){
            items.put(cartItem.getId(),cartItem);
         }else{
            //更新数量
            item.setCount(item.getCount()+1);
            //更新总金额
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }
    }
    public void deleteItem(Integer id){
         items.remove(id);
    }
    public void clear(){
        items.clear();
    }
    public void updateCount(Integer id,Integer count){
        CartItem cartItem = items.get(id);
        if(cartItem!=null){
            cartItem.setCount(count);
            cartItem.setTotalPrice(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount())));
        }
    }
}
