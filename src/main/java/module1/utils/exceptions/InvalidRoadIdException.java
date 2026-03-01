package module1.utils.exceptions;

public class InvalidRoadIdException extends IllegalArgumentException {
    public InvalidRoadIdException(String message) {
        super(message);
    }
}
