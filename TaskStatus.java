public enum TaskStatus {
    IN_PROGRESS("В работе"),
    DONE("Выполнена"),
    PAUSED("Отложена");
    final String russianStatus;
    TaskStatus(String russianStatus){
        this.russianStatus = russianStatus;
    }
    public String getRussianStatus(){
        return russianStatus;
    }
}
