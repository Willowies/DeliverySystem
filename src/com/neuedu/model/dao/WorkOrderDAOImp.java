package com.neuedu.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neuedu.model.po.Client;
import com.neuedu.model.po.DeliveryStockProduct;
import com.neuedu.model.po.NewOrder;
import com.neuedu.model.po.Product;
import com.neuedu.model.po.WarehouseNameInfo;
import com.neuedu.model.po.WorkOrder;
import com.neuedu.utils.DBUtil;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.swing.internal.plaf.metal.resources.metal_zh_TW;

public class WorkOrderDAOImp implements WorkOrderDAO {

	Connection conn;
	
	public WorkOrderDAOImp(Connection conn){
		this.conn = conn;
	}
	
	@Override
	public List<WorkOrder> searchWorkOrder(WorkOrder order) {
		List<WorkOrder> orders = new ArrayList<WorkOrder>();
		PreparedStatement ps = null;
		StringBuffer sbf = new StringBuffer("");
		sbf.append("select a.*,b.clientName,b.clientMobilephone,d.warehouseName "
				+ "from workorder a,userinfo b,neworder c,warehouse d "
				+ "where a.orderId=c.newOrderId and b.clientId=c.clientId "
				+ "and d.warehouseId=a.warehouseId and a.workType=1");
		int flag = 0;
		if (order.getWorkId() != 0) {
			sbf.append(" and workId = ? ");
		}
		if (order.getCreateDate() != null) {
			sbf.append(" and createDate = ? ");
		}
		if (order.getRequireDate() != null) {
			sbf.append(" and requireDate = ? ");
		}
		if (order.getWarehouseName() != null) {
			sbf.append(" and d.warehouseName = ? ");
		}
		if (order.getWorkStatus() != 0) {
			sbf.append(" and a.workStatus = ? ");
		}
		if (order.getClientName() != null) {
			flag = 1;
			sbf.append(" and orderId in ( select newOrderId from neworder where clientId in (select clientId from userinfo where clientName = ? ");
		}
		if (order.getClientPhone() != null) {
			if (flag == 0) {
				sbf.append(" and orderId in ( select newOrderId from neworder where clientId in (select clientId from userinfo where clientMobilephone = ? ");
				flag = 1;
			}else if (flag == 1) {
				sbf.append(" and clientMobilephone = ? ");
			}
		}
		if (flag==1) {
			sbf.append(" ) )");
		}
		System.out.println("sql:"+sbf);
		try {
			ps = conn.prepareStatement(sbf.toString());
			int index = 1;
			if (order.getWorkId() != 0) {
				ps.setInt(index, order.getWorkId());
				index++;
			}
			if (order.getCreateDate() != null) {
				Date date = new Date(order.getCreateDate().getTime());
				ps.setDate(index,  date);
				index++;
			}
			if (order.getRequireDate() != null) {
				Date date = new Date(order.getRequireDate().getTime());
				ps.setDate(index,  date);
				index++;
			}
			if (order.getWarehouseName() != null) {
				ps.setString(index, order.getWarehouseName());
				index++;
			}
			if (order.getWorkStatus() != 0) {
				ps.setInt(index, order.getWorkStatus());
				index++;
			}
			if (order.getClientName() != null) {
				ps.setString(index, order.getClientName());
				index++;
			}
			if (order.getClientPhone() != null) {
				ps.setString(index, order.getClientPhone());
				index++;
			}
			
			ResultSet rs = ps.executeQuery();
			
			if (order.getWorkType() != 2) {
				while (rs.next()) {
					WorkOrder o = new WorkOrder();
					o.setWorkId(rs.getInt("workId"));
					o.setWarehouseName(rs.getString("warehouseName"));
					o.setClientName(rs.getString("clientName"));
					o.setClientPhone(rs.getString("clientMobilephone"));
					o.setWorkStatus(rs.getInt("workStatus"));
					o.setWorkType(rs.getInt("workType"));
					o.setCreateDate(rs.getDate("createDate"));
					o.setRequireDate(rs.getDate("requireDate"));
					o.setRemark(rs.getString("remark"));
					orders.add(o);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		sbf = new StringBuffer("");
		sbf.append("select a.*,b.clientName,b.clientMobilephone,d.warehouseName "
				+ "from workorder a,userinfo b,returnorder c,warehouse d,neworder e "
				+ "where a.orderId=c.returnOrderId and c.newOrderId = e.newOrderId and b.clientId=e.clientId "
				+ "and d.warehouseId=a.warehouseId and a.workType=2");
		flag = 0;
		if (order.getWorkId() != 0) {
			sbf.append(" and a.workId = ? ");
		}
		if (order.getCreateDate() != null) {
			sbf.append(" and a.createDate = ? ");
		}
		if (order.getRequireDate() != null) {
			sbf.append(" and a.requireDate = ? ");
		}
		if (order.getWarehouseName() != null) {
			sbf.append(" and d.warehouseName = ? ");
		}
		if (order.getWorkStatus() != 0) {
			sbf.append(" and a.workStatus = ? ");
		}
		if (order.getClientName() != null) {
			flag = 1;
			sbf.append(" and orderId in ( select orderId from returnorder where returnOrderId in (SELECT newOrderId from neworder where clientId in (select clientId from userinfo where clientName = ? ");
		}
		if (order.getClientPhone() != null) {
			if (flag == 0) {
				sbf.append(" and orderId in ( select orderId from returnorder where returnOrderId in (SELECT newOrderId from neworder where clientId in (select clientId from userinfo where clientMobilephone = ? ");
				flag = 1;
			}else if (flag == 1) {
				sbf.append(" and clientMobilephone = ? ");
			}
		}
		if (flag==1) {
			sbf.append(" )))");
		}
		System.out.println("sql:"+sbf);
		try {
			ps = conn.prepareStatement(sbf.toString());
			int index = 1;
			if (order.getWorkId() != 0) {
				ps.setInt(index, order.getWorkId());
				index++;
			}
			if (order.getCreateDate() != null) {
				Date date = new Date(order.getCreateDate().getTime());
				ps.setDate(index,  date);
				index++;
			}
			if (order.getRequireDate() != null) {
				Date date = new Date(order.getRequireDate().getTime());
				ps.setDate(index,  date);
				index++;
			}
			if (order.getWarehouseName() != null) {
				ps.setString(index, order.getWarehouseName());
				index++;
			}
			if (order.getWorkStatus() != 0) {
				ps.setInt(index, order.getWorkStatus());
				index++;
			}
			if (order.getClientName() != null) {
				ps.setString(index, order.getClientName());
				index++;
			}
			if (order.getClientPhone() != null) {
				ps.setString(index, order.getClientPhone());
				index++;
			}
			
			ResultSet rs = ps.executeQuery();
			
			if (order.getWorkType() != 1) {
				while (rs.next()) {
					WorkOrder o = new WorkOrder();
					o.setWorkId(rs.getInt("workId"));
					o.setWarehouseName(rs.getString("warehouseName"));
					o.setClientName(rs.getString("clientName"));
					o.setClientPhone(rs.getString("clientMobilephone"));
					o.setWorkStatus(rs.getInt("workStatus"));
					o.setWorkType(rs.getInt("workType"));
					o.setCreateDate(rs.getDate("createDate"));
					o.setRequireDate(rs.getDate("requireDate"));
					o.setRemark(rs.getString("remark"));
					orders.add(o);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return orders;
	}
	
	public void createWorkOrder(int orderId,int orderType,String warehouseName,String remark,String operator) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("select * from warehouse where warehouseName = ? ;");
			ps.setString(1, warehouseName);
		
			int warehouseId = 0;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				warehouseId = rs.getInt("warehouseId");
			}
			
			Date date = new Date(new java.util.Date().getTime());
			Date date2 = new Date(new java.util.Date().getTime()+7*24*60*60*1000);
			Date date3 = new Date(new java.util.Date().getTime()+24*60*60*1000);
			ps = conn.prepareStatement(" insert into workorder (warehouseId,orderId,workStatus,workType,"
					+ "createDate,requireDate,operator,operateDate,remark,status) values (?,?,?,?,?,?,?,?,?,?)");
			ps.setInt(1, warehouseId);
			ps.setInt(2, orderId);
			ps.setInt(3, 1);
			ps.setInt(4, orderType);
			ps.setDate(5, date);
			ps.setDate(6, date2);
			ps.setString(7, operator);
			ps.setDate(8, date);
			ps.setString(9, remark);
			ps.setInt(10, 1);
			
			ps.executeUpdate();
			
			ps = conn.prepareStatement("select * from workorder where orderId = ? and workType=? ;");
			ps.setInt(1, orderId);
			ps.setInt(2, orderType);
		
			int workId = 0;
			ResultSet rs2 = ps.executeQuery();
			while (rs2.next()) {
				workId = rs2.getInt("workId");
			}
			
			ps = conn.prepareStatement(" insert into producttransferorder (workId,planOutDate) values (?,?)");
			ps.setInt(1, workId);
			ps.setDate(2, date3);
			
			ps.executeUpdate();
			
			if (orderType == 1) {
				ps = conn.prepareStatement(" update neworder set orderState = 6 "
						+ "and operator=? and operateDate=?  where newOrderId=?");
				ps.setString(1, operator);
				ps.setDate(2, date);
				ps.setInt(3, orderId);
				
				ps.executeUpdate();
			}else {
				ps = conn.prepareStatement(" update returnorder set orderState = 6 "
						+ "and operator=? and operateDate=?  where returnOrderId=?");
				ps.setString(1, operator);
				ps.setDate(2, date);
				ps.setInt(3, orderId);
				
				ps.executeUpdate();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
	}

	@Override
	public List<NewOrder> searchLackOrder(NewOrder order) {
		java.util.Date generateDate = order.getGenerateDate();
		java.util.Date requireDate = order.getRequireDate();
		boolean enough = order.isEnoughOrNot();
		
		StringBuffer sbf = new StringBuffer("");
		sbf.append("select o.*,b.allocatableQuantity,product.productName "
				+ "FROM neworder o,warehouse a,warehouseproduct b LEFT JOIN product using(productId) "
				+ "WHERE a.warehouseRank = 1 and a.warehouseId = b.warehouseId and o.productId = b.productId  and o.orderState = 3");
		if (generateDate != null) {
			sbf.append(" and generateDate = ? ");
		}
		if (requireDate != null) {
			sbf.append(" and requireDate = ? ");
		}
		
		List<NewOrder> newOrders = new ArrayList<NewOrder>();
		try {
			PreparedStatement ps = conn.prepareStatement(sbf.toString());
			int index=1;
			if (generateDate != null) {
				ps.setDate(index,new Date(generateDate.getTime()));
				index++;
			}
			if (requireDate != null) {
				ps.setDate(index, new Date(requireDate.getTime()));
				index++;
			}
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				NewOrder newOrder = new NewOrder();
				newOrder.setOrderId(rs.getInt("neworderId"));
				
				Product p = new Product();
				p.setProductName(rs.getString("productName"));
				newOrder.setProduct(p);
				
				newOrder.setProductQuantity(rs.getInt("productQuantity"));
				newOrder.setGenerateDate(rs.getDate("generateDate"));
				newOrder.setRequireDate(rs.getDate("requireDate"));
				if (rs.getInt("productQuantity")>rs.getInt("allocatableQUantity")) {
					newOrder.setEnoughOrNot(false);
					if (!enough) {
						newOrders.add(newOrder);
					}
				}else {
					newOrder.setEnoughOrNot(true);
					newOrders.add(newOrder);
				}

			}
			
		} catch (SQLException e) {
			
		}
		return newOrders;
	}

	//淇敼璁㈠崟鐘舵��
	public void modifyLackStatus(int orderId, String operator) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(" update neworder set orderState = 1 "
					+ "and operator=? and operateDate=? where newOrderId=?");
			ps.setString(1, operator);
			ps.setDate(2, new Date(new java.util.Date().getTime()));
			ps.setInt(3, orderId);
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
	}

	@Override
	public List<WorkOrder> searchWorkOrderByPage(WorkOrder order,int pageSize,int page) {
		List<WorkOrder> orders = new ArrayList<WorkOrder>();
		PreparedStatement ps = null;
		StringBuffer sbf = new StringBuffer("");
		sbf.append("select a.*,b.clientName,b.clientMobilephone,d.warehouseName "
				+ "from workorder a,userinfo b,neworder c,warehouse d "
				+ "where a.orderId=c.newOrderId and b.clientId=c.clientId "
				+ "and d.warehouseId=a.warehouseId and a.workType=1");
		int flag = 0;
		if (order.getWorkId() != 0) {
			sbf.append(" and workId = ? ");
		}
		if (order.getCreateDate() != null) {
			sbf.append(" and createDate = ? ");
		}
		if (order.getRequireDate() != null) {
			sbf.append(" and requireDate = ? ");
		}
		if (order.getWarehouseName() != null) {
			sbf.append(" and d.warehouseName = ? ");
		}
		if (order.getWorkStatus() != 0) {
			sbf.append(" and a.workStatus = ? ");
		}
		if (order.getClientName() != null) {
			flag = 1;
			sbf.append(" and orderId in ( select newOrderId from neworder where clientId in (select clientId from userinfo where clientName = ? ");
		}
		if (order.getClientPhone() != null) {
			if (flag == 0) {
				sbf.append(" and orderId in ( select newOrderId from neworder where clientId in (select clientId from userinfo where clientMobilephone = ? ");
				flag = 1;
			}else if (flag == 1) {
				sbf.append(" and clientMobilephone = ? ");
			}
		}
		if (flag==1) {
			sbf.append(" ) )");
		}
		System.out.println("sql:"+sbf);
		try {
			ps = conn.prepareStatement(sbf.toString());
			int index = 1;
			if (order.getWorkId() != 0) {
				ps.setInt(index, order.getWorkId());
				index++;
			}
			if (order.getCreateDate() != null) {
				Date date = new Date(order.getCreateDate().getTime());
				ps.setDate(index,  date);
				index++;
			}
			if (order.getRequireDate() != null) {
				Date date = new Date(order.getRequireDate().getTime());
				ps.setDate(index,  date);
				index++;
			}
			if (order.getWarehouseName() != null) {
				ps.setString(index, order.getWarehouseName());
				index++;
			}
			if (order.getWorkStatus() != 0) {
				ps.setInt(index, order.getWorkStatus());
				index++;
			}
			if (order.getClientName() != null) {
				ps.setString(index, order.getClientName());
				index++;
			}
			if (order.getClientPhone() != null) {
				ps.setString(index, order.getClientPhone());
				index++;
			}
			
			ResultSet rs = ps.executeQuery();
			
			if (order.getWorkType() != 2) {
				while (rs.next()) {
					WorkOrder o = new WorkOrder();
					o.setWorkId(rs.getInt("workId"));
					o.setWarehouseName(rs.getString("warehouseName"));
					o.setClientName(rs.getString("clientName"));
					o.setClientPhone(rs.getString("clientMobilephone"));
					o.setWorkStatus(rs.getInt("workStatus"));
					o.setWorkType(rs.getInt("workType"));
					o.setCreateDate(rs.getDate("createDate"));
					o.setRequireDate(rs.getDate("requireDate"));
					o.setRemark(rs.getString("remark"));
					orders.add(o);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		sbf = new StringBuffer("");
		sbf.append("select a.*,b.clientName,b.clientMobilephone,d.warehouseName "
				+ "from workorder a,userinfo b,returnorder c,warehouse d,neworder e "
				+ "where a.orderId=c.returnOrderId and c.newOrderId = e.newOrderId and b.clientId=e.clientId "
				+ "and d.warehouseId=a.warehouseId and a.workType=2");
		flag = 0;
		if (order.getWorkId() != 0) {
			sbf.append(" and a.workId = ? ");
		}
		if (order.getCreateDate() != null) {
			sbf.append(" and a.createDate = ? ");
		}
		if (order.getRequireDate() != null) {
			sbf.append(" and a.requireDate = ? ");
		}
		if (order.getWarehouseName() != null) {
			sbf.append(" and d.warehouseName = ? ");
		}
		if (order.getWorkStatus() != 0) {
			sbf.append(" and a.workStatus = ? ");
		}
		if (order.getClientName() != null) {
			flag = 1;
			sbf.append(" and orderId in ( select orderId from returnorder where returnOrderId in (SELECT newOrderId from neworder where clientId in (select clientId from userinfo where clientName = ? ");
		}
		if (order.getClientPhone() != null) {
			if (flag == 0) {
				sbf.append(" and orderId in ( select orderId from returnorder where returnOrderId in (SELECT newOrderId from neworder where clientId in (select clientId from userinfo where clientMobilephone = ? ");
				flag = 1;
			}else if (flag == 1) {
				sbf.append(" and clientMobilephone = ? ");
			}
		}
		if (flag==1) {
			sbf.append(" )))");
		}
		System.out.println("sql:"+sbf);
		try {
			ps = conn.prepareStatement(sbf.toString());
			int index = 1;
			if (order.getWorkId() != 0) {
				ps.setInt(index, order.getWorkId());
				index++;
			}
			if (order.getCreateDate() != null) {
				Date date = new Date(order.getCreateDate().getTime());
				ps.setDate(index,  date);
				index++;
			}
			if (order.getRequireDate() != null) {
				Date date = new Date(order.getRequireDate().getTime());
				ps.setDate(index,  date);
				index++;
			}
			if (order.getWarehouseName() != null) {
				ps.setString(index, order.getWarehouseName());
				index++;
			}
			if (order.getWorkStatus() != 0) {
				ps.setInt(index, order.getWorkStatus());
				index++;
			}
			if (order.getClientName() != null) {
				ps.setString(index, order.getClientName());
				index++;
			}
			if (order.getClientPhone() != null) {
				ps.setString(index, order.getClientPhone());
				index++;
			}
			
			ResultSet rs = ps.executeQuery();
			
			if (order.getWorkType() != 1) {
				while (rs.next()) {
					WorkOrder o = new WorkOrder();
					o.setWorkId(rs.getInt("workId"));
					o.setWarehouseName(rs.getString("warehouseName"));
					o.setClientName(rs.getString("clientName"));
					o.setClientPhone(rs.getString("clientMobilephone"));
					o.setWorkStatus(rs.getInt("workStatus"));
					o.setWorkType(rs.getInt("workType"));
					o.setCreateDate(rs.getDate("createDate"));
					o.setRequireDate(rs.getDate("requireDate"));
					o.setRemark(rs.getString("remark"));
					orders.add(o);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		List<WorkOrder> l = new ArrayList<>();
		for (int i = (page-1)*pageSize; i < page*pageSize; i++) {
			if (i>=orders.size()) {
				break;
			}
			l.add(orders.get(i));
		}
		return l;
	}

	@Override
	public List<WorkOrder> searchWorkOrderWithProduct(int workId) {
		List<WorkOrder> orders = new ArrayList<WorkOrder>();
		PreparedStatement ps = null;
		StringBuffer sbf = new StringBuffer("");
		sbf.append("select a.*,b.returnQuantity,d.productName,d.productUnit,d.productCode "
				+ "from workorder a,returnorder b,neworder c,product d "
				+ "where workType = 2 and b.returnOrderId = a.orderId and b.newOrderId = c.newOrderId and d.productId = c.productId "
				+ " and workId=?;");
		System.out.println(sbf);
		try {
			ps = conn.prepareStatement(sbf.toString());
			ps.setInt(1, workId);
			ResultSet rs = ps.executeQuery();
			
			
				while (rs.next()) {
					WorkOrder o = new WorkOrder();
					o.setWorkId(rs.getInt("workId"));
					o.setWorkStatus(rs.getInt("workStatus"));
					o.setWorkType(rs.getInt("workType"));
					o.setCreateDate(rs.getDate("createDate"));
					o.setRequireDate(rs.getDate("requireDate"));
					o.setRemark(rs.getString("remark"));
					o.setProductName(rs.getString("productName"));
					o.setProductQuantity(rs.getInt("returnQuantity"));
					o.setProductCode(rs.getString("productCode"));
					o.setProductUnit(rs.getString("productUnit"));
					orders.add(o);
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return orders;
	}

	
	//鏌ヨ鎸囧畾椤电爜鐨勬暟鎹�
	@Override
	public List<WorkOrder> selectPageWork(java.util.Date requireDate, int workStatus, int workType, int pageNum) {
		List<WorkOrder> list = new ArrayList<WorkOrder>();
		int pageSize = 5;//鍥哄畾
		StringBuffer sbf = new StringBuffer("");//鐢ㄢ�溾�濆紑澶�
		sbf.append(" select * from workorder where 1=1 ");
		if(requireDate != null){
			sbf.append(" and requireDate=? ");
		}
		if(workStatus != 0){
			sbf.append(" and workStatus=? ");
		}
		if(workType != 0){
			sbf.append(" and workType=? ");
		}
		
		//鏌ヨ锛屾瀯寤簊ql
		try {
			PreparedStatement ps = conn.prepareStatement(" select * from ( "
					+ " select @rownum:=@rownum+1 as rownum, a.*, b.total, b.receiverName, b.receiverPhone, b.receiverAddress, b.productQuantity, d.productCode, d.productUnit "
					+ " from (select @rownum:=0) t, ( "+ sbf.toString() +" ) as a, neworder as b, product as d "
					+ " where a.orderId=b.newOrderId and b.productId=d.productId "
					+ " ) as c "
					+ " where c.rownum <= "+ pageSize*pageNum +" and c.rownum >"+ pageSize*(pageNum-1)
					);
			int index = 1;
			if(requireDate != null){
				Date newRequireDate = new Date(requireDate.getTime());
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
			//鎵ц
			ResultSet rs = ps.executeQuery();//鎵ц鏌ヨ锛岃繑鍥炵粨鏋滈泦
			while(rs.next()){
				WorkOrder workOrder = new WorkOrder();
				
				workOrder.setWorkId(rs.getInt("workId"));
				workOrder.setWorkType(rs.getInt("workType"));
				workOrder.setWorkStatus(rs.getInt("workStatus"));
				workOrder.setRequireDate(rs.getDate("requireDate"));
				workOrder.setTotalAmount(rs.getFloat("total"));//璁㈠崟鐨則otal
				workOrder.setRemark(rs.getString("remark"));
				
				workOrder.setWarehouseId(rs.getInt("warehouseId"));
				workOrder.setDeliveryStaffId(rs.getInt("deliveryStaffId"));//workorder鍘熸湰灏辨湁鐨�
				
				workOrder.setClientName(rs.getString("receiverName"));
				workOrder.setClientPhone(rs.getString("receiverPhone"));
				workOrder.setClientAddress(rs.getString("receiverAddress"));//璁㈠崟鐨剅eceiverAddress
				
				workOrder.setProductUnit(rs.getString("productUnit"));
				workOrder.setProductQuantity(rs.getInt("productQuantity"));
				workOrder.setProductCode(rs.getString("productCode"));
				
				list.add(workOrder);//娣诲姞鏌ヨ鍒扮殑鏁版嵁
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;//杩斿洖list
	}

	//鏁版嵁鐨勯〉鏁�
	@Override
	public int selectPageCount(java.util.Date requireDate, int workStatus, int workType) {
		List<WorkOrder> list = new ArrayList<WorkOrder>();
		int pageSize = 5;//鍥哄畾
		int count = 0;
		StringBuffer sbf = new StringBuffer("");//鐢ㄢ�溾�濆紑澶�
		sbf.append(" select count(*) cc from workorder where 1=1 ");
		if(requireDate != null){
			sbf.append(" and requireDate=? ");
		}
		if(workStatus != 0){
			sbf.append(" and workStatus=? ");
		}
		if(workType != 0){
			sbf.append(" and workType=? ");
		}
		//鏋勫缓sql璇彞
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sbf.toString());
			
			int index = 1;
			if(requireDate != null){
				Date newRequireDate = new Date(requireDate.getTime());
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
			//鎵ц璇彞
			ResultSet rs = ps.executeQuery();
			if(rs.next()){//璇存槑璧风爜鏈変竴涓�
				count = rs.getInt("cc");//鑾峰緱鏁版嵁鎬绘暟
			}
			System.out.println("count::"+count);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		int pagecount = 0;
		if(count % pageSize == 0){
			pagecount = count / pageSize;
		}else{
			pagecount = count / pageSize + 1;
		}
		System.out.println("pagecount+++++:"+pagecount);
		return pagecount;
	}

	@Override
	public List<WarehouseNameInfo> getWarehosueInfo() {
		List<WarehouseNameInfo> orders = new ArrayList<WarehouseNameInfo>();
		PreparedStatement ps = null;
		StringBuffer sbf = new StringBuffer("");
		sbf.append("select * from warehouse where warehouseRank=0 ");
		
		System.out.println("sql: "+sbf);
		try {
			ps = conn.prepareStatement(sbf.toString());
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				WarehouseNameInfo o = new WarehouseNameInfo();
				o.setWarehouseName(rs.getString("warehouseName"));
				
				orders.add(o);
				
			}
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return orders;
	}

}
