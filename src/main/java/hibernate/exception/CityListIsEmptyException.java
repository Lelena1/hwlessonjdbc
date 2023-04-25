package hibernate.exception;

public class CityListIsEmptyException extends RuntimeException {
    public CityListIsEmptyException(String message) {
        super(message);
    }
}
