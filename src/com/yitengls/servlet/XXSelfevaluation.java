package com.yitengls.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.yitengls.bean.TpriseBean;
import com.yitengls.bean.TselfevaluationBean;
import com.yitengls.bean.TselfevaluserviewBean;
import com.yitengls.bean.TuserBean;
import com.yitengls.dao.XXTselfevaluationDao;

/**
 * Servlet implementation class XXSelfevaluation
 */
@WebServlet("/XXSelfevaluation")
public class XXSelfevaluation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public XXSelfevaluation() {
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
		// TODO Auto-generated method stub""
		String action = request.getParameter("action");
		
		/**
		 * 添加自我评价记录
		 */
		if(action.equals("add")){
			String stringDate = request.getParameter("evaldate");
			String result = request.getParameter("result");
			String content = request.getParameter("content");
			java.sql.Date evaldate = XXStamp.switchDate(stringDate);
			String year = XXStamp.getYear(stringDate);
			String uname =((TuserBean)request.getSession().getAttribute("user")).getUname();
			int unameid =((TuserBean)request.getSession().getAttribute("user")).getId();
			String[] params = request.getParameterValues("params[]");
			XXTselfevaluationDao dao = new XXTselfevaluationDao();
			boolean b = dao.checkEvaldate(evaldate,unameid);
			if(b==false){
				response.getWriter().print("{\"ret\":\"2\"}");
				return;
			}
			int ret = 0;
			ret = dao.addSelfevaluation(year,evaldate,uname,unameid,result,content,params);
			response.getWriter().print("{\"ret\":\""+ret+"\"}");
		}
		
		/**
		 * 更新自我评价记录
		 */
		else if(action.equals("update")){
			String stringDate = request.getParameter("evaldate");
			java.sql.Date evaldate = XXStamp.switchDate(stringDate);
			String year = XXStamp.getYear(stringDate);
			String uname = request.getParameter("uname");
			int unameid =Integer.parseInt(request.getParameter("unameid"));
			String result = request.getParameter("result");
			String content = request.getParameter("content");
			int id = Integer.parseInt(request.getParameter("id"));
			String[] params = request.getParameterValues("params[]");
			XXTselfevaluationDao dao = new XXTselfevaluationDao();
			int ret = 0;
			ret = dao.updateSelfevaluation(id,year,evaldate,uname,unameid,result,content,params);
			response.getWriter().print("{\"result\":\""+ret+"\"}");
		}
		
		/**
		 * 删除自我评价记录(id)
		 */
		else if(action.equals("del")){
			int id = Integer.parseInt(request.getParameter("id"));
			XXTselfevaluationDao dao = new XXTselfevaluationDao();
			int ret = 0;
			ret = dao.delSelfevaluation(id);
			response.getWriter().print(ret);
		}
		
		/**
		 * 删除自我评价记录(unameid)
		 */
		else if(action.equals("delByUnameid")){
			int unameid = Integer.parseInt(request.getParameter("unameid"));
			XXTselfevaluationDao dao = new XXTselfevaluationDao();
			int ret = 0;
			ret = dao.delSelfevaluationByUnameid(unameid);
			response.getWriter().print(ret);
		}
		
		/**
		 * 员工按时间段查找自己的评价记录
		 */
		else if(action.equals("search")){
			String stringDate1 = request.getParameter("startdate");
			java.sql.Date startdate = XXStamp.switchDate(stringDate1);
			String stringDate2 = request.getParameter("enddate");
			java.sql.Date enddate = XXStamp.switchDate(stringDate2);
			int unameid =((TuserBean)request.getSession().getAttribute("user")).getId();
			XXTselfevaluationDao dao = new XXTselfevaluationDao();
			List<TselfevaluationBean> list = dao.searchSelfevaluation(startdate,enddate,unameid);
			Gson gson = new Gson();
			String json = gson.toJson(list);
//			 System.out.println(json);
			response.getWriter().print(json);
		}
		
		
		/**
		 *员工按时间段分页查找自己的评价记录
		 */
		else if(action.equals("searchByPage")){
			String stringDate1 = request.getParameter("startdate");
			java.sql.Date startdate = XXStamp.switchDate(stringDate1);
			String stringDate2 = request.getParameter("enddate");
			java.sql.Date enddate = XXStamp.switchDate(stringDate2);
			int unameid =((TuserBean)request.getSession().getAttribute("user")).getId();
			int pageNow = Integer.parseInt(request.getParameter("pageNow"));
			int pageSize = Integer.parseInt(request.getParameter("pageSize"));
			XXTselfevaluationDao dao = new XXTselfevaluationDao();
			List<TselfevaluationBean> list = dao.searchSelfevaluationByPage(pageNow,pageSize,startdate,enddate,unameid);
			Gson gson = new Gson();
			String json = gson.toJson(list);
//			 System.out.println(json);
			response.getWriter().print(json);
		}
		
