package com.yitengls.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.yitengls.bean.TregulationBean;
import com.yitengls.bean.TuserBean;
import com.yitengls.dao.XXTregulationDao;
import com.yitengls.utils.Power;

/**
 * Servlet implementation class XXSearchRegulation
 */
@WebServlet("/XXSearchRegulation")
public class XXSearchRegulation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public XXSearchRegulation() {
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
		//判断修改规章制度（updateRegulation）权限
		TuserBean ub = (TuserBean)request.getSession().getAttribute("user");
		if(!Power.validate(ub.getPermission(), Power.searchRegulation)){
			response.getWriter().print("[{\"ret\":\"result/noPermission.jsp\"}]");
			return;
		}
		String title = request.getParameter("searchRegulationTitle");
		XXTregulationDao dao = new XXTregulationDao();
		List<TregulationBean> list = dao.searchRegulation(title);
		Gson gson = new Gson();
		String json = gson.toJson(list);
//		System.out.println(json);
		response.getWriter().print(json);
	}
}
