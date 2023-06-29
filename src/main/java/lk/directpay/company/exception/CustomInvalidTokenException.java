package lk.directpay.company.exception;

public class CustomInvalidTokenException extends RuntimeException{

    public CustomInvalidTokenException(String message){
        super(message);
    }
}
