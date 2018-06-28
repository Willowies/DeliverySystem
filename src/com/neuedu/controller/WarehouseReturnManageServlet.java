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

import com.neuedu.model.po.Employee;
import com.neuedu.model.po.NewOrder;
import com.neuedu.model.po.SubReturnRecord;
import com.neuedu.model.po.WorkOrder;
import com.neuedu.model.service.DispatchService;
import com.neuedu.model.service.WarehouseReturnService;
import com.neuedu.model.service.WarehouseService;

/**
 * Servlet implementation class WarehouseReturnManageServlet
 */
public class WarehouseReturnManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int warehouseId = 1;
	private String operator = "王昊文";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WarehouseReturnManageServlet() {
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

		System.out.println("进入warehouse return servlet");
		
		Employee e = (Employee) request.getSession().getAttribute("employee");
		if (e==null) {
			response.sendRedirect(request.getContextPath()+"/login.jsp");
			return;
		}
		operator = e.getEmployeeName();

		String action = request.getParameter("action");
		if ("searchWorkOrder".equals(action)) {
			//搜索任务单
			searchWorkOrder(request, response);
		} else if ("returnRecord".equals(action)) {
			//记录退货
			returnRecord(request, response);
		} else if("searchReturnProduct".equals(action)){
			//搜索退货商品
			warehouseId = WarehouseReturnService.getInstance().getWarehouseId(e.getEmployeeId());
			searchReturnProduct(request, response);
		} else if("searchReturnProductByPage".equals(action)){
			//搜索退货商品
			warehouseId = WarehouseReturnService.getInstance().getWarehouseId(e.getEmployeeId());
			searchReturnProductByPage(request, response);
		} else if ("subReturnOut".equals(action)) {
			//分站退货
			warehouseId = WarehouseReturnService.getInstance().getWarehouseId(e.getEmployeeId());
			subReturnOut(request, response);
		} else if ("searchSubReturnOrder".equals(action)) {
			//搜索分站退货单
			searchSubReturnOrder(request, response);
		} else if ("centerReturnIn".equals(action)) {
			//中心退货入库
			centerReturnIn(request, response);
		} else if ("searchCenterReturnOrder".equals(action)) {
			//搜索中心退货单
			searchCenterReturnOrder(request, response);
		} else if ("centerReturnOut".equals(action)) {
			//中心退货出库
			centerReturnOut(request, response);
		}
	}
	
	
	private void centerReturnOut(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String oId = request.getParameter("orderId");
		String pId = request.getParameter("productId");
		String pQ = request.getParameter("productQuantity");
		
		int orderId = 0;
		int productId = 0;
		int productQuantity = 0;
		
		if (oId != null && !"".equals(oId)) {
			orderId = Integer.parseInt(oId);
		}
		if (pId != null && !"".equals(pId)) {
			productId = Integer.parseInt(pId);
		}
		if (pQ != null && !"".equals(pQ)) {
			productQuantity = Integer.parseInt(pQ);
		}
		
		if (orderId == 0|| productId==0 || productQuantity==0) {
			response.sendRedirect(request.getContextPath()+"/warehouse/centerReturnOut.jsp");
		}
		WarehouseReturnService.getInstance().centerReturnOut(orderId,productId,productQuantity);
		
		response.sendRedirect(request.getContextPath()+"/warehouse/centerReturnOut.jsp");
		
	}

	private void searchCenterReturnOrder(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String orderId = request.getParameter("orderId");

		System.out.println("orderId:" + orderId );
		
		int id = 0;
		if (orderId != null && !"".equals(orderId)) {
			id = Integer.parseInt(orderId);
		}
		
		if (id==0) {
			response.sendRedirect(request.getContextPath()+"/warehouse/centerReturnOut.jsp");
			return;
		}
		List<SubReturnRecord> l = WarehouseReturnService.getInstance().searchCenterReturnProduct(id);
		

		request.setAttribute("page", 1);
		request.setAttribute("orderList", l);
		
		request.getRequestDispatcher("warehouse/centerReturnOutResult.jsp").forward(request, response);
		
	}

	private void centerReturnIn(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String oId = request.getParameter("orderId");
		String pId = request.getParameter("productId");
		String pQ = request.getParameter("productQuantity");
		
		int orderId = 0;
		int productId = 0;
		int productQuantity = 0;
		
		if (oId != null && !"".equals(oId)) {
			orderId = Integer.parseInt(oId);
		}
		if (pId != null && !"".equals(pId)) {
			productId = Integer.parseInt(pId);
		}
		if (pQ != null && !"".equals(pQ)) {
			productQuantity = Integer.parseInt(pQ);
		}
		
		if (orderId == 0|| productId==0 || productQuantity==0) {
			response.sendRedirect(request.getContextPath()+"/warehouse/centerReturnIn.jsp");
		}
		WarehouseReturnService.getInstance().centerReturnIn(orderId,productId,productQuantity);
		
		response.sendRedirect(request.getContextPath()+"/warehouse/centerReturnIn.jsp");
		
	}

	private void searchSubReturnOrder(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String orderId = request.getParameter("orderId");

		System.out.println("orderId:" + orderId );
		
		int id = 0;
		if (orderId != null && !"".equals(orderId)) {
			id = Integer.parseInt(orderId);
		}
		
		if (id==0) {
			response.sendRedirect(request.getContextPath()+"/warehouse/centerReturnIn.jsp");
			return;
		}
		List<SubReturnRecord> l = WarehouseReturnService.getInstance().searchSubReturnProduct(id);
		

		request.setAttribute("page", 1);
		request.setAttribute("orderList", l);
		
		request.getRequestDispatcher("warehouse/centerReturnInResult.jsp").forward(request, response);
		
	}

	private void subReturnOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String string = request.getParameter("check");
		
		System.out.println("info:"+string);
		
		String []tokens = string.split("_");
		
		int productId = Integer.parseInt(tokens[0]);
		int productQuantity = Integer.parseInt(tokens[1]);
		Date start = new Date();
		Date end = new Date();
		try {
			start = new SimpleDateFormat("yyyy-MM-dd").parse(tokens[2]);
			end = new SimpleDateFormat("yyyy-MM-dd").parse(tokens[3]);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WarehouseReturnService.getInstance().subReturnOut(productId,productQuantity,start,end,warehouseId);
		
		request.getRequestDispatcher("warehouseReturnManageServlet?action=searchReturnProductByPage&page=1").forward(request, response);
	}
	
	private void searchReturnProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String s = request.getParameter("start");
		String e = request.getParameter("end");
		
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
		
		List<SubReturnRecord> l = WarehouseReturnService.getInstance().searchReturnProduct(start,end,warehouseId);
		List<SubReturnRecord> list = new ArrayList<SubReturnRecord>();
		
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
		
		request.getSession().setAttribute("subReturnPageNum", pageNum);
		request.setAttribute("page", 1);
		request.setAttribute("orderList", list);
		request.getSession().setAttribute("start", start);
		request.getSession().setAttribute("end", end);
		
		request.getRequestDispatcher("warehouse/subReturnOutResult.jsp").forward(request, response);
	}
	
	private void searchReturnProductByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Date start = (Date)request.getSession().getAttribute("start");
		Date end = (Date)request.getSession().getAttribute("end");

		String p = request.getParameter("page");
		int page = 1;
		if (p != null && !"".equals(p)) {
			page = Integer.parseInt(p);
		}
		
		List<SubReturnRecord> l = WarehouseReturnService.getInstance().searchReturnProduct(start,end,warehouseId);
		List<SubReturnRecord> list = new ArrayList<SubReturnRecord>();
		
		for (int i = (page-1)*5; i < page*5 ; i++) {
			if (i>=l.size()) {
				break;
			}
			list.add(l.get(i));
		}
		
		int pageNum = l.size()%5 == 0?list.size()/5:list.size()/5+1;
		
		
		request.getSession().setAttribute("subReturnPageNum", pageNum);
		request.setAttribute("page", page);
		request.setAttribute("orderList", list);
		request.getSession().setAttribute("start", start);
		request.getSession().setAttribute("end", end);
		
		request.getRequestDispatcher("warehouse/subReturnOutResult.jsp").forward(request, response);
	}
	
	private void returnRecord(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("workId");
		String quantity = request.getParameter("productQuantity");
		
		int workId = 0;
		int q = 0;
		 
		if (id != null && !"".equals(id)) {
			workId = Integer.parseInt(id);
		}
		if (quantity != null && !"".equals(quantity)) {
			q = Integer.parseInt(quantity);
		}
		
		if (workId == 0|| q ==0) {
			response.sendRedirect(request.getContextPath()+"/warehouse/returnRecord.jsp");
			return;
		}
		
		WarehouseReturnService.getInstance().returnRecord(workId,q,"王昊文");
		response.sendRedirect(request.getContextPath()+"/warehouse/returnRecord.jsp");
	}

	//搜索任务单
		private void searchWorkOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String workId = request.getParameter("orderId");

			System.out.println("workId:" + workId );
			
			int id = 0;
			if (workId != null && !"".equals(workId)) {
				id = Integer.parseInt(workId);
			}
			
			if (id==0) {
				response.sendRedirect(request.getContextPath()+"/warehouse/returnRecord.jsp");
				return;
			}
			List<WorkOrder> list = WarehouseReturnService.getInstance().searchWorkOrder(id);

			int pageNum = list.size()%5 == 0?list.size()/5:list.size()/5+1;
			request.getSession().setAttribute("pageNum", pageNum);
			request.getSession().setAttribute("returnWorkId", id);
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
			
			request.getRequestDispatcher("warehouse/returnRecordResult.jsp").forward(request, response);
		}
		
}
