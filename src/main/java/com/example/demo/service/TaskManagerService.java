package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.TaskManager;

public interface TaskManagerService 
{
	public List<TaskManager> findAll();
	public TaskManager findById(Long id);
	public void SaveOrUpdate(TaskManager ts);
	public void deleteById(Long id);

}
