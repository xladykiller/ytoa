package com.yitengls.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.yitengls.bean.Tcase1Bean;
import com.yitengls.bean.Tcase2Bean;
import com.yitengls.db.DBmanager;

public class TcaseDao {

	public List<Tcase1Bean> getCases1ByYear(String year) {
		StringBuilder sb = new StringBuilder(
				"select * from tcase1 where year = ?");
		Connection conn = DBmanager.getconn();
		QueryRunner qr = new QueryRunner();
		List<Tcase1Bean> list = null;
		try {
			list = qr.query(conn, sb.toString(),
					new BeanListHandler<Tcase1Bean>(Tcase1Bean.class), year);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBmanager.close(conn);
		}
		return list;

	}

	public List<Tcase2Bean> getCases2ByYear(String year) {
		StringBuilder sb = new StringBuilder(
				"select * from tcase2 where year = ?");
		Connection conn = DBmanager.getconn();
		QueryRunner qr = new QueryRunner();
		List<Tcase2Bean> list = null;
		try {
			list = qr.query(conn, sb.toString(),
					new BeanListHandler<Tcase2Bean>(Tcase2Bean.class), year);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBmanager.close(conn);
		}
		return list;

	}

	public List<Tcase1Bean> getCases1(String yearSelect, String numberBtn,
			int sponsorid, int coordinatorid, String fuzzy, int pageNow,
			int pageSize) {
		StringBuilder sb = new StringBuilder(
				"SELECT * from tcase1 where (`year` like '%")
				.append(yearSelect).append("%' and proxyid like '%")
				.append(numberBtn).append("%' and sponsorid like '%")
				.append(sponsorid==0?"":sponsorid).append("%' and coordinatorid like '%")
				.append(coordinatorid==0?"":coordinatorid).append("%'").append(") and (")
				.append("`year` like '%").append(fuzzy)
				.append("%' or proxyid like '%").append(fuzzy)
				.append("%' or sponsorid like '%").append(fuzzy)
				.append("%' or coordinatorid like '%").append(fuzzy)
				.append("%'").append(") limit ?,?");
		Connection conn = DBmanager.getconn();
		QueryRunner qr = new QueryRunner();
		List<Tcase1Bean> list = null;
System.out.println(sb.toString().replace("?",String.valueOf((pageNow - 1) * pageSize )).replace("?", String.valueOf(pageSize)));
		try {
			list = qr.query(conn, sb.toString(),
					new BeanListHandler<Tcase1Bean>(Tcase1Bean.class),
					(pageNow - 1) * pageSize, pageSize);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBmanager.close(conn);
		}
		return list;
	}
	public List<Tcase1Bean> getCases1(String yearSelect, String numberBtn,
			int sponsorid, int coordinatorid, String fuzzy ) {
		StringBuilder sb = new StringBuilder(
				"SELECT * from tcase1 where (`year` like '%")
				.append(yearSelect).append("%' and proxyid like '%")
				.append(numberBtn).append("%' and sponsorid like '%")
				.append(sponsorid==0?"":sponsorid).append("%' and coordinatorid like '%")
				.append(coordinatorid==0?"":coordinatorid).append("%'").append(") and (")
				.append("`year` like '%").append(fuzzy)
				.append("%' or proxyid like '%").append(fuzzy)
				.append("%' or sponsorid like '%").append(fuzzy)
				.append("%' or coordinatorid like '%").append(fuzzy)
				.append("%'").append(") ");
		Connection conn = DBmanager.getconn();
		QueryRunner qr = new QueryRunner();
		List<Tcase1Bean> list = null;
		System.out.println(sb.toString());
		try {
			list = qr.query(conn, sb.toString(),
					new BeanListHandler<Tcase1Bean>(Tcase1Bean.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBmanager.close(conn);
		}
		return list;
	}

	
	public List<Tcase2Bean> getCases2(String yearSelect, String numberBtn,
			int sponsorid, int coordinatorid, String fuzzy, int pageNow,
			int pageSize) {
		StringBuilder sb = new StringBuilder(
				"SELECT * from tcase2 where (`year` like '%")
				.append(yearSelect).append("%' and number like '%")
				.append(numberBtn).append("%' and sponsorid like '%")
				.append(sponsorid==0?"":sponsorid).append("%' and coordinatorid like '%")
				.append(coordinatorid==0?"":coordinatorid).append("%'").append(") and (")
				.append("`year` like '%").append(fuzzy)
				.append("%' or number like '%").append(fuzzy)
				.append("%' or sponsorid like '%").append(fuzzy)
				.append("%' or coordinatorid like '%").append(fuzzy)
				.append("%'").append(") limit ?,?");
		Connection conn = DBmanager.getconn();
		QueryRunner qr = new QueryRunner();
		List<Tcase2Bean> list = null;
System.out.println(sb.toString().replace("?",String.valueOf((pageNow - 1) * pageSize )).replace("?", String.valueOf(pageSize)));
		try {
			list = qr.query(conn, sb.toString(),
					new BeanListHandler<Tcase2Bean>(Tcase2Bean.class),
					(pageNow - 1) * pageSize, pageSize);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBmanager.close(conn);
		}
		return list;
	}
	public List<Tcase2Bean> getCases2(String yearSelect, String numberBtn,
			int sponsorid, int coordinatorid, String fuzzy ) {
		StringBuilder sb = new StringBuilder(
				"SELECT * from tcase2 where (`year` like '%")
				.append(yearSelect).append("%' and number like '%")
				.append(numberBtn).append("%' and sponsorid like '%")
				.append(sponsorid==0?"":sponsorid).append("%' and coordinatorid like '%")
				.append(coordinatorid==0?"":coordinatorid).append("%'").append(") and (")
				.append("`year` like '%").append(fuzzy)
				.append("%' or number like '%").append(fuzzy)
				.append("%' or sponsorid like '%").append(fuzzy)
				.append("%' or coordinatorid like '%").append(fuzzy)
				.append("%'").append(") ");
		Connection conn = DBmanager.getconn();
		QueryRunner qr = new QueryRunner();
		List<Tcase2Bean> list = null;
		System.out.println(sb.toString());
		try {
			list = qr.query(conn, sb.toString(),
					new BeanListHandler<Tcase2Bean>(Tcase2Bean.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBmanager.close(conn);
		}
		return list;
	}
	
	public int saveProxyid(int casetype, int id, String proxy){
		StringBuilder sb  = new StringBuilder("update ");
		if(casetype==1){
			sb.append("tcase1 set proxyid=? where id=?");
		}else{
			sb.append("tcase2 set number=? where id=?");
		}
		Connection conn = DBmanager.getconn();
		QueryRunner qr = new QueryRunner();
		int ret = 0;
		try {
			ret = qr.update(conn, sb.toString(), proxy,id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
				
	}
	
}
