package Maps;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IslandGenerator {
	int height;
	int width;
	int seed;
	double[][] map;
	double[][] particleMap;

	public double[][] getMap() {
		return particleMap;
	}

	public char[][] getCharMap() {
		char[][] temp = new char[height][width];
		char[] chars = { 's', 'b', 'f', 'j', 'm' };
		int[] charKey = { 0, 0, 0, 0 };
		int[] counter = new int[256];
		for (int i = 0; i < counter.length; i++) {// sets counter to 0
			counter[i] = 0;
		}
		for (int i = 0; i < height; i++) {// counts values
			for (int j = 0; j < width; j++) {
				counter[(int) particleMap[i][j]]++;
			}
		}

		int totalValues = height * width;
		int tempInt = 0;

		for (int i = 0; i < counter.length; i++) {// sets counter to 0
			tempInt += counter[i];
			if (tempInt >= totalValues * .5 && charKey[0] == 0) {
				charKey[0] = i;
				tempInt = 0;
			}
			if (tempInt >= totalValues * .15 && charKey[1] == 0 && charKey[0] != 0) {
				charKey[1] = i;
				tempInt = 0;
			}
			if (tempInt >= totalValues * .2 && charKey[2] == 0 && charKey[1] != 0) {
				charKey[2] = i;
				tempInt = 0;
			}
			if (tempInt >= totalValues * .1 && charKey[3] == 0 && charKey[2] != 0) {
				charKey[3] = i;
				tempInt = 0;
			}
		}

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				double tempVal = particleMap[i][j];

				if (tempVal >= 0 && tempVal <= charKey[0] - 1) {
					temp[i][j] = chars[0];
				} else if (tempVal >= charKey[0] && tempVal <= charKey[1] - 1) {
					temp[i][j] = chars[1];
				} else if (tempVal >= charKey[1] && tempVal <= charKey[2] - 1) {
					temp[i][j] = chars[2];
				} else if (tempVal >= charKey[2] && tempVal <= charKey[3] - 1) {
					temp[i][j] = chars[3];
				} else if (tempVal >= charKey[3] && tempVal <= 255) {
					temp[i][j] = chars[4];
				}
			}
		}

		return temp;
	}

	public IslandGenerator(int inWid, int inHei) {
		width = inWid;
		height = inHei;
		seed = 0;
		map = new double[height][width];
		particleMap = new double[height][width];
	}

	public IslandGenerator(int inWid, int inHei, int inSeed) {
		width = inWid;
		height = inHei;
		seed = inSeed;
		map = new double[height][width];
		particleMap = new double[height][width];
	}

	// @SuppressWarnings("static-access")
	public double[][] generateIsland(int inWid, int inHei, double inFreq, int inOct) {
		// generator noise
		PerlinNoise png;// = new PerlinNoise(height, width, seed);
		Random random;// = new Random(seed);
		if (seed != 0) {
			png = new PerlinNoise(height, width, seed);
			random = new Random(seed);
		} else {
			png = new PerlinNoise(height, width);
			random = new Random();
		}

		map = png.generate_noise(inWid, inHei, inFreq, inOct);

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				particleMap[y][x] = 0.0;// (int) (((png.noise(x, y) + 1) * .5) * 255);
			}
		}

		// for (int q = 0; q < height; q++) {
		// for (int r = 0; r < width; r++) {
		// System.out.println(r + "," + q);
		// particleMap[q][r] = 0;
		//
		// not sure
		for (int i = 0; i < (height * width) * .85; i++) {
			// int x = (int)((random.nextInt(width) - (width/2)) * .55) + (width/2);//(width / 16) + random.nextInt(width - (width / 8));// rand from 15 to
			// width-16
			// int y = (int)((random.nextInt(height) - (height/2)) * .55) + (height/2);//(height / 16) + random.nextInt(height - (height / 8));// rand from 15
			// to width-16
			int x = (width / 16) + random.nextInt(width - (width / 8));// rand from 15 to width-16
			int y = (height / 16) + random.nextInt(height - (height / 8));// rand from 15 to width-16
			// int x = random.nextInt(width);
			// int y = random.nextInt(height);

			for (int j = 0; j < (height * width) * .15; j++) {
				// System.out.println(j + "," + i);
				particleMap[y][x] += 7;
				if (particleMap[y][x] >= 255)
					particleMap[y][x] = 255;
				double current_value = particleMap[y][x];

				boolean oneTrue = false;
				List<String> choices = new ArrayList<>();// [4];
				if (x - 1 > 0)
					if (particleMap[y][x - 1] <= current_value) {
						choices.add("left");
						oneTrue = true;
					}
				if (x + 1 < width - 1)
					if (particleMap[y][x + 1] <= current_value) {
						choices.add("right");
						oneTrue = true;
					}
				if (y - 1 > 0)
					if (particleMap[y - 1][x] <= current_value) {
						choices.add("up");
						oneTrue = true;
					}
				if (y + 1 < height - 1)
					if (particleMap[y + 1][x] <= current_value) {
						choices.add("down");
						oneTrue = true;
					}

				if (!oneTrue)// one of the 4 if statements above
					break;

				int randInt = random.nextInt(choices.size());
				String newString = choices.get(randInt);
				if (newString == "left")
					x -= 1;
				else if (newString == "right")
					x += 1;
				else if (newString == "up")
					y -= 1;
				else if (newString == "down")
					y += 1;

			}
		}
		// }
		// }

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				map[y][x] *= ((particleMap[y][x]) / 255.0);
			}
		}
		
		particleMap = doubleSize(particleMap);
		height *= 2;
		width *= 2;
//		particleMap = doubleSize(particleMap);
//		height *= 2;
//		width *= 2;
		
		for (int y = 0; y < 2; y++) {
			smoothen();
		}

		return map;
	}

	private double[][] doubleSize(double[][] inMap){
		double[][] temp = new double[inMap.length * 2][inMap[0].length * 2];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				temp[y * 2][x * 2] = inMap[y][x];
				temp[(y * 2) + 1][x * 2] = inMap[y][x];
				temp[y * 2][(x * 2) + 1] = inMap[y][x];
				temp[(y * 2) + 1][(x * 2) + 1] = inMap[y][x];
			}
		}
		
		return temp;
	}
	
	private void smoothen() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				double average = 0.0;
				double times = 0.0;

				if (x - 1 >= 0)
					average += particleMap[y][x - 1];
				times += 1;
				if (x + 1 < width - 1)
					average += particleMap[y][x + 1];
				times += 1;
				if (y - 1 >= 0)
					average += particleMap[y - 1][x];
				times += 1;
				if (y + 1 < height - 1)
					average += particleMap[y + 1][x];
				times += 1;

				if (x - 1 >= 0 && y - 1 >= 0)
					average += particleMap[y - 1][x - 1];
				times += 1;
				if (x + 1 < width && y - 1 >= 0)
					average += particleMap[y - 1][x + 1];
				times += 1;
				if (x - 1 >= 0 && y + 1 < height)
					average += particleMap[y + 1][x - 1];
				times += 1;
				if (x + 1 < width && y + 1 < height)
					average += particleMap[y + 1][x + 1];
				times += 1;

				average += particleMap[y][x];
				times += 1;

				average /= times;

				particleMap[y][x] = (int) average;
			}
		}
	}
}
