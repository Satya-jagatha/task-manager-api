package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.dao.TaskManagerRepo;
import com.example.demo.entity.TaskManager;
import com.example.demo.exception.TaskNotFoundException;
import com.example.demo.service.TaskManagerService;
import com.example.demo.service.TaskManagerServiceImpl;

@ExtendWith(MockitoExtension.class)
public class TaskManagerServiceTest 
{
	@Mock
	private TaskManagerRepo taskRepo;
	
	@InjectMocks
	private TaskManagerServiceImpl taskService;
	
	private TaskManager task;
	
	@BeforeEach
	void setup()
	{
		task = new TaskManager();
		task.setId(1L);
		task.setStatus("Open");
		task.setTitle("DSA");
	}
	
	@Test
	void findAll_Success()
	{
		when(taskRepo.findAll()).thenReturn(List.of(task));
		
		List<TaskManager> result = taskService.findAll();
		
		
		assertEquals(1,result.size());
		assertEquals("DSA",result.get(0).getTitle());
		
	}
	
	@Test
	void findById_Success()
	{
		when(taskRepo.findById(1L)).thenReturn(Optional.of(task));
		TaskManager result = taskService.findById(1L);
		
		assertNotNull(result);
		assertEquals(1L,result.getId());
		assertEquals("DSA",result.getTitle());
	}
	
	@Test
	void findById_NotSuccess()
	{
		when(taskRepo.findById(10L)).thenReturn(Optional.empty());
		assertThrows(TaskNotFoundException.class, () -> {
			taskService.findById(10L);
		});
	}
	
	@Test
	void saveOrUpdate_Success()
	{
		taskService.SaveOrUpdate(task);
		
		verify(taskRepo,times(1)).save(task);
	}
	
	@Test
	void deleteById_Success()
	{
		when(taskRepo.findById(1L)).thenReturn(Optional.of(task));
		
		taskService.deleteById(1L);
		
		verify(taskRepo,times(1)).deleteById(1L);
		
	}
	
	@Test
	void deleteById_NotSuccess()
	{
		when(taskRepo.findById(10L)).thenReturn(Optional.empty());
		assertThrows(TaskNotFoundException.class, () -> {
			taskService.deleteById(10L);
		});
		
		verify(taskRepo, never()).deleteById(10L);
		
	}
}
