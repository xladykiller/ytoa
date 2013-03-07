package com.yitengls.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.yitengls.bean.TpowerBean;
import com.yitengls.db.DBmanager;

public class XXTpowerDao {

	/**
	 * 读取所有角色
	 * @return
	 */
	public List<TpowerBean> readRole() {
		Connection conn = DBmanager.getconn();
		StringBuilder sb = new StringBuilder(
				"select id,rolename,permission from tpower");
		QueryRunner qr = new QueryRunner();
		List<TpowerBean> list = null;
		try {
			list = qr.query(conn, sb.toString(),
					new BeanListHandler<TpowerBean>(TpowerBean.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			DBmanager.close(conn);
		}
		return list;
	}
	
	/**
	 * 删除角色
	 * @param id
	 * @return
	 */
	public int delPower(String id){
		Connection conn = DBmanager.getconn();
		StringBuilder sb = new StringBuilder(
				"delete from tpower where id=? and id not in(select powerid from tuser)");
		QueryRunner qr = new QueryRunner();
		int ret = 0;
		try {
			ret = qr.update(conn, sb.toString(), id);
		} catch (Exception e) {
			e.printStackTrace();
		}  finally{
			DBmanager.close(conn);
		}
		return ret;
	}
	
	/**
	 * 修改角色
	 * @param id
	 * @param rolename
	 * @param permission
	 * @return
	 */
	public int updatePower(String id,String rolename,String permission){
		Connection conn = DBmanager.getconn();
		StringBuilder sb = new StringBuilder(
				"update tpower set rolename=?, permission=? where id=?");
		QueryRunner qr = new QueryRunner();
		int ret = 0;
		try {
			ret = qr.update(conn, sb.toString(), rolename,permission,id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			DBmanager.close(conn);
		}
		return ret;
	}
	
	/**
	 * 检查角色名是否重复
	 * @param rolename
	 * @return true:不重复
	 */
	public boolean checkRolename(String rolename){
		boolean b = false;
		Connection conn = DBmanager.getconn();
		StringBuilder sb = new StringBuilder(
				"select * from tpower where rolename=?");
		QueryRunner qr = new QueryRunner();
		TpowerBean pb = null;
		try {
			pb = qr.query(conn, sb.toString(), new BeanHandler<TpowerBean>(
					TpowerBean.class), rolename);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBmanager.close(conn);
		}
		if (pb == null) {
			b = true;// 角色名不重复，可以添加
		}
		return b;
	}
	
	/**
	 * 添加权限
	 * @param rolename
	 * @param permission
	 * @return
	 */
	public int addPower(String rolename,String permission) {
		Connection conn = DBmanager.getconn();
		StringBuilder sb = new StringBuilder(
				"insert into tpower values(null,?,?)");
		QueryRunner qr = new QueryRunner();
		int ret = 0;
		try {
			ret = qr.update(conn, sb.toString(), rolename,permission);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBmanager.close(conn);
		}
		return ret;
	}
	
}
