package entities;

/**
 * Kelas entity TodoList yang merepresentasikan data di tabel database.
 */
public class TodoList {
    private Integer id; // ID unik dari todo list (Primary Key di database)
    private String namaAkunGame; // Nama akun game
    private String status; // Status todo (e.g., "Pending", "Completed")
    private Double harga; // Harga terkait akun game
    private String emailPengguna; // Email pengguna yang terkait

    // Constructor tanpa parameter (default)
    public TodoList() {
    }

    // Constructor dengan parameter
    public TodoList(Integer id, String namaAkunGame, String status, Double harga, String emailPengguna) {
        this.id = id;
        this.namaAkunGame = namaAkunGame;
        this.status = status;
        this.harga = harga;
        this.emailPengguna = emailPengguna;
    }

    // Getter dan Setter untuk ID
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // Getter dan Setter untuk Nama Akun Game
    public String getNamaAkunGame() {
        return namaAkunGame;
    }

    public void setNamaAkunGame(String namaAkunGame) {
        this.namaAkunGame = namaAkunGame;
    }

    // Getter dan Setter untuk Status
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Getter dan Setter untuk Harga
    public Double getHarga() {
        return harga;
    }

    public void setHarga(Double harga) {
        this.harga = harga;
    }

    // Getter dan Setter untuk Email Pengguna
    public String getEmailPengguna() {
        return emailPengguna;
    }

    public void setEmailPengguna(String emailPengguna) {
        this.emailPengguna = emailPengguna;
    }

    // Override toString untuk debugging atau log output
    @Override
    public String toString() {
        return "TodoList{" +
                "id=" + id +
                ", namaAkunGame='" + namaAkunGame + '\'' +
                ", status='" + status + '\'' +
                ", harga=" + harga +
                ", emailPengguna='" + emailPengguna + '\'' +
                '}';
    }
}
