create database quan_ly_hoc_sinh_codegym;
use quan_ly_hoc_sinh_codegym;

create table modules(
	module_id int primary key auto_increment,
    module_name varchar(50) not null
);

create table courses(
	course_id int primary key auto_increment,
	course_id int primary key auto_increment,
    course_name varchar(100) not null
);
create table roles(
	role_id int primary key auto_increment,
    role_name varchar(20) not null
);
create table teachers(
	teacher_id varchar(15) primary key,
    teacher_name varchar(100) not null,
    dob date not null,
	gender varchar(20) not null,
	address varchar(100) not null,
	number_phone varchar(20) not null,
    email varchar(100) unique,
    `status` boolean default false,
	is_delete bit(1) default 0,
     role_id int,
    foreign key (role_id) references roles(role_id)
);



create table attendance_statuses (
	status_id int primary key auto_increment,
    status_name varchar(50) not null
);

create table classes(
	class_id int primary key auto_increment,
    class_name varchar(50) not null,
    module_id int not null,
    course_id int not null,
    teacher_id varchar(15), 
    start_date date not null,
    quantity_student int,
    is_delete bit(1) default 0,
    foreign key (module_id) references modules(module_id),
    foreign key (course_id) references courses(course_id),
    foreign key (teacher_id) references teachers(teacher_id)
);

create table students(
	student_id varchar(15) primary key,
	student_name varchar(100) not null,
	dob date not null,
	gender varchar(20) not null,
	address varchar(100) not null,
	number_phone varchar(20) not null,
	email varchar(100) unique,
	start_learn_date date not null,
	class_id int,
	`status` boolean default false,
	is_delete bit(1) default 0,
    role_id int,
    foreign key (role_id) references roles(role_id),
	foreign key (class_id) references classes(class_id)
);
 
 create table student_modules(
	student_module_id int primary key auto_increment,
	student_id varchar(15),
    module_id int,
    registration_date date,
    `status` boolean default false,
    unique (student_id, module_id),
    foreign key (student_id) references students(student_id),
    foreign key (module_id) references modules(module_id)
 );
 
create table scores(
	score_id int primary key auto_increment,
    student_module_id int,
    quiz_score double,
    practice_score double,
    average_score double,
    foreign key (student_module_id) references student_modules(student_module_id)
);

create table assessments(
	assessment_id int primary key auto_increment,
	average_score double,
    module_id int not null,
    student_id varchar(15),
    `status` boolean default false,
    foreign key (student_id) references students(student_id)
);

create table attendance(
	attendance_id int primary key auto_increment,
    student_id varchar(15) not null,
    attendance_date date not null,
    status_id int,
	foreign key (student_id) references students(student_id),
    foreign key (status_id) references attendance_statuses(status_id)
);

create table accounts(
	account_id int primary key auto_increment,
    username varchar(50) not null unique,
    password varchar(100) not null,
    role_id int,
    token varchar(100),
    status boolean,
    student_id varchar(15),
    teacher_id varchar(15),
    foreign key(role_id) references roles(role_id)
  ); 
delimiter //
create trigger trg_before_insert_scores
before insert on scores
for each row
begin 
set new.average_score=(new.quiz_score+new.practice_score)/2;
end //
delimiter ;
delimiter //
create trigger trg_before_update_scores
before update on scores
for each row
begin 
set new.average_score=(new.quiz_score+ new.practice_score)/2;
end //
delimiter ;
delimiter //
create trigger trg_before_insert_average_score
before insert on assessments
for each row
begin
 if new.average_score>=7.5 then
 set new.status = true;
else 
set new.status=false;
end if;
end //
delimiter ;
delimiter //
create trigger trg_before_update_average_score
before update on assessments
for each row
begin
 if new.average_score>=7.5 then
 set new.status = true;
else 
set new.status=false;
end if;
end //
delimiter ;


