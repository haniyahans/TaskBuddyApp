import java.util.LinkedList;

public class UserManager {
    // Atribut untuk menyimpan daftar user
    // Menggunakan LinkedList untuk menyimpan user
    LinkedList<User> user = new LinkedList<User>();
    // Atribut untuk mengelola antrian user menggunakan UserQueueManager
    private UserQueueManager queue;

    // Konstruktor untuk inisialisasi UserManager dengan UserQueueManager
    public UserManager(UserQueueManager queue) {
        this.queue = queue;
    }

    // Metode untuk mencari user berdasarkan username
    public User findUser(String username){
        for (User u : user) {
            if (u.getUsername().equals(username)) {
                return u;
            }
        }
        return null;
    }

    // Metode untuk mendaftarkan user baru
    public boolean registerUser(String username, String password, String role){
        // Jika username sudah digunakan, tidak akan mendaftarkan user baru
        // Mengembalikan true jika berhasil mendaftar, false jika gagal
        if (findUser(username) != null) {
            System.out.println("Username sudah digunakan.");
            return false;
        }
        user.add(new User(username, password, role));
        System.out.println("User '" + username + "' berhasil didaftarkan.");
        return true;
    }

    // Metode untuk mendapatkan daftar user
    public User loginUser(String username, String password){
        // Mencari user berdasarkan username dan memeriksa password
        User user = findUser(username);

        // Jika berhasil, menambahkan user ke antrian dan mengembalikan user
        if (user != null && user.getPassword().equals(password)) {
            System.out.println("Login berhasil sebagai " + user.getRole());
            queue.addUserToQueue(user);
            return user;
        } else { // Jika gagal, mengembalikan null
            System.out.println("Login gagal. Username atau password salah.");
            return null;
        }
    }

    // Metode untuk menghapus user berdasarkan username dengan pengecekan role
    public boolean deleteUser(String username) {
        // Mencari user berdasarkan username
        User userToRemove = findUser(username);
        // Jika user ditemukan, memeriksa role
        if (userToRemove != null) {
            // Jika user adalah member, bisa dihapus dari daftar user dan antrian
            if (userToRemove.getRole().equalsIgnoreCase("member")) {
                user.remove(userToRemove);
                queue.removeUserFromQueue(username);
                System.out.println("User member '" + username + "' berhasil dihapus.");
                return true;
            } else { // Jika user adalah admin, tidak bisa dihapus
                System.out.println("User '" + username + "' bukan member. Tidak bisa dihapus.");
                return false;
            }
        } else { // Jika user tidak ditemukan, mengembalikan false
            System.out.println("User '" + username + "' tidak ditemukan.");
            return false;
        }
    }
}
