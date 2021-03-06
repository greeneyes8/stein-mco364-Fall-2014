package stein.threads;

public class MultiThreadingOneValue {

	static class IntegerWrapper {
		int i;
	}

	public static void main(String[] args) {
		final IntegerWrapper wrapper = new IntegerWrapper();

		for (int i = 0; i < 1000; i++) {
			Thread thread = new Thread() {
				public void run() {
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
					}
					wrapper.i++;
				}
			};
			thread.start();

		}
		System.out.println(wrapper.i);
	}
}
