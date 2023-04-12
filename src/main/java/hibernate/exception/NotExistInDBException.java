package hibernate.exception;

public class NotExistInDBException extends RuntimeException {
    public NotExistInDBException(String message) {
        super(message);
    }
}
