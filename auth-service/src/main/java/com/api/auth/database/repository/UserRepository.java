package com.api.auth.database.repository;

import com.api.auth.database.entity.User;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(@Size(max = 100) @NotNull String username);

    @Query(value = """
            SELECT EXISTS (
                SELECT 1
                FROM User as u
                WHERE u.username = :username
                  AND u.password = :password
            )
    """)
    boolean isValidUsernameAndToken(String username, String password);
}
