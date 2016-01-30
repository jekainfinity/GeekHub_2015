package advancedgorm

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class LessonsController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Lessons.list(params), model:[lessonsCount: Lessons.count()]
    }

    def show(Lessons lessons) {
        respond lessons
    }

    def create() {
        respond new Lessons(params)
    }

    @Transactional
    def save(Lessons lessons) {
        if (lessons == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (lessons.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond lessons.errors, view:'create'
            return
        }

        lessons.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'lessons.label', default: 'Lessons'), lessons.id])
                redirect lessons
            }
            '*' { respond lessons, [status: CREATED] }
        }
    }

    def edit(Lessons lessons) {
        respond lessons
    }

    @Transactional
    def update(Lessons lessons) {
        if (lessons == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (lessons.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond lessons.errors, view:'edit'
            return
        }

        lessons.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'lessons.label', default: 'Lessons'), lessons.id])
                redirect lessons
            }
            '*'{ respond lessons, [status: OK] }
        }
    }

    @Transactional
    def delete(Lessons lessons) {

        if (lessons == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        lessons.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'lessons.label', default: 'Lessons'), lessons.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'lessons.label', default: 'Lessons'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
