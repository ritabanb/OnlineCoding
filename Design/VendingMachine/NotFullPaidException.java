package Amazon_Prepare.VendingMachine;

public class NotFullPaidException extends RuntimeException {
    private String message;
    private long remaining;

    public NotFullPaidException(String message, long remaining) {
        this.message = message;
        this.remaining = remaining;
    }

    public long getRemaining() {
        return remaining;
    }

    public String getMessage() {
        return message + remaining;
    }
}
