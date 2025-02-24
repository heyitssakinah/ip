package abuhurairah.task;

public class CustomException extends Exception {
    private String ErrorMessage;

    public CustomException(String message) {
        super(message);
    }
}
