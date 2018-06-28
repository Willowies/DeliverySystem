package com.neuedu.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.neuedu.model.dao.SupDAO;
import com.neuedu.model.dao.SupDAOImp;
import com.neuedu.model.po.Employee;
import com.neuedu.model.po.Supplier;
import com.neuedu.utils.DBUtil;

public class SupService {
	private SupService() {
	}

	private static SupService service = new SupService();

	public static SupService getInstance() {
		return service;
	}

	// ע���µĿͻ�
	public void registerSup(Supplier sup, Employee e) throws ClassNotFoundException, SQLException {
		// ��ȡ���ݿ�����
		Connection conn = DBUtil.getConn();
		// ��������
		DBUtil.beginTransaction(conn);

		try {
			SupDAO dao = new SupDAOImp(conn);
			dao.registerSup(sup, e);
			// �ύ����
			DBUtil.commit(conn);

		} catch (Exception e1) {
			DBUtil.rollback(conn);
		} finally {
			DBUtil.closeConn(conn);
		}

	}

	// ����ɾ��
	public void deleteSup(int[] ids,Employee eSup) {
		// ��ȡ�����ݿ������
		Connection conn = DBUtil.getConn();
		// ��������
		DBUtil.beginTransaction(conn);
		try {
			SupDAO dao = new SupDAOImp(conn);
			dao.deleteSups(ids,eSup);
			// �ύ
			DBUtil.commit(conn);
		} catch (Exception e) {
			// �ع�
			DBUtil.rollback(conn);
		} finally {
			DBUtil.closeConn(conn);

		}
	}

	// ��ϲ�ѯ
	public List<Supplier> selectSup(String supName) {
		Connection conn = DBUtil.getConn();
		SupDAO dao = new SupDAOImp(conn);
		return dao.selectSup(supName);
	}

	// ��ҳ��ѯ
	public List<Supplier> selectSup(String supName, int pageNum) {
		Connection conn = DBUtil.getConn();
		SupDAO dao = new SupDAOImp(conn);
		return dao.selectSup(supName, pageNum);
	}

	// ��ѯҳ��
	public int selectPageCount(String supName) {
		Connection conn = DBUtil.getConn();
		SupDAO dao = new SupDAOImp(conn);
		return dao.selectPageCount(supName);
	}

	// ��ȡ�ͻ�ID��Ȼ��ñ༭�ͻ�
	public Supplier getSupById(int supId) {
		Connection conn = DBUtil.getConn();
		SupDAO dao = new SupDAOImp(conn);
		return dao.getSupById(supId);
	}

	public void updateSup(Supplier sup, Employee e) {
		// ��ȡ�����ݿ������
		Connection conn = DBUtil.getConn();
		// ��������
		DBUtil.beginTransaction(conn);
		try {
			SupDAO dao = new SupDAOImp(conn);
			dao.updateSup(sup, e);;
			// �ύ
			DBUtil.commit(conn);
		} catch (Exception e1) {
			// �ع�
			DBUtil.rollback(conn);
		} finally {
			DBUtil.closeConn(conn);
		}
	}
	
	// �жϹ�Ӧ�����Ƿ��ظ�
		public boolean validateSupName(String name) {
			Connection conn = DBUtil.getConn();
			SupDAO dao = new SupDAOImp(conn);
			Supplier sup = dao.validateSupName(name);
			DBUtil.closeConn(conn);
			if (sup == null) {
				return false;
			} else {
				return true;
			}
		}

}