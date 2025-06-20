import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.Scanner;

public class TaskBuddyApp {
    private User currentUser = null;
    private UserQueueManager userQueueManager = new UserQueueManager();
    private UserManager userManager = new UserManager(userQueueManager);
    private TaskTreeManager taskTreeManager = new TaskTreeManager();
    private LogHistory activityLog = new LogHistory();
    private TaskOperations taskOperations = new TaskOperations();
    private Scanner scanner = new Scanner(System.in);

    //untuk formating CLI
    private static final String SEPARATOR = "=".repeat(63);
    private static final String LINE = "-".repeat(61);
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");


    public void start() {
        displayWelcome();
        while (true) {
            if (currentUser == null) {
                displayLoginRegisterMenu();
            }
        }
    }

    private void displayWelcome() {
        System.out.println(SEPARATOR);
        System.out.println("                    TASKBUDDY APPLICATION                  ");
         System.out.println("                    Sistem Manajemen Tugas                ");
        System.out.println(SEPARATOR + "\n");
    }

    private void displayLoginRegisterMenu() {
        System.out.println(" " + LINE);
        System.out.println("│                         MENU UTAMA                          │");
        System.out.println(" " + LINE);
        System.out.println("│  [1] Login ke Akun                                          │");
        System.out.println("│  [2] Registrasi Akun Baru                                   │");
        System.out.println("│  [3] Keluar dari Aplikasi                                   │");
        System.out.println(" " + LINE);
        System.out.print("\nPilih menu [1-3]: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> handleLogin();
            case 2 -> handleRegistration();
            case 3 -> {
                System.out.println("\n" + SEPARATOR);
                System.out.println("         Terima kasih telah menggunakan TaskBuddy!       ");
                System.out.println(SEPARATOR);
                System.exit(0);
            }
            default -> System.out.println("\n[!] Pilihan tidak valid. Silakan pilih 1-3.");
        }
    }

    private void handleLogin() {
        System.out.println(" " + LINE);
        System.out.println("│                           LOGIN                             │");
        System.out.println(" " + LINE);
        System.out.print("Masukkan Username: ");
        String username = scanner.nextLine();
        System.out.print("Masukkan Password: ");
        String password = scanner.nextLine();

        currentUser = userManager.loginUser(username, password);
        if (currentUser != null) {
            System.out.println("\nLogin berhasil!");
            System.out.println("Selamat datang, " + currentUser.getUsername() + " (" + currentUser.getRole() + ") !");
            displayMainMenu();
        } else {
            System.out.println("\nLogin gagal. Periksa kembali username dan password Anda.");
        }
    }

    private void handleRegistration() {
        System.out.println(" " + LINE);
        System.out.println("│                         REGISTRASI                          │");
        System.out.println(" " + LINE);
        System.out.print("Masukkan Username Baru: ");
        String username = scanner.nextLine();
        System.out.print("Masukkan Password: ");
        String password = scanner.nextLine();
        System.out.print("Masukkan Role [admin/member]: ");
        String role = scanner.nextLine();

        if (userManager.registerUser(username, password, role)) {
            System.out.println("\nRegistrasi berhasil! Silakan login untuk melanjutkan.");
        } else {
            System.out.println("\nRegistrasi gagal. Username sudah digunakan.");
        }
    }

    private void displayMainMenu() {
        while (currentUser != null) {
            if (currentUser.getRole().equalsIgnoreCase("Admin")) {
                displayAdminMenu();
            } else {
                displayMemberMenu();
            }
        }
    }

