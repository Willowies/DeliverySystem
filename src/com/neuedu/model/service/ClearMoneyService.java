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
	//单例模式
	private static ClearMoneyService service = new ClearMoneyService();
	private ClearMoneyService(){}
	public static ClearMoneyService getInstance(){
		return service;
	}
	
	//查询未结算签收单
	public static List<Sign> selectSign(String substation,Date selectDate,String product){
		Connection conn = DBUtil.getConn();
		SignDAO dao = new SignDAOImp(conn);
		return dao.selectSign(substation, selectDate, product);
	}
	
	//结算签收单 
	public void clearSubstation(int[] ids,String employeeName){
		Connection conn = DBUtil.getConn();
		//开启事务
		DBUtil.beginTransaction(conn);
		try{
			SignDAO dao = new SignDAOImp(conn);
			dao.clearSubstation(ids,employeeName);
			DBUtil.commit(conn);
		}catch(Exception e){
			//回滚
			DBUtil.rollback(conn);
		}finally{
			DBUtil.closeConn(conn);
		}
	}
	
	//查询已结算订单
	public List<Sign> selectClearedSub(String substation, Date date, String product) {
		Connection conn = DBUtil.getConn();
		SignDAO dao = new SignDAOImp(conn);
		return dao.selectClearedSub(substation, date, product);
	}
	
	
	//查询未结算购货入库单
	public static List<PurchaseInOrder> selectPIOrder(String supplier,Date date,String product){
		Connection conn = DBUtil.getConn();
		PurchaseInOrderDAO dao = new PurchaseInOrderDAOImp(conn);
		return dao.selectPIOrder(supplier, date, product);
	}
	
	//查询已结算购货入库单
		public static List<PurchaseInOrder> selectClearedPIOrder(String supplier,Date date,String product){
			Connection conn = DBUtil.getConn();
			PurchaseInOrderDAO dao = new PurchaseInOrderDAOImp(conn);
			return dao.selectClearedSuppiler(supplier, date, product);
		}
	
	//查询未结算中心退货单
	public static List<CenterReturnOrder> selectCROrder(String supplier,Date date,String product){
		Connection conn = DBUtil.getConn();
		CenterReturnOrderDAO dao = new CenterReturnOrderDAOImp(conn);
		return dao.selectCROrder(supplier, date, product);
	}
	
	//查询已结算中心退货单
		public static List<CenterReturnOrder> selectClearedCROrder(String supplier,Date date,String product){
			Connection conn = DBUtil.getConn();
			CenterReturnOrderDAO dao = new CenterReturnOrderDAOImp(conn);
			return dao.selectClearedSuppiler(supplier, date, product);
		}
	
	//结算购货单和退货单
	public static void clearSuppiler(int[] ids1,int[] ids2,String employeeName){
		Connection conn = DBUtil.getConn();
		//开启事务
		DBUtil.beginTransaction(conn);
		try{
			//结算购货单
			PurchaseInOrderDAO dao1 = new PurchaseInOrderDAOImp(conn);
			dao1.clearSuppiler(ids1, employeeName);
			//结算退货单
			CenterReturnOrderDAO dao2 = new CenterReturnOrderDAOImp(conn);
			dao2.clearSuppiler(ids2, employeeName);
			DBUtil.commit(conn);
		}catch(Exception e){
			//回滚
			DBUtil.rollback(conn);
		}finally{
			DBUtil.closeConn(conn);
		}
	}
	
}
