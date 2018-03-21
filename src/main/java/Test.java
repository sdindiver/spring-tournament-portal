
public class Test {

	public static void main(String[] args) throws InterruptedException {

		Thread first = new Thread(() -> {

			try {
				Thread.sleep(4000l);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		Thread second = new Thread(() -> {

			try {
				Thread.sleep(4000l);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		first.start();
		first.join();
		System.out.println("printing");
		second.start();
		second.join();
		

	}
}
