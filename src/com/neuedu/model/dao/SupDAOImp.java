package com.neuedu.model.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.neuedu.model.po.Client;
import com.neuedu.model.po.Employee;
import com.neuedu.model.po.Supplier;
import com.neuedu.utils.DBUtil;

public class SupDAOImp implements SupDAO {
	Connection conn;

	public SupDAOImp(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void registerSup(Supplier sup, Employee e) throws SQLException {
		
		PreparedStatement ps = null;
		// 获取当前时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		String currentDate = df.format(new Date());
		try {
			String sql = "insert into supplierinfo(supName,supAddress,supLinkman,"
					+ "supPhone,supBankName,supBankAccount,supFax,"
					+ "supPostcode,supLegalPerson,supRemark,status,operate,operateDate)"
		            + "values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, sup.getSupName());
			ps.setString(2, sup.getSupAddress());
			ps.setString(3, sup.getSupLinkman());
			ps.setString(4, sup.getSupPhone());
			ps.setString(5, sup.getSupBankName());
			ps.setInt(6, sup.getSupBankAccount());
			ps.setInt(7, sup.getSupFax());
			ps.setInt(8, sup.getSupPostcode());
			ps.setString(9, sup.getSupLegalPerson());
			ps.setString(10, sup.getSupRemark());
			
			ps.setInt(11, 1);
			ps.setString(12, e.getEmployeeName());
			ps.setString(13, currentDate);
			
			ps.executeUpdate();
				
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			DBUtil.closePS(ps);
		}

	}

	@Override
	public void deleteSups(int[] ids,Employee e) {
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		String currentDate = df.format(new Date());
		String id = Arrays.toString(ids).replace('[', '(').replace(']', ')');
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(" update supplierinfo  set status = 0,operate=? ,operateDate=? "
				       + "  where supId in  " + id);
			ps.setString(1, e.getEmployeeName());
			ps.setString(2, currentDate);
			ps.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			DBUtil.closePS(ps);
		}

	}

