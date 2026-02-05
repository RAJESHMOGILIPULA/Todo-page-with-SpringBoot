package com.in28minutes.springboot.myfirstwebapp.todo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Integer>{
	public List<Todo> findByUsername(String username);
	public Optional<Todo> findByIdAndUsername(int id, String username);
	public void deleteByIdAndUsername(int id, String username);
}
