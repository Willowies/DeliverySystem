package com.neuedu.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.model.po.Employee;
import com.neuedu.model.po.Invoice;
import com.neuedu.model.po.NewOrder;
import com.neuedu.model.po.WorkOrder;
import com.neuedu.model.service.InvoiceService;

/**
 * Servlet implementation class InvoiceManageServlet
 */
public class InvoiceManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InvoiceManageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		String action = request.getParameter("action");
		if("select".equals(action)){
			selectInvoice(request,response);
		}else if("selectSubInvoice".equals(action)){
			selectSubInvoice(request,response);
		}else if("getSubstationInvoice".equals(action)){
			getSubstationInvoice(request,response);
		}else if("selectWorkOrderR".equals(action)){
			selectWorkOrderR(request,response);
		}else if("register".equals(action)){
			registerInvoice(request,response);
		}else if("selectWorkOrderC".equals(action)){
			selectWorkOrderC(request,response);
		}else if("getClientInvoice".equals(action)){
			getClientInvoice(request,response);
		}else if("selectWorkOrderA".equals(action)){
			selectWorkOrderA(request,response);
		}else if("abandonInvoice".equals(action)){
			abandonInvoice(request,response);
		}else if("selectWorkOrderAfromS".equals(action)){
			selectWorkOrderAfromS(request,response);
		}else if("abandonInvoicefromS".equals(action)){
			abandonInvoicefromS(request,response);
		}else if("selectfromS".equals(action)){
			selectInvoicefromS(request,response);
		}
	}
	
	//发票废弃
	private void abandonInvoice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		WorkOrder workorder = (WorkOrder)request.getSession().getAttribute("workorderA");
		Invoice invoice = InvoiceService.getInstance().selectInvoiceByWorkId(workorder.getWorkId());
		if(invoice.getStatus()==0){
			request.getSession().setAttribute("messageAID", "发票已被废弃");
		}else{
			//Employee e = (Employee)request.getSession().getAttribute("employee");
			//InvoiceService.getInstance().getSubstationInvoice(invoice.getInvoiceId(),e.getEmployeeName());
		    InvoiceService.getInstance().abandonInvoice(workorder.getWorkId(),"test");
			request.getSession().setAttribute("messageAID", "废弃成功");
		}
		//request.getRequestDispatcher("abandonInvoiceResult.jsp").forward(request, response);
		response.sendRedirect(request.getContextPath()+"/finance/abandonInvoiceResult.jsp");
	}
	
	private void abandonInvoicefromS(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		WorkOrder workorder = (WorkOrder)request.getSession().getAttribute("workorderAfromS");
		Invoice invoice = InvoiceService.getInstance().selectInvoiceByWorkId(workorder.getWorkId());
		if(invoice.getStatus()==0){
			request.getSession().setAttribute("messageAfromS", "发票已被废弃");
		}else{
			//Employee e = (Employee)request.getSession().getAttribute("employee");
			//InvoiceService.getInstance().getSubstationInvoice(invoice.getInvoiceId(),e.getEmployeeName());
		    InvoiceService.getInstance().abandonInvoice(workorder.getWorkId(),"test");
			request.getSession().setAttribute("messageAfromS", "废弃成功");
		}
		//request.getRequestDispatcher("abandonInvoiceResult.jsp").forward(request, response);
		response.sendRedirect(request.getContextPath()+"/substation/abandonInvoiceResult.jsp");
	}

	//废弃前查询订单及任务单信息
	private void selectWorkOrderA(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String workId = request.getParameter("workId");
		WorkOrder workorder = InvoiceService.getInstance().selectWorkOrder(workId);
		if(workorder==null){
			//未查到该任务单
			request.getSession().setAttribute("messageAI", "未查到该数据");
			//request.getRequestDispatcher("abandonInvoice.jsp").forward(request, response);
			response.sendRedirect(request.getContextPath()+"/finance/abandonInvoice.jsp");
		}else{
			//查找到该任务单
			NewOrder neworder = InvoiceService.getInstance().selectNewOrder(workorder.getOrderId());
			request.getSession().setAttribute("workorderA", workorder);
			request.getSession().setAttribute("neworderA", neworder);
			//request.getRequestDispatcher("abandonInvoiceResult.jsp").forward(request, response);
			response.sendRedirect(request.getContextPath()+"/finance/abandonInvoiceResult.jsp");
		}
		
	}
	
	private void selectWorkOrderAfromS(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String workId = request.getParameter("workId");
		WorkOrder workorder = InvoiceService.getInstance().selectWorkOrder(workId);
		if(workorder==null){
			//未查到该任务单
			request.getSession().setAttribute("messageAIfromS", "未查到该数据");
			//request.getRequestDispatcher("abandonInvoice.jsp").forward(request, response);
			response.sendRedirect(request.getContextPath()+"/substation/abandonInvoice.jsp");
		}else{
			//查找到该任务单
			NewOrder neworder = InvoiceService.getInstance().selectNewOrder(workorder.getOrderId());
			request.getSession().setAttribute("workorderAfromS", workorder);
			request.getSession().setAttribute("neworderAfromS", neworder);
			//request.getRequestDispatcher("abandonInvoiceResult.jsp").forward(request, response);
			response.sendRedirect(request.getContextPath()+"/substation/abandonInvoiceResult.jsp");
		}
		
	}

	//客户领用前查询任务单及订单信息
	private void selectWorkOrderC(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String workId = request.getParameter("workId");
		WorkOrder workorder = InvoiceService.getInstance().selectWorkOrder(workId);
		if(workorder==null){
			//未查到该任务单
			request.getSession().setAttribute("messageSWOC", "未查到该数据");
			//request.getRequestDispatcher("substation/getClientInvoice.jsp").forward(request, response);
			response.sendRedirect(request.getContextPath()+"/substation/getClientInvoice.jsp");
		}else{
			//查找到该任务单
			NewOrder neworder = InvoiceService.getInstance().selectNewOrder(workorder.getOrderId());
			request.getSession().setAttribute("workorderC", workorder);
			request.getSession().setAttribute("neworderC", neworder);
			//request.getRequestDispatcher("substation/getClientInvoiceResult.jsp").forward(request, response);
			response.sendRedirect(request.getContextPath()+"/substation/getClientInvoiceResult.jsp");
		}
		
	}	
	
	//客户领用发票
	private void getClientInvoice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String invoiceId = request.getParameter("invoiceId");
		WorkOrder workorderC = (WorkOrder)request.getSession().getAttribute("workorderC");
		Invoice invoice = InvoiceService.getInstance().selectInvoice(invoiceId);
		if(invoice==null || invoice.getWorkId()!=workorderC.getWorkId()){
			request.getSession().setAttribute("messageGCI", "任务单号与发票号不对应");	
		}else if(invoice.getState()!=2 || invoice.getStatus()==0){
			request.getSession().setAttribute("messageGCI", "发票已领用或作废");
		}else {
			//Employee e = (Employee)request.getSession().getAttribute("employee");
			//InvoiceService.getInstance().getCilentInvoice(invoice.getInvoiceId(),e.getEmployeeName());
			InvoiceService.getInstance().getClientInvoice(invoice.getInvoiceId(),"test");
			request.getSession().setAttribute("messageGCI", "领用成功");
		}
		//request.getRequestDispatcher("getClientInvoiceResult.jsp").forward(request, response);
		response.sendRedirect(request.getContextPath()+"/substation/getClientInvoiceResult.jsp");
	}

	//登记前查询任务单及订单信息
	private void selectWorkOrderR(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String workId = request.getParameter("workId");
		WorkOrder workorder = InvoiceService.getInstance().selectWorkOrder(workId);
		if(workorder==null){
			//未查到该任务单
			request.getSession().setAttribute("messageSWR", "未查到该数据");
			response.sendRedirect(request.getContextPath()+"/finance/registerInvoice.jsp");
		}else{
			//查找到该任务单
			NewOrder neworder = InvoiceService.getInstance().selectNewOrder(workorder.getOrderId());
			Invoice invoice = new Invoice();
			invoice.setWorkId(Integer.parseInt(workId));
			invoice.setOrderId(workorder.getOrderId());
			invoice.setInvoiceAmount(neworder.getTotal());
			//到数据库中记录该发票信息
			//Employee e = (Employee)request.getSession().getAttribute("employee");
			//InvoiceService.getInstance().getSubstationInvoice(invoice.getInvoiceId(),e.getEmployeeName());
			invoice = InvoiceService.getInstance().recordInvoice(invoice,"test");
			request.getSession().setAttribute("workorder", workorder);
			request.getSession().setAttribute("neworder", neworder);
			request.getSession().setAttribute("recinvoice", invoice);
			response.sendRedirect(request.getContextPath()+"/finance/registerInvoiceResult.jsp");
			//request.getRequestDispatcher("registerInvoiceResult.jsp").forward(request, response);
		}
	}
	
	//分站领用发票
	private void getSubstationInvoice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String substationName = request.getParameter("substationName");
		Invoice invoice = (Invoice)request.getSession().getAttribute("subinvoice");
		if(!substationName.equals(invoice.getSubstationName())){
			//领用分站非任务单对应分站
			request.getSession().setAttribute("messageGSIR", "领用分站非任务单对应分站");
		}else{
			//Employee e = (Employee)request.getSession().getAttribute("employee");
			//InvoiceService.getInstance().getSubstationInvoice(invoice.getInvoiceId(),e.getEmployeeName());
			InvoiceService.getInstance().getSubstationInvoice(invoice.getInvoiceId(),"test");
			request.getSession().setAttribute("messageGSIR", "领用成功");
		}
		//request.getRequestDispatcher("getSubstationInvoiceResult.jsp").forward(request, response);
		response.sendRedirect(request.getContextPath()+"/finance/getSubstationInvoiceResult.jsp");
	}
	
	//发票被分站领用前通过发票号查询
	private void selectSubInvoice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String invoiceId = request.getParameter("invoiceId");
		
		Invoice invoice = InvoiceService.getInstance().selectInvoice(invoiceId);
		
		if(invoice==null){
			//未查到该发票
			request.getSession().setAttribute("messageGSI", "未查到该数据");
			//request.getRequestDispatcher("getSubstationInvoice.jsp").forward(request, response);
			response.sendRedirect(request.getContextPath()+"/finance/getSubstationInvoice.jsp");
		}else if(invoice.getState()!=1 || invoice.getStatus()==0){
			//发票已领用或作废
			//request.getSession().setAttribute("messageGSI", "发票已领用或作废");
			request.getRequestDispatcher("getSubstationInvoice.jsp").forward(request, response);
			response.sendRedirect(request.getContextPath()+"/finance/getSubstationInvoice.jsp");
		}else{
			invoice.setStateInfo("正常");
			request.getSession().setAttribute("subinvoice",invoice);
			//request.getRequestDispatcher("getSubstationInvoiceResult.jsp").forward(request, response);
			response.sendRedirect(request.getContextPath()+"/finance/getSubstationInvoiceResult.jsp");
		}
	}
	
	//发票登记
	private void registerInvoice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Invoice recinvoice = (Invoice)request.getSession().getAttribute("recinvoice");
		//Employee e = (Employee)request.getSession().getAttribute("employee");
		//InvoiceService.getInstance().getSubstationInvoice(invoice.getInvoiceId(),e.getEmployeeName());
		InvoiceService.getInstance().registerInvoice(recinvoice.getInvoiceId(),"test");
		request.getSession().setAttribute("messageRR", "注册成功");
		//request.getRequestDispatcher("registerInvoiceResult.jsp").forward(request, response);
		response.sendRedirect(request.getContextPath()+"/finance/registerInvoiceResult.jsp");
	}
	
	//发票查询
	private void selectInvoice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String invoiceId = request.getParameter("invoiceId");
		
		Invoice invoice = InvoiceService.getInstance().selectInvoice(invoiceId);
		
		if(invoice==null){
			//未查到该发票
			request.getSession().setAttribute("messageSI", "未查到该数据");
			response.sendRedirect(request.getContextPath()+"/finance/selectInvoice.jsp");
			//request.getRequestDispatcher("finance/selectInvoice.jsp").forward(request, response);
		}else{
			//查询成功
			int state = invoice.getState();
			switch(state){
				case 1:
					//发票正常
					if(invoice.getStatus()==0){
						invoice.setStateInfo("废弃/丢失");
					}else{
						invoice.setStateInfo("正常");
					}
					invoice.setReceivedPerson("未被领用");
					invoice.setReceiveDate(null);
					invoice.setSubstationName("未被分站领用");
				break;
				case 2:
					//发票被分站领用
					if(invoice.getStatus()==0){
						invoice.setStateInfo("废弃/丢失");
					}else{
						invoice.setStateInfo("被分站领用");
					}
					invoice.setReceivedPerson("未被领用");
					invoice.setReceiveDate(null);
				break;
				case 3:
					//发票被客户领用
					if(invoice.getStatus()==0){
						invoice.setStateInfo("废弃/丢失");
					}else{
						invoice.setStateInfo("被客户领用");
					}
				break;
			}
			request.getSession().setAttribute("invoice", invoice);
			//request.getRequestDispatcher("finance/selectInvoiceResult.jsp").forward(request, response);
			response.sendRedirect(request.getContextPath()+"/finance/selectInvoiceResult.jsp");
		}
		}
		
	private void selectInvoicefromS(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String invoiceId = request.getParameter("invoiceId");
		
		Invoice invoice = InvoiceService.getInstance().selectInvoice(invoiceId);
		
		if(invoice==null){
			//未查到该发票
			request.getSession().setAttribute("messageSIfromS", "未查到该数据");
			response.sendRedirect(request.getContextPath()+"/substation/selectInvoice.jsp");
			//request.getRequestDispatcher("finance/selectInvoice.jsp").forward(request, response);
		}else{
			//查询成功
			int state = invoice.getState();
			switch(state){
				case 1:
					//发票正常
					if(invoice.getStatus()==0){
						invoice.setStateInfo("废弃/丢失");
					}else{
						invoice.setStateInfo("正常");
					}
					invoice.setReceivedPerson("未被领用");
					invoice.setReceiveDate(null);
					invoice.setSubstationName("未被分站领用");
				break;
				case 2:
					//发票被分站领用
					if(invoice.getStatus()==0){
						invoice.setStateInfo("废弃/丢失");
					}else{
						invoice.setStateInfo("被分站领用");
					}
					invoice.setReceivedPerson("未被领用");
					invoice.setReceiveDate(null);
				break;
				case 3:
					//发票被客户领用
					if(invoice.getStatus()==0){
						invoice.setStateInfo("废弃/丢失");
					}else{
						invoice.setStateInfo("被客户领用");
					}
				break;
			}
			request.getSession().setAttribute("invoicefromS", invoice);
			//request.getRequestDispatcher("finance/selectInvoiceResult.jsp").forward(request, response);
			response.sendRedirect(request.getContextPath()+"/substation/selectInvoiceResult.jsp");
		}
	}

}
