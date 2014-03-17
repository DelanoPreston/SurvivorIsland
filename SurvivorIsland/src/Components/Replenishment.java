package Components;

import java.io.Serializable;

import com.artemis.Component;

public class Replenishment extends Component implements Serializable{
	private static final long serialVersionUID = 6822653777451147616L;
	public int replenishment;
	
	public int getReplenishment(){
		return replenishment;
	}
	
	public void setReplenishment(int replen){
		this.replenishment = replen;
	}
	
	public Replenishment(int replen){
		this.replenishment = replen;
	}
	
	public void addReplenishment(int replen){
		this.replenishment += replen;
	}
}
