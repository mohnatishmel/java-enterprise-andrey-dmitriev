<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="user-form-modal modal fade" id="userForm" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="user-form">
                <form action="/" method="post" class="input-form">
                    <h3>Login</h3>

                    <span class="alert alert-danger" role="alert"></span>

                    <div class="input-group">
                        <%--        <label for="login">Enter Login</label>--%>
                        <input id="loginInput" name="login" type="text" placeholder="Enter login.."/>
                    </div>

                    <div class="input-group">
                        <%--        <label for="password">Enter password</label>--%>
                        <input id="passwordInput" name="password" type="password" placeholder="Enter password.."/>
                    </div>

                    <input name="command" type="hidden" value="Login"/>

                    <div class="form-group btn-group" onmousedown="return false" onselectstart="return false">
                        <button id="submitUserFormLogin" type="button" class="btn btn-secondary form-control action-change"><span>Login</span></button>
                    </div>

                    <div class="form-group btn-group" onmousedown="return false" onselectstart="return false">
                        <button id="submitUserFormRegister" type="button" class="btn btn btn-outline-secondary form-control action-change"><span>Register</span></button>
                    </div>
<%--                    <div onmousedown="return false" onselectstart="return false">--%>
<%--                        <button id="actionChangeRegister" type="button" class="inactive form-control action-change"><span>Register</span></button>--%>
<%--                    </div>--%>
                </form>
            </div>
        </div>
    </div>
</div>