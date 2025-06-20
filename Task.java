import java.time.LocalDate;
public abstract class Task {//penerapan abstrac dari oop (untuk bikin blueprint deskripsi tugas)
    protected String name;//enkapsulasi
    protected String description;
    protected int priority; 
    protected LocalDate deadline;
    protected boolean isCompleted;
//---------------------------------------------------------SABRINA---------------------------------------------------
    public Task(String name, String description, int priority, LocalDate deadline) { //konstruktor yang nanti diturunkan di SimpleTask
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.deadline = deadline;
        this.isCompleted = false;//default, jadi setiap bikin objek baru tugasnya akan blm selesai
    }
    public abstract String getTaskInfo();//method abstrak untuk blueprint di kelas turunannya (deskripsi tugas)
//-----------------------------------------------------------SHABRE---------------------------------------------------
    public String getName() {
        return name;
    }
    
    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    //enkapsulasi set
    public void setName(String name){
        this.name = name;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setPriority(int priority){
        this.priority = priority;
    }

    public void setDeadline(LocalDate deadline){
        this.deadline = deadline;
    }

    public void setCompleted(boolean completed){
        isCompleted = completed;
    }
}
