package com.yitengls.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.yitengls.bean.TuserBean;
import com.yitengls.bean.TuserviewBean;
import com.yitengls.db.DBmanager;

public class XXTuserDao {

	/**
	 * 添加一个用户
	 * 
	 * @param tb
	 * @return int
	 */
	public int addUser(TuserBean tb) {
		Connection conn = DBmanager.getconn();
		StringBuilder sb = new StringBuilder(
				"insert into tuser values(null,?,?,?,?,?)");
		QueryRunner qr = new QueryRunner();
		int ret = 0;
		try {
			ret = qr.update(conn, sb.toString(), tb.getAccount(), tb.getPsw(),
					tb.getPowerid(), tb.getUname(), tb.getCellphone());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBmanager.close(conn);
		}
		return ret;
	}

	/**
	 * 检查用户名是否重复，若不重复返回true
	 * 
	 * @param account
	 * @return boolean
	 */
	public boolean checkUserName(String account) {
		boolean b = false;
		Connection conn = DBmanager.getconn();
		StringBuilder sb = new StringBuilder(
				"select * from tuserview where account=?");
		QueryRunner qr = new QueryRunner();
		TuserBean ub = null;
		try {
			ub = qr.query(conn, sb.toString(), new BeanHandler<TuserBean>(
					TuserBean.class), account);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBmanager.close(conn);
		}

		if (ub == null) {
			b = true;// 用户名不重复，可以添加
		}
		return b;
	}

	/**
	 * 根据用户名得到用户记录
	 * @param account
	 * @return
	 */
	public TuserviewBean getUserByAccount(String account) {
		Connection conn = DBmanager.getconn();
		StringBuilder sb = new StringBuilder(
				"select id,account,psw,uname,cellphone,rolename  from tuserview where account=?");
		QueryRunner qr = new QueryRunner();
		TuserviewBean uvb = null;
		try {
			uvb = qr.query(conn, sb.toString(), new BeanHandler<TuserviewBean>(
					TuserviewBean.class), account);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBmanager.close(conn);
		}
		return uvb;
	}

	/**
	 * 模糊查询用户
	 * 
	 * @param uvb
	 * @return
	 */
	public List<TuserviewBean> searchUser(TuserviewBean uvb) {
		StringBuilder sb = new StringBuilder(
				"select * from tuserview where 1=1 ");
		if (null != uvb.getAccount())
			sb.append(" and account like '%").append(uvb.getAccount())
					.append("%'");
		if (null != uvb.getUname())
			sb.append(" and uname like '%").append(uvb.getUname()).append("%'");
		if (null != uvb.getCellphone())
			sb.append(" and cellphone like '%").append(uvb.getCellphone())
					.append("%'");
		if (0 != uvb.getPowerid())
			sb.append(" and powerid =").append(uvb.getPowerid());
		sb.append(" order by account");
		Connection conn = DBmanager.getconn();
		QueryRunner qr = new QueryRunner();
		List<TuserviewBean> ls = null;
		try {
			ls = qr.query(conn, sb.toString(),
					new BeanListHandler<TuserviewBean>(TuserviewBean.class));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBmanager.close(conn);
		}
		return ls;
	}

