package com.neuedu.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.synth.SynthSeparatorUI;

import com.neuedu.model.po.CenterReturnOrder;
import com.neuedu.model.po.PurchaseInOrder;
import com.neuedu.model.po.Sign;
import com.neuedu.model.service.ClearMoneyService;

/**
 * Servlet implementation class ClearMoneyServlet
 */
public class ClearMoneyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClearMoneyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String action = request.getParameter("action");
		if("selectSign".equals(action)){
			selectSign(request,response);
		}else if("clearSubstation".equals(action)){
			clearSubstation(request,response);
		}else if("selectClearedSub".equals(action)){
			selectClearedSub(request,response);
		}else if("selectOrder".equals(action)){
			selectOrder(request,response);
		}else if("clearSupplier".equals(action)){
			clearSupplier(request,response);
		}else if("selectclearedSubByPage".equals(action)){
			selectclearedSubByPage(request,response);
		}else if("selectSignByPage".equals(action)){
			selectSignByPage(request,response);
		}else if("selectClearedSup".equals(action)){
			selectClearedSup(request,response);
		}
	}
	//查询供应商已结算
	private void selectClearedSup(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//System.out.println("进到查询供应商已结算的页面里了");
		String supplier = request.getParameter("supplier"); 
		String selectDate = request.getParameter("date");
		String product = request.getParameter("product");
		Date date = null;
		
		//将String转化成Date类型
		if(selectDate != null && !"".equals(selectDate)){
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			try {
				date = df.parse(selectDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}      
		}
		
		List<PurchaseInOrder> list1 = ClearMoneyService.getInstance().selectClearedPIOrder(supplier, date, product);
		List<CenterReturnOrder> list2 = ClearMoneyService.getInstance().selectClearedCROrder(supplier, date, product);
		
		if(list1.size()==0||null == list1){
			if(list2.size()==0||null == list2){
				//未查到符合条件的签收单
				request.getSession().setAttribute("messageselectClearedSup", "未查到符合条件的签收单");
				//request.getRequestDispatcher("clearSuppiler.jsp").forward(request, response);
				response.sendRedirect(request.getContextPath()+"/finance/selectClearedSup.jsp");
			}	
			
		}else{
			//查到符合条件的签收单
			System.out.println(list1.size());
			System.out.println(list2.size());
			request.getSession().setAttribute("resultLista", list1);
			request.getSession().setAttribute("resultListb", list2);
			//request.getRequestDispatcher("clearSupplierResult.jsp").forward(request, response);
			response.sendRedirect(request.getContextPath()+"/finance/selectClearedSupResult.jsp");
		}
	}

	//与分站结算前分页查询签收单
	private void selectSignByPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String substation = (String) request.getSession().getAttribute("substationselectSign"); 
		String selectDate = (String) request.getSession().getAttribute("dateselectSign");
		String product = (String) request.getSession().getAttribute("productselectSign");
		Date date = null;
		
		//将String转化成Date类型
		if(selectDate != null && !"".equals(selectDate)){
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			try {
				date = df.parse(selectDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}      
		}
	
		String p = request.getParameter("page");
		int page = 1;
		if (p != null && !"".equals(p)) {
			page = Integer.parseInt(p);
		}
		//System.out.println(page);
		
		//进行查询
		List<Sign> list = ClearMoneyService.getInstance().selectSign(substation, date, product);
		List<Sign> l = new ArrayList<Sign>();

		for (int i = (page-1)*3; i < page*3; i++) {
			//if (i>5) {
			if (i>list.size()-1) {
				break;
			}
			l.add(list.get(i));
		}
		
		//request.getSession().setAttribute("pageNum", pageNum);
		request.getSession().setAttribute("pageselectSign", page);
		request.getSession().setAttribute("resultListselectSign", l);
		response.sendRedirect(request.getContextPath()+"/finance/clearSubstationResult.jsp");
		
		
	}

	//分页查询签收单
	private void selectclearedSubByPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String substation = (String) request.getSession().getAttribute("substationselectClearedSub"); 
		String selectDate = (String) request.getSession().getAttribute("dateselectClearedSub");
		String product = (String) request.getSession().getAttribute("productselectClearedSub");
		Date date = null;
		
		//将String转化成Date类型
		if(selectDate != null && !"".equals(selectDate)){
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			try {
				date = df.parse(selectDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}      
		}
	
		String p = request.getParameter("page");
		int page = 1;
		if (p != null && !"".equals(p)) {
			page = Integer.parseInt(p);
		}
		
		//进行查询
		List<Sign> list = ClearMoneyService.getInstance().selectClearedSub(substation, date, product);
		List<Sign> l = new ArrayList<Sign>();

		for (int i = (page-1)*3; i < page*3; i++) {
			//if (i>5) {
			if (i>list.size()-1) {
				break;
			}
			l.add(list.get(i));
		}
		
		//request.getSession().setAttribute("pageNum", pageNum);
		request.getSession().setAttribute("page", page);
		request.getSession().setAttribute("resultClearedList", l);
		response.sendRedirect(request.getContextPath()+"/finance/selectClearedSubResult.jsp");
		
	}

	//结算购货入库单和中心退货单
	private void clearSupplier(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("进到servlet的结算方法了！");
		//截取购货单、退货单id
		String[] ids = request.getParameterValues("chk");
		int[] ids1 = new int[ids.length];//购货入库单。
		int index1=0;
		int[] ids2 = new int[ids.length];//中心退货单。
		int index2=0;
		for(int i = 0;i<ids.length;i++){
			String s = ids[i].substring(0, 2);
			//System.out.println(s);
			if(s.equals("In")){
				//购货单
				ids1[index1] = Integer.parseInt(ids[i].substring(2));
				//System.out.println(ids1[index1]);
				index1++;
			}else if(s.equals("Ou")){
					//退货单
					ids2[index2] = Integer.parseInt(ids[i].substring(3));
					//System.out.println(ids2[index2]);
					index2++;
			}else {
				System.out.println("没收到");
			}
		}
		
		//Employee e = (Employee)request.getSession().getAttribute("employee");
		//ClearMoneyService.getInstance().clearSubstation(ids, e.getEmployeeName());
		//进行结算
		ClearMoneyService.getInstance().clearSuppiler(ids1, ids2, "test");
		request.getSession().setAttribute("messageclearSupplier", "结算成功");
		request.getSession().setAttribute("resultList1", null);
		request.getSession().setAttribute("resultList2", null);
		//request.getRequestDispatcher("clearSubstationResult.jsp").forward(request, response);
		response.sendRedirect(request.getContextPath()+"/finance/clearSubstationResult.jsp");
	}

	//查询与供应商未结算的单据
	private void selectOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String supplier = request.getParameter("supplier"); 
		String selectDate = request.getParameter("date");
		String product = request.getParameter("product");
		Date date = null;
		
		//将String转化成Date类型
		if(selectDate != null && !"".equals(selectDate)){
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			try {
				date = df.parse(selectDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}      
		}
		
		//进行购货入库单查询
		List<PurchaseInOrder> list1 = ClearMoneyService.getInstance().selectPIOrder(supplier, date, product);
		//进行退货单查询
		List<CenterReturnOrder> list2 = ClearMoneyService.getInstance().selectCROrder(supplier, date, product);
				
		if(list1.size()==0||null == list1){
			if(list2.size()==0||null == list2){
				//未查到符合条件的签收单
				request.getSession().setAttribute("messageselectOrder", "未查到符合条件的签收单");
				//request.getRequestDispatcher("clearSuppiler.jsp").forward(request, response);
				response.sendRedirect(request.getContextPath()+"/finance/clearSuppiler.jsp");
			}
		}else{
			//查到符合条件的签收单
			request.getSession().setAttribute("resultList1", list1);
			request.getSession().setAttribute("resultList2", list2);
			//request.getRequestDispatcher("clearSupplierResult.jsp").forward(request, response);
			response.sendRedirect(request.getContextPath()+"/finance/clearSupplierResult.jsp");
		}
	}

	//查询分站已结算过的签收单
	private void selectClearedSub(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String substation = request.getParameter("substation"); 
		String selectDate = request.getParameter("date");
		String product = request.getParameter("product");
		Date date = null;
		
		//将String转化成Date类型
		if(selectDate != null && !"".equals(selectDate)){
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			try {
				date = df.parse(selectDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}      
		}
		
		//进行查询
		List<Sign> list = ClearMoneyService.getInstance().selectClearedSub(substation, date, product);
		List<Sign> l = new ArrayList<Sign>();
		
		for (int i = 0; i < list.size(); i++) {
			//if (i>5) {
			if (i>2) {
				break;
			}
			l.add(list.get(i));
		}
		
		int pageNum = list.size()%3 == 0?list.size()/3:list.size()/3+1;
		//int pageNum = list.size()%3 == 0?l.size()/3:l.size()/3+1;
		//System.out.println(pageNum);
		if (list.isEmpty()) {
			pageNum = 1;
			//System.out.println("页数为空");
		}
		
		if(list.size()==0||null == list){
			//未查到符合条件的签收单
			request.getSession().setAttribute("messageCleared", "未查到符合条件的签收单");
			//request.getRequestDispatcher("finance/selectClearedSub.jsp").forward(request, response);
			response.sendRedirect(request.getContextPath()+"/finance/selectClearedSub.jsp");
		}else{
			//查到符合条件的签收单
			System.out.println(list.size());
			request.getSession().setAttribute("pageNum", pageNum);
			request.getSession().setAttribute("page", 1);
			request.getSession().setAttribute("resultClearedList", l);
			request.getSession().setAttribute("substationselectClearedSub", substation);
			request.getSession().setAttribute("dateselectClearedSub", selectDate);
			request.getSession().setAttribute("productselectClearedSub", product);
			//request.getRequestDispatcher("finance/selectClearedSubResult.jsp").forward(request, response);
			response.sendRedirect(request.getContextPath()+"/finance/selectClearedSubResult.jsp");
		}
	}

	//结算签收单
	private void clearSubstation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("进到了签收Servlet");
		
		String[] ids = request.getParameterValues("chk");
		int[] idss = new int[ids.length];
		for(int i = 0;i<ids.length;i++){
			idss[i] = Integer.parseInt(ids[i]);
			System.out.println(idss[i]);
		}
		//Employee e = (Employee)request.getSession().getAttribute("employee");
		//ClearMoneyService.getInstance().clearSubstation(ids, e.getEmployeeName());
		ClearMoneyService.getInstance().clearSubstation(idss, "test");
		request.getSession().setAttribute("messageC", "结算成功");
		
		request.getSession().setAttribute("resultListselectSign", null);
		//request.getRequestDispatcher("clearSubstationResult.jsp").forward(request, response);	
		response.sendRedirect(request.getContextPath()+"/finance/clearSubstationResult.jsp");
	}

	//与分站结算前查询签收单
	private void selectSign(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("进到选择方法里了。");
		
		String substation = request.getParameter("substation"); 
		String selectDate = request.getParameter("date");
		String product = request.getParameter("product");
		Date date = null;
		//System.out.println(selectDate);
		//System.out.println(product);
		//将String转化成Date类型
		if(selectDate != null && !"".equals(selectDate)){
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			try {
				date = df.parse(selectDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}      
		}
		
		List<Sign> list = ClearMoneyService.getInstance().selectSign(substation, date, product);
		List<Sign> l = new ArrayList<Sign>();
		
		for (int i = 0; i < list.size(); i++) {
			//if (i>5) {
			if (i>2) {
				break;
			}
			l.add(list.get(i));
		}
		
		int pageNum = list.size()%3 == 0?list.size()/3:list.size()/3+1;
		//int pageNum = list.size()%3 == 0?l.size()/3:l.size()/3+1;
		//System.out.println(pageNum);
		if (list.isEmpty()) {
			pageNum = 1;
			//System.out.println("页数为空");
		}
		
		if(list.size()==0||null == list){
			//未查到符合条件的签收单
			request.getSession().setAttribute("messageCSUB", "未查到符合条件的签收单");
			//request.getRequestDispatcher("finance/clearSubstation.jsp").forward(request, response);
			response.sendRedirect(request.getContextPath()+"/finance/clearSubstation.jsp");
		}else{
			//查到符合条件的签收单
			System.out.println(list.size());
			request.getSession().setAttribute("pageNumselectSign", pageNum);
			request.getSession().setAttribute("pageselectSign", 1);
			request.getSession().setAttribute("substationselectSign", substation);
			request.getSession().setAttribute("dateselectSign", selectDate);
			request.getSession().setAttribute("productselectSign", product);
			request.getSession().setAttribute("resultListselectSign", l);
			//request.getRequestDispatcher("finance/clearSubstationResult.jsp").forward(request, response);
			response.sendRedirect(request.getContextPath()+"/finance/clearSubstationResult.jsp");
		}
	}

}
