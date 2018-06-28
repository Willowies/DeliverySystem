package com.neuedu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.neuedu.model.po.Employee;
import com.neuedu.model.po.Supplier;
import com.neuedu.model.service.ClientService;
import com.neuedu.model.service.SupService;




/**
 * Servlet implementation class ManageClientServlet
 */
public class SupManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SupManageServlet() {
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
		if ("registerSup".equals(action)) {
			doRegisterSup(request, response);
		} else if ("deleteSup".equals(action)) {
			doDeleteSup(request, response);
		}else if ("updateSup".equals(action)) {
			doUpdateSup(request, response);
		}else if ("editSup".equals(action)) {
			doEditSup(request, response);
		}else if("validateSupName".equals(action)) {
			System.out.print("clientIc");
			doValidateSupName(request, response);
		}

	}

	private void doValidateSupName(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String supName = request.getParameter("supName");
		System.out.print(supName);
		boolean flag = SupService.getInstance().validateSupName(supName);
		response.setContentType("text/html");
		PrintWriter pw =  response.getWriter();
		pw.print(flag);
		pw.close();
	}
	private void doRegisterSup(HttpServletRequest request, HttpServletResponse response) {
		// response.setContentType("text/html");

		// ��ȡ������
		String supName = request.getParameter("supName");
		String supAddress = request.getParameter("supAddress");
		String supLinkman = request.getParameter("supLinkman");
		String supPhone = request.getParameter("supPhone");
		String supBankName = request.getParameter("supBankName");
		String supBankAccount = request.getParameter("supBankAccount");
		String supFax = request.getParameter("supFax");
		String supPostcode = request.getParameter("supPostcode");
		String supLegalPerson = request.getParameter("supLegalPerson");
		String supRemark = request.getParameter("supRemark");

		// У��

		// ��װ����
		Supplier sup = new Supplier();
		sup.setSupName(supName);
		sup.setSupAddress(supAddress);
		sup.setSupLinkman(supLinkman);
		sup.setSupPhone(supPhone);
		sup.setSupBankName(supBankName);
		sup.setSupBankAccount(Integer.parseInt(supBankAccount));
		sup.setSupFax(Integer.parseInt(supFax));
		sup.setSupPostcode(Integer.parseInt(supPostcode));
		sup.setSupLegalPerson(supLegalPerson);
		sup.setSupRemark(supRemark);

		
		Employee eSup = new Employee();
		eSup.setCenterType(1);
		eSup.setEmployeeAccount("12345");
		eSup.setEmployeeId(12);
		eSup.setEmployeeName("����");
		eSup.setEmployeePassword("1542");

		// ����ģ�Ͳ������б���

		try {
			SupService.getInstance().registerSup(sup, eSup);
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
			response.sendRedirect(request.getContextPath() + "/supService/supSelectResult.jsp");
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	}

	private void doDeleteSup(HttpServletRequest request, HttpServletResponse response) {
		String[] ids = request.getParameterValues("chk");
		int[] idss = new int[ids.length];
		for (int i = 0; i < ids.length; i++) {
			idss[i] = Integer.parseInt(ids[i]);
		}

		Employee eSup = new Employee();
		eSup.setCenterType(1);
		eSup.setEmployeeAccount("12345");
		eSup.setEmployeeId(12);
		eSup.setEmployeeName("����");
		eSup.setEmployeePassword("1542");
		
		SupService.getInstance().deleteSup(idss,eSup);

		int pageNum = (Integer) request.getSession().getAttribute("pageNum");
		try {
			// action��ʽ���ܴ���
			response.sendRedirect( request.getContextPath() + "/supService/supSearchServlet?pageNum=" + pageNum);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    private void doEditSup(HttpServletRequest request, HttpServletResponse response) {	
    	        //�õ����ID�Ŀͻ���Ϣ
    			String supId = request.getParameter("supId");
    			Supplier sup = SupService.getInstance().getSupById(Integer.parseInt(supId));
    					
    			
    			//element.setAttribute(attributename,attributevalue)
    			
    			request.setAttribute("editSup", sup);
    			try {
    				request.getRequestDispatcher("/supService/supEditInfo.jsp").forward(request, response);
    			} catch (ServletException e) {
    				e.printStackTrace();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
	}
	
    private void doUpdateSup(HttpServletRequest request, HttpServletResponse response) {
    	//��ȡ������
    	String supId = request.getParameter("supId");
    	String supName = request.getParameter("supName");
		String supAddress = request.getParameter("supAddress");
		String supLinkman = request.getParameter("supLinkman");
		String supPhone = request.getParameter("supPhone");
		String supBankName = request.getParameter("supBankName");
		String supBankAccount = request.getParameter("supBankAccount");
		String supFax = request.getParameter("supFax");
		String supPostcode = request.getParameter("supPostcode");
		String supLegalPerson = request.getParameter("supLegalPerson");
		String supRemark = request.getParameter("supRemark");
    	
		//��װ����
		// ��װ����
		Supplier sup = new Supplier();
		
		
		System.out.println("�˻���" + supBankAccount);
		
		sup.setSupId(Integer.parseInt(supId));
		sup.setSupName(supName);
		sup.setSupAddress(supAddress);
		sup.setSupLinkman(supLinkman);
		sup.setSupPhone(supPhone);
		sup.setSupBankName(supBankName);
		sup.setSupBankAccount(Integer.parseInt(supBankAccount));
		sup.setSupFax(Integer.parseInt(supFax));
		sup.setSupPostcode(Integer.parseInt(supPostcode));
		sup.setSupLegalPerson(supLegalPerson);
		sup.setSupRemark(supRemark);
		
	
		Employee eSup = new Employee();
		eSup.setCenterType(1);
		eSup.setEmployeeAccount("12345");
		eSup.setEmployeeId(12);
		eSup.setEmployeeName("����");
		eSup.setEmployeePassword("1542");
		
		SupService.getInstance().updateSup(sup, eSup);
		
		
		int pageNum = (Integer) request.getSession().getAttribute("pageNum");
		try {
			response.sendRedirect(request.getContextPath() + "/supService/supSearchServlet?pageNum=" + pageNum);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	
	
}// ��������
