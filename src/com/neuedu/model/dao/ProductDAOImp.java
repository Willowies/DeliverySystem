package com.neuedu.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neuedu.model.po.Product;


public class ProductDAOImp implements ProductDAO {
	private Connection conn;
	public ProductDAOImp(Connection conn) {
		this.conn = conn;
	}
	public List<Product> selectProduct(Product p){
		List<Product> products = new ArrayList<Product>();
		StringBuffer sbf = new StringBuffer("");
		String productName = p.getProductName();
		int firstClassId= p.getFirstClassId();
		int secondClassId = p.getSecondClassId();
		sbf.append("select *  from  product where 1=1  ");
		if(productName != null && !"".equals(productName)){
			sbf.append(" and productName=?");
		}
		if(firstClassId != 0){
			sbf.append(" and firstclassid=?");
		}
		if(secondClassId != 0){
			sbf.append(" and secondclassid=?");
		}
		sbf.append(";");
		
		try {
			PreparedStatement ps = conn.prepareStatement(sbf.toString());
			int index=1;
			if(productName != null && !"".equals(productName)){
				ps.setString(index, productName);
				index++;
			}
			if(firstClassId != 0){
				ps.setInt(index, firstClassId);
				index++;
			}
			if(secondClassId != 0){
				ps.setInt(index, secondClassId);
				index++;
			}
			//System.out.println(ps);
			//执行
			//统计获得数据数
			
			ResultSet rs = ps.executeQuery();
			//封装获取的数据
			/*while (rs.next()) {
				client.setClientContactAddress(rs.getString("ClientContactAddress"));
				client.setClientEmail(rs.getString("ClientEmail"));
				client.setClientIc(rs.getString("ClientIc"));
				client.setClientId(rs.getInt("ClientId"));
				client.setClientMobileNumber(rs.getString("clientmobilephone"));
				client.setClientName(rs.getString("ClientName"));
				client.setClientPhoneNumber(rs.getString("ClientPhoneNumber"));
				client.setClientPostCode(rs.getInt("ClientPostCode"));
			}*/
			System.out.println(ps);
			while (rs.next()) {
				Product product = new Product();
				product.setProductId(rs.getInt("productId"));
				product.setProductName(rs.getString("productName"));
				product.setFirstClassId(rs.getInt("firstClassId"));
				product.setSecondClassId(rs.getInt("secondclassid"));
				product.setProductPrice(rs.getFloat("productprice"));
				product.setProductDiscount(rs.getFloat("productprice"));
				products.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return products;
	}
}
