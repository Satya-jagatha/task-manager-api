package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotBlank;

@Entity
public class TaskManager 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message="title cannot be blank:")
	private String title;
	
	@NotBlank(message="status cannot be blank:")
	private String status;
	
	private LocalDateTime createdAt;
	
	public TaskManager() {
		
	}
	public TaskManager(Long id, String title, String status, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.title = title;
		this.status = status;
		this.createdAt = createdAt;
	}
	
	@PrePersist
	public void onCreate()
	{
		this.createdAt = LocalDateTime.now();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	

}
