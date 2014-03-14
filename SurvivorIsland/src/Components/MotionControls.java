package Components;

public class MotionControls {
	public int left;
	public int right;
	public int accelerate;

	public double accelerationRate;
	public double rotationRate;

	public MotionControls(int left, int right, int accelerate, double accelerationRate, double rotationRate) {
		this.left = left;
		this.right = right;
		this.accelerate = accelerate;
		this.accelerationRate = accelerationRate;
		this.rotationRate = rotationRate;
	}
}
