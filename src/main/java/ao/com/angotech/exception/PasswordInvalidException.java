package ao.com.angotech.exception;

public class PasswordInvalidException extends RuntimeException{

    public PasswordInvalidException(String msg) {
        super(msg);
    }
}
