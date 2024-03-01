package test.testamazon.exceptions;

public class ExpiredJwtException extends RuntimeException {
    public ExpiredJwtException(String msg) {
        super(msg);
    }
}
