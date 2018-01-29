
<#include "common/base.ftl">
<@html title="Spring scurity登录页面">
<div class="container">
    <h1 class="welcome text-center">Welcome to Login</h1>
    <div class="card card-container">
        <h2 class='login_title text-center'>登录</h2>
        <hr>

        <form class="form-signin">
            <span id="reauth-email" class="reauth-email"></span>
            <p class="input_title">用户名</p>
            <input type="text" id="username" class="login_box" placeholder="请输入用户名" required autofocus>
            <p class="input_title">密码</p>
            <input type="password" id="password" class="login_box"  required>
            <div id="remember" class="checkbox">
                <label>

                </label>
            </div>
            <button class="btn btn-lg btn-primary" type="submit">Login</button>
        </form><!-- /form -->
    </div><!-- /card-container -->
</div><!-- /container -->
</@html>