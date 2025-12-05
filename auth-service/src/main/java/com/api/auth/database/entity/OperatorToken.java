package com.api.auth.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "operator_token")
public class OperatorToken extends BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "operator_id", nullable = false)
    private Long operatorId;

    @Column(name = "token", nullable = false, length = 100)
    private String token;
}
