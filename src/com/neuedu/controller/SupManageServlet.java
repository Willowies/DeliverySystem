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

		// 根据action选择所做的服务
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

		// 获取表单参数
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

		// 校验

		// 封装数据
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
		eSup.setEmployeeName("王无");
		eSup.setEmployeePassword("1542");

		// 调用模型层代码进行保存

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
		 * 响应 页面跳转的方式： 请求转发： URL不发生变化 路径可以以/开头，也可以不带/ 都是相对于当前路径进行跳转 不支持站外跳转
		 * 过程中只有一个request 重定向： URL发生变化 路径不能以/开头，否则相对于tomcat服务器进行跳转 建议写成绝对路径的形式 支持站外跳转
		 * 产生两个request对象 数据共享的方式决定页面跳转的方式，如果数据存放在request内，则只能请求转发，否则都可以
		 * 
		 * //请求转发 //request.getRequestDispatcher("/selectUser.jsp").forward(request,
		 * response); //重定向
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
		eSup.setEmployeeName("王无");
		eSup.setEmployeePassword("1542");
		
		SupService.getInstance().deleteSup(idss,eSup);

		int pageNum = (Integer) request.getSession().getAttribute("pageNum");
		try {
			// action格式可能错误
			response.sendRedirect( request.getContextPath() + "/supService/supSearchServlet?pageNum=" + pageNum);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    private void doEditSup(HttpServletRequest request, HttpServletResponse response) {	
    	        //得到这个ID的客户信息
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
    	//获取表单参数
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
    	
		//封装数据
		// 封装数据
		Supplier sup = new Supplier();
		
		
		System.out.println("账户是" + supBankAccount);
		
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
		eSup.setEmployeeName("王无");
		eSup.setEmployeePassword("1542");
		
		SupService.getInstance().updateSup(sup, eSup);
		
		
		int pageNum = (Integer) request.getSession().getAttribute("pageNum");
		try {
			response.sendRedirect(request.getContextPath() + "/supService/supSearchServlet?pageNum=" + pageNum);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	
	
}// 整个结束
