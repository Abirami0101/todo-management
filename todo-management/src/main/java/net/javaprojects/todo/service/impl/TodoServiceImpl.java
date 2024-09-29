package net.javaprojects.todo.service.impl;

import lombok.AllArgsConstructor;
import net.javaprojects.todo.dto.TodoDto;
import net.javaprojects.todo.entity.Todo;
import net.javaprojects.todo.exception.ResourceNotFoundException;
import net.javaprojects.todo.repository.TodoRepository;
import net.javaprojects.todo.service.TodoService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {
    private TodoRepository todoRepository;
    private ModelMapper modelMapper;
    @Override
    public TodoDto addTodo(TodoDto todoDto) {
        //Convert TodoDto to Todo
//        Todo todo=new Todo();
//        todo.setCompleted(todoDto.isCompleted());
//        todo.setDescription(todoDto.getDescription());
//        todo.setTitle(todoDto.getTitle());
        Todo todo=modelMapper.map(todoDto, Todo.class);

        //Save Todo
        Todo savedTodo=todoRepository.save(todo);

        //Convert Todo to TodoDto
//        TodoDto savedTodoDto=new TodoDto();
//        savedTodoDto.setId(todo.getId());
//        savedTodoDto.setCompleted(todo.isCompleted());
//        savedTodoDto.setDescription(todo.getDescription());
//        savedTodoDto.setTitle(todo.getTitle());
        TodoDto savedTodoDto=modelMapper.map(savedTodo,TodoDto.class);
        return savedTodoDto;
    }

    @Override
    public TodoDto getTodo(Long id) {
        Todo todo=todoRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Todo not found for id"+id));
        return modelMapper.map(todo, TodoDto.class);
    }

    @Override
    public List<TodoDto> getAllTodos() {
        List<Todo> todos=todoRepository.findAll();
        return todos.stream().map((todo) ->modelMapper.map(todo,TodoDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public TodoDto updateTodo(TodoDto todoDto, Long id) {
        Todo todo=todoRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Todo is not found for id " +id));
        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setCompleted(todoDto.isCompleted());

        Todo updatedTodo=todoRepository.save(todo);
        return modelMapper.map(updatedTodo,TodoDto.class);
    }

    @Override
    public void deleteTodo(Long id) {
        Todo todo=todoRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Todo is not found for id " +id));
        todoRepository.delete(todo);
    }

    @Override
    public TodoDto completeTodo(Long id) {
        Todo todo=todoRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Todo is not found for id " +id));
        todo.setCompleted(Boolean.TRUE);
        Todo updateTodo=todoRepository.save(todo);
        return modelMapper.map(updateTodo,TodoDto.class);
    }

    @Override
    public TodoDto inCompleteTodo(Long id) {
        Todo todo=todoRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Todo is not found for id " +id));
        todo.setCompleted(Boolean.FALSE);
        Todo updatedTodo=todoRepository.save(todo);
        return modelMapper.map(updatedTodo, TodoDto.class);
    }
}
