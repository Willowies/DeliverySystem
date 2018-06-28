package com.neuedu.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.model.po.Client;
import com.neuedu.model.po.Employee;
import com.neuedu.model.service.ClientService;




/**
 * Servlet implementation class ManageClientServlet
 */
public class ClientManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ClientManageServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");

		// ����actionѡ�������ķ���
		String action = request.getParameter("action");
		if ("register".equals(action)) {
			doRegisterClient(request, response);
		} else if ("deleteClient".equals(action)) {
			doDeleteClient(request, response);
		}else if ("updateClient".equals(action)) {
			doUpdateClient(request, response);
		}else if ("editClient".equals(action)) {
			doEditClient(request, response);
		}else if("validateIc".equals(action)) {
			System.out.print("clientIc");
			doValidateIc(request, response);
		}

	}

	private void doValidateIc(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String clientIc = request.getParameter("clientIc");
		System.out.print(clientIc);
		boolean flag = ClientService.getInstance().validateClientIc(clientIc);
		response.setContentType("text/html");
		PrintWriter pw =  response.getWriter();
		pw.print(flag);
		pw.close();
	}
	
	
	private void doRegisterClient(HttpServletRequest request, HttpServletResponse response) {
		// response.setContentType("text/html");

		// ��ȡ������
		String clientname = request.getParameter("clientname");
		String clientIc = request.getParameter("clientIc");
		String clientWorkPlace = request.getParameter("clientWorkPlace");
		String clientPhoneNumber = request.getParameter("clientPhoneNumber");
		String clientMobilePhone = request.getParameter("clientMobilePhone");
		String clientContactAddress = request.getParameter("clientContactAddress");
		String clientPostcode = request.getParameter("clientPostcode");
		String clientEmail = request.getParameter("clientEmail");

		// У��

		// ��װ����
		Client c = new Client();
		c.setClientName(clientname);
		c.setClientIc(clientIc);
		c.setClientWorkPlace(clientWorkPlace);
		c.setClientPhoneNumber(clientPhoneNumber);
		c.setClientMobilePhone(clientMobilePhone);
		c.setClientContactAddress(clientContactAddress);
		c.setClientPostcode(Integer.parseInt(clientPostcode));
		c.setClientEmail(clientEmail);

		Employee e = new Employee();
		e.setCenterType(1);
		e.setEmployeeAccount("12345");
		e.setEmployeeId(12);
		e.setEmployeeName("����");
		e.setEmployeePassword("1542");

		// ����ģ�Ͳ������б���

		try {
			ClientService.getInstance().registerClient(c, e);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


		/*
		 * ��Ӧ ҳ����ת�ķ�ʽ�� ����ת���� URL�������仯 ·��������/��ͷ��Ҳ���Բ���/ ��������ڵ�ǰ·��������ת ��֧��վ����ת
		 * ������ֻ��һ��request �ض��� URL�����仯 ·��������/��ͷ�����������tomcat������������ת ����д�ɾ���·������ʽ ֧��վ����ת
		 * ��������request���� ���ݹ���ķ�ʽ����ҳ����ת�ķ�ʽ��������ݴ����request�ڣ���ֻ������ת�������򶼿���
		 * 
		 * //����ת�� //request.getRequestDispatcher("/selectUser.jsp").forward(request,
		 * response); //�ض���
		 */
		try {
			response.sendRedirect(request.getContextPath() + "/clientService/selectResult.jsp");
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	}

	private void doDeleteClient(HttpServletRequest request, HttpServletResponse response) {
		String[] ids = request.getParameterValues("chk");
		int[] idss = new int[ids.length];
		for (int i = 0; i < ids.length; i++) {
			idss[i] = Integer.parseInt(ids[i]);
		}

		Employee e = new Employee();
		e.setCenterType(1);
		e.setEmployeeAccount("12345");
		e.setEmployeeId(12);
		e.setEmployeeName("����");
		e.setEmployeePassword("1542");
		
		ClientService.getInstance().deleteUsers(idss,e);

		int pageNum = (Integer) request.getSession().getAttribute("pageNum");
		try {
			// action��ʽ���ܴ���
			response.sendRedirect( request.getContextPath() + "/clientService/clientSearchServlet?pageNum=" + pageNum);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
/*
 * 
 
	private void doSearchClient(HttpServletRequest request, HttpServletResponse response) {
		String pagenum = request.getParameter("pageNum");
		int pageNum = 1;
		String clientname = "";
		String clientIc = "";
		String clientMobilePhone = "";
		String pageSize = "";
		
		if (pagenum != null && !"".equals(pagenum)) {
			// ���ҳ����в�ѯ
			pageNum = Integer.parseInt(pagenum);
			
			clientname = (String) request.getSession().getAttribute("clinetname");
			clientIc = (String) request.getSession().getAttribute("clientIc");
			clientMobilePhone = (String) request.getSession().getAttribute("clientMobilePhone");
			pageSize = (String) request.getSession().getAttribute("pageSize");
		} else {
			// �����ѯ��ť���в�ѯ
			clientname = request.getParameter("clientname");
			clientIc = request.getParameter("clientIc");
			clientMobilePhone = request.getParameter("clientMobilePhone");
			pageSize = request.getParameter("pageSize");
		}
		*/
		/*int ageNew = 0;
		if (clientIc != null && !"".equals(clientIc)) {
			ageNew = Integer.parseInt(clientIc);
		}*/
		
		// ��ѯ
		 /*List<Client> list = ClientService.getInstance().selectUser(clientname, clientIc, clientMobilePhone); 
		
		// ��ѯָ��ҳ������
		List<Client> list = ClientService.getInstance().selectClient(clientname, clientIc, clientMobilePhone,Integer.parseInt(pageSize),
				pageNum);
		// ��ѯҳ��
		int pagecount = ClientService.getInstance().selectPageCount(clientname, clientIc, clientMobilePhone,Integer.parseInt(pageSize));
		/*
		 * ���ݹ��� page request:���������� session����ǰ�����Ӧ�ĻỰ���� applicationContext��Ӧ��
		 * ԭ�򣺾�����ѡ��ΧС�������� ���Ⲣ������ �ര��ͬʱ����ʱ�������ݻ��ҵ�����
		 
		request.setAttribute("resultList", list);
		request.setAttribute("pagecount", pagecount);
		request.getSession().setAttribute("pageNum", pageNum);
		
		request.getSession().setAttribute("clientname", clientname);
		request.getSession().setAttribute("clinetIc", clientIc);
		request.getSession().setAttribute("clinetMobilePhone", clientMobilePhone);
		request.getSession().setAttribute("pageSize", pageSize);
		// ��ת
		try {
			request.getRequestDispatcher("searchResultTest.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
*/
    private void doEditClient(HttpServletRequest request, HttpServletResponse response) {	
    	        //�õ����ID�Ŀͻ���Ϣ
    			String clientId = request.getParameter("clientId");
    			Client c = ClientService.getInstance().getClientById(Integer.parseInt(clientId));
    			
    			//element.setAttribute(attributename,attributevalue)
    			
    			request.setAttribute("editClient", c);
    			try {
    				request.getRequestDispatcher("/clientService/editClient.jsp").forward(request, response);
    			} catch (ServletException e) {
    				e.printStackTrace();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
	}
	
    private void doUpdateClient(HttpServletRequest request, HttpServletResponse response) {
		
    	String clientId = request.getParameter("clientId");
		String clientname = request.getParameter("clientName");
		String clientIc = request.getParameter("clientIc");
		String clientWorkPlace = request.getParameter("clientWorkPlace");
		String clientPhoneNumber = request.getParameter("clientPhoneNumber");
		String clientMobilePhone = request.getParameter("clientMobilePhone");
		String clientContactAddress = request.getParameter("clientContactAddress");
		String clientPostcode = request.getParameter("clientPostcode");
		String clientEmail = request.getParameter("clientEmail");
		
		
		Client c = new Client();
		
		int idNew = 0;
		if (clientId != null && !"".equals(clientId)) {
			idNew = Integer.parseInt(clientId);
		}
		
		System.out.println(idNew);
		System.out.println(clientPostcode);
		c.setClientId(idNew);
		c.setClientName(clientname);
		c.setClientIc(clientIc);
		c.setClientWorkPlace(clientWorkPlace);
		c.setClientPhoneNumber(clientPhoneNumber);
		c.setClientMobilePhone(clientMobilePhone);
		c.setClientContactAddress(clientContactAddress);
		c.setClientPostcode(Integer.parseInt(clientPostcode));
		c.setClientEmail(clientEmail);
		
		Employee e = new Employee();
		e.setCenterType(1);
		e.setEmployeeAccount("12345");
		e.setEmployeeId(12);
		e.setEmployeeName("����");
		e.setEmployeePassword("1542");
		
		ClientService.getInstance().updateClient(c,e);
		
		int pageNum = (Integer) request.getSession().getAttribute("pageNum");
		try {
			response.sendRedirect(request.getContextPath() + "/clientService/clientSearchServlet?pageNum=" + pageNum);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	
	
}// ��������
