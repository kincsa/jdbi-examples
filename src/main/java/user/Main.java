package user;


import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        Jdbi jdbi = Jdbi.create("jdbc:h2:mem:test");
        jdbi.installPlugin(new SqlObjectPlugin());

        try (Handle handle = jdbi.open()) {

            UserDao dao = handle.attach(UserDao.class);
            dao.createTable();

            User user1 = User.builder()
                    .name("James Bond")
                    .username("jamesbond")
                    .password("007")
                    .email("jamesbond@mail.com")
                    .gender(User.Gender.MALE)
                    .enabled(true)
                    .dob(LocalDate.parse("1920-11-11"))
                    .build();

            User user2 = User.builder()
                    .name("Jesse Pinkman")
                    .username("jessepinkman")
                    .password("breakingbad123")
                    .email("jessepinkman@mail.com")
                    .gender(User.Gender.MALE)
                    .enabled(true)
                    .dob(LocalDate.parse("1992-06-12"))
                    .build();

            User user3 = User.builder()
                    .name("Rubeus Hagrid")
                    .username("hagrid")
                    .password("hogwarts123")
                    .email("hagrid@mail.com")
                    .gender(User.Gender.MALE)
                    .enabled(true)
                    .dob(LocalDate.parse("1928-12-06"))
                    .build();

            User user4 = User.builder()
                    .name("Thomas Shelby")
                    .username("tommyshelby")
                    .password("blinders123")
                    .email("tommyshelby@mail.com")
                    .gender(User.Gender.MALE)
                    .enabled(true)
                    .dob(LocalDate.parse("1910-04-30"))
                    .build();

            dao.insert(user1);
            dao.insert(user2);
            dao.insert(user3);
            dao.insert(user4);

            dao.findById(2).stream().forEach(System.out::println);

            dao.findByUsername("tommyshelby").stream().forEach(System.out::println);

            dao.delete(user1);

            dao.list().stream().forEach(System.out::println);

        }

    }

}