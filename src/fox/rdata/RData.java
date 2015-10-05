package fox.rdata;

import java.io.Serializable;

public class RData extends RDataComponent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RData() {
		this("base");
		// TODO Auto-generated constructor stub
	}

	public RData(String arg1) {
		super(arg1);
		setLayer(1);
		setParent(null);
		// TODO Auto-generated constructor stub
	}

}
