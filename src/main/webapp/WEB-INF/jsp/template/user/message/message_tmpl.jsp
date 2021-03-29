<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="d-none message-template">
    <li id="headingMessage">
        <div id="collapseControl" data-toggle="collapse" data-target="#collapse" aria-expanded="false"
             aria-controls="collapse">
            <div class="d-flex flex-row align-items-center">
                <div class="d-flex flex-row align-items-center view-container">
<%--                    <i id="fixedIcon" class="fa fa-check-circle check-icon inactive"></i>--%>
                    <i class="fa fa-envelope-o inactive"></i>
                    <i>from:</i>
                    <div class="ml-2">
                        <h6 class="mb-0 name" id="senderName"></h6>
                    </div>
<%--                    <i class="transparent" >|</i>--%>
                </div>
            </div>

            <div id="collapse" class="collapse" aria-labelledby="headingMessage" data-parent="#viewList">
                <div class="text-box card-body">
                    <i id="messageBody">
                    </i>
                </div>
            </div>
        </div>
    </li>
</div>

<div id="messageToolBox" class="d-none">
    <div class="tools message-tool-box">
    <div class="float-right">
        <label class="switch">
            <input id="resolveRequestToggleSwitch" type="checkbox">
            <span class="slider round"></span>
        </label>
    </div>
        <div id="deleteMessageBtn" class="deleteBtn float-right">
            <i class="fa fa-user-times" aria-hidden="true"></i>
        </div>
    </div>
</div>
