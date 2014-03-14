package Ash;

public interface Family {
    public NodeList getNodeList();

    public void newEntity(Entity entity);

    public void removeEntity(Entity entity);

    public void componentAddedToEntity(Entity entity, Class<? extends Component> klass);

    public void componentRemovedFromEntity(Entity entity, Class<? extends Component> klass);

    public void cleanUp();
}
