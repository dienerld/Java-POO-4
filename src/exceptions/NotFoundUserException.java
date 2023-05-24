package exceptions;

public class NotFoundUserException extends RuntimeException {
    public NotFoundUserException(String s) {
        super(s + " não encontrado.");
    }
}
