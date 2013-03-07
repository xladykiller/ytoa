package com.yitengls.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yitengls.bean.TuserBean;
import com.yitengls.dao.XXTregulationDao;
import com.yitengls.utils.Power;

/**
 * Servlet implementation class XXDelRegulation
 */
@WebServlet("/XXDelRegulation")
public class XXDelRegulation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public XXDelRegulation() {
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
		//判断删除规章制度（delRegulation）权限
		TuserBean ub = (TuserBean)request.getSession().getAttribute("user");
		if(!Power.validate(ub.getPermission(), Power.delRegulation)){
			response.getWriter().print(2);
			return;
		}
		String id = request.getParameter("id");
		XXTregulationDao dao = new XXTregulationDao();
		int ret = 0;
		ret = dao.delRegulation(id);
		response.getWriter().print(ret);
	}

}
