package com.classbook.cba.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnector {
	private static Connection connection;

	public static Connection getConnection() {
		return connection == null ? createConnection() : connection;
	}

	private static Connection createConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://mysql-14395797-dummy-hmm-2024.a.aivencloud.com:12571/cb_app_db?sslmode=require",
					"avnadmin", "AVNS_9GyEO8aN8-iRO0_Abuo");
			connection = conn;
			new Thread().wait(6000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}

}
