package fox.rdata;

import fox.rdata.exception.InvalidKeyNameException;

public class MainClass {

	/**
	 * @param args
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RData test = null;
		try {
			test = (RData) new RData().addData(new RDataPrimative("test1", new Integer(5))).addData(new RDataPrimative("test2", new Integer(10)));
			RDataComponent comp = new RDataComponent("comp1").addData(new RDataPrimative("num1", new Integer(20))).addData(new RDataPrimative("num2", new Integer(25)));
			test.addData(comp).addData(new RDataPrimative("test3", new Integer(15)));
			RDataList list = new RDataList("list1").addData(new RDataPrimative(null, new String("This")));
			list.addData(new RDataPrimative(null, new String("Is")));
			list.addData(new RDataPrimative(null, new String("Text")));
			test.addData(list);

		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(test.getSize());
		System.out.println();
		System.out.flush();
		// String[] names = test.getKeyNames();
		System.out.println(test);
	}
}
// 126206496
