package fox.rdata;

import java.io.Serializable;

import fox.rdata.exception.InvalidKeyNameException;
import fox.rdata.exception.KeyNameNotFoundException;

public class RDataList extends RDataStructure implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RDataList(String arg1) {
		super(arg1);
		// TODO Auto-generated constructor stub
	}

	public RDataList addData(RDataBase data) {
		if (dataList == null) {
			return this.addData(data, 0);
		} else {
			return this.addData(data, dataList.length);
		}
	}

	public RDataList addData(RDataBase data, int index) {
		if (data == null) {
			throw new NullPointerException("RDataBase object cannot be null");
		}
		if (index < 0) {
			throw new IndexOutOfBoundsException("You may not have an index less than zero.");
		}
		if (dataList != null && dataList.length > 0) {
			RDataBase[] tempList = new RDataBase[dataList.length + 1];
			// TODO If misbehaving, increase initial memory allocation to
			// prevent overflows.
			data.setLayer(getLayer() + 1);
			data.setParent(this);
			boolean inserted = false;
			for (int i = 0; i < dataList.length; i++) {
				if (i == index) {
					tempList[i] = data;
					inserted = true;
				}
				if (inserted) {
					tempList[i + 1] = dataList[i];
				} else {
					tempList[i] = dataList[i];
				}
			}
			if (!inserted) {
				tempList[dataList.length] = data;
			}
			dataList = tempList;

		} else {
			dataList = new RDataBase[1];
			dataList[0] = data;
		}
		return this;
	}

	public RDataBase getData(int index) {
		checkDataListExists();
		checkIndex(index);
		return dataList[index];
	}

	public RDataList removeData(int index) {
		checkDataListExists();
		checkIndex(index);
		RDataBase[] tempList = new RDataBase[dataList.length - 1];
		boolean remove = false;
		for (int i = 0; i < (tempList.length); i++) {
			if (i == index) {
				remove = true;
			}
			if (remove) {
				tempList[i] = dataList[i + 1];
			} else {
				tempList[i] = dataList[i];
			}
		}
		dataList = tempList;
		return this;
	}

	public RDataList replaceData(RDataBase data, int index) {
		this.removeData(index);
		this.addData(data, index);
		return this;
	}

	public RDataList swapData(int index1, int index2) {
		RDataBase tempData = this.getData(index1);
		this.replaceData(this.getData(index2), index1);
		this.replaceData(tempData, index2);
		return this;
	}

	private void checkIndex(int index) {
		if (index < 0) {
			throw new IndexOutOfBoundsException("You may not have an index less than zero.");
		} else if (index >= dataList.length) {
			throw new IndexOutOfBoundsException("Index too high: " + index + " (Max: " + (dataList.length - 1) + ")");
		}
	}

	private void checkDataListExists() {
		if (dataList == null || dataList.length == 0) {
			throw new NullPointerException("Data structure is empty. Add some data first.");
		}
	}

	public RDataComponent getDataAsComponent(int index) throws KeyNameNotFoundException, InvalidKeyNameException {
		return (RDataComponent) this.getData(index);
	}

	public RDataList getDataAsList(int index) throws KeyNameNotFoundException, InvalidKeyNameException {
		return (RDataList) this.getData(index);
	}

	public RDataPrimative getDataAsPrimative(int index) throws KeyNameNotFoundException, InvalidKeyNameException {
		return (RDataPrimative) this.getData(index);
	}

	public String toString() {
		String string = new String("[\n");
		for (int i = 0; i < dataList.length; i++) {
			for (int j = 0; j < getLayer(); j++) {
				string = string.concat("\t");
			}
			string = string.concat(i + dataList[i].toString() + "\n");
		}
		for (int j = 0; j < getLayer() - 1; j++) {
			string = string.concat("\t");
		}
		string = string.concat("]");
		return string;
	}
}
