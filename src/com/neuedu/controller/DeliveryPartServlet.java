package com.neuedu.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.model.po.DeliveryStockProduct;
import com.neuedu.model.po.SubReturnRecord;
import com.neuedu.model.service.DeliveryPartService;
import com.neuedu.model.service.WarehouseReturnService;

/**
 * Servlet implementation class DeliveryPartServlet
 */
public class DeliveryPartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeliveryPartServlet() {
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
		
		System.out.println("进入dispatch servlet");

		String action = request.getParameter("action");
		if ("searchLackProduct".equals(action)) {
			//搜索框中搜索订单
			searchLackProduct(request, response);
		}else if ("searchLackProductByPage".equals(action)) {
			//跳转页面的搜索
			searchLackProductByPage(request, response);
		} 
	}
	
	private void searchLackProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String productName = request.getParameter("productName");
		
		System.out.println("productName:"+productName);
		
		if (productName==null && !"".equals(productName)) {
			response.sendRedirect(request.getContextPath()+"/delivery/stockManage.jsp");
			return;
		}
		
		List<DeliveryStockProduct> l = DeliveryPartService.getInstance().searchLackProduct(productName);
		List<DeliveryStockProduct> list = new ArrayList<DeliveryStockProduct>();
		
		if (!l.isEmpty()) {
			for (int i = 0; i < l.size(); i++) {
				if (i>5) {
					break;
				}
				list.add(l.get(i));
			}
		}
		
		int pageNum = l.size()%5 == 0?list.size()/5:list.size()/5+1;
		if (list.isEmpty()) {
			pageNum = 1;
		}
		
		request.getSession().setAttribute("stockPageNum", pageNum);
		request.setAttribute("page", 1);
		request.setAttribute("orderList", list);
		request.getSession().setAttribute("stockProduct", productName);
		
		request.getRequestDispatcher("delivery/stockManageResult.jsp").forward(request, response);
	}

	private void searchLackProductByPage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String productName = (String)request.getSession().getAttribute("stockProduct");
		
		String p = request.getParameter("page");
		int page = 1;
		if (p != null && !"".equals(p)) {
			page = Integer.parseInt(p);
		}
		
		List<DeliveryStockProduct> l = DeliveryPartService.getInstance().searchLackProduct(productName);
		List<DeliveryStockProduct> list = new ArrayList<DeliveryStockProduct>();
		
		if (!l.isEmpty()) {
			for (int i = (page-1)*5; i < page*5 ; i++) {
				if (i>=l.size()) {
					break;
				}
				list.add(l.get(i));
			}
		}
		
		int pageNum = l.size()%5 == 0?list.size()/5:list.size()/5+1;
		if (list.isEmpty()) {
			pageNum = 1;
		}
		
		request.getSession().setAttribute("stockPageNum", pageNum);
		request.setAttribute("page", page);
		request.setAttribute("orderList", list);
		request.getSession().setAttribute("stockProduct", productName);
		
		request.getRequestDispatcher("delivery/stockManageResult.jsp").forward(request, response);
		
	}
}
