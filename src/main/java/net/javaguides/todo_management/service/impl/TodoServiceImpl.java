package net.javaguides.todo_management.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.todo_management.dto.TodoDto;
import net.javaguides.todo_management.entity.Todo;
import net.javaguides.todo_management.exception.ResourceNotFoundException;
import net.javaguides.todo_management.mapper.AutoMapper;
import net.javaguides.todo_management.repository.TodoRepository;
import net.javaguides.todo_management.service.TodoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {

    private TodoRepository todoRepository;


    @Override
    public TodoDto addTodo(TodoDto todoDto) {
        // Convert TodoDto object to Todo Jpa object
        Todo todo = AutoMapper.Mapper.mapToTodo(todoDto);
        // Save Todo Jpa object to DB
        Todo savedTodo = todoRepository.save(todo);
        // Convert Todo Jpa object to TodoDto object
        TodoDto savedTodoDto = AutoMapper.Mapper.mapToTodoDto(savedTodo);
        return savedTodoDto;
    }

    @Override
    public TodoDto getTodoById(Long id) {
        // Get Todo Jpa particular object from DB
        Todo todo = todoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Todo Not Found by Id " + id)
        );
        // Convert Todo Jpa object to TodoDto object
        TodoDto todoDto = AutoMapper.Mapper.mapToTodoDto(todo);
        return todoDto;
    }

    @Override
    public List<TodoDto> getAllTodos() {
        // Get All Todos Jpa objects from DB
        List<Todo> todos = todoRepository.findAll();
        // Convert Todo Jpa objects to TodoDto objects with Struct Mapper
        List<TodoDto> todoDtos = todos.stream().map((todo) -> AutoMapper.Mapper.mapToTodoDto(todo))
                .collect(Collectors.toUnmodifiableList());
        return todoDtos;
    }

    @Override
    public TodoDto updateTodo(TodoDto todoDto , Long id) {
        // Get Todo Jpa particular object from DB
        Todo todo = todoRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Todo Not Found by Id " + id)
                );
        // change value of the object with the passed id
        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setCompleted(todoDto.getCompleted());
        // update object  with the new values
        Todo updatedTodo = todoRepository.save(todo);

        return AutoMapper.Mapper.mapToTodoDto(updatedTodo);

    }

    @Override
    public void deleteTodo(Long id) {
        // Get Todo Jpa particular object from DB
        Todo todo = todoRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Todo Not Found by Id " + id)
                );
        todoRepository.deleteById(id);
    }

    @Override
    public TodoDto completeTodo(Long id) {
        // Get Todo Jpa particular object from DB
        Todo todo = todoRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Todo Not Found by Id " + id)
                );
        todo.setCompleted(Boolean.TRUE);
        Todo updatedTodo = todoRepository.save(todo);
        return AutoMapper.Mapper.mapToTodoDto(updatedTodo);
    }

    @Override
    public TodoDto inCompleteTodo(Long id) {
        // Get Todo Jpa particular object from DB
        Todo todo = todoRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Todo Not Found by Id " + id)
                );
        todo.setCompleted(Boolean.FALSE);
        Todo updatedTodo = todoRepository.save(todo);
        return AutoMapper.Mapper.mapToTodoDto(updatedTodo);
    }
}
