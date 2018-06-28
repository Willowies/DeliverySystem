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

	// 注册新的客户
	public void registerSup(Supplier sup, Employee e) throws ClassNotFoundException, SQLException {
		// 获取数据库连接
		Connection conn = DBUtil.getConn();
		// 开启事务
		DBUtil.beginTransaction(conn);

		try {
			SupDAO dao = new SupDAOImp(conn);
			dao.registerSup(sup, e);
			// 提交事务
			DBUtil.commit(conn);

		} catch (Exception e1) {
			DBUtil.rollback(conn);
		} finally {
			DBUtil.closeConn(conn);
		}

	}

	// 批量删除
	public void deleteSup(int[] ids,Employee eSup) {
		// 获取与数据库的连接
		Connection conn = DBUtil.getConn();
		// 开启事务
		DBUtil.beginTransaction(conn);
		try {
			SupDAO dao = new SupDAOImp(conn);
			dao.deleteSups(ids,eSup);
			// 提交
			DBUtil.commit(conn);
		} catch (Exception e) {
			// 回滚
			DBUtil.rollback(conn);
		} finally {
			DBUtil.closeConn(conn);

		}
	}

	// 组合查询
	public List<Supplier> selectSup(String supName) {
		Connection conn = DBUtil.getConn();
		SupDAO dao = new SupDAOImp(conn);
		return dao.selectSup(supName);
	}

	// 分页查询
	public List<Supplier> selectSup(String supName, int pageNum) {
		Connection conn = DBUtil.getConn();
		SupDAO dao = new SupDAOImp(conn);
		return dao.selectSup(supName, pageNum);
	}

	// 查询页数
	public int selectPageCount(String supName) {
		Connection conn = DBUtil.getConn();
		SupDAO dao = new SupDAOImp(conn);
		return dao.selectPageCount(supName);
	}

	// 获取客户ID，然后好编辑客户
	public Supplier getSupById(int supId) {
		Connection conn = DBUtil.getConn();
		SupDAO dao = new SupDAOImp(conn);
		return dao.getSupById(supId);
	}

	public void updateSup(Supplier sup, Employee e) {
		// 获取与数据库的连接
		Connection conn = DBUtil.getConn();
		// 开启事务
		DBUtil.beginTransaction(conn);
		try {
			SupDAO dao = new SupDAOImp(conn);
			dao.updateSup(sup, e);;
			// 提交
			DBUtil.commit(conn);
		} catch (Exception e1) {
			// 回滚
			DBUtil.rollback(conn);
		} finally {
			DBUtil.closeConn(conn);
		}
	}
	
	// 判断供应商名是否重复
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