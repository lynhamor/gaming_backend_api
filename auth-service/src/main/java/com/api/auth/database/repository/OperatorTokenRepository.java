package com.api.auth.database.repository;

import com.api.auth.database.entity.OperatorToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OperatorTokenRepository extends JpaRepository<OperatorToken, Long> {
    OperatorToken findByOperatorIdAndToken(Long operatorId, String token);

//    @Query("""
//        SELECT
//            ot
//        FROM OperatorToken AS ot
//        LEFT JOIN
//            Operator AS o
//            ON o.id = ot.operatorId
//        WHERE
//            o.operatorName = :username
//            AND ot.token = :token
//    """)
//    Optional<OperatorToken> findByUsernameAndToken(String username, String token);
}
