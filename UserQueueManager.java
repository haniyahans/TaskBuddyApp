import java.util.LinkedList;
import java.util.Queue;

public class UserQueueManager {

    // Mendeklarasikan struktur data Queue menggunakan LinkedList untuk menyimpan objek User
    private Queue<User> userQueue = new LinkedList<>();

    // Method untuk menambahkan user ke dalam antrian pada saat berhasil login
    public void addUserToQueue(User user) {
        for (User u : userQueue) {
            if (u.getUsername().equals(user.getUsername())) {
                userQueue.remove(u); // Jika user sudah ada di antrean, hapus user lama dari antrian
                break;
            }
        }
        // Tambahkan user baru ke akhir antrian
        userQueue.add(user);
        System.out.println("User '" + user.getUsername() + "' ditambahkan ke antrian.");
    }

    // Method untuk menampilkan seluruh user dalam antrian
    public void displayQueue() {
        if (userQueue.isEmpty()) {
            System.out.println("Antrian kosong.");
            return;
        }

        System.out.println("Daftar Antrian Pengguna:");
        // Menampilkan username dan role dalam antrian
        for (User u : userQueue) {
            System.out.println("- " + u.getUsername() + " (" + u.getRole() + ")");
        }
    }

    // Method untuk memproses user paling depan (FIFO), lalu menghapusnya dari antrian
    public User getNextUser() {
        if (userQueue.isEmpty()) {
            System.out.println("Antrian kosong.");
            return null;
        }

        User nextUser = userQueue.poll();
        System.out.println("User berikutnya adalah: " + nextUser.getUsername());
        return nextUser;
    }

    // Method untuk menghapus user tertentu dari antrian berdasarkan username ketika di hapus dari sistem
    public void removeUserFromQueue(String username) {
        boolean removed = userQueue.removeIf(u -> u.getUsername().equals(username));
        if (removed) {
            System.out.println("User '" + username + "' dihapus dari antrian.");
        } else {
            System.out.println("User '" + username + "' tidak ditemukan di antrian.");
        }
    }
}
