package services;

import entities.TodoList;

public interface TodoListService {
    TodoList[] getTodoList(); // Mengambil semua TodoList
    void addTodoList(String todo); // Menambahkan TodoList baru
    Boolean removeTodoList(Integer number); // Menghapus TodoList berdasarkan ID
    Boolean editTodoList(Integer number, String todo); // Mengedit TodoList berdasarkan ID
}
