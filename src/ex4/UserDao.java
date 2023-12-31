package ex4;

import java.util.List;

import javax.sql.DataSource;

public interface UserDao {
    void setDataSource(DataSource dataSource);

    void save(User user);

    User findById(long id);

    List<User> findAll();

    void update(User user);

    void delete(long id);
}
