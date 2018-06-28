package com.neuedu.model.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.neuedu.model.po.ProInfo;
import com.neuedu.utils.DBUtil;

public class FeedbackDAOImp implements FeedbackDAO {
	
	Connection conn = null;
	
	public FeedbackDAOImp(Connection conn) {
		this.conn = conn;
	}

	//回执录入,创建feedback中的新条目，并且更改订单和任务单的信息
	@Override
	public void feedbackRecord(int workId, int customerFeedback, String remark) {

		System.out.println("DAOImp,feedbackRecording,  workStatus:"+workId+"  customerFeedback:"+customerFeedback+"  remark:"+remark);
		if(workId!=0 && customerFeedback!=0){
			PreparedStatement ps = null;
			PreparedStatement ps2 = null;
			PreparedStatement ps3 = null;
			PreparedStatement ps4 = null;
			
			try {
				ps = conn.prepareStatement(" insert into feedback (workId, customerFeedback, remark, recordStatus ) "
						+ " values (?, ?, ?, 1)");
				ps2 = conn.prepareStatement(" update workorder as a, neworder as b set a.workStatus=?, b.orderState=? "
						+ " where a.orderId=b.newOrderId and b.workType=1");
				ps3 = conn.prepareStatement(" update workorder as a, returnorder as b set a.workStatus=?, b.orderState=? "
						+ " where a.orderId=b.returnOrder and a.workType=2 ");
				ps4 = conn.prepareStatement(" select workType from workorder where workId=? ");
				ps.setInt(1, workId);
				ps.setInt(2, customerFeedback);
				ps.setString(3, remark);
				ps.executeUpdate();
				
				ps4.setInt(1, workId);
				ResultSet rs = ps4.executeQuery();
				while(rs.next()){
					int workType = rs.getInt("workType");
					if(workType==1){
						ps2.setInt(1, 5);
						ps2.setInt(2, 11);
						ps2.executeUpdate();
						break;
					}
					if(workType==2){
						ps3.setInt(1, 5);
						ps3.setInt(2, 11);
						ps3.executeUpdate();
						break;
					}
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBUtil.closePS(ps);
			}
		}
	}
	//分页查询，统计商品信息
	@Override
	public List<ProInfo> selectPageProduct(java.util.Date beginDate, java.util.Date endDate, String productName,
			int pageNum) {
		//创建一个list用来存放返回结果
		List<ProInfo> list = new ArrayList<ProInfo>();
		int pageSize = 5;//固定的页码大小
		//构建sql语句
		StringBuffer sbf = new StringBuffer("");//用“”开头
		sbf.append(" select productId from product where 1=1 ");
		if(productName!=null && !productName.equals("")){
			sbf.append(" and productName=? ");
		}else{
			sbf.append(" and productName like '%' ");
		}
		StringBuffer sbf2 = new StringBuffer("");
		sbf2.append(" select productId from product where 1=1 ");
		if(productName!=null && !productName.equals("")){
			sbf2.append("  and productName=? ");
		}else{
			sbf2.append(" and productName like '%' ");
		}
		try {
			PreparedStatement ps = conn.prepareStatement(" select * from ( " 
					+ " select @rownum:=@rownum+1 as rownum, a.*, sum(b.productQuantity) as deliveryNum "
					+ " from (select @rownum:=0) t, product a left join neworder b "
					+ " on b.productId in ( " +sbf.toString()+ " ) and b.finishDate between ? and ? "
							+ " where a.productId = b.productId ) as k "
									   + "  where k.rownum <= "+pageSize*pageNum+" and k.rownum > "+pageSize*(pageNum-1));
			int index = 1;
			if(productName!=null && !productName.equals("")){
				ps.setString(index, productName);
				index++;
			}
			if(beginDate!=null){
				Date newBeginDate = new Date(beginDate.getTime());
				ps.setDate(index, newBeginDate);
				index++;
			}
			if(endDate!=null){
				Date newEndDate = new Date(endDate.getTime());
				ps.setDate(index, newEndDate);
				index++;
			}
			//执行
			ResultSet rs = ps.executeQuery();//执行查询，返回结果集
			while(rs.next()){
				ProInfo pi = new ProInfo();
				pi.setProductName(rs.getString("productName"));
				System.out.println("pi.productName:"+pi.getProductName());
				pi.setCollectionStatus("已收款");
				System.out.println("pi.collectionStatus:"+pi.getCollectionStatus());
				pi.setDeliveryNum(rs.getInt("deliveryNum"));
				System.out.println("pi.deliveryNum:"+pi.getDeliveryNum());
				pi.setTotalCollection(rs.getFloat("productPrice")*rs.getInt("deliveryNum"));
				System.out.println("pi.totalCollection:"+pi.getTotalCollection());
				list.add(pi);
			}
			PreparedStatement ps2 = conn.prepareStatement(" select * from ( "
					+ " select @rownum:=@rownum+1 as rownum, a.*, sum(b.returnQuantity) as returnNum "
					+ " from (select @rownum:=0) t, product a left join returnorder b " 
					+ " on a.productId " 
					+ " in ( " +sbf2.toString()+ " ) and b.operateDate between ? and ? "
					+ " ) as k "
					+ " where k.rownum <= "+pageSize*pageNum+" and k.rownum > "+pageSize*(pageNum-1));
			int index2 = 1;
			if(productName!=null && !productName.equals("")){
				ps2.setString(index2, productName);
				index2++;
			}
			if(beginDate!=null){
				Date newBeginDate = new Date(beginDate.getTime());
				ps2.setDate(index2, newBeginDate);
				index2++;
			}
			if(endDate!=null){
				Date newEndDate = new Date(endDate.getTime());
				ps2.setDate(index2, newEndDate);
				index2++;
			}
			//执行
			ResultSet rs2 = ps.executeQuery();//执行查询，返回结果集
			
			while(rs2.next()){
				System.out.println("啦啦啦");
				ProInfo temp = null;
				System.out.println("size:"+list.size());
				for(int i = 0; i < list.size(); i++){
					System.out.println("rs2.pN:"+rs2.getString("productName"));
					System.out.println("list.pN"+list.get(i).getProductName());
					if(list.get(i).getProductName().equals(rs2.getString("productName"))){
						list.get(i).setTotalRefund(rs2.getInt("returnNum")*rs2.getFloat("productPrice"));
						System.out.println("pi.totalRefund:"+list.get(i).getTotalRefund());
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	//统计商品信息总页数
	@Override
	public int selectPageProduct(java.util.Date beginDate, java.util.Date endDate, String productName) {
		//创建一个list用来存放返回结果
		List<ProInfo> list = new ArrayList<ProInfo>();
		int pageSize = 5;//固定的页码大小
		//构建sql语句
		StringBuffer sbf = new StringBuffer("");//用“”开头
		sbf.append(" select productId from product where 1=1 ");
		if(productName!=null && !productName.equals("")){
			sbf.append(" and productName=? ");
		}else{
			sbf.append(" and productName like '%' ");
		}
		try {
			PreparedStatement ps = conn.prepareStatement(" select @rownum:=@rownum+1 as rownum, a.*, sum(b.productQuantity) as deliveryNum "
					+ " from (select @rownum:=0) t, product a left join neworder b "
					+ " on b.productId in ( " +sbf.toString()+ " ) and b.finishDate BETWEEN ? and ? "
							+ " where a.productId = b.productId ");
			int index = 1;
			if(productName!=null && !productName.equals("")){
				ps.setString(index, productName);
				index++;
			}
			if(beginDate!=null){
				Date newBeginDate = new Date(beginDate.getTime());
				ps.setDate(index, newBeginDate);
				index++;
			}
			if(endDate!=null){
				Date newEndDate = new Date(endDate.getTime());
				ps.setDate(index, newEndDate);
				index++;
			}
			//执行
			ResultSet rs = ps.executeQuery();//执行查询，返回结果集
			while(rs.next()){
				ProInfo pi = new ProInfo();
				pi.setProductName(rs.getString("productName"));
				pi.setCollectionStatus("已收款");
				pi.setDeliveryNum(rs.getInt("deliveryNum"));
				pi.setTotalCollection(rs.getFloat("productPrice")*rs.getInt("deliveryNum"));
				list.add(pi);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		int pagecount = list.size()%pageSize==0 ? list.size()/pageSize : list.size()/pageSize+1;
		
		return pagecount;
	}
	
	
}
