package controller;

import service.TicketBookingService;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        TicketBookingService service = new TicketBookingService();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do{
            System.out.println("=======================================================");
            System.out.println("           CINEMA TICKET BOOKING MANAGEMENT            ");
            System.out.println("=======================================================");
            System.out.println("1. Danh sách tất cả phiếu đặt vé");
            System.out.println("2. Thêm mới phiếu đặt vé");
            System.out.println("3. Cập nhật thông tin phiếu đặt vé");
            System.out.println("4. Xóa phiếu đặt vé");
            System.out.println("5. Tìm kiếm phiếu đặt vé theo tên khách hàng");
            System.out.println("6. Tìm kiếm phiếu đặt vé theo tên phim");
            System.out.println("7. Thoát");
            System.out.println("Chọn chức năng (1-7): ");

            try{
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e){
                choice = -1;
            }

            switch (choice) {
                case 1:
                    service.displayAllBookings();
                    break;
                    case 2:
                        service.addNewBooking();
                        break;
                        case 3:
                            service.updateBooking();
                            break;
                case 4:
                    service.deleteBooking();
                    break;
                case 5:
                    service.searchByCustomer();
                    break;
                case 6:
                    service.searchByMovie();
                    break;
                case 7:
                    System.out.println("Thoát chương trình...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("-> Lựa chọn không hợp lệ! Vui lòng chọn từ 1-7.");
            }
        }while(true);
    }
}
