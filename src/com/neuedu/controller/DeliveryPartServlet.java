package com.neuedu.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.model.po.DeliveryReturnProduct;
import com.neuedu.model.po.DeliveryStockProduct;
import com.neuedu.model.po.DeliveryWarehouseOrder;
import com.neuedu.model.po.SubReturnRecord;
import com.neuedu.model.service.DeliveryPartService;
import com.neuedu.model.service.WarehouseReturnService;
import com.sun.javafx.collections.MappingChange.Map;

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
		} else if ("searchLackProductByPage".equals(action)) {
			//跳转页面的搜索
			searchLackProductByPage(request, response);
		} else if ("stock".equals(action)) {
			//进货
			stock(request, response);
		} else if("searchReturnProduct".equals(action)){
			//搜索退货商品
			searchReturnProduct(request, response);
		} else if("searchReturnProductByPage".equals(action)){
			//搜索退货商品
			searchReturnProductByPage(request, response);
		} else if("returnProduct".equals(action)){
			//退货
			returnProduct(request, response);
		} else if("searchWarehouseProduct".equals(action)){
			//查询预警值和最大值
			searchWarehouseProduct(request, response);
		} else if("searchWarehouseProductByPage".equals(action)){
			//查询预警值和最大值
			searchWarehouseProductByPage(request, response);
		} else if("modify".equals(action)){
			//查询预警值和最大值
			modify(request, response);
		} else if("searchWarehouseValue".equals(action)){
			//查询库存量
			searchWarehouseValue(request, response);
		} else if("searchWarehouseValueByPage".equals(action)){
			//查询库存量
			searchWarehouseValueByPage(request, response);
		} else if("searchOrder".equals(action)){
			//查询出入单
			searchOrder(request, response);
		} else if("searchOrderByPage".equals(action)){
			//查询出入单
			searchOrderByPage(request, response);
		} else if("orderStatistics".equals(action)){
			//订购查询
			orderStatistics(request, response);
		} else if("substationStatistics".equals(action)){
			//分站查询
			substationStatistics(request, response);
		} else if("substationStatisticsByPage".equals(action)){
			//分站查询
			substationStatisticsByPage(request, response);
		} else if("satisfyStatistics".equals(action)){
			//满意度查询
			satisfyStatistics(request, response);
		}
		
	}
	

	private void satisfyStatistics(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String s = request.getParameter("start3");
		String e = request.getParameter("end3");
		
		System.out.println("startDate:"+s+" endDate:"+e);
		
		Date start = new Date();
		Date end = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			start = sdf.parse(s);
			end =  sdf.parse(e);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		HashMap<String, String> result = DeliveryPartService.getInstance().satisfyStatistics(start,end);
		
		String n = result.get("neworder");
		String r = result.get("returnorder");
		
		request.setAttribute("newV", n);
		request.setAttribute("returnV", r);
		
		request.getRequestDispatcher("delivery/satisfyStatistics.jsp").forward(request, response);
	}

	private void substationStatistics(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String s = request.getParameter("start2");
		String e = request.getParameter("end2");
		
		System.out.println("startDate:"+s+" endDate:"+e);
		
		Date start = new Date();
		Date end = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			start = sdf.parse(s);
			end =  sdf.parse(e);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		List<DeliveryWarehouseOrder> l = DeliveryPartService.getInstance().substationStatistics(start,end);
		List<DeliveryWarehouseOrder> list = new ArrayList<DeliveryWarehouseOrder>();
		
		if (!l.isEmpty()) {
			for (int i = 0; i < l.size(); i++) {
				if (i>=5) {
					break;
				}
				list.add(l.get(i));
			}
		}
		
		int pageNum = l.size()%5 == 0?l.size()/5:l.size()/5+1;
		if (list.isEmpty()) {
			pageNum = 1;	
		}
		
		request.getSession().setAttribute("substationStatisticsPageNum", pageNum);
		request.setAttribute("page", 1);
		request.setAttribute("orderList", list);
		request.getSession().setAttribute("substationStatisticsStart", start);
		request.getSession().setAttribute("substationStatisticsEnd", end);
		
		request.getRequestDispatcher("delivery/substationStatistics.jsp").forward(request, response);
	}
	
	private void substationStatisticsByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Date start = (Date)request.getSession().getAttribute("substationStatisticsStart");
		Date end = (Date)request.getSession().getAttribute("substationStatisticsEnd");
		
		String p = request.getParameter("page");
		int page = 1;
		if (p != null && !"".equals(p)) {
			page = Integer.parseInt(p);
		}
		
		List<DeliveryWarehouseOrder> l = DeliveryPartService.getInstance().substationStatistics(start,end);
		List<DeliveryWarehouseOrder> list = new ArrayList<DeliveryWarehouseOrder>();
		
		if (!l.isEmpty()) {
			for (int i = (page-1)*5; i < page*5 ; i++) {
				if (i>=l.size()) {
					break;
				}
				list.add(l.get(i));
			}
		}
		
		int pageNum = l.size()%5 == 0?l.size()/5:l.size()/5+1;
		if (list.isEmpty()) {
			pageNum = 1;	
		}
		
		request.getSession().setAttribute("substationStatisticsPageNum", pageNum);
		request.setAttribute("page", page);
		request.setAttribute("orderList", list);
		request.getSession().setAttribute("substationStatisticsStart", start);
		request.getSession().setAttribute("substationStatisticsEnd", end);
		
		request.getRequestDispatcher("delivery/substationStatistics.jsp").forward(request, response);
	}

	private void orderStatistics(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String s = request.getParameter("start1");
		String e = request.getParameter("end1");
		
		System.out.println("startDate:"+s+" endDate:"+e);
		
		Date start = new Date();
		Date end = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			start = sdf.parse(s);
			end =  sdf.parse(e);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		List<DeliveryWarehouseOrder> list = DeliveryPartService.getInstance().orderStatistics(start,end);

		request.setAttribute("orderList", list);
		request.getRequestDispatcher("delivery/orderStatistics.jsp").forward(request, response);
		
	}

	private void searchOrder(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
		String s = request.getParameter("start");
		String e = request.getParameter("end");
		String productName = request.getParameter("productName");
		
		System.out.println("startDate:"+s+" endDate:"+e);
		
		Date start = new Date();
		Date end = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			start = sdf.parse(s);
			end =  sdf.parse(e);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		List<DeliveryWarehouseOrder> l = DeliveryPartService.getInstance().searchOrder(productName,start,end);
		List<DeliveryWarehouseOrder> list = new ArrayList<DeliveryWarehouseOrder>();
		
		if (!l.isEmpty()) {
			for (int i = 0; i < l.size(); i++) {
				if (i>=5) {
					break;
				}
				list.add(l.get(i));
			}
		}
		
		int pageNum = l.size()%5 == 0?l.size()/5:l.size()/5+1;
		if (list.isEmpty()) {
			pageNum = 1;	
		}
		
		request.getSession().setAttribute("warehouseOrderPageNum", pageNum);
		request.setAttribute("page", 1);
		request.setAttribute("orderList", list);
		request.getSession().setAttribute("warehouseOrderStart", start);
		request.getSession().setAttribute("warehouseOrderEnd", end);
		request.getSession().setAttribute("warehouseOrderProductName", productName);
		
		
		request.getRequestDispatcher("delivery/warehouseOrderSearchResult.jsp").forward(request, response);
		
	}

	private void searchOrderByPage(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
		Date start = (Date)request.getSession().getAttribute("returnStart");
		Date end = (Date)request.getSession().getAttribute("returnEnd");
		String productName = request.getParameter("warehouseOrderProductName");
		
		String p = request.getParameter("page");
		int page = 1;
		if (p != null && !"".equals(p)) {
			page = Integer.parseInt(p);
		}
		
		List<DeliveryWarehouseOrder> l = DeliveryPartService.getInstance().searchOrder(productName,start,end);
		List<DeliveryWarehouseOrder> list = new ArrayList<DeliveryWarehouseOrder>();
		
		if (!l.isEmpty()) {
			for (int i = (page-1)*5; i < page*5 ; i++) {
				if (i>=l.size()) {
					break;
				}
				list.add(l.get(i));
			}
		}
		
		int pageNum = l.size()%5 == 0?l.size()/5:l.size()/5+1;
		if (list.isEmpty()) {
			pageNum = 1;
		}
		
		request.getSession().setAttribute("warehouseOrderPageNum", pageNum);
		request.setAttribute("page", page);
		request.setAttribute("orderList", list);
		request.getSession().setAttribute("warehouseOrderStart", start);
		request.getSession().setAttribute("warehouseOrderEnd", end);
		
		
		request.getRequestDispatcher("delivery/warehouseOrderSearchResult.jsp").forward(request, response);
	}

	private void searchWarehouseValue(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
		String productName = request.getParameter("productName");
		String warehouseName = request.getParameter("warehouseName");
		
		List<DeliveryStockProduct> l = DeliveryPartService.getInstance().searchWarehouseValue(productName,warehouseName);
		List<DeliveryStockProduct> list = new ArrayList<DeliveryStockProduct>();
		
		if (!l.isEmpty()) {
			for (int i = 0; i < l.size(); i++) {
				if (i>=5) {
					break;
				}
				list.add(l.get(i));
			}
		}
		
		int pageNum = l.size()%5 == 0?l.size()/5:l.size()/5+1;
		if (list.isEmpty()) {
			pageNum = 1;
		}
		
		request.getSession().setAttribute("warehouseValuePageNum", pageNum);
		request.setAttribute("page", 1);
		request.setAttribute("orderList", list);
		request.getSession().setAttribute("warehouseValueProductName", productName);
		request.getSession().setAttribute("warehouseValueWarehouseName", warehouseName);
		
		request.getRequestDispatcher("delivery/warehouseValueResult.jsp").forward(request, response);
		
	}

	private void searchWarehouseValueByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String productName = (String)request.getSession().getAttribute("warehouseValueProductName");
		String warehouseName = (String)request.getSession().getAttribute("warehouseValueWarehouseName");
		
		String p = request.getParameter("page");
		int page = 1;
		if (p != null && !"".equals(p)) {
			page = Integer.parseInt(p);
		}
		
		List<DeliveryStockProduct> l = DeliveryPartService.getInstance().searchWarehouseValue(productName,warehouseName);
		List<DeliveryStockProduct> list = new ArrayList<DeliveryStockProduct>();
		
		if (!l.isEmpty()) {
			for (int i = (page-1)*5; i < page*5 ; i++) {
				if (i>=l.size()) {
					break;
				}
				list.add(l.get(i));
			}
		}
		
		int pageNum = l.size()%5 == 0?l.size()/5:l.size()/5+1;
		if (list.isEmpty()) {
			pageNum = 1;
		}
		
		request.getSession().setAttribute("warehouseValuePageNum", pageNum);
		request.setAttribute("page", page);
		request.setAttribute("orderList", list);
		
		request.getRequestDispatcher("delivery/warehouseValueResult.jsp").forward(request, response);
		
	}

	private void modify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pId = request.getParameter("productId");
		int productId=0;
		if (pId != null && !"".equals(pId)) {
			productId = Integer.parseInt(pId);
		}
		
		String w = request.getParameter("warning"+productId);
		String m = request.getParameter("max"+productId);
		int warning = 0;
		int max = 0;
		
		if (w != null && !"".equals(w)) {
			warning = Integer.parseInt(w);
		}
		if (m != null && !"".equals(m)) {
			max = Integer.parseInt(m);
		}
		
		if (productId==0 || max==0 || warning==0) {
			response.sendRedirect(request.getContextPath()+"/delivery/warehouseProductSetting.jsp");
			return;
		}
		
		DeliveryPartService.getInstance().modify(productId,warning,max,"王昊文");
		response.sendRedirect(request.getContextPath()+"/deliveryPartServlet?action=searchWarehouseProductByPage&page=1");
		
	}

	private void searchWarehouseProduct(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
		String productName = request.getParameter("productName");
		
		System.out.println("productName:"+productName);
		
		if (productName==null && !"".equals(productName)) {
			response.sendRedirect(request.getContextPath()+"/delivery/warehouseProductSetting.jsp");
			return;
		}
		
		List<DeliveryStockProduct> l = DeliveryPartService.getInstance().searchWarehouseProduct(productName);
		List<DeliveryStockProduct> list = new ArrayList<DeliveryStockProduct>();
		
		if (!l.isEmpty()) {
			for (int i = 0; i < l.size(); i++) {
				if (i>=5) {
					break;
				}
				list.add(l.get(i));
			}
		}
		
		int pageNum = l.size()%5 == 0?l.size()/5:l.size()/5+1;
		if (list.isEmpty()) {
			pageNum = 1;
		}
		
		request.getSession().setAttribute("warehouseProductPageNum", pageNum);
		request.setAttribute("page", 1);
		request.setAttribute("orderList", list);
		request.getSession().setAttribute("warehouseProductName", productName);
		
		request.getRequestDispatcher("delivery/warehouseProductSettingResult.jsp").forward(request, response);
		
	}

	private void searchWarehouseProductByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String productName = (String)request.getSession().getAttribute("warehouseProductName");
		
		String p = request.getParameter("page");
		int page = 1;
		if (p != null && !"".equals(p)) {
			page = Integer.parseInt(p);
		}
		
		List<DeliveryStockProduct> l = DeliveryPartService.getInstance().searchWarehouseProduct(productName);
		List<DeliveryStockProduct> list = new ArrayList<DeliveryStockProduct>();
		
		if (!l.isEmpty()) {
			for (int i = (page-1)*5; i < page*5 ; i++) {
				if (i>=l.size()) {
					break;
				}
				list.add(l.get(i));
			}
		}
		
		int pageNum = l.size()%5 == 0?l.size()/5:l.size()/5+1;
		if (list.isEmpty()) {
			pageNum = 1;
		}
		
		request.getSession().setAttribute("warehouseProductPageNum", pageNum);
		request.setAttribute("page", page);
		request.setAttribute("orderList", list);
		request.getSession().setAttribute("warehouseProductName", productName);
		
		request.getRequestDispatcher("delivery/warehouseProductSettingResult.jsp").forward(request, response);
		
	}
	
	private void returnProduct(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
		String pId = request.getParameter("productId");
		System.out.println("return:pid"+pId);
		int productId=0;
		if (pId != null && !"".equals(pId)) {
			productId = Integer.parseInt(pId);
		}
		
		String pQ = request.getParameter("productQuantity"+productId);
		int productQuantity = 0;
		
		if (pQ != null && !"".equals(pQ)) {
			productQuantity = Integer.parseInt(pQ);
		}
		
		if (productId==0 || productQuantity==0) {
			response.sendRedirect(request.getContextPath()+"/delivery/returnManage.jsp");
			return;
		}
		
		DeliveryPartService.getInstance().returnProduct(productId,productQuantity,"王昊文");
		response.sendRedirect(request.getContextPath()+"/deliveryPartServlet?action=searchReturnProductByPage&page=1");
		
	}

	private void searchReturnProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String s = request.getParameter("start");
		String e = request.getParameter("end");
		String supName = request.getParameter("supName");
		String productCode = request.getParameter("productCode");
		
		System.out.println("startDate:"+s+" endDate:"+e);
		
		Date start = new Date();
		Date end = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			start = sdf.parse(s);
			end =  sdf.parse(e);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		List<DeliveryReturnProduct> l = DeliveryPartService.getInstance().searchReturnProduct(supName,productCode,start,end);
		List<DeliveryReturnProduct> list = new ArrayList<DeliveryReturnProduct>();
		
		if (!l.isEmpty()) {
			for (int i = 0; i < l.size(); i++) {
				if (i>=5) {
					break;
				}
				list.add(l.get(i));
			}
		}
		
		int pageNum = l.size()%5 == 0?l.size()/5:l.size()/5+1;
		if (list.isEmpty()) {
			pageNum = 1;
		}
		
		request.getSession().setAttribute("returnPageNum", pageNum);
		request.setAttribute("page", 1);
		request.setAttribute("orderList", list);
		request.getSession().setAttribute("returnStart", start);
		request.getSession().setAttribute("returnEnd", end);
		request.getSession().setAttribute("returnProductCode", productCode);
		request.getSession().setAttribute("returnSupName", supName);
		
		request.getRequestDispatcher("delivery/returnManageResult.jsp").forward(request, response);
	}
	
	private void searchReturnProductByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Date start = (Date)request.getSession().getAttribute("returnStart");
		Date end = (Date)request.getSession().getAttribute("returnEnd");

		String supName = request.getParameter("returnSupName");
		String productCode = request.getParameter("returnProductCode");
		
		String p = request.getParameter("page");
		int page = 1;
		if (p != null && !"".equals(p)) {
			page = Integer.parseInt(p);
		}
		
		List<DeliveryReturnProduct> l = DeliveryPartService.getInstance().searchReturnProduct(supName,productCode,start,end);
		List<DeliveryReturnProduct> list = new ArrayList<DeliveryReturnProduct>();
		
		for (int i = (page-1)*5; i < page*5 ; i++) {
			if (i>=l.size()) {
				break;
			}
			list.add(l.get(i));
		}
		
		int pageNum = l.size()%5 == 0?l.size()/5:l.size()/5+1;
		
		
		request.getSession().setAttribute("returnPageNum", pageNum);
		request.setAttribute("page", page);
		request.setAttribute("orderList", list);
		
		request.getRequestDispatcher("delivery/returnManageResult.jsp").forward(request, response);
	}
	
	private void stock(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String pId = request.getParameter("productId");
		int productId=0;
		if (pId != null && !"".equals(pId)) {
			productId = Integer.parseInt(pId);
		}
		
		String pQ = request.getParameter("productQuantity"+productId);
		int productQuantity = 0;
		
		if (pQ != null && !"".equals(pQ)) {
			productQuantity = Integer.parseInt(pQ);
		}
		
		if (productId==0 || productQuantity==0) {
			response.sendRedirect(request.getContextPath()+"/delivery/stockManage.jsp");
		}
		
		DeliveryPartService.getInstance().stock(productId,productQuantity,"王昊文");
		response.sendRedirect(request.getContextPath()+"/deliveryPartServlet?action=searchLackProductByPage&page=1");
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
				if (i>=5) {
					break;
				}
				list.add(l.get(i));
			}
		}
		
		int pageNum = l.size()%5 == 0?l.size()/5:l.size()/5+1;
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
		
		int pageNum = l.size()%5 == 0?l.size()/5:l.size()/5+1;
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
