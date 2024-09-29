package net.javaprojects.todo.repository;

import net.javaprojects.todo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long > {
}
