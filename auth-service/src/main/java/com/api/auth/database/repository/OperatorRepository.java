package com.api.auth.database.repository;

import com.api.auth.database.entity.Operator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OperatorRepository extends JpaRepository<Operator, Long> {

    @Query(value = """
            SELECT
                  o.id                      AS id
                , o.operator_name           AS operatorName
                , o.is_active               AS isActive   
            FROM Operator AS o
            WHERE
                    :operatorId IS NULL OR o.id = :operatorId
                AND :operatorName IS NULL OR :operatorName = '' OR o.operator_name = :operatorName
        """, nativeQuery = true)
    List<Operator> findOperatorBy(Long operatorId, String operatorName);

    Optional<Operator> findByOperatorName(String operatorName);

    @Query(value = """
            SELECT EXISTS (
                SELECT 1
                FROM Operator as o
                LEFT JOIN OperatorToken as ot
                       ON ot.operatorId = o.id
                WHERE o.operatorName = :operatorName
                  AND ot.token = :token
            )
    """)
    boolean isValidOperatorNameAndToken(String operatorName, String token);
}
