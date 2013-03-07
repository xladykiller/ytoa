package com.yitengls.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.yitengls.bean.TinfoBean;
import com.yitengls.db.DBmanager;

public class XXTinfoDao {
	
	/**
	 * 修改通知
	 * @param ib
	 * @return
	 */
	public int updateInfo(TinfoBean rb){
		Connection conn = DBmanager.getconn();
		StringBuilder sb = new StringBuilder(
				"update tinfo set title=?, content=?, author=?, addtime=curdate() where id=?");
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
	
	/**
	 * 添加通知
	 * @param ib
	 * @return
	 */
	public int addInfo(TinfoBean ib) {
		Connection conn = DBmanager.getconn();
		StringBuilder sb = new StringBuilder(
				"insert into tinfo values(null,?,?,curdate(),?,?)");
		QueryRunner qr = new QueryRunner();
		int ret = 0;
		try {
			ret = qr.update(conn, sb.toString(), ib.getTitle(),ib.getContent(),ib.getAuthor(),ib.getAttachment());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBmanager.close(conn);
		}
		return ret;
	}
	
	/**
	 * 删除通知
	 * @param id
	 * @return
	 */
	public int delInfo(String id){
		Connection conn = DBmanager.getconn();
		QueryRunner qr = new QueryRunner();
		StringBuilder sb = new StringBuilder("delete from tinfo where id=?");
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
	 * 模糊查询通知(按时间排序)
	 * @param title
	 * @return
	 */
	public List<TinfoBean> searchInfo(String title){
		StringBuilder sb = new StringBuilder("select * from tinfo where 1=1");
		if(title != ""){
			sb.append(" and title like '%").append(title).append("%'");
		}
		sb.append(" ORDER BY addtime DESC, id DESC");
		Connection conn = DBmanager.getconn();
		QueryRunner qr = new QueryRunner();
		List<TinfoBean> ls = null;
		try {
			ls = qr.query(conn, sb.toString(),
					new BeanListHandler<TinfoBean>(TinfoBean.class));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBmanager.close(conn);
		}
		return ls;
	}
	
	/**
	 * 分页查询通知(按时间排序)
	 * @param pageNow
	 * @param pageSize
	 * @param title
	 * @return
	 */
	public List<TinfoBean> getInfoByPage(int pageNow, int pageSize,String title){
		StringBuilder sb = null;
		if(title.isEmpty()){
//			sb = new StringBuilder("SELECT * from tinfo where id >=(SELECT id from tinfo limit ?,1) ORDER BY addtime DESC LIMIT ?");
			sb =  new StringBuilder("select * from tinfo ORDER BY addtime DESC, id DESC LIMIT ?,?");
		}else{
//			sb = new StringBuilder("SELECT * from tinfo where id >=(SELECT id from tinfo where title like '%").
//					append(title).append("%'").append(" limit ?,1) and title like '%").append(title).append("%'  ORDER BY addtime DESC LIMIT ?");
			sb =  new StringBuilder("select * from tinfo where title like '%").append(title).append("%' ORDER BY addtime DESC, id DESC LIMIT ?,?");
		}
//		sb.append("   ORDER BY addtime DESC");
		Connection conn = DBmanager.getconn();
		QueryRunner qr = new QueryRunner();
		List<TinfoBean> ls = null;
		try {
			ls = qr.query(conn, sb.toString(),
					new BeanListHandler<TinfoBean>(TinfoBean.class),pageSize*(pageNow-1),pageSize);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBmanager.close(conn);
		}
		return ls;
	}
	
	/**
	 * 根据id查询通知记录
	 * @param id
	 * @return
	 */
	public TinfoBean getInfoById(String id){
		Connection conn = DBmanager.getconn();
		QueryRunner qr = new QueryRunner();
		StringBuilder sb = new StringBuilder("select from tinfo where id=?");
		TinfoBean ib = null;
		try {
			ib = qr.query(conn, sb.toString(),new BeanHandler<TinfoBean>(TinfoBean.class), id);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBmanager.close(conn);
		}
		return ib;
	}
}
