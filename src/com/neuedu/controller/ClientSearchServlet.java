package com.neuedu.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.model.po.Client;
import com.neuedu.model.service.ClientService;

/**
 * Servlet implementation class ClientSearch
 */
public class ClientSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String pagenum = request.getParameter("pageNum");
		
		int pageNum = 1;
		String clientName = "";
		String clientIc = "";
		String clientMobilePhone = "";
		//String pageSize = "";
		
		if (pagenum != null && !"".equals(pagenum)) {
			// 点击页码进行查询
			pageNum = Integer.parseInt(pagenum);
			
			clientName = (String) request.getSession().getAttribute("clinetName");
			clientIc = (String) request.getSession().getAttribute("clientIc");
			clientMobilePhone = (String) request.getSession().getAttribute("clientMobilePhone");
			//pageSize = (String) request.getSession().getAttribute("pageSize");
		} else {
			// 点击查询按钮进行查询
			clientName = request.getParameter("clientName");
			clientIc = request.getParameter("clientIc");
			clientMobilePhone = request.getParameter("clientMobilePhone");
			//pageSize = request.getParameter("pageSize");
		}
		
		//int pSizeNew = 0;
		//if (pageSize != null && !"".equals(pageSize)) {
			//pSizeNew = Integer.parseInt(pageSize);
		//}
		
		// 查询
		 /*List<Client> list = ClientService.getInstance().selectUser(clientname, clientIc, clientMobilePhone); 
		
		// 查询指定页的数据*/
		List<Client> list = ClientService.getInstance().selectClient(clientName, clientIc, clientMobilePhone,pageNum);
		// 查询页数
		int pagecount = ClientService.getInstance().selectPageCount(clientName, clientIc, clientMobilePhone);
		/*
		 * 数据共享 page request:请求作用域 session：当前请求对应的会话对象 applicationContext：应用
		 * 原则：尽可能选择范围小的作用域 避免并发操作 多窗口同时操作时产生数据混乱的问题
		 */
		request.setAttribute("resultList", list);
		request.setAttribute("pagecount", pagecount);
		request.getSession().setAttribute("pageNum", pageNum);
		
		request.getSession().setAttribute("clientName", clientName);
		request.getSession().setAttribute("clinetIc", clientIc);
		request.getSession().setAttribute("clinetMobilePhone", clientMobilePhone);
		//request.getSession().setAttribute("pageSize", pageSize);
		// 跳转
		try {
			request.getRequestDispatcher("/clientService/selectResult.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
