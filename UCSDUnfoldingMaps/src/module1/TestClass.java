package module1;

/**
 * Test code for module 1.
 * Testing github synchronization. 
 * @author Pawel Daniluk
 * @version 1.0
 *
 */
public class TestClass {
	
	private int i;
	
	public TestClass(int i) {this.i = i;}

	public String toString() {
		return new Integer(i).toString();
		
	}
	
	public static void main(String[] args) {
		
		TestClass myInt = new TestClass(150);
		System.out.println(myInt);

	}

}
