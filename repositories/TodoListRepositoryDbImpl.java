package repositories;

import config.Database;
import entities.TodoList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementasi TodoListRepository menggunakan koneksi langsung ke database.
 */
public class TodoListRepositoryDbImpl implements TodoListRepository {

    private final Database database;

    // Constructor untuk inisialisasi koneksi database
    public TodoListRepositoryDbImpl(Database database) {
        this.database = database;
    }

    @Override
    public List<TodoList> getAll() {
        List<TodoList> todoLists = new ArrayList<>();
        String query = "SELECT * FROM todolist";  // Query untuk mengambil semua data TodoList
        try (Connection connection = database.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            // Mengambil setiap baris dari ResultSet dan membuat objek TodoList
            while (resultSet.next()) {
                TodoList todo = new TodoList();
                todo.setId(resultSet.getInt("id"));
                todo.setTodo(resultSet.getString("todo"));
                todoLists.add(todo);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving TodoLists from the database", e);
        }
        return todoLists;
    }

    @Override
    public void add(TodoList todoList) {
        String query = "INSERT INTO todolist (todo) VALUES (?)"; // Query untuk menambahkan TodoList
        try (Connection connection = database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, todoList.getTodo());  // Set nilai todo
            preparedStatement.executeUpdate();  // Menjalankan query
        } catch (SQLException e) {
            throw new RuntimeException("Error adding TodoList to the database", e);
        }
    }

    @Override
    public boolean remove(Integer id) {
        String query = "DELETE FROM todolist WHERE id = ?";  // Query untuk menghapus TodoList berdasarkan ID
        try (Connection connection = database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);  // Set ID yang akan dihapus
            return preparedStatement.executeUpdate() > 0;  // Mengecek apakah baris berhasil dihapus
        } catch (SQLException e) {
            throw new RuntimeException("Error removing TodoList from the database", e);
        }
    }

    @Override
    public boolean edit(TodoList todoList) {
        String query = "UPDATE todolist SET todo = ? WHERE id = ?";  // Query untuk mengedit TodoList berdasarkan ID
        try (Connection connection = database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, todoList.getTodo());  // Set nilai todo baru
            preparedStatement.setInt(2, todoList.getId());  // Set ID yang akan diubah
            return preparedStatement.executeUpdate() > 0;  // Mengecek apakah baris berhasil diperbarui
        } catch (SQLException e) {
            throw new RuntimeException("Error updating TodoList in the database", e);
        }
    }
}
