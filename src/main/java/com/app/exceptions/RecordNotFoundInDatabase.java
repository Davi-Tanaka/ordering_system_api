package com.app.exceptions;

/**
 * Extends ${@link  Exception}. Used to indicate that a record was not found in a database table.
 */
public class RecordNotFoundInDatabase extends Exception {

    public RecordNotFoundInDatabase(String cause) {
        super(cause);
    }
    
    public RecordNotFoundInDatabase() {
        super("Data Not Found.");
    }
}