-- Thêm dữ liệu mẫu
INSERT INTO modules (module_name) VALUES 
('Module 1'), ('Module 2'), ('Module 3'),
('Module 4'), ('Module 5'), ('Module 6');

INSERT INTO courses (course_name) VALUES 
('Full-stack'), 
('Back-end'), 
('Front-end');

INSERT INTO attendance_statuses (status_name) VALUES 
('Vắng không phép'), 
('Vắng có phép'), 
('Có mặt');

INSERT INTO roles (role_name) VALUES 
('admin'), 
('student'), 
('teacher');

INSERT INTO teachers (teacher_id, teacher_name, dob, gender, address, number_phone, email, status,role_id )VALUES
('GV001', 'Nguyễn Văn A', '1980-05-10', 'Nam', 'Hà Nội', '0901234567', 'a@gmail.com', true,3),
('GV002', 'Trần Thị B', '1985-08-20', 'Nữ', 'Đà Nẵng', '0912345678', 'b@gmail.com', true,3);

INSERT INTO classes (class_name, module_id, course_id, teacher_id, start_date, quantity_student) VALUES
('C0225G1', 1, 1, 'GV001', '2025-01-01', 10),
('C0325G1', 2, 2, 'GV002', '2025-03-01', 10);

-- Lớp C0225G1 - class_id = 1
INSERT INTO students (student_id, student_name, dob, gender, address, number_phone, email, start_learn_date, class_id,role_id) VALUES
('HS001', 'Lê Văn A', '2005-05-01', 'Nam', 'HCM', '0911111111', 'hs001@gmail.com', '2025-01-01', 1,2),
('HS002', 'Lê Văn B', '2005-05-02', 'Nam', 'HCM', '0911111112', 'hs002@gmail.com', '2025-01-01', 1,2),
('HS003', 'Lê Văn C', '2005-05-03', 'Nam', 'HCM', '0911111113', 'hs003@gmail.com', '2025-01-01', 1,2),
('HS004', 'Lê Văn D', '2005-05-04', 'Nam', 'HCM', '0911111114', 'hs004@gmail.com', '2025-01-01', 1,2),
('HS005', 'Lê Văn E', '2005-05-05', 'Nam', 'HCM', '0911111115', 'hs005@gmail.com', '2025-01-01', 1,2),
('HS006', 'Lê Văn F', '2005-05-06', 'Nam', 'HCM', '0911111116', 'hs006@gmail.com', '2025-01-01', 1,2),
('HS007', 'Lê Văn G', '2005-05-07', 'Nam', 'HCM', '0911111117', 'hs007@gmail.com', '2025-01-01', 1,2),
('HS008', 'Lê Văn H', '2005-05-08', 'Nam', 'HCM', '0911111118', 'hs008@gmail.com', '2025-01-01', 1,2),
('HS009', 'Lê Văn I', '2005-05-09', 'Nam', 'HCM', '0911111119', 'hs009@gmail.com', '2025-01-01', 1,2),
('HS010', 'Lê Văn J', '2005-05-10', 'Nam', 'HCM', '0911111120', 'hs010@gmail.com', '2025-01-01', 1,2);

