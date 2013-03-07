package com.yitengls.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yitengls.dao.XXTpowerDao;

/**
 * Servlet implementation class XXAddPower
 */
@WebServlet("/XXAddPower")
public class XXAddPower extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public XXAddPower() {
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
		String rolename = request.getParameter("rolename");
		String permission = request.getParameter("permission");
		XXTpowerDao dao = new XXTpowerDao();
		
		boolean b = dao.checkRolename(rolename);
		if(b == false){
			response.getWriter().print("{\"result\":\"2\"}");
			return;
		}
		
		int ret = 0;
		ret = dao.addPower(rolename,permission);
		if(ret == 1){
			response.getWriter().print("{\"result\":\"1\"}");
		}else{
			response.getWriter().print("{\"result\":\"0\"}");
		}
	}

}
