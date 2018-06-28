package com.neuedu.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.neuedu.model.po.Invoice;
import com.neuedu.model.po.NewOrder;
import com.neuedu.model.po.Product;
import com.neuedu.model.po.ReturnOrder;
import com.neuedu.model.po.WorkOrder;
import com.neuedu.utils.DBUtil;

public class InvoiceDAOImp implements InvoiceDAO {
	
	Connection conn;
	
	public InvoiceDAOImp(Connection conn){
		this.conn = conn;
	}
	
	//发票查询
	public Invoice selectInvoice(String invoiceId) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		Invoice invoice = null;
		int warehouseId = -1;
		int clientId = -1;
		
		//从发票表中获取数据
		try {
			ps = conn.prepareStatement("select * from invoice where invoiceId="+invoiceId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				invoice = new Invoice();
				invoice.setInvoiceId(Integer.parseInt(invoiceId));
				invoice.setRegisterDate(rs.getDate("registerDate"));
				invoice.setReceiveDate(rs.getDate("receiveDate"));
				invoice.setState(rs.getInt("state"));
				invoice.setVoidDate(rs.getDate("voidDate"));
				invoice.setWorkId(rs.getInt("workId"));
				invoice.setStatus(rs.getInt("status"));
				invoice.setOperator(rs.getString("operator"));
				invoice.setOperateDate(rs.getDate("operateDate"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//从其他表中获取数据
		if(invoice!=null){
			try {
				//取得发票订单号
				ps = conn.prepareStatement(" select orderId,warehouseId from workorder where workId="+invoice.getWorkId());
				ResultSet rs = ps.executeQuery();
				if(rs.next()){
					invoice.setOrderId(rs.getInt("orderId"));
					warehouseId = rs.getInt("warehouseId");
				}
				//取得发票分站名称
				ps = conn.prepareStatement("select warehouseName from warehouse where warehouseId ="+warehouseId);
				rs = ps.executeQuery();
				if(rs.next()){
					invoice.setSubstationName(rs.getString("warehouseName"));
				}
				//取得领用人和发票金额
				ps = conn.prepareStatement("select clientId,total from neworder where newOrderId ="+invoice.getOrderId());
				rs = ps.executeQuery();
				if(rs.next()){
					clientId = rs.getInt("clientId");
					invoice.setInvoiceAmount(rs.getFloat("total"));
				}
				ps = conn.prepareStatement("select clientName from userinfo where clientId ="+clientId);
				rs = ps.executeQuery();
				if(rs.next()){
					invoice.setReceivedPerson(rs.getString("clientName"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return invoice;
	}

	//发票被分站领用
	public void getSubstationInvoice(int invoiceId,String employeeName) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		try {
			//将发票状态设置为被分站领用
			ps = conn.prepareStatement("update invoice set state=2,operator=?,operateDate=? where invoiceId="+invoiceId);
			ps.setString(1,employeeName);
			ps.setDate(2,new Date(System.currentTimeMillis()));
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
	}
	
	//查询退货订单信息
			public ReturnOrder selectReturnOrder(int orderId) {
				// TODO Auto-generated method stub
				PreparedStatement ps = null;
				ReturnOrder returnorder = null;
				int productId = -1;
				try {
					//查询订单
					ps = conn.prepareStatement("select * from returnorder where returnOrderId="+orderId);
					ResultSet rs = ps.executeQuery();
					if(rs.next()){
						returnorder = new ReturnOrder();
						returnorder.setReturnOrderId(orderId);
						returnorder.setReturnQuantity(rs.getInt("returnQuantity"));
						returnorder.setReturnTotal(rs.getFloat("returnTotal"));
						productId = rs.getInt("productId");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					DBUtil.closePS(ps);
				}
				//从其他表中查询数据。
				//查询订单对应的商品
				if(returnorder!=null){
					try {
						ps = conn.prepareStatement("select * from product where productId="+productId);
						ResultSet rs = ps.executeQuery();
						if(rs.next()){
							returnorder.setProductName(rs.getString("productName"));
							returnorder.setProductUnit(rs.getString("productUnit"));
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally{
						DBUtil.closePS(ps);
					}
				}
				return returnorder;
			}

	//查询订单信息
	public NewOrder selectNewOrder(int orderId) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		NewOrder neworder = null;
		int productId = -1;
		try {
			//查询订单
			ps = conn.prepareStatement("select * from neworder where newOrderId="+orderId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				neworder = new NewOrder();
				neworder.setOrderId(orderId);
				neworder.setProductQuantity(rs.getInt("productQuantity"));
				neworder.setTotal(rs.getFloat("total"));
				productId = rs.getInt("productId");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
		//从其他表中查询数据。
		//查询订单对应的商品
		if(neworder!=null){
			try {
				ps = conn.prepareStatement("select * from product where productId="+productId);
				ResultSet rs = ps.executeQuery();
				if(rs.next()){
					neworder.setProductName(rs.getString("productName"));
					neworder.setProductUnit(rs.getString("productUnit"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				DBUtil.closePS(ps);
			}
		}
		return neworder;
	}

	//注册发票信息
	public Invoice recordInvoice(Invoice invoice,String employeeName) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		//鉴定是否已经记录过
		try {
			ps = conn.prepareStatement("select * from invoice where workId = "+invoice.getWorkId());
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				//已经被记录过
				invoice.setInvoiceId(rs.getInt("invoiceId"));
				invoice.setRegisterDate(rs.getDate("registerDate"));
			}else{
				//没有被记录过
				//进行记录。
				ps = conn.prepareStatement(" insert into invoice(registerDate,receiveDate,state,voidDate,workId,status,operator,operateDate) values (?,?,?,?,?,?,?,?)");
				ps.setDate(1, new Date(System.currentTimeMillis()));
				ps.setDate(2, null);
				ps.setInt(3, 0);
				ps.setDate(4, null);
				ps.setInt(5, invoice.getWorkId());
				ps.setInt(6, 1);
				ps.setString(7, employeeName);
				ps.setDate(8, new Date(System.currentTimeMillis()));
				ps.executeUpdate();
				
				ps = conn.prepareStatement("select * from invoice where workId = "+invoice.getWorkId());
				rs = ps.executeQuery();
				if(rs.next()){
					invoice.setInvoiceId(rs.getInt("invoiceId"));
					invoice.setRegisterDate(rs.getDate("registerDate"));
				}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
		return invoice;
	}

	//通过发票号查询任务单
	public WorkOrder selectWorkOrder(String workId) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		WorkOrder workorder = null;
		try {
			ps = conn.prepareStatement("select * from workorder where workId="+workId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				workorder = new WorkOrder();
				workorder.setWorkId(Integer.parseInt(workId));
				workorder.setOrderId(rs.getInt("orderId"));
				workorder.setCreateDate(rs.getDate("createDate"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
		return workorder;
	}

	//发票注册
	public void registerInvoice(int invoiceId, String employeeName) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		try {
			//将发票状态设置为被分站领用
			ps = conn.prepareStatement("update invoice set state=1,registerDate=?,operator=?,operateDate=? where invoiceId="+invoiceId);
			ps.setDate(1, new Date(System.currentTimeMillis()));
			ps.setString(2,employeeName);
			ps.setDate(3,new Date(System.currentTimeMillis()));
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
	}

	//客户领用发票
	public void getClientInvoice(int invoiceId, String employeeName) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		try {
			//将发票状态设置为被分站领用
			ps = conn.prepareStatement("update invoice set state=3,receiveDate=?,operator=?,operateDate=? where invoiceId="+invoiceId);
			ps.setDate(1, new Date(System.currentTimeMillis()));
			ps.setString(2,employeeName);
			ps.setDate(3,new Date(System.currentTimeMillis()));
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
	}


	//通过任务单号废弃订单
	public void abandonInvoice(int workId, String employeeName) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		try {
			//将发票状态设置为被废弃
			ps = conn.prepareStatement("update invoice set status=0,voidDate=?,operator=?,operateDate=? where workId="+workId);
			ps.setDate(1, new Date(System.currentTimeMillis()));
			ps.setString(2,employeeName);
			ps.setDate(3,new Date(System.currentTimeMillis()));
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
	}


	//通过任务单号查询发票
	public Invoice selectInvoiceByWorkId(int workId) {
		PreparedStatement ps = null;
		Invoice invoice = null;
		try {
			ps = conn.prepareStatement("select * from invoice where workId="+workId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				invoice = new Invoice();
				invoice.setInvoiceId(rs.getInt("invoiceId"));
				invoice.setRegisterDate(rs.getDate("registerDate"));
				invoice.setReceiveDate(rs.getDate("receiveDate"));
				invoice.setState(rs.getInt("state"));
				invoice.setVoidDate(rs.getDate("voidDate"));
				invoice.setWorkId(workId);
				invoice.setStatus(rs.getInt("status"));
				invoice.setOperator(rs.getString("operator"));
				invoice.setOperateDate(rs.getDate("operateDate"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}	
		return invoice;
	}

	//退货时自动废弃
	public void abandonByNewOrderId(int newOrderId){
		PreparedStatement ps = null;
		int workId = -1;
		try {
			ps = conn.prepareStatement("select workId from workorder where workType = 1 and orderId = "+newOrderId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				workId = rs.getInt("workId");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(workId!=-1){
			abandonInvoice(workId,"test");
		}
	}
}
