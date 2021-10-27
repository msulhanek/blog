package sk.posam.blog.exception;

public class PostNotFondException extends RuntimeException {
    public PostNotFondException(String message) {
        super(message);
    }
}
