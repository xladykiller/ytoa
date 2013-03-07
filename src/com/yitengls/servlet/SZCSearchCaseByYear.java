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
 * Servlet implementation class SZCSearchCaseByYear
 */
@WebServlet("/SZCSearchCaseByYear")
public class SZCSearchCaseByYear extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SZCSearchCaseByYear() {
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
		// TODO Auto-generated method stub
		String year = request.getParameter("year");
		String casetype = request.getParameter("type");
		List list = null;
		TcaseDao tdao = new TcaseDao();
		if(casetype.equalsIgnoreCase("1")){
			list = tdao.getCases1ByYear(year);
		}else if(casetype.equalsIgnoreCase("2")){
			list = tdao.getCases2ByYear(year);
		}
		Gson gson = new Gson();
		
		String json = null;
		if(list != null){
			json = gson.toJson(list);
		}else{
			json = "[]";
		}
		System.out.println(json);
		response.getWriter().print(json);

	}

}
