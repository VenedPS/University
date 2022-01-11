package ua.com.foxminded.university.exception;

public class LessonNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public LessonNotFoundException() {
        super("Lessons not found!");
    }
    
    public LessonNotFoundException(Throwable cause) {
        super("Lessons not found!", cause);
    }
    
    public LessonNotFoundException(int id) {
        super(String.format("Lesson with id=%d not found!", id));
    }
    
    public LessonNotFoundException(int id, Throwable cause) {
        super(String.format("Lesson with id=%d not found!", id), cause);
    }
 
}
