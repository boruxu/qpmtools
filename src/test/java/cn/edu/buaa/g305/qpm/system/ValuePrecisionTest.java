package cn.edu.buaa.g305.qpm.system;

import static org.junit.Assert.*;

import org.junit.Test;

public class ValuePrecisionTest {

	@Test
	public void testStringPrecision() {
		
		int[] expecteds=new int[]{0,1,2,3};
		int[] actuals=new int[]{ValuePrecision.precision("1112"),
				ValuePrecision.precision("11.2"),
				ValuePrecision.precision("11.12"),
				ValuePrecision.precision("1.112")};
		assertArrayEquals(expecteds, actuals);
	}

}
