package ua.com.foxminded.university.service.exception;

public class StudentNotFoundException extends Exception{
    private static final long serialVersionUID = 1L;

    public StudentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public StudentNotFoundException(String message) {
        super(message);
    }

    public StudentNotFoundException(Throwable cause) {
        super(cause);
    }
 
}
