package service;

import dao.TicketBookingDAO;
import model.TicketBooking;

import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;

public class TicketBookingService {
    private final TicketBookingDAO bookingDAO = new TicketBookingDAO();
    private final Scanner scanner = new Scanner(System.in);

    public void displayAllBookings() {
        List<TicketBooking> list = bookingDAO.getAllBookings();
        if(list.isEmpty()){
            System.out.println("-> Không có phiếu đặt vé nào trong hệ thống.");
            return;
        }
        printHeader();
        for (TicketBooking b : list){
            System.out.println(b.toString());
        }
        printFooter();
    }

    public void addNewBooking() {
        System.out.println("--- THÊM MỚI PHIẾU ĐẶT VÉ ---");
        String movieTitle = validateString("Nhập tên phim: ");
        String customerName = validateString("Nhập tên khách hàng: ");
        Timestamp showtime = validateTimestamp("Nhập suất chiếu (yyyy-mm-dd hh:mm:ss): ");
        Timestamp bookingDate =  new Timestamp(System.currentTimeMillis());
        int seatQuantity = validateInt("Nhập số lượng ghế đặt: ", 1, Integer.MAX_VALUE);
        String status = validateStatus();

        TicketBooking booking = new TicketBooking(0, movieTitle, customerName, showtime, bookingDate, seatQuantity, status);
        bookingDAO.insertBooking(booking);
    }

    public void updateBooking() {
        System.out.println("--- CẬP NHẬT THÔNG TIN PHIẾU ĐẶT VÉ ---");
        int bookingId = validateInt("Nhập ID phiếu đặt vé cần sửa: ", 1, Integer.MAX_VALUE);

        String movieTitle = validateString("Nhập tên phim mới: ");
        String customerName = validateString("Nhập tên khách hàng mới: ");
        Timestamp showtime = validateTimestamp("Nhập suất chiếu mới(yyyy-mm-dd hh:mm:ss): ");
        Timestamp bookingDate =  new Timestamp(System.currentTimeMillis());
        int seatQuantity = validateInt("Nhập số lượng ghế đặt mới: ", 1, Integer.MAX_VALUE);
        String status = validateStatus();

        TicketBooking booking = new TicketBooking(bookingId, movieTitle, customerName, showtime, bookingDate, seatQuantity, status);
        bookingDAO.updateBooking(booking);
    }

    public void deleteBooking() {
        System.out.println("--- XÓA PHIẾU ĐẶT VÉ ---");
        int bookingId = validateInt("Nhập ID phiếu đặt vé cần xóa: ", 1, Integer.MAX_VALUE);

        System.out.println("Bạn có chắc chắn muốn xóa phiếu có ID: "+bookingId+" không? (Y/N): ");
        String confirm = scanner.nextLine().trim();
        if(confirm.equalsIgnoreCase("Y")) {
            bookingDAO.deleteBooking(bookingId);
        }else {
            System.out.println("-> Đã hủy thao tác xóa.");
        }
    }

    public void searchByCustomer() {
        System.out.println("--- TÌM KIẾM THEO TÊN KHÁCH HÀNG ---");
        String name = validateString("Nhập tên khách hàng cần tìm: ");
        List<TicketBooking> list = bookingDAO.searchByCustomerName(name);
        if(list.isEmpty()){
            System.out.println("-> Không tìm thấy kết quả phù hợp.");
            return;
        }
        printHeader();
        for (TicketBooking b : list){
            System.out.println(b.toString());
        }
        printFooter();
    }

    public void searchByMovie() {
        System.out.println("--- TÌM KIẾM THEO TÊN PHIM ---");
        String title = validateString("Nhập tên phim cần tìm: ");
        List<TicketBooking> list = bookingDAO.searchByMovieTitle(title);
        if(list.isEmpty()){
            System.out.println("-> Không tìm thấy kết quả phù hợp.");
            return;
        }
        printHeader();
        for (TicketBooking b : list){
            System.out.println(b.toString());
        }
        printFooter();
    }

    //Các hàm validate dữ liệu
    private String validateString(String message) {
        String input;
        while (true) {
            System.out.print(message);
            input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("-> Lỗi: Không được để trống. Vui lòng nhập lại!");
        }
    }

    private int validateInt(String message, int min, int max) {
        int value;
        while (true) {
            System.out.print(message);
            try {
                value = Integer.parseInt(scanner.nextLine().trim());
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.println("-> Lỗi: Giá trị phải nằm trong khoảng hợp lệ!");
            } catch (NumberFormatException e){
                System.out.println("-> Lỗi: Vui lòng nhập một số nguyên hợp lệ!");
            }
        }
    }

    private Timestamp validateTimestamp(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();
            try {
                return Timestamp.valueOf(input);
            } catch (IllegalArgumentException e){
                System.out.println("-> Lỗi định dạng thời gian. Hãy nhập theo mẫu: yyyy-mm-dd hh:mm:ss");
            }
        }
    }

    private String validateStatus() {
        while (true) {
            System.out.print("Nhập trạng thái (Booked / Cancelled / CheckedIn): ");
            String status = scanner.nextLine().trim();
            if(status.equalsIgnoreCase("Booked") || status.equalsIgnoreCase("Cancelled") || status.equalsIgnoreCase("CheckedIn")) {
                return status;
            }
            System.out.println("-> Lỗi: Trạng thái không hợp lệ! (Booked/Cancelled/CheckedIn)");
        }
    }

    private void printHeader() {
        System.out.println("+---------------------------------------------------------------------------------+");
        System.out.printf("| %-10s | %-20s | %-15s | %-20s | %-20s | %-12s | %-12s |\n",
                "ID", "Tên Phim", "Khách Hàng", "Suất Chiếu", "Ngày Đặt", "Số Lượng Ghế", "Trạng Thái");
        System.out.println("+---------------------------------------------------------------------------------+");
    }

    private void printFooter() {
        System.out.println("+---------------------------------------------------------------------------------+");
    }
}
