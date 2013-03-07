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
 * Servlet implementation class XXAddUser
 */
@WebServlet("/XXAddUser")
public class XXAddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public XXAddUser() {
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

/*		String json = ParaToJson.paraToJson(request);
		System.out.println(json);
		Gson gson = new Gson();
		TuserBean ub = gson.fromJson(json, TuserBean.class);
		System.out.println(gson.toJson(ub));*/
		
		//判断添加用户（addUser）权限
		TuserBean ub = (TuserBean)request.getSession().getAttribute("user");
		if(!Power.validate(ub.getPermission(), Power.addUser)){
			response.getWriter().print("{\"result\":\"result/noPermission.jsp\"}");
			return;
		}
		String account = request.getParameter("account");
		String psw = request.getParameter("psw");
		int powerid = Integer.parseInt( request.getParameter("powerid"));
		String uname = request.getParameter("uname");
		String cellphone = request.getParameter("cellphone");
		
		TuserBean tb = new TuserBean();
		tb.setAccount(account);
		tb.setCellphone(cellphone);
		tb.setPowerid(powerid);
		tb.setPsw(psw);
		tb.setUname(uname);
		
		XXTuserDao dao = new XXTuserDao();
		boolean b = dao.checkUserName(account);
		if(b==false){
			response.getWriter().print("{\"result\":\"2\"}");
			return;
		}
		int ret=0;
		ret=dao.addUser(tb);
		if(ret==1){
			response.getWriter().print("{\"result\":\"1\"}");
		}else{
			response.getWriter().print("{\"result\":\"0\"}");
		}
	}

}
