package com.neuedu.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.neuedu.model.po.Client;



public class ClientDAOImp implements ClientDAO{
	private Connection conn;
	public ClientDAOImp(Connection conn) {
		this.conn = conn;
	}
	
	public Client selectClient(Client c) {
		StringBuffer sbf = new StringBuffer("");
		String clientName = c.getClientName();
		String clientIc = c.getClientIc();
		String clientMobilePhone = c.getClientMobilePhone();
		String clientPhoneNumber = c.getClientPhoneNumber();
		sbf.append("select *  from  userinfo where 1=1  ");
		if(clientName != null && !"".equals(clientName)){
			sbf.append(" and clientName=?");
		}
		if(clientIc != null && !"".equals(clientIc)){
			sbf.append(" and clientIc=?");
		}
		if(clientMobilePhone != null && !"".equals(clientMobilePhone)){
			sbf.append(" and clientMobilePhone=?");
		}
		if(clientPhoneNumber != null && !"".equals(clientPhoneNumber)){
			sbf.append(" and clientPhoneNumber=?");
		}
		sbf.append(";");
		Client client = new Client();;
		try {
			PreparedStatement ps = conn.prepareStatement(sbf.toString());
			int index=1;
			if(clientName != null && !"".equals(clientName)){
				ps.setString(index, clientName);
				index++;
			}
			if(clientIc != null && !"".equals(clientIc)){
				ps.setString(index, clientIc);
				index++;
			}
			if(clientMobilePhone != null && !"".equals(clientMobilePhone)){
				ps.setString(index, clientMobilePhone);
				index++;
			}
			if(clientPhoneNumber != null && !"".equals(clientPhoneNumber)){
				ps.setString(index, clientPhoneNumber);
				index++;
			}
			//System.out.println(ps);
			//执行
			//统计获得数据数
			ResultSet rs = ps.executeQuery();
			rs.last();
			int rs_row = rs.getRow();
			rs.beforeFirst();
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
			if(rs_row == 0){
				client = null;//表示未找到Ss
			}else if (rs_row ==1){
				rs.next();
				client.setClientContactAddress(rs.getString("ClientContactAddress"));
				client.setClientEmail(rs.getString("ClientEmail"));
				client.setClientIc(rs.getString("ClientIc"));
				client.setClientId(rs.getInt("ClientId"));
				client.setClientMobilePhone(rs.getString("clientmobilephone"));
				client.setClientName(rs.getString("ClientName"));
				client.setClientPhoneNumber(rs.getString("ClientPhoneNumber"));
				client.setClientPostCode(rs.getInt("ClientPostCode"));
			}else if(rs_row>1){
				client = null;//找到多个不符合定位要求
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return client;
	}
}
