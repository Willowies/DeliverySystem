package com.neuedu.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neuedu.model.po.DeliveryStockProduct;
import com.neuedu.model.po.SubReturnRecord;

public class DeliveryProductDAOImp implements DeliveryProductDAO {
	
	Connection conn;
	
	public DeliveryProductDAOImp(Connection conn){
		this.conn = conn;
	}

	@Override
	public List<DeliveryStockProduct> searchStockProduct(String productName) {
		List<DeliveryStockProduct> orders = new ArrayList<DeliveryStockProduct>();
		PreparedStatement ps = null;
		StringBuffer sbf = new StringBuffer("");
		sbf.append("select a.*,b.*,sum(c.productQuantity) q,f.firstClassName,s.secondClassName "
				+ " from firstclass f,secondclass s,warehouseproduct a,product b left join neworder as c  on c.productId = b.productId and c.orderState=3"
				+ " where b.productName like \"%?%\" and a.productId=b.productId and warehouseId in (select warehouseId from warehouse where warehouseRank=1)"
				+ "  and f.firstClassId=b.firstClassId and s.secondClassId=b.secondClassId;");
		
		System.out.println("sql: "+sbf);
		try {
			ps = conn.prepareStatement(sbf.toString());
			if (productName == null) {
				productName = "";
			}
			ps.setString(1, productName);
			
			
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				DeliveryStockProduct o = new DeliveryStockProduct();
				o.setProductId(rs.getInt("productId"));
				o.setFirstClass(rs.getString("firstClassName"));
				o.setSecondClass(rs.getString("secondClassName"));
				o.setProductName(rs.getString("productName"));
				o.setProductCode(rs.getString("productCode"));
				o.setProductUnit(rs.getString("productUnit"));
				o.setNow(rs.getInt("productQuantity"));
				o.setWarning(rs.getInt("warningValue"));
				int lack = rs.getInt(rs.getInt("p"));
				int w = rs.getInt("warningValue");
				int n = rs.getInt("productQuantity");
				if (n<w) {
					lack += w-n;
				}
				o.setLack(lack);
				orders.add(o);
				
			}
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return orders;
	}
	
	
}
