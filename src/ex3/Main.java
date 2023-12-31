package ex3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("./ex3Context.xml");

        Library library = (Library) context.getBean("library");
        System.out.println(library);
    }
}