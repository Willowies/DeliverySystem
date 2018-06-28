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

		if("selectPurchaseIn".equals(action)){ //��ѯ������ⵥ
			selectPurchaseInOrder(request,response);
		}
		else if("editCenter".equals(action)){ //�༭�����Ϣ
			request.getRequestDispatcher("warehouse/centerInEdit.jsp").forward(request, response);			
		}
		else if("saveCenter".equals(action)){  //���������Ϣ��ת�����ҳ		
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
				request.setAttribute("message", "�����������������");
				request.getRequestDispatcher("warehouse/centerInEdit.jsp").forward(request, response);
			}		
		}
		else if("centerIn".equals(action)){  //������
			intoCenter(request,response);
			request.getRequestDispatcher("warehouse/centerIn.jsp").forward(request, response);
		}
		else if("selectTransfer".equals(action)){  //���ҳ��������
			selectProductTransferOrder(request,response);
		}
		else if("centerOut".equals(action)){  //���Ŀⷿ����
			outCenter(request,response);
		}
		else if("selectDistri".equals(action)){  //���ҷַ���
			selectDistributionOrder(request,response);
		}
		else if("editSubWarehouse".equals(action)){  //�༭��վ�����Ϣ
			request.getRequestDispatcher("warehouse/subWarehouseInEdit.jsp").forward(request, response);
		}
		else if("saveSubWarehouse".equals(action)){ //�����վ�����Ϣ�����ط�վ���ҳ
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
				request.setAttribute("message", "�����������������");
				request.getRequestDispatcher("warehouse/subWarehouseInEdit.jsp").forward(request, response);
			}
		}
		else if("subWarehouseIn".equals(action)){ //��վ�ⷿ���
			inSubWarehouse(request,response);
			request.getRequestDispatcher("warehouse/subWarehouseIn.jsp").forward(request, response);
		}
		else if("selectWork".equals(action)){ //��������
			selectWork(request,response);
		}
		else if("editReceive".equals(action)){  //�༭�����Ϣ
			request.getRequestDispatcher("warehouse/receiveEdit.jsp").forward(request, response);
		}
		else if("saveReceive".equals(action)){  //�����վ�����Ϣ
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
				request.setAttribute("message", "����������������ڼ������");
				request.getRequestDispatcher("warehouse/receiveEdit.jsp").forward(request, response);
			}
		}
		//���
		else if("receive".equals(action)){
			receiveProduct(request,response);
			request.getRequestDispatcher("warehouse/receive.jsp").forward(request, response);
		}
		//���ҳ�����Ϣ
		else if("selectCenterOut".equals(action)){
			selectCenterOut(request,response);
		}
		//���ҷַ���Ϣ
		else if("selectDistribution".equals(action)){
			selectDistribution(request,response);
		}
		//��ҳ��ѯ���������
		else if("selectTransferByPage".equals(action)){
			selectTransferByPage(request,response);
		}
		//��ҳ��ѯ������Ϣ
		else if("selectCenterOutByPage".equals(action)){
			selectCenterOutByPage(request,response);
		}
		//��ҳ��ѯ�ַ���Ϣ
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
		//���²�ѯ
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
		//���²�ѯ
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
		//���²�ѯ
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
				//��������Ʒ����Ĭ������ȫ����Ʒ
				list = WarehouseService.getInstance().selectDistribution(outDate,warehouseName);
			}
			if(list.size() == 0 || list == null){
				//��ѯʧ��
				request.setAttribute("message", "δ��ѯ���ַ���Ϣ");
				try {
					request.getRequestDispatcher("warehouse/selectDistribution.jsp").forward(request, response);
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else{
				//��ѯ�ɹ�
				List<DistributionOrder> li = new ArrayList<DistributionOrder>(); //ÿҳ��ʾ���б�
				for (int i = 0; i < list.size(); i++) { //һҳ��ʾ����
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
			//û����������
			request.setAttribute("message", "����������������ںͲֿ�����");
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
				//��������Ʒ����Ĭ������ȫ����Ʒ
				corList = WarehouseService.getInstance().selectCenterOut(outDate);				
			}
			if(corList.size() == 0 || corList == null){
				//��ѯʧ��
				request.setAttribute("message", "δ��ѯ��������Ϣ");
				try {
					request.getRequestDispatcher("warehouse/selectCenterOut.jsp").forward(request, response);
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else{
				//��ѯ�ɹ�
				List<CenterOutRecord> li = new ArrayList<CenterOutRecord>(); //ÿҳ��ʾ���б�
				for (int i = 0; i < corList.size(); i++) { //һҳ��ʾ����
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
			//û����������
			request.setAttribute("message", "�����������������");
			try {
				request.getRequestDispatcher("warehouse/selectCenterOut.jsp").forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	//���
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
			request.setAttribute("message", "����ɹ�");
		}else{
			request.setAttribute("message", "���ʧ��");
		}
	}

	//��������
	private void selectWork(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("workIdInput");
		if(id != ""){
			int workId = Integer.parseInt(id);
			request.getSession().setAttribute("workIdInput", workId);
			if(WarehouseService.getInstance().selectWorkOrder(workId)){
				//���ҳɹ�
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
				//����ʧ��
				request.setAttribute("message", "δ���ҵ���Ӧ����");
				try {
					request.getRequestDispatcher("warehouse/receive.jsp").forward(request, response);
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}			
		}else{
			//û����������
			request.setAttribute("message", "���������񵥺�");
			try {
				request.getRequestDispatcher("warehouse/receive.jsp").forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	
	}

	//��վ�ⷿ���
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
			request.setAttribute("message", "���ɹ�");
		}else{
			request.setAttribute("message", "���ʧ��");
		}
	}

	//���ҷַ���
	private void selectDistributionOrder(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("distributionInId");		
		if(id != ""){
			int distributionId = Integer.parseInt(id);
			request.getSession().setAttribute("distributionInId", distributionId);
			DistributionOrder dOrder = null;
			dOrder = WarehouseService.getInstance().selectDistributionOrder(distributionId);
			if(dOrder.getDistributionId() != 0){
				//���ҳɹ�
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
				//����ʧ��
				request.setAttribute("message", "δ���ҵ���Ӧ�ַ���");
				try {
					request.getRequestDispatcher("warehouse/subWarehouseIn.jsp").forward(request, response);
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}else{
			//δ���� �ַ�����
			request.setAttribute("message", "������ַ�����");
			try {
				request.getRequestDispatcher("warehouse/subWarehouseIn.jsp").forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}

	//���Ŀⷿ��������
	private void outCenter(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("transferOrder");
		if(id != null){
			int transferId = Integer.parseInt(id);
			//System.out.println(transferId);
			//Employee e = request.getSession().getAttribute("employee");
			if(WarehouseService.getInstance().outCenter(transferId,"zfy")){
				request.setAttribute("message", "����ɹ�");
			}else{
				request.setAttribute("message", "����ʧ��");
			}
			try {
				request.getRequestDispatcher("warehouse/centerOut.jsp").forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			request.setAttribute("message", "��ѡ�������");
			try {
				request.getRequestDispatcher("warehouse/centerOutResult.jsp").forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
		
	}

	//���ҹ�����������
	private void selectPurchaseInOrder(HttpServletRequest request, HttpServletResponse response){
		String purchaseId = request.getParameter("purchaseInId");
		if(purchaseId != ""){
			PurchaseInOrder purchaseOrder = null;
			request.getSession().setAttribute("purchaseInId", Integer.parseInt(purchaseId));
			purchaseOrder = WarehouseService.getInstance().selectPurchaseInOrder(Integer.parseInt(purchaseId));
			if(purchaseOrder.getPurchaseId() != 0){
				//���ҳɹ�			
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
				//����ʧ��
				request.setAttribute("message", "δ��ѯ��������");
				try {
					request.getRequestDispatcher("warehouse/centerIn.jsp").forward(request, response);
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}else{
			//����������
			request.setAttribute("message", "�����빺������");
			try {
				request.getRequestDispatcher("warehouse/centerIn.jsp").forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
		
	}
	//���Ŀⷿ���������
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
			//String���͵�����ת��Ϊjava.util.Date
			cInRecord.setCenterInDate(formatter.parse(request.getSession().getAttribute("inDateInput").toString()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		//Employee e = request.getSession().getAttribute("employee");
		if(WarehouseService.getInstance().intoCenter(cInRecord,"zfy")){
			request.setAttribute("message", "���ɹ�");
		}else{
			request.setAttribute("message", "���ʧ��");
		}
		
	}
	//���ҵ������Ĳ����ĳ�����������
	private void selectProductTransferOrder(HttpServletRequest request, HttpServletResponse response){
		String str = request.getParameter("completeDate");
		request.getSession().setAttribute("completeDateInput", str);
		//String���͵�����ת��Ϊjava.util.Date
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
				//����ʧ��
				request.setAttribute("message", "δ��ѯ����Ӧ���������");
				try {
					request.getRequestDispatcher("warehouse/centerOut.jsp").forward(request, response);
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else{
				//���ҳɹ�
				//System.out.println(list.size());
				List<ProductTransferOrder> li = new ArrayList<ProductTransferOrder>(); //ÿҳ��ʾ���б�
				for (int i = 0; i < list.size(); i++) { //һҳ��ʾ����
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
			request.setAttribute("message", "����������Ҫ���������");
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
