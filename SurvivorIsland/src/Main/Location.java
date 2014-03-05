package Main;

public class Location {
	double x;
	double y;

	public int getMapX() {
		return (int) x;
	}

	public int getMapY() {
		return (int) y;
	}

	public int getTileX() {
		double temp = 0;
		if (x % 16 > 8)
			temp = x - 8;

		temp = x / 16;

		return (int) temp;
	}

	public int getTileY() {
		double temp = 0;
		if (y % 16 > 8)
			temp = y - 8;

		temp = y / 16;

		return (int) temp;
	}

	public Location(double inX, double inY) {
		x = inX;
		y = inY;
	}

	public Location(int inX, int inY) {
		x = inX;
		y = inY;
	}

	public void addMovement(double[] inChange) {
		x -= inChange[0];
		y -= inChange[1];
	}
}
