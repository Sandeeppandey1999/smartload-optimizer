package com.example.optimizer.dto;

import java.util.List;

import com.example.optimizer.model.Order;
import com.example.optimizer.model.Truck;

public class RequestDto {
       private Truck truck;
       private List<Order> orders;
       
       public Truck getTruck() {
           return truck;
       }
       public void setTruck(Truck truck) {
           this.truck = truck;
       }
       public List<Order> getOrders() {
           return orders;
       }
       public void setOrders(List<Order> orders) {
           this.orders = orders;
       }

       
}
