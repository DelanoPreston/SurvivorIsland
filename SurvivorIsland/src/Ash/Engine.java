package Ash;

import java.util.HashMap;
import java.util.Map;

public class Engine {
    private EntityList entityList;
    private EntitySystemList systemList;
    private Map<Class<? extends Family>, Family> families;

    public Engine() {
        entityList = new EntityList();
        systemList = new EntitySystemList();
        families = new HashMap<Class<? extends Family>, Family>();
    }

    public void add(Entity entity) {
        entityList.add(entity);

        for (Family family : families.values()) {
            family.newEntity(entity);
        }
    }

    public void remove(Entity entity) {
        for (Family family : families.values()) {
            family.removeEntity(entity);
        }

        entityList.remove(entity);
    }

    public void removeAllEntities() {
        while (!entityList.isEmpty()) {
            remove(entityList.getHead());
        }
    }

    public Entity[] getEntities() {
        Entity[] list = new Entity[entityList.getSize()];
        int index = 0;
        for (Entity e : entityList) {
            list[index++] = e;
        }
        return list;
    }

    private void componentAdded(Entity entity, Class<? extends Component> klass) {
        for (Family family : families.values()) {
            family.componentAddedToEntity(entity, klass);
        }
    }

    private void componentRemoved(Entity entity, Class<? extends Component> klass) {
        for (Family family : families.values()) {
            family.componentRemovedFromEntity(entity, klass);
        }
    }

    public NodeList getNodeList(Class<? extends Node> klass) {
        return null;
    }

    public void releaseNodeList(Class<? extends Node> klass) {
        Family family = families.remove(klass);
        if (family != null) {
            family.cleanUp();
        }
    }

    public void add(EntitySystem system) {
        systemList.add(system);
    }
    
    public EntitySystem getSystem(Class<? extends EntitySystem> klass) {
        return null;
    }

    public void remove(EntitySystem system) {
        systemList.remove(system);
    }

    public void removeAllSystems() {
        while (!systemList.isEmpty()) {
            remove(systemList.getHead());
        }
    }

    public void clear() {
        removeAllEntities();
        removeAllSystems();
    }
}
