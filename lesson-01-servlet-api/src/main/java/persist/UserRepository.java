package persist;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class UserRepository {

    Map<Long, User> userMap = new ConcurrentHashMap<>();
    AtomicLong identity = new AtomicLong(0L);

    public List<User> findAll() {
        return new ArrayList<>(userMap.values());
    }

    public User findById(Long id) throws EntityNotFoundException {
        return Optional.ofNullable(userMap.get(id))
                .orElseThrow(() -> new EntityNotFoundException(User.class, id));
    }

    public void insert(User user) {
        Long id = identity.incrementAndGet();
        user.setId(id);
        userMap.put(user.getId(), user);
    }

    public void update(User user) throws EntityNotFoundException{
        if (userMap.get(user.getId()) == null) {
            throw new EntityNotFoundException(User.class, user.getId());
        }
        userMap.put(user.getId(), user);
    }

    public void delete(Long id) throws EntityNotFoundException {
        if (userMap.get(id) == null) {
            throw new EntityNotFoundException(User.class, id);
        }
        userMap.remove(id);
    }
}
