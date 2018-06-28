package com.neuedu.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.neuedu.model.dao.InvoiceDAO;
import com.neuedu.model.dao.InvoiceDAOImp;
import com.neuedu.model.dao.WorkOrderDAO;
import com.neuedu.model.dao.WorkOrderDAOImp;
import com.neuedu.model.po.Invoice;
import com.neuedu.model.po.NewOrder;
import com.neuedu.model.po.ReturnOrder;
import com.neuedu.model.po.WorkOrder;
import com.neuedu.utils.DBUtil;

public class InvoiceService {
	
		//单例模式
		private static InvoiceService service = new InvoiceService();
		private InvoiceService(){}
		public static InvoiceService getInstance(){
			return service;
		}
		//查询发票
		public static Invoice selectInvoice(String invoiceId){
			Connection conn = DBUtil.getConn();
			InvoiceDAO dao = new InvoiceDAOImp(conn);
			return dao.selectInvoice(invoiceId);
		}
		
		//发票被分站领用
		public static void getSubstationInvoice(int invoiceId,String emloyeeName){
			Connection conn = DBUtil.getConn();
			//开启事务
			DBUtil.beginTransaction(conn);
			try{
				InvoiceDAO dao = new InvoiceDAOImp(conn);
				dao.getSubstationInvoice(invoiceId,emloyeeName);
				DBUtil.commit(conn);
			}catch(Exception e){
				//回滚
				DBUtil.rollback(conn);
			}finally{
				DBUtil.closeConn(conn);
			}
		}
		
		//查询通过任务单号查询任务单
		public static WorkOrder selectWorkOrder(String workId){
			WorkOrder workorder = new WorkOrder();
/*			workorder.setOrderId(Integer.parseInt(workId));
			Connection conn = DBUtil.getConn();
			WorkOrderDAO dao = new WorkOrderDAOImp(conn);
			List<WorkOrder> orders = new ArrayList<WorkOrder>();
			orders = dao.searchWorkOrder(workorder);
			if(orders.isEmpty()){
				return null;
			}else{
				return orders.get(0);
			}*/
			Connection conn = DBUtil.getConn();
			InvoiceDAO dao = new InvoiceDAOImp(conn);
			return dao.selectWorkOrder(workId);			
		}
		
		//通过订单号查询订单
		public static NewOrder selectNewOrder(int orderId){
			Connection conn = DBUtil.getConn();
			InvoiceDAO dao = new InvoiceDAOImp(conn);
			return dao.selectNewOrder(orderId);
		}
		
		//通过订单号查询订单
		public static ReturnOrder selectReturnOrder(int orderId){
			Connection conn = DBUtil.getConn();
			InvoiceDAO dao = new InvoiceDAOImp(conn);
			return dao.selectReturnOrder(orderId);
		}
		
		//记录发票
		public static Invoice recordInvoice(Invoice invoice,String employeeName){
			Connection conn = DBUtil.getConn();
			//开启事务
			DBUtil.beginTransaction(conn);
			try{
				InvoiceDAO dao = new InvoiceDAOImp(conn);
				invoice = dao.recordInvoice(invoice,employeeName);
				DBUtil.commit(conn);
			}catch(Exception e){
				//回滚
				DBUtil.rollback(conn);
			}finally{
				DBUtil.closeConn(conn);
			}
			return invoice;
		}
		
		//登记发票
		public static void registerInvoice(int invoiceId,String employeeName){
			Connection conn = DBUtil.getConn();
			//开启事务
			DBUtil.beginTransaction(conn);
			try{
				InvoiceDAO dao = new InvoiceDAOImp(conn);
				dao.registerInvoice(invoiceId,employeeName);
				DBUtil.commit(conn);
			}catch(Exception e){
				//回滚
				DBUtil.rollback(conn);
			}finally{
				DBUtil.closeConn(conn);
			}
		}
		
		//客户领用发票
		public static void getClientInvoice(int invoiceId, String employeeName) {
			// TODO Auto-generated method stub
			Connection conn = DBUtil.getConn();
			//开启事务
			DBUtil.beginTransaction(conn);
			try{
				InvoiceDAO dao = new InvoiceDAOImp(conn);
				dao.getClientInvoice(invoiceId,employeeName);
				DBUtil.commit(conn);
			}catch(Exception e){
				//回滚
				DBUtil.rollback(conn);
			}finally{
				DBUtil.closeConn(conn);
			}
		}
		
		//通过任务单号废弃发票
		public static void abandonInvoice(int workId,String employeeName){

			Connection conn = DBUtil.getConn();
			//开启事务
			DBUtil.beginTransaction(conn);
			try{
				InvoiceDAO dao = new InvoiceDAOImp(conn);
				dao.abandonInvoice(workId,employeeName);
				DBUtil.commit(conn);
			}catch(Exception e){
				//回滚
				DBUtil.rollback(conn);
			}finally{
				DBUtil.closeConn(conn);
			}
		}
		
		//通过任务单号查询发票
		public static Invoice selectInvoiceByWorkId(int workId){
			Connection conn = DBUtil.getConn();
			InvoiceDAO dao = new InvoiceDAOImp(conn);
			return dao.selectInvoiceByWorkId(workId);
		}
}	