	// 组合查询
	@Override
	public List<Supplier> selectSup(String supName) {
		List<Supplier> list = new ArrayList<Supplier>();
		StringBuffer sbf = new StringBuffer("");
		sbf.append("  select *  from  supplierinfo where 1=1 and status = 1  ");
		if (supName != null && !"".equals(supName)) {
			sbf.append(" and supName like ? ");
		}
		try {
			PreparedStatement ps = conn.prepareStatement(sbf.toString());
			int index = 1;
			if (supName != null && !"".equals(supName)) {
				ps.setString(index, "%" + supName+ "%");
			}
			// 执行
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				
				Supplier sup = new Supplier();
				System.out.println(rs.getInt("supId"));
				sup.setSupId(rs.getInt("supId"));
				sup.setSupName(rs.getString("supName"));
				sup.setSupAddress(rs.getString("supAddress"));
				sup.setSupLinkman(rs.getString("supLinkman"));
				sup.setSupPhone(rs.getString("supPhone"));
				sup.setSupBankName(rs.getString("supBankName"));
				
				sup.setSupBankAccount(rs.getInt("supBankAccount"));
				sup.setSupFax(rs.getInt("supFax"));
				sup.setSupPostcode(rs.getInt("supPostcode"));
				
				sup.setSupLegalPerson(rs.getString("supLegalPerson"));
				sup.setSupRemark(rs.getString("supRemark"));
				
				sup.setStatus(rs.getInt("status"));
				sup.setOperate(rs.getString("operate"));
				sup.setOperateDate(rs.getString("operateDate"));

				list.add(sup);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// 分页查询
	@Override
	public List<Supplier> selectSup(String supName,int pageNum) {
		List<Supplier> list = new ArrayList<Supplier>();
		StringBuffer sbf = new StringBuffer("");
		sbf.append("  select * from  supplierinfo where 1=1  and status = 1");
		if (supName != null && !"".equals(supName)) {
			sbf.append(" and supName like ? ");
		}

		try {
			String selectSql = sbf.toString() + " limit "+ 5 * (pageNum - 1)+","+5;
			PreparedStatement ps = conn.prepareStatement(selectSql);
			
			int index = 1;
			if (supName != null && !"".equals(supName)) {
				ps.setString(index, "%" + supName +"%");
			}

			// 执行
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Supplier sup = new Supplier();
				System.out.println(rs.getInt("supId"));
				sup.setSupId(rs.getInt("supId"));
				sup.setSupName(rs.getString("supName"));
				sup.setSupAddress(rs.getString("supAddress"));
				sup.setSupLinkman(rs.getString("supLinkman"));
				sup.setSupPhone(rs.getString("supPhone"));
				sup.setSupBankName(rs.getString("supBankName"));
				
				sup.setSupBankAccount(rs.getInt("supBankAccount"));
				sup.setSupFax(rs.getInt("supFax"));
				sup.setSupPostcode(rs.getInt("supPostcode"));
				
				sup.setSupLegalPerson(rs.getString("supLegalPerson"));
				sup.setSupRemark(rs.getString("supRemark"));
				
				sup.setStatus(rs.getInt("status"));
				sup.setOperate(rs.getString("operate"));
				sup.setOperateDate(rs.getString("operateDate"));

				list.add(sup);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// 查询页数
	@Override
	public int selectPageCount(String supName) {
		int count = 0;
		StringBuffer sbf = new StringBuffer("");
		sbf.append("  select count(*) cc  from  supplierinfo where 1=1  ");
		if (supName != null && !"".equals(supName)) {
			sbf.append(" and supName=? ");
		}
		try {
			PreparedStatement ps = conn.prepareStatement(sbf.toString());
			int index = 1;
			if (supName != null && !"".equals(supName)) {
				ps.setString(index, supName);
			}

			// 执行
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

	//按ID找到某一个具体的用户
	@Override
	public Supplier getSupById(int supId) {
		Supplier sup = new Supplier();
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement("  select *  from supplierinfo  where supId=? ");
			ps.setInt(1, supId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				System.out.println(rs.getInt("supId"));
				sup.setSupId(rs.getInt("supId"));
				sup.setSupName(rs.getString("supName"));
				sup.setSupAddress(rs.getString("supAddress"));
				sup.setSupLinkman(rs.getString("supLinkman"));
				sup.setSupPhone(rs.getString("supPhone"));
				sup.setSupBankName(rs.getString("supBankName"));
				
				sup.setSupBankAccount(rs.getInt("supBankAccount"));
				sup.setSupFax(rs.getInt("supFax"));
				sup.setSupPostcode(rs.getInt("supPostcode"));
				
				sup.setSupLegalPerson(rs.getString("supLegalPerson"));
				sup.setSupRemark(rs.getString("supRemark"));
				
				sup.setStatus(rs.getInt("status"));
				sup.setOperate(rs.getString("operate"));
				sup.setOperateDate(rs.getString("operateDate"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sup;
	}

	@Override
	public void updateSup(Supplier sup,Employee e) {
		PreparedStatement ps = null;
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		String currentDate = df.format(new Date());
		try {
			String sql = " update supplierinfo set supName=?,supAddress=?,supLinkman=?, "
					+ " supPhone=?,supBankName=?,supBankAccount=?,supFax=?,"
					+ " supPostcode=?,supLegalPerson=?,supRemark=?,operateDate=? "
		            + " where supId=? ";
			ps = conn.prepareStatement(sql);
			
			System.out.println("id:"+sup.getSupId());
			
			ps.setString(1, sup.getSupName());
			ps.setString(2, sup.getSupAddress());
			ps.setString(3, sup.getSupLinkman());
			ps.setString(4, sup.getSupPhone());
			ps.setString(5, sup.getSupBankName());
			ps.setInt(6, sup.getSupBankAccount());
			ps.setInt(7, sup.getSupFax());
			ps.setInt(8, sup.getSupPostcode());
			ps.setString(9, sup.getSupLegalPerson());
			ps.setString(10, sup.getSupRemark());

			ps.setString(11, currentDate);
			
			ps.setInt(12,sup.getSupId());
			
			ps.executeUpdate();
						
		} catch (SQLException e1) {
			e1.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
	}
	
	@Override
	public Supplier validateSupName(String name) {
		Supplier sup = null;
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement("  select *  from supplierinfo  where supName=? ");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				sup = new Supplier();
				System.out.println(rs.getInt("supId"));
				sup.setSupId(rs.getInt("supId"));
				sup.setSupName(rs.getString("supName"));
				sup.setSupAddress(rs.getString("supAddress"));
				sup.setSupLinkman(rs.getString("supLinkman"));
				sup.setSupPhone(rs.getString("supPhone"));
				sup.setSupBankName(rs.getString("supBankName"));
				
				sup.setSupBankAccount(rs.getInt("supBankAccount"));
				sup.setSupFax(rs.getInt("supFax"));
				sup.setSupPostcode(rs.getInt("supPostcode"));
				
				sup.setSupLegalPerson(rs.getString("supLegalPerson"));
				sup.setSupRemark(rs.getString("supRemark"));
				
				sup.setStatus(rs.getInt("status"));
				sup.setOperate(rs.getString("operate"));
				sup.setOperateDate(rs.getString("operateDate"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sup;
	}

}

