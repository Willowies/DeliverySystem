package com.neuedu.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;


import com.neuedu.model.po.FirstClass;
import com.neuedu.model.po.Product;
import com.neuedu.model.po.Supplier;
import com.neuedu.utils.DBUtil;

public class FirstClassDAOImp  implements FirstClassDAO{

	Connection conn;
	
	public FirstClassDAOImp(Connection conn) {
		this.conn = conn;
	}
	
	
	//添加一级分类
	@Override
	public void addFirstClass(FirstClass firstclass)throws SQLException {
		PreparedStatement ps = null;
		
		/*StringBuffer sbf = new StringBuffer("");
		sbf.append("select * from firstClass where firstClassName=");
		sbf.append(firstclass.getFirstClassName());
		sbf.append(" and status=1 ");
		ps = conn.prepareStatement(sbf.toString());
		ResultSet rs = ps.executeQuery();*/
		System.out.println(firstclass.getFirstClassDescription());
			try {
				
				    String sql ="insert into firstClass(firstClassName,firstClassDescription,status,operator,operateDate) values (?,?,?,?,?)";
					ps = conn.prepareStatement(sql);
					/*firstclassId 在数据库中自增，不用传*/
					
					ps.setString(1,firstclass.getFirstClassName());
					System.out.println(firstclass.getFirstClassDescription());
					ps.setString(2,firstclass.getFirstClassDescription());
					ps.setInt(3,firstclass.getStatus());
					ps.setString(4, firstclass.getOperator());
					ps.setDate(5,firstclass.getOperateDate());
					
					ps.executeUpdate();
					System.out.println(firstclass.getFirstClassName());
			
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}finally {
				DBUtil.closePS(ps);

			}
		
		
		
	}

	//修改一级分类
	@Override
	public void editFirstClass(FirstClass firstclass) throws SQLException {
		    PreparedStatement ps = null;
	        try {
				
				ps = conn.prepareStatement("update firstClass set firstClassName=?,firstClassDescription=?,status=?,operator=?,operateDate=? "+"where firstClassId=?");
				ps.setString(1, firstclass.getFirstClassName());
				ps.setString(2,firstclass.getFirstClassDescription());
				ps.setInt(3,firstclass.getStatus());
				ps.setString(4, firstclass.getOperator());
				ps.setDate(5,firstclass.getOperateDate());
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}finally {
				DBUtil.closePS(ps);
			}
		
	
	
	
	}
	//通过id删除一级分类

