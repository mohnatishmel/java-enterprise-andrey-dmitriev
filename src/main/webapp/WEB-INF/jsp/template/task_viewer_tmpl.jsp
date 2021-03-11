<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<script>
    // -----------------------------------------------
    //                  INIT
    // -----------------------------------------------
    var viewList;

    var currentView = "TODAY";

    var logged = <c:out value = "${requestScope.alreadyLogged}"/>

    jQuery(function () {


            $(document).ready(function () {
                $("#userForm").find(".alert").hide();
                fillTaskTable();
                showTime();
                if (logged === false) {
                $("#userForm").modal("show");
                }
            });



        function clearBox(elementID) {
                $("#" + elementID).empty();
        }

        // -----------------------------------------------
        //                    LOGIN
        // -----------------------------------------------
        <c:import url="/WEB-INF/jsp/js/controller/Controller.jsp"/>
        <c:import url="/WEB-INF/jsp/js/model/model.jsp"/>
        <c:import url="/WEB-INF/jsp/js/view/view.jsp"/>




        function sortTasksByDateUP() {
            viewList.sort((function (a, b) {
                if (a.deadLine > b.deadLine) {
                    return 1;
                }
                if (a.deadLine < b.deadLine) {
                    return -1;
                }
                return 0;
            }))
        }

        function sortTasksByDateDown() {
            viewList.sort((function (a, b) {
                if (a.deadLine > b.deadLine) {
                    return -1;
                }
                if (a.deadLine < b.deadLine) {
                    return 1;
                }
                return 0;
            }))
        }


    })
</script>

<%--    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#editTaskModalCenter">--%>
<%--        Launch demo modal--%>
<%--    </button>--%>

    <!-- Modal -->
<%--    <div class="modal fade" id="deleteTaskModal" tabindex="-1" role="dialog" aria-labelledby="taskEditTitle" aria-hidden="true">--%>
<%--        <div class="modal-dialog modal-dialog-centered" role="document">--%>
<%--            <div class="modal-content">--%>
<%--                <div class="modal-body">--%>
<%--                    <span>Put this task into the 'Trash Box'?</span>--%>
<%--                </div>--%>
<%--                <div class="modal-footer">--%>
<%--                    <input id="deleteTaskId" type="hidden" value=""/>--%>
<%--                    <button type="button" class="form-control btn btn-outline-secondary" data-dismiss="modal">NO</button>--%>
<%--                    <button id="deleteTask" type="button" class="form-control btn btn-secondary" data-dismiss="modal">YES</button>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>

<div class="modal fade" id="uploadFileModal" tabindex="-1" role="dialog" aria-labelledby="uploadFileModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
<%--            <div class="modal-header">--%>
<%--                <h5 class="modal-title" id="uploadFileModalLabel">Upload File</h5>--%>
<%--                <button type="button" class="close" data-dismiss="modal" aria-label="Close">--%>
<%--                    <span aria-hidden="true">&times;</span>--%>
<%--                </button>--%>
<%--            </div>--%>
            <div class="modal-body">
                <div class="form-group">
                    <form id="uploadForm" method="POST" enctype="multipart/form-data">
                        <label for="uploadFileInput">Select a file to upload:</label>
                        <input type="file" class="form-control" id="uploadFileInput" name="file">
                    </form>
                    <input type="hidden" id="uploadFileTaskId">
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="form-control btn btn-outline-secondary" data-dismiss="modal">Close</button>
                <button type="button" class="form-control btn btn-secondary" id="uploadFileBtn" data-dismiss="modal">Upload File</button>
            </div>
        </div>
    </div>
</div>

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

