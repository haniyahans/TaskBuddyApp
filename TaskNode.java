import java.util.LinkedList;
public class TaskNode {//untuk masukin tugas ke tree (punya main tugas dan sub tugas)
    Task task; //simpan objek tugas dan informasinya, jadi bikin objek tugasnya dulu (nama, prioritas, dl) baru dimasukin ke tree
    LinkedList<TaskNode> subtasks;//biar punya sub tugas dan jadi tree
    public TaskNode(Task task) {//konstruktor, disini dia buat tugas utamanya dulu, trs baru tambahin sub tugasnya pake add
        this.task = task;//tambah tugas utama
        this.subtasks = new LinkedList<>();//tambah subtugas
    }
    public Task getTask() {//untuk nampilin root task
        return task;
    }
    public LinkedList<TaskNode> getSubtasks() {//untuk nampilin subtask
        return subtasks;
    }
}
