package Components;

import java.io.Serializable;

import com.artemis.Component;


public class Position extends Component implements Serializable{
	private static final long serialVersionUID = -708214087544636232L;
	public float x;
	public float y;
	
	public float getX(){
		return x;		
	}
	
	public float getY(){
		return y;
	}
	
	public void setX(float x){
		this.x = x;
	}
	
	public void setY(float y){
		this.y = y;
	}
	
	public Position(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	public void setPosition(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	public void addX(float x){
		this.x += x;
	}
	
	public void addY(float y){
		this.y += y;
	}
}
