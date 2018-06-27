package com.neuedu.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.neuedu.model.po.Product;
import com.neuedu.model.po.Supplier;



public class ProductDAOImp implements ProductDAO {
	private Connection conn;
	public ProductDAOImp(Connection conn) {
		this.conn = conn;
	}
	public List<Product> selectProduct(Product p){
		List<Product> products = new ArrayList<Product>();
		int productId = p.getProductId();
		String productCode = p.getProductCode();
		String productName = p.getProductName();
		int firstClassId= p.getFirstClassId();
		int secondClassId = p.getSecondClassId();
		String productUnit = p.getProductUnit();
		float productPrice = p.getProductPrice();
		float productDiscount = p.getProductDiscount();
		float productCost = p.getProductCost();
		String productModel = p.getProductModel();
		//Suppllier supplier = new Suppllier();
		int supId = p.getSupplier().getSupId();
		String manufacturer = p.getManufacturer();
		Date expirationDate = p.getExpirationDate();
		int returnAble = p.getReturnAble();
		String remark = p.getRemark();
		int status = p.getStatus();
		String operator = p.getOperator();
		Date operateDate = p.getOperateDate();
		
		StringBuffer sbf = new StringBuffer("");
		sbf.append("select *  from  product left join supplierinfo using(supid) where 1=1   ");
		if(productId != 0){
			sbf.append(" and productId=?");
		}
		if(productCode != null && !"".equals(productCode)){
			sbf.append(" and productCode=?");
		}
		if(productName != null && !"".equals(productName)){
			sbf.append(" and productName=?");
		}
		if(firstClassId != 0){
			sbf.append(" and firstclassid=?");
		}
		if(secondClassId != 0){
			sbf.append(" and secondclassid=?");
		}
		if( productUnit != null && !"".equals( productUnit)){
			sbf.append(" and  productUnit=?");
		}
		if(productPrice != 0){
			sbf.append(" and productPrice=?");
		}
		if(productDiscount != 0){
			sbf.append(" and productDiscount=?");
		}
		if(productCost != 0){
			sbf.append(" and productCost=?");
		}
		if(productModel != null && !"".equals(productModel)){
			sbf.append(" and productModel=?");
		}
		if(supId != 0){
			sbf.append(" and supId=?");
		}
		if(manufacturer  != null && !"".equals(manufacturer )){
			sbf.append(" and manufacturer =?");
		}
		if(expirationDate != null){
			sbf.append(" and expirationDate=?");
		}
		if(returnAble != 0){
			sbf.append(" and returnAble=?");
		}
		if(remark != null && !"".equals(remark)){
			sbf.append(" and remark=?");
		}
		if(status != 0){
			sbf.append(" and status=?");
		}
		if(operator != null && !"".equals(operator)){
			sbf.append(" and operator=?");
		}
		if(operateDate != null){
			sbf.append(" and operateDate=?");
		}
		
		sbf.append(";");
		
		try {
			PreparedStatement ps = conn.prepareStatement(sbf.toString());
			int index=1;
			if(productId != 0){
				ps.setInt (index, productId);
				index++;
			}
			if(productCode != null && !"".equals(productCode)){
				ps.setString(index,productCode);
				index++;
			}
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
			if( productUnit != null && !"".equals( productUnit)){
				ps.setString(index, productUnit);
				index++;
			}
			if(productPrice != 0){
				ps.setFloat(index, productPrice);
				index++;
			}
			if(productDiscount != 0){
				ps.setFloat(index, productDiscount);
				index++;
			}
			if(productCost != 0){
				ps.setFloat(index, productCost);
				index++;
			}
			if(productModel != null && !"".equals(productModel)){
				ps.setString(index, productModel);
				index++;
			}
			if(supId != 0){
				ps.setInt(index, supId);
				index++;
			}
			if(manufacturer  != null && !"".equals(manufacturer )){
				ps.setString(index, manufacturer);
				index++;
			}
			if(expirationDate != null){
				ps.setDate(index, new java.sql.Date(expirationDate.getTime()));
				index++;
			}
			if(returnAble != 0){
				ps.setInt(index, returnAble);
				index++;
			}
			if(remark != null && !"".equals(remark)){
				ps.setString(index, remark);
				index++;
			}
			if(status != 0){
				ps.setInt(index, status);
				index++;
			}
			if(operator != null && !"".equals(operator)){
				ps.setString(index, operator);
				index++;
			}
			if(operateDate != null){
				ps.setDate(index, new java.sql.Date(operateDate.getTime()));
				index++;
			}
			//System.out.println(ps);
			//执行
			ResultSet rs = ps.executeQuery();
			//封装获取的数据
			System.out.println(ps);
			while (rs.next()) {
				Product product = new Product();
				product.setProductId(rs.getInt("productId"));
				product.setProductCode(rs.getString("productCode"));
				product.setProductName(rs.getString("productName"));
				product.setFirstClassId(rs.getInt("firstClassId"));
				product.setSecondClassId(rs.getInt("secondclassid"));
				product.setProductUnit(rs.getString("productunit"));
				product.setProductPrice(rs.getFloat("productprice"));
				product.setProductDiscount(rs.getFloat("productDiscount"));
				product.setProductCost(rs.getFloat("productCost"));
				
				//supplier类待完善
				Supplier supplier = new Supplier();
				supplier.setSupId(rs.getInt("supId"));
				product.setSupplier(supplier);
				
				
				product.setManufacturer(rs.getString("manufacturer"));
				product.setExpirationDate(rs.getDate("expirationDate"));
				product.setReturnAble(rs.getInt("returnAble"));
				product.setRemark(rs.getString("remark"));
				product.setStatus(rs.getInt("status"));
				product.setOperator(rs.getString("operator"));
				product.setOperateDate(rs.getDate("operateDate"));
				products.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return products;
	}
	@Override
	public void updateProduct(Product p) {
		int productId = p.getProductId();
		String productCode = p.getProductCode();
		String productName = p.getProductName();
		int firstClassId= p.getFirstClassId();
		int secondClassId = p.getSecondClassId();
		String productUnit = p.getProductUnit();
		float productPrice = p.getProductPrice();
		float productDiscount = p.getProductDiscount();
		float productCost = p.getProductCost();
		String productModel = p.getProductModel();
		int supId = p.getSupplier().getSupId();
		String manufacturer = p.getManufacturer();
		Date expirationDate = p.getExpirationDate();
		int returnAble = p.getReturnAble();
		String remark = p.getRemark();
		int status = p.getStatus();
		String operator = p.getOperator();
		Date operateDate = p.getOperateDate();
		
		StringBuffer sbf = new StringBuffer("");
		sbf.append("update product  set  ");
		if(productCode != null && !"".equals(productCode)){
			sbf.append(" productCode=?");
		}
		if(productName != null && !"".equals(productName)){
			sbf.append(" ,productName=?");
		}
		if(firstClassId != 0){
			sbf.append(" ,firstclassid=?");
		}
		if(secondClassId != 0){
			sbf.append(" ,secondclassid=?");
		}
		if( productUnit != null && !"".equals( productUnit)){
			sbf.append(" ,productUnit=?");
		}
		if(productPrice != 0){
			sbf.append(" ,productPrice=?");
		}
		if(productDiscount != 0){
			sbf.append(" ,productDiscount=?");
		}
		if(productCost != 0){
			sbf.append(" ,productCost=?");
		}
		if(productModel != null && !"".equals(productModel)){
			sbf.append(" ,productModel=?");
		}
		if(supId != 0){
			sbf.append(" ,supId=?");
		}
		if(manufacturer  != null && !"".equals(manufacturer )){
			sbf.append(" ,manufacturer =?");
		}
		if(expirationDate != null){
			sbf.append(" ,expirationDate=?");
		}
		if(returnAble != 0){
			sbf.append(" ,returnAble=?");
		}
		if(remark != null && !"".equals(remark)){
			sbf.append(" ,remark=?");
		}
		if(status != 0){
			sbf.append(" ,status=?");
		}
		if(operator != null && !"".equals(operator)){
			sbf.append(" ,operator=?");
		}
		if(operateDate != null){
			sbf.append(" ,operateDate=?");
		}
		if(productId != 0){
			sbf.append(" where productId=?");
		}
		sbf.append(";");
		
		try {
			PreparedStatement ps = conn.prepareStatement(sbf.toString());
			int index=1;
			if(productId != 0){
				ps.setInt (index, productId);
				index++;
			}
			if(productCode != null && !"".equals(productCode)){
				ps.setString(index,productCode);
				index++;
			}
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
			if(secondClassId != 0){
				ps.setInt(index, secondClassId);
				index++;
			}
			if( productUnit != null && !"".equals( productUnit)){
				ps.setString(index, productUnit);
				index++;
			}
			if(productPrice != 0){
				ps.setFloat(index, productPrice);
				index++;
			}
			if(productDiscount != 0){
				ps.setFloat(index, productDiscount);
				index++;
			}
			if(productCost != 0){
				ps.setFloat(index, productCost);
				index++;
			}
			if(productModel != null && !"".equals(productModel)){
				ps.setString(index, productModel);
				index++;
			}
			if(supId != 0){
				ps.setInt(index, supId);
				index++;
			}
			if(manufacturer  != null && !"".equals(manufacturer )){
				ps.setString(index, manufacturer);
				index++;
			}
			if(expirationDate != null){
				ps.setDate(index, new java.sql.Date(expirationDate.getTime()));
				index++;
			}
			if(returnAble != 0){
				ps.setInt(index, returnAble);
				index++;
			}
			if(remark != null && !"".equals(remark)){
				ps.setString(index, remark);
				index++;
			}
			if(status != 0){
				ps.setInt(index, status);
				index++;
			}
			if(operator != null && !"".equals(operator)){
				ps.setString(index, operator);
				index++;
			}
			if(operateDate != null){
				ps.setDate(index, new java.sql.Date(operateDate.getTime()));
				index++;
			}
			//System.out.println(ps);
			//执行
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public void createProduct(Product p) {
		String productCode = p.getProductCode();
		String productName = p.getProductName();
		int firstClassId= p.getFirstClassId();
		int secondClassId = p.getSecondClassId();
		String productUnit = p.getProductUnit();
		float productPrice = p.getProductPrice();
		float productDiscount = p.getProductDiscount();
		float productCost = p.getProductCost();
		String productModel = p.getProductModel();
		int supId = p.getSupplier().getSupId();
		String manufacturer = p.getManufacturer();
		Date expirationDate = p.getExpirationDate();
		int returnAble = p.getReturnAble();
		String remark = p.getRemark();
		int status = p.getStatus();
		String operator = p.getOperator();
		Date operateDate = p.getOperateDate();
		
		
		StringBuffer sbf = new StringBuffer("");
		sbf.append("insert into product values (null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
		
		try {
			PreparedStatement ps = conn.prepareStatement(sbf.toString());
			ps.setString(1,productCode);
			ps.setString(2, productName);
			ps.setInt(3, firstClassId);
			ps.setInt(4, secondClassId);
			ps.setString(5, productUnit);
			ps.setFloat(6, productPrice);
			ps.setFloat(7, productDiscount);
			ps.setFloat(8, productCost);
			ps.setString(9, productModel);
			ps.setInt(10, supId);
			ps.setString(11, manufacturer);
			ps.setDate(12, new java.sql.Date(expirationDate.getTime()));
			ps.setInt(13, returnAble);
			ps.setString(14, remark);
			ps.setInt(15, status);
			ps.setString(16, operator);
			ps.setDate(17, new java.sql.Date(operateDate.getTime()));

			//System.out.println(ps);
			//执行
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public void deleteProduct(Product p) {
		int productId = p.getProductId();
		String operator = p.getOperator();
		Date operateDate = p.getOperateDate();
		
		StringBuffer sbf = new StringBuffer("");
		sbf.append("update product set status =0");
		if(operator != null && !"".equals(operator)){
			sbf.append(" ,operator=?");
		}
		if(operateDate != null){
			sbf.append(" ,operateDate=?");
		}
		if(productId != 0){
			sbf.append(" where productId=?");
		}
		
		try {
			PreparedStatement ps = conn.prepareStatement(sbf.toString());
			ps.setString(1, operator);
			ps.setDate(2, new java.sql.Date(operateDate.getTime()));
			ps.setInt(3, productId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
