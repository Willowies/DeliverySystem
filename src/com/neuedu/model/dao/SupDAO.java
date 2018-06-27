package com.neuedu.model.dao;

import java.sql.SQLException;
import java.util.List;
import com.neuedu.model.po.Employee;
import com.neuedu.model.po.Supplier;

public interface SupDAO {

	void registerSup(Supplier sup,Employee e) throws SQLException;


	void deleteSups(int[] ids,Employee eSup);


	//组合查询
	List<Supplier> selectSup(String supName);

	//分页查询
	List<Supplier> selectSup(String supName, int pageNum);

	//查询页数
	int selectPageCount(String supName);


	Supplier getSupById(int supId);

	
	void updateSup(Supplier sup, Employee e);





	

}
