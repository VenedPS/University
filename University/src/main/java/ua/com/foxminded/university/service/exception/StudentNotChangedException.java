package ua.com.foxminded.university.service.exception;

public class StudentNotChangedException extends Exception{
    private static final long serialVersionUID = 1L;

    public StudentNotChangedException(String message, Throwable cause) {
        super(message, cause);
    }

    public StudentNotChangedException(String message) {
        super(message);
    }

    public StudentNotChangedException(Throwable cause) {
        super(cause);
    }
 
}
