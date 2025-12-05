package com.api.auth.database.repository;

import com.api.auth.database.entity.Operator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperatorRepository extends JpaRepository<Operator, Long> {
}
