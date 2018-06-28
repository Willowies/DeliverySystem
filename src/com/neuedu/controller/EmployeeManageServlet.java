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
		
		//���ݿ��ѯ���
		Employee e = EmployeeManageService.getInstance().login(account,password);
		
		if(e==null){
			//�˻�������
			request.setAttribute("message", "���˻������ڻ��������");
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
			//�˻���ȷ
			int center = e.getCenterType();
			request.getSession().setAttribute("employee",e);
			switch(center){
				case 1:
					//�ͻ���������
					try {
						response.sendRedirect(request.getContextPath()+"");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				case 2:
					//��������
					try {
						response.sendRedirect(request.getContextPath()+"/dispatch/dispatchHome.jsp");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				case 3:
					//��վ����
					try {
						response.sendRedirect(request.getContextPath()+"/.jsp");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				case 4:
					//�ⷿ����
					try {
						response.sendRedirect(request.getContextPath()+"/.jsp");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				case 5:
					//��������
					try {
						response.sendRedirect(request.getContextPath()+"/.jsp");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				case 6:
					//��������
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
