package fox.rdata.exception;

public class KeyNameNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String keyName;

	public KeyNameNotFoundException(String key) {
		super("No data exists with key name: " + key);
		keyName = key;
	}

	public String getKeyName() {
		return keyName;
	}

}
