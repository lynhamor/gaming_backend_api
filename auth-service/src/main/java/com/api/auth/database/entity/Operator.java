package com.api.auth.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "operator")
public class Operator extends BaseEntity<Long>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "operator_name", nullable = false, length = 100)
    private String operatorName;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "is_active")
    private Boolean isActive = true;

}
