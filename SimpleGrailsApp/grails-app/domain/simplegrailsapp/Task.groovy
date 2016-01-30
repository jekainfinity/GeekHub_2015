package simplegrailsapp

class Task {
    String name;
    String description;
    static belongsTo = [taskDate: TaskDate]

    static constraints = {
        name blank: false;
        description blank: true;
    }
}
