package com.neuedu.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.model.po.Warehouse;
import com.neuedu.model.service.WarehouseService;

public class WarehouseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public WarehouseServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("成功进入Servlet");		
		//设定编码格式
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		if("addWarehouse".equals(action)) {
			addWarehouse(request, response);
		}else if("selectWarehouse".equals(action)) {
			selectWarehouse(request, response);
		
		}else if("deleteWarehouse".equals(action)) {
			deleteWarehouse(request, response);
		}else if("editWarehouse".equals(action)) {
			editWarehouse(request, response);
		}else if("updateWarehouse".equals(action)) {
			updateWarehouse(request, response);
		}
		
		
	}
	
	
	

	//增加库房
	private void addWarehouse(HttpServletRequest request, HttpServletResponse response) throws IOException {
		      //获取表单参数
				String warehouseName=request.getParameter("warehouseName");
				String warehouseAddress=request.getParameter("warehouseAddress");
				String warehouseKeeper=request.getParameter("warehouseKeeper");
				String warehouseRank=request.getParameter("warehouseRank");
				//封装数据
				Warehouse warehouse=new Warehouse();
				warehouse.setWarehouseName(warehouseName);
				warehouse.setWarehouseAddress(warehouseAddress);
				warehouse.setWarehouseKeeper(warehouseKeeper);
				warehouse.setWarehouseRank(Integer.parseInt(warehouseRank));
				warehouse.setStatus(1);
				warehouse.setOperator("gss");
				warehouse.setOperateDate(new Date());
				//调用模型层代码增加库房
				WarehouseService.getInstance().addWarehouse(warehouse);				
				//响应
				//1：请求转发
				//request.getRequestDispatcher("setWarehouse.jsp").forward(request, response);
				//2:重定向
				response.sendRedirect(request.getContextPath()+"/warehouse1/setWarehouse.jsp");
	} 

	
	//查询库房
	private void selectWarehouse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String warehouseName=request.getParameter("warehouseName");
		System.out.println("1"+warehouseName);
		List<Warehouse> list=WarehouseService.getInstance().selectWarehouse(warehouseName);
		//数据共享
		request.setAttribute("resultList", list);
		//跳转
		request.getRequestDispatcher("warehouse1/selectResult.jsp").forward(request, response);
	}
	
	
	
	
	
	
	//删除库房
		private void deleteWarehouse(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
			request.setCharacterEncoding("utf-8");
			// TODO Auto-generated method stub
			String warehouseId = request.getParameter("warehouseId");
			System.out.println(warehouseId);
			WarehouseService.getInstance().deleteWarehouse(warehouseId);	
			try {
				response.sendRedirect(request.getContextPath()+"/warehouse1/setWarehouse.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	
		
		
		private void editWarehouse(HttpServletRequest request, HttpServletResponse response) {
			String warehouseId=request.getParameter("warehouseId");
			Warehouse ware=WarehouseService.getInstance().getWarehouseById(Integer.parseInt(warehouseId));
			request.setAttribute("editWarehouse", ware);
			try {
				request.getRequestDispatcher("/warehouse1/updateWarehouse.jsp").forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
	
	//修改库房
	private void updateWarehouse(HttpServletRequest request, HttpServletResponse response) {
		String warehouseId = request.getParameter("warehouseId");
		String warehouseName = request.getParameter("warehouseName");
		String warehouseAddress = request.getParameter("warehouseAddress");
		String warehouseKeeper = request.getParameter("warehouseKeeper");
		String warehouseRank = request.getParameter("warehouseRank");
//		String warehouseStatus = request.getParameter("warehouseStatus");
//		String warehouseOperator = request.getParameter("warehouseOperator");
//		String warehouseOperateDate = request.getParameter("warehouseOperateDate");
		Warehouse warehouse=new Warehouse();
		warehouse.setWarehouseId(Integer.parseInt(warehouseId));
		warehouse.setWarehouseName(warehouseName);
		warehouse.setWarehouseAddress(warehouseAddress);
		warehouse.setWarehouseKeeper(warehouseKeeper);
		warehouse.setWarehouseRank(Integer.parseInt(warehouseRank));
		WarehouseService.getInstance().updateWarehouse(warehouse);
		//跳转
		try {
			response.sendRedirect(request.getContextPath()+"/warehouse1/setWarehouse.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}	
	
	
	
	
	
		
	
	
		
}
