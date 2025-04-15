package db;

public class DBIntegrityException extends RuntimeException {
    public static final long serialVersionUID = 1L;
    public DBIntegrityException(String message){
        super(message);
    }
}