package com.yitengls.servlet;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yitengls.bean.TpowerBean;
import com.yitengls.dao.XXTpowerDao;

/**
 * Servlet implementation class xxReadRole
 */
@WebServlet("/xxReadRole")
public class XXReadRole extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public XXReadRole() {
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
		// TuserBean user =
		// (TuserBean)request.getSession().getAttribute("user");
		List<TpowerBean> list = null;
		XXTpowerDao powerdao = new XXTpowerDao();
		list = powerdao.readRole();
		if (list != null) {
			StringBuilder sb = new StringBuilder("{\"result\":[");
			Iterator<TpowerBean> it = list.iterator();
			int i = 1;
			while (it.hasNext()) {
				TpowerBean pb = it.next();
				if(i != 1){
					sb.append(",");
				}else{
					i=2;
				}
				sb.append("{\"id\":\"").append(pb.getId())
				.append("\",\"rolename\":\"").append(pb.getRolename()).append("\"}");
			}
			sb.append("]}");
			response.getWriter().print(sb.toString());

		}else{
			response.getWriter().print("{\"result\":[]}");
		}
	}

}
