package com.neuedu.controller;
import com.neuedu.model.po.Employee;
import com.neuedu.model.service.*;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EmployeeManageServlet
 */
public class EmployeeManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeManageServlet() {
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
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		String action = request.getParameter("action");
		if("login".equals(action)){
			login(request,response);
		}else if("register".equals(action)){
			register(request,response);
		}
	}

	private void login(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		
		//数据库查询结果
		Employee e = EmployeeManageService.getInstance().login(account,password);
		
		if(e==null){
			//账户不存在
			request.setAttribute("message", "该账户不存在或密码错误");
			try {
				request.getRequestDispatcher("login.jsp").forward(request, response);
			} catch (ServletException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else{
			//账户正确
			int center = e.getCenterType();
			request.getSession().setAttribute("employee",e);
			switch(center){
				case 1:
					//客户服务中心
					try {
						response.sendRedirect(request.getContextPath()+"");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				case 2:
					//调度中心
					try {
						response.sendRedirect(request.getContextPath()+"/dispatch/dispatchHome.jsp");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				case 3:
					//分站中心
					try {
						response.sendRedirect(request.getContextPath()+"/.jsp");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				case 4:
					//库房中心
					try {
						response.sendRedirect(request.getContextPath()+"/.jsp");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				case 5:
					//配送中心
					try {
						response.sendRedirect(request.getContextPath()+"/.jsp");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				case 6:
					//财务中心
					try {
						response.sendRedirect(request.getContextPath()+"/.jsp");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
			}
		}
	}
	
	private void register(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

}
