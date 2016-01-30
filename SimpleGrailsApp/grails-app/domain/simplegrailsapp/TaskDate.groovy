package simplegrailsapp

class TaskDate {
    int day;
    int mounth;
    int year;

    static hasMany = [tasks:Task]

    static constraints = {
    }
}
