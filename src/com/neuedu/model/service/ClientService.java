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

	// ע���µĿͻ�
	public void registerClient(Client client, Employee e) throws ClassNotFoundException, SQLException {
		// ��ȡ���ݿ�����
		Connection conn = DBUtil.getConn();
		// ��������
		DBUtil.beginTransaction(conn);

		try {
			ClientDAO dao = new ClientDAOImp(conn);
			dao.registerClient(client, e);
			// �ύ����
			DBUtil.commit(conn);

		} catch (Exception e1) {
			DBUtil.rollback(conn);
		} finally {
			DBUtil.closeConn(conn);
		}

	}

	// ����ɾ��
	public void deleteUsers(int[] ids,Employee e) {
		// ��ȡ�����ݿ������
		Connection conn = DBUtil.getConn();
		// ��������
		DBUtil.beginTransaction(conn);
		try {
			ClientDAO dao = new ClientDAOImp(conn);
			dao.deleteUsers(ids,e);
			// �ύ
			DBUtil.commit(conn);
		} catch (Exception e1) {
			// �ع�
			DBUtil.rollback(conn);
		} finally {
			DBUtil.closeConn(conn);

		}
	}

	// ��ϲ�ѯ
	public List<Client> selectClient(String clientname, String clientIc, String clientMobilePhone) {
		Connection conn = DBUtil.getConn();
		ClientDAO dao = new ClientDAOImp(conn);
		return dao.selectClient(clientname, clientIc, clientMobilePhone);
	}

	// ��ҳ��ѯ
	public List<Client> selectClient(String clientName, String clientIc, String clientMobilePhone,int pageNum) {
		Connection conn = DBUtil.getConn();
		ClientDAO dao = new ClientDAOImp(conn);
		return dao.selectClient(clientName, clientIc, clientMobilePhone, pageNum);
	}

	// ��ѯҳ��
	public int selectPageCount(String clientName, String clientIc, String clientMobilePhone) {
		Connection conn = DBUtil.getConn();
		ClientDAO dao = new ClientDAOImp(conn);
		return dao.selectPageCount(clientName, clientIc, clientMobilePhone);
	}

	// ��ȡ�ͻ�ID��Ȼ��ñ༭�ͻ�
	public Client getClientById(int clientId) {
		Connection conn = DBUtil.getConn();
		ClientDAO dao = new ClientDAOImp(conn);
		return dao.getClientById(clientId);
	}

	public void updateClient(Client client, Employee e) {
		// ��ȡ�����ݿ������
		Connection conn = DBUtil.getConn();
		// ��������
		DBUtil.beginTransaction(conn);
		try {
			ClientDAO dao = new ClientDAOImp(conn);
			dao.updateClient(client,e);
			// �ύ
			DBUtil.commit(conn);
		} catch (Exception e1) {
			// �ع�
			DBUtil.rollback(conn);
		} finally {
			DBUtil.closeConn(conn);
		}
	}

}