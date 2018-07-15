package com.ms.ex.booksearch.booksearch.repository;

import com.ms.ex.booksearch.booksearch.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
