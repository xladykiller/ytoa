package com.yitengls.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.yitengls.bean.TstampBean;
import com.yitengls.bean.TstampuserviewBean;
import com.yitengls.db.DBmanager;

public class XXTstampDao {
	/**
	 * 添加公章使用
	 * @param rb
	 * @return
	 */
	public int addStamp(TstampBean b) {
		Connection conn = DBmanager.getconn();
		StringBuilder sb = new StringBuilder(
				"insert into tstamp values(null,?,?,?,?,?,?,?)");
		QueryRunner qr = new QueryRunner();
		int ret = 0;
		try {
			ret = qr.update(conn, sb.toString(),b.getYear(),b.getUsedate(),b.getUsereason(),b.getFilenum(),b.getPetitioner(),b.getPetitionerid(),b.getMemo());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBmanager.close(conn);
		}
		return ret;
	}

//	/**
//	 * 检查典型案例标题是否重复
//	 * @param title
//	 * @return true:不重复 
//	 */
//	public boolean checkExampleTitle(String title){
//		boolean b = false;
//		Connection conn = DBmanager.getconn();
//		StringBuilder sb = new StringBuilder("select * from texample where title=?");
//		QueryRunner qr = new QueryRunner();
//		TexampleBean eb = new TexampleBean();
//		try {
//			eb = qr.query(conn, sb.toString(),new BeanHandler<TexampleBean>(TexampleBean.class),title);
//		}catch (SQLException e) {
//			e.printStackTrace();
//		} finally{
//			DBmanager.close(conn);
//		}
//		if(eb == null){
//			b = true;
//		}
//		return b;
//	}
	
	/**
	 * 模糊查询公章使用
	 * @param title
	 * @return
	 */
	public List<TstampuserviewBean> searchStamp(String year,String petitioner,String uname,String usereason){
		StringBuilder sb = new StringBuilder("select * from tstampuserview where 1=1");
		sb.append(" and year like '%").append(year)
		.append("%' and petitioner like '%").append(petitioner)
		.append("%' and uname like '%").append(uname)
		.append("%' and usereason like '%").append(usereason)
		.append("%' ORDER BY usedate DESC");
		Connection conn = DBmanager.getconn();
		QueryRunner qr = new QueryRunner();
		List<TstampuserviewBean> list = null;
		try {
			list = qr.query(conn, sb.toString(),
					new BeanListHandler<TstampuserviewBean>(TstampuserviewBean.class));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBmanager.close(conn);
		}
		return list;
	}
	
	/**
	 * 分页查询公章使用
	 * @param pageNow
	 * @param pageSize
	 * @param title
	 * @return
	 */
	public List<TstampuserviewBean> getStampByPage(int pageNow, int pageSize,String year,String petitioner,String uname,String usereason){		
		StringBuilder sb = new StringBuilder("select * from tstampuserview where 1=1");
		sb.append(" and year like '%").append(year)
		.append("%' and petitioner like '%").append(petitioner)
		.append("%' and uname like '%").append(uname)
		.append("%' and usereason like '%").append(usereason)
		.append("%' ORDER BY usedate DESC LIMIT ?,?");
		
		Connection conn = DBmanager.getconn();
		QueryRunner qr = new QueryRunner();
		List<TstampuserviewBean> list = null;
		try {
			list = qr.query(conn, sb.toString(),
					new BeanListHandler<TstampuserviewBean>(TstampuserviewBean.class),pageSize*(pageNow-1),pageSize);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBmanager.close(conn);
		}
		return list;
	}
	
	/**
	 * 删除公章使用
	 * @param id
	 * @return
	 */
	public int delStamp(String id){
		Connection conn = DBmanager.getconn();
		QueryRunner qr = new QueryRunner();
		StringBuilder sb = new StringBuilder("delete from tstamp where id=?");
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
	 * 修改公章使用
	 * @param rb
	 * @return
	 */
	public int updateStamp(TstampBean b){
		Connection conn = DBmanager.getconn();
		StringBuilder sb = new StringBuilder(
				"update tstamp set year=?, usedate=?, filenum=?, usereason=?, memo=? where id=?");
		QueryRunner qr = new QueryRunner();
		int ret=0;
		try {
			ret = qr.update(conn, sb.toString(),b.getYear(),b.getUsedate(),b.getFilenum(),b.getUsereason(),b.getMemo(),b.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBmanager.close(conn);
		}
		return ret;
	}
}
