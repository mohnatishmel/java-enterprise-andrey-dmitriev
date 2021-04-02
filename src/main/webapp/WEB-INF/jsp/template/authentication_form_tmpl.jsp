<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="authenticationForm">
    <form action="/" method="post" id="loginForm" class="input-form user-form">
        <h3>Sing In</h3>

        <span class="alert alert-danger" role="alert"></span>

        <div class="input-group">
            <%--        <label for="login">Enter Login</label>--%>
            <input id="loginFormLoginInput" name="login" type="text" placeholder="Enter login.."/>
        </div>

        <div class="input-group">
            <%--        <label for="password">Enter password</label>--%>
            <input id="loginFormPasswordInput" name="password" type="password" placeholder="Enter password.."/>
        </div>

        <input name="command" type="hidden" value="Login"/>

        <div class="form-group btn-group" onmousedown="return false" onselectstart="return false">
            <button id="submitLoginForm" type="button" class="btn btn-secondary form-control action-change"><span>
                <i class="fa fa-sign-in" aria-hidden="true"></i>  Login</span>
            </button>
        </div>

        <div class="form-group btn-group" onmousedown="return false" onselectstart="return false">
            <button id="switchToRegisterForm" type="button"
                    class="btn btn btn-outline-secondary form-control action-change"><span>
                <i class="fa fa-user-plus" aria-hidden="true"></i>  Register</span></button>
        </div>
    </form>

    <form action="/" method="post" id="registerForm" class="input-form user-form">
        <h3>Sing Up</h3>

        <span class="alert alert-danger" role="alert"></span>

        <div class="input-group">
            <%--        <label for="login">Enter Login</label>--%>
            <input id="registerFormLoginInput" name="login" type="text" placeholder="Enter login.."/>
        </div>

        <div class="input-group">
            <%--        <label for="password">Enter password</label>--%>
            <input id="registerFormPasswordInput" name="password" type="password" placeholder="Enter password.."/>
        </div>

        <input name="command" type="hidden" value="Login"/>

        <div class="form-group btn-group" onmousedown="return false" onselectstart="return false">
            <button id="switchToLoginForm" type="button" class="btn btn-outline-secondary form-control action-change"><span>
            <i class="fa fa-sign-in" aria-hidden="true"></i>  Login</span>
            </button>
        </div>

        <div class="form-group btn-group" onmousedown="return false" onselectstart="return false">
            <button id="submitRegisterForm" type="button"
                    class="btn btn btn-secondary form-control action-change"><span>
            <i class="fa fa-user-plus" aria-hidden="true"></i>  Register</span></button>
        </div>
    </form>
</div>