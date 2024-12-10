package repositories;

import config.Database;
import entities.TodoList;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementasi repository TodoList yang menghubungkan ke database.
 */
public class TodoListRepositoryImpl implements TodoListRepository {
    private final Database database;

    // Constructor untuk inisialisasi koneksi database
    public TodoListRepositoryImpl(Database database) {
        this.database = database;
    }

    @Override
    public List<TodoList> getAll() {
        List<TodoList> todoLists = new ArrayList<>();
        String query = "SELECT * FROM todolist";
        try (Connection connection = database.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                TodoList todo = new TodoList(
                        resultSet.getInt("id"),
                        resultSet.getString("nama_akun_game"),
                        resultSet.getString("status"),
                        resultSet.getDouble("harga"),
                        resultSet.getString("email_pengguna")
                );
                todoLists.add(todo);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving TodoLists", e);
        }
        return todoLists;
    }

    @Override
    public void add(TodoList todoList) {
        String query = "INSERT INTO todolist (nama_akun_game, status, harga, email_pengguna) VALUES (?, ?, ?, ?)";
        try (Connection connection = database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, todoList.getNamaAkunGame());
            preparedStatement.setString(2, todoList.getStatus());
            preparedStatement.setDouble(3, todoList.getHarga());
            preparedStatement.setString(4, todoList.getEmailPengguna());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error adding TodoList", e);
        }
    }

    @Override
    public boolean remove(Integer id) {
        String query = "DELETE FROM todolist WHERE id = ?";
        try (Connection connection = database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error removing TodoList", e);
        }
    }

    @Override
    public boolean edit(TodoList todoList) {
        String query = "UPDATE todolist SET nama_akun_game = ?, status = ?, harga = ?, email_pengguna = ? WHERE id = ?";
        try (Connection connection = database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, todoList.getNamaAkunGame());
            preparedStatement.setString(2, todoList.getStatus());
            preparedStatement.setDouble(3, todoList.getHarga());
            preparedStatement.setString(4, todoList.getEmailPengguna());
            preparedStatement.setInt(5, todoList.getId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error updating TodoList", e);
        }
    }
}
