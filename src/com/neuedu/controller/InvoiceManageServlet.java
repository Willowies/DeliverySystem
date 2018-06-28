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
	
	//��Ʊ����
	private void abandonInvoice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		WorkOrder workorder = (WorkOrder)request.getSession().getAttribute("workorderA");
		Invoice invoice = InvoiceService.getInstance().selectInvoiceByWorkId(workorder.getWorkId());
		if(invoice.getStatus()==0){
			request.getSession().setAttribute("messageAID", "��Ʊ�ѱ�����");
		}else{
			//Employee e = (Employee)request.getSession().getAttribute("employee");
			//InvoiceService.getInstance().getSubstationInvoice(invoice.getInvoiceId(),e.getEmployeeName());
		    InvoiceService.getInstance().abandonInvoice(workorder.getWorkId(),"test");
			request.getSession().setAttribute("messageAID", "�����ɹ�");
		}
		//request.getRequestDispatcher("abandonInvoiceResult.jsp").forward(request, response);
		response.sendRedirect(request.getContextPath()+"/finance/abandonInvoiceResult.jsp");
	}
	
	private void abandonInvoicefromS(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		WorkOrder workorder = (WorkOrder)request.getSession().getAttribute("workorderAfromS");
		Invoice invoice = InvoiceService.getInstance().selectInvoiceByWorkId(workorder.getWorkId());
		if(invoice.getStatus()==0){
			request.getSession().setAttribute("messageAfromS", "��Ʊ�ѱ�����");
		}else{
			//Employee e = (Employee)request.getSession().getAttribute("employee");
			//InvoiceService.getInstance().getSubstationInvoice(invoice.getInvoiceId(),e.getEmployeeName());
		    InvoiceService.getInstance().abandonInvoice(workorder.getWorkId(),"test");
			request.getSession().setAttribute("messageAfromS", "�����ɹ�");
		}
		//request.getRequestDispatcher("abandonInvoiceResult.jsp").forward(request, response);
		response.sendRedirect(request.getContextPath()+"/substation/abandonInvoiceResult.jsp");
	}

	//����ǰ��ѯ������������Ϣ
	private void selectWorkOrderA(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String workId = request.getParameter("workId");
		WorkOrder workorder = InvoiceService.getInstance().selectWorkOrder(workId);
		if(workorder==null){
			//δ�鵽������
			request.getSession().setAttribute("messageAI", "δ�鵽������");
			//request.getRequestDispatcher("abandonInvoice.jsp").forward(request, response);
			response.sendRedirect(request.getContextPath()+"/finance/abandonInvoice.jsp");
		}else{
			//���ҵ�������
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
			//δ�鵽������
			request.getSession().setAttribute("messageAIfromS", "δ�鵽������");
			//request.getRequestDispatcher("abandonInvoice.jsp").forward(request, response);
			response.sendRedirect(request.getContextPath()+"/substation/abandonInvoice.jsp");
		}else{
			//���ҵ�������
			NewOrder neworder = InvoiceService.getInstance().selectNewOrder(workorder.getOrderId());
			request.getSession().setAttribute("workorderAfromS", workorder);
			request.getSession().setAttribute("neworderAfromS", neworder);
			//request.getRequestDispatcher("abandonInvoiceResult.jsp").forward(request, response);
			response.sendRedirect(request.getContextPath()+"/substation/abandonInvoiceResult.jsp");
		}
		
	}

	//�ͻ�����ǰ��ѯ���񵥼�������Ϣ
	private void selectWorkOrderC(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String workId = request.getParameter("workId");
		WorkOrder workorder = InvoiceService.getInstance().selectWorkOrder(workId);
		if(workorder==null){
			//δ�鵽������
			request.getSession().setAttribute("messageSWOC", "δ�鵽������");
			//request.getRequestDispatcher("substation/getClientInvoice.jsp").forward(request, response);
			response.sendRedirect(request.getContextPath()+"/substation/getClientInvoice.jsp");
		}else{
			//���ҵ�������
			NewOrder neworder = InvoiceService.getInstance().selectNewOrder(workorder.getOrderId());
			request.getSession().setAttribute("workorderC", workorder);
			request.getSession().setAttribute("neworderC", neworder);
			//request.getRequestDispatcher("substation/getClientInvoiceResult.jsp").forward(request, response);
			response.sendRedirect(request.getContextPath()+"/substation/getClientInvoiceResult.jsp");
		}
		
	}	
	
	//�ͻ����÷�Ʊ
	private void getClientInvoice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String invoiceId = request.getParameter("invoiceId");
		WorkOrder workorderC = (WorkOrder)request.getSession().getAttribute("workorderC");
		Invoice invoice = InvoiceService.getInstance().selectInvoice(invoiceId);
		if(invoice==null || invoice.getWorkId()!=workorderC.getWorkId()){
			request.getSession().setAttribute("messageGCI", "���񵥺��뷢Ʊ�Ų���Ӧ");	
		}else if(invoice.getState()!=2 || invoice.getStatus()==0){
			request.getSession().setAttribute("messageGCI", "��Ʊ�����û�����");
		}else {
			//Employee e = (Employee)request.getSession().getAttribute("employee");
			//InvoiceService.getInstance().getCilentInvoice(invoice.getInvoiceId(),e.getEmployeeName());
			InvoiceService.getInstance().getClientInvoice(invoice.getInvoiceId(),"test");
			request.getSession().setAttribute("messageGCI", "���óɹ�");
		}
		//request.getRequestDispatcher("getClientInvoiceResult.jsp").forward(request, response);
		response.sendRedirect(request.getContextPath()+"/substation/getClientInvoiceResult.jsp");
	}

	//�Ǽ�ǰ��ѯ���񵥼�������Ϣ
	private void selectWorkOrderR(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String workId = request.getParameter("workId");
		WorkOrder workorder = InvoiceService.getInstance().selectWorkOrder(workId);
		if(workorder==null){
			//δ�鵽������
			request.getSession().setAttribute("messageSWR", "δ�鵽������");
			response.sendRedirect(request.getContextPath()+"/finance/registerInvoice.jsp");
		}else{
			//���ҵ�������
			NewOrder neworder = InvoiceService.getInstance().selectNewOrder(workorder.getOrderId());
			Invoice invoice = new Invoice();
			invoice.setWorkId(Integer.parseInt(workId));
			invoice.setOrderId(workorder.getOrderId());
			invoice.setInvoiceAmount(neworder.getTotal());
			//�����ݿ��м�¼�÷�Ʊ��Ϣ
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
	
	//��վ���÷�Ʊ
	private void getSubstationInvoice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String substationName = request.getParameter("substationName");
		Invoice invoice = (Invoice)request.getSession().getAttribute("subinvoice");
		if(!substationName.equals(invoice.getSubstationName())){
			//���÷�վ�����񵥶�Ӧ��վ
			request.getSession().setAttribute("messageGSIR", "���÷�վ�����񵥶�Ӧ��վ");
		}else{
			//Employee e = (Employee)request.getSession().getAttribute("employee");
			//InvoiceService.getInstance().getSubstationInvoice(invoice.getInvoiceId(),e.getEmployeeName());
			InvoiceService.getInstance().getSubstationInvoice(invoice.getInvoiceId(),"test");
			request.getSession().setAttribute("messageGSIR", "���óɹ�");
		}
		//request.getRequestDispatcher("getSubstationInvoiceResult.jsp").forward(request, response);
		response.sendRedirect(request.getContextPath()+"/finance/getSubstationInvoiceResult.jsp");
	}
	
	//��Ʊ����վ����ǰͨ����Ʊ�Ų�ѯ
	private void selectSubInvoice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String invoiceId = request.getParameter("invoiceId");
		
		Invoice invoice = InvoiceService.getInstance().selectInvoice(invoiceId);
		
		if(invoice==null){
			//δ�鵽�÷�Ʊ
			request.getSession().setAttribute("messageGSI", "δ�鵽������");
			//request.getRequestDispatcher("getSubstationInvoice.jsp").forward(request, response);
			response.sendRedirect(request.getContextPath()+"/finance/getSubstationInvoice.jsp");
		}else if(invoice.getState()!=1 || invoice.getStatus()==0){
			//��Ʊ�����û�����
			//request.getSession().setAttribute("messageGSI", "��Ʊ�����û�����");
			request.getRequestDispatcher("getSubstationInvoice.jsp").forward(request, response);
			response.sendRedirect(request.getContextPath()+"/finance/getSubstationInvoice.jsp");
		}else{
			invoice.setStateInfo("����");
			request.getSession().setAttribute("subinvoice",invoice);
			//request.getRequestDispatcher("getSubstationInvoiceResult.jsp").forward(request, response);
			response.sendRedirect(request.getContextPath()+"/finance/getSubstationInvoiceResult.jsp");
		}
	}
	
	//��Ʊ�Ǽ�
	private void registerInvoice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Invoice recinvoice = (Invoice)request.getSession().getAttribute("recinvoice");
		//Employee e = (Employee)request.getSession().getAttribute("employee");
		//InvoiceService.getInstance().getSubstationInvoice(invoice.getInvoiceId(),e.getEmployeeName());
		InvoiceService.getInstance().registerInvoice(recinvoice.getInvoiceId(),"test");
		request.getSession().setAttribute("messageRR", "ע��ɹ�");
		//request.getRequestDispatcher("registerInvoiceResult.jsp").forward(request, response);
		response.sendRedirect(request.getContextPath()+"/finance/registerInvoiceResult.jsp");
	}
	
	//��Ʊ��ѯ
	private void selectInvoice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String invoiceId = request.getParameter("invoiceId");
		
		Invoice invoice = InvoiceService.getInstance().selectInvoice(invoiceId);
		
		if(invoice==null){
			//δ�鵽�÷�Ʊ
			request.getSession().setAttribute("messageSI", "δ�鵽������");
			response.sendRedirect(request.getContextPath()+"/finance/selectInvoice.jsp");
			//request.getRequestDispatcher("finance/selectInvoice.jsp").forward(request, response);
		}else{
			//��ѯ�ɹ�
			int state = invoice.getState();
			switch(state){
				case 1:
					//��Ʊ����
					if(invoice.getStatus()==0){
						invoice.setStateInfo("����/��ʧ");
					}else{
						invoice.setStateInfo("����");
					}
					invoice.setReceivedPerson("δ������");
					invoice.setReceiveDate(null);
					invoice.setSubstationName("δ����վ����");
				break;
				case 2:
					//��Ʊ����վ����
					if(invoice.getStatus()==0){
						invoice.setStateInfo("����/��ʧ");
					}else{
						invoice.setStateInfo("����վ����");
					}
					invoice.setReceivedPerson("δ������");
					invoice.setReceiveDate(null);
				break;
				case 3:
					//��Ʊ���ͻ�����
					if(invoice.getStatus()==0){
						invoice.setStateInfo("����/��ʧ");
					}else{
						invoice.setStateInfo("���ͻ�����");
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
			//δ�鵽�÷�Ʊ
			request.getSession().setAttribute("messageSIfromS", "δ�鵽������");
			response.sendRedirect(request.getContextPath()+"/substation/selectInvoice.jsp");
			//request.getRequestDispatcher("finance/selectInvoice.jsp").forward(request, response);
		}else{
			//��ѯ�ɹ�
			int state = invoice.getState();
			switch(state){
				case 1:
					//��Ʊ����
					if(invoice.getStatus()==0){
						invoice.setStateInfo("����/��ʧ");
					}else{
						invoice.setStateInfo("����");
					}
					invoice.setReceivedPerson("δ������");
					invoice.setReceiveDate(null);
					invoice.setSubstationName("δ����վ����");
				break;
				case 2:
					//��Ʊ����վ����
					if(invoice.getStatus()==0){
						invoice.setStateInfo("����/��ʧ");
					}else{
						invoice.setStateInfo("����վ����");
					}
					invoice.setReceivedPerson("δ������");
					invoice.setReceiveDate(null);
				break;
				case 3:
					//��Ʊ���ͻ�����
					if(invoice.getStatus()==0){
						invoice.setStateInfo("����/��ʧ");
					}else{
						invoice.setStateInfo("���ͻ�����");
					}
				break;
			}
			request.getSession().setAttribute("invoicefromS", invoice);
			//request.getRequestDispatcher("finance/selectInvoiceResult.jsp").forward(request, response);
			response.sendRedirect(request.getContextPath()+"/substation/selectInvoiceResult.jsp");
		}
	}

}
