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
	
		//����ģʽ
		private static InvoiceService service = new InvoiceService();
		private InvoiceService(){}
		public static InvoiceService getInstance(){
			return service;
		}
		//��ѯ��Ʊ
		public static Invoice selectInvoice(String invoiceId){
			Connection conn = DBUtil.getConn();
			InvoiceDAO dao = new InvoiceDAOImp(conn);
			return dao.selectInvoice(invoiceId);
		}
		
		//��Ʊ����վ����
		public static void getSubstationInvoice(int invoiceId,String emloyeeName){
			Connection conn = DBUtil.getConn();
			//��������
			DBUtil.beginTransaction(conn);
			try{
				InvoiceDAO dao = new InvoiceDAOImp(conn);
				dao.getSubstationInvoice(invoiceId,emloyeeName);
				DBUtil.commit(conn);
			}catch(Exception e){
				//�ع�
				DBUtil.rollback(conn);
			}finally{
				DBUtil.closeConn(conn);
			}
		}
		
		//��ѯͨ�����񵥺Ų�ѯ����
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
		
		//ͨ�������Ų�ѯ����
		public static NewOrder selectNewOrder(int orderId){
			Connection conn = DBUtil.getConn();
			InvoiceDAO dao = new InvoiceDAOImp(conn);
			return dao.selectNewOrder(orderId);
		}
		
		//ͨ�������Ų�ѯ����
		public static ReturnOrder selectReturnOrder(int orderId){
			Connection conn = DBUtil.getConn();
			InvoiceDAO dao = new InvoiceDAOImp(conn);
			return dao.selectReturnOrder(orderId);
		}
		
		//��¼��Ʊ
		public static Invoice recordInvoice(Invoice invoice,String employeeName){
			Connection conn = DBUtil.getConn();
			//��������
			DBUtil.beginTransaction(conn);
			try{
				InvoiceDAO dao = new InvoiceDAOImp(conn);
				invoice = dao.recordInvoice(invoice,employeeName);
				DBUtil.commit(conn);
			}catch(Exception e){
				//�ع�
				DBUtil.rollback(conn);
			}finally{
				DBUtil.closeConn(conn);
			}
			return invoice;
		}
		
		//�ǼǷ�Ʊ
		public static void registerInvoice(int invoiceId,String employeeName){
			Connection conn = DBUtil.getConn();
			//��������
			DBUtil.beginTransaction(conn);
			try{
				InvoiceDAO dao = new InvoiceDAOImp(conn);
				dao.registerInvoice(invoiceId,employeeName);
				DBUtil.commit(conn);
			}catch(Exception e){
				//�ع�
				DBUtil.rollback(conn);
			}finally{
				DBUtil.closeConn(conn);
			}
		}
		
		//�ͻ����÷�Ʊ
		public static void getClientInvoice(int invoiceId, String employeeName) {
			// TODO Auto-generated method stub
			Connection conn = DBUtil.getConn();
			//��������
			DBUtil.beginTransaction(conn);
			try{
				InvoiceDAO dao = new InvoiceDAOImp(conn);
				dao.getClientInvoice(invoiceId,employeeName);
				DBUtil.commit(conn);
			}catch(Exception e){
				//�ع�
				DBUtil.rollback(conn);
			}finally{
				DBUtil.closeConn(conn);
			}
		}
		
		//ͨ�����񵥺ŷ�����Ʊ
		public static void abandonInvoice(int workId,String employeeName){

			Connection conn = DBUtil.getConn();
			//��������
			DBUtil.beginTransaction(conn);
			try{
				InvoiceDAO dao = new InvoiceDAOImp(conn);
				dao.abandonInvoice(workId,employeeName);
				DBUtil.commit(conn);
			}catch(Exception e){
				//�ع�
				DBUtil.rollback(conn);
			}finally{
				DBUtil.closeConn(conn);
			}
		}
		
		//ͨ�����񵥺Ų�ѯ��Ʊ
		public static Invoice selectInvoiceByWorkId(int workId){
			Connection conn = DBUtil.getConn();
			InvoiceDAO dao = new InvoiceDAOImp(conn);
			return dao.selectInvoiceByWorkId(workId);
		}
}	
