package com.neuedu.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.model.po.Employee;
import com.neuedu.model.po.Product;
import com.neuedu.model.po.Supplier;
import com.neuedu.model.service.ProductService;


/**
 * Servlet implementation class OrderServlet
 */
public class ProductSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductSelectServlet() {
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
		if("creatProduct".equals(action)){
			try {
				creatProduct(req,resp);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


	}
	private void creatProduct(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException, ParseException{
		String productCode=req.getParameter("productCode");
		String productName=req.getParameter("productName");
		int firstClassId=0;
		String fClassId = req.getParameter("firstClassId");
		if(fClassId!=null&&!"".equals(fClassId)){
			firstClassId = Integer.parseInt(fClassId);
		}
		int secondClassId=0;
		String sClassId = req.getParameter("secondClassId");
		if(sClassId!=null&&!"".equals(sClassId)){
			secondClassId = Integer.parseInt(sClassId);
		}		
		String productUnit=req.getParameter("productUnit");
		
		float productPrice=0;
		String pPrice = req.getParameter("productPrice");
		if(sClassId!=null&&!"".equals(sClassId)){
			productPrice = Float.parseFloat(pPrice);
		}	
		
		float productDiscount=0;
		String pDiscount = req.getParameter("productDiscount");
		if(pDiscount!=null&&!"".equals(pDiscount)){
			productDiscount = Float.parseFloat(pDiscount);
		}	
		
		float productCost=0;
		String pCost = req.getParameter("productCost");
		if(pCost!=null&&!"".equals(pCost)){
			productCost = Float.parseFloat(pDiscount);
		}
		String productModel=req.getParameter("productModel");
		
		String manufacturer=req.getParameter("manufacturer");
		
		Date expirationDate=null;
		String eDate = req.getParameter("expirationDate");
		if(eDate!=null&&!"".equals(eDate)){
			expirationDate = new SimpleDateFormat("YYYY-MM-DD").parse(eDate);
		}
		req.getParameter("");
		
		int returnAble= 0;
		String rAble =req.getParameter("returnAble");
		if(rAble!=null&&!"".equals(rAble)){
			returnAble = 1;
		}
		int supId= 0;
		String sId =req.getParameter("supId");
		if(sId!=null&&!"".equals(sId)){
			supId = Integer.parseInt(sId);
		}
		String remark=req.getParameter("remark");
		int status=1;
		
		//Employee employee = (Employee)req.getSession().getAttribute("employee");
		Employee employee = new Employee();
		employee.setEmployeeId(1);
		employee.setEmployeeName("王昊文");
		
		String operator=employee.getEmployeeName();
		Date operateDate= new Date();
		
		/*添加商品*/
		
		Product product = new Product();
		product.setProductCode(productCode);
		product.setProductName(productName);
		product.setFirstClassId(firstClassId);
		product.setSecondClassId(secondClassId);
		product.setProductModel(productModel);
		product.setProductUnit(productUnit);
		product.setProductPrice(productPrice);
		product.setProductDiscount(productDiscount);
		product.setProductCost(productCost);
		
		//supplier类待完善
		Supplier supplier = new Supplier();
		supplier.setSupId(supId);
		product.setSupplier(supplier);
		
		
		product.setManufacturer(manufacturer);
		product.setExpirationDate(expirationDate);
		product.setReturnAble(returnAble);
		product.setRemark(remark);
		product.setStatus(status);
		product.setOperator(operator);
		product.setOperateDate(operateDate);
		
		ProductService.getInstance().creatProduct(product);
	}

}
