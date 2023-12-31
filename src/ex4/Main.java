package ex4;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("./ex4Context.xml");
        UserDao userDao = (UserDao) context.getBean("userDao");

        userDao.save(new User("mehdi", "mehdi@gmail.com"));
        userDao.save(new User("walid", "walid@gmail.com"));
        userDao.save(new User("mariam", "mariam@gmail.com"));
        userDao.save(new User("loubna", "loubna@gmail.com"));

        userDao.findAll().forEach(user -> System.out.println("User: " + user));

    }
}
