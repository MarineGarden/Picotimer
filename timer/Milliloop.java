package timer;

import java.math.BigInteger;

public class Milliloop {
	
	public static final BigInteger ZERO = new BigInteger("0");
	private static final BigInteger ONE = new BigInteger("1");
	
	public static long go(BigInteger repeat) {
		long start = System.currentTimeMillis();
		while( ! repeat.equals(ZERO))
			repeat = repeat.subtract(ONE);
		long finish = System.currentTimeMillis();
		return finish - start;
	}

	public static void main(String[] args) {
		System.out.println(Milliloop.go(new BigInteger("1000000")));
	}

}
