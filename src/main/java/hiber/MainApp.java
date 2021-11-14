package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        userService.clean();

        userService.add(new User("User1", "Lastname1", "user1@mail.ru",
                new Car("Ferrari", 1970)));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru",
                new Car("Ford", 1974)));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru",
                new Car("Pontiac", 1979)));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru",
                new Car("Toyota", 1973)));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println(user.toString());
        }
        System.out.println();

        User user = userService.getUser("Ford", 1974);
        System.out.println(user.toString());

        context.close();
    }
}
