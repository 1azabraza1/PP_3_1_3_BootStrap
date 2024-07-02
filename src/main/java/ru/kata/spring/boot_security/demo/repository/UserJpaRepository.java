package ru.kata.spring.boot_security.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.entity.User;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long> {

    @Query("select u from User u left join fetch u.roles where u.name =:name")
    User findByName(String name);
}
