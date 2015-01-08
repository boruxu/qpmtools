package cn.edu.buaa.g305.qpm.system;

import static org.junit.Assert.*;

import org.junit.Test;

public class DoublePrecisionTest {

	@Test
	public void test() {
		double[] expecteds=new double[]{9.8905,9.8904};
		double[] actuals=new double[]{DoublePrecision.precision(9.890451,4),
				DoublePrecision.precision(9.890449,4)};
		assertArrayEquals(expecteds, actuals,0);

	}

}
