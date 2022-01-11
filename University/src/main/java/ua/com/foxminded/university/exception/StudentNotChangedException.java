package ua.com.foxminded.university.exception;

public class StudentNotChangedException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public StudentNotChangedException() {
        super("Students not changed!");
    }
    
    public StudentNotChangedException(Throwable cause) {
        super("Students not changed!", cause);
    }
    
    public StudentNotChangedException(int id) {
        super(String.format("Student with id=%d not changed!", id));
    }
    
    public StudentNotChangedException(int id, Throwable cause) {
        super(String.format("Student with id=%d not changed!", id), cause);
    }
 
}
