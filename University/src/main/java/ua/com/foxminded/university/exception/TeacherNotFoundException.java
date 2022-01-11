package ua.com.foxminded.university.exception;

public class TeacherNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public TeacherNotFoundException() {
        super("Teachers not found!");
    }
    
    public TeacherNotFoundException(Throwable cause) {
        super("Teachers not found!", cause);
    }
    
    public TeacherNotFoundException(int id) {
        super(String.format("Teacher with id=%d not found!", id));
    }
    
    public TeacherNotFoundException(int id, Throwable cause) {
        super(String.format("Teacher with id=%d not found!", id), cause);
    }
 
}