	/**
	 * 分页查询用户
	 * 
	 * @param pageNow
	 * @param pageSize
	 * @param title
	 * @return
	 */
	public List<TuserviewBean> getUserByPage(int pageNow, int pageSize,
			TuserviewBean uvb) {
		/*
		 * StringBuilder sb = null; String account = uvb.getAccount(); String
		 * uname = uvb.getUname(); String cellphone = uvb.getCellphone(); int
		 * powerid = uvb.getPowerid();
		 */
		// sb = new
		// StringBuilder("select * from tinfo where title like '%").append(title).append("%' ORDER BY addtime DESC, id DESC LIMIT ?,?");
		StringBuilder sb = new StringBuilder(
				"select * from tuserview where 1=1 ");
		if (null != uvb.getAccount())
			sb.append(" and account like '%").append(uvb.getAccount())
					.append("%'");
		if (null != uvb.getUname())
			sb.append(" and uname like '%").append(uvb.getUname()).append("%'");
		if (null != uvb.getCellphone())
			sb.append(" and cellphone like '%").append(uvb.getCellphone())
					.append("%'");
		if (0 != uvb.getPowerid())
			sb.append(" and powerid =").append(uvb.getPowerid());
		sb.append(" order by account limit ?,?");
		/*
		 * sb = new
		 * StringBuilder("SELECT * from tuserview where account like '%")
		 * .append(account).append("%' and uname like '%")
		 * .append(uname).append("%' and cellphone like '%")
		 * .append(cellphone).append("%' and powerid =").append(powerid)
		 * .append(" limit ?,?");
		 */
		Connection conn = DBmanager.getconn();
		QueryRunner qr = new QueryRunner();
		List<TuserviewBean> ls = null;
		try {
			ls = qr.query(conn, sb.toString(),
					new BeanListHandler<TuserviewBean>(TuserviewBean.class),
					pageSize * (pageNow - 1), pageSize);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBmanager.close(conn);
		}
		return ls;
	}

	/**
	 * 删除用户
	 * 
	 * @param id
	 * @return 影响条数
	 */
	public int delUser(int id) {
		Connection conn = DBmanager.getconn();
		StringBuilder sb = new StringBuilder("DELETE FROM tuser where id=? ");
		QueryRunner qr = new QueryRunner();
		int ret = 0;
		try {
			ret = qr.update(conn, sb.toString(), id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBmanager.close(conn);
		}
		return ret;
	}

	/**
	 * 修改用户
	 * 
	 * @param tb
	 * @return 影响条数
	 */
	public int updateUser(TuserBean tb) {
		int id = tb.getId();
		// String account = tb.getAccount();
		String uname = tb.getUname();
		String cellphone = tb.getCellphone();
		int powerid = tb.getPowerid();
		String psw = tb.getPsw();

		Connection conn = DBmanager.getconn();
		if (psw.equals("")) {
			StringBuilder sb = new StringBuilder(
					"update tuser set uname=?, cellphone=?, powerid=? where id=?");
			QueryRunner qr = new QueryRunner();
			int ret = 0;
			try {
				ret = qr.update(conn, sb.toString(), uname, cellphone, powerid,
						id);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBmanager.close(conn);
			}
			return ret;
		} else {
			StringBuilder sb = new StringBuilder(
					"update tuser set uname=?, cellphone=?, powerid=?, psw=? where id=?");
			QueryRunner qr = new QueryRunner();
			int ret = 0;
			try {
				ret = qr.update(conn, sb.toString(), uname, cellphone, powerid,
						psw, id);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBmanager.close(conn);
			}
			return ret;
		}
	}

	/**
	 * 修改用户密码
	 * 
	 * @param account
	 * @param psw
	 * @return
	 */
	public int updatePsw(String account, String psw) {
		Connection conn = DBmanager.getconn();
		StringBuilder sb = new StringBuilder(
				"update tuser set psw=? where account=?");
		QueryRunner qr = new QueryRunner();
		int ret = 0;
		try {
			ret = qr.update(conn, sb.toString(), psw, account);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBmanager.close(conn);
		}
		return ret;
	}

	/**
	 * 检查用户密码是否正确
	 * 
	 * @param account
	 * @param psw
	 * @return
	 */
	public boolean checkPsw(String account, String psw) {
		Connection conn = DBmanager.getconn();
		StringBuilder sb = new StringBuilder(
				"select psw from tuser where account=?");
		QueryRunner qr = new QueryRunner();
		String realpsw = null;
		try {
			realpsw = qr.query(conn, sb.toString(), new ScalarHandler<String>(
					"psw"), account);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBmanager.close(conn);
		}
		if (realpsw != null && realpsw.equals(psw)) {
			return true;
		} else {
			return false;
		}

	}
}
