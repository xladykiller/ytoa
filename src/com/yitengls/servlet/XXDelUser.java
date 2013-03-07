package com.yitengls.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yitengls.bean.TuserBean;
import com.yitengls.dao.XXTuserDao;
import com.yitengls.utils.Power;

/**
 * Servlet implementation class XXDelUser
 */
@WebServlet("/XXDelUser")
public class XXDelUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public XXDelUser() {
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
		
		//判断删除用户（delUser）权限
		TuserBean ub = (TuserBean)request.getSession().getAttribute("user");
		if(!Power.validate(ub.getPermission(), Power.delUser)){
			response.getWriter().print(2);
			return;
		}
		int id = Integer.parseInt(request.getParameter("id"));
		XXTuserDao dao = new XXTuserDao();
		int ret = dao.delUser(id);
		response.getWriter().print(ret);
	}

}
