package com.yitengls.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.yitengls.bean.TexampleBean;
import com.yitengls.bean.TuserBean;
import com.yitengls.dao.XXTexampleDao;
import com.yitengls.utils.Power;

/**
 * Servlet implementation class XXExample
 */
@WebServlet("/XXExample")
public class XXExample extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public XXExample() {
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
		String action = request.getParameter("action");

		//根据title查询典型案例-----------------------------------------------------------------------------------
		if (action.equals("search")) {
			//判断查询案例（searchExample）权限
			TuserBean ub = (TuserBean)request.getSession().getAttribute("user");
			if(!Power.validate(ub.getPermission(), Power.searchExample)){
				response.getWriter().print("[{\"ret\":\"noPermission\"}]");
				return;
			}
			String title = request.getParameter("searchExampleTitle");
			XXTexampleDao dao = new XXTexampleDao();
			List<TexampleBean> list = dao.searchExample(title);
			Gson gson = new Gson();
			String json = gson.toJson(list);
			// System.out.println(json);
			response.getWriter().print(json);
		}

		//根据title，pageNow，pageSize分页查询典型案例-----------------------------------------------------------------------------------
		else if (action.equals("searchByPage")) {
			//判断查询案例（searchExample）权限
			TuserBean ub = (TuserBean)request.getSession().getAttribute("user");
			if(!Power.validate(ub.getPermission(), Power.searchExample)){
				response.getWriter().print("[{\"ret\":\"noPermission\"}]");
				return;
			}
			int pageNow = Integer.parseInt(request.getParameter("pageNow"));
			int pageSize = Integer.parseInt(request.getParameter("pageSize"));
			String title = request.getParameter("searchExampleTitle");
			XXTexampleDao dao = new XXTexampleDao();
			List<TexampleBean> list = null;
			list = dao.getExampleByPage(pageNow, pageSize, title);
			Gson gson = new Gson();
			String json = gson.toJson(list);
			// System.out.print(json);
			response.getWriter().print(json);
		}

		//根据id删除典型案例-----------------------------------------------------------------------------------
		else if (action.equals("del")) {
			//判断删除案例（delExample）权限
			TuserBean ub = (TuserBean)request.getSession().getAttribute("user");
			if(!Power.validate(ub.getPermission(), Power.delExample)){
				response.getWriter().print(2);		
				return;
			}
			String id = request.getParameter("id");
			XXTexampleDao dao = new XXTexampleDao();
			int ret = 0;
			ret = dao.delExample(id);
			response.getWriter().print(ret);
		}

		//添加典型案例-----------------------------------------------------------------------------------
		else if (action.equals("add")) {
			//判断添加案例（addExample）权限
			TuserBean ub = (TuserBean)request.getSession().getAttribute("user");
			if(!Power.validate(ub.getPermission(), Power.addExample)){
				response.getWriter().print("{\"ret\":\"noPermission\"}");		
				return;
			}
			String title = request.getParameter("exampleTitle");
			String content = request.getParameter("exampleContent");
			String author = request.getParameter("exampleAuthor");

			XXTexampleDao dao = new XXTexampleDao();
			TexampleBean eb = new TexampleBean();
			eb.setTitle(title);
			eb.setAuthor(author);
			eb.setContent(content);

			int ret = 0;
			ret = dao.addExample(eb);
			if (ret == 1) {
				 response.getWriter().print("{\"ret\":\"1\"}");
//				response.getWriter().print("添加成功！");
			} else {
				 response.getWriter().print("{\"ret\":\"0\"}");
//				response.getWriter().print("添加失败！");
			}
		}

		
		//更新典型案例-----------------------------------------------------------------------------------
		else if (action.equals("update")) {
			//判断修改案例（updateExample）权限
			TuserBean ub = (TuserBean)request.getSession().getAttribute("user");
			if(!Power.validate(ub.getPermission(), Power.updateExample)){
				response.getWriter().print(2);		
				return;
			}
			String id = request.getParameter("id");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String author = request.getParameter("author");
			TexampleBean eb = new TexampleBean();
			eb.setId(Integer.parseInt(id));
			eb.setTitle(title);
			eb.setContent(content);
			eb.setAuthor(author);
			XXTexampleDao dao = new XXTexampleDao();
			int ret = 0;
			ret = dao.updateExample(eb);
			response.getWriter().print(ret);
		}
	}

}
