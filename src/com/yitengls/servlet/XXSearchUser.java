package com.yitengls.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.yitengls.bean.TuserBean;
import com.yitengls.bean.TuserviewBean;
import com.yitengls.dao.XXTuserDao;
import com.yitengls.utils.Power;

/**
 * Servlet implementation class XXSearchUser
 */
@WebServlet("/XXSearchUser")
public class XXSearchUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public XXSearchUser() {
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

		//判断查询用户（searchUser）权限
		TuserBean ub = (TuserBean)request.getSession().getAttribute("user");
		if(!Power.validate(ub.getPermission(), Power.searchUser)){
			response.getWriter().print("[{\"ret\":\"result/noPermission.jsp\"}]");
			return;
		}
		String account = request.getParameter("account");
		String uname = request.getParameter("uname");
		String cellphone = request.getParameter("cellphone");
		int powerid = Integer.parseInt(request.getParameter("powerid"));

		TuserviewBean uvb = new TuserviewBean();
		if (!account.isEmpty())
			uvb.setAccount(account);
		if (!uname.isEmpty())
			uvb.setUname(uname);
		if (!cellphone.isEmpty())
			uvb.setCellphone(cellphone);
		uvb.setPowerid(powerid);

		XXTuserDao dao = new XXTuserDao();
		List<TuserviewBean> list = dao.searchUser(uvb);
		Gson gson = new Gson();
		String json = gson.toJson(list);
		response.getWriter().print(json);
		/*if (ls != null) {
			StringBuilder sb = new StringBuilder("{\"users\":[");
			boolean first = true;
			for (TuserviewBean bean : ls) {
				if (first) {
					first = false;
				} else {
					sb.append(",");
				}
				sb.append("{\"account\":\"").append(bean.getAccount())
						.append("\",").append("\"uname\":\"")
						.append(bean.getUname()).append("\",")
						.append("\"cellphone\":\"").append(bean.getCellphone())
						.append("\",").append("\"powerid\":\"")
						.append(bean.getPowerid()).append("\",")
						.append("\"rolename\":\"").append(bean.getRolename()).append("\",")
						.append("\"pwd\":\"").append(bean.getPsw())
						.append("\"}");
			}
			sb.append("]}");
			response.getWriter().println(sb.toString());
		} else {
			response.getWriter().print("{\"users\":[]}");
		}*/

	}
}
