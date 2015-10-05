package fox.rdata;

import java.io.Serializable;

public abstract class RDataStructure extends RDataBase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected RDataBase[] dataList;

	public RDataStructure(String arg1) {
		super(arg1);
		// TODO Auto-generated constructor stub
	}

	// public abstract RDataStructure addData(RDataBase data) throws
	// InvalidKeyNameException;

	public int getSize() {
		return dataList.length;
	}
}
