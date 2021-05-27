package vn.hqkhai.babymeal.exception;

public class ObjectNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 2941511122695383553L;
    private String fieldName;

    
    /**
     * Constructor of class ObjectNotFoundException
     * 
     * @param fieldName The field name
     */
    public ObjectNotFoundException(String fieldName) {
        super("Not found");
        this.fieldName = fieldName;
    }

    
    /**
     * Get field name
     * 
     * @return The name of the field
     */
    public String getFieldName() {
        return fieldName;
    }
}
