package user;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;
import java.util.Optional;

@RegisterBeanMapper(User.class)
public interface UserDao {

    @SqlUpdate("""
        CREATE TABLE users (
            id IDENTITY PRIMARY KEY,
            username VARCHAR UNIQUE,
            password VARCHAR,
            name VARCHAR,
            email VARCHAR,
            gender VARCHAR,
            dob DATE,
            enabled BIT
        )
        """
    )
    void createTable();


    @SqlUpdate("INSERT INTO users VALUES (:id, :username, :password, :name, :email, :gender, :dob, :enabled)")
    @GetGeneratedKeys("id")
    Long insert(@BindBean User user);

    @SqlQuery("SELECT * FROM users where id= :id")
    Optional<User> findById(@Bind("id") long id);

    @SqlQuery("SELECT * FROM users where username= :username")
    Optional<User> findByUsername(@Bind("username") String username);

    @SqlUpdate("DELETE from users WHERE username = :username")
    void delete(@BindBean User user);

    @SqlQuery("SELECT * FROM users")
    List<User> list();

}