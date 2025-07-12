package com.example.ecsite.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "administrators")
@Getter
@Setter
@NoArgsConstructor
public class Administrator extends BaseEntity implements Serializable {
    @Column(nullable = false)
    private String password;
}
