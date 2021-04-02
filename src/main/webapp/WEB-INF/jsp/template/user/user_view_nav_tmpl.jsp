<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="user-view-nav" class="d-flex justify-content-between align-items-center activity">
    <div class="user-view-nav d-none">
        <span class="clock" data-toggle="tooltip" data-placement="top" title="Current Time">
            <i class="fa fa-clock-o"></i><span class="clock-container ml-2"></span>
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

    <div class="icons d-none">
<%--        <i class="fa fa-search"></i>--%>
    <span class="icon-btn" data-toggle="tooltip" data-placement="top" title="More actions">
        <i class="fa fa-ellipsis-h"></i>
            </span>
    </div>
</div>