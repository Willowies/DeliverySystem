package com.neuedu.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.neuedu.model.po.Client;
import com.neuedu.model.po.Employee;

public interface ClientDAO {

	void registerClient(Client client,Employee e) throws SQLException;


	void deleteUsers(int[] ids,Employee e);


	//��ϲ�ѯ
	List<Client> selectClient(String clientname, String clientIc, String clientMobilePhone);

	//��ҳ��ѯ
	List<Client> selectClient(String clientname, String clientIc, String clientMobilePhone, int pageNum);

	//��ѯҳ��
	int selectPageCount(String clientname, String clientIc, String clientMobilePhone);


	Client getClientById(int clientId);

	
	void updateClient(Client client, Employee e);


	Client validateClientIc(String ic);





	

}
