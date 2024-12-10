package services;

import entities.TodoList;
import repositories.TodoListRepository;
import repositories.TodoListRepositoryDbImpl;

import java.util.List;

public class TodoListServiceImpl implements TodoListService {

    private final TodoListRepository todoListRepository;

    // Constructor untuk menginisialisasi repository
    public TodoListServiceImpl(TodoListRepository todoListRepository) {
        this.todoListRepository = todoListRepository;
    }

    @Override
    public TodoList[] getTodoList() {
        List<TodoList> todoLists = todoListRepository.getAll(); // Mengambil semua TodoList
        return todoLists.toArray(new TodoList[0]); // Mengkonversi List ke Array
    }

    @Override
    public void addTodoList(String todo) {
        TodoList newTodo = new TodoList();
        newTodo.setTodo(todo); // Menambahkan todo ke objek TodoList baru
        todoListRepository.add(newTodo); // Menambahkan TodoList ke repository
    }

    @Override
    public Boolean removeTodoList(Integer number) {
        return todoListRepository.remove(number); // Menghapus TodoList berdasarkan ID
    }

    @Override
    public Boolean editTodoList(Integer number, String todo) {
        TodoList todoList = new TodoList();
        todoList.setId(number); // Mengatur ID TodoList yang ingin diubah
        todoList.setTodo(todo); // Mengatur nilai todo yang baru
        return todoListRepository.edit(todoList); // Mengupdate TodoList di repository
    }
}
