package advancedgorm

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ClassroomController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Classroom.list(params), model:[classroomCount: Classroom.count()]
    }

    def show(Classroom classroom) {
        respond classroom
    }

    def create() {
        respond new Classroom(params)
    }

    @Transactional
    def save(Classroom classroom) {
        if (classroom == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (classroom.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond classroom.errors, view:'create'
            return
        }

        classroom.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'classroom.label', default: 'Classroom'), classroom.id])
                redirect classroom
            }
            '*' { respond classroom, [status: CREATED] }
        }
    }

    def edit(Classroom classroom) {
        respond classroom
    }

    @Transactional
    def update(Classroom classroom) {
        if (classroom == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (classroom.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond classroom.errors, view:'edit'
            return
        }

        classroom.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'classroom.label', default: 'Classroom'), classroom.id])
                redirect classroom
            }
            '*'{ respond classroom, [status: OK] }
        }
    }

    @Transactional
    def delete(Classroom classroom) {

        if (classroom == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        classroom.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'classroom.label', default: 'Classroom'), classroom.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'classroom.label', default: 'Classroom'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
