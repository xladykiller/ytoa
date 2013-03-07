package com.yitengls.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.Gson;
import com.yitengls.bean.TinfoBean;
import com.yitengls.bean.TuserBean;
import com.yitengls.dao.XXTinfoDao;
import com.yitengls.utils.Power;

/**
 * Servlet implementation class XXInfo
 */
@MultipartConfig
@WebServlet("/XXInfo")
public class XXInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public XXInfo() {
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
		String action = request.getParameter("action");
		
		//根据title查询通知------------------------------------------------------------
		if (action.equals("search")) {
			//判断查询通知（searchInfo）权限
			TuserBean ub = (TuserBean)request.getSession().getAttribute("user");
			if(!Power.validate(ub.getPermission(), Power.searchInfo)){
				response.getWriter().print("[{\"ret\":\"noPermission\"}]");
				return;
			}
			String title = request.getParameter("searchInfoTitle");
			XXTinfoDao dao = new XXTinfoDao();
			List<TinfoBean> list = dao.searchInfo(title);
			Gson gson = new Gson();
			String json = gson.toJson(list);
			// System.out.println(json);
			response.getWriter().print(json);
		}

		//根据title，pageNow，pageSize分页查询通知------------------------------------------------------------
		else if (action.equals("searchByPage")) {
			//判断查询通知（searchInfo）权限
			TuserBean ub = (TuserBean)request.getSession().getAttribute("user");
			if(!Power.validate(ub.getPermission(), Power.searchInfo)){
				response.getWriter().print("[{\"ret\":\"noPermission\"}]");
				return;
			}
			int pageNow = Integer.parseInt(request.getParameter("pageNow"));
			int pageSize = Integer.parseInt(request.getParameter("pageSize"));
			String title = request.getParameter("searchInfoTitle");
			XXTinfoDao dao = new XXTinfoDao();
			List<TinfoBean> list = null;
			list = dao.getInfoByPage(pageNow, pageSize, title);
			Gson gson = new Gson();
			String json = gson.toJson(list);
			// System.out.print(json);
			response.getWriter().print(json);
		}

		//根据id删除通知------------------------------------------------------------
		else if (action.equals("del")) {
			//判断删除通知（delInfo）权限
			TuserBean ub = (TuserBean)request.getSession().getAttribute("user");
			if(!Power.validate(ub.getPermission(), Power.delInfo)){
				response.getWriter().print(2);
				return;
			}
			String id = request.getParameter("id");
			XXTinfoDao dao = new XXTinfoDao();
			int ret = 0;
			ret = dao.delInfo(id);
			response.getWriter().print(ret);
		}

		//添加通知------------------------------------------------------------
		else if (action.equals("add")) {
			//判断添加通知（addInfo）权限
			TuserBean ub = (TuserBean)request.getSession().getAttribute("user");
			if(!Power.validate(ub.getPermission(), Power.addInfo)){
				response.sendRedirect("result/noPermission.jsp");
				return;
			}

			String title = request.getParameter("infoTitle");
			String author = request.getParameter("infoAuthor");
			String content = request.getParameter("infoContent");
			String attachment = "";

			Part part = request.getPart("attachment");
			String filename = getFilename(part);
			// System.out.println(filename);
			if (!filename.isEmpty()) {
				String name1 = String.valueOf(System.currentTimeMillis());
				String name2 = XXInfo.getExtensionName(filename);
				attachment = name1 + "." + name2;
				String path = request.getServletContext().getRealPath("/upload");
				part.write(path + File.separator +attachment);
//				System.out.println(path + File.separator +attachment);
			}

			XXTinfoDao dao = new XXTinfoDao();
			TinfoBean ib = new TinfoBean();
			ib.setTitle(title);
			ib.setAuthor(author);
			ib.setContent(content);
			if(attachment == ""){
				//如果没有附件，数据库中attachment字段值为空串
				ib.setAttachment("");
			}else{				
				ib.setAttachment(request.getContextPath()+"/upload/"+attachment);
			}

			int ret = 0;
			ret = dao.addInfo(ib);
			if (ret == 1) {
				// response.getWriter().print("{\"result\":\"1\"}");
				response.getWriter().print("添加成功！");
			} else {
				// response.getWriter().print("{\"result\":\"0\"}");
				response.getWriter().print("添加失败！");
			}
		}

		/*//根据id得到附件字段（存储位置）------------------------------------------------------------
		else if(action.equals("download")){
			//判断下载附件（downloadInfo）权限
			TuserBean ub = (TuserBean)request.getSession().getAttribute("user");
			if(!Power.validate(ub.getPermission(), Power.downloadInfo)){
				response.sendRedirect("result/noPermission.jsp");
				return;
			}
			String id = request.getParameter("id");
			XXTinfoDao dao = new XXTinfoDao();
			TinfoBean ib = null;
			ib = dao.getInfoById(id);
			String attachment = ib.getAttachment();
			response.getWriter().print(attachment);
		}
		*/
		//更新通知------------------------------------------------------------
		else if (action.equals("update")) {
			//判断修改通知（updateInfo）权限
			TuserBean ub = (TuserBean)request.getSession().getAttribute("user");
			if(!Power.validate(ub.getPermission(), Power.updateInfo)){
				response.getWriter().print(2);
				return;
			}
			String id = request.getParameter("id");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String author = request.getParameter("author");
			TinfoBean ib = new TinfoBean();
			ib.setId(Integer.parseInt(id));
			ib.setTitle(title);
			ib.setContent(content);
			ib.setAuthor(author);
			XXTinfoDao dao = new XXTinfoDao();
			int ret = 0;
			ret = dao.updateInfo(ib);
			response.getWriter().print(ret);
		}
	}

	/**
	 * 取得文件名
	 * @param part
	 * @return
	 */
	private String getFilename(Part part) {
		String header = part.getHeader("Content-Disposition");
		String filename = header.substring(header.indexOf("filename=\"") + 10,
				header.lastIndexOf("\""));
		return filename;
	}

	/**
	 * 取得文件名后缀
	 * @param filename
	 * @return
	 */
	public static String getExtensionName(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length() - 1))) {
				return filename.substring(dot + 1);
			}
		}
		return filename;
	}

}
