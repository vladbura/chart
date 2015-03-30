package com.vbur.avensis.chart.DatabaseController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;
import com.vbur.avensis.chart.BaseServices.Constants;

public class DatabaseEngine {
	private static DatabaseEngine _INSTANCE = null;
	private static final Logger AppLog = LogManager
			.getLogger(DatabaseEngine.class);

	/**
	 * @return the instance of database if is already created or not
	 */
	public static DatabaseEngine getInstance() {
		if (_INSTANCE == null)
			_INSTANCE = new DatabaseEngine();
		return _INSTANCE;
	}

	public static BoneCP getPool() {
		return _INSTANCE.pool;
	}

	public static void shutdown() {
		_INSTANCE.cleanShutdown();
	}

	private final BoneCPConfig dbConfig;

	private BoneCP pool;

	/**
	 * this can be configure also from a config file for this proposal we chose
	 * to be just in source
	 * attached in configs folder as 
	 * server.conf
	 */
	private DatabaseEngine() {
		try {
		    Class.forName("org.postgresql.Driver");
		 } catch (ClassNotFoundException e) {
		    System.err.println("Where is your PostgreSQL JDBC Driver? "+ "Include in your library path!");
		    e.printStackTrace();
		 }
		AppLog.info("Initializing database configuration");
		dbConfig = new BoneCPConfig();
		dbConfig.setJdbcUrl("jdbc:postgresql://212.146.105.61:5432/anevis");
		dbConfig.setUsername("anevis");
		dbConfig.setPassword("anevis");
		dbConfig.setCloseConnectionWatch(true);
		dbConfig.setCloseConnectionWatchTimeout(10, TimeUnit.SECONDS);
		dbConfig.setDefaultAutoCommit(false);
		dbConfig.setDetectUnclosedStatements(true);
		dbConfig.setDetectUnresolvedTransactions(true);
		dbConfig.setMaxConnectionsPerPartition(100);

		AppLog.info("Initializing database pool");

		try {
			pool = new BoneCP(dbConfig);
		} catch (final SQLException e) {
			AppLog.error("Error inigializing database pool", e);
			System.exit(Constants.EXIT_ERROR_NO_DATABASE);
		}

		AppLog.info("Database pool initialized. Running test query!");

		try {
			final Connection con = pool.getConnection();
			final PreparedStatement stmt = con
					.prepareStatement("SELECT VERSION() as v");
			final ResultSet result = stmt.executeQuery();
			if (result.next())
				AppLog.info("Test succesfull server version is: \""	+ result.getString("v") + "\"");
			else
				throw new SQLException("No results found, invalid test");
			con.close();
		} catch (final SQLException e) {
			AppLog.error("Error running test query", e);
			System.exit(Constants.EXIT_ERROR_INVALID_DATABASE);
		}
	}

	private void cleanShutdown() {
		pool.shutdown();
	}

}
