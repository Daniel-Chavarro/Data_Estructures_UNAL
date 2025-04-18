package biblored.model.DAO;

import biblored.model.generic.User;

import java.util.ArrayList;

public class UserDAO implements InterfaceDAO<User> {
    private final ArrayList<User> users;

    public UserDAO() {
        users = new ArrayList<>();
    }

    @Override
    public boolean add(User user) {
        if(read(user.getId()) == null) {
            users.add(user);
            return true;
        }
        return false;
    }

    @Override
    public User read(int id) {
        User user = null;
        for (User u : users) {
            if (u.getId() == id) {
                user = u;
            }
        }
        return user;
    }

    @Override
    public ArrayList<User> readAll() {
        return users;
    }

    @Override
    public boolean update(int id, User user) {
        User found = read(id);
        if (found != null) {
            found.setName(user.getName());
            found.setEmail(user.getEmail());
            found.setPassword(user.getPassword());
            found.setPhone(user.getPhone());
            found.setAddress(user.getAddress());
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        User found = read(id);
        if (found != null) {
            users.remove(found);
            return true;
        }
        return false;
    }
}
