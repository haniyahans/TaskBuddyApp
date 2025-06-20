import java.time.LocalDate;
public abstract class Task {//penerapan abstrac dari oop (untuk bikin blueprint deskripsi tugas)
    protected String name;
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
    public abstract String getTaskInfo();//method abstrak untuk blueprint di kelas SimpleTask (berisi deskripsi tugas)
//-----------------------------------------------------------SHABRE---------------------------------------------------
    //enkapsulasi get
    public String getName() { //mengembalikan nilai dari variabel instance 'name'
        return name;
    }  
    
    public String getDescription() {  //mengembalikan nilai dari variabel instance 'description'
        return description;
    }

    public int getPriority() {  //mengembalikan nilai dari variabel instance 'priority'
        return priority;
    }

    public LocalDate getDeadline() {  //mengembalikan nilai dari variabel instance 'deadline'
        return deadline;
    }

    public boolean isCompleted() {  //mengembalikan nilai dari variabel instance 'isCompleted'
        return isCompleted;
    }

    //enkapsulasi set
    public void setName(String name){ //Mengatur variabel instance 'name' objek saat ini dengan nilai yang diberikan dari parameter 'name'
        this.name = name;
    }

    public void setDescription(String description){ //Mengatur variabel instance 'description' dengan nilai yang diberikan
        this.description = description;
    }

    public void setPriority(int priority){ //Mengatur variabel instance 'priority' dengan nilai yang diberikan
        this.priority = priority;
    }

    public void setDeadline(LocalDate deadline){ //Mengatur variabel instance 'deadline'dengan nilai yang diberikan
        this.deadline = deadline;
    }

    public void setCompleted(boolean completed){ // Mengatur variabel instance 'isCompleted' dengan nilai yang diberikan
        isCompleted = completed;
    }
}
