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
 * Servlet implementation class XXUpdateUser
 */
@WebServlet("/XXUpdateUser")
public class XXUpdateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public XXUpdateUser() {
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
		
		//判断修改用户（updateUser）权限
		TuserBean ub = (TuserBean)request.getSession().getAttribute("user");
		if(!Power.validate(ub.getPermission(), Power.updateUser)){
			response.getWriter().print(2);
			return;
		}
		
		int id = Integer.parseInt(request.getParameter("id"));
		String account = request.getParameter("account");
		String uname = request.getParameter("uname");
		String cellphone = request.getParameter("cellphone");
		int powerid = Integer.parseInt( request.getParameter("powerid"));
		String psw = request.getParameter("psw");
		
		TuserBean tb = new TuserBean();
		tb.setId(id);
		tb.setAccount(account);
		tb.setUname(uname);
		tb.setCellphone(cellphone);
		tb.setPowerid(powerid);
		tb.setPsw(psw);
		
		XXTuserDao dao = new XXTuserDao();
		int ret=0;
		ret=dao.updateUser(tb);
		response.getWriter().print(ret);
	}

}
