package repositories;

import entities.TodoList;
import java.util.List;

/**
 * Interface untuk repository TodoList yang akan mengatur operasi CRUD.
 */
public interface TodoListRepository {
    // Mengambil semua TodoList dari database
    List<TodoList> getAll();

    // Menambahkan TodoList baru ke database
    void add(TodoList todoList);

    // Menghapus TodoList berdasarkan ID
    boolean remove(Integer id);

    // Mengedit TodoList yang sudah ada
    boolean edit(TodoList todoList);
}
