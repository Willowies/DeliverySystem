package com.neuedu.model.service;

import java.util.Date;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.neuedu.model.dao.WorkOrderDAO;
import com.neuedu.model.dao.WorkOrderDAOImp;
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
	public List<WorkOrder> selectPageWork(Date requireDate, int workStatus, int workType, int pageNum){
		Connection conn = (Connection) DBUtil.getConn();
		WorkOrderDAO dao = new WorkOrderDAOImp(conn);
		return dao.selectPageWork(requireDate, workStatus, workType, pageNum);
	}
	//2.查询数据的页数
	public int selectPageCount(Date requireDate, int workStatus, int workType){
		Connection conn = (Connection)DBUtil.getConn();
		WorkOrderDAO dao = new WorkOrderDAOImp(conn);
		return dao.selectPageCount(requireDate, workStatus, workType);
	}
	
}
