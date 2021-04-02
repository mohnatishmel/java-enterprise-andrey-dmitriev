<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="task-view-nav" class="d-flex justify-content-between align-items-center activity">
    <div class="task-view-nav">
        <span class="clock" data-toggle="tooltip" data-placement="top" title="Current Time">
            <i class="fa fa-clock-o"></i><span class="ml-2 clock-container"></span>
        </span>
        <span class="item-view cursor-pointer inactive active-btn" id="todayViewBtn" data-toggle="tooltip"
              data-placement="top" title="Today Tasks">Today</span>
        <span class="item-view cursor-pointer inactive" id="tomorrowViewBtn" data-toggle="tooltip"
              data-placement="top" title="Tasks on Tomorrow">Tomorrow</span>
        <span class="item-view cursor-pointer inactive" id="somedayViewBtn" data-toggle="tooltip"
              data-placement="top" title="Someday">Someday</span>
        <span class="item-view cursor-pointer tools inactive" id="trashBoxViewBtn">
            <i class="fa fa-trash-o" aria-hidden="true" data-toggle="tooltip"
               data-placement="top" title="Trash Box"></i>
        </span>


        <span class="item-view cursor-pointer tools inactive" id="fixedViewBtn">
            <i class="fa fa-check-square-o" aria-hidden="true"  data-toggle="tooltip" data-placement="top" title="Fixed"></i>
        </span>

        <span class="item-view cursor-pointer inactive active-btn" id="unlockedUsersViewBtn" data-toggle="tooltip"
              data-placement="top" title="Active Users">Active</span>

        <span class="item-view cursor-pointer inactive" id="lockedUsersViewBtn" data-toggle="tooltip" data-placement="top"
              title="Locked Users">Locked</span>

        <span class="item-view cursor-pointer inactive" id="incomeMessagesBtn" data-toggle="tooltip" data-placement="top"
              title="Income Messages">
            <i class="fa fa-envelope-o" aria-hidden="true"></i>
        </span>

    </div>

    <div class="icons">
        <span data-toggle="collapse"
              data-target="#createTaskForm" aria-expanded="false"
              aria-controls="createTaskForm">

            <span class="icon-btn" id="openCreateTaskFormBtn" data-toggle="tooltip" data-placement="top" title="Add New Task">
                <i class="fa fa-plus" aria-hidden="true" ></i>
            </span>

        </span>

        <span>
        <span id="trashBoxDropdownMenuButton"
              data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <span class="icon-btn" id="trashBoxOptionsBtn" data-toggle="tooltip" data-placement="top" title="More actions">
                <i class="fa fa-ellipsis-h"></i>
            </span>
        </span>

        <div class="dropdown-menu" aria-labelledby="trashBoxDropdownMenuButton">
            <button class="dropdown-item" id="emptyTaskTrashBoxBtn">Empty Trash Box</button>
            <button class="dropdown-item" id="restoreAllTasksBtn">Restore All</button>
        </div>
        </span>

        <span>
        <span id="fixedDropdownMenu"
              data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <span class="icon-btn" id="fixedOptionsBtn" data-toggle="tooltip" data-placement="top" title="More actions">
                <i class="fa fa-ellipsis-h"></i>
            </span>
        </span>

        <div class="dropdown-menu" aria-labelledby="fixedDropdownMenu">
            <button class="dropdown-item" id="clearFixedTasksBtn">Clear List</button>
        </div>
        </span>

        <span>
        <span id="lockedUsersDropdownMenu"
              data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <span class="icon-btn" id="lockedUsersOptionsBtn" data-toggle="tooltip" data-placement="top" title="More actions">
                <i class="fa fa-ellipsis-h"></i>
            </span>
        </span>

        <div class="dropdown-menu" aria-labelledby="lockedUsersDropdownMenu">
            <button class="dropdown-item" id="enableAllLockedUsersBtn">Enable All</button>
            <button class="dropdown-item" id="deleteAllLockedUsersBtn">Delete All</button>
        </div>
        </span>
    </div>
</div>