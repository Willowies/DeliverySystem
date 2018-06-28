package com.neuedu.model.service;

import java.util.Date;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.neuedu.model.dao.DeliveryStaffDAO;
import com.neuedu.model.dao.DeliveryStaffDAOImp;
import com.neuedu.model.dao.FeedbackDAO;
import com.neuedu.model.dao.FeedbackDAOImp;
import com.neuedu.model.dao.SignDAO;
import com.neuedu.model.dao.SignDAOImp;
import com.neuedu.model.dao.WorkOrderDAO;
import com.neuedu.model.dao.WorkOrderDAOImp;
import com.neuedu.model.po.DeliveryStaff;
import com.neuedu.model.po.ProInfo;
import com.neuedu.model.po.Sign;
import com.neuedu.model.po.WorkOrder;
import com.neuedu.utils.DBUtil;

public class SubstationService {
	/*
	 * 设计成单例模式
	 */
	private SubstationService(){
		
	}
	
	private static SubstationService service = new SubstationService();
	
	public static SubstationService getInstance(){
		return service;
	}
	/*
	 * Service来完成业务逻辑和事务处理
	 */
	//1.查询每一页的数据
	public List<WorkOrder> selectPageWork(int warehouseId, Date requireDate, int workStatus, int workType, int pageNum){
		Connection conn = (Connection) DBUtil.getConn();
		WorkOrderDAO dao = new WorkOrderDAOImp(conn);
		System.out.println("Service+++workStatus:"+workStatus+"  workType:"+workType);
		return dao.selectPageWork(warehouseId, requireDate, workStatus, workType, pageNum);
	}
	//2.查询数据的页数
	public int selectPageCount(int warehouseId, Date requireDate, int workStatus, int workType){
		Connection conn = (Connection)DBUtil.getConn();
		WorkOrderDAO dao = new WorkOrderDAOImp(conn);
		return dao.selectPageCount(warehouseId, requireDate, workStatus, workType);
	}
	//3.分配任务，更新数据库信息
	public void workAssign(int workId, int workStatus, int deliveryStaffId){
		//连接数据库
		Connection conn = (Connection)DBUtil.getConn();
		//开启事务
		DBUtil.beginTransaction(conn);
		try {
			WorkOrderDAO dao = new WorkOrderDAOImp(conn);
			System.out.println("Service,assignworking,  workId:"+workId+"  workStatus:"+workStatus+"  deliveryStaffId:"+deliveryStaffId);
			dao.workAssign(workId, workStatus, deliveryStaffId);
			DBUtil.commit(conn);
		} catch (Exception e) {
			//数据库报错回滚
			DBUtil.rollback(conn);
		} finally {
			DBUtil.closeConn(conn);
		}
	}
	//4.分页查询，加入了deliveryStaffId的参数
	public List<WorkOrder> selectPageWork(int warehouseId, int deliveryStaffId, Date requireDate, int workStatus, int workType, int pageNum){
		Connection conn = (Connection)DBUtil.getConn();
		WorkOrderDAO dao = new WorkOrderDAOImp(conn);
		System.out.println("Service+++workStatus:"+workStatus+"  workType:"+workType);
		return dao.selectPageWork(warehouseId, deliveryStaffId, requireDate, workStatus, workType, pageNum);
	}
	//5.查询页数，五个元素
	public int selectPageCount(int warehouseId, int deliveryStaffId, Date requireDate, int workStatus, int workType){
		Connection conn = (Connection)DBUtil.getConn();
		WorkOrderDAO dao = new WorkOrderDAOImp(conn);
		return dao.selectPageCount(warehouseId, deliveryStaffId, requireDate, workStatus, workType);
	}
	//6.回执录入
	public void feedbackRecord(int workId, int customerFeedback, String remark){
		//连接数据库
		Connection conn = (Connection)DBUtil.getConn();
		//开启事务
		DBUtil.beginTransaction(conn);
		try {
			FeedbackDAO dao = new FeedbackDAOImp(conn);
			System.out.println("Service,feedbackRecording,  workStatus:"+workId+"  customerFeedback:"+customerFeedback+"  remark:"+remark);
			dao.feedbackRecord(workId, customerFeedback, remark);
			DBUtil.commit(conn);
		} catch (Exception e) {
			//数据库报错回滚
			DBUtil.rollback(conn);
		} finally {
			DBUtil.closeConn(conn);
		}
	}
	//7.分页查询，统计商品信息
	public List<ProInfo> selectPageProduct(Date beginDate, Date endDate, String productName, int pageNum){
		Connection conn = (Connection)DBUtil.getConn();
		FeedbackDAO dao = new FeedbackDAOImp(conn);
		return dao.selectPageProduct(beginDate, endDate, productName, pageNum);
	}
	//8.统计商品信息的总页数
	public int selectPageProduct(Date beginDate, Date endDate, String productName){
		Connection conn = (Connection)DBUtil.getConn();
		FeedbackDAO dao = new FeedbackDAOImp(conn);
		return dao.selectPageProduct(beginDate, endDate, productName);
	}
	//9.获得指定签收单
	public Sign getSign(WorkOrder workOrder){
		Connection conn = (Connection)DBUtil.getConn();
		SignDAO dao = new SignDAOImp(conn);
		return dao.getSign(workOrder);
	}
	//10.获得配送员信息
	public List<DeliveryStaff> getDeliveryStaff(int employeeId){
		Connection conn = (Connection)DBUtil.getConn();
		DeliveryStaffDAO dao = new DeliveryStaffDAOImp(conn);
		return dao.getDeliveryStaff(employeeId);
	}
	
}
