package com.yitengls.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.yitengls.bean.TexampleBean;
import com.yitengls.db.DBmanager;

public class XXTexampleDao {
	/**
	 * 添加典型案例
	 * @param rb
	 * @return
	 */
	public int addExample(TexampleBean eb) {
		Connection conn = DBmanager.getconn();
		StringBuilder sb = new StringBuilder(
				"insert into texample values(null,?,?,curdate(),?)");
		QueryRunner qr = new QueryRunner();
		int ret = 0;
		try {
			ret = qr.update(conn, sb.toString(), eb.getTitle(),eb.getContent(),eb.getAuthor());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBmanager.close(conn);
		}
		return ret;
	}

	/**
	 * 检查典型案例标题是否重复
	 * @param title
	 * @return true:不重复 
	 */
	public boolean checkExampleTitle(String title){
		boolean b = false;
		Connection conn = DBmanager.getconn();
		StringBuilder sb = new StringBuilder("select * from texample where title=?");
		QueryRunner qr = new QueryRunner();
		TexampleBean eb = new TexampleBean();
		try {
			eb = qr.query(conn, sb.toString(),new BeanHandler<TexampleBean>(TexampleBean.class),title);
		}catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBmanager.close(conn);
		}
		if(eb == null){
			b = true;
		}
		return b;
	}
	
	/**
	 * 模糊查询典型案例
	 * @param title
	 * @return
	 */
	public List<TexampleBean> searchExample(String title){
		StringBuilder sb = new StringBuilder("select * from texample where 1=1");
		if(title != ""){
			sb.append(" and title like '%").append(title).append("%'");
		}
		sb.append(" ORDER BY addtime DESC, id DESC");
		Connection conn = DBmanager.getconn();
		QueryRunner qr = new QueryRunner();
		List<TexampleBean> ls = null;
		try {
			ls = qr.query(conn, sb.toString(),
					new BeanListHandler<TexampleBean>(TexampleBean.class));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBmanager.close(conn);
		}
		return ls;
	}
	
	/**
	 * 分页查询典型案例
	 * @param pageNow
	 * @param pageSize
	 * @param title
	 * @return
	 */
	public List<TexampleBean> getExampleByPage(int pageNow, int pageSize,String title){
		StringBuilder sb = null;
		if(title.isEmpty()){
//			sb = new StringBuilder("SELECT * from texample where id >=(SELECT id from texample limit ?,1) LIMIT ?");
			sb =  new StringBuilder("select * from texample ORDER BY addtime DESC, id DESC LIMIT ?,?");
		}else{
//			sb = new StringBuilder("SELECT * from texample where id >=(SELECT id from texample where title like '%").
//					append(title).append("%'").append(" limit ?,1) and title like '%").append(title).append("%' LIMIT ?");
			sb =  new StringBuilder("select * from texample where title like '%").append(title).append("%' ORDER BY addtime DESC, id DESC LIMIT ?,?");
		}
		Connection conn = DBmanager.getconn();
		QueryRunner qr = new QueryRunner();
		List<TexampleBean> ls = null;
		try {
			ls = qr.query(conn, sb.toString(),
					new BeanListHandler<TexampleBean>(TexampleBean.class),pageSize*(pageNow-1),pageSize);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBmanager.close(conn);
		}
		return ls;
	}
	
	/**
	 * 删除典型案例
	 * @param id
	 * @return
	 */
	public int delExample(String id){
		Connection conn = DBmanager.getconn();
		QueryRunner qr = new QueryRunner();
		StringBuilder sb = new StringBuilder("delete from texample where id=?");
		int ret = 0;
		try {
			ret = qr.update(conn, sb.toString(), id);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBmanager.close(conn);
		}
		return ret;
	}
	
	/**
	 * 修改典型案例
	 * @param rb
	 * @return
	 */
	public int updateExample(TexampleBean eb){
		Connection conn = DBmanager.getconn();
		StringBuilder sb = new StringBuilder(
				"update texample set title=?, content=?, author=?, addtime=curdate() where id=?");
		QueryRunner qr = new QueryRunner();
		int ret=0;
		try {
			ret = qr.update(conn, sb.toString(), eb.getTitle(),eb.getContent(),eb.getAuthor(),eb.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBmanager.close(conn);
		}
		return ret;
	}
}
