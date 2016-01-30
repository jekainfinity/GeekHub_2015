package simplegrailsapp

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class TaskDateController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond TaskDate.list(params), model:[taskDateCount: TaskDate.count()]
    }

    def show(TaskDate taskDate) {
        respond taskDate
    }

    def create() {
        respond new TaskDate(params)
    }

    @Transactional
    def save(TaskDate taskDate) {
        if (taskDate == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (taskDate.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond taskDate.errors, view:'create'
            return
        }

        taskDate.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'taskDate.label', default: 'TaskDate'), taskDate.id])
                redirect taskDate
            }
            '*' { respond taskDate, [status: CREATED] }
        }
    }

    def edit(TaskDate taskDate) {
        respond taskDate
    }

    @Transactional
    def update(TaskDate taskDate) {
        if (taskDate == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (taskDate.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond taskDate.errors, view:'edit'
            return
        }

        taskDate.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'taskDate.label', default: 'TaskDate'), taskDate.id])
                redirect taskDate
            }
            '*'{ respond taskDate, [status: OK] }
        }
    }

    @Transactional
    def delete(TaskDate taskDate) {

        if (taskDate == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        taskDate.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'taskDate.label', default: 'TaskDate'), taskDate.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'taskDate.label', default: 'TaskDate'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
