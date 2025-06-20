import java.util.LinkedList;
public class TaskTreeManager {//kumpulan method (4 method) yang akan atur tree tugas
    private LinkedList<TaskNode> mainTasks;//untuk simpan root tugas (tugas utama)
//---------------------------------------------------------KONSTRUKTOR--------------------------------------------------------------//
    public TaskTreeManager() {
        mainTasks = new LinkedList<>();
    }
//---------------------------------------------------------METHOD 1-TAMBAH TUGAS UTAMA---------------------------------------------//
    public void addMainTask(Task task) {
       mainTasks.add(new TaskNode(task));
    }
//---------------------------------------------------------METHOD 2-TAMBAH SUB TUGAS-----------------------------------------------//
    public boolean addSubtask(String parentTaskName, Task subtask) {
        TaskNode parent = findTaskNode(parentTaskName);//cari parentnya terus masukin sub tugas nya
        if (parent != null) {
            parent.subtasks.add(new TaskNode(subtask));
            return true;//jika berhasil tambah sub tugas return true
        }
        return false;//jika parentnya null maka return false
    }
//---------------------------------------------------------METHOD 3-CARI TUGAS----------------------------------------------------//
    public TaskNode findTaskNode(String taskName) {
        for (TaskNode mainTask : mainTasks) {//cari di setiap tugas utama
            TaskNode found = findTaskNodeRecursive(mainTask, taskName);//pada tiap tugas utama tadi telusuri ke akar-akar nya juga
            if (found != null) return found;//return jika ketemu tugas yang dicari
        }
        return null;//jika tidak ditemukan return null
    }
    private TaskNode findTaskNodeRecursive(TaskNode node, String taskName) {//untuk dimasukkan ke findTaskNode (cari secara rekursif)
        if (node == null) return null;//jika tugas utama kosong maka return null
        if (node.task.name.equalsIgnoreCase(taskName)) return node;//cari di tugas utama, jika ada maka keluarkan
        for (TaskNode sub : node.subtasks) {
            TaskNode found = findTaskNodeRecursive(sub, taskName);
            if (found != null) return found;//cari di subtugas, jika ada maka keluarkan
        }
        return null;//jika tidak ditemukan sama sekali, maka return null
    }
//---------------------------------------------------------METHOD 4-HAPUS TUGAS---------------------------------------------------//
    public boolean removeTask(String taskName) {
        for (TaskNode mainTask : mainTasks) {//hapus tugas utama
            if (mainTask.task.name.equalsIgnoreCase(taskName)) {//cocokin nama tugas utama
                mainTasks.remove(mainTask);//jika benar maka hapus
                return true;//dan return true
            }
        }
        for (TaskNode mainTask : mainTasks) { //Jika bukan main task, cari di subtasks
            if (removeTaskRecursive(mainTask, taskName)) {//telusuri tiap subnya, jika ada maka hapus
                return true;//dan return true
            }
        }
        return false;
    }
    private boolean removeTaskRecursive(TaskNode parent, String taskName) {//untuk dimasukkan ke removeTask (hapus subtugas)
        for (TaskNode child : parent.subtasks) {//loop dari tugas utama 
            if (child.task.name.equalsIgnoreCase(taskName)) {
                parent.subtasks.remove(child);//hapus subtugasnya lalu return true
                return true;
            }
            if (removeTaskRecursive(child, taskName)) return true;//jika tidak ditemukan maka loop lgi di sub tugasnya
        }
        return false;//return false klo tidak ditemukan yang akan dihapus
    }
//---------------------------------------------------------METHOD SHABRE---------------------------------------------------------//
    public LinkedList<Task> getAllTasksRecursive() {//untuk mengeluarkan semua tugas
        LinkedList<Task> allTasks = new LinkedList<>();//list untuk tampung semua tugasnya
        for (TaskNode mainTask : mainTasks) {//untuk masukin semua tugas dari rootnya
            collectTasks(mainTask, allTasks);
        }
        return allTasks;//return semua tugas
    }
    private void collectTasks(TaskNode node, LinkedList<Task> list) {///untuk dimasukkan ke getAllTasksRecursive (kumpulin semua tugas)
        if (node == null) return;//jika tugas utama tidak ada maka keluar dari method
        list.add(node.task);//menambah tugas utama ke list
        for (TaskNode sub : node.subtasks) {
            collectTasks(sub, list);//menambah sub tugas ke list
        }
    }
//---------------------------------------------------------TAMBAHAN-DISPLAY TREE------------------------------------------------//
    public void displayTree() {//tampilin dalam bentuk struktur pohon
        if (mainTasks.isEmpty()) {
            System.out.println("Tidak ada tugas.");
            return;
        }
        for (TaskNode mainTask : mainTasks) { //mulai tampilin secara rekursif dari root
            displayTreeRecursive(mainTask, "");
            System.out.println(); //pemisah antar main task
            }
    }
    private void displayTreeRecursive(TaskNode node, String indent) {//rekursif untuk tampilin
        if (node == null) return;//jika tugas utama kosong keluar dari method
        System.out.println(indent + "- " + node.task.name);//tampilin nama tugas, untuk memperlihatkan level
        for (TaskNode sub : node.subtasks) {//loop subtugas
            displayTreeRecursive(sub, indent + "  ");//tampilin subtugas
        }
    }
}
