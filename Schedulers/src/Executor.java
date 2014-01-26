import javax.swing.JApplet;


public class Executor extends JApplet {

	@Override
	public void destroy() {
		System.out.println("destroy");
	}

	@Override
	public void init() {
		System.out.println("init");
	}

	@Override
	public void start() {
		System.out.println("start");
	}

	@Override
	public void stop() {
		System.out.println("stop");
	}
	
}
