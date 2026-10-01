package dao;

import config.DatabaseConnection;
import model.TicketBooking;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketBookingDAO {
    //1. Lấy danh sách tất cả phiếu
    public List<TicketBooking> getAllBookings() {
        List<TicketBooking> list = new ArrayList<>();
        String sql  = "SELECT * FROM get_all_ticket_bookings()";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(ticketBookingMapper(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //2. Thêm mới một phiếu đặt vé
    public void insertBooking(TicketBooking booking) {
        String sql  = "call insert_ticket_booking(?,?,?,?,?,?)";
        try (Connection conn = DatabaseConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setString(1, booking.getMovieTitle());
            stmt.setString(2, booking.getCustomerName());
            stmt.setTimestamp(3, booking.getShowTime());
            stmt.setTimestamp(4, booking.getBookingDate());
            stmt.setInt(5, booking.getSeatQuantity());
            stmt.setString(6, booking.getStatus());
            stmt.execute();

            System.out.println("-> Thêm mới phiếu đặt vé thành công");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //3. Cập nhật phiếu đặt vé
    public void updateBooking(TicketBooking booking) {
        String sql  = "call update_ticket_booking(?,?,?,?,?,?)";
        try (Connection conn = DatabaseConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setString(1, booking.getMovieTitle());
            stmt.setString(2, booking.getCustomerName());
            stmt.setTimestamp(3, booking.getShowTime());
            stmt.setTimestamp(4, booking.getBookingDate());
            stmt.setInt(5, booking.getSeatQuantity());
            stmt.setString(6, booking.getStatus());
            stmt.execute();

            System.out.println("-> Cập nhật phiếu đặt vé thành công");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //4. Xóa phiếu đặt vé
    public void deleteBooking(int bookingId) {
        String sql  = "call delete_ticket_booking(?)";
        try (Connection conn = DatabaseConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setInt(1, bookingId);
            stmt.execute();

            System.out.println("-> Xóa phiếu đặt vé thành công");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //5.1. Tìm kiếm phiếu đặt vé theo tên phim
    public List<TicketBooking> searchByCustomerName(String customerName) {
        List<TicketBooking> list = new ArrayList<>();
        String sql  = "SELECT * FROM get_bookings_by_customer_name(?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, customerName);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(ticketBookingMapper(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //5.2. Tìm kiếm phiếu đặt vé theo tên khách
    public List<TicketBooking> searchByMovieTitle(String title) {
        List<TicketBooking> list = new ArrayList<>();
        String sql  = "SELECT * FROM search_bookings_by_movie_title(?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, title);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(ticketBookingMapper(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    private TicketBooking ticketBookingMapper(ResultSet rs) throws SQLException {
        return new TicketBooking(
                rs.getInt("booking_id"),
                rs.getString("movie_title"),
                rs.getString("movie_title"),
                rs.getTimestamp("show_time"),
                rs.getTimestamp("booking_date"),
                rs.getInt("seat_quantity"),
                rs.getString("status")
        );
    }
}
