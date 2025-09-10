package net.javaguides.todo_management.controller;

import lombok.AllArgsConstructor;
import net.javaguides.todo_management.dto.TodoDto;
import net.javaguides.todo_management.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/todos")
public class TodoController {

    private TodoService todoService;

    // build Add Todo Rest API
    @PostMapping
    public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto) {
        TodoDto savedTodoDto = todoService.addTodo(todoDto);
        return new ResponseEntity<>(savedTodoDto, HttpStatus.CREATED);
    }

    // build Get Todo by Id Rest API
    @GetMapping("{id}")
    public ResponseEntity<TodoDto> getTodoById(@PathVariable("id") Long todoIid) {
        TodoDto todoDto = todoService.getTodoById(todoIid);
        return new ResponseEntity<>(todoDto, HttpStatus.OK);
    }

    // build Get All Todos Rest API
    @GetMapping
    public ResponseEntity<List<TodoDto>> getAllTodos() {
        List<TodoDto> todoDtos = todoService.getAllTodos();
        //return new ResponseEntity<>(todoDtos, HttpStatus.OK);
        return ResponseEntity.ok(todoDtos);
    }

    // build Update Todo with particular id
    @PutMapping("{id}")
    public ResponseEntity<TodoDto> updateTodoDto(@RequestBody TodoDto todoDto ,
                                                 @PathVariable("id") Long todoIid) {
        TodoDto updatedTodoDto = todoService.updateTodo(todoDto, todoIid);
        return ResponseEntity.ok(updatedTodoDto);
    }

    // build Delete Todo with particular id
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable("id") Long todoId) {
        todoService.deleteTodo(todoId);
        return ResponseEntity.ok("Todo deleted successfully");
    }

    // build Complete Todo
    @PatchMapping("{id}/complete")
    public ResponseEntity<TodoDto> completeTodo(@PathVariable("id") Long todoId) {
        TodoDto todoDto = todoService.completeTodo(todoId);
        return ResponseEntity.ok(todoDto);
    }

    // build Complete Todo
    @PatchMapping("{id}/in-complete")
    public ResponseEntity<TodoDto> inCompleteTodo(@PathVariable("id") Long todoId) {
        TodoDto todoDto = todoService.inCompleteTodo(todoId);
        return ResponseEntity.ok(todoDto);
    }

}
