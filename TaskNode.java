import java.util.LinkedList;
public class TaskNode {//untuk masukin tugas ke tree (punya tugas utama dan sub tugas)
    Task task; //simpan objek tugas dan informasinya, jadi bikin objek tugasnya dulu (nama, prioritas, dll) baru dimasukin ke tree
    LinkedList<TaskNode> subtasks;//agar mempunyai sub tugas dan jadi tree
    public TaskNode(Task task) {//konstruktor, disini bisa buat tugas utamanya dulu, lalu baru tambahin sub tugasnya pake add
        this.task = task;//tambah tugas utama
        this.subtasks = new LinkedList<>();//tambah subtugas
    }
    public Task getTask() {//untuk ambil objek task
        return task;
    }
    public LinkedList<TaskNode> getSubtasks() {//untuk ambil subtask
        return subtasks;
    }
}
