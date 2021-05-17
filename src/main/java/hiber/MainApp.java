package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("Alla", "Pugacheva", "a.pugacheva@list.ru");
      User user2 = new User("Maksim", "Galkin", "galka@rambler.ru");
      User user3 = new User("Vladimir", "Kuzmin", "vk@gmail.com");
      User user4 = new User("Filipp", "Kirkorov", "filya@inbox.ru");

      user1.setCar(new Car("Moskvitch", 412));
      user2.setCar(new Car("Zhiguli", 2101));
      user3.setCar(new Car("LADA", 2108));
      user4.setCar(new Car("UAZ", 469));

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);

      List<User> users = userService.listUsers();
      for (User user : users) {
         Car car = user.getCar();
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         System.out.println("Car: " + car.getModel());
         System.out.println();
      }

      User userByCar = userService.getUserByCar("Moskvitch", 412);
      System.out.println("Владелец автомобиля: " + userByCar.getFirstName() + " " + userByCar.getLastName());

      context.close();
   }
}
