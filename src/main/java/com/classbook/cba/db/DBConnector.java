package com.classbook.cba.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class DBConnector {
	private static Connection connection;

	public static Connection getConnection() {
		return connection == null ? createConnection() : connection;
	}

	private static Connection createConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Add async for server delay
			ExecutorService threadpool = Executors.newCachedThreadPool();
			Future<Connection> futureTask = threadpool.submit(() -> DriverManager.getConnection(
					"jdbc:mysql://mysql-14395797-dummy-hmm-2024.a.aivencloud.com:12571/cb_app_db?sslmode=require",
					"avnadmin", "AVNS_9GyEO8aN8-iRO0_Abuo"));
			while (!futureTask.isDone()) {
				System.out.println("FutureTask is not finished yet...");
			}
			Connection conn = futureTask.get();
			connection = conn;
			threadpool.shutdown();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}

}