	//删除一级分类
	@Override
	public void deleteFirstClass(int firstClassId,String  operator,Date operateDate ) throws SQLException {
		
	
		PreparedStatement ps = null;
		StringBuffer sbf = new StringBuffer("");
		sbf.append("select  from secondClass where firstClassId=");
		sbf.append(firstClassId);
		sbf.append("except select from secondClass where status=0;");
		ps = conn.prepareStatement(sbf.toString());
		ResultSet rs = ps.executeQuery();
		try {
			if(false==rs.next()) {
				
				ps = conn.prepareStatement("update firstClass set status = 0,operator = ?,operateDate = ? where firstClassId = ?");
				ps.setString(1, operator);
				ps.setDate(2, new java.sql.Date(operateDate.getTime()));
				ps.setInt(3, firstClassId);
				ps.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.closePS(ps);
		}
		
		
		
		
	}
	//通过一级分类名查询一级分类
	@Override
	public List<FirstClass> selectFirstClass(String firstclassname , int firstclassid){
		StringBuffer sbf = new StringBuffer("");
		List<FirstClass> list = new ArrayList<FirstClass>();
		sbf.append("select * from firstClass where status=1 ");
		
		if (firstclassname != null && !"".equals(firstclassname)) {
			sbf.append(" and firstClassName=?");
			}
		if(firstclassid !=0)
		{
			sbf.append("and firstClassId=?");
		}
		try {
			PreparedStatement ps = conn.prepareStatement(sbf.toString());
			int index = 1;
			if (firstclassname != null && !"".equals(firstclassname)) {
				ps.setString(index, firstclassname);
				index++;
			}
			if(firstclassid !=0){
				ps.setInt(index, firstclassid);
				index++;
			}
			
			
			ResultSet rs = ps.executeQuery();
				while (rs.next()) {
				FirstClass firstclass = new FirstClass();
				firstclass.setFirstClassId(rs.getInt("firstClassId"));
				firstclass.setFirstClassName(rs.getString("firstClassName"));
				firstclass.setFirstClassDescription(rs.getString("firstClassDescription"));
				firstclass.setStatus(rs.getInt("status"));
				firstclass.setOperator(rs.getString("operator"));
				firstclass.setOperateDate(rs.getDate("operateDate"));
				list.add(firstclass);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//查询一级分类的页数
	@Override
	public int selectfirstClassPageCount(String firstclassname) {
		int count = 0;
		StringBuffer sbf = new StringBuffer("");
		sbf.append("  select count(*) cc  from  firstClass where status=1  ");
		if (firstclassname != null && !"".equals(firstclassname)) {
			sbf.append(" and firstClassName=? ");
		}
		try {
			PreparedStatement ps = conn.prepareStatement(sbf.toString());
			int index = 1;
			if (firstclassname != null && !"".equals(firstclassname)) {
				ps.setString(index, firstclassname);
			}

			// ִ��
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				count = rs.getInt("cc");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		int pagecount = 0;
		if (count % 5 == 0) {
			pagecount = count / 5;
		} else {
			pagecount = count / 5 + 1;
		}
		return pagecount;

	}
	//通过id查询一级
	@Override
	public FirstClass getfistClassById(int firstclassid) {
		FirstClass firstclass = new FirstClass();
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement("  select *  from firstClass  where fristClassId=? and status=0");
			ps.setInt(1, firstclassid);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				
				firstclass.setFirstClassId(rs.getInt("firstClassId"));
				firstclass.setFirstClassName(rs.getString("firstClassName"));
				firstclass.setFirstClassDescription(rs.getString("firstClassDescription"));
				firstclass.setStatus(rs.getInt("status"));
				firstclass.setOperator(rs.getString("operator"));
				firstclass.setOperateDate(rs.getDate("operateDate"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return firstclass;
	}
	//validate函数
	@Override
	public FirstClass validatefirstClassName(String firstClassName) {
		FirstClass fc = null;
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement("  select *  from firstClass  where firstClassName=? ");
			ps.setString(1, firstClassName);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				fc = new FirstClass();
				fc.setFirstClassId(rs.getInt("firstclassid"));
				fc.setFirstClassName(rs.getString("firstclassname"));
				fc.setFirstClassDescription(rs.getString("firstclassdescription"));
				fc.setStatus(rs.getInt("status"));
				fc.setOperator(rs.getString("operator"));
				fc.setOperateDate(rs.getDate("operatedate"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fc;
	}
	//根据一级分类id查询商品
	@Override
	public List<Product> selectProduct(int firstclassid){
		StringBuffer sbf = new StringBuffer("");
		List<Product> list = new ArrayList<Product>();
		
		sbf.append("select * from Product where 1=1 and status=1 ");
		
		if (firstclassid != 0 && !"".equals(firstclassid)) {
			sbf.append(" and firstClassId=? ");
		}
		try {
			PreparedStatement ps = conn.prepareStatement(sbf.toString());
			int index = 1;
			if (firstclassid != 0 && !"".equals(firstclassid)) {
		        ps.setInt(index, firstclassid);
			// ִ��
			ResultSet rs = ps.executeQuery();
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
				
				//supplier�������
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
				list.add(product);
			  }
		}
	}catch (SQLException e) {
		e.printStackTrace();
	}
	return list;
}
	//查询商品的页数	
	@Override
	public int selectProductPageCount(int firstclassid) {
		int count = 0;
		StringBuffer sbf = new StringBuffer("");
		sbf.append("  select count(*) cc  from  Product where status=1  ");
		if (firstclassid != 0 && !"".equals(firstclassid)) {
			sbf.append(" and firstClassId=？");
		}
		try {
			PreparedStatement ps = conn.prepareStatement(sbf.toString());
			int index = 1;
			if (firstclassid != 0 && !"".equals(firstclassid)) {
				ps.setInt(index, firstclassid);
			}

			// ִ��
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				count = rs.getInt("cc");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		int pagecount = 0;
		if (count % 5 == 0) {
			pagecount = count / 5;
		} else {
			pagecount = count / 5 + 1;
		}
		return pagecount;

	}
	
	@Override
    public int selectfirstClassIdByfirstClassName(String firstclassname) {
    	StringBuffer sbf = new StringBuffer("");
    	int firstclassid = 0;
		sbf.append("  select firstClassId from  firstClass where status=1  ");
		if (firstclassname != null && !"".equals(firstclassname)) {
			sbf.append(" and firstClassName=？");
		}
		
		try {
			PreparedStatement ps = conn.prepareStatement(sbf.toString());
			int index = 1;
			if (firstclassname != null && !"".equals(firstclassname)) {
				ps.setString(index, firstclassname);
			}
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				firstclassid = rs.getInt("firstClassId");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return firstclassid;
    	
		
    	
    }

}
