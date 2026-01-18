import java.time.format.*;
import java.util.*;
import java.time.LocalDate;

public class ListOfTasks {
    static void menu(){
        System.out.println("Добро пожаловать в To-Do List. Вы можете:");
        System.out.println("1 - добавить задачу");
        System.out.println("2 - показать все задачи");
        System.out.println("3 - изменить задачу");
        System.out.println("4 - удалить задачу");
        System.out.println("0 - выйти");
        System.out.println("Введите номер необходимой команды.");
    }
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        ArrayList<Task> Tasks = new ArrayList<Task>();
        menu();
        int number = scan.nextInt();
        String bufferClean = scan.nextLine();
        while (number!=0){
            switch (number){
                case 1:
                    LocalDate creationDate = LocalDate.now();
                    Task newTask = new Task(creationDate);
                    System.out.println("Введите новую задачу. Введите название задачи:");
                    String title = scan.nextLine();
                    int flag=0;
                    while (flag!=1){
                        try{
                            newTask.setTitle(title);
                            flag=1;
                        } catch (IllegalArgumentException e) {
                            flag=0;
                            System.out.println(e.getMessage()+" Пожалуйста, повторите ввод.");
                            title = scan.nextLine();
                        }
                    }
                    System.out.println("Введите описание задачи.");
                    String description = scan.nextLine();
                    try{
                        newTask.setDescription(description);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println("Введите статус задачи (без его номера): 1) В работе  2) Выполнена  3) Отложена.\n(Статус задачи по умолчанию: В работе)");
                    String russianStatus = scan.nextLine();
                    TaskStatus status = TaskStatus.IN_PROGRESS;
                    switch (russianStatus){
                        case "Выполнена":
                            status = TaskStatus.DONE;
                            break;
                        case "Отложена":
                            status = TaskStatus.PAUSED;
                            break;
                        default:
                            System.out.println("Введен некорректный статус.");
                    }
                    newTask.setStatus(status);
                    System.out.println("Введите дедлайн задачи в формате ДД.ММ.ГГГГ");
                    String deadlineString =scan.nextLine();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                    LocalDate deadline = LocalDate.now();
                    try {
                        deadline = LocalDate.parse(deadlineString, formatter);
                    } catch (DateTimeParseException e) {
                        System.out.println("Неверный формат даты");
                        deadline = null;
                    }
                    try{
                        newTask.setDeadline(deadline);
                    } catch (IllegalArgumentException e){
                        System.out.println(e.getMessage());
                    }
                    Tasks.add(newTask);
                    System.out.println("Добавленная задача\n"+newTask);
                    break;
                case 2:
                    System.out.println("Все задачи:\n");
                    for (int i=0; i< Tasks.size(); i++){
                        System.out.println((i+1)+") "+Tasks.get(i));
                    }
                    break;
                case 3:
                    System.out.println("Введите номер задачи, которую вы хотите изменить:");
                    int changeId = scan.nextInt()-1;
                    bufferClean = scan.nextLine();
                    while (changeId < 0 || changeId >= Tasks.size()){
                        System.out.println("Введен некорректный id. Пожалуйста, повтроите ввод.");
                        changeId = scan.nextInt();
                        bufferClean = scan.nextLine();
                    }
                    Task changeTask = Tasks.get(changeId);
                    System.out.println("Введите новое название задачи:");
                    String changeTitle = scan.nextLine();
                    int flag2=0;
                    while (flag2!=1){
                        try{
                            changeTask.setTitle(changeTitle);
                            flag2=1;
                        } catch (IllegalArgumentException e) {
                            flag2=0;
                            System.out.println(e.getMessage()+" Пожалуйста, повторите ввод.");
                            changeTitle = scan.nextLine();
                        }
                    }
                    System.out.println("Введите новое описание задачи.");
                    String changeDescription = scan.nextLine();
                    try{
                        changeTask.setDescription(changeDescription);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println("Введите новый статус задачи (без его номера): 1) В работе  2) Выполнена  3) Отложена");
                    String changeRussianStatus = scan.nextLine();
                    TaskStatus changeStatus= changeTask.getStatus();
                    switch (changeRussianStatus){
                        case "В работе":
                            changeStatus = TaskStatus.IN_PROGRESS;
                            break;
                        case "Выполнена":
                            changeStatus = TaskStatus.DONE;
                            break;
                        case "Отложена":
                            changeStatus = TaskStatus.PAUSED;
                            break;
                        default:
                            System.out.println("Введен некорректный статус. Пожалуйста, повтроите ввод.");
                    }
                    changeTask.setStatus(changeStatus);
                    System.out.println("Введите новый дедлайн задачи.");
                    String changeDeadlineString =scan.nextLine();
                    LocalDate changeDeadline = LocalDate.now();
                    try {
                        changeDeadline = LocalDate.parse(changeDeadlineString, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                    } catch (DateTimeParseException e) {
                        System.out.println("Неверный формат даты");
                        changeDeadline = null;
                    }
                    try{
                        changeTask.setDeadline(changeDeadline);
                    } catch (IllegalArgumentException e){
                        System.out.println(e.getMessage());
                    }
                    System.out.println("Измененная задача\n"+changeTask);
                    break;
                case 4:
                    System.out.println("Введите номер задачи, которую вы хотите удалить:");
                    int deleteId = scan.nextInt() - 1;
                    bufferClean = scan.nextLine();
                    while (deleteId < 0 || deleteId >= Tasks.size()){
                        System.out.println("Введен некорректный номер. Пожалуйста, повтроите ввод.");
                        deleteId = scan.nextInt();
                        bufferClean = scan.nextLine();
                    }
                    Task deleteTask = Tasks.remove(deleteId);
                    System.out.println("Удаленная задача\n"+deleteTask);
                    break;
                default:
                    System.out.println("Введена некорректная команда. Пожалуйста, повтроите ввод.");
                    menu();
            }
            menu();
            number = scan.nextInt();
            bufferClean = scan.nextLine();
        }
        scan.close();
    }
}
