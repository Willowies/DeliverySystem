package com.neuedu.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.model.po.CancelOrder;
import com.neuedu.model.po.Client;
import com.neuedu.model.po.Employee;
import com.neuedu.model.po.NewOrder;
import com.neuedu.model.po.Product;
import com.neuedu.model.po.ReturnOrder;
import com.neuedu.model.service.CancelOrderService;
import com.neuedu.model.service.ClientService;
import com.neuedu.model.service.NewOrderService;
import com.neuedu.model.service.ProductService;
import com.neuedu.model.service.ReturnOrderService;


/**
 * Servlet implementation class OrderServlet
 */
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderServlet() {
        super();
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String action = req.getParameter("action");
		System.out.println("action = "+action);
		if("locateClient".equals(action)){
			locateClient(req,resp);
		}
		if("selectProduct".equals(action)){
			selectProduct(req,resp);
		}
		if("newOrder".equals(action)){
			try {
				createNewOrder(req,resp);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if("creatCancelOrder".equals(action)){
			try {
				createCancelOrder(req,resp);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if("selectNewOrderForCancel".equals(action)){
			try {
				selectNewOrderForCancel(req,resp);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if("creatReturnOrder".equals(action)){
			try {
				createReturnOrder(req,resp);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if("selectNewOrderForReturn".equals(action)){
			try {
				selectNewOrderForReturn(req,resp);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

	}
	private void locateClient(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException{
		/*定位客户*/
		Client c = new Client();
		String clientIc = (String)req.getParameter("clientIc");
		String clientName = (String)req.getParameter("clientName");
		String clientPhoneNumber = (String)req.getParameter("clientPhoneNumber");
		String clientMobilephone = (String)req.getParameter("clientMobilephone");
		String orderType = (String)req.getParameter("orderType");
		c.setClientIc(clientIc);
		c.setClientName(clientName);
		c.setClientMobilePhone(clientMobilephone);
		//这里phonenumber会接受到控制与mobilephone 不同，待调试
		c.setClientPhoneNumber(clientPhoneNumber);
		Client client = ClientService.getInstance().selectClient(c);
		if(client==null) client = new Client();
		
		req.getSession().setAttribute("Client", client);
		/*请求转发*/
		/*将客户完整信息封装到请求之中，用于新订订单的生成*/
		try {
			if("1".equals(orderType)){
				req.getRequestDispatcher("/clientService/newOrder.jsp").forward(req, resp);
			}
			if("2".equals(orderType)){
				NewOrder newOrder = new NewOrder();
				newOrder.setClient(client);
				List<NewOrder> newOrders = NewOrderService.getInstance().selectNewOrder(newOrder);
				req.getSession().setAttribute("resultList", newOrders);
				req.getRequestDispatcher("/clientService/CancelOrder.jsp").forward(req, resp);
			}
			if("3".equals(orderType)){
				NewOrder newOrder = new NewOrder();
				newOrder.setClient(client);
				List<NewOrder> newOrders = NewOrderService.getInstance().selectNewOrder(newOrder);
				req.getSession().setAttribute("resultList", newOrders);
				req.getRequestDispatcher("/clientService/ReturnOrder.jsp").forward(req, resp);
			}
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void selectProduct(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException{
		Product p = new Product();
		String productName = (String) req.getParameter("productName");
		String firstClassId = (String) req.getParameter("firstClassId");
		String secondClassId = (String) req.getParameter("secondClassId");
		
		p.setProductName(productName);
		if(firstClassId !=null && !"".equals(firstClassId)){
			p.setFirstClassId(Integer.parseInt(firstClassId));
		}
		if(secondClassId !=null && !"".equals(secondClassId)){
			p.setSecondClassId(Integer.parseInt(secondClassId));
		}
		
		List<Product> products = ProductService.getInstance().selectProduct(p);
		req.setAttribute("resultList", products);
		
		try {
			req.getRequestDispatcher("/clientService/newOrder.jsp").forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void createNewOrder(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException, ParseException{
		/*生成新订订单*/
		Client client = (Client)req.getSession().getAttribute("Client");
		int productId =Integer.parseInt(req.getParameter("productId"));
		
		Product p = new Product();
		p.setProductId(productId);
		Product product = ProductService.getInstance().selectProduct(p).get(0);
		
		//Employee employee = (Employee)req.getSession().getAttribute("employee");
		Employee employee = new Employee();
		employee.setEmployeeId(1);
		employee.setEmployeeName("王昊文");
		
		
		int productQuantity = Integer.parseInt(req.getParameter("productQuantity"));
		String receiverName = req.getParameter("receiverName");
		String receiverPhone = req.getParameter("receiverMobilePhone");
		String receiverAddress = req.getParameter("receiverAddress");
		
		String rPostCode = req.getParameter("receiverPostCode");
		int receiverPostCode = 0;
		if(rPostCode!=null&&!"".equals(rPostCode)){
			receiverPostCode = Integer.parseInt(rPostCode);
		}
		
		float total = product.getProductPrice()* productQuantity*product.getProductDiscount();
		int orderState = 1;
		int whetherInvoice =0;
		if(req.getParameter("whetherInvoice")!=null){
			whetherInvoice = 1;
		}
		int employeeId = employee.getEmployeeId();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String now = sdf.format(new Date());
		Date generateDate = sdf.parse(now);
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		String rDate = req.getParameter("requireDate");
		Date requireDate = null;
		if(rDate != null &&!"".equals(rDate)){
			requireDate = sdf2.parse(rDate);
		}
		
		String newOrderRemark = req.getParameter("newOrderRemark");
		String deliverRequest = req.getParameter("deliverRequest");
		int status = 1;
		String operator = employee.getEmployeeName();
		Date operateDate = generateDate;
		
		NewOrder newOrder = new NewOrder();
		newOrder.setClient(client);
		newOrder.setProduct(product);
		newOrder.setProductQuantity(productQuantity);
		newOrder.setReceiverName(receiverName);
		newOrder.setReceiverPhone(receiverPhone);
		newOrder.setReceiverAddress(receiverAddress);
		newOrder.setReceiverPostCode(receiverPostCode);
		newOrder.setTotal(total);
		newOrder.setOrderState(orderState);
		newOrder.setEmployeeId(employeeId);
		newOrder.setWhetherInvoice(whetherInvoice);
		newOrder.setRequireDate(requireDate);
		newOrder.setGenerateDate(generateDate);
		newOrder.setNewOrderRemark(newOrderRemark);
		newOrder.setDeliverRequest(deliverRequest);
		newOrder.setStatus(status);
		newOrder.setOperator(operator);
		newOrder.setOperateDate(operateDate);
		
		NewOrderService.getInstance().creatNewOrder(newOrder);
		
	}
	private void createCancelOrder(HttpServletRequest req, HttpServletResponse resp) throws ParseException{
		int newOrderId = 0;
		String nOrderId = req.getParameter("newOrderId");
		if(nOrderId!=null &&!"".equals(nOrderId)){
			newOrderId = Integer.parseInt(nOrderId);
		}
		String cancelReason = req.getParameter("cancelReason");

		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		
		String cDate = req.getParameter("requireCancelDate");
		Date cancelDate = null;
		if(cDate != null &&!"".equals(cDate)){
			cancelDate = sdf2.parse(cDate);
		}else{
			cancelDate = new Date();
		}
		//Employee employee = (Employee)req.getSession().getAttribute("employee");
		Employee employee = new Employee();
		employee.setEmployeeId(1);
		employee.setEmployeeName("王昊文");
		int employeeId = employee.getEmployeeId();
		
		CancelOrder cancelOrder = new CancelOrder();
		
		NewOrder n = new NewOrder();
		n.setOrderId(newOrderId);
		
		cancelOrder.setNewOrder(n);
		cancelOrder.setCancelReason(cancelReason);
		cancelOrder.setCancelDate(cancelDate);
		cancelOrder.setEmployeeId(employeeId);
		cancelOrder.setOperator(employee.getEmployeeName());
		cancelOrder.setOperateDate(cancelDate);
		CancelOrderService.getInstance().cancelOrder(cancelOrder);
	}
	private void createReturnOrder(HttpServletRequest req, HttpServletResponse resp) throws ParseException{
		int newOrderId = 0;
		String nOrderId = req.getParameter("newOrderId");
		if(nOrderId!=null &&!"".equals(nOrderId)){
			newOrderId = Integer.parseInt(nOrderId);
		}
		int returnQuantity = 0;
		String rQuantity = req.getParameter("returnQuantity");
		if(rQuantity!=null &&!"".equals(rQuantity)){
			returnQuantity = Integer.parseInt(rQuantity);
		}
		String returnReason = req.getParameter("returnReason");
		String deliverRequest = req.getParameter("deliverRequest");

		//Employee employee = (Employee)req.getSession().getAttribute("employee");
		Employee employee = new Employee();
		employee.setEmployeeId(1);
		employee.setEmployeeName("王昊文");
		int employeeId = employee.getEmployeeId();
		String operator = employee.getEmployeeName();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String now = sdf.format(new Date());
		Date generateDate = sdf.parse(now);
		Calendar c = Calendar.getInstance();
		c.setTime(generateDate);
		c.add(Calendar.DAY_OF_MONTH, 7);//生成日期加上7天
		
		Date returnDate = c.getTime();
		
		ReturnOrder returnOrder = new ReturnOrder();
		
		NewOrder n = new NewOrder();
		n.setOrderId(newOrderId);
		
		returnOrder.setNewOrder(n);
		returnOrder.setReturnQuantity(returnQuantity);
		returnOrder.setReturnReason(returnReason);
		returnOrder.setDeliverRequest(deliverRequest);
		returnOrder.setGenerateDate(generateDate);
		returnOrder.setReturnDate(returnDate);
		returnOrder.setEmployeeId(employeeId);
		returnOrder.setOperator(operator);
		returnOrder.setOperateDate(generateDate);
		ReturnOrderService.getInstance().creatReturnOrder(returnOrder);
	}

	private void selectNewOrderForCancel(HttpServletRequest req, HttpServletResponse resp) throws ParseException, ServletException, IOException{
		Client client = (Client)req.getSession().getAttribute("Client");
		
		int orderState = Integer.parseInt(req.getParameter("orderState"));
		
		String  oId = req.getParameter("orderId");
		int orderId = 0;
		if(oId != null && !"".equals(oId)){
			orderId = Integer.parseInt(req.getParameter("orderId"));
		}
		
		String gDate = req.getParameter("generateDate");
		Date generateDate = null;
		if(gDate != null && !"".equals(gDate)){
			generateDate = new SimpleDateFormat("yyyy-MM-dd").parse(gDate);
		}
		
		String fDate = req.getParameter("finishDate");
		Date finishDate = null;
		if(fDate != null && !"".equals(fDate)){
			finishDate = new SimpleDateFormat("yyyy-MM-dd").parse(gDate);
		}
		
		NewOrder newOrder = new NewOrder();
		newOrder.setClient(client);
		newOrder.setOrderId(orderId);
		newOrder.setOrderState(orderState);
		newOrder.setGenerateDate(generateDate);
		newOrder.setFinishDate(finishDate);
		
		List<NewOrder> newOrders = NewOrderService.getInstance().selectNewOrder(newOrder);
		req.setAttribute("resultList", newOrders);
		req.getRequestDispatcher("/clientService/CancelOrder.jsp").forward(req, resp);
	}
	private void selectNewOrderForReturn(HttpServletRequest req, HttpServletResponse resp) throws ParseException, ServletException, IOException{
		Client client = (Client)req.getSession().getAttribute("Client");
		
		int orderState = Integer.parseInt(req.getParameter("orderState"));
		
		String  oId = req.getParameter("orderId");
		int orderId = 0;
		if(oId != null && !"".equals(oId)){
			orderId = Integer.parseInt(req.getParameter("orderId"));
		}
		String gDate = req.getParameter("generateDate");
		Date generateDate = null;
		if(gDate != null && !"".equals(gDate)){
			generateDate = new SimpleDateFormat("yyyy-MM-dd").parse(gDate);
		}
		
		String fDate = req.getParameter("finishDate");
		Date finishDate = null;
		if(fDate != null && !"".equals(fDate)){
			finishDate = new SimpleDateFormat("yyyy-MM-dd").parse(gDate);
		}
		
		NewOrder newOrder = new NewOrder();
		newOrder.setClient(client);
		newOrder.setOrderId(orderId);
		newOrder.setOrderState(orderState);
		newOrder.setGenerateDate(generateDate);
		newOrder.setFinishDate(finishDate);
		
		List<NewOrder> newOrders = NewOrderService.getInstance().selectNewOrder(newOrder);
		req.setAttribute("resultList", newOrders);
		req.getRequestDispatcher("/clientService/ReturnOrder.jsp").forward(req, resp);
	}

	private void selectOrder(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException, ParseException{
		/*查询新订订单*/
		req.setCharacterEncoding("utf-8");
		/******************这样写可以获得，但是要注意请求转发对象**************************/
		Client client = (Client)req.getAttribute("locateClient");
		/******************前端需要补充是否获得发票框**************************/
		
		int orderId = Integer.parseInt(req.getParameter("orderId"));
		int orderState = Integer.parseInt(req.getParameter("orderState"));
		Date generateDate = new SimpleDateFormat("dd-MMM-yyyy").parse(req.getParameter("generateDate"));
		Date finishDate = new SimpleDateFormat("dd-MMM-yyyy").parse(req.getParameter("finishDate"));
		int pageSize = Integer.parseInt(req.getParameter("pageSize"));
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		
		int selectrange = Integer.parseInt(req.getParameter("selectrange"));//从订单类型下拉框里获得查询范围
		/*根据查询的范围指定需要查询的服务*/
		if(selectrange == 0){//查询全部订单
			NewOrder newOrder = new NewOrder();
			newOrder.setOrderId(1);
			NewOrderService.getInstance().selectNewOrderByPage(newOrder, 5, 0);
			//returnOrder.setOperateDate(finishDate);
			//SelectService.getInstance().selectAllOrder();
		}
		else if(selectrange == 1)
			;
		else if(selectrange == 2)
			;
		else if(selectrange == 3)
			;
	}
}
