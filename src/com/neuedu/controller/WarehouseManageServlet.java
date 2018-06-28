package com.neuedu.controller;

import java.io.IOException;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.model.po.CenterInRecord;
import com.neuedu.model.po.CenterOutRecord;
import com.neuedu.model.po.DistributionOrder;
import com.neuedu.model.po.ProductTransferOrder;
import com.neuedu.model.po.PurchaseInOrder;
import com.neuedu.model.po.ReceiveRecord;
import com.neuedu.model.po.SubInRecord;
import com.neuedu.model.po.WorkOrder;
import com.neuedu.model.service.WarehouseService;

/**
 * Servlet implementation class WarehouseManageServlet
 */
public class WarehouseManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public WarehouseManageServlet() {
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

		if("selectPurchaseIn".equals(action)){ //查询购货入库单
			selectPurchaseInOrder(request,response);
		}
		else if("editCenter".equals(action)){ //编辑入库信息
			request.getRequestDispatcher("warehouse/centerInEdit.jsp").forward(request, response);			
		}
		else if("saveCenter".equals(action)){  //保存入库信息跳转回入库页		
			String inDateInput = request.getParameter("inDateInput");
			String remarkInput = request.getParameter("remarkInput");
			if(inDateInput != ""){
				request.getSession().setAttribute("inDateInput", inDateInput);
				request.getSession().setAttribute("remarkInput", remarkInput);
				String purchaseId = request.getSession().getAttribute("purchaseInId").toString();
				String inDate = request.getSession().getAttribute("inDateInput").toString();
				String remark = request.getSession().getAttribute("remarkInput").toString();
				PurchaseInOrder purchaseOrder = null;
				purchaseOrder = WarehouseService.getInstance().selectPurchaseInOrder(Integer.parseInt(purchaseId));
				request.setAttribute("purchaseId", purchaseOrder.getPurchaseId());
				request.setAttribute("productId", purchaseOrder.getProductId());
				request.setAttribute("productName", purchaseOrder.getProductName());
				request.setAttribute("expectedQuantity", purchaseOrder.getProductQuantity());
				request.setAttribute("acturalQuantity", purchaseOrder.getProductQuantity());
				request.setAttribute("productAmount", purchaseOrder.getProductAmount());
				request.setAttribute("inDate", inDate);
				request.setAttribute("remark", remark);
				
				request.getRequestDispatcher("warehouse/centerInResult.jsp").forward(request, response);
				
			}else{
				request.setAttribute("message", "请至少输入入库日期");
				request.getRequestDispatcher("warehouse/centerInEdit.jsp").forward(request, response);
			}		
		}
		else if("centerIn".equals(action)){  //入库操作
			intoCenter(request,response);
			request.getRequestDispatcher("warehouse/centerIn.jsp").forward(request, response);
		}
		else if("selectTransfer".equals(action)){  //查找出库调拨单
			selectProductTransferOrder(request,response);
		}
		else if("centerOut".equals(action)){  //中心库房出库
			outCenter(request,response);
		}
		else if("selectDistri".equals(action)){  //查找分发单
			selectDistributionOrder(request,response);
		}
		else if("editSubWarehouse".equals(action)){  //编辑分站入库信息
			request.getRequestDispatcher("warehouse/subWarehouseInEdit.jsp").forward(request, response);
		}
		else if("saveSubWarehouse".equals(action)){ //保存分站入库信息并跳回分站入库页
			String inDateInput = request.getParameter("inDateInput");
			String remarkInput = request.getParameter("remarkInput");
			if(inDateInput != ""){
				request.getSession().setAttribute("inDateInput", inDateInput);
				request.getSession().setAttribute("remarkInput", remarkInput);
				String inDate = request.getSession().getAttribute("inDateInput").toString();
				String remark = request.getSession().getAttribute("remarkInput").toString();
				String distributionId = request.getSession().getAttribute("distributionInId").toString();
				DistributionOrder dOrder = null;
				dOrder = WarehouseService.getInstance().selectDistributionOrder(Integer.parseInt(distributionId));
				request.setAttribute("distributionId",dOrder.getDistributionId());
				request.setAttribute("productName", dOrder.getProductName());
				request.setAttribute("expectedQuantity", dOrder.getProductQuantity());
				request.setAttribute("acturalQuantity", dOrder.getProductQuantity());
				request.setAttribute("inDate", inDate);
				request.setAttribute("remark", remark);
				
				request.getRequestDispatcher("warehouse/subWarehouseInResult.jsp").forward(request, response);
				
			}else{
				request.setAttribute("message", "请至少输入入库日期");
				request.getRequestDispatcher("warehouse/subWarehouseInEdit.jsp").forward(request, response);
			}
		}
		else if("subWarehouseIn".equals(action)){ //分站库房入库
			inSubWarehouse(request,response);
			request.getRequestDispatcher("warehouse/subWarehouseIn.jsp").forward(request, response);
		}
		else if("selectWork".equals(action)){ //查找任务单
			selectWork(request,response);
		}
		else if("editReceive".equals(action)){  //编辑领货信息
			request.getRequestDispatcher("warehouse/receiveEdit.jsp").forward(request, response);
		}
		else if("saveReceive".equals(action)){  //保存分站领货信息
			String receiveDateInput = request.getParameter("receiveDateInput");
			String remarkInput = request.getParameter("remarkInput");
			String personInput = request.getParameter("personInput");
			if(receiveDateInput != ""){
				request.getSession().setAttribute("receiveDateInput", receiveDateInput);
				request.getSession().setAttribute("remarkInput", remarkInput);
				request.getSession().setAttribute("personInput", personInput);
				String receiveDate = request.getSession().getAttribute("receiveDateInput").toString();
				String remark = request.getSession().getAttribute("remarkInput").toString();
				String receivePerson = request.getSession().getAttribute("personInput").toString();
				String workId = request.getSession().getAttribute("workIdInput").toString();
				request.setAttribute("workId",Integer.parseInt(workId));
				request.setAttribute("receiveDate", receiveDate);
				request.setAttribute("receivePerson", receivePerson);
				request.setAttribute("remark", remark);
				
				request.getRequestDispatcher("warehouse/receiveResult.jsp").forward(request, response);
				
			}else{
				request.setAttribute("message", "请至少输入领货日期及领货人");
				request.getRequestDispatcher("warehouse/receiveEdit.jsp").forward(request, response);
			}
		}
		//领货
		else if("receive".equals(action)){
			receiveProduct(request,response);
			request.getRequestDispatcher("warehouse/receive.jsp").forward(request, response);
		}
		//查找出库信息
		else if("selectCenterOut".equals(action)){
			selectCenterOut(request,response);
		}
		//查找分发信息
		else if("selectDistribution".equals(action)){
			selectDistribution(request,response);
		}
		//分页查询货物调拨单
		else if("selectTransferByPage".equals(action)){
			selectTransferByPage(request,response);
		}
		//分页查询出库信息
		else if("selectCenterOutByPage".equals(action)){
			selectCenterOutByPage(request,response);
		}
		//分页查询分发信息
		else if("selectCenterOutByPage".equals(action)){
			selectDistriByPage(request,response);
		}	
	}
	
	private void selectDistriByPage(HttpServletRequest request, HttpServletResponse response) {
		String outDateInput = request.getSession().getAttribute("outDateInput").toString();
		String productName = request.getSession().getAttribute("productInput").toString();
		String warehouseName = request.getSession().getAttribute("warehouseName").toString();
		Date outDate = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			outDate = formatter.parse(outDateInput);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String p = request.getParameter("page");
		int page = 1;
		if (p != null && !"".equals(p)) {
			page = Integer.parseInt(p);
		}
		//重新查询
		List<DistributionOrder> list = WarehouseService.getInstance().selectDistribution(outDate,productName,warehouseName);
		List<DistributionOrder> li = new ArrayList<DistributionOrder>();
		for (int i = (page-1)*3; i < page*3; i++) {
			if (i>list.size()-1) {
				break;
			}
			li.add(list.get(i));
		}
		request.setAttribute("resultList", li);
		request.getSession().setAttribute("page2", p);
		try {					
			request.getRequestDispatcher("warehouse/selectDistributionResult.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void selectCenterOutByPage(HttpServletRequest request, HttpServletResponse response) {
		String outDateInput = request.getSession().getAttribute("outDateInput").toString();
		String productName = request.getSession().getAttribute("productInput").toString();
		Date outDate = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			outDate = formatter.parse(outDateInput);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		String p = request.getParameter("page");
		int page = 1;
		if (p != null && !"".equals(p)) {
			page = Integer.parseInt(p);
		}
		//重新查询
		List<CenterOutRecord> corList = WarehouseService.getInstance().selectCenterOut(outDate,productName);
		List<CenterOutRecord> li = new ArrayList<CenterOutRecord>();
		for (int i = (page-1)*3; i < page*3; i++) {
			if (i>corList.size()-1) {
				break;
			}
			li.add(corList.get(i));
		}
		
		request.setAttribute("resultList", li);
		request.getSession().setAttribute("page1", p);
		try {					
			request.getRequestDispatcher("warehouse/selectCenterOutResult.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void selectTransferByPage(HttpServletRequest request, HttpServletResponse response) {
		String str = request.getSession().getAttribute("completeDateInput").toString();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date inputDate = null;
		try {
			inputDate = formatter.parse(str);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		String p = request.getParameter("page");
		int page = 1;
		if (p != null && !"".equals(p)) {
			page = Integer.parseInt(p);
		}
		//重新查询
		List<ProductTransferOrder> list = WarehouseService.getInstance().selectProductTransferOrder(inputDate);
		List<ProductTransferOrder> li = new ArrayList<ProductTransferOrder>(); 
		for (int i = (page-1)*3; i < page*3; i++) {
			if (i>list.size()-1) {
				break;
			}
			li.add(list.get(i));
		}
		
		request.setAttribute("resultList", li);
		request.getSession().setAttribute("page", p);
		try {					
			request.getRequestDispatcher("warehouse/centerOutResult.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void selectDistribution(HttpServletRequest request, HttpServletResponse response) {
		String outDateInput = request.getParameter("outDateInput");
		String productName = request.getParameter("productInput");
		String warehouseName = request.getParameter("warehouseName");
		Date outDate = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			outDate = formatter.parse(outDateInput);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(outDate != null && warehouseName != ""){
			request.getSession().setAttribute("outDateInput", outDateInput);
			request.getSession().setAttribute("productName", productName);
			request.getSession().setAttribute("warehouseName", warehouseName);
			List<DistributionOrder> list = null;
			if(productName != ""){
				list = WarehouseService.getInstance().selectDistribution(outDate,productName,warehouseName);
			}else{
				//不输入商品名则默认搜索全部商品
				list = WarehouseService.getInstance().selectDistribution(outDate,warehouseName);
			}
			if(list.size() == 0 || list == null){
				//查询失败
				request.setAttribute("message", "未查询到分发信息");
				try {
					request.getRequestDispatcher("warehouse/selectDistribution.jsp").forward(request, response);
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else{
				//查询成功
				List<DistributionOrder> li = new ArrayList<DistributionOrder>(); //每页显示的列表
				for (int i = 0; i < list.size(); i++) { //一页显示三条
					if (i>2) {
						break;
					}
					li.add(list.get(i));
				}
				int pageNum = list.size()%3 == 0?list.size()/3:list.size()/3+1;
				if (list.isEmpty()) {
					pageNum = 1;
				}
				request.getSession().setAttribute("pageNum2", pageNum);
				request.getSession().setAttribute("page2", 1);
				request.setAttribute("resultList", list);
				
				try {
					request.getRequestDispatcher("warehouse/selectDistributionResult.jsp").forward(request, response);
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}else{
			//没有输入条件
			request.setAttribute("message", "请至少输入出库日期和仓库名称");
			try {
				request.getRequestDispatcher("warehouse/selectDistribution.jsp").forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	private void selectCenterOut(HttpServletRequest request, HttpServletResponse response) {
		String outDateInput = request.getParameter("outDateInput");
		String productName = request.getParameter("productInput");
		request.getSession().setAttribute("outDateInput",outDateInput);
		request.getSession().setAttribute("productInput",productName);
		Date outDate = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			outDate = formatter.parse(outDateInput);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(outDate != null){
			List<CenterOutRecord> corList = null;
			if(productName != ""){
				corList = WarehouseService.getInstance().selectCenterOut(outDate,productName);
			}else{
				//不输入商品名则默认搜索全部商品
				corList = WarehouseService.getInstance().selectCenterOut(outDate);				
			}
			if(corList.size() == 0 || corList == null){
				//查询失败
				request.setAttribute("message", "未查询到出库信息");
				try {
					request.getRequestDispatcher("warehouse/selectCenterOut.jsp").forward(request, response);
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else{
				//查询成功
				List<CenterOutRecord> li = new ArrayList<CenterOutRecord>(); //每页显示的列表
				for (int i = 0; i < corList.size(); i++) { //一页显示三条
					if (i>2) {
						break;
					}
					li.add(corList.get(i));
				}
				int pageNum = corList.size()%3 == 0?corList.size()/3:corList.size()/3+1;
				if (corList.isEmpty()) {
					pageNum = 1;
				}
				request.setAttribute("resultList", corList);
				request.getSession().setAttribute("pageNum1", pageNum);
				request.getSession().setAttribute("page1", 1);
				try {
					request.getRequestDispatcher("warehouse/selectCenterOutResult.jsp").forward(request, response);
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}else{
			//没有输入条件
			request.setAttribute("message", "请至少输入出库日期");
			try {
				request.getRequestDispatcher("warehouse/selectCenterOut.jsp").forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	//领货
	private void receiveProduct(HttpServletRequest request, HttpServletResponse response) {
		String workId = request.getSession().getAttribute("workIdInput").toString();
		String receivePerson = request.getSession().getAttribute("personInput").toString();
		String receiveDate = request.getSession().getAttribute("receiveDateInput").toString();
		String remark = request.getSession().getAttribute("remarkInput").toString();
		ReceiveRecord rr = new ReceiveRecord();
		rr.setWorkId(Integer.parseInt(workId));
		rr.setReceivePerson(receivePerson);
		rr.setRemark(remark);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			rr.setReceiveDate(formatter.parse(receiveDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//Employee e = request.getSession().getAttribute("employee");
		if(WarehouseService.getInstance().receiveProduct(rr,"zfy")){
			request.setAttribute("message", "领货成功");
		}else{
			request.setAttribute("message", "领货失败");
		}
	}

	//查找任务单
	private void selectWork(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("workIdInput");
		if(id != ""){
			int workId = Integer.parseInt(id);
			request.getSession().setAttribute("workIdInput", workId);
			if(WarehouseService.getInstance().selectWorkOrder(workId)){
				//查找成功
				request.setAttribute("workId", workId);
				request.setAttribute("receivePerson", " ");
				request.setAttribute("receiveDate", " ");
				request.setAttribute("remark", " ");
				try {
					request.getRequestDispatcher("warehouse/receiveResult.jsp").forward(request, response);
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else{
				//查找失败
				request.setAttribute("message", "未查找到相应任务单");
				try {
					request.getRequestDispatcher("warehouse/receive.jsp").forward(request, response);
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}			
		}else{
			//没有输入条件
			request.setAttribute("message", "请输入任务单号");
			try {
				request.getRequestDispatcher("warehouse/receive.jsp").forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	
	}

	//分站库房入库
	private void inSubWarehouse(HttpServletRequest request, HttpServletResponse response) {
		String distributionId = request.getSession().getAttribute("distributionInId").toString();
		DistributionOrder dOrder = null;
		dOrder = WarehouseService.getInstance().selectDistributionOrder(Integer.parseInt(distributionId));
		SubInRecord sir = new SubInRecord();
		sir.setDistributionId(dOrder.getDistributionId());
		sir.setProductId(dOrder.getProductId());
		sir.setExpectedQuantity(dOrder.getProductQuantity());
		sir.setActuralQuantity(dOrder.getProductQuantity());
		sir.setRemark(request.getSession().getAttribute("remarkInput").toString());
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			sir.setInDate(formatter.parse(request.getSession().getAttribute("inDateInput").toString()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//Employee e = request.getSession().getAttribute("employee");
		if(WarehouseService.getInstance().inSubWarehouse(sir,"zfy")){
			request.setAttribute("message", "入库成功");
		}else{
			request.setAttribute("message", "入库失败");
		}
	}

	//查找分发单
	private void selectDistributionOrder(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("distributionInId");		
		if(id != ""){
			int distributionId = Integer.parseInt(id);
			request.getSession().setAttribute("distributionInId", distributionId);
			DistributionOrder dOrder = null;
			dOrder = WarehouseService.getInstance().selectDistributionOrder(distributionId);
			if(dOrder.getDistributionId() != 0){
				//查找成功
				request.setAttribute("distributionId",dOrder.getDistributionId());
				request.setAttribute("productName", dOrder.getProductName());
				request.setAttribute("expectedQuantity", dOrder.getProductQuantity());
				request.setAttribute("acturalQuantity", dOrder.getProductQuantity());
				request.setAttribute("inDate", " ");
				request.setAttribute("remark", " ");
				try {
					request.getRequestDispatcher("warehouse/subWarehouseInResult.jsp").forward(request, response);
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else{
				//查找失败
				request.setAttribute("message", "未查找到相应分发单");
				try {
					request.getRequestDispatcher("warehouse/subWarehouseIn.jsp").forward(request, response);
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}else{
			//未输入 分发单号
			request.setAttribute("message", "请输入分发单号");
			try {
				request.getRequestDispatcher("warehouse/subWarehouseIn.jsp").forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}

	//中心库房调拨出库
	private void outCenter(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("transferOrder");
		if(id != null){
			int transferId = Integer.parseInt(id);
			//System.out.println(transferId);
			//Employee e = request.getSession().getAttribute("employee");
			if(WarehouseService.getInstance().outCenter(transferId,"zfy")){
				request.setAttribute("message", "出库成功");
			}else{
				request.setAttribute("message", "出库失败");
			}
			try {
				request.getRequestDispatcher("warehouse/centerOut.jsp").forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			request.setAttribute("message", "请选择调拨单");
			try {
				request.getRequestDispatcher("warehouse/centerOutResult.jsp").forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
		
	}

	//查找购货入库调拨单
	private void selectPurchaseInOrder(HttpServletRequest request, HttpServletResponse response){
		String purchaseId = request.getParameter("purchaseInId");
		if(purchaseId != ""){
			PurchaseInOrder purchaseOrder = null;
			request.getSession().setAttribute("purchaseInId", Integer.parseInt(purchaseId));
			purchaseOrder = WarehouseService.getInstance().selectPurchaseInOrder(Integer.parseInt(purchaseId));
			if(purchaseOrder.getPurchaseId() != 0){
				//查找成功			
				request.setAttribute("purchaseId", purchaseOrder.getPurchaseId());
				request.setAttribute("productId", purchaseOrder.getProductId());
				request.setAttribute("productName", purchaseOrder.getProductName());
				request.setAttribute("expectedQuantity", purchaseOrder.getProductQuantity());
				request.setAttribute("acturalQuantity", purchaseOrder.getProductQuantity());
				request.setAttribute("productAmount", purchaseOrder.getProductAmount());
				request.setAttribute("inDate", " ");
				request.setAttribute("remark", " ");
				try {
					request.getRequestDispatcher("warehouse/centerInResult.jsp").forward(request, response);
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else{
				//查找失败
				request.setAttribute("message", "未查询到购货单");
				try {
					request.getRequestDispatcher("warehouse/centerIn.jsp").forward(request, response);
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}else{
			//无输入条件
			request.setAttribute("message", "请输入购货单号");
			try {
				request.getRequestDispatcher("warehouse/centerIn.jsp").forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
		
	}
	//中心库房将货物入库
	private void intoCenter(HttpServletRequest request, HttpServletResponse response){
		
		String purchaseId = request.getSession().getAttribute("purchaseInId").toString();
		PurchaseInOrder purchaseOrder = null;
		purchaseOrder = WarehouseService.getInstance().selectPurchaseInOrder(Integer.parseInt(purchaseId));		
		
		CenterInRecord cInRecord = new CenterInRecord();
		cInRecord.setPurchaseId(purchaseOrder.getPurchaseId());
		cInRecord.setProductId(purchaseOrder.getProductId());
		cInRecord.setProductName(purchaseOrder.getProductName());
		cInRecord.setExpectedQuantity(purchaseOrder.getProductQuantity());
		cInRecord.setActuralQuantity(purchaseOrder.getProductQuantity());
		cInRecord.setRemark(request.getSession().getAttribute("remarkInput").toString());
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			//String类型的日期转换为java.util.Date
			cInRecord.setCenterInDate(formatter.parse(request.getSession().getAttribute("inDateInput").toString()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		//Employee e = request.getSession().getAttribute("employee");
		if(WarehouseService.getInstance().intoCenter(cInRecord,"zfy")){
			request.setAttribute("message", "入库成功");
		}else{
			request.setAttribute("message", "入库失败");
		}
		
	}
	//查找调度中心产生的出库货物调拨单
	private void selectProductTransferOrder(HttpServletRequest request, HttpServletResponse response){
		String str = request.getParameter("completeDate");
		request.getSession().setAttribute("completeDateInput", str);
		//String类型的日期转换为java.util.Date
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date inputDate = null;
		try {
			inputDate = formatter.parse(str);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		if(inputDate != null){
			List<ProductTransferOrder> list = WarehouseService.getInstance().selectProductTransferOrder(inputDate);
			if(list.size() == 0 || list == null){
				//查找失败
				request.setAttribute("message", "未查询到相应货物调拨单");
				try {
					request.getRequestDispatcher("warehouse/centerOut.jsp").forward(request, response);
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else{
				//查找成功
				//System.out.println(list.size());
				List<ProductTransferOrder> li = new ArrayList<ProductTransferOrder>(); //每页显示的列表
				for (int i = 0; i < list.size(); i++) { //一页显示三条
					if (i>2) {
						break;
					}
					li.add(list.get(i));
				}
				int pageNum = list.size()%3 == 0?list.size()/3:list.size()/3+1;
				if (list.isEmpty()) {
					pageNum = 1;
				}
				request.setAttribute("resultList", li);
				request.getSession().setAttribute("pageNum", pageNum);
				request.getSession().setAttribute("page", 1);
				try {					
					request.getRequestDispatcher("warehouse/centerOutResult.jsp").forward(request, response);
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}else{
			request.setAttribute("message", "请至少输入要求完成日期");
			try {
				request.getRequestDispatcher("warehouse/centerOut.jsp").forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
