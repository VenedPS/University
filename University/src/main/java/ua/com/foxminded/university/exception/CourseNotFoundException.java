package ua.com.foxminded.university.exception;

public class CourseNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public CourseNotFoundException() {
        super("Courses not found!");
    }
    
    public CourseNotFoundException(Throwable cause) {
        super("Courses not found!", cause);
    }
    
    public CourseNotFoundException(int id) {
        super(String.format("Course with id=%d not found!", id));
    }
    
    public CourseNotFoundException(int id, Throwable cause) {
        super(String.format("Course with id=%d not found!", id), cause);
    }
 
}
