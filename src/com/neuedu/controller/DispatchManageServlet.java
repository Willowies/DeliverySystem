package com.neuedu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.model.po.Client;
import com.neuedu.model.po.NewOrder;
import com.neuedu.model.po.Product;
import com.neuedu.model.po.ReturnOrder;
import com.neuedu.model.po.WorkOrder;
import com.neuedu.model.service.DispatchService;

/**
 * Servlet implementation class DispatchManageServlet
 */
public class DispatchManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	

	@Override
	public void init() throws ServletException {
		super.init();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				dispatchAuto();
			}
		}).start();;
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DispatchManageServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		System.out.println("进入dispatch servlet");

		String action = request.getParameter("action");
		if ("searchOrder".equals(action)) {
			//搜索框中搜索订单
			searchOrder(request, response);
		}else if ("searchOrderByPage".equals(action)) {
			//跳转页面的搜索
			searchOrderByPage(request, response);
		} else if ("dispatch".equals(action)) {
			//调度订单
			dispatch(request, response);
		} else if ("searchLackOrder".equals(action)) {
			//搜索缺货订单
			searchLackOrder(request, response);
		} else if ("searchLackOrderByPage".equals(action)) {
			//搜索缺货订单
			searchLackOrderByPage(request, response);
		} else if ("modify".equals(action)) {
			//更改状态
			modify(request, response);
		} else if ("searchWorkOrder".equals(action)) {
			searchWorkOrder(request, response);
		} else if ("searchWorkOrderByPage".equals(action)) {
			searchWorkOrderByPage(request, response);
		}
	}
	
	//改变订单状态
	private void modify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("check");
		int orderId = 0;
		
		if (id!=null && !"".equals(id)) {
			orderId = Integer.parseInt(id);
		}
		
		if (orderId != 0) {
			DispatchService.getInstance().modify(orderId,"王昊文");
			response.sendRedirect(request.getContextPath()+"/dispatchManageServlet?action=searchLackOrderByPage&page=1");
		}else {
			response.sendRedirect(request.getContextPath()+"/dispatch/dispatchHome.jsp");
		}
	}
	
	//搜索缺货订单
	private void searchLackOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NewOrder order =  parseNewOrder(request, response);
		order.setOrderState(3);
		
		List<NewOrder> l = DispatchService.getInstance().searchLackOrder(order);
		List<NewOrder> list = new ArrayList<NewOrder>();
		
		for (int i = 0; i < l.size(); i++) {
			if (i>5) {
				break;
			}
			list.add(l.get(i));
		}
		
		int pageNum = l.size()%5 == 0?list.size()/5:list.size()/5+1;
		if (list.isEmpty()) {
			pageNum = 1;
		}
		
		request.getSession().setAttribute("modifyPageNum", pageNum);
		request.setAttribute("page", 1);
		request.setAttribute("orderList", list);
		request.getSession().setAttribute("modifyOrder", order);
		
		request.getRequestDispatcher("dispatch/modifyOrderStatusResult.jsp").forward(request, response);
	}
	
	//分页 查询缺货
	private void searchLackOrderByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NewOrder order =  (NewOrder) request.getSession().getAttribute("modifyOrder");
		String p = request.getParameter("page");
		int page = 1;
		if (p != null && !"".equals(p)) {
			page = Integer.parseInt(p);
		}
		
		List<NewOrder> l = DispatchService.getInstance().searchLackOrder(order);
		List<NewOrder> list = new ArrayList<NewOrder>();
		
		for (int i = (page-1)*5; i < page*5 ; i++) {
			if (i>=l.size()) {
				break;
			}
			list.add(l.get(i));
		}
		
		int pageNum = l.size()%5 == 0?list.size()/5:list.size()/5+1;
		if (list.isEmpty()) {
			pageNum = 1;
		}
		
		request.getSession().setAttribute("modifyPageNum", pageNum);
		request.setAttribute("page", page);
		request.setAttribute("orderList", list);
		
		request.getRequestDispatcher("dispatch/modifyOrderStatusResult.jsp").forward(request, response);
	}
	
	//搜索订单
	private void searchOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String orderType = request.getParameter("orderType");
		//通过订单类型查询特定的订单
		if (orderType == null) {
			request.getRequestDispatcher("dispatchGoods.jsp").forward(request, response);
		}else if (orderType.equals("1")) {
			NewOrder order = parseNewOrder(request, response);
			List<NewOrder> l = DispatchService.getInstance().searchNewOrder(order);
			List<NewOrder> list = new ArrayList<NewOrder>();
			
			for (int i = 0; i < l.size(); i++) {
				if (i>5) {
					break;
				}
				list.add(l.get(i));
			}
			
			int pageNum = l.size()%5 == 0?list.size()/5:list.size()/5+1;
			
			request.getSession().setAttribute("type", 1);
			request.getSession().setAttribute("dispatchPageNum", pageNum);
			request.setAttribute("page", 1);
			request.setAttribute("orderList", list);
			request.getSession().setAttribute("dispatchOrder", order);
			
			request.getRequestDispatcher("dispatch/dispatchGoodsResult.jsp").forward(request, response);
		}else if (orderType.equals("2")) {
			ReturnOrder returnOrder = parseReturnOrder(request, response);
			
			
			
			List<ReturnOrder> l = DispatchService.getInstance().searchReturnOrder(returnOrder);
			List<ReturnOrder> list = new ArrayList<ReturnOrder>();
			
			for (int i = 0; i < l.size(); i++) {
				if (i>5) {
					break;
				}
				list.add(l.get(i));
			}
			
			int pageNum = l.size()%5 == 0?list.size()/5:list.size()/5+1;
			
			request.getSession().setAttribute("type", 2);
			request.getSession().setAttribute("dispatchPageNum", pageNum);
			request.setAttribute("page", 1);
			request.setAttribute("orderList", list);
			request.getSession().setAttribute("dispatchOrder", returnOrder);
			
			request.getRequestDispatcher("dispatch/dispatchGoodsResult.jsp").forward(request, response);
		}
		
	}
	
	//跳转页面的搜索
	private void searchOrderByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int orderType =(Integer) request.getSession().getAttribute("type");
		String p = request.getParameter("page");
		int page = 1;
		if (p != null && !"".equals(p)) {
			page = Integer.parseInt(p);
		}
		
		//通过订单类型查询特定的订单
		if (orderType == 0) {
			request.getRequestDispatcher("dispatchGoods.jsp").forward(request, response);
		}else if (orderType == 1) {
			NewOrder order =(NewOrder) request.getSession().getAttribute("dispatchOrder");
			List<NewOrder> l = DispatchService.getInstance().searchNewOrder(order);
			List<NewOrder> list = new ArrayList<NewOrder>();
			
			for (int i = (page-1)*5; i < page*5 ; i++) {
				if (i>=l.size()) {
					break;
				}
				list.add(l.get(i));
			}
			
			int pageNum = l.size()%5 == 0?list.size()/5:list.size()/5+1;
			
			request.getSession().setAttribute("type", 1);
			request.getSession().setAttribute("pageNum", pageNum);
			request.setAttribute("page", page);
			request.setAttribute("orderList", list);
			
			request.getRequestDispatcher("dispatch/dispatchGoodsResult.jsp").forward(request, response);
		}else if (orderType == 2) {
			ReturnOrder returnOrder = (ReturnOrder) request.getSession().getAttribute("dispatchOrder");
			
			List<ReturnOrder> l = DispatchService.getInstance().searchReturnOrder(returnOrder);
			List<ReturnOrder> list = new ArrayList<ReturnOrder>();
			
			for (int i = 0; i < l.size(); i++) {
				if (i>5) {
					break;
				}
				list.add(l.get(i));
			}
			
			int pageNum = l.size()%5 == 0?list.size()/5:list.size()/5+1;
			
			request.getSession().setAttribute("type", 2);
			request.getSession().setAttribute("dispatchPageNum", pageNum);
			request.setAttribute("page", page);
			request.setAttribute("orderList", list);
			request.getSession().setAttribute("dispatchOrder", returnOrder);
			
			request.getRequestDispatcher("dispatch/dispatchGoodsResult.jsp").forward(request, response);
		}
	}
	
	//调度订单
	private void dispatch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("orderId");
		String warehouseName = request.getParameter("query");
		String remark = request.getParameter("remark");
		String type =  request.getParameter("orderType");
		
		int orderType = 0;
		int orderId = 0;
		
		
		if (type.contains("新订")) {
			orderType = 1;
		}else if (type.contains("退货")) {
			orderType = 2;
		}
		
		if (id != null && !"".equals(id)) {
			orderId = Integer.parseInt(id);
		}
		if (remark != null && !"".equals(remark)) {
			remark = "";
		}
		
		DispatchService.getInstance().dispatchOrder(orderId, orderType, warehouseName, remark, "王昊文");
		
		response.sendRedirect(request.getContextPath()+"/dispatchManageServlet?action=searchOrderByPage&page=1");
		
	}

	//通过传递的信息封装为新订订单
	private NewOrder parseNewOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String createDate = request.getParameter("createDate");
		String requireDate = request.getParameter("requireDate");
		String dispatchOrNot = request.getParameter("dispatchOrNot");

		System.out.println("createDate:" + createDate + " requireDate:" + requireDate + " dispatchOrNot:"
				+ dispatchOrNot);
		
		NewOrder newOrder = new NewOrder();
		Product p = new Product();
		Client c = new Client();
		newOrder.setClient(c);
		newOrder.setProduct(p);
		if (createDate != null && !"".equals(createDate)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date d = sdf.parse(createDate);
				newOrder.setGenerateDate(d);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (requireDate != null && !"".equals(requireDate)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date d = sdf.parse(requireDate);
				newOrder.setRequireDate(d);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (dispatchOrNot != null && !"".equals(dispatchOrNot)) {
			newOrder.setOrderState(1);
		}
		return newOrder;
	}
	
	//通过传递的信息封装为退货订单
	private ReturnOrder parseReturnOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String createDate = request.getParameter("createDate");
		String requireDate = request.getParameter("requireDate");
		String dispatchOrNot = request.getParameter("dispatchOrNot");

		System.out.println("createDate:" + createDate + " requireDate:" + requireDate + " dispatchOrNot:"
				+ dispatchOrNot);
		
		ReturnOrder returnOrder = new ReturnOrder();
		returnOrder.setNewOrder(new NewOrder());
		if (createDate != null  && !"".equals(createDate)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date d = sdf.parse(createDate);
				returnOrder.setGenerateDate(d);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (requireDate != null  && !"".equals(requireDate)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date d = sdf.parse(requireDate);
				returnOrder.setReturnDate(d);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (dispatchOrNot != null  && !"".equals(dispatchOrNot)) {
			returnOrder.setOrderState(1);
		}
		return returnOrder;
	}
	
	
	//搜索任务单
	private void searchWorkOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String workId = request.getParameter("workId");
		String requireDate = request.getParameter("requireDate");
		String warehouseName = request.getParameter("query");
		String orderType = request.getParameter("orderType");
		String orderStatus = request.getParameter("orderStatus");
		String clientName = request.getParameter("clientName");
		String clientPhone = request.getParameter("clientPhone");

		System.out.println("workId:" + workId + " requireDate:" + requireDate + " warehouseName:"
				+ warehouseName + " orderType:"+ orderType+ " orderStatus:"+ orderStatus
				+ " clientName:"+ clientName+ " clientPhone:"+ clientPhone);
		
		WorkOrder order = new WorkOrder();
		if (workId != null && !"".equals(workId)) {
			order.setWorkId(Integer.parseInt(workId));
		}
		if (requireDate != null && !"".equals(requireDate)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date d = sdf.parse(requireDate);
				order.setRequireDate(d);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (warehouseName != null && !"".equals(warehouseName)) {
			order.setWarehouseName(warehouseName);
		}
		if (orderType != null && !"".equals(orderType)) {
			order.setWorkType(Integer.parseInt(orderType));
		}
		if (orderStatus != null && !"".equals(orderStatus)) {
			order.setWorkStatus(Integer.parseInt(orderStatus));
		}
		if (clientName != null && !"".equals(clientName)) {
			order.setClientName(clientName);
		}
		if (clientPhone != null && !"".equals(clientPhone)) {
			order.setClientPhone(clientPhone);
		}
		List<WorkOrder> list = DispatchService.getInstance().searchWorkOrder(order);
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		Date d = null;
//		try {
//			d = sdf.parse("2018-06-22");
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		WorkOrder o = new WorkOrder();
//		o.setWorkId(10001);
//		o.setWorkType(1);
//		o.setWorkStatus(1);
//		o.setRequireDate(d);
//		o.setWarehouseName("warehousename");
//		o.setClientName("王昊文");
//		o.setClientPhone("12341234123");
//		List<WorkOrder> list = new ArrayList<>();
//		for (int i = 0; i < 12; i++) {
//			list.add(o);
//		}
		int pageNum = list.size()%5 == 0?list.size()/5:list.size()/5+1;
		request.getSession().setAttribute("pageNum", pageNum);
		request.getSession().setAttribute("workOrderListOriginal", list);
		List<WorkOrder> pageList = new ArrayList<WorkOrder>();
		if (list.size()>5) {
			for (int i = 0; i < 5; i++) {
				pageList.add(list.get(i));
			}
		}else if(!list.isEmpty()){
			for (int i = 0; i < list.size(); i++) {
				pageList.add(list.get(i));
			}
		}
		request.setAttribute("page", 1);
		request.setAttribute("workOrderList", pageList);
		
		request.getRequestDispatcher("dispatch/searchWorkOrderResult.jsp").forward(request, response);
	}
	
	
	//分页搜索任务单
	private void searchWorkOrderByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page");
		int p = Integer.parseInt(page);
		int s = (p-1)*5;
		int e = p*5-1;
		
		List<WorkOrder> list = (List<WorkOrder>) request.getSession().getAttribute("workOrderListOriginal");
		List<WorkOrder> pageList = new ArrayList<WorkOrder>();
		
		e = e+1>list.size()?list.size()-1:e;
		if(!list.isEmpty()){
			for (int i = s; i <= e; i++) {
				pageList.add(list.get(i));
			}
		}
		request.setAttribute("page", p);
		request.setAttribute("workOrderList", pageList);
		
		request.getRequestDispatcher("dispatch/searchWorkOrderResult.jsp").forward(request, response);
	}

	//自动调度
	private void dispatchAuto() {
		 
	}
}
