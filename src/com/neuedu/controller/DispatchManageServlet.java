package com.neuedu.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DispatchManageServlet
 */
public class DispatchManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DispatchManageServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		System.out.println("½øÈëdispatch servlet");
		
		String action = request.getParameter("action");
		if ("home".equals(action)) {
			
		}else if ("searchOrder".equals(action)) {
			String createDate = request.getParameter("createDate");
			String requireDate = request.getParameter("requireDate");
			String dispatchOrNot = request.getParameter("dispatchOrNot");
			String orderType = request.getParameter("orderType");
			
			System.out.println("createDate:"+createDate+" requireDate:"+requireDate
					+" dispatchOrNot:"+dispatchOrNot+" orderType:"+orderType);
			
		}else if ("dispatch".equals(action)) {
			
		}else if ("modify".equals(action)) {
			
		}else if ("searchWorkOrder".equals(action)) {
			
		}
	}
	
	private void searchOrder() {
		
	}

}
