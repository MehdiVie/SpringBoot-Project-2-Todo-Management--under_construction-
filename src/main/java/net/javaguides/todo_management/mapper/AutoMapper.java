package net.javaguides.todo_management.mapper;

import net.javaguides.todo_management.dto.TodoDto;
import net.javaguides.todo_management.entity.Todo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoMapper {
    AutoMapper Mapper = Mappers.getMapper(AutoMapper.class);

    TodoDto mapToTodoDto(Todo todo);
    Todo mapToTodo(TodoDto todoDto);
}
