package com.yitengls.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.yitengls.dao.TcaseDao;

/**
 * Servlet implementation class SZCCaseHandler
 */
@WebServlet("/SZCCaseHandler")
public class SZCCaseHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SZCCaseHandler() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = (String) request.getParameter("action");
		if (action.equals("getCase")) {
			String yearSelect = request.getParameter("yearSelect");
			String typeSelect = request.getParameter("typeSelect");
			String numberBtn = request.getParameter("numberBtn");
			int pageNow = Integer.parseInt((String) request
					.getParameter("pageNow"));
			int pageSize = Integer.parseInt((String) request
					.getParameter("pageSize"));
			int sponsorid = Integer.parseInt(request
					.getParameter("sponsorSelect"));
			int coordinatorid = Integer.parseInt(request
					.getParameter("coordinatorSelect"));
			String fuzzy = request.getParameter("fuzzyBtn");
			TcaseDao casedao = new TcaseDao();

			List list = null;
			if (typeSelect.equals("1")) {//查tcase1 
				if (pageNow != 0) {
					list = casedao.getCases1(yearSelect, numberBtn, sponsorid,
							coordinatorid, fuzzy, pageNow, pageSize);
				}else{
					list = casedao.getCases1(yearSelect, numberBtn, sponsorid,
							coordinatorid, fuzzy);
				}
			}else{//查tcase2
				if (pageNow != 0) {
					list = casedao.getCases2(yearSelect, numberBtn, sponsorid,
							coordinatorid, fuzzy, pageNow, pageSize);
				}else{
					list = casedao.getCases2(yearSelect, numberBtn, sponsorid,
							coordinatorid, fuzzy);
				}
			}
			Gson gson = new Gson();
			System.out.println(gson.toJson(list));
			response.getWriter().print(gson.toJson(list));
			
		}else if(action.equals("saveProxyid")){
			int oid = Integer.parseInt(request.getParameter("id"));
			int casetype = Integer.parseInt(request.getParameter("casetype"));
			String proxyid = request.getParameter("proxyid");
			TcaseDao dao = new TcaseDao();
			int ret = dao.saveProxyid(casetype, oid, proxyid);
			if(ret != 0){
				response.getWriter().print("{\"result\":\"1\"}");
			}else{
				response.getWriter().print("{\"result\":\"0\"}");
			}
			
		}
	}

}
