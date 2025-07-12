package com.example.ecsite.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public abstract class UserAuditEntity extends BaseEntity {
    @CreatedBy
    @Column(name = "created_by", nullable = false)
    private Administrator creator;

    @LastModifiedBy
    @Column(name = "updated_by", nullable = false)
    private Administrator updater;
}
