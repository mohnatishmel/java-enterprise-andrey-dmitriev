<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="task-view-nav" class="d-flex justify-content-between align-items-center activity">
    <div class="task-view-nav">
        <span class="clock" data-toggle="tooltip" data-placement="top" title="Current Time">
            <i class="fa fa-clock-o"></i><span class="ml-2 clock-container"></span>
        </span>
        <span class="item-view cursor-pointer inactive active-btn" id="todayViewBtn" data-toggle="tooltip" data-placement="top" title="Today Tasks">Today</span>
        <span class="item-view cursor-pointer inactive" id="tomorrowViewBtn" data-toggle="tooltip" data-placement="top" title="Tasks on Tomorrow">Tomorrow</span>
        <span class="item-view cursor-pointer inactive" id="somedayViewBtn" data-toggle="tooltip" data-placement="top" title="Someday">Someday</span>
        <span class="item-view cursor-pointer tools inactive" id="trashBoxViewBtn">
            <i class="fa fa-trash-o" aria-hidden="true" data-toggle="tooltip" data-placement="top" title="Trash Box"></i>
        </span>
        <span class="item-view cursor-pointer tools inactive" id="fixedViewBtn">
            <i class="fa fa-check-square-o" aria-hidden="true"  data-toggle="tooltip" data-placement="top" title="Fixed"></i>
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
<%--        <i class="fa fa-search" ></i>--%>
        <span class="icon-btn"  data-toggle="tooltip" data-placement="top" title="More actions">
        <i class="fa fa-ellipsis-h"></i>
            </span>
    </div>
</div>