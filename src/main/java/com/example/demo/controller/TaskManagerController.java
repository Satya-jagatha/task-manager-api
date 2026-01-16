package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.TaskManager;
import com.example.demo.service.TaskManagerService;

@RestController
@RequestMapping("/task")
public class TaskManagerController 
{
	@Autowired
	TaskManagerService tm;
	
	@GetMapping("/find")
	public List<TaskManager> findAll()
	{
		return tm.findAll();
	}
	
	@GetMapping("/find/{id}")
	public TaskManager findById(@PathVariable Long id)
	{
		return tm.findById(id);
	}
	
	@PostMapping("/save")
	public TaskManager save(@RequestBody TaskManager t)
	{
		tm.SaveOrUpdate(t);
		return t;
	}
	
	@PutMapping("/update")
	public TaskManager update(@RequestBody TaskManager t)
	{
		tm.SaveOrUpdate(t);
		return t;
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteById(@PathVariable Long id)
	{
		tm.deleteById(id);
		return "Record with id : "+id+" was successully deleted";
	}

}
