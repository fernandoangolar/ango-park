package ao.com.angotech.exception;

public class UsernameUniqueViolationException extends RuntimeException{

    public UsernameUniqueViolationException(String msg) {
        super(msg);
    }
}
