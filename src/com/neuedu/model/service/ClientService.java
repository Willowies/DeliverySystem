package com.neuedu.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.neuedu.model.dao.ClientDAO;
import com.neuedu.model.dao.ClientDAOImp;
import com.neuedu.model.po.Client;
import com.neuedu.model.po.Employee;

import com.neuedu.utils.DBUtil;

public class ClientService {
	private ClientService() {
	}

	private static ClientService service = new ClientService();

	public static ClientService getInstance() {
		return service;
	}

	// 注册新的客户
	public void registerClient(Client client, Employee e) throws ClassNotFoundException, SQLException {
		// 获取数据库连接
		Connection conn = DBUtil.getConn();
		// 开启事务
		DBUtil.beginTransaction(conn);

		try {
			ClientDAO dao = new ClientDAOImp(conn);
			dao.registerClient(client, e);
			// 提交事务
			DBUtil.commit(conn);

		} catch (Exception e1) {
			DBUtil.rollback(conn);
		} finally {
			DBUtil.closeConn(conn);
		}

	}

	// 批量删除
	public void deleteUsers(int[] ids,Employee e) {
		// 获取与数据库的连接
		Connection conn = DBUtil.getConn();
		// 开启事务
		DBUtil.beginTransaction(conn);
		try {
			ClientDAO dao = new ClientDAOImp(conn);
			dao.deleteUsers(ids,e);
			// 提交
			DBUtil.commit(conn);
		} catch (Exception e1) {
			// 回滚
			DBUtil.rollback(conn);
		} finally {
			DBUtil.closeConn(conn);

		}
	}

	// 组合查询
	public List<Client> selectClient(String clientname, String clientIc, String clientMobilePhone) {
		Connection conn = DBUtil.getConn();
		ClientDAO dao = new ClientDAOImp(conn);
		return dao.selectClient(clientname, clientIc, clientMobilePhone);
	}

	// 分页查询
	public List<Client> selectClient(String clientName, String clientIc, String clientMobilePhone,int pageNum) {
		Connection conn = DBUtil.getConn();
		ClientDAO dao = new ClientDAOImp(conn);
		return dao.selectClient(clientName, clientIc, clientMobilePhone, pageNum);
	}

	// 查询页数
	public int selectPageCount(String clientName, String clientIc, String clientMobilePhone) {
		Connection conn = DBUtil.getConn();
		ClientDAO dao = new ClientDAOImp(conn);
		return dao.selectPageCount(clientName, clientIc, clientMobilePhone);
	}

	// 获取客户ID，然后好编辑客户
	public Client getClientById(int clientId) {
		Connection conn = DBUtil.getConn();
		ClientDAO dao = new ClientDAOImp(conn);
		return dao.getClientById(clientId);
	}

	public void updateClient(Client client, Employee e) {
		// 获取与数据库的连接
		Connection conn = DBUtil.getConn();
		// 开启事务
		DBUtil.beginTransaction(conn);
		try {
			ClientDAO dao = new ClientDAOImp(conn);
			dao.updateClient(client,e);
			// 提交
			DBUtil.commit(conn);
		} catch (Exception e1) {
			// 回滚
			DBUtil.rollback(conn);
		} finally {
			DBUtil.closeConn(conn);
		}
	}

}