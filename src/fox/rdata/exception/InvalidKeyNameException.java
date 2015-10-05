package fox.rdata.exception;

public class InvalidKeyNameException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String invalidKey = "";
	private boolean alreadyExists;

	public InvalidKeyNameException(String key, boolean alreadyExists) {
		super(alreadyExists ? "There is already an entry with a key name of: " + key : "Key name cannot be null or empty");
		// TODO Auto-generated constructor stub
		invalidKey = key;
		this.alreadyExists = alreadyExists;
	}

	public String getInvalidKey() {
		return invalidKey;
	}

	public boolean alreadyExists() {
		return alreadyExists;
	}

}
