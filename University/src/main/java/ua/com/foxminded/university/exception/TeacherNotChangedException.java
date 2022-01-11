package ua.com.foxminded.university.exception;

public class TeacherNotChangedException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public TeacherNotChangedException() {
        super("Teachers not changed!");
    }
    
    public TeacherNotChangedException(Throwable cause) {
        super("Teachers not changed!", cause);
    }
    
    public TeacherNotChangedException(int id) {
        super(String.format("Teacher with id=%d not changed!", id));
    }
    
    public TeacherNotChangedException(int id, Throwable cause) {
        super(String.format("Teacher with id=%d not changed!", id), cause);
    }
 
}
