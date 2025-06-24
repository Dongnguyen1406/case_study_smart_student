create database quan_ly_hoc_sinh_codegym;
use quan_ly_hoc_sinh_codegym;

create table modules(
	module_id int primary key auto_increment,
    module_name varchar(50) not null
);

create table courses(
	course_id int primary key auto_increment,
    course_name varchar(100) not null
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
	is_delete bit(1) default 0
);

create table roles(
	role_id int primary key auto_increment,
    role_name varchar(20) not null
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
    token nvarchar(100),
    status boolean,
    student_id varchar(15),
    teacher_id varchar(15),
    foreign key(role_id) references roles(role_id),
    foreign key(student_id) references students(student_id),
    foreign key(teacher_id) references teachers(teacher_id)
);















