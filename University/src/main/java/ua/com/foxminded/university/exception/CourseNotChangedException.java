package ua.com.foxminded.university.exception;

public class CourseNotChangedException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public CourseNotChangedException() {
        super("Courses not changed!");
    }
    
    public CourseNotChangedException(Throwable cause) {
        super("Courses not changed!", cause);
    }
    
    public CourseNotChangedException(int id) {
        super(String.format("Course with id=%d not changed!", id));
    }
    
    public CourseNotChangedException(int id, Throwable cause) {
        super(String.format("Course with id=%d not changed!", id), cause);
    }
 
}
