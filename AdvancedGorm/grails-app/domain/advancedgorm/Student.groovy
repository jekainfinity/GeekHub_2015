package advancedgorm

class Student {
    String name
    String surename
    int years

    static belongsTo = [group: Party]

    static constraints = {
        name nullable: false, blank: false;
        surename nullable: false, blank: false;
        years min: 6;
    }

}
