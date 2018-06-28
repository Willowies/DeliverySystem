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

		// 根据action选择所做的服务
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

		// 获取表单参数
		String clientname = request.getParameter("clientname");
		String clientIc = request.getParameter("clientIc");
		String clientWorkPlace = request.getParameter("clientWorkPlace");
		String clientPhoneNumber = request.getParameter("clientPhoneNumber");
		String clientMobilePhone = request.getParameter("clientMobilePhone");
		String clientContactAddress = request.getParameter("clientContactAddress");
		String clientPostcode = request.getParameter("clientPostcode");
		String clientEmail = request.getParameter("clientEmail");

		// 校验

		// 封装数据
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
		e.setEmployeeName("张三");
		e.setEmployeePassword("1542");

		// 调用模型层代码进行保存

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
		 * 响应 页面跳转的方式： 请求转发： URL不发生变化 路径可以以/开头，也可以不带/ 都是相对于当前路径进行跳转 不支持站外跳转
		 * 过程中只有一个request 重定向： URL发生变化 路径不能以/开头，否则相对于tomcat服务器进行跳转 建议写成绝对路径的形式 支持站外跳转
		 * 产生两个request对象 数据共享的方式决定页面跳转的方式，如果数据存放在request内，则只能请求转发，否则都可以
		 * 
		 * //请求转发 //request.getRequestDispatcher("/selectUser.jsp").forward(request,
		 * response); //重定向
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
		e.setEmployeeName("张三");
		e.setEmployeePassword("1542");
		
		ClientService.getInstance().deleteUsers(idss,e);

		int pageNum = (Integer) request.getSession().getAttribute("pageNum");
		try {
			// action格式可能错误
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
			// 点击页码进行查询
			pageNum = Integer.parseInt(pagenum);
			
			clientname = (String) request.getSession().getAttribute("clinetname");
			clientIc = (String) request.getSession().getAttribute("clientIc");
			clientMobilePhone = (String) request.getSession().getAttribute("clientMobilePhone");
			pageSize = (String) request.getSession().getAttribute("pageSize");
		} else {
			// 点击查询按钮进行查询
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
		
		// 查询
		 /*List<Client> list = ClientService.getInstance().selectUser(clientname, clientIc, clientMobilePhone); 
		
		// 查询指定页的数据
		List<Client> list = ClientService.getInstance().selectClient(clientname, clientIc, clientMobilePhone,Integer.parseInt(pageSize),
				pageNum);
		// 查询页数
		int pagecount = ClientService.getInstance().selectPageCount(clientname, clientIc, clientMobilePhone,Integer.parseInt(pageSize));
		/*
		 * 数据共享 page request:请求作用域 session：当前请求对应的会话对象 applicationContext：应用
		 * 原则：尽可能选择范围小的作用域 避免并发操作 多窗口同时操作时产生数据混乱的问题
		 
		request.setAttribute("resultList", list);
		request.setAttribute("pagecount", pagecount);
		request.getSession().setAttribute("pageNum", pageNum);
		
		request.getSession().setAttribute("clientname", clientname);
		request.getSession().setAttribute("clinetIc", clientIc);
		request.getSession().setAttribute("clinetMobilePhone", clientMobilePhone);
		request.getSession().setAttribute("pageSize", pageSize);
		// 跳转
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
    	        //得到这个ID的客户信息
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
		e.setEmployeeName("张三");
		e.setEmployeePassword("1542");
		
		ClientService.getInstance().updateClient(c,e);
		
		int pageNum = (Integer) request.getSession().getAttribute("pageNum");
		try {
			response.sendRedirect(request.getContextPath() + "/clientService/clientSearchServlet?pageNum=" + pageNum);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	
	
}// 整个结束
