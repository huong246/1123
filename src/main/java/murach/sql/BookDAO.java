package murach.sql; // Cập nhật đúng package bạn đang dùng

import java.sql.*;

public class BookDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/murach";
    private String jdbcUsername = "root";
    private String jdbcPassword = "Orenda123";

    protected Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }

    public boolean isBookCodeExists(String bookcode) {
        boolean exists = false;
        String sql = "SELECT * FROM Book WHERE bookcode = ?";
        try (Connection conn = getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, bookcode);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                exists = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }

    public void insertBook(String bookcode, String title, String author, String category) {
        String sql = "INSERT INTO Book (bookcode, title, author, category) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, bookcode);
            statement.setString(2, title);
            statement.setString(3, author);
            statement.setString(4, category);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}