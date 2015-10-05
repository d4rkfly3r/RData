package fox.rdata;

import java.io.Serializable;

public abstract class RDataBase implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public final String name;
	private int layer;
	private RDataStructure parent;

	public RDataBase(String name) {
		if (name != null) {
			this.name = name.trim();
		} else {
			this.name = null;
		}
	}

	public RDataBase setLayer(int layer) {
		this.layer = layer;
		return this;
	}

	public RDataBase setParent(RDataStructure parent) {
		this.parent = parent;
		return this;
	}

	public int getLayer() {
		return layer;
	}

	public RDataStructure getParent() {
		return parent;
	}
}
