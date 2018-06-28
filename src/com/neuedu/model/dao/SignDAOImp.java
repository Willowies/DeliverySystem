package com.neuedu.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.neuedu.model.po.Sign;
import com.neuedu.utils.DBUtil;
import com.neuedu.model.po.WorkOrder;
import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;
import java.text.SimpleDateFormat;

public class SignDAOImp implements SignDAO {
	
	Connection conn;
	
	public SignDAOImp(Connection conn){
		this.conn = conn;
	}

	//��ѯδ����ǩ�յ�
	public List<Sign> selectSign(String substation, Date date, String product) {
		List<Sign> list = new ArrayList<Sign>();
		//sql���
		//��ѯδ������ͨ�ͻ�����
		StringBuffer sbf = new StringBuffer("");
		sbf.append("select a.*,b.orderId,b.workType,d.productQuantity,d.total,e.productName,e.productPrice "
				+"from sign a,workorder b,warehouse c,neworder d,product e "
				+"where a.workId = b.workId and "
				+ "b.warehouseId = c.warehouseId and "
				+"b.orderId = d.neworderId and "
				+"d.productId = e.productId and "
				+"b.workType = 1 and "
				+"a.clearingStatus = 0 ");
		if(date!=null&&!"".equals(date)){
			sbf.append("and a.deliveryDate = ?");
		}
		if(substation!=null&&!"".equals(substation)){
			sbf.append("and c.warehouseName =? ");
		}
		if(product!=null&&!"".equals(product)){
			sbf.append("and e.productName =? ");
		}
		//System.out.println("sql:"+sbf);
		
		try {
			PreparedStatement ps = conn.prepareStatement(sbf.toString());
			int index = 1;
			if(date!=null&&!"".equals(date)){
				ps.setDate(index,new java.sql.Date(date.getTime()));
				index++;	
			}
			if(substation!=null&&!"".equals(substation)){
				ps.setString(index, substation);
				index++;
			}
			if(product!=null&&!"".equals(product)){
				ps.setString(index, product);
			}
			
			//ִ��
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Sign s = new Sign();
				s.setSignId(rs.getInt("signId"));
				s.setWorkId(rs.getInt("workId"));
				s.setDeliveryDate(rs.getDate("deliveryDate"));
				s.setReceiptNeedOrNot(rs.getInt("receiptNeedOrNot"));
				s.setClearingStatus(rs.getInt("clearingStatus"));
				s.setRemark(rs.getString("remark"));
				s.setCustomerFeedback(rs.getInt("customerFeedback"));
				s.setCustomerSignature(rs.getString("customerSignature"));
				s.setOrderId(rs.getInt("orderId"));
				s.setWorkType(rs.getInt("workType"));
				s.setProductQuantity(rs.getInt("productQuantity"));
				s.setTotal(rs.getFloat("total"));
				s.setProductName(rs.getString("productName"));
				s.setProductPrice(rs.getFloat("productPrice"));
				list.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//��ѯ�˻���
		sbf = new StringBuffer("");
		sbf.append("select a.*,b.orderId,b.workType,e.returnQuantity,e.returnTotal,f.productName,f.productPrice "
				+"from sign a,workorder b,warehouse c,neworder d,returnorder e,product f "
				+"where a.workId = b.workId and "
				+"b.orderId = e.returnOrderId and "
				+ "b.warehouseId = c.warehouseId and "
				+"e.neworderId = d.neworderId and "
				+"d.productId = f.productId and "
				+"b.workType = 2 and "
				+"a.clearingStatus = 0 ");
		if(date!=null&&!"".equals(date)){
			sbf.append("and a.deliveryDate = ?");
		}
		if(substation!=null&&!"".equals(substation)){
			sbf.append("and c.warehouseName =? ");
		}
		if(product!=null&&!"".equals(product)){
			sbf.append("and f.productName =? ");
		}
		//System.out.println("sql:"+sbf);
		
		try {
			PreparedStatement ps = conn.prepareStatement(sbf.toString());
			int index = 1;
			if(date!=null&&!"".equals(date)){
				ps.setDate(index,new java.sql.Date(date.getTime()));
				index++;	
			}
			if(substation!=null&&!"".equals(substation)){
				ps.setString(index, substation);
				index++;
			}
			if(product!=null&&!"".equals(product)){
				ps.setString(index, product);
			}
			
			//ִ��
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Sign s = new Sign();
				s.setSignId(rs.getInt("signId"));
				s.setWorkId(rs.getInt("workId"));
				s.setDeliveryDate(rs.getDate("deliveryDate"));
				s.setReceiptNeedOrNot(rs.getInt("receiptNeedOrNot"));
				s.setClearingStatus(rs.getInt("clearingStatus"));
				s.setRemark(rs.getString("remark"));
				s.setCustomerFeedback(rs.getInt("customerFeedback"));
				s.setCustomerSignature(rs.getString("customerSignature"));
				s.setOrderId(rs.getInt("orderId"));
				s.setWorkType(rs.getInt("workType"));
				s.setProductQuantity(rs.getInt("returnQuantity"));
				s.setTotal(rs.getFloat("returnTotal"));
				s.setProductName(rs.getString("productName"));
				s.setProductPrice(rs.getFloat("productPrice"));
				list.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	//����ǩ�յ�
	public void clearSubstation(int[] ids,String employeeName){
		String id = Arrays.toString(ids).replace('[','(').replace(']', ')');
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(" update sign set clearingStatus = 1,operator=?,operateDate=? where signId in "+id);
			ps.setString(1,employeeName);
			ps.setDate(2,new java.sql.Date(System.currentTimeMillis()));
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
		
	}

	//��ѯ�ѽ��㶩��
	public List<Sign> selectClearedSub(String substation, Date date, String product) {
		List<Sign> list = new ArrayList<Sign>();
		//sql���
		//��ѯδ������ͨ�ͻ�����
		StringBuffer sbf = new StringBuffer("");
		sbf.append("select a.*,b.orderId,b.workType,d.productQuantity,d.total,e.productName,e.productPrice "
				+"from sign a,workorder b,warehouse c,neworder d,product e "
				+"where a.workId = b.workId and "
				+ "b.warehouseId = c.warehouseId and "
				+"b.orderId = d.neworderId and "
				+"d.productId = e.productId and "
				+"b.workType = 1 and "
				+"a.clearingStatus = 1 ");
		if(date!=null&&!"".equals(date)){
			sbf.append("and a.deliveryDate = ?");
		}
		if(substation!=null&&!"".equals(substation)){
			sbf.append("and c.warehouseName =? ");
		}
		if(product!=null&&!"".equals(product)){
			sbf.append("and e.productName =? ");
		}
		//System.out.println("sql:"+sbf);
		
		try {
			PreparedStatement ps = conn.prepareStatement(sbf.toString());
			int index = 1;
			if(date!=null&&!"".equals(date)){
				ps.setDate(index,new java.sql.Date(date.getTime()));
				index++;	
			}
			if(substation!=null&&!"".equals(substation)){
				ps.setString(index, substation);
				index++;
			}
			if(product!=null&&!"".equals(product)){
				ps.setString(index, product);
			}
			
			//ִ��
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Sign s = new Sign();
				s.setSignId(rs.getInt("signId"));
				s.setWorkId(rs.getInt("workId"));
				s.setDeliveryDate(rs.getDate("deliveryDate"));
				s.setReceiptNeedOrNot(rs.getInt("receiptNeedOrNot"));
				s.setClearingStatus(rs.getInt("clearingStatus"));
				s.setRemark(rs.getString("remark"));
				s.setCustomerFeedback(rs.getInt("customerFeedback"));
				s.setCustomerSignature(rs.getString("customerSignature"));
				s.setOrderId(rs.getInt("orderId"));
				s.setWorkType(rs.getInt("workType"));
				s.setProductQuantity(rs.getInt("productQuantity"));
				s.setTotal(rs.getFloat("total"));
				s.setProductName(rs.getString("productName"));
				s.setProductPrice(rs.getFloat("productPrice"));
				list.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//��ѯ�˻���
		sbf = new StringBuffer("");
		sbf.append("select a.*,b.orderId,b.workType,e.returnQuantity,e.returnTotal,f.productName,f.productPrice "
				+"from sign a,workorder b,warehouse c,neworder d,returnorder e,product f "
				+"where a.workId = b.workId and "
				+"b.orderId = e.returnOrderId and "
				+ "b.warehouseId = c.warehouseId and "
				+"e.neworderId = d.neworderId and "
				+"d.productId = f.productId and "
				+"b.workType = 2 and "
				+"a.clearingStatus = 1 ");
		if(date!=null&&!"".equals(date)){
			sbf.append("and a.deliveryDate = ?");
		}
		if(substation!=null&&!"".equals(substation)){
			sbf.append("and c.warehouseName =? ");
		}
		if(product!=null&&!"".equals(product)){
			sbf.append("and f.productName =? ");
		}
		//System.out.println("sql:"+sbf);
		
		try {
			PreparedStatement ps = conn.prepareStatement(sbf.toString());
			int index = 1;
			if(date!=null&&!"".equals(date)){
				ps.setDate(index,new java.sql.Date(date.getTime()));
				index++;	
			}
			if(substation!=null&&!"".equals(substation)){
				ps.setString(index, substation);
				index++;
			}
			if(product!=null&&!"".equals(product)){
				ps.setString(index, product);
			}
			
			//ִ��
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Sign s = new Sign();
				s.setSignId(rs.getInt("signId"));
				s.setWorkId(rs.getInt("workId"));
				s.setDeliveryDate(rs.getDate("deliveryDate"));
				s.setReceiptNeedOrNot(rs.getInt("receiptNeedOrNot"));
				s.setClearingStatus(rs.getInt("clearingStatus"));
				s.setRemark(rs.getString("remark"));
				s.setCustomerFeedback(rs.getInt("customerFeedback"));
				s.setCustomerSignature(rs.getString("customerSignature"));
				s.setOrderId(rs.getInt("orderId"));
				s.setWorkType(rs.getInt("workType"));
				s.setProductQuantity(rs.getInt("returnQuantity"));
				s.setTotal(rs.getFloat("returnTotal"));
				s.setProductName(rs.getString("productName"));
				s.setProductPrice(rs.getFloat("productPrice"));
				list.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public Sign getSign(WorkOrder workOrder) {
		Sign signResult = null;
		List<Sign> list = new ArrayList<Sign>();
		int deliveryStaffId = workOrder.getDeliveryStaffId();
		java.util.Date requireDate = workOrder.getRequireDate();
		int workStatus = workOrder.getWorkStatus();
		int workType = workOrder.getWorkType();
		
		//构建sql语句
		StringBuffer sbf = new StringBuffer("");//用“”开头
		sbf.append(" select * from workorder where 1=1 ");
		if(deliveryStaffId!=0){
			sbf.append(" and deliveryStaffId=? ");
		}
		if(requireDate!=null){
			sbf.append(" and requireDate=? ");
		}
		if(workStatus!=0){
			sbf.append(" and workStatus=? ");
		}
		if(workType!=0){
			sbf.append(" and workType=? ");
		}
		//构建sql语句
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		PreparedStatement ps3 = null;
		try {
			ps = conn.prepareStatement(" select a.*, "
					+ " b.newOrderId, b.total, b.receiverPostcode, b.receiverAddress, b.deliverRequest, b.productQuantity, b.whetherInvoice, "
					+ " p.productPrice, p.productName, "
					+ " u.clientName, u.clientMobilephone, w.warehouseAddress "
					+ " from ( " + sbf.toString()+ " ) as a, neworder as b, product as p, userinfo as u, warehouse as w "
					+ " where a.orderId=b.newOrderId and b.productId=p.productId and b.clientId=u.clientId and a.warehouseId=w.warehouseId "
					);
			ps2 = conn.prepareStatement(" insert into sign (workId, deliveryDate, receiptNeedOrNot, clearingStatus, "
					+ " customerFeedback, customerSignature, status, operator, operateDate) values "
					+ " (?,?,?,?,?,?,?,?,?) ");
			ps3 = conn.prepareStatement(" select signId from sign where 1=1 ");
			
			int index = 1;
			if(deliveryStaffId != 0){
				ps.setInt(index, deliveryStaffId);
				index++;
			}
			if(requireDate != null){
				java.sql.Date newRequireDate = new java.sql.Date(requireDate.getTime());
				ps.setDate(index, newRequireDate);
				index++;
			}
			if(workStatus != 0){
				ps.setInt(index, workStatus);
				index++;
			}
			if(workType != 0){
				ps.setInt(index, workType);
			}
			
			//执行
			ResultSet rs = ps.executeQuery();//执行查询，返回结果集
			int num_q = 1;
			while(rs.next()){//应该只有一次
				Sign sign = new Sign();
				//签收单
				sign.setWorkId(rs.getInt("workId"));
				
				java.util.Date date = new java.util.Date();
				SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
				System.out.println(dateFormat.format(date));
				sign.setDeliveryDate(date);//实时获取
				
				sign.setReceiptNeedOrNot(rs.getInt("whetherInvoice"));
				sign.setClearingStatus(0);//默认clearingStatus 0
				sign.setOrderId(rs.getInt("orderId"));
				sign.setTotal(rs.getFloat("total"));
				sign.setWorkType(rs.getInt("workType"));
				sign.setProductName(rs.getString("productName"));
				sign.setProductPrice(rs.getFloat("productPrice"));
				sign.setProductQuantity(rs.getInt("productQuantity"));
				sign.setCustomerName(rs.getString("clientName"));
				sign.setCustomerPhone(rs.getString("clientMobilephone"));
				sign.setPostCode(rs.getInt("receiverPostcode"));
				sign.setDeliveryAddress(rs.getString("receiverAddress"));
				sign.setDeliveryRequirement(rs.getString("deliverRequest"));
				sign.setDeliverySubstation(rs.getInt("warehouseId"));
				sign.setSubstationAddress(rs.getString("warehouseAddress"));
				
				//增加sign表的条目
				ps2.setInt(1, sign.getWorkId());
				ps2.setDate(2, new java.sql.Date(sign.getDeliveryDate().getTime()));
				ps2.setInt(3, sign.getReceiptNeedOrNot());
				ps2.setInt(4, sign.getClearingStatus());
				ps2.setInt(5, 0);
				ps2.setString(6, "");
				ps2.setInt(7, 1);
				ps2.setString(8, "");
				ps2.setDate(9, new java.sql.Date(date.getTime()));
				ps2.executeUpdate();//在sign中添加一条新条目
				
				ResultSet rsResultSet = ps3.executeQuery();
				int num = 1;
				while(rsResultSet.next()){//应该只有一次
					sign.setSignId(rsResultSet.getInt("signId"));
					list.add(sign);
					System.out.println("获取signId，num = "+num);
					num++;
				}
				System.out.println("获取签收单要显示的所有信息，num_q = "+num_q);
				num_q++;
			}
			if(num_q==2){
				signResult = list.get(0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closePS(ps);
		}
		
		return signResult;
	}

	
}
