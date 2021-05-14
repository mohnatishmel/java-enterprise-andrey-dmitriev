<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="d-none task-template">
    <li id="headingTask">
        <div id="collapseControl" data-toggle="collapse" data-target="#collapse" aria-expanded="false"
             aria-controls="collapse">
            <div class="d-flex justify-content-between">
                <div class="d-flex flex-row align-items-center">
                    <i id="fixedIcon" class="fa fa-check-circle check-icon inactive"></i>
                    <div class="ml-2">
                        <h6 class="mb-0 name" id="taskName"></h6>
                        <div class="d-flex flex-row mt-1 text-black-50 date-time">
                            <div><i class="fa fa-calendar-o"></i>
                                <span class="ml-2" id="taskDeadline"></span>
                            </div>
                            <div class="ml-3">
                                <i class="fa fa-clock-o"></i>
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
        <div class="float-right" data-toggle="tooltip" data-placement="top" title="Edit task">
            <i class="fa fa-pencil-square-o inactive" id="updateToolboxBtn" aria-hidden="true"
               data-toggle="modal" data-target="#editTaskModalCenter"></i>
        </div>
        <div class="float-right" data-toggle="tooltip" data-placement="top" title="Put task into trash box">
            <i class="fa fa-trash-o inactive" aria-hidden="true" id="toTrashBoxToolboxBtn"></i>
        </div>
        <div class="float-right" data-toggle="tooltip" data-placement="top" title="Upload file">
            <i class="fa fa-upload inactive" aria-hidden="true" id="uploadToolBoxBtn" data-toggle="modal"
               data-target="#uploadFileModal"></i>
        </div>
        <div class="float-right" data-toggle="tooltip" data-placement="top" title="Download file">
            <i class="fa fa-download inactive" id="downloadToolboxBtn" aria-hidden="true"></i>
        </div>
    </div>
</div>

<div id="taskToolBoxBoxForTrashBox" class="d-none">
    <div class="tools task-trash-tool-box-template">
        <div id="outOfTrashBoxBtn" class=" outOfTrashBoxBtn float-right" data-toggle="tooltip" data-placement="top" title="Restore task from trash box">
            <i class="fa fa-trash-o inactive" aria-hidden="true"><span class="fa fa-arrow-right" aria-hidden="true"></span></i>
        </div>
        <div id="deleteTaskBtn" class="float-right deleteBtn" data-toggle="tooltip" data-placement="top" title="Delete task">
            <i class="fa fa-minus-circle" aria-hidden="true"></i>
        </div>
    </div>
</div>
