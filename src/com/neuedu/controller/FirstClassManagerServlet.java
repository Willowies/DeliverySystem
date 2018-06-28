package com.neuedu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.neuedu.model.po.FirstClass;
import com.neuedu.model.po.Product;
import com.neuedu.model.service.FirstClassService;



/**
 * Servlet implementation class FirstClassMangerServlet
 */
@WebServlet("/firstClassMangerServlet")
public class FirstClassManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FirstClassManagerServlet() {
        super();
        // TODO Auto-generated constructor stub
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
		
		String action = request.getParameter("action");
		 if("add".equals(action)) {
			 addFirstClass(request,response);			
		 }else if("selectfirstclass".equals(action)) {
			 selectFirstClass(request,response);
		 }else if("edit".equals(action)) {
			 editFirstClass(request,response);
		 }else if("delete".equals(action)) {
			 deleteFirstClass(request,response);
		 }else if("selectproducts".equals(action)) {
			 selectProducts(request,response);
		 }else if("validate".equals(action)) {
				System.out.print("clientIc");
				validatefirstClassName(request, response);
			} 
	}

	private void validatefirstClassName(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String firstclassname = request.getParameter("firstclassname");
		System.out.print(firstclassname);
		boolean flag = FirstClassService.getInstance().validatefirstClassName(firstclassname);
		response.setContentType("text/html");
		PrintWriter pw =  response.getWriter();
		pw.print(flag);
		pw.close();
		
	}

	private void addFirstClass(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		
		String firstclassname = /*"操作员1123038211";*/request.getParameter("firstclassname");	
		String firstclassdescription =/*"操作员1123038211";*/request.getParameter("fed");
		int status = 1;
		String operator = "test1";/*(String) request.getSession().getAttribute("");*/ 
		java.util.Date  date=new java.util.Date();
		java.sql.Date operatedate=new java.sql.Date(date.getTime());
		boolean flag = true;
		
		FirstClass fc = new FirstClass();
		
		fc.setFirstClassName(firstclassname);
		fc.setFirstClassDescription(firstclassdescription);
		fc.setStatus(status);
		fc.setOperator(operator);
		fc.setOperateDate(operatedate);       
			try {
				FirstClassService.getInstance().addFirstClass(fc);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			try {
				response.sendRedirect("/DeliverySystem/firstclass/firstClassHome.jsp");
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
				//request.getRequestDispatcher("firstclass/AddfirstClass.jsp").forward(request, response);
				//request.getRequestDispatcher("firstclass/firstClassHome.jsp").forward(request, response);
                // request.getRequestDispatcher("../AddfirstClass.jsp").forward(request, response);
                // this.getServletContext().getRequestDispatcher("/firstclass/AddfirstClass.jsp").forward(request, response);
                // response.sendRedirect(request.getContextPath()+"/AddfirstClass.jsp");
                //request.getRequestDispatcher("firstclass/firstClassHome.jsp").forward(request, response);
	
		
	}

	private void selectFirstClass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstclassname = request.getParameter("firstclassname");
		int firstclassid = Integer.parseInt(request.getParameter("firstclassid"));		
		List<FirstClass> fcs = FirstClassService.getInstance().selectFirstClass(firstclassname,firstclassid);
		
			
		
		//this.getServletContext().getRequestDispatcher("/firstclass/AddfirstClass.jsp").forward(request, response);
		 request.setAttribute("firstclassresultlist", fcs);
		 request.getSession().setAttribute("firstClassName", firstclassname);
		 request.getSession().setAttribute("firstclassid", firstclassid);
		 request.getRequestDispatcher("firstclass/ChangefirstClassResult.jsp").forward(request, response);
		
		
	}

	private void editFirstClass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int firstclassid = Integer.parseInt(request.getParameter("firstclassid"));
		String firstclassname = request.getParameter("firstclassname");
		String firstclassdescription = request.getParameter("firstclassdescription");
		int status = 1;
		String operator = (String) request.getSession().getAttribute("");
		java.util.Date  date=new java.util.Date();
		java.sql.Date operatedate=new java.sql.Date(date.getTime());
		
		FirstClass fc = new FirstClass();
		fc.setFirstClassName(firstclassname);
		fc.setFirstClassDescription(firstclassdescription);
		fc.setStatus(status);
		fc.setOperator(operator);
		fc.setOperateDate(operatedate);
		
		try {
			FirstClassService.getInstance().editFirstClass(fc);
			request.getRequestDispatcher("/editUser.jsp").forward(request, response);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

	private void deleteFirstClass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		    int firstclassid = Integer.parseInt(request.getParameter("chk"));
			String operator = (String) request.getSession().getAttribute("");
			java.sql.Date operatedate = (java.sql.Date) new Date();
			
			FirstClassService.getInstance().deleteFirstClass(firstclassid, operator,  operatedate);
			request.getRequestDispatcher("ChangefirstClassResult.jsp").forward(request, response);
	}

	private void selectProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String firstclassname = request.getParameter("firstclassname");
		int firstclassid = FirstClassService.getInstance().selectfirstClassIdByfirstClassName(firstclassname);	
		List<Product> products= FirstClassService.getInstance().selectProduct(firstclassid);
		 
		request.setAttribute("productresultlist", products);
		request.getSession().setAttribute("firstClassName", firstclassname);
		
		//request.getRequestDispatcher("firstclass/firstClassSerachProductsResult.jsp").forward(request, response);
		request.getRequestDispatcher("firstclass/ChangefirstClassResult.jsp").forward(request, response);
		
	}

	

}
