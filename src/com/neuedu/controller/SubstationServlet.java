package com.neuedu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.lang.model.type.PrimitiveType;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.neuedu.model.dao.SubReturnRecordDAO;
import com.neuedu.model.dao.SubReturnRecordDAOImp;
import com.neuedu.model.po.DeliveryStaff;
import com.neuedu.model.po.Employee;
import com.neuedu.model.po.ProInfo;
import com.neuedu.model.po.Sign;
import com.neuedu.model.po.WorkOrder;
import com.neuedu.model.service.SubstationService;
import com.neuedu.utils.DBUtil;
import com.sun.security.auth.NTDomainPrincipal;

/**
 * Servlet implementation class SubstationServlet
 */
public class SubstationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public int warehouseId = 0;
	public int employeeId = 0;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubstationServlet() {
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
		//解决中文乱码问题
		request.setCharacterEncoding("utf-8");
		Employee e = (Employee)request.getSession().getAttribute("employee");
		SubReturnRecordDAO dao = new SubReturnRecordDAOImp((Connection)DBUtil.getConn());
		if(e!=null){
			employeeId = e.getEmployeeId();
			warehouseId = dao.getWarehouseId(e.getEmployeeId());
		}
//		warehouseId = 1;
		String action = (String)request.getParameter("action");
		System.out.println("action:"+action);
		
		if(action.equals("search")){
			workQuery(request, response);
		}
		if(action.equals("assign")){
			workQuery(request, response);//先进行一波查询
		}
		if(action.equals("delivery")){
			getDeliveryStaff(request, response);//获取配送员信息
		}
		if(action.equals("assignWork")){
			workAssign(request, response);//确认分配
		}
		if(action.equals("print")){
			workQuery2(request, response);//先进行一波查询
		}
		if(action.equals("showSign")){
			showSign(request, response);//显示签收单
		}
		if(action.equals("record")){
			workQuery2(request, response);
		}
		if(action.equals("recordFeedback")){
			feedbackRecord(request, response);//录入信息
		}
		if(action.equals("pay")){
			productInfo(request, response);//缴款查询
		}
	}
	//三个参数的查询
	private void workQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("sansansansan+warehouseId:"+warehouseId);
		String pagenum = (String)request.getParameter("pageNum");//从结尾处
		int pageNum = 1;
		String requireDate="";
		String workType="";
		String workStatus="";
		//如果pagenum不为空也不为null，否则说明是第一次搜索
		if(pagenum != null && !pagenum.equals("")){
			pageNum = Integer.parseInt(pagenum);
			requireDate = (String)request.getParameter("requireDate");
			workType = (String)request.getParameter("workType");
			workStatus = (String)request.getParameter("workStatus");
		}else{
			requireDate = (String)request.getParameter("requireDate");
			workType = (String)request.getParameter("workType");
			workStatus = (String)request.getParameter("workStatus");
		}
		//转化数据类型
		Date newRequireDate = null;
		
		System.out.println(requireDate+"---requireDate");
		
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		if(requireDate!=null && !requireDate.equals("")){
			try {
				newRequireDate = sd.parse(requireDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		int newWorkType = 0;
		if(workType!=null && !workType.equals("")){
			System.out.println("workType:::"+workType);
			newWorkType = Integer.parseInt(workType);
		}
		
		int newWorkStatus = 0;
		if(workStatus!=null && !workStatus.equals("")){
			System.out.println("workStatus:::"+workStatus);
			newWorkStatus = Integer.parseInt(workStatus);
		}
		
		//查询,注意要传转换之后的参数
		List<WorkOrder> list = SubstationService.getInstance().selectPageWork(warehouseId, newRequireDate, newWorkStatus, newWorkType, pageNum);
		for(WorkOrder workOrder:list){
			System.out.println(workOrder.getClientName()+":"+workOrder.getClientPhone());
			System.out.println(workOrder.getWorkStatus()+"::workStatus");
			System.out.println(workOrder.getWorkId()+":::workId");
		}
		//获取页码
		int pageCount = SubstationService.getInstance().selectPageCount(warehouseId, newRequireDate, newWorkStatus, newWorkType);
		request.setAttribute("resultList", list);
		request.setAttribute("pageCount", pageCount);
		request.getSession().setAttribute("pageNum", pageNum);//默认第一次是第1页
		request.getSession().setAttribute("requireDate", requireDate);
		request.getSession().setAttribute("workStatus", workStatus);
		request.getSession().setAttribute("workType", workType);
		
		//跳转，请求转发，因为要用到数据,判断action
		String action = request.getParameter("action");
		if(action.equals("search")){
			request.getRequestDispatcher("substation/searchWorkOrder.jsp").forward(request, response);
		}
		if(action.equals("assign")){
			request.getRequestDispatcher("substation/workAssign.jsp").forward(request, response);
		}

	}
	//四个参数的查询
	private void workQuery2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String pagenum = (String)request.getParameter("pageNum");//从结尾处
		int pageNum = 1;
		String deliveryStaffId="";
		String requireDate="";
		String workType="";
		String workStatus="";
		
		//如果pagenum不为空也不为null，否则说明是第一次搜索
		if(pagenum != null && !pagenum.equals("")){
			pageNum = Integer.parseInt(pagenum);
			deliveryStaffId = (String)request.getParameter("deliveryStaffId");
			requireDate = (String)request.getParameter("requireDate");
			workType = (String)request.getParameter("workType");
			workStatus = (String)request.getParameter("workStatus");
		}else{
			deliveryStaffId = (String)request.getParameter("deliveryStaffId");
			requireDate = (String)request.getParameter("requireDate");
			workType = (String)request.getParameter("workType");
			workStatus = (String)request.getParameter("workStatus");
		}
		int newDeliveryStaffId = 0;
		if(deliveryStaffId!=null && !deliveryStaffId.equals("")){
			System.out.println("四参数查询:deliveryStaffId:"+deliveryStaffId);
			newDeliveryStaffId = Integer.parseInt(deliveryStaffId);
		}
		Date newRequireDate = null;
		
		System.out.println(requireDate+"---requireDate");
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		if(requireDate!=null && !requireDate.equals("")){
			try {
				newRequireDate = sd.parse(requireDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		int newWorkType = 0;
		if(workType!=null && !workType.equals("")){
			System.out.println("workType:::"+workType);
			newWorkType = Integer.parseInt(workType);
		}
		
		int newWorkStatus = 0;
		if(workStatus!=null && !workStatus.equals("")){
			System.out.println("workStatus:::"+workStatus);
			newWorkStatus = Integer.parseInt(workStatus);
		}
		//获取任务单
		System.out.println("warehouse---servlet:"+warehouseId);
		List<WorkOrder> list = SubstationService.getInstance().selectPageWork(warehouseId, newDeliveryStaffId, newRequireDate, newWorkStatus, newWorkType, pageNum);
		for(WorkOrder workOrder:list){
			System.out.println(workOrder.getClientName()+":"+workOrder.getClientPhone());
			System.out.println(workOrder.getWorkStatus()+"::workStatus");
			System.out.println(workOrder.getWorkId()+":::workId");
		}
		//获取页码
		int pageCount = SubstationService.getInstance().selectPageCount(warehouseId, newDeliveryStaffId, newRequireDate, newWorkStatus, newWorkType);
		request.getSession().setAttribute("resultList", list);//
		request.setAttribute("pageCount", pageCount);
		request.getSession().setAttribute("pageNum", pageNum);//默认第一次是第1页
		request.getSession().setAttribute("requireDate", requireDate);
		request.getSession().setAttribute("workStatus", workStatus);
		request.getSession().setAttribute("workType", workType);
		
		//跳转，请求转发，因为要用到数据,判断action
		String action = request.getParameter("action");
		if(action.equals("print")){
			request.getSession().setAttribute("searchbutton", "searchbutton");//添加这个代表是搜索操作
			request.getRequestDispatcher("substation/signPrint.jsp").forward(request, response);
		}
		if(action.equals("record")){
			request.getSession().setAttribute("searchbutton", "searchbutton");//添加这个代表是搜索操作
			request.getRequestDispatcher("substation/feedbackRecord.jsp").forward(request, response);
		}
	}
	//获取对应分站的配送员
	private void getDeliveryStaff(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("获取配送员信息");
		
		//得到配送员的信息
		List<DeliveryStaff> deList = SubstationService.getInstance().getDeliveryStaff(employeeId);
//		List<DeliveryStaff> deList = SubstationService.getInstance().getDeliveryStaff(5);
		
		request.setAttribute("deliveryStaffList", deList);
		
		response.setContentType("text/plain;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		StringBuffer sBuffer = new StringBuffer("");
		for(int i=0;i<deList.size();i++){
			sBuffer.append("<option value=\"");
			sBuffer.append(i+1);		
			sBuffer.append("\">");
			sBuffer.append(deList.get(i).getDeliveryStaffId());
			sBuffer.append("</option>");
		}
		pw.print(sBuffer.toString());
		pw.close();
		
	}
	//确认分配按钮
	private void workAssign(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//三个属性都肯定不能为空
		String workId = (String)request.getParameter("workIdOfW");//任务单号
		String workStatus = (String)request.getParameter("workStatusAssign");//任务状态，“可分配”的才能分配
		String deliveryStaffId = (String)request.getParameter("deliveryStaff");//配送员代号
		int newWorkId = 0;
		int newWorkStatus = 0;
		int newDeliveryStaffId = 0;
		System.out.println("workId++"+workId+"  workStatus::"+workStatus+"  deliveryStaffId--"+deliveryStaffId);
		
		if(workId!=null && !workId.equals("")){
			newWorkId = Integer.parseInt(workId);
		}
		if(workStatus!=null && !workStatus.equals("")){
			newWorkStatus = Integer.parseInt(workStatus);
		}
		if(deliveryStaffId!=null && !deliveryStaffId.equals("")){
			newDeliveryStaffId = Integer.parseInt(deliveryStaffId);
		}
		//分配任务，更新数据库
		SubstationService.getInstance().workAssign(newWorkId, newWorkStatus, newDeliveryStaffId);
		//更新完毕，重新刷新那一页
		int pageNum = (Integer)request.getSession().getAttribute("pageNum");
		response.sendRedirect(request.getContextPath()+"/substationServlet?action=assign&pageNum="+pageNum);
	}
	//显示签收单
	private void showSign(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("显示签收单");
		List<WorkOrder> list = (ArrayList<WorkOrder>)request.getSession().getAttribute("resultList");
		String index = (String)request.getParameter("index");
		System.out.println("index+"+index);
		int newIndex = 0;
		if(index!=null && !index.equals("")){
			newIndex = Integer.parseInt(index);//一般必有index
		}
		WorkOrder workOrder = list.get(newIndex);
		Sign sign = SubstationService.getInstance().getSign(workOrder);
		System.out.println("sign:"+sign.getSignId());
		request.setAttribute("resultSign", sign);
		
		response.setContentType("text/plain;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		String signstr = "<div class=\"infoBlock\"><div class=\"infoRow\"><div class=\"infoRowColumn\"><p>签收单号</p><span class=\"infoBorder\">"+sign.getSignId() +"</span></div><div class=\"infoRowColumn\"><p>任务单号</p><span class=\"infoBorder\">"+sign.getWorkId()+"</span></div></div><div class=\"infoRow\"><div class=\"infoRowColumn\"><p>客户姓名</p><span class=\"infoBorder\">"+sign.getCustomerName()+"</span></div><div class=\"infoRowColumn\"><p>联系电话</p><span class=\"infoBorder\">"+sign.getCustomerPhone()+"</span></div></div><div class=\"infoRow\"><div class=\"infoRowColumn\"><p>邮编</p><span class=\"infoBorder\">"+sign.getPostCode()+"</span></div><div class=\"infoRowColumn\"><p>送货日期</p><span class=\"infoBorder\">"+sign.getDeliveryDate().toString()+"</span></div></div><div class=\"infoRowColumnlong\"><p>任务类型</p><span class=\"infoBorder\">"+sign.getWorkType()+"</span></div></div><div class=\"infoBlock\"><div class=\"infoRowColumnlong\"><p>地址</p><span class=\"infoBorderlong\">"+sign.getDeliveryAddress()+"</span></div><div class=\"infoRowColumnlong\"><p>送货要求</p><span class=\"infoBorderlong\">"+sign.getDeliveryRequirement()+"</span></div></div><div class=\"infoBlock\"><div class=\"infoRow\"><div class=\"infoRowColumn\"><p>送货分站代号</p><span class=\"infoBorder\">"+sign.getDeliverySubstation()+"</span></div><div class=\"infoRowColumn\"><p>分站地址</p><span class=\"infoBorder\">"+sign.getSubstationAddress()+"</span></div></div><div class=\"infoRow\"><div class=\"infoRowColumn\"><p>分站电话</p><span class=\"infoBorder\"></span></div><div class=\"infoRowColumn\"><p>是否需要发票</p><span class=\"infoBorder\">"+sign.getReceiptNeedOrNot()+"</span></div></div></div><div class=\"infoBlock\"><div class=\"infoRow\"><div class=\"infoRowColumn\"><p>商品名称</p><span class=\"infoBorder\">"+sign.getProductName()+"</span></div><div class=\"infoRowColumnshort\"><p>单价</p><span class=\"infoBordershort\">"+sign.getProductPrice()+"</span></div><div class=\"infoRowColumnshort\"><p>商品数量</p><span class=\"infoBordershort\">"+sign.getProductQuantity()+"</span></div></div><div class=\"infoRow\"><div class=\"infoRowColumn\"><p>商品总价</p><span class=\"infoBorder\">"+sign.getTotal()+"</span></div><div class=\"infoRowColumnmedium\"><p>备注</p><span class=\"infoBordermedium\"></span></div></div></div><div class=\"infoBlock\"><div class=\"infoRow\"><div class=\"infoRowColumn\"><p>客户反馈</p><span class=\"infoBorder\" style=\"overflow-y:scroll;\"></span></div><div class=\"infoRowColumn\"><p>客户签名</p><span class=\"infoBorder\"></span></div></div></div>";
		
		pw.print(signstr);
		pw.close();
		
//		//跳转，请求转发，因为要用到数据,判断action
//		String action = request.getParameter("action");
//		if(action.equals("showSign")){
////			request.getSession().setAttribute("searchbutton", "searchbutton");//添加这个代表是搜索操作or显示签收单
//			request.getRequestDispatcher("substation/signPrint.jsp").forward(request, response);
//		}
//		return;
	}
	//回执录入按钮
	private void feedbackRecord(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//三个属性，remark可空
		String workId = (String)request.getParameter("workIdRecord");
		String customerFeedback = (String)request.getParameter("customerFeedback");
		String remark = "";
		if(remark!=null && !remark.equals("")){
			remark = (String)request.getParameter("remarkRecord");
		}
		int newWorkId = Integer.parseInt(workId);
		int newCustomerFeedback = Integer.parseInt(customerFeedback);
		String newRemark = "";
		if(remark!=null && !remark.equals("")){
			newRemark = remark;
		}
		//分配任务，更新数据库
		SubstationService.getInstance().feedbackRecord(newWorkId, newCustomerFeedback, newRemark);
		//更新完毕，重新刷新那一页
		int pageNum = (Integer)request.getSession().getAttribute("pageNum");
		response.sendRedirect(request.getContextPath()+"/substationServlet?action=record&pageNum="+pageNum);
	}
	//搜索查询商品信息
	private void productInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String pagenum = (String)request.getParameter("pageNum");//从结尾处
		int pageNum = 1;
		String beginDate="";
		String endDate="";
		String productName="";
		//如果pagenum不为空也不为null，否则说明是第一次搜索并且默认为第一页
		if(pagenum != null && !pagenum.equals("")){
			pageNum = Integer.parseInt(pagenum);
			beginDate = (String)request.getParameter("beginDate");
			endDate = (String)request.getParameter("endDate");
			productName = (String)request.getParameter("productName");
		}else{
			beginDate = (String)request.getParameter("beginDate");
			endDate = (String)request.getParameter("endDate");
			productName = (String)request.getParameter("productName");
		}
		//转化数据类型
		Date newBeginDate = null;
		Date newEndDate = null;
		
		System.out.println(beginDate+"---beginDate");
		System.out.println(endDate+"---endDate");
		
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		if(beginDate!=null && !beginDate.equals("")){
			try {
				newBeginDate = sd.parse(beginDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(endDate!=null && !endDate.equals("")){
			try {
				newEndDate = sd.parse(endDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		String newProductName="";
		if(productName!=null && !productName.equals("")){
			newProductName = productName;
		}
		//查询,注意要传转换之后的参数
		List<ProInfo> list = SubstationService.getInstance().selectPageProduct(newBeginDate, newEndDate, newProductName, pageNum);
		System.out.println("listsize!!"+list.size());
		for(ProInfo proInfo:list){
			System.out.println(proInfo.getProductName()+":::productName");
		}
		//获取页码
		System.out.println("productName!!"+productName);
		int pageCount = SubstationService.getInstance().selectPageProduct(newBeginDate, newEndDate, newProductName);
		System.out.println("pageCount!!"+pageCount);
		
		request.setAttribute("resultList", list);
		request.setAttribute("pageCount", pageCount);
		request.getSession().setAttribute("pageNum", pageNum);//默认第一次是第1页
		request.getSession().setAttribute("beginDate", beginDate);
		request.getSession().setAttribute("endDate", endDate);
		request.getSession().setAttribute("productName", productName);
		
		//跳转，请求转发，因为要用到数据,判断action
		String action = request.getParameter("action");
		if(action.equals("pay")){
			request.getRequestDispatcher("substation/paymentQuery.jsp").forward(request, response);
		}
	}
}




