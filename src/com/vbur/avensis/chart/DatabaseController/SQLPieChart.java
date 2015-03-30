package com.vbur.avensis.chart.DatabaseController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class SQLPieChart {

	private static final Logger AppLog = LogManager.getLogger(SQLPieChart.class);

	public List<DataPie> dataPie() {
		List<DataPie> list = new ArrayList<DataPie>();
		String sql = "SELECT * FROM pie_chart";
		try {
			DatabaseEngine.getInstance();
			Connection con = DatabaseEngine.getPool().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet result = stmt.executeQuery();
			while (result.next()) {
				list.add(processRow(result));
			}
			stmt.close();
			con.close();
		} catch (SQLException e) {
			AppLog.error("Error initializating database SQLPieChart", e);
		}
		return list;
	}

	private  DataPie processRow(ResultSet result) throws SQLException {
		DataPie data = new DataPie();
		data.setName(result.getString("country"));
		data.setWeight(result.getDouble("weight"));
		return data;
	}


	public DataPie insert(DataPie data) {

		String sql = "INSERT INTO \"public\".pie_chart ( \"country\",\"weight\") VALUES (?, ?) ";
		try {
			DatabaseEngine.getInstance();
			Connection con = DatabaseEngine.getPool().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, data.getName());
			stmt.setDouble(2, data.getWeight());
			stmt.executeUpdate();
			con.commit();
		    stmt.close();
			con.close();
		} catch (SQLException e) {
			AppLog.error("Unable to insert data " +data.getName());
			e.printStackTrace();
		}
		return data;
	}
}
