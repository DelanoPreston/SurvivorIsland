package Ash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComponentMatchingFamily implements Family {

    private NodeList nodes;
    private NodePool pool;
    private Class<? extends Node> nodeClass;
    private List<Entity> entities;
    private Map<Class<? extends Component>, Component> components;
    private Engine engine;

    public ComponentMatchingFamily(Class<? extends Node> klass, Engine engine) {
        this.engine = engine;
        this.nodeClass = klass;

        this.pool = new NodePool();
        entities = new ArrayList<Entity>();
        components = new HashMap<Class<? extends Component>, Component>();
    }

    @Override
    public NodeList getNodeList() {
        return nodes;
    }

    @Override
    public void newEntity(Entity entity) {
        addIfMatch(entity);
    }

    @Override
    public void removeEntity(Entity entity) {
        removeIfMatch(entity);
    }

    private void addIfMatch(Entity entity) {
        if(!entities.contains(entity)) {
            
            for(Class<? extends Component> klass : components.keySet()) {
                if(!entity.has(klass)) {
                    return;
                }
            }
            Node node = pool.get();
            node.setEntity(entity);
            
        }
    }

    private void removeIfMatch(Entity entity) {
        
    }

    @Override
    public void componentAddedToEntity(Entity entity, Class<? extends Component> klass) {
        addIfMatch(entity);
    }

    @Override
    public void componentRemovedFromEntity(Entity entity, Class<? extends Component> klass) {
        if (components.containsKey(klass)) {
            removeIfMatch(entity);
        }
    }

    @Override
    public void cleanUp() {

    }

}
