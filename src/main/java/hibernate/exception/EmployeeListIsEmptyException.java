package hibernate.exception;

public class EmployeeListIsEmptyException extends RuntimeException {
    public EmployeeListIsEmptyException(String message) {
        super(message);
    }
}
