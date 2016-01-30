package advancedgorm

class Party {
    String name;
    int countOfStudent;

    static belongsTo = [lesson: Lessons]
    static hasMany = [students: Student]

    static constraints = {
        name blank: false, nullable: false;
        countOfStudent min: 10;
        students nullable: true;
    }
}