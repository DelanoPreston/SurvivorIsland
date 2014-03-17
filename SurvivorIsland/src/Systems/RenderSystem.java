package Systems;

import java.awt.Container;
import java.util.ArrayList;
import java.util.List;

import ashley.core.Engine;

import com.artemis.Aspect;
import com.artemis.Entity;
import com.artemis.systems.EntityProcessingSystem;
//import ashley.core.Engine;

public class RenderSystem extends EntityProcessingSystem {
	

	Container container;// display container

	public RenderSystem(Aspect aspect) {
		super(aspect);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void process(Entity arg0) {
		// TODO Auto-generated method stub
		
	}
}
