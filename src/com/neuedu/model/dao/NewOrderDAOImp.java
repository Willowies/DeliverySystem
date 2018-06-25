package com.neuedu.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.neuedu.model.po.Client;
import com.neuedu.model.po.NewOrder;
import com.neuedu.model.po.Product;
import com.neuedu.utils.DBUtil;



public class NewOrderDAOImp implements NewOrderDAO {
	private Connection conn;
	public NewOrderDAOImp(Connection conn) {
		this.conn = conn;
	}
	public void creatNewOrder(NewOrder newOrder) {
		PreparedStatement ps = null;
		try {
			/*测试用代码,运行后将会往数据库neworder表里插一条数据
			Date d = new Date(02,11,2018);
			ps = conn.prepareStatement("insert into neworder values(null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setInt(1, 1);
			ps.setInt(2, 1);
			ps.setInt(3, 3);
			ps.setString(4, "asd");
			ps.setString(5, "asd");
			ps.setString(6, "asd");
			ps.setInt(7, 4);
			ps.setDate(8, d);
			ps.setDate(9, d);
			ps.setInt(10, 1);
			ps.setString(11, "asd");
			ps.setString(12, "asd");
			ps.setFloat(13, 12.3f);
			ps.setInt(14, 1);
			ps.setInt(15, 1);
			ps.setInt(16, 2);
			ps.setString(17, "asdasd");
			ps.setDate(18, d);
			ps.setDate(19,d);
			System.out.println(ps);
			ps.executeUpdate();
			*/
			ps = conn.prepareStatement(" insert into newOrder values (null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setInt(1, newOrder.getClient().getClientId());
			ps.setInt(2, newOrder.getProduct().getProductId());
			ps.setInt(3, newOrder.getProductQuantity());
			ps.setString(4, newOrder.getReceiverName());
			ps.setString(5, newOrder.getReceiverPhone());
			ps.setString(6, newOrder.getReceiverAddress());
			ps.setInt(7, newOrder.getReceiverPostCode());
			ps.setDate(8, new java.sql.Date(newOrder.getGenerateDate().getTime()));
			ps.setDate(9, new java.sql.Date(newOrder.getRequireDate().getTime()));
			ps.setInt(10, newOrder.getWhetherInvoice());
			ps.setString(11, newOrder.getDeliverRequest());
			ps.setString(12, newOrder.getNewOrderRemark());
			ps.setFloat(13, newOrder.getTotal());
			ps.setInt(14, newOrder.getEmployeeId());
			ps.setInt(15, newOrder.getOrderState());
			ps.setInt(16, newOrder.getStatus());
			ps.setString(17, newOrder.getOperator());
			ps.setDate(18, new java.sql.Date(newOrder.getOperateDate().getTime()));
			ps.setDate(19, new java.sql.Date(newOrder.getFinishDate().getTime()));
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
	}

	public void deleteNewOrderById(int newOrderId,String operator,Date operatorDate) {
		try {
			PreparedStatement ps = conn.prepareStatement("update neworder set status =0,operator=?,operateDate=? where neworderid = ?");
			ps.setString(1, operator);
			ps.setDate(2, new java.sql.Date(operatorDate.getTime()));
			ps.setInt(3, newOrderId);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void upDateNewOrder(NewOrder newOrder) {
		
	}

	@Override
	public List<NewOrder> selectNewOrder(NewOrder n) {
		StringBuffer sbf = new StringBuffer("");
		int orderId = n.getOrderId();
		int clienitId = n.getClient().getClientId();
		int productId = n.getProduct().getProductId();
		int productQuantity = n.getProductQuantity();
		String receiverName = n.getReceiverName();
		String receiverPhone= n.getReceiverPhone();
		String receiverAddress=n.getReceiverAddress();
		int receiverPostCode=n.getReceiverPostCode();
		float total=n.getTotal();
		int orderState=n.getOrderState();
		int employeeId=n.getEmployeeId();
		int whetherInvoice=n.getWhetherInvoice();
		Date requireDate=n.getRequireDate();
		Date finishDate=n.getFinishDate();
		Date generateDate=n.getGenerateDate();
		String newOrderRemark=n.getNewOrderRemark();
		String deliverRequest=n.getDeliverRequest();
		int status = n.getStatus();
		String operator=n.getOperator();
		Date operateDate = n.getOperateDate();
		
		sbf.append("select * FROM neworder LEFT JOIN product using(productId) LEFT JOIN userinfo USING(clientId) WHERE 1=1 ");
		if(orderId != 0){
			sbf.append(" and neworderId=?");
		}
		if(clienitId != 0){
			sbf.append(" and clienitId=?");
		}
		if(productId != 0){
			sbf.append(" and productId=?");
		}
		if(productQuantity != 0){
			sbf.append(" and productQuantity=?");
		}
		if(receiverName != null && !"".equals(receiverName)){
			sbf.append(" and receiverName=?");
		}
		if(receiverPhone != null && !"".equals(receiverPhone)){
			sbf.append(" and receiverPhone=?");
		}
		if(receiverAddress != null && !"".equals(receiverAddress)){
			sbf.append(" and receiverAddress=?");
		}
		if(receiverPostCode != 0){
			sbf.append(" and receiverPostCode=?");
		}
		if(total != 0){
			sbf.append(" and total=?");
		}
		if(orderState != 0){
			sbf.append(" and orderState=?");
		}
		if(employeeId != 0){
			sbf.append(" and employeeId=?");
		}
		if(whetherInvoice != 0){
			sbf.append(" and whetherInvoice=?");
		}
		if (requireDate != null) {
			sbf.append(" and requireDate = ? ");
		}
		if (finishDate != null) {
			sbf.append(" and FinishDate = ? ");
		}
		if (generateDate != null) {
			sbf.append(" and GenerateDate = ? ");
		}
		if(newOrderRemark != null && !"".equals(newOrderRemark)){
			sbf.append(" and newOrderRemark=?");
		}
		if(deliverRequest != null && !"".equals(deliverRequest)){
			sbf.append(" and deliverRequest=?");
		}
		if(status != 0){
			sbf.append(" and orderId=?");
		}
		if(operator != null && !"".equals(operator)){
			sbf.append(" and operator=?");
		}
		if (n.getOperateDate() != null) {
			sbf.append(" and operateDate = ? ");
		}
		sbf.append(";");
		
		List<NewOrder> newOrders = new ArrayList<NewOrder>();
		try {
			PreparedStatement ps = conn.prepareStatement(sbf.toString());
			int index=1;
			if(orderId != 0){
				ps.setInt(index, orderId);
				index++;
			}
			if(clienitId != 0){
				ps.setInt(index, clienitId);
				index++;
			}
			if(productId != 0){
				ps.setInt(index, productId);
				index++;
			}
			if(productQuantity != 0){
				ps.setInt(index, productQuantity);
				index++;
			}
			if(receiverName != null && !"".equals(receiverName)){
				ps.setString(index, receiverName);
				index++;
			}
			if(receiverPhone != null && !"".equals(receiverPhone)){
				ps.setString(index, receiverPhone);
				index++;
			}
			if(receiverAddress != null && !"".equals(receiverAddress)){
				ps.setString(index, receiverAddress);
				index++;
			}
			if(receiverPostCode != 0){
				ps.setInt(index, receiverPostCode);
				index++;
			}
			if(total != 0){
				ps.setFloat(index, total);
				index++;
			}
			if(orderState != 0){
				ps.setInt(index, orderState);
				index++;
			}
			if(employeeId != 0){
				ps.setInt(index, employeeId);
				index++;
			}
			if(whetherInvoice != 0){
				ps.setInt(index, whetherInvoice);
				index++;
			}
			if (requireDate != null) {
				ps.setDate(index, new java.sql.Date(requireDate.getTime()));
				index++;
			}
			if (finishDate != null) {
				ps.setDate(index, new java.sql.Date(finishDate.getTime()));
				index++;
			}
			if (generateDate != null) {
				ps.setDate(index, new java.sql.Date(generateDate.getTime()));
				index++;
			}
			if(newOrderRemark != null && !"".equals(newOrderRemark)){
				ps.setString(index, newOrderRemark);
				index++;
			}
			if(deliverRequest != null && !"".equals(deliverRequest)){
				ps.setString(index, deliverRequest);
				index++;
			}
			if(status != 0){
				ps.setInt(index, status);
				index++;
			}
			if(operator != null && !"".equals(operator)){
				ps.setString(index, operator);
				index++;
			}
			if (n.getOperateDate() != null) {
				ps.setDate(index, new java.sql.Date(operateDate.getTime()));
				index++;
			}
			//System.out.println(ps);
			//执行
			//统计获得数据数
			ResultSet rs = ps.executeQuery();
			System.out.println(ps);
			while (rs.next()) {
				NewOrder newOrder = new NewOrder();
				newOrder.setOrderId(rs.getInt("neworderId"));
				
				Client c1 = new Client();
				c1.setClientContactAddress(rs.getString("ClientContactAddress"));
				c1.setClientEmail(rs.getString("ClientEmail"));
				c1.setClientIc(rs.getString("ClientIc"));
				c1.setClientId(rs.getInt("ClientId"));
				c1.setClientMobilePhone(rs.getString("clientmobilephone"));
				c1.setClientName(rs.getString("ClientName"));
				c1.setClientPhoneNumber(rs.getString("ClientPhoneNumber"));
				c1.setClientPostCode(rs.getInt("ClientPostCode"));
				newOrder.setClient(c1);
				
				Product p = new Product();
				p.setProductId(rs.getInt("productId"));
				p.setProductName(rs.getString("productName"));
				p.setFirstClassId(rs.getInt("firstClassId"));
				p.setSecondClassId(rs.getInt("secondclassid"));
				p.setProductPrice(rs.getFloat("productprice"));
				p.setProductDiscount(rs.getFloat("productDiscount"));
				newOrder.setProduct(p);
				
				
				newOrder.setProductQuantity(rs.getInt("productQuantity"));
				newOrder.setReceiverName(rs.getString("receiverName"));
				newOrder.setReceiverPhone(rs.getString("receiverPhone"));
				newOrder.setReceiverAddress(rs.getString("receiverAddress"));
				newOrder.setReceiverPostCode(rs.getInt("receiverpostcode"));
				newOrder.setGenerateDate(rs.getDate("generateDate"));
				newOrder.setRequireDate(rs.getDate("requireDate"));
				newOrder.setWhetherInvoice(rs.getInt("whetherInvoice"));
				newOrder.setDeliverRequest(rs.getString("deliverRequest"));
				newOrder.setNewOrderRemark(rs.getString("newOrderRemark"));
				newOrder.setTotal(rs.getFloat("total"));
				newOrder.setEmployeeId(rs.getInt("employeeId"));
				newOrder.setOrderState(rs.getInt("orderstate"));
				newOrder.setStatus(rs.getInt("status"));
				newOrder.setOperator(rs.getString("operator"));
				newOrder.setOperateDate(rs.getDate("operateDate"));
				newOrder.setFinishDate(rs.getDate("finishDate"));
				newOrders.add(newOrder);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return newOrders;
	}

	@Override
	public List<NewOrder> selectNewOrderByPage(NewOrder n, int pageSize, int pageNum) {
		StringBuffer sbf = new StringBuffer("");
		int orderId = n.getOrderId();
		int clienitId = n.getClient().getClientId();
		int productId = n.getProduct().getProductId();
		int productQuantity = n.getProductQuantity();
		String receiverName = n.getReceiverName();
		String receiverPhone= n.getReceiverPhone();
		String receiverAddress=n.getReceiverAddress();
		int receiverPostCode=n.getReceiverPostCode();
		float total=n.getTotal();
		int orderState=n.getOrderState();
		int employeeId=n.getEmployeeId();
		int whetherInvoice=n.getWhetherInvoice();
		Date requireDate=n.getRequireDate();
		Date finishDate=n.getFinishDate();
		Date generateDate=n.getGenerateDate();
		String newOrderRemark=n.getNewOrderRemark();
		String deliverRequest=n.getDeliverRequest();
		int status = n.getStatus();
		String operator=n.getOperator();
		Date operateDate = n.getOperateDate();
		
		sbf.append("select *  from  neworder LEFT JOIN product using(productId) LEFT JOIN userinfo USING(clientId) where 1=1  ");
		if(orderId != 0){
			sbf.append(" and neworderId=?");
		}
		if(clienitId != 0){
			sbf.append(" and clienitId=?");
		}
		if(productId != 0){
			sbf.append(" and productId=?");
		}
		if(productQuantity != 0){
			sbf.append(" and productQuantity=?");
		}
		if(receiverName != null && !"".equals(receiverName)){
			sbf.append(" and receiverName=?");
		}
		if(receiverPhone != null && !"".equals(receiverPhone)){
			sbf.append(" and receiverPhone=?");
		}
		if(receiverAddress != null && !"".equals(receiverAddress)){
			sbf.append(" and receiverAddress=?");
		}
		if(receiverPostCode != 0){
			sbf.append(" and receiverPostCode=?");
		}
		if(total != 0){
			sbf.append(" and total=?");
		}
		if(orderState != 0){
			sbf.append(" and orderState=?");
		}
		if(employeeId != 0){
			sbf.append(" and employeeId=?");
		}
		if(whetherInvoice != 0){
			sbf.append(" and whetherInvoice=?");
		}
		if (requireDate != null) {
			sbf.append(" and requireDate = ? ");
		}
		if (finishDate != null) {
			sbf.append(" and FinishDate = ? ");
		}
		if (generateDate != null) {
			sbf.append(" and GenerateDate = ? ");
		}
		if(newOrderRemark != null && !"".equals(newOrderRemark)){
			sbf.append(" and newOrderRemark=?");
		}
		if(deliverRequest != null && !"".equals(deliverRequest)){
			sbf.append(" and deliverRequest=?");
		}
		if(status != 0){
			sbf.append(" and orderId=?");
		}
		if(operator != null && !"".equals(operator)){
			sbf.append(" and operator=?");
		}
		if (n.getOperateDate() != null) {
			sbf.append(" and operateDate = ? ");
		}
		if (pageNum != 0 && pageSize != 0){
			sbf.append(" limit ?,? ");
		}
		sbf.append(";");
		
		List<NewOrder> newOrders = new ArrayList<NewOrder>();
		try {
			PreparedStatement ps = conn.prepareStatement(sbf.toString());
			int index=1;
			if(orderId != 0){
				ps.setInt(index, orderId);
				index++;
			}
			if(clienitId != 0){
				ps.setInt(index, clienitId);
				index++;
			}
			if(productId != 0){
				ps.setInt(index, productId);
				index++;
			}
			if(productQuantity != 0){
				ps.setInt(index, productQuantity);
				index++;
			}
			if(receiverName != null && !"".equals(receiverName)){
				ps.setString(index, receiverName);
				index++;
			}
			if(receiverPhone != null && !"".equals(receiverPhone)){
				ps.setString(index, receiverPhone);
				index++;
			}
			if(receiverAddress != null && !"".equals(receiverAddress)){
				ps.setString(index, receiverAddress);
				index++;
			}
			if(receiverPostCode != 0){
				ps.setInt(index, receiverPostCode);
				index++;
			}
			if(total != 0){
				ps.setFloat(index, total);
				index++;
			}
			if(orderState != 0){
				ps.setInt(index, orderState);
				index++;
			}
			if(employeeId != 0){
				ps.setInt(index, employeeId);
				index++;
			}
			if(whetherInvoice != 0){
				ps.setInt(index, whetherInvoice);
				index++;
			}
			if (requireDate != null) {
				ps.setDate(index, new java.sql.Date(requireDate.getTime()));
				index++;
			}
			if (finishDate != null) {
				ps.setDate(index, new java.sql.Date(finishDate.getTime()));
				index++;
			}
			if (generateDate != null) {
				ps.setDate(index, new java.sql.Date(generateDate.getTime()));
				index++;
			}
			if(newOrderRemark != null && !"".equals(newOrderRemark)){
				ps.setString(index, newOrderRemark);
				index++;
			}
			if(deliverRequest != null && !"".equals(deliverRequest)){
				ps.setString(index, deliverRequest);
				index++;
			}
			if(status != 0){
				ps.setInt(index, status);
				index++;
			}
			if(operator != null && !"".equals(operator)){
				ps.setString(index, operator);
				index++;
			}
			if (n.getOperateDate() != null) {
				ps.setDate(index, new java.sql.Date(operateDate.getTime()));
				index++;
			}
			if (pageNum != 0 && pageSize != 0){
				ps.setInt(index, pageNum-1);
				index++;
				ps.setInt(index, pageSize);
				index++;
			}
			//System.out.println(ps);
			//执行
			//统计获得数据数
			System.out.println(ps);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				NewOrder newOrder = new NewOrder();
				newOrder.setOrderId(rs.getInt("neworderId"));
				
				Client c1 = new Client();
				c1.setClientContactAddress(rs.getString("ClientContactAddress"));
				c1.setClientEmail(rs.getString("ClientEmail"));
				c1.setClientIc(rs.getString("ClientIc"));
				c1.setClientId(rs.getInt("ClientId"));
				c1.setClientMobilePhone(rs.getString("clientmobilephone"));
				c1.setClientName(rs.getString("ClientName"));
				c1.setClientPhoneNumber(rs.getString("ClientPhoneNumber"));
				c1.setClientPostCode(rs.getInt("ClientPostCode"));
				newOrder.setClient(c1);
				
				Product p = new Product();
				p.setProductId(rs.getInt("productId"));
				p.setProductName(rs.getString("productName"));
				p.setFirstClassId(rs.getInt("firstClassId"));
				p.setSecondClassId(rs.getInt("secondclassid"));
				p.setProductPrice(rs.getFloat("productprice"));
				p.setProductDiscount(rs.getFloat("productDiscount"));
				newOrder.setProduct(p);
				
				newOrder.setProductQuantity(rs.getInt("productQuantity"));
				newOrder.setReceiverName(rs.getString("receiverName"));
				newOrder.setReceiverPhone(rs.getString("receiverPhone"));
				newOrder.setReceiverAddress(rs.getString("receiverAddress"));
				newOrder.setReceiverPostCode(rs.getInt("receiverpostcode"));
				newOrder.setGenerateDate(rs.getDate("generateDate"));
				newOrder.setRequireDate(rs.getDate("requireDate"));
				newOrder.setWhetherInvoice(rs.getInt("whetherInvoice"));
				newOrder.setDeliverRequest(rs.getString("deliverRequest"));
				newOrder.setNewOrderRemark(rs.getString("newOrderRemark"));
				newOrder.setTotal(rs.getFloat("total"));
				newOrder.setEmployeeId(rs.getInt("employeeId"));
				newOrder.setOrderState(rs.getInt("orderstate"));
				newOrder.setStatus(rs.getInt("status"));
				newOrder.setOperator(rs.getString("operator"));
				newOrder.setOperateDate(rs.getDate("operateDate"));
				newOrder.setFinishDate(rs.getDate("finishDate"));
				newOrders.add(newOrder);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return newOrders;
	}

	@Override
	public int selectNewOrderPageCount(NewOrder newOrder, int pageSize, int pageNum) {
		return 0;
	}
	@Override
	public void setNewOrderState(int newOrderId, int orderState, String operator, Date operatorDate) {
		try {
			PreparedStatement ps = conn.prepareStatement("update neworder set orderstate =?,operator=?,operatorDate=? where neworderid = ?");
			ps.setInt(1, orderState);
			ps.setString(2, operator);
			ps.setDate(3, new java.sql.Date(operatorDate.getTime()));
			ps.setInt(4, newOrderId);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
