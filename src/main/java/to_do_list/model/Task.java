package to_do_list.model;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class Task{
    private String title, description;
    private TaskStatus status;
    private LocalDate creationDate, deadline;
    private Long id;

    public Task(){
    }

    public Long getId(){ return id; }
    public void setId(Long id){ this.id=id; }

    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        if (title == null || title.isBlank() || title.length() > 100) {
            throw new IllegalArgumentException("Некорректное название задачи.");
        }
        this.title = title;
    }

    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        if (description != null && description.length()>=500){
            throw new IllegalArgumentException("Описание задачи долно быть меньше 500 символов.");
        }
        this.description=description;
    }

    public TaskStatus getStatus(){
        return status;
    }
    public void setStatus(TaskStatus status){
        if (status == null) {
            throw new IllegalArgumentException("Статус не может быть null.");
        }
        this.status = status;
    }

    public LocalDate getCreationDate(){
        return creationDate;
    }
    public void setCreationDate(LocalDate creationDate){
        if (creationDate == null) {
            throw new IllegalArgumentException("Дата создания не может быть null.");
        }
        this.creationDate= creationDate;
    }

    public LocalDate getDeadline(){
        return deadline;
    }
    public void setDeadline(LocalDate deadline){
        if (deadline != null && creationDate != null && deadline.isBefore(creationDate)) {
            throw new IllegalArgumentException("Дедлайн не может быть раньше даты создания.");
        }
        this.deadline = deadline;
    }

    public String toString(){
        return "Task title: "+title+"\nDescription: "+description+"\nStatus: "+status.getRussianStatus()+"\nCreation date: "+(creationDate != null ? creationDate : "Не задана")+"\nDeadline: "+(deadline != null ? deadline : "Не задан");
    }
}