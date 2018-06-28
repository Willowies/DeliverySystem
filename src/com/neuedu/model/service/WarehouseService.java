package com.neuedu.model.service;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import com.neuedu.model.dao.CenterInRecordDAO;
import com.neuedu.model.dao.CenterInRecordDAOImp;
import com.neuedu.model.dao.CenterOutRecordDAO;
import com.neuedu.model.dao.CenterOutRecordDAOImp;
import com.neuedu.model.dao.DistributionOrderDAO;
import com.neuedu.model.dao.DistributionOrderDAOImp;
import com.neuedu.model.dao.ProductTransferOrderDAO;
import com.neuedu.model.dao.ProductTransferOrderDAOImp;
import com.neuedu.model.dao.PurchaseInOrderDAO;
import com.neuedu.model.dao.PurchaseInOrderDAOImp;
import com.neuedu.model.dao.ReceiveRecordDAO;
import com.neuedu.model.dao.ReceiveRecordDAOImp;
import com.neuedu.model.dao.SubInRecordDAO;
import com.neuedu.model.dao.SubInRecordDAOImp;
import com.neuedu.model.dao.WarehouseDAO;
import com.neuedu.model.dao.WarehouseDAOImp;
import com.neuedu.model.dao.WorkOrderDAO;
import com.neuedu.model.dao.WorkOrderDAOImp;
import com.neuedu.model.po.CenterInRecord;
import com.neuedu.model.po.CenterOutRecord;
import com.neuedu.model.po.DistributionOrder;
import com.neuedu.model.po.ProductTransferOrder;
import com.neuedu.model.po.PurchaseInOrder;
import com.neuedu.model.po.ReceiveRecord;
import com.neuedu.model.po.SubInRecord;
import com.neuedu.model.po.Warehouse;
import com.neuedu.utils.DBUtil;

public class WarehouseService {
	private WarehouseService(){}
	private static WarehouseService service = new WarehouseService();
	public static WarehouseService getInstance(){
		return service;
	}
	
	public PurchaseInOrder selectPurchaseInOrder(int purchaseId){
		Connection conn = DBUtil.getConn();
		PurchaseInOrderDAO purOrderDao = new PurchaseInOrderDAOImp(conn);
		return purOrderDao.selectPurchaseInOrder(purchaseId);
		
	}

	public boolean intoCenter(CenterInRecord cInRecord, String employee) {
		Connection conn = DBUtil.getConn();
		CenterInRecordDAO cInRecordDao = new CenterInRecordDAOImp(conn);
		return cInRecordDao.intoCenter(cInRecord,employee);
	}

	public List<ProductTransferOrder> selectProductTransferOrder(Date inputDate) {
		Connection conn = DBUtil.getConn();
		ProductTransferOrderDAO transferOrderDao = new ProductTransferOrderDAOImp(conn);
		return transferOrderDao.selectProductTransferOrder(inputDate);
	}

	public boolean outCenter(int transferId,String employee) {
		Connection conn = DBUtil.getConn();
		CenterOutRecordDAO cOutRecordDao = new CenterOutRecordDAOImp(conn);
		return cOutRecordDao.outCenter(transferId,employee);
	}

	public DistributionOrder selectDistributionOrder(int distributionId) {
		Connection conn = DBUtil.getConn();
		DistributionOrderDAO disDao = new DistributionOrderDAOImp(conn);
		return disDao.selectDistributionOrder(distributionId);
	}

	public boolean inSubWarehouse(SubInRecord sir, String employee) {
		Connection conn = DBUtil.getConn();
		SubInRecordDAO sirDao = new SubInRecordDAOImp(conn);
		return sirDao.inSubWarehouse(sir,employee);
	}

	public boolean selectWorkOrder(int workId) {
		Connection conn = DBUtil.getConn();
		WorkOrderDAO workOrderDao = new WorkOrderDAOImp(conn);
		return workOrderDao.selectWorkOrder(workId);
	}

	public boolean receiveProduct(ReceiveRecord rr, String employee) {
		Connection conn = DBUtil.getConn();
		ReceiveRecordDAO receiveDao = new ReceiveRecordDAOImp(conn);
		return receiveDao.receiveProduct(rr,employee);
	}

	public List<CenterOutRecord> selectCenterOut(Date outDate, String productName) {
		Connection conn = DBUtil.getConn();
		CenterOutRecordDAO corDao = new CenterOutRecordDAOImp(conn);
		return corDao.selectCenterOut(outDate, productName);
	}

	public List<CenterOutRecord> selectCenterOut(Date outDate) {
		Connection conn = DBUtil.getConn();
		CenterOutRecordDAO corDao = new CenterOutRecordDAOImp(conn);
		return corDao.selectCenterOut(outDate);
	}

	public List<DistributionOrder> selectDistribution(Date outDate, String productName, String warehouseName) {
		Connection conn = DBUtil.getConn();
		DistributionOrderDAO disOrderDao = new DistributionOrderDAOImp(conn);
		return disOrderDao.selectDistribution(outDate,productName,warehouseName);
	}

	public List<DistributionOrder> selectDistribution(Date outDate, String warehouseName) {
		Connection conn = DBUtil.getConn();
		DistributionOrderDAO disOrderDao = new DistributionOrderDAOImp(conn);
		return disOrderDao.selectDistribution(outDate,warehouseName);
	}
	//增加库房
	public void addWarehouse(Warehouse warehouse) {
		//获取与数据库的连接
		Connection conn=DBUtil.getConn();
		//开启事务
		DBUtil.beginTransaction(conn);
		try {
			WarehouseDAO dao=new WarehouseDAOImp(conn);
			dao.addWarehouse(warehouse);
			//提交事务
			DBUtil.commit(conn);			
		}catch(Exception e) {
			//回滚
			DBUtil.rollback(conn);
		}finally {
			//关闭连接
			DBUtil.closeConn(conn);
		}
	}
	
	
	//组合查询
	public List<Warehouse> selectWarehouse(String warehouseName){
		//获取与数据库的连接
		Connection conn=DBUtil.getConn();
		WarehouseDAO dao=new WarehouseDAOImp(conn);
		return dao.selectWarehouse(warehouseName);
	}
	
	
		
	
	//更改库房
	public void updateWarehouse(Warehouse warehouse) {
		//获取与数据库的连接
		Connection conn=DBUtil.getConn();
		WarehouseDAO dao=new WarehouseDAOImp(conn);
		dao.updateWarehouse(warehouse);
	}
	
	
	
	//删除库房
	public void deleteWarehouse(String warehouseId) {
		Connection conn = DBUtil.getConn();
		WarehouseDAO dao=new WarehouseDAOImp(conn);
		dao.deleteWarehouse(warehouseId);
	}

	public Warehouse getWarehouseById(int warehouseId) {
		Connection conn=DBUtil.getConn();
		WarehouseDAO dao=new WarehouseDAOImp(conn);
		return dao.getWarehouseById(warehouseId);
	}
}
