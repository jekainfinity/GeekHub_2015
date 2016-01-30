package advancedgorm

class   Classroom {
    int number
    int large

    static belongsTo = [lesson: Lessons]

    static constraints = {
        number min: 0;
        large min: 10;
    }

    static mapping = {
        lesson lazy: true;
    }
}
