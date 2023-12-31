package ex4;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcUserDao implements UserDao {
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(User user) {
        String sql = "INSERT INTO users (username, email) VALUE (?, ?)";
        try (Connection connection = dataSource.getConnection();
                PreparedStatement stm = connection.prepareStatement(sql);) {
            stm.setString(1, user.getUsername());
            stm.setString(2, user.getEmail());
            stm.executeUpdate();

            ResultSet rs = stm.getGeneratedKeys();
            if (rs.next()) {
                user.setId(rs.getLong(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findById(long id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        User user = null;
        try (Connection connection = dataSource.getConnection();
                PreparedStatement stm = connection.prepareStatement(sql);) {
            stm.setLong(1, id);
            ResultSet rs = stm.executeQuery();
            rs.next();
            if (rs.getRow() == 0)
                user = null;
            else
                user = new User();
            user.setId(rs.getLong("id"));
            user.setUsername(rs.getString("username"));
            user.setEmail(rs.getString("email"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        ArrayList<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Connection connection = dataSource.getConnection();
                PreparedStatement stm = connection.prepareStatement(sql);) {
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void update(User user) {
        String sql = "UPDATE users SET username = ?, email = ? WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
                PreparedStatement stm = connection.prepareStatement(sql);) {
            stm.setString(1, user.getUsername());
            stm.setString(2, user.getEmail());
            stm.setLong(3, user.getId());
            stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
                PreparedStatement stm = connection.prepareStatement(sql);) {
            stm.setLong(1, id);
            stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
