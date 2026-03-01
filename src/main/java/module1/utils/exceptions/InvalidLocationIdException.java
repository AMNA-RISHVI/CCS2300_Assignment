package module1.utils.exceptions;

public class InvalidLocationIdException extends IllegalArgumentException {
    public InvalidLocationIdException(String message) {
        super(message);
    }
}
