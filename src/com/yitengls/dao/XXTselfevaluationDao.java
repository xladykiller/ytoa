package com.yitengls.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.yitengls.bean.TpriseBean;
import com.yitengls.bean.TselfevaluationBean;
import com.yitengls.bean.TselfevaluserviewBean;
import com.yitengls.db.DBmanager;

public class XXTselfevaluationDao {

	/**
	 * 员工添加自我评价
	 * @param year
	 * @param evaldate
	 * @param uname
	 * @param unameid
	 * @param result
	 * @param content
	 * @param params
	 * @return
	 */
	public int addSelfevaluation(String year,java.util.Date evaldate,String uname,int unameid,String result,String content,String[] params){
		Connection conn = DBmanager.getconn();
		QueryRunner qr = new QueryRunner();
		StringBuilder sb = new StringBuilder("insert into tselfevaluation(`year`,`evaldate`,`uname`,`unameid`,`result`,`content`");
		for(int i = 0;params!=null&&i < params.length;i++){
			sb.append(",`").append(params[i]).append("`");
		}
		sb.append(") values('").append(year).append("','").
		append(evaldate).append("','").append(uname).append("','").
		append(unameid).append("','").append(result).append("','")
		.append(content).append("'");
		for(int i = 0; params!=null&&i < params.length;i++){
			sb.append(",'1'");
		}
		sb.append(")");
		int ret = 0;
		try {
			ret = qr.update(conn,sb.toString());
		}  catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBmanager.close(conn);
		}
		return ret;
	}
	
	/**
	 * 检查evaldate是否重复
	 * @param date
	 * @return true：不重复，可添加 
	 */
	public boolean checkEvaldate(java.sql.Date evaldate,int unameid) {
		boolean b = false;
		Connection conn = DBmanager.getconn();
		StringBuilder sb = new StringBuilder(
				"select * from tselfevaluation where evaldate=? and unameid=?");
		QueryRunner qr = new QueryRunner();
		TselfevaluationBean bean = null;
		try {
			bean = qr.query(conn, sb.toString(), new BeanHandler<TselfevaluationBean>(
					TselfevaluationBean.class), evaldate,unameid);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBmanager.close(conn);
		}

		if (bean == null) {
			b = true;// 时间不重复，可以添加
		}
		return b;
	}
	
	public int updateSelfevaluation(int id,String year,java.util.Date evaldate,
			String uname, int unameid, String result, String content ,String[] params){
		Connection conn = DBmanager.getconn();
		QueryRunner qr = new QueryRunner();
		StringBuilder sb1 = new StringBuilder("delete from tselfevaluation where id=").append(id);
		StringBuilder sb2 = new StringBuilder("insert into tselfevaluation(`year`,`evaldate`,`uname`,`unameid`,`result`,`content`");
		for(int i = 0;params!=null&&i < params.length;i++){
			sb2.append(",`").append(params[i]).append("`");
		}
		sb2.append(") values('").append(year).append("','").
		append(evaldate).append("','").append(uname).append("','").
		append(unameid).append("','").append(result).append("','")
		.append(content).append("'");
		for(int i = 0; params!=null&&i < params.length;i++){
			sb2.append(",'1'");
		}
		sb2.append(")");
		int ret1 = 0;
		int ret2 = 0;
		try {
			conn.setAutoCommit(false);
			ret1 = qr.update(conn,sb1.toString());
			ret2 = qr.update(conn,sb2.toString());
			conn.commit();
		}  catch (SQLException e) {
			e.printStackTrace();
			if(conn != null){
				try {
					conn.rollback();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		} finally{
			if(conn != null){
				try {
					conn.setAutoCommit(true);
					DBmanager.close(conn);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		if(ret1==1  && ret2==1)
			return 1;
		else
			return 0;
	}
	
	/**
	 * 根据id删除
	 * @param id
	 * @return
	 */
	public int delSelfevaluation(int id){
		Connection conn = DBmanager.getconn();
		QueryRunner qr = new QueryRunner();
		StringBuilder sb = new StringBuilder("delete from tselfevaluation where id=").append(id);
		int ret = 0;
		try {
			ret = qr.update(conn,sb.toString());
		}  catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBmanager.close(conn);
		}
		return ret;
	}

    /**
     * 根据unameid删除
     * @param unameid
     * @return
     */
	public int delSelfevaluationByUnameid(int unameid){
		Connection conn = DBmanager.getconn();
		QueryRunner qr = new QueryRunner();
		StringBuilder sb = new StringBuilder("delete from tselfevaluation where unameid=").append(unameid);
		int ret = 0;
		try {
			ret = qr.update(conn,sb.toString());
		}  catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBmanager.close(conn);
		}
		return ret;
	}
	
	
	/**
	 * 员工按时间段查找自己的评价记录
	 * @param startdate
	 * @param enddate
	 * @param uname
	 * @return
	 */
	public List<TselfevaluationBean> searchSelfevaluation(java.sql.Date startdate,java.sql.Date enddate,int unameid){
		Connection conn = DBmanager.getconn();
		QueryRunner qr = new QueryRunner();
		StringBuilder sb = new StringBuilder("select * from tselfevaluation where evaldate>='")
				.append(startdate).append("' and evaldate<='").append(enddate)
				.append("' and unameid='").append(unameid).append("'ORDER BY evaldate ");
		List<TselfevaluationBean> list = null;
		try {
			list = qr.query(conn,sb.toString(),new BeanListHandler<TselfevaluationBean>(TselfevaluationBean.class));
		}  catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBmanager.close(conn);
		}
		return list;
	}
	
	/**
	 * 员工按时间段分页查找自己的评价记录
	 * @param pageNow
	 * @param pageSize
	 * @param startdate
	 * @param enddate
	 * @param uname
	 * @return
	 */
	public List<TselfevaluationBean> searchSelfevaluationByPage(int pageNow, int pageSize,
			java.sql.Date startdate,java.sql.Date enddate,int unameid){
		Connection conn = DBmanager.getconn();
		QueryRunner qr = new QueryRunner();
		StringBuilder sb = new StringBuilder("select * from tselfevaluation where evaldate>='")
				.append(startdate).append("' and evaldate<='").append(enddate)
				.append("' and unameid='").append(unameid).append("'ORDER BY evaldate limit ?,?");
		List<TselfevaluationBean> list = null;
		try {
			list = qr.query(conn,sb.toString(),new BeanListHandler<TselfevaluationBean>(TselfevaluationBean.class),pageSize*(pageNow-1),pageSize);
		}  catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBmanager.close(conn);
		}
		return list;
	}
	
	
	/**
	 * 主任按时间段和账号查找员工的评价记录
	 * @param startdate
	 * @param enddate
	 * @param uname
	 * @return
	 */
	public List<TselfevaluserviewBean> searchEvaluation(java.sql.Date startdate,java.sql.Date enddate,
			String account,String uname){
		Connection conn = DBmanager.getconn();
		QueryRunner qr = new QueryRunner();
		StringBuilder sb = new StringBuilder("select * from tselfevaluserview where evaldate>='")
				.append(startdate).append("' and evaldate<='").append(enddate)
				.append("' and account like '%").append(account)
				.append("%' and uname like '%").append(uname).append("%' ORDER BY account,evaldate ");
		List<TselfevaluserviewBean> list = null;
		try {
			list = qr.query(conn,sb.toString(),new BeanListHandler<TselfevaluserviewBean>(TselfevaluserviewBean.class));
		}  catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBmanager.close(conn);
		}
		return list;
	}
	
	
	
	/**
	 * 主任按时间段和账号分页查找员工的评价记录
	 * @param pageNow
	 * @param pageSize
	 * @param startdate
	 * @param enddate
	 * @param uname
	 * @return
	 */
	public List<TselfevaluserviewBean> searchEvaluationByPage(int pageNow, int pageSize,
                   java.sql.Date startdate,java.sql.Date enddate,String account, String uname){
		Connection conn = DBmanager.getconn();
		QueryRunner qr = new QueryRunner();
		StringBuilder sb = new StringBuilder("select * from tselfevaluserview where evaldate>='")
				.append(startdate).append("' and evaldate<='").append(enddate)
				.append("' and account like '%").append(account)
				.append("%' and uname like '%").append(uname)
				.append("%' ORDER BY account,evaldate limit ?,?");
		List<TselfevaluserviewBean> list = null;
		try {
			list = qr.query(conn,sb.toString(),new BeanListHandler<TselfevaluserviewBean>(TselfevaluserviewBean.class),pageSize*(pageNow-1),pageSize);
		}  catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBmanager.close(conn);
		}
		return list;
	}
	
    /**
     * 计算某个时间段某个员工奖惩
     * @param startdate
     * @param enddate
     * @param unameid
     * @return
     */
	public int getPrise(java.sql.Date startdate,java.sql.Date enddate,int unameid){
		Connection conn = DBmanager.getconn();
		QueryRunner qr = new QueryRunner();
		StringBuilder sb = new StringBuilder("select * from tselfevaluserview where evaldate>='")
				.append(startdate).append("' and evaldate<='").append(enddate)
				.append("' and unameid='").append(unameid).append("'ORDER BY evaldate ");
		List<TselfevaluationBean> list = null;
		try {
			list = qr.query(conn,sb.toString(),new BeanListHandler<TselfevaluationBean>(TselfevaluationBean.class));
		}  catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBmanager.close(conn);
		}
		int prise = 0;
		for(int i=0; i<list.size(); i++){
			TselfevaluationBean b = list.get(i);
			if(b.getLate()!=null && b.getLate().equals("1")) prise-=5;
			if(b.getLeave()!=null && b.getLeave().equals("1")) prise-=5;
			if(b.getAbsence()!=null && b.getAbsence().equals("1")) prise-=5;
			if(b.getSick()!=null && b.getSick().equals("1")) prise-=5;
			
			if(b.getHappy()!=null && b.getHappy().equals("1")) prise+=5;
			if(b.getTidy()!=null && b.getTidy().equals("1")) prise+=5;
			if(b.getSpeedy()!=null && b.getSpeedy().equals("1")) prise+=5;
			
			if(b.getMistake()!=null && b.getMistake().equals("1")) prise-=10;
			if(b.getLeaveseat()!=null && b.getLeaveseat().equals("1")) prise-=10;
			if(b.getAbsentwork()!=null && b.getAbsentwork().equals("1")) prise-=10;
			if(b.getPlaygames()!=null && b.getPlaygames().equals("1")) prise-=10;		
		}
		return prise;
	}
	
	
	public List<TpriseBean> getAllPrise(java.sql.Date startdate,java.sql.Date enddate,String account,String uname){
		List<TselfevaluserviewBean> unameidlist =null;
		unameidlist = this.getId(account, uname);
		ArrayList<TpriseBean> priselist = new ArrayList<TpriseBean>();
		for(int i=0; i<unameidlist.size(); i++){
			int unameid = unameidlist.get(i).getUnameid();
			int prise = this.getPrise(startdate, enddate, unameid);
			TpriseBean b = new TpriseBean();
			b.setUnameid(unameid);
			b.setAccount(unameidlist.get(i).getAccount());
			b.setUname(unameidlist.get(i).getUname());
			b.setPrise(prise);
			priselist.add(b);
		}
		return priselist;
	}
	
	public List<TselfevaluserviewBean> getId(String account,String uname){
		Connection conn = DBmanager.getconn();
		QueryRunner qr = new QueryRunner();
		StringBuilder sb = new StringBuilder("select distinct unameid,account,uname from tselfevaluserview where account like '%")
				.append(account).append("%' and uname like'%").append(uname).append("%'");
		List<TselfevaluserviewBean> list =null;
		try {
			list = qr.query(conn,sb.toString(),new BeanListHandler<TselfevaluserviewBean>(TselfevaluserviewBean.class));
		}  catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBmanager.close(conn);
		}
		return list;
	}
}
