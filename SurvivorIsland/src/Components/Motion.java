package Components;

import java.io.Serializable;

import com.artemis.Component;

public class Motion extends Component implements Serializable{
	private static final long serialVersionUID = 1732255954876119453L;
	double velX;//x velocity
	double velY;//y velocity
	double delV;//speed
	float angV;//angle of the speed
}
