package Components;

import java.io.Serializable;

import com.artemis.Component;

public class Rotation extends Component implements Serializable {
	private static final long serialVersionUID = -6912249106985130796L;
	private float rot;

	public float getRot() {
		return rot;
	}

	public void setRot(float rot) {
		this.rot = rot;
	}

	public Rotation(float rot) {
		this.rot = rot;
	}

	public void addRot(float rot) {
		this.rot += rot;
	}
}
