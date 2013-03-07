package com.yitengls.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.yitengls.bean.Tcase1Bean;
import com.yitengls.bean.TriskBean;
import com.yitengls.db.DBmanager;

public class XXTriskDao {
	
	/**
	 * 检查proxyid是否存在
	 * @param proxyid
	 * @return
	 */
	public boolean checkProxyid(String proxyid){
		Connection conn = DBmanager.getconn();
		QueryRunner qr = new QueryRunner();
		StringBuilder sb = new StringBuilder("select * from tcase1 where proxyid=?");
		Tcase1Bean cb = null;
		try {
			cb = qr.query(conn, sb.toString(),new BeanHandler<Tcase1Bean>(Tcase1Bean.class),proxyid);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBmanager.close(conn);
		}
		if(cb == null){
			return false;
		}
		else{
			return true;
		}
	}
	

	/**
	 *添加风险记录
	 * @param rb
	 * @return
	 */
	public int addRisk(TriskBean rb) {
		Connection conn = DBmanager.getconn();
		StringBuilder sb = new StringBuilder(
				"insert into trisk values(null,?,?,?,?,?,?,?,?,?)");
		QueryRunner qr = new QueryRunner();
		int ret = 0;
		try {
			ret = qr.update(conn, sb.toString(),rb.getYear(),rb.getComplaindate(),rb.getComplainant(),rb.getComplainway(),rb.getPhone(),rb.getProxyid(),rb.getUndertaker(),rb.getContent(),rb.getResult());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBmanager.close(conn);
		}
		return ret;
	}
	
	/**
	 * 模糊查询风险记录
	 * @param year
	 * @param complainant
	 * @param proxyid
	 * @param undertaker
	 * @return
	 */
	public List<TriskBean> searchRisk(String year,String complainant,	String proxyid, String undertaker){
		StringBuilder sb = new StringBuilder("select * from trisk where 1=1");
		sb.append(" and year like '%").append(year)
		.append("%' and complainant like '%").append(complainant)
		.append("%' and proxyid like '%").append(proxyid)
		.append("%' and undertaker like '%").append(undertaker)
		.append("%' ORDER BY complaindate DESC");
		Connection conn = DBmanager.getconn();
		QueryRunner qr = new QueryRunner();
		List<TriskBean> list = null;
		try {
			list = qr.query(conn, sb.toString(),
					new BeanListHandler<TriskBean>(TriskBean.class));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBmanager.close(conn);
		}
		return list;
	}
	
	/**
	 * 分页查询风险记录
	 * @param pageNow
	 * @param pageSize
	 * @param year
	 * @param petitioner
	 * @param uname
	 * @param usereason
	 * @return
	 */
	public List<TriskBean> getRiskByPage(int pageNow, int pageSize,String year,String complainant,String proxyid, String undertaker){		
		StringBuilder sb = new StringBuilder("select * from trisk where 1=1");
		sb.append(" and year like '%").append(year)
		.append("%' and complainant like '%").append(complainant)
		.append("%' and proxyid like '%").append(proxyid)
		.append("%' and undertaker like '%").append(undertaker)
		.append("%' ORDER BY complaindate DESC LIMIT ?,?");
		Connection conn = DBmanager.getconn();
		QueryRunner qr = new QueryRunner();
		List<TriskBean> list = null;
		try {
			list = qr.query(conn, sb.toString(),
					new BeanListHandler<TriskBean>(TriskBean.class),pageSize*(pageNow-1),pageSize);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBmanager.close(conn);
		}
		return list;
	}
	
	/**
	 * 删除风险记录
	 * @param id
	 * @return
	 */
	public int delRisk(String id){
		Connection conn = DBmanager.getconn();
		QueryRunner qr = new QueryRunner();
		StringBuilder sb = new StringBuilder("delete from trisk where id=?");
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
	 * 更新风险记录
	 * @param rb
	 * @return
	 */
	public int updateRisk(TriskBean rb){
		Connection conn = DBmanager.getconn();
		StringBuilder sb = new StringBuilder(
				"update trisk set year=?, complaindate=?, complainant=?, complainway=?," +
				" phone=?, undertaker=?, content=?, result=?  where id=?");
		QueryRunner qr = new QueryRunner();
		int ret=0;
		try {
			ret = qr.update(conn, sb.toString(),rb.getYear(),rb.getComplaindate(),rb.getComplainant(),rb.getComplainway(),
					rb.getPhone(),rb.getUndertaker(),rb.getContent(),rb.getResult(),rb.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBmanager.close(conn);
		}
		return ret;
	}
	
	public List<Tcase1Bean> getProxyidList(String proxyidText){
		Connection conn = DBmanager.getconn();
		StringBuilder sb = new StringBuilder("select DISTINCT proxyid from tcase1 where proxyid like '%").append(proxyidText).append("%'");
		QueryRunner qr = new QueryRunner();
		List<Tcase1Bean> list = null;
		try {
			list = qr.query(conn, sb.toString(),new BeanListHandler<Tcase1Bean>(Tcase1Bean.class));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBmanager.close(conn);
		}
		return list;
	}
	 
}
