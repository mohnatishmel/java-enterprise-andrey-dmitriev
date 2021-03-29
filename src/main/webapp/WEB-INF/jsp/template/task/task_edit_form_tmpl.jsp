<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div class="modal fade" id="editTaskModalCenter" tabindex="-1" role="dialog" aria-labelledby="taskEditTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="taskEditTitle"></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">

                <div class="form-group">
                    <label for="updateTaskName">Task Name</label>
                    <input type="text" class="form-control" id="updateTaskName" placeholder="Task Name">
                </div>

                <div class="form-group">
                    <label for="updateTaskDescription">Description</label>
                    <textarea class="form-control" id="updateTaskDescription" rows="5" placeholder="Description" maxlength="600"></textarea>
                </div>

                <div class="form-group">
                    <label for="updateTaskDeadline">Select dead line date:</label>
                    <input id="updateTaskDeadline" class="form-control" type="date">
                </div>

            </div>
            <div class="modal-footer">
                <input id="updateTaskId" type="hidden" value=""/>
                <button type="button" class="form-control btn btn-outline-secondary" data-dismiss="modal" >Close</button>
                <button id="updateTask" type="button" class="form-control btn btn-secondary" data-dismiss="modal">Save changes</button>
            </div>
        </div>
    </div>
</div>