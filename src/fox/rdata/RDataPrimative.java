package fox.rdata;

import java.io.Serializable;

public class RDataPrimative extends RDataBase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Object data;
	private Class type;

	public RDataPrimative(String name) {
		super(name);
	}

	public RDataPrimative(String name, Object newData) {
		this(name);
		data = newData;
		type = data.getClass();
	}

	public RDataPrimative(String name, RDataPrimative rData) {
		this(name);
		data = rData.data;
		type = rData.type;
	}

	public Object getData() {
		return data;
	}

	public Class getType() {
		return type;
	}

	public RDataPrimative setData(Object newData) {
		data = newData;
		type = data.getClass();
		return this;
	}

	public String toString() {
		return (":(Type: " + type.getSimpleName() + ", Data: " + data.toString() + ")");
	}
}
