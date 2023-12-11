package com.example.kameleoontrialtask.repo;

import com.example.kameleoontrialtask.domain.models.Quote;
import com.example.kameleoontrialtask.domain.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    @Query(value = """
            SELECT * FROM users WHERE email = :email
    """, nativeQuery = true)
    Optional<User> findByEmail(@Param("email") String email);
}
