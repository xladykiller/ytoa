package com.yitengls.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.google.gson.Gson;
import com.yitengls.bean.TuserBean;
import com.yitengls.db.DBmanager;

public class TuserDao {

	/**
	 * 登录验证
	 * @param req
	 * @param account
	 * @param psw
	 * @return
	 */
	public String checkLogin(HttpServletRequest req, String account, String psw) {
		QueryRunner qr = new QueryRunner();
		Connection conn = DBmanager.getconn();
		StringBuilder sb = new StringBuilder(
				"select * from tuserview where account = ? and psw = ?");
		TuserBean user = null;
		try {
			user = qr.query(conn, sb.toString(), new BeanHandler<TuserBean>(
					TuserBean.class), account, psw);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBmanager.close(conn);
		}
		if (user != null) {
			req.getSession().setAttribute("user", user);
			String lastReq = (String) req.getSession().getAttribute("lastReq");
			if (lastReq == null) {
				lastReq = "index.jsp";
			}
			return "{\"status\":\"ok\",\"lastReq\":\"" + lastReq + "\"}";
		} else {
			req.getSession().setAttribute("user", null);
			return "{\"status\":\"error\"}";
		}
		// TuserBean user = new TuserBean();
		// user.setId(1);
		// user.setAccount("szc");
		// user.setPsw("123");
		// user.setPowerid(1);
		// user.setUname("szc");
		// user.setCellphone("123");
		// user.setRolename("a");
		// user.setPermission(128);
		// return user;
	}
	
	public String getAlluserToJson(){
		StringBuilder sb = new StringBuilder("select id, account, powerid, uname, cellphone, rolename, permission from tuserview");
		Connection conn = DBmanager.getconn();
		QueryRunner qr = new QueryRunner();
		List<TuserBean> list = null;
		try {
			list = qr.query(conn, sb.toString(), new BeanListHandler<TuserBean>(TuserBean.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBmanager.close(conn);
		}
		String json = "[]";
		if(list != null){
			Gson gson = new Gson();
			json = gson.toJson(list);
		}else{
			json = "[]";
		}
		return json;
	}
}
