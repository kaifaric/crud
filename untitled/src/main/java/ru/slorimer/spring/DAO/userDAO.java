package ru.slorimer.spring.DAO;

import org.springframework.stereotype.Component;
import ru.slorimer.spring.model.User;

import java.util.ArrayList;
import java.util.List;

@Component
public class userDAO {
    private List<User> userList;
    private static int PEOPLE_COUnT;

    {
        userList = new ArrayList<>();
        userList.add(new User(++PEOPLE_COUnT, "tom", 18, "emae@mail.ru"));
        userList.add(new User(++PEOPLE_COUnT, "tom1", 48, "blin@mail.ru"));
        userList.add(new User(++PEOPLE_COUnT, "tom2", 95, "negr@mail.ru"));
        userList.add(new User(++PEOPLE_COUnT, "tom3", 10, "babyshka228@mail.ru"));
    }
    public List<User> index(){
        return userList;
    }
    public User show(int id){
        return userList.stream().filter(userList -> userList.getId() == id).findAny().orElse(null);
    }
    public void save(User user){
        user.setId(++PEOPLE_COUnT);
        userList.add(user);
    }
    public void edit(int id, User newUser){
        User userToBeEdited = show(id);
        userToBeEdited.setName(newUser.getName());
        userToBeEdited.setAge(newUser.getAge());
        userToBeEdited.setEmail(newUser.getEmail());
    }
    public void delete(int id){
        userList.removeIf(u -> u.getId() == id);
    }
}
