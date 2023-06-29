package lk.directpay.company.exception;

public class InvalidRequestException extends RuntimeException{

    public InvalidRequestException(String message){
        super(message);
    }
}
