package com.yitengls.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.yitengls.bean.TstampBean;
import com.yitengls.bean.TstampuserviewBean;
import com.yitengls.bean.TuserBean;
import com.yitengls.bean.TuserviewBean;
import com.yitengls.dao.XXTstampDao;
import com.yitengls.dao.XXTuserDao;
import com.yitengls.utils.Power;

/**
 * Servlet implementation class XXStamp
 */
@WebServlet("/XXStamp")
public class XXStamp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public XXStamp() {
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
		// 模糊查询公章使用记录--------------------------------------------------------------------
		if (action.equals("search")) {
			//判断查询公章使用（searchStamp）权限
			TuserBean ub = (TuserBean)request.getSession().getAttribute("user");
			if(!Power.validate(ub.getPermission(), Power.searchStamp)){
				response.getWriter().print("[{\"ret\":\"noPermission\"}]");			
		        return;
		    }
			String year = request.getParameter("searchYear");
			String petitioner = request.getParameter("searchPetitioner");
			String uname = request.getParameter("searchUname");
			String usereason = request.getParameter("searchUseReason");
			XXTstampDao dao = new XXTstampDao();
			List<TstampuserviewBean> list = dao.searchStamp(year, petitioner,
					uname, usereason);
			Gson gson = new Gson();
			String json = gson.toJson(list);
			// System.out.println(json);
			response.getWriter().print(json);
		}

		// 分页查询公章使用记录--------------------------------------------------------------------
		else if (action.equals("searchByPage")) {
			int pageNow = Integer.parseInt(request.getParameter("pageNow"));
			int pageSize = Integer.parseInt(request.getParameter("pageSize"));
			String year = request.getParameter("searchYear");
			String petitioner = request.getParameter("searchPetitioner");
			String uname = request.getParameter("searchUname");
			String usereason = request.getParameter("searchUseReason");
			XXTstampDao dao = new XXTstampDao();
			List<TstampuserviewBean> list = dao.getStampByPage(pageNow,
					pageSize, year, petitioner, uname, usereason);
			Gson gson = new Gson();
			String json = gson.toJson(list);
			// System.out.print(json);
			response.getWriter().print(json);
		}

		// 根据id删除公章使用记录--------------------------------------------------------------------
		else if (action.equals("del")) {
			//判断删除公章使用（delStamp）权限
			TuserBean ub = (TuserBean)request.getSession().getAttribute("user");
			if(!Power.validate(ub.getPermission(), Power.delStamp)){
				response.getWriter().print(2);			
		        return;
		    }
			String id = request.getParameter("id");
			XXTstampDao dao = new XXTstampDao();
			int ret = 0;
			ret = dao.delStamp(id);
			response.getWriter().print(ret);
		}

		// 添加公章使用记录--------------------------------------------------------------------
		else if (action.equals("add")) {
			//判断添加公章使用（addStamp）权限
			TuserBean ub = (TuserBean)request.getSession().getAttribute("user");
			if(!Power.validate(ub.getPermission(), Power.addStamp)){
				response.getWriter().print("{\"ret\":\"noPermission\"}");			
		        return;
		    }
			// 检查使用者账户和密码
			String petitioner = request.getParameter("petitioner");
			String psw = request.getParameter("psw");
			XXTuserDao userdao = new XXTuserDao();
			boolean b = false;
			b = userdao.checkPsw(petitioner, psw);
			if (!b) {
				response.getWriter().print("{\"ret\":\"2\"}");
				return;
			}

			// 得到使用者id
			TuserviewBean uvb = new TuserviewBean();
			uvb = userdao.getUserByAccount(petitioner);
			int petitionerid = uvb.getId();
			//得到年份，转换时间格式
			String stringDate = request.getParameter("useDate");
			java.sql.Date usedate = XXStamp.switchDate(stringDate);
			String year = XXStamp.getYear(stringDate);
			/*// 得到年份
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			java.sql.Date usedate = null;
			try {
				java.util.Date date = sdf
						.parse(request.getParameter("useDate"));
				usedate = new java.sql.Date(date.getTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			sdf.applyPattern("yyyy");
			String year = sdf.format(usedate);*/

			// 读取参数
			int filenum = Integer.parseInt(request.getParameter("fileNum"));
			String usereason = request.getParameter("useReason");
			String memo = request.getParameter("memo");
			// 设置bean
			TstampBean sb = new TstampBean();
			sb.setYear(year);
			sb.setUsedate(usedate);
			sb.setPetitioner(petitioner);
			sb.setPetitionerid(petitionerid);
			sb.setFilenum(filenum);
			sb.setUsereason(usereason);
			sb.setMemo(memo);
			// 添加记录
			XXTstampDao dao = new XXTstampDao();
			int ret = 0;
			ret = dao.addStamp(sb);
			if (ret == 1) {
				response.getWriter().print("{\"ret\":\"1\"}");
			} else {
				response.getWriter().print("{\"ret\":\"0\"}");
			}
		}

		
		 //更新典型案例 --------------------------------------------------------------------
		else if (action.equals("update")) { 
			//判断修改公章使用（updateStamp）权限
			TuserBean ub = (TuserBean)request.getSession().getAttribute("user");
			if(!Power.validate(ub.getPermission(), Power.updateStamp)){
				response.getWriter().print(2);			
		        return;
		    }
			//读取参数
			int id =Integer.parseInt(request.getParameter("id")); 
			int filenum =Integer.parseInt(request.getParameter("updateFileNum")); 
			String usereason =  request.getParameter("updateUseReason"); 
			String memo =	  request.getParameter("updateMemo"); 
			
			//得到年份，转换时间格式
			String stringDate = request.getParameter("updateUseDate");
			java.sql.Date usedate = XXStamp.switchDate(stringDate);
			String year = XXStamp.getYear(stringDate);
			
			TstampBean sb = new TstampBean();
		    sb.setId(id); 
		    sb.setYear(year);
		    sb.setUsedate(usedate);
		    sb.setFilenum(filenum);
		    sb.setUsereason(usereason);
		    sb.setMemo(memo);
		    XXTstampDao dao = new	XXTstampDao(); 
		    int ret = 0; 
		    ret = dao.updateStamp(sb);
		    response.getWriter().print(ret); 
		    } 
		}
		 
	// 得到年份--------------------------------------------------------------------
	public static String getYear(String stringDate){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.sql.Date sqlDate = XXStamp.switchDate(stringDate);
		sdf.applyPattern("yyyy");
		String year = sdf.format(sqlDate);
		return year;
	}
	
	//转换String Date为sql Date--------------------------------------------------------------------
	public static java.sql.Date switchDate(String stringDate){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.sql.Date sqlDate = null;
		try {
			java.util.Date utilDate = sdf.parse(stringDate);
			sqlDate = new java.sql.Date(utilDate.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return sqlDate;
	}
	
	
}
