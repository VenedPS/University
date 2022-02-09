package ua.com.foxminded.university.exception;

public class GroupNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public GroupNotFoundException() {
        super("Groups not found!");
    }
    
    public GroupNotFoundException(Throwable cause) {
        super("Groups not found!", cause);
    }
    
    public GroupNotFoundException(int id) {
        super(String.format("Group with id=%d not found!", id));
    }
    
    public GroupNotFoundException(int id, Throwable cause) {
        super(String.format("Group with id=%d not found!", id), cause);
    }
 
}
