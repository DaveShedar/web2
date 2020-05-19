package service;

import model.User;

import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class UserService {

    /* хранилище данных */
    private Map< Long, User > dataBase = Collections.synchronizedMap(new HashMap<>());

    /* счетчик id */
    private AtomicLong maxId = new AtomicLong(0);
    /* список авторизованных пользователей */
    public Map< Long, User > authMap = Collections.synchronizedMap(new HashMap<>());



    public List< User > getAllUsers() {
        return new ArrayList<>(dataBase.values());
    }

    public User getUserById(Long id) {
        try {
            return dataBase.get(id);
        } catch (Exception e) {
            System.out.println("User is not found in dataBase");
            return null;
        }
    }

    public Long getCurrentId() {
        return maxId.get();
    }

    public Long getNewId() {
        return maxId.incrementAndGet();
    }

    public boolean addUser(User user) {
        dataBase.put(maxId.get(), user);
        return true;
    }

    public void deleteAllUser() {
        dataBase.clear();
    }

    public boolean isExistsThisUser(User user) {
        System.out.println("user as argum in isExistsThisUser: " + user.getEmail() + ":" + user.getPassword());
        for (Map.Entry< Long, User > entry : dataBase.entrySet()) {
            User userInDataBase = entry.getValue();
            System.out.println("userInDataBase: " + userInDataBase.getEmail() + ":" + userInDataBase.getPassword());
            if (userInDataBase.getEmail().equals(user.getEmail()) & (userInDataBase.getPassword().equals(user.getPassword()))) {
                return true;
            }
        }
        System.out.println("dataBase is not contains this user. isExistsThisUser exit");
        return false;
    }

    public List< User > getAllAuth() {
        return new ArrayList<>(authMap.values());
    }

    public boolean authUser(User user) {
        return authMap.containsValue(user);
    }

    public void logoutAllUsers() {
        authMap.clear();
    }

    public boolean isUserAuthById(Long id) {
        return authMap.containsKey(id);
    }

}