		/**
		 * 主任按时间段和账号查找员工的评价记录
		 */
		else if(action.equals("searchAll")){
			String stringDate1 = request.getParameter("startdate");
			java.sql.Date startdate = XXStamp.switchDate(stringDate1);
			String stringDate2 = request.getParameter("enddate");
			java.sql.Date enddate = XXStamp.switchDate(stringDate2);
			String account = request.getParameter("account");
			String uname = request.getParameter("uname");
			XXTselfevaluationDao dao = new XXTselfevaluationDao();
			List<TselfevaluserviewBean> list = dao.searchEvaluation(startdate,enddate,account,uname);
			Gson gson = new Gson();
			String json = gson.toJson(list);
//			 System.out.println(json);
			response.getWriter().print(json);
		}
		
		
		/**
		 * 主任按时间段和账号分页查找员工的评价记录
		 */
		else if(action.equals("searchAllByPage")){
			String stringDate1 = request.getParameter("startdate");
			java.sql.Date startdate = XXStamp.switchDate(stringDate1);
			String stringDate2 = request.getParameter("enddate");
			java.sql.Date enddate = XXStamp.switchDate(stringDate2);
			String account = request.getParameter("account");
			String uname = request.getParameter("uname");
			int pageNow = Integer.parseInt(request.getParameter("pageNow"));
			int pageSize = Integer.parseInt(request.getParameter("pageSize"));
			XXTselfevaluationDao dao = new XXTselfevaluationDao();
			List<TselfevaluserviewBean> list = dao.searchEvaluationByPage(pageNow,pageSize,startdate,enddate,account,uname);
			Gson gson = new Gson();
			String json = gson.toJson(list);
//			 System.out.println(json);
			response.getWriter().print(json);
		}
		
		/**
		 * 计算某个时间段某个员工奖惩
		 */
		else if(action.equals("getPrise")){
			String stringDate1 = request.getParameter("startdate");
			java.sql.Date startdate = XXStamp.switchDate(stringDate1);
			String stringDate2 = request.getParameter("enddate");
			java.sql.Date enddate = XXStamp.switchDate(stringDate2);
			int unameid =((TuserBean)request.getSession().getAttribute("user")).getId();
			XXTselfevaluationDao dao = new XXTselfevaluationDao();
			int ret = 0;
			ret = dao.getPrise(startdate,enddate,unameid);
			response.getWriter().print(ret);
		}
		
		/**
		 * 计算某个时间段多个个员工奖惩
		 */
		else if(action.equals("getAllPrise")){
			String stringDate1 = request.getParameter("startdate");
			java.sql.Date startdate = XXStamp.switchDate(stringDate1);
			String stringDate2 = request.getParameter("enddate");
			java.sql.Date enddate = XXStamp.switchDate(stringDate2);
			String account = request.getParameter("account");
			String uname = request.getParameter("uname");
			XXTselfevaluationDao dao = new XXTselfevaluationDao();
			List<TpriseBean> list = null;
			list = dao.getAllPrise(startdate,enddate,account,uname);
			Gson gson = new Gson();
			String json = gson.toJson(list);
//			System.out.println(json);
			response.getWriter().print(json);
		}
	}

}
