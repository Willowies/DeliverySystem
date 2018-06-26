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
import com.neuedu.utils.DBUtil;

public class ClientDAOImp implements ClientDAO {
	Connection conn;

	public ClientDAOImp(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void registerClient(Client client, Employee e) throws SQLException {
		
		PreparedStatement ps = null;
		// 获取当前时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		String currentDate = df.format(new Date());
		try {
			String sql = "insert into userinfo(clientname,clientIc,clientWorkPlace,"
					+ "clientPhoneNumber,clientMobilePhone,clientContactAddress,"
					+ "clientPostcode,clientEmail,status,operator,operateDate)"
		            + "values(?,?,?,?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, client.getClientName());
			ps.setString(2, client.getClientIc());
			ps.setString(3, client.getClientWorkPlace());
			ps.setString(4, client.getClientPhoneNumber());
			ps.setString(5, client.getClientMobilePhone());
			ps.setString(6, client.getClientContactAddress());
			ps.setInt(7, client.getClientPostcode());
			ps.setString(8, client.getClientEmail());
			
			ps.setInt(9, 2);
			ps.setString(10, e.getEmployeeName());
			ps.setString(11, currentDate);
			
			ps.executeUpdate();
			
			
			
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			DBUtil.closePS(ps);
		}

	}

	@Override
	public void deleteUsers(int[] ids) {
		String id = Arrays.toString(ids).replace('[', '(').replace(']', ')');
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(" delete  from  userinfo  " + "  where clientId in  " + id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closePS(ps);
		}

	}

	// 组合查询
	@Override
	public List<Client> selectClient(String clientname, String clientIc, String clientMobilePhone) {
		List<Client> list = new ArrayList<Client>();
		StringBuffer sbf = new StringBuffer("");
		sbf.append("  select *  from  userinfo where 1=1  ");
		if (clientname != null && !"".equals(clientname)) {
			sbf.append(" and clientname=? ");
		}
		if (clientIc != null && !"".equals(clientIc)) {
			sbf.append(" and clientIc=? ");
		}
		if (clientMobilePhone != null && !"".equals(clientMobilePhone)) {
			sbf.append(" and clientMobilePhone=? ");
		}
		try {
			PreparedStatement ps = conn.prepareStatement(sbf.toString());
			int index = 1;
			if (clientname != null && !"".equals(clientname)) {
				ps.setString(index, clientname);
				index++;
			}
			if (clientIc != null && !"".equals(clientIc)) {
				ps.setString(index, clientIc);
				index++;
			}
			if (clientMobilePhone != null && !"".equals(clientMobilePhone)) {
				ps.setString(index, clientMobilePhone);
			}
			// 执行
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Client client = new Client();
				System.out.println(rs.getInt("clientId"));
				client.setClientId(rs.getInt("clientId"));
				client.setClientName(rs.getString("clientname"));
				client.setClientIc(rs.getString("clientIc"));
				client.setClientWorkPlace(rs.getString("clientWorkPlace"));
				client.setClientPhoneNumber(rs.getString("clientPhoneNumber"));
				client.setClientMobilePhone(rs.getString("clientMobilePhone"));
				client.setClientContactAddress(rs.getString("clientContactAddress"));
				client.setClientPostcode(rs.getInt("clientPostcode"));
				client.setClientEmail(rs.getString("clientEmail"));
				client.setStatus(rs.getInt("status"));
				client.setOperator(rs.getString("operator"));
				client.setOperateDate(rs.getString("operateDate"));

				list.add(client);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// 分页查询
	@Override
	public List<Client> selectClient(String clientname, String clientIc, String clientMobilePhone, int pageSize,int pageNum) {
		List<Client> list = new ArrayList<Client>();
		StringBuffer sbf = new StringBuffer("");
		sbf.append("  select * from  userinfo where 1=1  ");
		if (clientname != null && !"".equals(clientname)) {
			sbf.append(" and clientname=? ");
		}
		if (clientIc != null && !"".equals(clientIc)) {
			sbf.append(" and clientIc=? ");
		}
		if (clientMobilePhone != null && !"".equals(clientMobilePhone)) {
			sbf.append(" and clientMobilePhone=? ");
		}
		try {
			String selectSql = sbf.toString() + " limit "+ pageSize * (pageNum - 1)+","+pageSize;
			PreparedStatement ps = conn.prepareStatement(selectSql);
			
			int index = 1;
			if (clientname != null && !"".equals(clientname)) {
				ps.setString(index, clientname);
				index++;
			}
			if (clientIc != null && !"".equals(clientIc)) {
				ps.setString(index, clientIc);
				index++;
			}
			if (clientMobilePhone != null && !"".equals(clientMobilePhone)) {
				ps.setString(index, clientMobilePhone);
			}
			// 执行
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Client client = new Client();

				System.out.println(rs.getInt("clientId"));
				client.setClientId(rs.getInt("clientId"));
				client.setClientName(rs.getString("clientname"));
				client.setClientIc(rs.getString("clientIc"));
				client.setClientWorkPlace(rs.getString("clientWorkPlace"));
				client.setClientPhoneNumber(rs.getString("clientPhoneNumber"));
				client.setClientMobilePhone(rs.getString("clientMobilePhone"));
				client.setClientContactAddress(rs.getString("clientContactAddress"));
				client.setClientPostcode(rs.getInt("clientPostcode"));
				client.setClientEmail(rs.getString("clientEmail"));
				client.setStatus(rs.getInt("status"));
				client.setOperator(rs.getString("operator"));
				client.setOperateDate(rs.getString("operateDate"));

				list.add(client);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// 查询页数
	@Override
	public int selectPageCount(String clientname, String clientIc, String clientMobilePhone, int pageSize) {
		int count = 0;
		StringBuffer sbf = new StringBuffer("");
		sbf.append("  select count(*) cc  from  userinfo where 1=1  ");
		if (clientname != null && !"".equals(clientname)) {
			sbf.append(" and clientname=? ");
		}
		if (clientIc != null && !"".equals(clientIc)) {
			sbf.append(" and clientIc=? ");
		}
		if (clientMobilePhone != null && !"".equals(clientMobilePhone)) {
			sbf.append(" and clientMobliePhone=? ");
		}
		try {
			PreparedStatement ps = conn.prepareStatement(sbf.toString());
			int index = 1;
			if (clientname != null && !"".equals(clientname)) {
				ps.setString(index, clientname);
				index++;
			}
			if (clientIc != null && !"".equals(clientIc)) {
				ps.setString(index, clientIc);
				index++;
			}
			if (clientMobilePhone != null && !"".equals(clientMobilePhone)) {
				ps.setString(index, clientMobilePhone);
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
		if (count % pageSize == 0) {
			pagecount = count / pageSize;
		} else {
			pagecount = count / pageSize + 1;
		}
		return pagecount;

	}

	//按ID找到某一个具体的用户
	@Override
	public Client getClientById(int clientId) {
		Client client = new Client();
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement("  select *  from userinfo  where clientId=? ");
			ps.setInt(1, clientId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				client.setClientId(rs.getInt("clientId"));
				client.setClientName(rs.getString("clientname"));
				client.setClientIc(rs.getString("clientIc"));
				client.setClientWorkPlace(rs.getString("clientWorkPlace"));
				client.setClientPhoneNumber(rs.getString("clientPhoneNumber"));
				client.setClientMobilePhone(rs.getString("clientMobilePhone"));
				client.setClientContactAddress(rs.getString("clientContactAddress"));
				client.setClientPostcode(rs.getInt("clientPostcode"));
				client.setClientEmail(rs.getString("clientEmail"));
				client.setStatus(rs.getInt("status"));
				client.setOperator(rs.getString("operator"));
				client.setOperateDate(rs.getString("operateDate"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return client;
	}

	@Override
	public void updateClient(Client client,Employee e) {
		PreparedStatement ps = null;
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		String currentDate = df.format(new Date());
		try {
			String sql = " update clientservice.userinfo set clientname=?,clientIc=?,clientWorkPlace=?,clientPhoneNumber=?," 
		               + " clientMobilePhone=?,clientContactAddress=?,clientPostcode=?,clientEmail=?,"
					   + " operateDate = ?"
					   + " where clientId=? ";
			ps = conn.prepareStatement(sql);
			
			System.out.println("id:"+client.getClientId());
			ps.setString(1, client.getClientName());
			ps.setString(2, client.getClientIc());
			ps.setString(3, client.getClientWorkPlace());
			ps.setString(4, client.getClientPhoneNumber());
			ps.setString(5, client.getClientMobilePhone());
			ps.setString(6, client.getClientContactAddress());
			ps.setInt(7, client.getClientPostcode());
			ps.setString(8, client.getClientEmail());
			
			ps.setString(9, currentDate);
			ps.setInt(10, client.getClientId());
			
			ps.executeUpdate();
			
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
	}

}
