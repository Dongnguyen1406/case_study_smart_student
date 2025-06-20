create database quan_ly_hoc_sinh_codegym;
use quan_ly_hoc_sinh_codegym;

create table students(
 student_id varchar(15) primary key auto_increment,
 student_name varchar(100),
 dob date,
 gender varchar(20),
 address varchar(100),
 number_phone varchar(20),
 start_learn_date date,
 module varchar(15),
 course varchar(100),
 score double,
 status boolean default false,
 is_delete bit(1) default 0
 );
 
create table scores(
	score_id int primary key auto_increment,
    student_id varchar(15),
    quiz_score double,
    practice_score double,
    average_score double,
    foreign key (student_id) references students(student_id)
);

create table assessments(
	assessment_id int primary key auto_increment,
	average_score double,
    module_id int,
    student_id varchar(15),
    status boolean default false,
    foreign key (student_id) references students(student_id)
);

create table attendance(
	attendance_id int primary key auto_increment,
    student_id varchar(15),
    attendance_date date,
    status boolean default false,
	foreign key (student_id) references students(student_id)
)


