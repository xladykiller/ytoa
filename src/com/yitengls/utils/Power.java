package com.yitengls.utils;

public class Power {
	
	public static final int addUser = 1;
	public static final int delUser = 2;
	public static final int updateUser = 3;
	public static final int searchUser = 4;
	public static final int updatePsw = 5;
	
	public static final int addPower = 6;
	public static final int delPower = 7;
	public static final int updatePower = 8;
	
	public static final int addRegulation = 11;
	public static final int delRegulation = 12;
	public static final int updateRegulation = 13;
	public static final int searchRegulation = 14;
	
	public static final int addInfo = 16;
	public static final int delInfo = 17;
	public static final int updateInfo = 18;
	public static final int searchInfo = 19;
	public static final int downloadInfo = 20;
	
	public static final int addExample = 21;
	public static final int delExample = 22;
	public static final int updateExample = 23;
	public static final int searchExample = 24;
	
	public static final int addStamp = 26;
	public static final int delStamp = 27;
	public static final int updateStamp = 28;
	public static final int searchStamp = 29;
	
	public static final int addRisk = 31;
	public static final int delRisk = 32;
	public static final int updateRisk = 33;
	public static final int searchRisk = 34;
	
	public static final int addEval = 36;
	public static final int delEval = 37;
	public static final int updateEval = 38;
	public static final int searchMyEval = 39;
	public static final int searchAllEval = 40;
	public static final int viewAllPrise = 41;
	
/**
 * 验证权限
 * @param permission 用户拥有的权限
 * @param power 需要验证的权限
 * @return
 */
	public static boolean validate(long permission, final int power) {

		long i = 1;
		if (((i << power) & permission) != 0) {
			return true;
		}
		return false;
	}
}