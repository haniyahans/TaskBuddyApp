import java.time.LocalDate;
public class SimpleTask extends Task {//penerapan inheritance dari oop (untuk bikin deskripsi tugas)
    public SimpleTask(String name, String description, int priority, LocalDate deadline) {//dia terapin polymorphism, jadi bikin objeknya pake ---Task tugas = new SimpleTask(...)---
        super(name, description, priority, deadline); //turunan task, isCompletednya ikut default
    }

    @Override
    public String getTaskInfo() {//print info tugasnya
        return String.format("%-15s | %-20s | Priority: %-2d | Deadline: %-12s | Done: %-5b",
                             "- " + name,
                             description,
                             priority,
                             deadline,
                             isCompleted);
    }
}
