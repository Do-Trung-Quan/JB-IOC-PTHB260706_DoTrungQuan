create table ticket_bookings(
	booking_id serial Primary key,
	movie_title varchar(150) not null,
	customer_name varchar(100) not null,
	booking_date timestamp not null,
	seat_quantity int not null,
	status varchar(30) not null
)

-- 1. function thực hiện lấy danh sách tất cả phiếu đặt vé
create or replace function get_all_ticket_bookings()
returns setof ticket_bookings as $$
begin
	return query select * from ticket_bookings;
end;
$$ language plpgsql;

--2.procedure thực hiện thêm mới 1 phiếu đặt vé
create or replace procedure insert_ticket_booking(
	p_movie_title varchar(150),
	p_customer_name varchar(100),
	p_show_time timestamp,
	p_booking_date timestamp,
	p_seat_quantity int,
	p_status varchar(30)
)
language plpgsql
as $$
begin
	insert into ticket_bookings (movie_title, customer_name, show_time, booking_date, seat_quantity, status)
	values (p_movie_title, p_customer_name, p_show_time, p_booking_date, p_seat_quantity, p_status);
end;
$$;

--3. function thực hiện lấy danh sách phiếu đặt vé theo tên khách hàng
create or replace function get_booking_by_customer_name(p_customer_name varchar)
returns setof ticket_bookings as $$
begin
	return query
	select * from ticket_bookings
	where customer_name ilike '%' || p_customer_name || '%';
end;
$$ language plpgsql;

--4. procedure thực hiện cập nhật thông tin phiếu đặt vé theo tên khách hàng
create or replace procedure update_ticket_booking(
	p_booking_id INT,
	p_movie_title varchar(150),
	p_customer_name varchar(100),
	p_show_time timestamp,
	p_booking_date timestamp,
	p_seat_quantity int,
	p_status varchar(30)
)
language plpgsql
as $$
begin
	update ticket_bookings
	set movie_title = p_movie_title,
		customer_name = p_customer_name,
		show_time = p_show_time,
		booking_date = p_booking_date,
		seat_quantity = p_seat_quantity,
		status = p_status
	where booking_id = p_booking_id;
end;
$$;

--5. procedure thực hiện xóa phiếu đặt vé theo booking_id
create or replace procedure delete_ticket_booking(
	p_booking_id int
)
language plpgsql
as $$
begin
	delete from ticket_bookings
	where booking_id = p_booking_id;
end;
$$;

--6. function tìm kiếm phiếu đặt vé theo tên phim
create or replace function search_bookings_by_movie_title(p_movie_title varchar)
returns setof ticket_bookings as $$
begin
	return query
	select * from ticket_bookings
	where movie_title ilike '%' || p_movie_title || '%';
end;
$$ language plpgsql;