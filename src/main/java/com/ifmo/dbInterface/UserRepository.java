package com.ifmo.dbInterface;

import com.ifmo.db.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    int countByLogin(String login);
    int countUserByLoginAndPassword(String login, String password);
    User getUserByLogin(String login);
}
