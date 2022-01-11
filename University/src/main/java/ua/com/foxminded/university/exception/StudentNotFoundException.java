package ua.com.foxminded.university.exception;

public class StudentNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public StudentNotFoundException() {
        super("Students not found!");
    }
    
    public StudentNotFoundException(Throwable cause) {
        super("Students not found!", cause);
    }
    
    public StudentNotFoundException(int id) {
        super(String.format("Student with id=%d not found!", id));
    }
    
    public StudentNotFoundException(int id, Throwable cause) {
        super(String.format("Student with id=%d not found!", id), cause);
    }
 
}
