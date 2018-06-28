package com.neuedu.model.service;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import com.neuedu.model.dao.CenterReturnOrderDAO;
import com.neuedu.model.dao.CenterReturnOrderDAOImp;
import com.neuedu.model.dao.InvoiceDAO;
import com.neuedu.model.dao.InvoiceDAOImp;
import com.neuedu.model.dao.PurchaseInOrderDAO;
import com.neuedu.model.dao.PurchaseInOrderDAOImp;
import com.neuedu.model.dao.SignDAO;
import com.neuedu.model.dao.SignDAOImp;
import com.neuedu.model.po.CenterReturnOrder;
import com.neuedu.model.po.PurchaseInOrder;
import com.neuedu.model.po.Sign;
import com.neuedu.utils.DBUtil;

public class ClearMoneyService {
	//����ģʽ
	private static ClearMoneyService service = new ClearMoneyService();
	private ClearMoneyService(){}
	public static ClearMoneyService getInstance(){
		return service;
	}
	
	//��ѯδ����ǩ�յ�
	public static List<Sign> selectSign(String substation,Date selectDate,String product){
		Connection conn = DBUtil.getConn();
		SignDAO dao = new SignDAOImp(conn);
		return dao.selectSign(substation, selectDate, product);
	}
	
	//����ǩ�յ� 
	public void clearSubstation(int[] ids,String employeeName){
		Connection conn = DBUtil.getConn();
		//��������
		DBUtil.beginTransaction(conn);
		try{
			SignDAO dao = new SignDAOImp(conn);
			dao.clearSubstation(ids,employeeName);
			DBUtil.commit(conn);
		}catch(Exception e){
			//�ع�
			DBUtil.rollback(conn);
		}finally{
			DBUtil.closeConn(conn);
		}
	}
	
	//��ѯ�ѽ��㶩��
	public List<Sign> selectClearedSub(String substation, Date date, String product) {
		Connection conn = DBUtil.getConn();
		SignDAO dao = new SignDAOImp(conn);
		return dao.selectClearedSub(substation, date, product);
	}
	
	
	//��ѯδ���㹺����ⵥ
	public static List<PurchaseInOrder> selectPIOrder(String supplier,Date date,String product){
		Connection conn = DBUtil.getConn();
		PurchaseInOrderDAO dao = new PurchaseInOrderDAOImp(conn);
		return dao.selectPIOrder(supplier, date, product);
	}
	
	//��ѯ�ѽ��㹺����ⵥ
		public static List<PurchaseInOrder> selectClearedPIOrder(String supplier,Date date,String product){
			Connection conn = DBUtil.getConn();
			PurchaseInOrderDAO dao = new PurchaseInOrderDAOImp(conn);
			return dao.selectClearedSuppiler(supplier, date, product);
		}
	
	//��ѯδ���������˻���
	public static List<CenterReturnOrder> selectCROrder(String supplier,Date date,String product){
		Connection conn = DBUtil.getConn();
		CenterReturnOrderDAO dao = new CenterReturnOrderDAOImp(conn);
		return dao.selectCROrder(supplier, date, product);
	}
	
	//��ѯ�ѽ��������˻���
		public static List<CenterReturnOrder> selectClearedCROrder(String supplier,Date date,String product){
			Connection conn = DBUtil.getConn();
			CenterReturnOrderDAO dao = new CenterReturnOrderDAOImp(conn);
			return dao.selectClearedSuppiler(supplier, date, product);
		}
	
	//���㹺�������˻���
	public static void clearSuppiler(int[] ids1,int[] ids2,String employeeName){
		Connection conn = DBUtil.getConn();
		//��������
		DBUtil.beginTransaction(conn);
		try{
			//���㹺����
			PurchaseInOrderDAO dao1 = new PurchaseInOrderDAOImp(conn);
			dao1.clearSuppiler(ids1, employeeName);
			//�����˻���
			CenterReturnOrderDAO dao2 = new CenterReturnOrderDAOImp(conn);
			dao2.clearSuppiler(ids2, employeeName);
			DBUtil.commit(conn);
		}catch(Exception e){
			//�ع�
			DBUtil.rollback(conn);
		}finally{
			DBUtil.closeConn(conn);
		}
	}
	
}
