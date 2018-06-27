package com.neuedu.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.model.po.WorkOrder;
import com.neuedu.model.service.SubstationService;
import com.sun.security.auth.NTDomainPrincipal;

/**
 * Servlet implementation class SubstationServlet
 */
public class SubstationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		
		String action = (String)request.getParameter("action");
		System.out.println("action:"+action);
		
		if(action.equals("search")){
			workQuery(request, response);
		}
		
	}
	
	private void workQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
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
		
		SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
		if(requireDate!=null && !requireDate.equals("")){
			try {
				newRequireDate = sd.parse(requireDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		int newWorkType = 0;
		if(workType!=null && !workType.equals("")){
			if(workType.equals("送货收款")){
				newWorkType = 1;
			}
			if(workType.equals("送货")){
				newWorkType = 2;
			}
			if(workType.equals("收款")){
				newWorkType = 3;
			}
			if(workType.equals("退货")){
				newWorkType = 4;
			}
		}
		
		int newWorkStatus = 0;
		if(workStatus!=null && !workStatus.equals("")){
			if(workStatus.equals("已调度")){
				newWorkStatus = 1;
			}
			if(workStatus.equals("可分配")){
				newWorkStatus = 2;
			}
			if(workStatus.equals("已分配")){
				newWorkStatus = 3;
			}
			if(workStatus.equals("已领货")){
				newWorkStatus = 4;
			}
			if(workStatus.equals("已完成")){
				newWorkStatus = 5;
			}
		}
		//查询,注意要传转换之后的参数
		List<WorkOrder> list = SubstationService.getInstance().selectPageWork(newRequireDate, newWorkStatus, newWorkType, pageNum);
		for(WorkOrder workOrder:list){
			System.out.println(workOrder.getClientName()+":"+workOrder.getClientPhone());
		}
		//获取页码
		int pageCount = SubstationService.getInstance().selectPageCount(newRequireDate, newWorkStatus, newWorkType);
		request.setAttribute("resultList", list);
		request.setAttribute("pageCount", pageCount);
		request.getSession().setAttribute("pageNum", pageNum);//默认第一次是第1页
		request.getSession().setAttribute("requireDate", requireDate);
		request.getSession().setAttribute("workStatus", workStatus);
		request.getSession().setAttribute("workType", workType);
		
		//跳转，请求转发，因为要用到数据
		request.getRequestDispatcher("substation/searchWorkOrder.jsp").forward(request, response);
	}
	
}