    private void displayUserInfo() {
        System.out.println(" " + LINE);
        System.out.println("│ User: " + String.format("%-15s", currentUser.getUsername()) + 
                          " | Role: " + String.format("%-10s", currentUser.getRole()) + 
                          " | " + java.time.LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) + " │");
        System.out.println(LINE);
    }

    private void displayAdminMenu() {
        System.out.println(" " + LINE);
        System.out.println("│                         MENU ADMIN                          │");
        System.out.println(" " + LINE);
        System.out.println("│  [1] Tambah Tugas Utama                                     │");
        System.out.println("│  [2] Tambah Subtugas                                        │");
        System.out.println("│  [3] Lihat Semua Tugas                                      │");
        System.out.println("│  [4] Hapus Tugas                                            │");
        System.out.println("│  [5] Cari Tugas                                             │");
        System.out.println("│  [6] Urutkan Tugas                                          │");
        System.out.println("│  [7] Tandai Tugas Selesai                                   │");
        System.out.println("│  [8] Lihat Log Aktivitas                                    │");
        System.out.println("│  [9] Undo Aktivitas                                         │");
        System.out.println("│  [10] Redo Aktivitas                                        │");
        System.out.println("│  [11] Lihat Antrian User                                    │");
        System.out.println("│  [12] Proses User Berikutnya                                │");
        System.out.println("│  [13] Hapus Member                                          │");
        System.out.println("│  [0] Logout                                                 │");
        System.out.println(" " + LINE);
        System.out.print("\nPilih menu: ");
        int adminChoice = scanner.nextInt();
        scanner.nextLine();

        switch (adminChoice) {
            case 1 -> addTask();
            case 2 -> addSubtask();
            case 3 -> viewTasks();
            case 4 -> deleteTask();
            case 5 -> searchTask();
            case 6 -> sortTasks();
            case 7 -> markTaskDone();
            case 8 -> viewActivityLog();
            case 9 -> undoActivity();
            case 10 -> redoActivity();
            case 11 -> userQueueManager.displayQueue();
            case 12 -> userQueueManager.getNextUser();
            case 13 -> removeUserFromQueue();
            case 0 -> currentUser = null;
            default -> System.out.println("[!] Pilihan tidak valid. Silahkan coba lagi.");
        }
    }

    private void displayMemberMenu() {
        System.out.println(" " + LINE);
        System.out.println("│                         MENU MEMBER                         │");
        System.out.println(" " + LINE);
        System.out.println("│  [1] Tambah Subtugas                                        │");
        System.out.println("│  [2] Lihat Semua Tugas                                      │");
        System.out.println("│  [3] Hapus Tugas                                            │");
        System.out.println("│  [4] Cari Tugas                                             │");
        System.out.println("│  [5] Urutkan Tugas                                          │");
        System.out.println("│  [6] Tandai Tugas Selesai                                   │");
        System.out.println("│  [7] Undo Aktivitas                                         │");
        System.out.println("│  [8] Redo Aktivitas                                         │");
        System.out.println("│  [9] Lihat Antrian User                                     │");
        System.out.println("│  [10] Proses User Berikutnya                                │");
        System.out.println("│  [0] Logout                                                 │");
        System.out.println(" " + LINE);
        System.out.print("\nPilih menu: ");
        int userChoice = scanner.nextInt();
        scanner.nextLine();

        switch (userChoice) {
            case 1 -> addSubtask();
            case 2 -> viewTasks();
            case 3 -> deleteTask();
            case 4 -> searchTask();
            case 5 -> sortTasks();
            case 6 -> markTaskDone();
            case 7 -> undoActivity();
            case 8 -> redoActivity();
            case 9 -> userQueueManager.displayQueue();
            case 10 -> userQueueManager.getNextUser();
            case 0 -> currentUser = null;
            default -> System.out.println("[!]Pilihan tidak valid. Silahkan coba lagi.");
        }
    }

    private void addTask() {
        System.out.println(" " + LINE);
        System.out.println("│                   TAMBAH TUGAS UTAMA                        │");
        System.out.println(" " + LINE);

        System.out.print("Nama Tugas: ");
        String name = scanner.nextLine();
        System.out.print("Deskripsi: ");
        String description = scanner.nextLine();

        System.out.print("\nPrioritas: \n");
        System.out.println("[1] High (Urgent)");
        System.out.println("[2] Medium (Normal)");
        System.out.println("[3] Low (Tidak Urgent)");
        System.out.print("Pilih prioritas [1-3]: ");
        int priority = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Deadline (YYYY-MM-DD): ");
        LocalDate deadline = LocalDate.parse(scanner.nextLine());

        Task newTask = new SimpleTask(name, description, priority, deadline);
        taskTreeManager.addMainTask(newTask);
        activityLog.addLog("Tambah", "Tugas utama: " + name);
        
        System.out.println("\nTugas utama berhasil ditambahkan!");
        pressEnterToContinue();
    }

    private void addSubtask() {
        System.out.println("" + LINE);
        System.out.println("│                       TAMBAH SUBTUGAS                       │");
        System.out.println("" + LINE);
        
        System.out.print("Nama Tugas Induk: ");
        String parentName = scanner.nextLine();
        TaskNode parentNode = taskTreeManager.findTaskNode(parentName);

        if (parentNode == null) {
            System.out.println("\nTugas induk tidak ditemukan.");
            return;
        }

        System.out.print("Nama Subtugas: ");
        String name = scanner.nextLine();
        System.out.print("Deskripsi: ");
        String description = scanner.nextLine();

        System.out.println("\nPrioritas:\n");
        System.out.println("[1] High (Urgent)");
        System.out.println("[2] Medium (Normal)");
        System.out.println("[3] Low (Tidak Urgent)");
        System.out.print("Pilih prioritas [1-3]: ");
        int priority = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Deadline (YYYY-MM-DD): ");
        LocalDate deadline = LocalDate.parse(scanner.nextLine());

        Task newSubtask = new SimpleTask(name, description, priority, deadline);
        if (taskTreeManager.addSubtask(parentName, newSubtask)) {
            activityLog.addLog("Tambah", "Subtugas " + name + " di bawah " + parentName);
            System.out.println("\nSubtugas berhasil ditambahkan!");
        } else {
            System.out.println("\nGagal menambahkan subtugas. Pastikan tugas induk ada.");
        }
        pressEnterToContinue();
    }

    private void viewTasks() {
        System.out.println(SEPARATOR);
        System.out.println("│                     DAFTAR SEMUA TUGAS                      │");
        System.out.println(SEPARATOR);

        LinkedList<Task> allTasks = taskTreeManager.getAllTasksRecursive();
        if (allTasks.isEmpty()) {
            System.out.println("\n[i] Belum ada tugas yang ditambahkan.");
        } else {
            System.out.println("\nSTRUKTUR TUGAS (Hierarki):");
            System.out.println(LINE);
            taskTreeManager.displayTree();
            
            System.out.println("\nDETAIL TUGAS:");
            System.out.println(LINE);
            System.out.printf("%-10s %-13s %-13s %-12s %-8s%n", 
                            "NAMA", "DESKRIPSI", "PRIORITAS", "DEADLINE", "STATUS");
            System.out.println(LINE);
            
            for (Task task : allTasks) {
                String priorityText;
                if (task.getPriority() == 1) {
                    priorityText = "HIGH";
                } else if (task.getPriority() == 2) {
                    priorityText = "MEDIUM";
                } else if (task.getPriority() == 3) {
                    priorityText = "LOW";
                } else {
                    priorityText = "UNKNOWN";
                }
                
                String statusText = task.isCompleted() ? "[SELESAI]" : "[AKTIF]";
                String deadlineText = task.getDeadline().format(DATE_FORMAT);
                
                String taskName = task.getName();
                if (taskName.length() > 18) {
                    taskName = taskName.substring(0, 15) + "...";
                }
                
                String taskDesc = task.getDescription();
                if (taskDesc.length() > 23) {
                    taskDesc = taskDesc.substring(0, 20) + "...";
                }
                
                System.out.printf("%-10s %-13s %-13s %-12s %-8s%n",
                                taskName,
                                taskDesc,
                                priorityText,
                                deadlineText,
                                statusText);
            }
            System.out.println(LINE);
            System.out.println("Total tugas: " + allTasks.size());
        }
        pressEnterToContinue();
    }

    private void deleteTask() {
        System.out.println(" " + LINE);
        System.out.println("│                         HAPUS TUGAS                         │");
        System.out.println(" " + LINE);

        System.out.print("Masukkan nama tugas yang ingin dihapus: ");
        String taskName = scanner.nextLine();
        if (taskTreeManager.removeTask(taskName)) {
            activityLog.addLog("Hapus", "Tugas " + taskName);
            System.out.println("\nTugas berhasil dihapus.");
        } else {
            System.out.println("\nTugas tidak ditemukan.");
        }
        pressEnterToContinue();
    }

    private void searchTask() {
        System.out.println(" " + LINE);
        System.out.println("│                        CARI TUGAS                           │");
        System.out.println(" " + LINE);
        
        System.out.print("Masukkan nama tugas yang ingin dicari: ");
        String taskName = scanner.nextLine(); //menerima input kata kunci dari user
        LinkedList<Task> tasks = taskTreeManager.getAllTasksRecursive(); //mengambil semua tugas utama dan sub tugas dari TaskTreeManager dalam LinkedList
        LinkedList<Task> foundTasks = taskOperations.linearSearchTasks(tasks, taskName); //memanggil method linearSearchTasks dari file TaskOperations untuk mencari tugas berdasarkan kata kunci
        
        System.out.println("\nHASIL PENCARIAN untuk '" + taskName + "':");
        System.out.println(" " + LINE);

        if (foundTasks.isEmpty()) { //memeriksa apakah daftar tugas yg ditemukan kosong
            System.out.println("[i] Tidak ada tugas yang ditemukan dengan kata kunci '" + taskName + "'."); //jika kosong ini di print
        } else { //jika ada berarti tugas ditemukan
            for (Task t : foundTasks) { //melakukan iterasi pada setiap tugas yg ditemukan
                System.out.println(t.getTaskInfo()); //mencetak informasi detail dari setiap tugas yg ditemukan
            }
        }
        pressEnterToContinue();
    }

    private void sortTasks() {
        System.out.println(" " + LINE);
        System.out.println("│                       URUTAN TUGAS                         │");
        System.out.println(" " + LINE);
        
        LinkedList<Task> tasks = taskTreeManager.getAllTasksRecursive(); //engambil semua tugas utama dan sub tugas dari TaskTreeManager
        taskOperations.bubbleSortTasks(tasks); //memanggil method bubbleSortTasks untuk mengurutkan daftar tugas yg telah di ambil
        //kondisi jika daftar tugas ada
        for (Task t : tasks) { //iterasi pada setiap tugas dalam daftar yg sudah diurutkan
            System.out.println(t.getTaskInfo()); //Mencetak informasi detail dari setiap tugas yg sudah terurut
        }
        System.out.println("Tugas berhasil diurutkan berdasarkan prioritas dan deadline."); //konfirmasi pesan bahwa pengurutan berhasil

        if (tasks.isEmpty()) { //memeriksa apakah daftar tugas kosong
            System.out.println("\n[i] Tidak ada tugas untuk diurutkan."); //jika kosong ini di print
            return; //eksekusi dihentikan karena tidak ada yg perlu ditampilkan
        }
        pressEnterToContinue();
    }

    private void markTaskDone() {
        System.out.println(" " + LINE);
        System.out.println("│                     TANDAI TUGAS SELESAI                    │");
        System.out.println(" " + LINE);

        System.out.print("Masukkan nama tugas yang sudah selesai: ");
        String taskName = scanner.nextLine();
        TaskNode taskNode = taskTreeManager.findTaskNode(taskName);
        if (taskNode != null) {
            taskNode.getTask().setCompleted(true);
            activityLog.addLog("Selesai", "Tugas " + taskName);
            System.out.println("\nTugas berhasil ditandai selesai.");
        } else {
            System.out.println("\nTugas tidak ditemukan.");
        }
        pressEnterToContinue();
    }

    private void viewActivityLog() {
        System.out.println(" " + LINE);
        System.out.println("│                      RIWAYAT AKTIVITAS                      │");
        System.out.println(" " + LINE);
        activityLog.displayLogs();
        pressEnterToContinue();
    }

    private void undoActivity() {
        System.out.println(" " + LINE);
        System.out.println("│                        UNDO AKTIVITAS                       │");
        System.out.println(" " + LINE);
        activityLog.undo();
        pressEnterToContinue();
    }

    private void redoActivity() {
        System.out.println(" " + LINE);
        System.out.println("│                        REDO AKTIVITAS                       │");
        System.out.println(" " + LINE);
        activityLog.redo();
        pressEnterToContinue();
    }

    private void removeUserFromQueue() {
        System.out.println(" " + LINE);
        System.out.println("│                        HAPUS MEMBER                         │");
        System.out.println(" " + LINE);

        System.out.print("Masukkan username member yang ingin dihapus: ");
        String usernameToDelete = scanner.nextLine();
        if (userManager.deleteUser(usernameToDelete)) {
            System.out.println("\nMember " + usernameToDelete + " berhasil dihapus.");
        } else {
            System.out.println("\nGagal menghapus member. Pastikan username benar.");
        }
        pressEnterToContinue();
    }

    private void pressEnterToContinue() {
        System.out.print("\nTekan Enter untuk melanjutkan...");
        scanner.nextLine();
    }

    public static void main(String[] args) {
        TaskBuddyApp app = new TaskBuddyApp();
        app.start();
    }
}
