package advancedgorm

class Teacher {
    String name
    String surename
    String characteristics;

    static belongsTo = [lesson: Lessons]

    static constraints = {
        name nullable: false, blank: false;
        surename nullable: false, blank: false;
        characteristics nullable:true;
    }

    static mapping = {
        lesson cascade:"all-delete-orphan";
    }

}
