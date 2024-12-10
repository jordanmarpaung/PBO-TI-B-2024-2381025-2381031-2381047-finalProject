import config.Database;
import repositories.TodoListRepository;
import repositories.TodoListRepositoryDbImpl;
import services.TodoListService;
import services.TodoListServiceImpl;
import views.TodoListTerminalViewImpl;

public class Main {
    public static void main(String[] args) {
        // Setup koneksi database
        Database database = new Database("game_marketplace", "root", "password", "localhost", "3306");
        database.setup(); // Menyiapkan koneksi ke database

        // Setup repository dengan menghubungkan ke database
        TodoListRepository todoListRepository = new TodoListRepositoryDbImpl(database);

        // Setup service untuk logika bisnis
        TodoListService todoListService = new TodoListServiceImpl(todoListRepository);

        // Setup view (UI) untuk interaksi dengan pengguna
        TodoListTerminalViewImpl todoListView = new TodoListTerminalViewImpl(TodoListService);

        // Menjalankan aplikasi
        todoListView.run();
    }
}v
