package com.neuedu.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.model.po.Supplier;
import com.neuedu.model.service.SupService;


/**
 * Servlet implementation class ClientSearch
 */
public class SupSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupSearchServlet() {
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
		
		String pagenum = request.getParameter("pageNum");
		
		int pageNum = 1;
		String supName = "";
		//String clientIc = "";
		//String clientMobilePhone = "";
		//String pageSize = "";
		
		if (pagenum != null && !"".equals(pagenum)) {
			// ���ҳ����в�ѯ
			pageNum = Integer.parseInt(pagenum);
			
			supName = (String) request.getSession().getAttribute("supName");
			//clientIc = (String) request.getSession().getAttribute("clientIc");
			//clientMobilePhone = (String) request.getSession().getAttribute("clientMobilePhone");
			//pageSize = (String) request.getSession().getAttribute("pageSize");
		} else {
			// �����ѯ��ť���в�ѯ
			supName = request.getParameter("supName");
			//clientIc = request.getParameter("clientIc");
			//clientMobilePhone = request.getParameter("clientMobilePhone");
			//pageSize = request.getParameter("pageSize");
		}
		
		//int pSizeNew = 0;
		//if (pageSize != null && !"".equals(pageSize)) {
			//pSizeNew = Integer.parseInt(pageSize);
		//}
		
		// ��ѯ
		 /*List<Client> list = ClientService.getInstance().selectUser(clientname, clientIc, clientMobilePhone); 
		
		// ��ѯָ��ҳ������*/
		//List<Supplier> list = ClientService.getInstance().selectClient(supName, clientIc, clientMobilePhone,pageNum);
		List<Supplier> list = SupService.getInstance().selectSup(supName, pageNum);
		// ��ѯҳ��
		//int pagecount = ClientService.getInstance().selectPageCount(supName, clientIc, clientMobilePhone);
		int pagecount = SupService.getInstance().selectPageCount(supName);
		
		/*
		 * ���ݹ��� page request:���������� session����ǰ�����Ӧ�ĻỰ���� applicationContext��Ӧ��
		 * ԭ�򣺾�����ѡ��ΧС�������� ���Ⲣ������ �ര��ͬʱ����ʱ�������ݻ��ҵ�����
		 */
		request.setAttribute("resultList", list);
		request.setAttribute("pagecount", pagecount);
		request.getSession().setAttribute("pageNum", pageNum);
		
		request.getSession().setAttribute("supName", supName);
		//request.getSession().setAttribute("clinetIc", clientIc);
		//request.getSession().setAttribute("clinetMobilePhone", clientMobilePhone);
		//request.getSession().setAttribute("pageSize", pageSize);
		// ��ת
		try {
			request.getRequestDispatcher("/supService/supSelectResult.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}