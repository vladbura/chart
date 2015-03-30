package com.vbur.avensis.chart.DatabaseController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.ws.Response;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class SQLRingChart {

	private static final Logger AppLog = LogManager.getLogger(SQLRingChart.class);

	public List<DataRing> dataRing() {
		List<DataRing> list = new ArrayList<DataRing>();
		String sql = "SELECT * FROM ring_chart";
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
			AppLog.error("Error initializating database SQLRingChart", e);
		}
		return list;
	}

	private  DataRing processRow(ResultSet result) throws SQLException {
		DataRing data = new DataRing();
		data.setName(result.getString("security"));
		data.setWeight(result.getDouble("weighting"));
		data.setDate(result.getString("pie_date"));
		return data;
	}


	public DataPie insert(DataRing data) {

		String sql = "INSERT INTO \"public\".ring_chart (\"pie_date\", \"security\",\"weighting\") VALUES (?, ?, ?) ";
		try {
			DatabaseEngine.getInstance();
			Connection con = DatabaseEngine.getPool().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, data.getDate());
			stmt.setString(2, data.getName());
			stmt.setDouble(3, data.getWeight());
			stmt.executeUpdate();
			con.commit();
		    stmt.close();
			con.close();
		} catch (SQLException e) {
			AppLog.error("Unable to insert data SQLRingChart " +data.getName());
			e.printStackTrace();
		}
		return data;
	}
}
