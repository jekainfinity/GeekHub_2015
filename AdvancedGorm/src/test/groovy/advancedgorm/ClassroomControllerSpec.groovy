package advancedgorm

import grails.test.mixin.*
import spock.lang.*

@TestFor(ClassroomController)
@Mock(Classroom)
class ClassroomControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null

        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
        assert false, "TODO: Provide a populateValidParams() implementation for this generated test suite"
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.classroomList
            model.classroomCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.classroom!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def classroom = new Classroom()
            classroom.validate()
            controller.save(classroom)

        then:"The create view is rendered again with the correct model"
            model.classroom!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            classroom = new Classroom(params)

            controller.save(classroom)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/classroom/show/1'
            controller.flash.message != null
            Classroom.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def classroom = new Classroom(params)
            controller.show(classroom)

        then:"A model is populated containing the domain instance"
            model.classroom == classroom
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def classroom = new Classroom(params)
            controller.edit(classroom)

        then:"A model is populated containing the domain instance"
            model.classroom == classroom
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/classroom/index'
            flash.message != null

        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def classroom = new Classroom()
            classroom.validate()
            controller.update(classroom)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.classroom == classroom

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            classroom = new Classroom(params).save(flush: true)
            controller.update(classroom)

        then:"A redirect is issued to the show action"
            classroom != null
            response.redirectedUrl == "/classroom/show/$classroom.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/classroom/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def classroom = new Classroom(params).save(flush: true)

        then:"It exists"
            Classroom.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(classroom)

        then:"The instance is deleted"
            Classroom.count() == 0
            response.redirectedUrl == '/classroom/index'
            flash.message != null
    }
}
