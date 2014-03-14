package Systems;

import java.awt.Container;
import java.util.ArrayList;
import java.util.List;


//import ashley.core.Engine;
import Ash.Engine;
import Nodes.RenderNode;

public class RenderSystem {//extends system
	Container container;//display container
	List<RenderNode> nodes = new ArrayList<>();
	
	public RenderSystem(Container inCont){
		container = inCont;
	}
	
	//@Override
	public void addToEngine(Engine engine){
//		nodes = engine.getNodeList(RenderNode.class);
	}
}
