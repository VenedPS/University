package ua.com.foxminded.university.exception;

public class GroupNotChangedException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public GroupNotChangedException() {
        super("Groups not changed!");
    }
    
    public GroupNotChangedException(Throwable cause) {
        super("Groups not changed!", cause);
    }
    
    public GroupNotChangedException(int id) {
        super(String.format("Group with id=%d not changed!", id));
    }
    
    public GroupNotChangedException(int id, Throwable cause) {
        super(String.format("Group with id=%d not changed!", id), cause);
    }
 
}
