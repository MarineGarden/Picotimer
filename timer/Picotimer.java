package timer;

import java.math.BigDecimal;
import java.math.BigInteger;

import test.TestResult;

public class Picotimer {
	
	private static final BigDecimal ZERO = new BigDecimal("0");
	private static final BigInteger THOUSAND = new BigInteger("1000");
	private static final BigDecimal BILLION = new BigDecimal("1000000000");
	
	private BigDecimal timeCounter;
	private boolean lock;
	
	public Picotimer(BigInteger picoseconds) {
		
		timeCounter = new BigDecimal(picoseconds).divide(BILLION);
		
	}
	
	public void start(Runnable futureTask) {
		
		proceed(futureTask);
		
	}
	
	public void pause() {
		
		lock = true;
		
	}
	
	public void resume(Runnable futureTask) {
		
		lock = false;
		proceed(futureTask);
		
	}
	
	private void proceed(Runnable futureTask) {
		
		while( ! lock && timeCounter.compareTo(ZERO) > -1)
			timeCounter = timeCounter.subtract(new BigDecimal(Milliloop.go(THOUSAND)));
		finish(futureTask);
		
	}
	
	private void finish(Runnable futureTask) {
		
		futureTask.run();
		
	}

	public static void main(String[] args) {
		// This is a simple test without any pause.
		Picotimer timer = new Picotimer(new BigInteger("5000000000000"));
		long start = System.currentTimeMillis();
		timer.start(new TestResult());
		long finish = System.currentTimeMillis();
		System.out.println(finish - start);
	}

}
