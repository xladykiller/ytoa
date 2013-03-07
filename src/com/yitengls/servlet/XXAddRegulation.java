package com.yitengls.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yitengls.bean.TregulationBean;
import com.yitengls.bean.TuserBean;
import com.yitengls.dao.XXTregulationDao;
import com.yitengls.utils.Power;

/**
 * Servlet implementation class XXAddRegulation
 */
@WebServlet("/XXAddRegulation")
public class XXAddRegulation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public XXAddRegulation() {
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
		//判断添加规章制度（addRegulation）权限
		TuserBean ub = (TuserBean)request.getSession().getAttribute("user");
		if(!Power.validate(ub.getPermission(), Power.addRegulation)){
			response.getWriter().print("{\"ret\":\"noPermission\"}");
			return;
		}
		
		String title = request.getParameter("regulationTitle");
		String author = request.getParameter("regulationAuthor");
		String content = request.getParameter("regulationContent");		
		
		XXTregulationDao dao = new XXTregulationDao();
		TregulationBean rb = new TregulationBean();
		rb.setTitle(title);
		rb.setAuthor(author);
		rb.setContent(content);
		
	/*	boolean b = dao.checkRegulationTitle(title);
		if(b == false){
			response.getWriter().print("{\"result\":\"2\"}");
			return;
		}*/
		
		int ret = 0;
		ret = dao.addRegulation(rb);
		if(ret == 1){
			response.getWriter().print("{\"ret\":\"1\"}");
		}else{
			response.getWriter().print("{\"ret\":\"0\"}");
		}
//		response.sendRedirect("result/success.jsp");
/*		if(ret == 1){
			request.getRequestDispatcher("result/success.jsp").forward(request,response);
		}
		else{
			request.getRequestDispatcher("result/error.jsp").forward(request,response);
		}*/
		
	}

}
