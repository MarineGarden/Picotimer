package timer;

import java.math.BigInteger;

import test.TestResult;

public class Picotimer {
	
	private static final BigInteger THOUSAND = new BigInteger("1000");
	private static final BigInteger BILLION = new BigInteger("1000000000");

	
	private BigInteger timeCounter;
	private boolean lock;
	
	public Picotimer(BigInteger picoseconds) {
		timeCounter = picoseconds.divide(BILLION);
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
		while( ! lock && timeCounter.compareTo(Milliloop.ZERO) > -1) {
			//System.out.println(timeCounter);
			timeCounter = timeCounter.subtract(new BigInteger(Long.toString(Milliloop.go(THOUSAND))));
		}
		finish(futureTask);
	}
	
	private void finish(Runnable futureTask) {
		futureTask.run();
	}

	public static void main(String[] args) {
		// This is a test without pause.
		Picotimer timer = new Picotimer(new BigInteger("5000000000000"));
		long start = System.currentTimeMillis();
		timer.start(new TestResult());
		long finish = System.currentTimeMillis();
		System.out.println(finish - start);
	}

}
