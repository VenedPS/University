CREATE TABLE groups
(id integer NOT NULL,
    name character(25) NOT NULL,
    CONSTRAINT groups_pkey PRIMARY KEY (id));

CREATE TABLE students
(id integer NOT NULL,
    group_id integer,
    first_name character varying(50) NOT NULL,
    second_name character varying(50) NOT NULL,
    birth_date date,
	address character varying(100),
	phone character varying(13),
	email character varying(50),
    CONSTRAINT students_pkey PRIMARY KEY (id),
    CONSTRAINT students_group_id_fkey FOREIGN KEY (group_id)
        REFERENCES groups (id)
        ON UPDATE CASCADE);

CREATE TABLE teachers
(id integer NOT NULL,
	first_name character varying(50) NOT NULL,
    second_name character varying(50) NOT NULL,
    birth_date date,
	address character varying(100),
	phone character varying(13),
	email character varying(50),
    CONSTRAINT teachers_pkey PRIMARY KEY (id));

CREATE TABLE courses
(id integer NOT NULL,
    name character varying(100) NOT NULL,
    teacher_id integer,
    CONSTRAINT courses_pkey PRIMARY KEY (id),
    CONSTRAINT courses_teacher_id_fkey FOREIGN KEY (teacher_id)
        REFERENCES teachers (id)
        ON UPDATE CASCADE);

CREATE TABLE group_courses
(group_id integer NOT NULL,
    course_id integer NOT NULL,
    CONSTRAINT group_courses_pkey PRIMARY KEY (group_id, course_id),
    CONSTRAINT group_courses_group_id_fkey FOREIGN KEY (group_id)
        REFERENCES groups (id)
        ON UPDATE CASCADE,
    CONSTRAINT group_courses_course_id_fkey FOREIGN KEY (course_id)
        REFERENCES courses (id)
        ON UPDATE CASCADE);

CREATE TABLE classrooms
(id integer NOT NULL,
    name character varying(100) NOT NULL,
    places integer,
    CONSTRAINT classrooms_pkey PRIMARY KEY (id));	
	
CREATE TABLE time_frames
(id integer NOT NULL,
    year integer NOT NULL,
    semester integer NOT NULL,
    CONSTRAINT time_frames_pkey PRIMARY KEY (id));

CREATE TABLE time_frame_lines
(id integer NOT NULL,
	time_frame_id integer NOT NULL,
    lessonNumber integer,
    frame_begin time without time zone,
	frame_end time without time zone,
    CONSTRAINT time_frame_lines_pkey PRIMARY KEY (id, time_frame_id),
    CONSTRAINT time_frame_lines_time_frame_id_fkey FOREIGN KEY (time_frame_id)
        REFERENCES time_frames (id)
        ON UPDATE CASCADE);	
	
CREATE TABLE study_calendars
(id integer NOT NULL,
    year integer NOT NULL,
    semester integer NOT NULL,
    CONSTRAINT study_calendars_pkey PRIMARY KEY (id));

CREATE TABLE study_calendar_days
(id integer NOT NULL,
	study_calendar_id integer NOT NULL,
    date date,
    dayType integer NOT NULL,
    CONSTRAINT study_calendar_days_pkey PRIMARY KEY (id, study_calendar_id),
    CONSTRAINT study_calendar_days_study_calendar_id_fkey FOREIGN KEY (study_calendar_id)
        REFERENCES study_calendars (id)
        ON UPDATE CASCADE);

CREATE TABLE timetables
(id integer NOT NULL,
    year integer NOT NULL,
    semester integer NOT NULL,
    CONSTRAINT timetables_pkey PRIMARY KEY (id));

CREATE TABLE lessons
(id integer NOT NULL,
	timetable_id integer NOT NULL,
	date date,
    lessonNumber integer,
	group_id integer,
	course_id integer,
	classroom_id integer,
	teacher_id integer,
    CONSTRAINT lesson_pkey PRIMARY KEY (id, timetable_id),
    CONSTRAINT lessons_timetable_id_fkey FOREIGN KEY (timetable_id)
        REFERENCES timetables (id)
        ON UPDATE CASCADE,
    CONSTRAINT lessons_group_id_fkey FOREIGN KEY (group_id)
        REFERENCES groups (id)
        ON UPDATE CASCADE,
    CONSTRAINT lessons_course_id_fkey FOREIGN KEY (course_id)
        REFERENCES courses (id)
        ON UPDATE CASCADE,
    CONSTRAINT lessons_teacher_id_fkey FOREIGN KEY (teacher_id)
        REFERENCES teachers (id)
        ON UPDATE CASCADE);