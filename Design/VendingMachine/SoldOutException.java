package Amazon_Prepare.VendingMachine;

public class SoldOutException extends RuntimeException{
    private String message;

    public SoldOutException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
