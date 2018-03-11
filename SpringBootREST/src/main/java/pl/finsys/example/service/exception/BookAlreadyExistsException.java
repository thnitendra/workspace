package pl.finsys.example.service.exception;

/**
 * Created by nitendra.thakur on 2/13/18.
 */
public class BookAlreadyExistsException extends RuntimeException {
    public BookAlreadyExistsException(String format) {
        super(format);
    }
}
