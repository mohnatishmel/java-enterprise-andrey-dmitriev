<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="account-locked-form-modal modal fade" id="accountLockedForm" tabindex="-1" role="dialog" ria-labelledby="account-locked"
     aria-hidden="true" data-keyboard="false" data-backdrop="static">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="account-locked-form">
                <form action="/" method="post" class="input-form">
                    <h4>Your account is temporary locked</h4>
                    <p>You can send a message to administrator</p>

                    <div class="form-group input-group">
<%--                        <label for="messageToAdministrator">Description</label>--%>
                        <textarea class="form-control" id="messageToAdministrator" rows="6" placeholder="Your message" maxlength="600"></textarea>
                    </div>

                    <div class="form-group btn-group" onmousedown="return false" onselectstart="return false">
                        <button id="accountLockedFormSubmitBtn" type="button" class="btn btn-secondary form-control action-change"><span>Send message</span></button>
                    </div>

                    <div class="form-group btn-group" onmousedown="return false" onselectstart="return false">
                        <button id="accountLockedFormSwitchToLoginBtn" type="button" class="btn btn btn-outline-secondary form-control action-change"><span>Login</span></button>
                    </div>
<%--                    <div onmousedown="return false" onselectstart="return false">--%>
<%--                        <button id="actionChangeRegister" type="button" class="inactive form-control action-change"><span>Register</span></button>--%>
<%--                    </div>--%>
                </form>
                <div id="messageSendSuccess">
                    <div style="display: flex">
                        <p>Your message sent to administrator</p>
                    </div>
                    <div class="form-group btn-group" onmousedown="return false" onselectstart="return false" style="display: flex">
                        <button type="button" class="btn btn-secondary form-control action-change message-send-success" data-dismiss="modal"><span>OK</span></button>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>