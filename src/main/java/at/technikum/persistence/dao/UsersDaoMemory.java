package at.technikum.persistence.dao;

import at.technikum.core.model.Users;

import java.util.*;

public class UsersDaoMemory implements Dao<Users> {
    private Map<Integer, Users> users = new HashMap<>();

    @Override
    public Optional<Users> get(int id) {
        return Optional.ofNullable( users.get(id) );
    }

    @Override
    public Collection<Users> getAll() {
        return users.values();
    }

    @Override
    public void save(Users user) {
        users.put(user.getId(), user);
    }

    @Override
    public void update(Users user, String[] params) {
        // update the item
        user.setId(Integer.parseInt(Objects.requireNonNull(params[0], "Id cannot be null")));
        user.setUsername(Objects.requireNonNull(params[1]));
        user.setPassword(Objects.requireNonNull(params[2]));
        user.setToken(Objects.requireNonNull( params[3]));
        // persist the updated item
        users.put(user.getId(), user);
    }

    @Override
    public void delete(Users user) {
        users.remove(user.getId());
    }
}
