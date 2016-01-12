package lifeexp;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


@SuppressWarnings("unused")
public class LIfeExpectancyTest {
	
	private LIfeExpectancy le;
	
	@Before
	public void setUp() {
		le = new LIfeExpectancy();
	}
	@Test
	public void testTestMap() {
		le.testMap();
	}

}
