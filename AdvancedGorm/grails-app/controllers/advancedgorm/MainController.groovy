package advancedgorm

class MainController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def AllStudentByGroup() {
        def groups = Party.list();
        ArrayList groupNames = groups.collect{group -> group.getName().toString()};
        [groupsNames: groupNames];
    }

    def showStudentByGroupName(String groupName) {
        Party group = Party.findByName(groupName);
        [students: group.getStudents(), groupName: groupName];
    }

    def index() {
        def controllers = ["AllStudentByGroup"];
        [controllersName: controllers] ;
    }


}
