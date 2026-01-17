package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.TaskManagerRepo;
import com.example.demo.entity.TaskManager;
import com.example.demo.exception.TaskNotFoundException;

@Service
public class TaskManagerServiceImpl implements TaskManagerService
{
	@Autowired
	TaskManagerRepo tm;

	
	public List<TaskManager> findAll() {
		// TODO Auto-generated method stub
		return tm.findAll();
	}


	public TaskManager findById(Long id) {
		// TODO Auto-generated method stub
		return tm.findById(id)
				.orElseThrow(() -> new TaskNotFoundException("The task object not found with the id :"+id));
	}

	
	public void SaveOrUpdate(TaskManager ts) {
		// TODO Auto-generated method stub
		tm.save(ts);
		
	}

	
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		TaskManager task = tm.findById(id)
		        .orElseThrow(() -> new TaskNotFoundException(
		            "The task object not found with the id :" + id
		        ));
		tm.deleteById(id);
	}

}
