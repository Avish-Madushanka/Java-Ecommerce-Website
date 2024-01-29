
package com.green.service;

import java.util.List;

import com.green.beans.OrderBean;
import com.green.beans.OrderDetails;
import com.green.beans.TransactionBean;

public interface OrderService {

	public String paymentSuccess(String userName, String total);

	public boolean addOrder(OrderBean order);

	public boolean addTransaction(TransactionBean transaction);

	public int countSoldItem(String prodId);

	public List<OrderBean> getAllOrders();

	public List<OrderBean> getOrdersByUserId(String emailId);

	public List<OrderDetails> getAllOrderDetails(String userEmailId);

	public String shipNow(String orderId, String prodId);
}
