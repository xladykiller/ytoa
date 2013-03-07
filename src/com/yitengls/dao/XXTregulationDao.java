package com.yitengls.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.yitengls.bean.TregulationBean;
import com.yitengls.db.DBmanager;

public class XXTregulationDao {

	/**
	 * 添加规章制度
	 * @param rb
	 * @return
	 */
	public int addRegulation(TregulationBean rb) {
		Connection conn = DBmanager.getconn();
		StringBuilder sb = new StringBuilder(
				"insert into tregulation values(null,?,?,curdate(),?)");
		QueryRunner qr = new QueryRunner();
		int ret = 0;
		try {
			ret = qr.update(conn, sb.toString(), rb.getTitle(),rb.getContent(),rb.getAuthor());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBmanager.close(conn);
		}
		return ret;
	}

	/**
	 * 检查规章制度标题是否重复
	 * @param title
	 * @return true:不重复 
	 */
	public boolean checkRegulationTitle(String title){
		boolean b = false;
		Connection conn = DBmanager.getconn();
		StringBuilder sb = new StringBuilder("select * from tregulation where title=?");
		QueryRunner qr = new QueryRunner();
		TregulationBean rb = new TregulationBean();
		try {
			rb = qr.query(conn, sb.toString(),new BeanHandler<TregulationBean>(TregulationBean.class),title);
		}catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBmanager.close(conn);
		}
		if(rb == null){
			b = true;
		}
		return b;
	}
	
	/**
	 * 模糊查询规章制度
	 * @param title
	 * @return
	 */
	public List<TregulationBean> searchRegulation(String title){
		StringBuilder sb = new StringBuilder("select * from tregulation where 1=1");
		if(title != ""){
			sb.append(" and title like '%").append(title).append("%'");
		}
		Connection conn = DBmanager.getconn();
		QueryRunner qr = new QueryRunner();
		List<TregulationBean> ls = null;
		try {
			ls = qr.query(conn, sb.toString(),
					new BeanListHandler<TregulationBean>(TregulationBean.class));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBmanager.close(conn);
		}
		return ls;
	}
	
	/**
	 * 分页查询规章制度
	 * @param pageNow
	 * @param pageSize
	 * @param title
	 * @return
	 */
	public List<TregulationBean> getRegulationByPage(int pageNow, int pageSize,String title){
		StringBuilder sb = null;
		if(title.isEmpty()){
			sb = new StringBuilder("SELECT * from tregulation where id >=(SELECT id from tregulation limit ?,1) LIMIT ?");
		}else{
			sb = new StringBuilder("SELECT * from tregulation where id >=(SELECT id from tregulation where title like '%").
					append(title).append("%'").append(" limit ?,1) and title like '%").append(title).append("%' LIMIT ?");
		}
		Connection conn = DBmanager.getconn();
		QueryRunner qr = new QueryRunner();
		List<TregulationBean> ls = null;
		try {
			ls = qr.query(conn, sb.toString(),
					new BeanListHandler<TregulationBean>(TregulationBean.class),pageSize*(pageNow-1),pageSize);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBmanager.close(conn);
		}
		return ls;
	}
	
	/**
	 * 删除规章制度
	 * @param id
	 * @return
	 */
	public int delRegulation(String id){
		Connection conn = DBmanager.getconn();
		QueryRunner qr = new QueryRunner();
		StringBuilder sb = new StringBuilder("delete from tregulation where id=?");
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
	 * 修改规章制度
	 * @param rb
	 * @return
	 */
	public int updateRegulation(TregulationBean rb){
		Connection conn = DBmanager.getconn();
		StringBuilder sb = new StringBuilder(
				"update tregulation set title=?, content=?, author=?, addtime=curdate() where id=?");
		QueryRunner qr = new QueryRunner();
		int ret=0;
		try {
			ret = qr.update(conn, sb.toString(), rb.getTitle(),rb.getContent(),rb.getAuthor(),rb.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBmanager.close(conn);
		}
		return ret;
	}
	
}
