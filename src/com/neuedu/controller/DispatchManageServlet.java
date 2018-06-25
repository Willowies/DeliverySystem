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
import com.oracle.jrockit.jfr.Producer;

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
			
			searchOrder(request, response);
			
		}else if ("searchOrderByPage".equals(action)) {
			
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
				List<NewOrder> list = DispatchService.getInstance().searchNewOrderByPage(order,1,6);
				
				for(NewOrder o:list) {
					Product p1 = new Product();
					p1.setProductName("冰箱");
					o.setProduct(p1);
				}
				int pageNum = l.size()%6 == 0?list.size()/6:list.size()/6+1;
				
				request.getSession().setAttribute("type", 1);
				request.getSession().setAttribute("pageNum", pageNum);
				request.setAttribute("page", 1);
				request.setAttribute("orderList", list);
				
				request.getRequestDispatcher("dispatch/dispatchGoodsResult.jsp").forward(request, response);
			}else if (orderType == 2) {
				ReturnOrder returnOrder = parseReturnOrder(request, response);
				
			}
			
		} else if ("dispatch".equals(action)) {
			//调度订单
			dispatch(request, response);
			
		} else if ("searchLackOrder".equals(action)) {
			//搜索缺货订单
			
		} else if ("modify".equals(action)) {

		} else if ("searchWorkOrder".equals(action)) {
			searchWorkOrder(request, response);
		} else if ("searchWorkOrderByPage".equals(action)) {
			searchWorkOrderByPage(request, response);
		}
	}
	
	
	private void searchOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String orderType = request.getParameter("orderType");
		System.out.println(orderType);
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
			
			int pageNum = l.size()%6 == 0?list.size()/6:list.size()/6+1;
			
			request.getSession().setAttribute("type", 1);
			request.getSession().setAttribute("dispatchPageNum", pageNum);
			request.setAttribute("page", 1);
			request.setAttribute("orderList", list);
			request.getSession().setAttribute("dispatchOrder", order);
			
			request.getRequestDispatcher("dispatch/dispatchGoodsResult.jsp").forward(request, response);
		}else if (orderType.equals("2")) {
			ReturnOrder returnOrder = parseReturnOrder(request, response);
			
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
		String enoughOrNot = request.getParameter("enoughOrNot");

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

	private void dispatchAuto() {
		 
	}
}
