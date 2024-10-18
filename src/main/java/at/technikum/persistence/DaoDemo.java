package at.technikum.persistence;

import at.technikum.core.model.Users;
import at.technikum.persistence.dao.Dao;
import at.technikum.persistence.dao.UsersDaoDb;

import java.util.Optional;

public class DaoDemo {
    private static Dao<Users> dao;

    public static void main(String[] args) {
//        dao = new PlaygroundPointDaoMemory();
        dao = new UsersDaoDb();
        UsersDaoDb.initDb();

        dao.save(new Users(1,"John Doe", "pw", "mySecret"));

        Users user1 = getUser(1);
        System.out.println(user1);

        dao.update(user1, new String[]{"1","Max Musterfrau", "pw", "mySecret"});
        System.out.println();

        Users user2 = getUser(2);
        dao.delete(user2);
        dao.save(new Users(2,"Jane Doe", "pw", "mySecret"));

        dao.getAll().forEach(System.out::println);
    }

    private static Users getUser(int id) {
        Optional<Users> user = dao.get(id);

        return user.orElseGet(
                Users::new
        );
    }
}
