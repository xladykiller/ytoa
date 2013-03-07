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
 * Servlet implementation class XXUpdateRegulation
 */
@WebServlet("/XXUpdateRegulation")
public class XXUpdateRegulation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public XXUpdateRegulation() {
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
		if(!Power.validate(ub.getPermission(), Power.updateRegulation)){
			response.getWriter().print(2);
			return;
		}
		String id = request.getParameter("id");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String author = request.getParameter("author");
		TregulationBean rb = new TregulationBean();
		rb.setId(Integer.parseInt(id));
		rb.setTitle(title);
		rb.setContent(content);
		rb.setAuthor(author);
		XXTregulationDao dao = new XXTregulationDao();
		int ret = 0;
		ret = dao.updateRegulation(rb);
		response.getWriter().print(ret);
	}

}
