<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="viewList align-self-stretch">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <%--                    <c:import url="/WEB-INF/jsp/template/task/task_view_nav_tmpl.jsp"/>--%>
                <%--                    <c:import url="/WEB-INF/jsp/template/user/user_view_nav_tmpl.jsp"/>--%>
                <c:import url="/WEB-INF/jsp/template/viewer_nav_tmpl.jsp"/>
                <div class="mt-3">
                    <div class="collapse" id="createTaskForm">
                        <div class="card card-body">

                            <div class="form-group">
                                <label for="createTaskName">Task Name</label>
                                <input type="text" class="form-control" id="createTaskName" placeholder="Task Name">
                            </div>

                            <div class="form-group">
                                <label for="createTaskDescription">Description</label>
                                <textarea class="form-control" id="createTaskDescription" rows="3"
                                          placeholder="Description" maxlength="600"></textarea>
                            </div>

                            <div class="form-group">
                                <label for="createTaskDedLine">Select dead line date:</label>
                                <input id="createTaskDedLine" class="form-control" type="date" placeholder="dd/mm/yyyy">
                            </div>

                            <div class="form-group">
                                <button class="form-control btn btn-outline-secondary" id="createTaskBtn"
                                        nmousedown="return false" onselectstart="return false">Create
                                </button>
                            </div>
                        </div>
                    </div>
                    <ul class="viewList list list-inline accordion task-list" id="viewList">
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>


<%--<input type="submit" id="somebutton" class="btn btn-primary" value="Fetch">--%>

