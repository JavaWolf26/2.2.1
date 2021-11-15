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

        User user1 = new User("User1", "Lastname1", "user1@mail.ru");
        User user2 = new User("User2", "Lastname2", "user2@mail.ru");
        User user3 = new User("User3", "Lastname3", "user3@mail.ru");
        User user4 = new User("User4", "Lastname4", "user4@mail.ru");

        Car toyota = new Car("Toyota", 1973);
        Car ford = new Car("Ford", 1974);
        Car ferrari = new Car("Ferrari", 1972);
        Car mercedes = new Car("Mercedes", 1978);

        userService.add(user1.setCar(toyota).setUser(user1));
        userService.add(user2.setCar(ford).setUser(user2));
        userService.add(user3.setCar(ferrari).setUser(user3));
        userService.add(user4.setCar(mercedes).setUser(user4));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println(user.toString());
        }
        System.out.println();

        User user = userService.getUserCar("Ferrari", 1972);
        System.out.println(user.toString());

        context.close();
    }
}
