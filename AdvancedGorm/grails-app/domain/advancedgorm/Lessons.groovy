package advancedgorm

class Lessons {
    String name
    int duration;
    String describe;

    static hasMany = [classes: Classroom, groups: Party]
    static hasOne = [teacher: Teacher]

    static constraints = {
        describe blank: true, nullable: true;
        name nullable: false, blank: false;
        duration min: 40, max: 150;
        classes nullable: true;
        groups nullable: true;
        teacher nullable: true;

    }

    static mapping = {
        groups lazy: true;
    }
}

