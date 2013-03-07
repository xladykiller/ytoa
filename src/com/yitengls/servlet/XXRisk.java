package com.yitengls.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.yitengls.bean.Tcase1Bean;
import com.yitengls.bean.TriskBean;
import com.yitengls.bean.TuserBean;
import com.yitengls.dao.XXTriskDao;
import com.yitengls.utils.Power;

/**
 * Servlet implementation class XXRisk
 */
@WebServlet("/XXRisk")
public class XXRisk extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public XXRisk() {
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
		
		/**
		 * 添加风险记录----------------------------------------------------------------------------------------------------------------
		 */
		if(action.equals("add")){
			//判断添加风险（addRisk）权限
			TuserBean ub = (TuserBean)request.getSession().getAttribute("user");
			if(!Power.validate(ub.getPermission(), Power.addRisk)){
				response.getWriter().print("{\"ret\":\"noPermission\"}");
			    return;
			}
			// 检查代理案号是否正确
			String proxyid = request.getParameter("proxyid");
			XXTriskDao dao = new XXTriskDao();
			boolean b = false;
			b = dao.checkProxyid(proxyid);
			if (!b) {
				response.getWriter().print("{\"ret\":\"2\"}");
				return;
			}

			//得到年份，转换时间格式
			String stringDate = request.getParameter("complainDate");
			java.sql.Date complaindate = XXStamp.switchDate(stringDate);
			String year = XXStamp.getYear(stringDate);

			// 读取参数
			String complainant = request.getParameter("complainant");
			String complainway = request.getParameter("complainWay");
			String phone = request.getParameter("phone");
			String undertaker = request.getParameter("undertaker");
			String content = request.getParameter("content");
			String result = request.getParameter("result");
			// 设置bean
			TriskBean rb = new TriskBean();
			rb.setYear(year);
			rb.setComplaindate(complaindate);
			rb.setComplainant(complainant);
			rb.setComplainway(complainway);
			rb.setPhone(phone);
			rb.setProxyid(proxyid);
			rb.setUndertaker(undertaker);
			rb.setContent(content);
			rb.setResult(result);
			// 添加记录
			int ret = 0;
			ret = dao.addRisk(rb);
			if (ret == 1) {
				response.getWriter().print("{\"ret\":\"1\"}");
			} else {
				response.getWriter().print("{\"ret\":\"0\"}");
			}		
		}
		
		/**
		 * 模糊查询风险记录----------------------------------------------------------------------------------------------------------------
		 */
		else if(action.equals("search")){
			//判断查询风险（searchRisk）权限
			TuserBean ub = (TuserBean)request.getSession().getAttribute("user");
			if(!Power.validate(ub.getPermission(), Power.searchRisk)){
				response.getWriter().print("[{\"ret\":\"noPermission\"}]");
			    return;
			}
			String year = request.getParameter("searchYear");
			String complainant = request.getParameter("searchComplainant");
			String proxyid = request.getParameter("searchProxyid");
			String undertaker = request.getParameter("searchUndertaker");
			XXTriskDao dao = new XXTriskDao();
			List<TriskBean> list = dao.searchRisk(year, complainant,	proxyid, undertaker);
			Gson gson = new Gson();
			String json = gson.toJson(list);
//			System.out.println(json);
			response.getWriter().print(json);
		}
		
	
		/**
		 * 分页查询风险记录----------------------------------------------------------------------------------------------------------------
		 */
		else if (action.equals("searchByPage")) {
			//判断查询风险（searchRisk）权限
			TuserBean ub = (TuserBean)request.getSession().getAttribute("user");
			if(!Power.validate(ub.getPermission(), Power.searchRisk)){
				response.getWriter().print("[{\"ret\":\"noPermission\"}]");
			    return;
			}
			int pageNow = Integer.parseInt(request.getParameter("pageNow"));
			int pageSize = Integer.parseInt(request.getParameter("pageSize"));
			String year = request.getParameter("searchYear");
			String complainant = request.getParameter("searchComplainant");
			String proxyid = request.getParameter("searchProxyid");
			String undertaker = request.getParameter("searchUndertaker");
			XXTriskDao dao = new XXTriskDao();
			List<TriskBean> list = dao.getRiskByPage(pageNow,
					pageSize, year, complainant,	proxyid, undertaker);
			Gson gson = new Gson();
			String json = gson.toJson(list);
			response.getWriter().print(json);
		}
		
		/**
		 * 删除风险记录----------------------------------------------------------------------------------------------------------------
		 */
		else if(action.equals("del")){
			//判断删除风险（delRisk）权限
			TuserBean ub = (TuserBean)request.getSession().getAttribute("user");
			if(!Power.validate(ub.getPermission(), Power.delRisk)){
				response.getWriter().print(2);
			    return;
			}
			String id = request.getParameter("id");
			XXTriskDao dao = new XXTriskDao();
			int ret = 0;
			ret = dao.delRisk(id);
			response.getWriter().print(ret);
		}
		
		
		/**
		 * 更新风险记录----------------------------------------------------------------------------------------------------------------
		 */
		else if (action.equals("update")) { 
			//判断更新风险（updateRisk）权限
			TuserBean ub = (TuserBean)request.getSession().getAttribute("user");
			if(!Power.validate(ub.getPermission(), Power.updateRisk)){
				response.getWriter().print(2);
			    return;
			}
			//读取参数
			int id =Integer.parseInt(request.getParameter("id")); 
			String complainant =  request.getParameter("updateComplainant"); 
			String complainway =	  request.getParameter("updateComplainWay"); 
			String phone =  request.getParameter("updatePhone"); 
			String undertaker =	  request.getParameter("updateUndertaker"); 
			String content =  request.getParameter("updateContent"); 
			String result =	  request.getParameter("updateResult"); 
			
			//得到年份，转换时间格式
			String stringDate = request.getParameter("updateComplainDate");
			java.sql.Date complaindate = XXStamp.switchDate(stringDate);
			String year = XXStamp.getYear(stringDate);
			
			TriskBean rb = new TriskBean();
		    rb.setId(id);
		    rb.setYear(year);
		    rb.setComplaindate(complaindate);
		    rb.setComplainant(complainant);
		    rb.setComplainway(complainway);
		    rb.setPhone(phone);
		    rb.setUndertaker(undertaker);
		    rb.setContent(content);
		    rb.setResult(result);
		    
		    XXTriskDao dao = new	XXTriskDao(); 
		    int ret = 0; 
		    ret = dao.updateRisk(rb);
		    response.getWriter().print(ret); 
		} 
		
		/**
		 * 模糊查询得到tcase1表的proxyid---------------------------------------------------------------------------------------------------
		 */
		else if(action.equals("getProxyidList")){
			String proxyidText = request.getParameter("proxyidText");
			 XXTriskDao dao = new	XXTriskDao();
			 List<Tcase1Bean> list = dao.getProxyidList(proxyidText);
			 Gson gson = new Gson();
			 String json = gson.toJson(list);
			 response.getWriter().print(json);
		}
	}
	
	
	

}
