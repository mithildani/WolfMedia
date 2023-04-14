package wolfmedia.api;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import wolfmedia.DBConnection;

public class PaymentDao {
    public PaymentDao() {
    }

    public double getTotalMonthlyPayment(int year, int month) throws SQLException {
        Connection conn = DBConnection.getConnection();
        double totalPayment = 0;
        try {
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT SUM(Amount) AS TotalPayment FROM ExpensePayment WHERE Year = ? AND Month = ?");
            stmt.setInt(1, year);
            stmt.setInt(2, month);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
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
            PreparedStatement stmt = conn
                    .prepareStatement("SELECT SUM(Amount) AS TotalRevenue FROM ExpensePayment WHERE Year = ?");
            stmt.setInt(1, year);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                totalRevenue = rs.getDouble("TotalRevenue");
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving total yearly revenue: " + e.getMessage());
            throw e;
        }

        return totalRevenue;
    }

    public ResultSet getHostPayment(int hostId) throws SQLException {
        ResultSet rs;
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT P.HostID as HostID, SUM(Amount) as HostPayment, Year, Month " +
                            "FROM ExpensePayment EP " +
                            "INNER JOIN PodcastEpisode PE on EP.MediaID = PE.MediaID " +
                            "INNER JOIN Podcast P on PE.PodcastID = P.PodcastID " +
                            "WHERE P.HostID= ? " +
                            "GROUP BY P.HostID,Year,Month");
            stmt.setInt(1, hostId);
            rs = stmt.executeQuery();
            return rs;
        } catch (SQLException e) {
            System.out.println("Error retrieving host payment: " + e.getMessage());
            throw e;
        }
    }

    public ResultSet getArtistPayment(int artistId) throws SQLException {
        ResultSet rs;
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT ArtistID, SUM(Amount*0.7/NOA.NumberOfArtists) as ArtistPayment, EP.Year as Year, EP.Month as Month "
                            +
                            "FROM ExpensePayment EP " +
                            "INNER JOIN ( " +
                            "  SELECT DISTINCT S.MediaID, ? as ArtistID " +
                            "  FROM Song S" +
                            "           INNER JOIN Album A on S.AlbumID = A.AlbumID " +
                            "           LEFT JOIN Collaborated C on S.MediaID = C.MediaID " +
                            "  WHERE A.ArtistID = ? " +
                            "     OR C.ArtistID = ? " +
                            ") as ArtistsSongs on ArtistsSongs.MediaID = EP.MediaID " +
                            "LEFT JOIN ( " +
                            "  SELECT S.MediaID, COUNT(*) + COUNT(C.ArtistID) as NumberOfArtists " +
                            "  FROM Song S " +
                            "  LEFT JOIN Collaborated C on S.MediaID = C.MediaID " +
                            "  GROUP BY S.MediaID, C.ArtistID " +
                            ") as NOA on NOA.MediaID = ArtistsSongs.MediaID " +
                            "GROUP BY ArtistID, EP.Year, EP.Month ");
            stmt.setInt(1, artistId);
            stmt.setInt(2, artistId);
            stmt.setInt(3, artistId);
            rs = stmt.executeQuery();
            return rs;
        } catch (SQLException e) {
            System.out.println("Error retrieving artist payment: " + e.getMessage());
            throw e;
        }
    }

    public ResultSet getRecordLabelPayment(String labelName) throws SQLException {
        ResultSet rs;
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT RL.LabelName as LabelName, SUM(Amount)*0.3 as RecordLabelPayment, Year, Month " +
                            "FROM ExpensePayment EP " +
                            "INNER JOIN Song S on EP.MediaID = S.MediaID " +
                            "INNER JOIN Album A on S.AlbumID = A.AlbumID " +
                            "INNER JOIN Artist A2 on A.ArtistID = A2.ArtistID " +
                            "INNER JOIN Contract C on A2.ArtistID = C.ArtistID " +
                            "INNER JOIN RecordLabel RL on C.LabelName = RL.LabelName " +
                            "WHERE RL.LabelName = ? " +
                            "GROUP BY Year, RL.LabelName, Month ");
            stmt.setString(1, labelName);
            rs = stmt.executeQuery();
            return rs;
        } catch (SQLException e) {
            System.out.println("Error retrieving record label payment: " + e.getMessage());
            throw e;
        }
    }

    public boolean createSongPayment(int mediaId, int year, int month) throws SQLException {
        String sql = "INSERT INTO ExpensePayment (MediaID, Year, Month, Amount, CreatedAt) " +
                "VALUE (?, ?, ?, " +
                "                    ( " +
                "                        SELECT SUM(Count) as 'TotalPlayCount' " +
                "                        FROM Playbacks " +
                "                        WHERE MediaID= ? " +
                "                        AND YEAR(PlayedAt) = ? " +
                "                        AND MONTH(PlayedAt) = ? " +
                "                        GROUP BY MediaID " +
                "                    ) * " +
                "                    ( " +
                "                        SELECT RoyaltyRate " +
                "                        FROM Song " +
                "                        WHERE MediaID= ? " +
                "                    ), DATE(NOW()) " +
                "        ) ";
        Connection conn = DBConnection.getConnection();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, mediaId);
            stmt.setInt(2, year);
            stmt.setInt(3, month);
            stmt.setInt(4, mediaId);
            stmt.setInt(5, year);
            stmt.setInt(6, month);
            stmt.setInt(7, mediaId);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error Creating Payment");
            throw e;
        }
    }

    public boolean createPodcastPayment(int hostId, int year, int month) throws SQLException {
        String podSql = "INSERT INTO ExpensePayment (MediaID, Year, Month, Amount, CreatedAt) " +
                "(SELECT PE.MediaID, " +
                "          YEAR(AP.PlayedAt) as Year, " +
                "          MONTH(AP.PlayedAt) as Month, " +
                "          FlatFee+AdRate*SUM(Count) as Amount, " +
                "          DATE(NOW()) as CreatedAt " +
                "   FROM PodcastEpisode PE " +
                "   INNER JOIN Podcast P on PE.PodcastID = P.PodcastID " +
                "   INNER JOIN PodcastHost PH on P.HostID = PH.HostID " +
                "   INNER JOIN AdsPlayed AP on PE.MediaID = AP.MediaID " +
                "   WHERE P.HostID= ? " +
                "   AND YEAR(AP.PlayedAt) = ? " +
                "   AND MONTH(AP.PlayedAt) = ? " +
                "   GROUP BY PE.MediaID, Year, Month) ";
        Connection conn = DBConnection.getConnection();
        try (PreparedStatement stmt = conn.prepareStatement(podSql)) {
            stmt.setInt(1, hostId);
            stmt.setInt(2, year);
            stmt.setInt(3, month);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error Creating Payment");
            throw e;
        }
    }

    public boolean createSubscriberPayment(int userId, int year, int month) throws SQLException {
        String sql = "INSERT INTO RevenuePayment (UserID, Month, Year, Amount, PaymentDate) " +
                "( " +
                "   SELECT UserID, ?, ?, Fee, DATE(NOW()) " +
                "   FROM Users " +
                "   WHERE Users.UserID = ? " +
                ") ";
        Connection conn = DBConnection.getConnection();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, month);
            stmt.setInt(2, year);
            stmt.setInt(3, userId);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error Creating Payment");
            throw e;
        }
    }

}