-- Lớp C0325G1 - class_id = 2
INSERT INTO students (student_id, student_name, dob, gender, address, number_phone, email, start_learn_date, class_id,role_id) VALUES
('HS011', 'Trần Thị A', '2005-06-01', 'Nữ', 'HCM', '0921111111', 'hs011@gmail.com', '2025-03-01', 2,2),
('HS012', 'Trần Thị B', '2005-06-02', 'Nữ', 'HCM', '0921111112', 'hs012@gmail.com', '2025-03-01', 2,2),
('HS013', 'Trần Thị C', '2005-06-03', 'Nữ', 'HCM', '0921111113', 'hs013@gmail.com', '2025-03-01', 2,2),
('HS014', 'Trần Thị D', '2005-06-04', 'Nữ', 'HCM', '0921111114', 'hs014@gmail.com', '2025-03-01', 2,2),
('HS015', 'Trần Thị E', '2005-06-05', 'Nữ', 'HCM', '0921111115', 'hs015@gmail.com', '2025-03-01', 2,2),
('HS016', 'Trần Thị F', '2005-06-06', 'Nữ', 'HCM', '0921111116', 'hs016@gmail.com', '2025-03-01', 2,2),
('HS017', 'Trần Thị G', '2005-06-07', 'Nữ', 'HCM', '0921111117', 'hs017@gmail.com', '2025-03-01', 2,2),
('HS018', 'Trần Thị H', '2005-06-08', 'Nữ', 'HCM', '0921111118', 'hs018@gmail.com', '2025-03-01', 2,2),
('HS019', 'Trần Thị I', '2005-06-09', 'Nữ', 'HCM', '0921111119', 'hs019@gmail.com', '2025-03-01', 2,2),
('HS020', 'Trần Thị J', '2005-06-10', 'Nữ', 'HCM', '0921111120', 'hs020@gmail.com', '2025-03-01', 2,2);

-- Thêm dữ liệu đăng ký học module cho học sinh
INSERT INTO student_modules (student_id, module_id, registration_date, status) VALUES
('HS001', 1, '2025-01-05', true),
('HS002', 1, '2025-01-05', true),
('HS011', 2, '2025-03-05', true),
('HS012', 2, '2025-03-05', true);

-- Tài khoản giáo viên
INSERT INTO accounts (username, password, role_id, status, teacher_id) VALUES
('gv001', 'pass123', 3, true, 'GV001'),
('gv002', 'pass123', 3, true, 'GV002');

-- Tài khoản học sinh
INSERT INTO accounts (username, password, role_id, status, student_id) VALUES
('hs001', 'pass123', 2, true, 'HS001'),
('hs011', 'pass123', 2, true, 'HS011');
select * from roles;
-- Tài khoản admin
INSERT INTO accounts (username, password, role_id, status) VALUES
('admin', 'admin123', 1, true);

-- HS001
INSERT INTO attendance (student_id, attendance_date, status_id) VALUES
('HS001', '2025-06-20', 3),  -- Có mặt
('HS001', '2025-06-21', 2),  -- Vắng có phép
('HS001', '2025-06-22', 1);  -- Vắng không phép

-- HS002
INSERT INTO attendance (student_id, attendance_date, status_id) VALUES
('HS002', '2025-06-20', 3),
('HS002', '2025-06-21', 3),
('HS002', '2025-06-22', 3);

-- HS011
INSERT INTO attendance (student_id, attendance_date, status_id) VALUES
('HS011', '2025-06-20', 2),
('HS011', '2025-06-21', 2),
('HS011', '2025-06-22', 3);

-- HS012
INSERT INTO attendance (student_id, attendance_date, status_id) VALUES
('HS012', '2025-06-20', 1),
('HS012', '2025-06-21', 1),
('HS012', '2025-06-22', 3);

INSERT INTO scores (student_module_id, quiz_score, practice_score) VALUES
(1, 8.0, 9.0),
(2, 7.5, 8.5 ),
(3, 9.0, 8.0),
(4, 6.0, 7.0);

INSERT INTO assessments (average_score, module_id, student_id, status) VALUES
(8.5, 1, 'HS001', true),
(8.0, 1, 'HS002', true),
(8.5, 2, 'HS011', true),
(6.5, 2, 'HS012', false);  -- Chưa đạt

-- Query
-- Xem bảng điểm danh của một học sinh 
SELECT 
    s.student_id,
    s.student_name,
    m.module_name,
    COUNT(CASE WHEN a.status_id = 1 THEN 1 END) AS so_ngay_vang_khong_phep,
    CASE 
        WHEN COUNT(CASE WHEN a.status_id = 1 THEN 1 END) >= 3 THEN 'Không được đi thi'
        ELSE 'Đủ điều kiện'
    END AS ket_qua_du_thi
