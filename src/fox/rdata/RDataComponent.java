package fox.rdata;

import java.io.Serializable;

import fox.rdata.exception.InvalidKeyNameException;
import fox.rdata.exception.KeyNameNotFoundException;

public class RDataComponent extends RDataStructure implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RDataComponent(String arg1) {
		super(arg1);
		// TODO Auto-generated constructor stub
	}

	public RDataComponent addData(RDataBase data) throws InvalidKeyNameException {
		if (data == null) {
			throw new NullPointerException("RDataBase object cannot be null");
		}
		if (data.name == null || data.name.trim() == "") {
			throw new InvalidKeyNameException(data.name, false);
		}
		if (dataList != null && dataList.length > 0) {
			for (int i = 0; i < dataList.length; i++) {
				if (dataList[i].name.equalsIgnoreCase(data.name)) {
					throw new InvalidKeyNameException(data.name, true);
				}
			}
			RDataBase[] tempList = new RDataBase[dataList.length + 1];
			// TODO If misbehaving, increase initial memory allocation to
			// prevent overflows.
			for (int i = 0; i < dataList.length; i++) {
				tempList[i] = dataList[i];
			}
			data.setLayer(getLayer() + 1);
			data.setParent(this);
			tempList[dataList.length] = data;
			dataList = tempList;

		} else {
			dataList = new RDataBase[1];
			dataList[0] = data;
		}
		// TODO Auto-generated method stub
		return this;
	}

	public RDataBase getData(String key) throws KeyNameNotFoundException, InvalidKeyNameException {
		checkDataListExists();
		checkKeyName(key);
		for (int i = 0; i < dataList.length; i++) {
			if (dataList[i].name.equalsIgnoreCase(key.trim())) {
				return dataList[i];
			}
		}
		throw new KeyNameNotFoundException(key);
	}

	public RDataComponent removeData(String key) throws InvalidKeyNameException, KeyNameNotFoundException {
		checkDataListExists();
		checkKeyName(key);
		boolean keyFound = false;
		RDataBase[] tempList = new RDataBase[dataList.length - 1];
		for (int i = 0; i < dataList.length; i++) {
			if (dataList[i].name.equalsIgnoreCase(key.trim())) {
				keyFound = true;
				break;
			}
		}
		if (!keyFound) {
			throw new KeyNameNotFoundException(key);
		}
		keyFound = false;
		for (int i = 0; i < dataList.length; i++) {
			if (keyFound) {
				tempList[i - 1] = dataList[i];
			} else if (i + 1 < dataList.length) {
				tempList[i] = dataList[i];
			}
			if (dataList[i].name.equalsIgnoreCase(key.trim())) {
				keyFound = true;
			}
		}
		dataList = tempList;
		return this;
	}

	private void checkKeyName(String key) throws InvalidKeyNameException {
		if (key == null || key.trim() == "") {
			throw new InvalidKeyNameException(key, false);
		}
	}

	private void checkDataListExists() {
		if (dataList == null || dataList.length == 0) {
			throw new NullPointerException("Data structure is empty. Add some data first.");
		}
	}

	public String[] getKeyNames() {
		String[] list = new String[dataList.length];
		for (int i = 0; i < list.length; i++) {
			list[i] = dataList[i].name;
		}
		return list;
	}

	public RDataComponent getDataAsComponent(String key) throws KeyNameNotFoundException, InvalidKeyNameException {
		return (RDataComponent) this.getData(key);
	}

	public RDataList getDataAsList(String key) throws KeyNameNotFoundException, InvalidKeyNameException {
		return (RDataList) this.getData(key);
	}

	public RDataPrimative getDataAsPrimative(String key) throws KeyNameNotFoundException, InvalidKeyNameException {
		return (RDataPrimative) this.getData(key);
	}

	public String toString() {
		String string = new String("{\n");
		for (int i = 0; i < dataList.length; i++) {
			for (int j = 0; j < getLayer(); j++) {
				string = string.concat("\t");
			}
			string = string.concat(dataList[i].name + dataList[i].toString() + "\n");
		}
		for (int j = 0; j < getLayer() - 1; j++) {
			string = string.concat("\t");
		}
		string = string.concat("}");
		return string;
	}

}

//
