import java.util.LinkedList;


public class TaskOperations {

    public LinkedList<Task> linearSearchTasks(LinkedList<Task> tasks, String keyword) {
            LinkedList<Task> foundTasks = new LinkedList<>(); //membuat linkedlist untuk menyimpan tugas yg ditemukan
            for (Task task : tasks) { //melakukan iterasi pda setiap tugas dalam daftar yang diberikan
                if (task.getName().toLowerCase().contains(keyword.toLowerCase()) || //memeriksa apakah nama tugas mengandung kata kunci pencarian (dalam lowercase)
                        task.getDescription().toLowerCase().contains(keyword.toLowerCase())) { //atau mungkin deskripsinya yg mengandung kata kunci pencarian
                    foundTasks.add(task); //kalau cocok, tugas di tambahkan ke daftar foundTasks
                }
            }
            return foundTasks; //mengembalikan semua daftar tugas yg cocok
    } 

  public void bubbleSortTasks(LinkedList<Task> tasks) {
        int n = tasks.size(); //untuk mendapatkan jumlah total tugas dalam daftar
        if (n <= 1) return; // klau tugasnya kosong atau hanya 1, maka tidak diurutkan, langsung kluar dari method

        for (int i = 0; i < n - 1; i++) { //melakukan iterasi sebanyak n-1 kali
            for (int j = 0; j < n - i - 1; j++) { //membandingkan dan menukar elemen yang berdekatan dan batas n-i-1, jdi elemen yg sudah diurutkan tidak ikut diurutkan lagi
                Task task1 = tasks.get(j); //mengambil objek Task pertama untuk perbandingan
                Task task2 = tasks.get(j + 1); //objek Task kedua/berikutnya untuk perbandingan

                //membandingkan berdasarkan prioritas (ascending karna prioritasnya 1 low, 2 medium, dan 3 low)
                if (task1.getPriority() > task2.getPriority()) { // jika task1 lebih besar angkanya dari task2
                    tasks.set(j, task2); //tukar posisi task1 dengan task2
                    tasks.set(j + 1, task1); //melanjutkan penukaran
                   
                    //membandingkan berdasarkan deadline
                } else if (task1.getPriority() == task2.getPriority()){ //jika prioritas kedua tugas sama
                    if (task1.getDeadline().isAfter(task2.getDeadline())) { //bandingkan berdasarkan deadline. jika deadline task1 setelah deadline task2
                        tasks.set(j, task2); //tukar task1 dengan task2, agar deadline dengan waktu terderkat didahulukan 
                        tasks.set(j + 1, task1); //lanjut penukaran
                    }
                }
            }
        }
    }

}