FROM student_modules sm
JOIN students s ON sm.student_id = s.student_id
JOIN modules m ON sm.module_id = m.module_id
LEFT JOIN attendance a 
    ON sm.student_id = a.student_id 
    AND a.attendance_date >= sm.registration_date
where s.student_id = 'HS001'
GROUP BY s.student_id, s.student_name, m.module_name;

-- xem bảng đánh giá của tất cả học sinh hoặc một học sinh
SELECT 
    s.student_id,
    s.student_name,
    m.module_name,
    a.average_score,
    CASE 
        WHEN a.status = true THEN 'Đạt'
        ELSE 'Chưa đạt'
    END AS ket_qua_danh_gia
FROM assessments a
JOIN students s ON a.student_id = s.student_id
JOIN modules m ON a.module_id = m.module_id
WHERE s.student_id = 'HS001';
 select *from classes;
 select * from students;
 
 
select s.student_name,s.dob,s.gender,s.address,s.number_phone,s.email,s.start_learn_date, c.class_name from students s join classes c on s.class_id=c.class_id join accounts a on s.student_id=a.student_id where a.username="hs001";
 update students  set student_name="NGUYEN THI THU", dob='2001-12-01' ,gender='nữ',address='Đà Nẵng',number_phone='0912234566',email='le171123@gmail.com'where student_id='HS009';
 select * from accounts;
 select * from students;
 select * from scores;
 select * from attendance;
 select * from attendance_statuses;
 select * from student_modules;
 select* from assessments;
 
 
select s.student_name, atts.status_name,ass.status  from students s
 join  attendance att on s.student_id=att.student_id
 join attendance_statuses atts on att.status_id=atts.status_id
 join assessments ass on s.student_id=ass.student_id;

 
 select s.student_id,m.module_name, sc.quiz_score, sc.practice_score,sc.average_score 
 from students s 
 join student_modules sm on s.student_id= sm.student_id
 join modules m on sm.module_id=m.module_id
 join scores sc on sm.student_module_id=sc.student_module_id 
 join accounts a on s.student_id=a.student_id where a.username="hs011";
 
DELIMITER //

CREATE PROCEDURE check_exam_eligibility(IN in_student_id VARCHAR(15))
BEGIN
    DECLARE latest_module_id INT;
    DECLARE start_date DATE;
    DECLARE absent_count INT;
    DECLARE module_name_value VARCHAR(100);

    -- Bước 1: Lấy module_id mới nhất, ngày đăng ký, module_name
    SELECT sm.module_id, sm.registration_date, m.module_name
    INTO latest_module_id, start_date, module_name_value
    FROM student_modules sm
    JOIN modules m ON sm.module_id = m.module_id
    WHERE sm.student_id = in_student_id
    ORDER BY sm.module_id DESC
    LIMIT 1;

    -- Bước 2: Đếm số buổi vắng không phép từ ngày đăng ký đến hiện tại
    SELECT COUNT(*)
    INTO absent_count
    FROM attendance
    WHERE student_id = in_student_id
      AND attendance_date >= start_date
      AND status_id = 1; -- 1 = Vắng không phép

    -- Bước 3: Trả kết quả đầy đủ
    SELECT
        in_student_id AS student_id,
        latest_module_id AS module_id,
        module_name_value AS module_name,
        start_date AS registration_date,
        absent_count AS unexcused_absences,
        CASE
            WHEN absent_count > 3 THEN 'Không được thi'
            ELSE 'Được thi'
        END AS result ;
END;
//

DELIMITER ;
CALL check_exam_eligibility('HS011');

select c.class_name, m.module_name, co.course_name, t.teacher_name , c.start_date, c.quantity_student
from classes c
join modules m on c.module_id = m.module_id
join courses co on co.course_id = c.course_id
join teachers t on t.teacher_id = c.teacher_id
where c.is_delete = false and t.is_delete = false;




