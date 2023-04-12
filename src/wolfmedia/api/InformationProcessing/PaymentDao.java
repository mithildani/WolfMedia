package wolfmedia.api.InformationProcessing;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import wolfmedia.DBConnection;



public class PaymentDao {
	public PaymentDao() {}
	
    public double getTotalMonthlyPayment(int year, int month) throws SQLException {
        Connection conn = DBConnection.getConnection();
        double totalPayment = 0;
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT SUM(Amount) AS TotalPayment FROM ExpensePayment WHERE Year = ? AND Month = ?");
            stmt.setInt(1, year);
            stmt.setInt(2, month);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                totalPayment = rs.getDouble("TotalPayment");
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving total monthly payment: " + e.getMessage());
            throw e;
        }
        return totalPayment;
    }
    
    public double getTotalYearlyRevenue(int year) throws SQLException {
        Connection conn = DBConnection.getConnection();
        double totalRevenue = 0;
        
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT SUM(Amount) AS TotalRevenue FROM ExpensePayment WHERE Year = ?");
            stmt.setInt(1, year);
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()) {
                totalRevenue = rs.getDouble("TotalRevenue");
            }
            
        } catch (SQLException e) {
            System.out.println("Error retrieving total yearly revenue: " + e.getMessage());
            throw e;
        }
        
        return totalRevenue;
    }

}





