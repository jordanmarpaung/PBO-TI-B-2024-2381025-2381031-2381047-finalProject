package views;

import entities.GameAccount;
import services.GameAccountService;
import java.util.Scanner;

public class TodoListTerminalViewImpl implements TodoListView {
    public static Scanner scanner = new Scanner(System.in);
    private final GameAccountService gameAccountService;

    public TodoListTerminalViewImpl(GameAccountService gameAccountService) {
        this.gameAccountService = gameAccountService;
    }

    // Method to get user input
    public String input(String info) {
        System.out.print(info + " : ");
        return scanner.nextLine();
    }

    // Main menu to choose actions
    public void showMainMenu() {
        boolean isRunning = true;
        while (isRunning) {
            showGameAccountList();
            System.out.println("MENU : ");
            System.out.println("1. Tambah Akun Game");
            System.out.println("2. Hapus Akun Game");
            System.out.println("3. Edit Akun Game");
            System.out.println("4. Keluar");
            String selectedMenu = input("Pilih");

            switch (selectedMenu) {
                case "1":
                    showMenuAddGameAccount();
                    break;
                case "2":
                    showMenuRemoveGameAccount();
                    break;
                case "3":
                    showMenuEditGameAccount();
                    break;
                case "4":
                    isRunning = false;
                    break;
                default:
                    System.out.println("Pilih menu dengan benar");
            }
        }
    }

    // Show all game accounts
    public void showGameAccountList() {
        System.out.println("DAFTAR AKUN GAME");
        GameAccount[] gameAccounts = gameAccountService.getGameAccounts();
        for (var i = 0; i < gameAccounts.length; i++) {
            var gameAccount = gameAccounts[i];
            if (gameAccount != null) {
                System.out.println((i + 1) + ". " + gameAccount.getGameName() + " - " + gameAccount.getPrice());
            }
        }
    }

    // Add new game account
    public void showMenuAddGameAccount() {
        System.out.println("MENAMBAH AKUN GAME");
        String gameName = input("Nama Game (x jika batal)");
        if (gameName.equals("x")) {
            return;
        }
        String accountDetails = input("Detail Akun (x jika batal)");
        if (accountDetails.equals("x")) {
            return;
        }
        double price = Double.parseDouble(input("Harga (x jika batal)"));
        if (price == 0) {
            return;
        }

        GameAccount newAccount = new GameAccount();
        newAccount.setGameName(gameName);
        newAccount.setAccountDetails(accountDetails);
        newAccount.setPrice(price);

        gameAccountService.addGameAccount(newAccount);
        System.out.println("Akun Game Berhasil Ditambahkan!");
    }

    // Remove a game account
    public void showMenuRemoveGameAccount() {
        System.out.println("MENGHAPUS AKUN GAME");
        String selectedAccountId = input("Masukkan ID Akun yang dihapus (x jika batal)");
        if (selectedAccountId.equals("x")) {
            return;
        }

        boolean success = gameAccountService.removeGameAccount(Integer.valueOf(selectedAccountId));
        if (!success) {
            System.out.println("Gagal menghapus akun game dengan ID: " + selectedAccountId);
        } else {
            System.out.println("Akun Game Berhasil Dihapus!");
        }
    }

    // Edit an existing game account
    public void showMenuEditGameAccount() {
        System.out.println("MENGEDIT AKUN GAME");
        String selectedAccountId = input("Masukkan ID Akun yang ingin diedit (x jika batal)");
        if (selectedAccountId.equals("x")) {
            return;
        }

        GameAccount selectedAccount = gameAccountService.getGameAccountById(Integer.valueOf(selectedAccountId));
        if (selectedAccount != null) {
            String newGameName = input("Masukkan Nama Game Baru (x jika batal)");
            if (newGameName.equals("x")) {
                return;
            }
            String newAccountDetails = input("Masukkan Detail Akun Baru (x jika batal)");
            if (newAccountDetails.equals("x")) {
                return;
            }
            double newPrice = Double.parseDouble(input("Masukkan Harga Baru (x jika batal)"));
            if (newPrice == 0) {
                return;
            }

            selectedAccount.setGameName(newGameName);
            selectedAccount.setAccountDetails(newAccountDetails);
            selectedAccount.setPrice(newPrice);

            boolean isSuccess = gameAccountService.updateGameAccount(selectedAccount);
            if (isSuccess) {
                System.out.println("Akun Game Berhasil Diedit!");
            } else {
                System.out.println("Gagal mengedit akun game.");
            }
        } else {
            System.out.println("Akun Game dengan ID " + selectedAccountId + " tidak ditemukan.");
        }
    }

    // Run the application
    @Override
    public void run() {
        showMainMenu();
    }
}
