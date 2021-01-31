package persist;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Class<?> entityClass, Long id) {
        super("Persistent storage has no entity with attributes: class " +entityClass.getSimpleName() + ", id: " +  id);
    }
}
