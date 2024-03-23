package com.kalyan.expenses.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EntityListeners({ AuditingEntityListener.class })
@MappedSuperclass
public class BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@CreatedDate
	@Column(name = "created_on", updatable = false)
	private LocalDateTime createdOn;

	@LastModifiedDate
	@Column(name = "updated_on")
	private LocalDateTime updatedOn;

	@CreatedBy
	@Column(name = "created_by", updatable = false)
	private Integer createdBy;

	@LastModifiedBy
	@Column(name = "updated_by")
	private Integer updatedBy;

	@Version
	private Integer version;
}
