package com.yitengls.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.eclipse.jdt.internal.compiler.ast.ThisReference;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DBmanager {
	// static private Connection conn = null;
	// static private String url =
	// "jdbc:mysql://localhost:3306/oa?autoReconnect=true&useUnicode=true&characterEncoding=utf8";
	// static private String jdbcDriver = "com.mysql.jdbc.Driver";
	// static private String user = "root";
	// static private String password = "123456";

	public static Connection getconn() {
		// if (conn == null) {
		// DbUtils.loadDriver(jdbcDriver);
		// try {
		// conn = DriverManager.getConnection(url, user, password);
		// } catch (SQLException e) {
		// DbUtils.closeQuietly(conn);
		// conn = null;
		// e.printStackTrace();
		// }
		// }
		//
		// return conn;
		Connection connection = null;
		try {
			if (cpds == null) {
				init();
			}
			connection = cpds.getConnection(); // getconnection
		} catch (SQLException ex) {
			ex.printStackTrace();
			release();
		}
		return connection;
	}

	// public static void close(Connection con) {
	// DbUtils.closeQuietly(con);
	// con = null;
	// }
	//
	// public static void close() {
	// DbUtils.closeQuietly(conn);
	// conn = null;
	// }

	private static ComboPooledDataSource cpds = null;

	private static void init() {
		try {
			cpds = new ComboPooledDataSource();

		} catch (Exception e) {
			System.out.println("[error]ComboPooledDataSource init Error!");
			e.printStackTrace();
		}
	}

	public static void release() {
		try {
			if (cpds != null) {
				cpds.close();
				cpds = null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void close(Connection conn) {
		if (conn != null) {
			try {
				if (conn.isClosed() == false) {
					conn.close();
				}
				conn = null;
			} catch (SQLException e) {
				System.out.println("[error]Connection close Error!");
				e.printStackTrace();
			}
		}
	}

}
