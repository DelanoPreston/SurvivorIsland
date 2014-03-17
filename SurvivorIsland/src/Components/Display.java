package Components;

import java.awt.Image;
import java.io.Serializable;

import com.artemis.Component;

public class Display extends Component implements Serializable{
	private static final long serialVersionUID = 4847158988377680792L;
	transient Image image;
	public String arrayDispName;
	public int imageKey;
}
