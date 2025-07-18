package com.example.ecsite.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.userdetails.User;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public abstract class BaseAuditorEntity extends BaseEntity {
    @CreatedBy
    @Column(name = "created_by", nullable = false)
    private User creator;

    @LastModifiedBy
    @Column(name = "updated_by", nullable = false)
    private User updater;
}
