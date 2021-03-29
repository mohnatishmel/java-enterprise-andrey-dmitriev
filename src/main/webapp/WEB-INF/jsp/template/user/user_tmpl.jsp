<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="user-template" class="d-none">
    <li id="headingUser" class="d-flex justify-content-between">
        <div class="d-flex flex-row align-items-center">
            <div class="ml-2">
                <h6 class="mb-0 name" id="userName"></h6>
                <div class="d-flex flex-row mt-1 text-black-50 date-time">
<%--                            <div><i class="fa fa-calendar-o"></i>--%>
<%--                                <span class="ml-2" id="taskDeadline"></span>--%>
<%--                            </div>--%>
<%--                            <div class="ml-3"><i class="fa fa-clock-o"></i>--%>
<%--                                <span id="daysLeft" class="ml-2"></span>--%>
<%--                            </div>--%>
                </div>
            </div>
        </div>
        <input type="hidden" id="accountNotLocked">
    </li>
</div>

<div id="userToolBox" class="d-none">
    <div class="tools user-tool-box-template d-flex align-items-center">
        <div id="deleteUserBtn" class="deleteBtn">
            <i class="fa fa-user-times" aria-hidden="true"></i>
        </div>
        <div>
        <label class="switch">
            <input id="lockUserAccountToggleSwitch" class="lockUserAccountToggleSwitch" type="checkbox">
            <span class="slider round"></span>
        </label>
    </div>
    </div>
</div>

<c:import url="/WEB-INF/jsp/template/user/message/message_tmpl.jsp"/>