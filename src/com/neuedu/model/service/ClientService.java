package com.neuedu.model.service;

import java.sql.Connection;

import com.neuedu.model.dao.ClientDAO;
import com.neuedu.model.dao.ClientDAOImp;
import com.neuedu.model.po.Client;
import com.neuedu.utils.DBUtil;



public class ClientService {
	private ClientService(){
	}
	
	private static ClientService service = new ClientService();
	
	public static ClientService getInstance(){
		return service;
	}
	public Client selectClient(Client c){
		Connection conn = DBUtil.getConn();
		ClientDAO dao = new ClientDAOImp(conn);
		Client client = dao.selectClient(c);
		return client;
	}
}
