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
 * Servlet implementation class XXUpdatePsw
 */
@WebServlet("/XXUpdatePsw")
public class XXUpdatePsw extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public XXUpdatePsw() {
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
		//判断修改密码权限（updatePsw）
		TuserBean ub = (TuserBean)request.getSession().getAttribute("user");
		if(!Power.validate(ub.getPermission(), Power.updatePsw)){
			response.getWriter().print("{\"status\":\"noPermission\"}");
		
	return;
}
		String oldpsw = request.getParameter("oldpsw");
		String newpsw = request.getParameter("newpsw");
		TuserBean user = (TuserBean)request.getSession().getAttribute("user");
		String account = user.getAccount();
		
		XXTuserDao dao = new XXTuserDao();
		boolean b = false;
		b = dao.checkPsw(account, oldpsw);
		if(b == false){
			response.getWriter().print("{\"status\":\"falseOldpsw\"}");
			return;
		}
		
		int ret = 0;
		ret = dao.updatePsw(account, newpsw);
		if(ret == 0){
			response.getWriter().print("{\"status\":\"failedNewpsw\"}");
			return;
		}
		
		if(b==true && ret==1){
			response.getWriter().print("{\"status\":\"success\"}");
		}
	}

}