<div class="viewList">
        <div class="container mt-5">
            <div class="row">
                <div class="col-md-12">
                    <div class="d-flex justify-content-between align-items-center activity">

                        <div class="task-view-nav">
                            <span class="clock">
                                <i class="fa fa-clock-o"></i><span class="ml-2" id="clock"></span>
                            </span>
                            <span class="task-view cursor-pointer inactive active-btn" id="todayViewBtn" data-toggle="tooltip" data-placement="top" title="Today Tasks">Today</span>
                            <span class="task-view cursor-pointer inactive" id="tomorrowViewBtn" data-toggle="tooltip" data-placement="top" title="Tasks on Tomorrow">Tomorrow</span>
                            <span class="task-view cursor-pointer inactive" id="somedayViewBtn" data-toggle="tooltip" data-placement="top" title="Someday">Someday</span>
                            <span class="task-view cursor-pointer tools inactive" id="trashBoxViewBtn">
                                <i class="fa fa-trash-o" aria-hidden="true" data-toggle="tooltip" data-placement="top" title="Trash Box"></i>
                            </span>
                            <span class="task-view cursor-pointer tools inactive" id="fixedViewBtn">
                                <i class="fa fa-check-square-o" aria-hidden="true"  data-toggle="tooltip" data-placement="top" title="Fixed"></i>
                            </span>
                        </div>

                        <div class="icons">
                            <span data-toggle="collapse"
                            data-target="#createTaskForm" aria-expanded="false"
                            aria-controls="createTaskForm">
                                <i class="fa fa-plus" aria-hidden="true" id="openCreateTaskFormBtn" data-toggle="tooltip" data-placement="top" title="Add New Task"></i>
                            </span>
                            <i class="fa fa-search" ></i>
                            <i class="fa fa-ellipsis-h"></i>
                        </div>
                    </div>

                    <div class="mt-3">
                        <div class="collapse" id="createTaskForm">
                            <div class="card card-body">

                                <div class="form-group">
                                    <label for="createTaskName">Task Name</label>
                                    <input type="text" class="form-control" id="createTaskName" placeholder="Task Name">
                                </div>

                                <div class="form-group">
                                    <label for="createTaskDescription">Description</label>
                                    <textarea class="form-control" id="createTaskDescription" rows="3" placeholder="Description" maxlength="600"></textarea>
                                </div>

                                <div class="form-group">
                                    <label for="createTaskDedLine">Select dead line date:</label>
                                    <input id="createTaskDedLine" class="form-control" type="date" placeholder="dd/mm/yyyy">
                                </div>

                                <div class="form-group">
                                    <button class="form-control btn btn-outline-secondary" id="createTaskBtn" nmousedown="return false" onselectstart="return false">Create</button>
                                </div>

                            </div>

                        </div>
                        <ul class="list list-inline accordion task-list" id="viewList">
                        </ul>
                    </div>
                </div>
            </div>
        </div>
</div>

<div class="d-none task-template">
<li id="headingTask">
    <div id="collapseControl" data-toggle="collapse" data-target="#collapse" aria-expanded="false"
         aria-controls="collapse">
        <div class="d-flex justify-content-between">
                <div class="d-flex flex-row align-items-center"><i id="fixedIcon" class="fa fa-check-circle check-icon inactive"></i>
                <div class="ml-2">
                    <h6 class="mb-0 name" id="taskName"></h6>
                    <div class="d-flex flex-row mt-1 text-black-50 date-time">
                        <div><i class="fa fa-calendar-o"></i>
                            <span class="ml-2" id="taskDeadline"></span>
                        </div>
                        <div class="ml-3"><i class="fa fa-clock-o"></i>
                            <span id="daysLeft" class="ml-2"></span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id="collapse" class="collapse" aria-labelledby="headingTask" data-parent="#viewList">
            <div class="text-box card-body">
                <p id="taskDescription">
                </p>
            </div>
        </div>
    </div>
    <input type="hidden" id="inBasket">
    <input type="hidden" id="fixed">
</li>
</div>


<div id="taskToolBoxCommonTemplate" class="d-none">
<div class="tools">
    <div class="float-right">
        <label class="switch">
            <input id="fixedToolboxToggleSwitch" type="checkbox">
            <span class="slider round"></span>
        </label>
    </div>
    <div class="float-right">
        <i class="fa fa-pencil-square-o inactive" id="updateToolboxBtn" aria-hidden="true"
           data-toggle="modal" data-target="#editTaskModalCenter"></i>
    </div>
    <div class="float-right">
        <i class="fa fa-trash-o inactive" aria-hidden="true" id="toTrashBoxToolboxBtn"></i>
    </div>
    <div class="float-right">
        <i class="fa fa-download inactive" id="downloadToolboxBtn" aria-hidden="true"></i>
    </div>
    <div class="float-right">
        <i class="fa fa-upload inactive" aria-hidden="true" id="uploadToolBoxBtn" data-toggle="modal" data-target="#uploadFileModal"></i>
    </div>
</div>
</div>
<div id="taskToolBoxBoxForTrashBox" class="d-none">
<div class="tools task-trash-tool-box-template">
    <div id="outOfTrashBoxBtn" class=" outOfTrashBoxBtn float-right">
        <i class="fa fa-trash-o inactive" aria-hidden="true"><span class="fa fa-arrow-right" aria-hidden="true"></span></i>

    </div>
    <div id="deleteTaskBtn" class="float-right deleteBtn">
        <i class="fa fa-minus-circle" aria-hidden="true"></i>
    </div>
</div>
</div>



<c:import url="/WEB-INF/jsp/template/login_form_tmpl.jsp"/>



<%--<input type="submit" id="somebutton" class="btn btn-primary" value="Fetch">--%>

