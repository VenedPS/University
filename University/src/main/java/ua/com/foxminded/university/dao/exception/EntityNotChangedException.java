package ua.com.foxminded.university.dao.exception;

public class EntityNotChangedException extends Exception{
    private static final long serialVersionUID = 1L;

    public EntityNotChangedException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityNotChangedException(String message) {
        super(message);
    }

    public EntityNotChangedException(Throwable cause) {
        super(cause);
    }
 
}
