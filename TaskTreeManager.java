import java.util.LinkedList;
public class TaskTreeManager {//kumpulan method (4 method) yang akan atur tree tugas
    private LinkedList<TaskNode> mainTasks;//untuk simpan root tugas
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
        TaskNode parent = findTaskNode(parentTaskName);//cari parentnya trs masukin subnya
        if (parent != null) {
            parent.subtasks.add(new TaskNode(subtask));
            return true;//klo berhasil tambah subtree return true
        }
        return false;//klo parentnya null maka return false
    }
//---------------------------------------------------------METHOD 3-CARI TUGAS----------------------------------------------------//
    public TaskNode findTaskNode(String taskName) {
        for (TaskNode mainTask : mainTasks) {//cari di setiap tugas utama
            TaskNode found = findTaskNodeRecursive(mainTask, taskName);//pada tiap tugas utama tadi telusuri ke akar2 nya juga
            if (found != null) return found;//return klo ketemu
        }
        return null;//klo ga ketemu return null
    }
    private TaskNode findTaskNodeRecursive(TaskNode node, String taskName) {//untuk dimasukin ke findTaskNode (cari secara rekursif)
        if (node == null) return null;//klo tugas utama kosong balikin null
        if (node.task.name.equalsIgnoreCase(taskName)) return node;//cari di tugas utama, klo ada keluarin
        for (TaskNode sub : node.subtasks) {
            TaskNode found = findTaskNodeRecursive(sub, taskName);
            if (found != null) return found;//cari di subtugas, klo ada keluarin
        }
        return null;//klo ga ditemuin samsek return null
    }
//---------------------------------------------------------METHOD 4-HAPUS TUGAS---------------------------------------------------//
    public boolean removeTask(String taskName) {
        for (TaskNode mainTask : mainTasks) {//hapus tugas utama
            if (mainTask.task.name.equalsIgnoreCase(taskName)) {//cocokin nama tugas utama
                mainTasks.remove(mainTask);//klo bener maka hapus
                return true;//dan return true
            }
        }
        for (TaskNode mainTask : mainTasks) { //Jika bukan main task, cari di subtasks
            if (removeTaskRecursive(mainTask, taskName)) {//telusuri tiap subnya, klo ada hapus
                return true;//dan return true
            }
        }
        return false;
    }
    private boolean removeTaskRecursive(TaskNode parent, String taskName) {//untuk dimasukin ke removeTask (hapus subtugas)
        for (TaskNode child : parent.subtasks) {//loop dari tugas utama 
            if (child.task.name.equalsIgnoreCase(taskName)) {
                parent.subtasks.remove(child);//hapus subtugasnya lalu return true
                return true;
            }
            if (removeTaskRecursive(child, taskName)) return true;//klo gaketemu loop lgi di subnya
        }
        return false;//return false klo ga ketemu yg dihapus
    }
//---------------------------------------------------------METHOD SHABRE---------------------------------------------------------//
    public LinkedList<Task> getAllTasksRecursive() {//untuk keluarin semua tugas
        LinkedList<Task> allTasks = new LinkedList<>();//list untuk tampung semua tugasnya
        for (TaskNode mainTask : mainTasks) {//untuk masukin semua tugas dari rootnya
            collectTasks(mainTask, allTasks);
        }
        return allTasks;//kembaliin semua tugas
    }
    private void collectTasks(TaskNode node, LinkedList<Task> list) {///untuk dimasukin ke getAllTasksRecursive(kumpulin semua tugas)
        if (node == null) return;//klo tugas utama ga ada keluar dari method
        list.add(node.task);//tambahin tugas utama ke list
        for (TaskNode sub : node.subtasks) {
            collectTasks(sub, list);//tambahin sub tugas ke list
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
            System.out.println(); //Pemisah antar main task
            }
    }
    private void displayTreeRecursive(TaskNode node, String indent) {//rekursif untuk tampilin
        if (node == null) return;//jika tugas utama kosong keluar dari method
        System.out.println(indent + "- " + node.task.name);//tampilin nama tugas, untuk liatin level
        for (TaskNode sub : node.subtasks) {//loop subtugas
            displayTreeRecursive(sub, indent + "  ");//tampilin subtugas
        }
    }
}
