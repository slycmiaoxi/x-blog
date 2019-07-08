<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>

<header class="am-topbar am-topbar-inverse admin-header">
    <div class="am-topbar-brand">

        后台管理

    </div>
    <div class="am-icon-list tpl-header-nav-hover-ico am-fl am-margin-right">

    </div>

    <button class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only"
            data-am-collapse="{target: '#topbar-collapse'}"><span class="am-sr-only">导航切换</span> <span
            class="am-icon-bars"></span></button>

    <div class="am-collapse am-topbar-collapse" id="topbar-collapse">

        <ul class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list tpl-header-list">
            <li class="am-dropdown" data-am-dropdown data-am-dropdown-toggle>
                <a class="am-dropdown-toggle tpl-header-list-link" href="javascript:;">
                    <span class="am-icon-bell-o"></span> 说明 <span
                        class="am-badge tpl-badge-success am-round"></span></span>
                </a>
                <ul class="am-dropdown-content tpl-dropdown-content">
                    <li class="tpl-dropdown-content-external">
                        <h3>支持电脑<span class="tpl-color-success"></span> 和手机移动端观看</h3><a href="###"></a></li>
                    <li class="tpl-dropdown-list-bdbc"><a href="#" class="tpl-dropdown-list-fl"><span
                            class="am-icon-btn am-icon-plus tpl-dropdown-ico-btn-size tpl-badge-success"></span> 欢迎您的使用</a>
                        <span class="tpl-dropdown-list-fr"></span>
                    </li>

                </ul>
            </li>
            <li class="am-dropdown" data-am-dropdown data-am-dropdown-toggle>
                <a class="am-dropdown-toggle tpl-header-list-link" href="javascript:;">
                    <span class="am-icon-comment-o"></span> 操作说明 <span
                        class="am-badge tpl-badge-danger am-round"></span></span>
                </a>
                <ul class="am-dropdown-content tpl-dropdown-content">
                    <li class="tpl-dropdown-content-external">
                        <h3>说明 <span class="tpl-color-danger"></span> 正在完善中</h3><a href="###"></a></li>
                    <li>
                </ul>
            </li>
            <li class="am-dropdown" data-am-dropdown data-am-dropdown-toggle>
                <a class="am-dropdown-toggle tpl-header-list-link" href="javascript:;">
                    <span class="am-icon-calendar"></span> 全部功能 <span
                        class="am-badge tpl-badge-primary am-round"></span></span>
                </a>
                <ul class="am-dropdown-content tpl-dropdown-content">
                    <li class="tpl-dropdown-content-external">
                        <h3>后期 <span class="tpl-color-primary"></span> 正在完善</h3><a href="###"></a></li>
                    <li>


                </ul>
            </li>
            <li class="am-hide-sm-only"><a href="javascript:;" id="admin-fullscreen" class="tpl-header-list-link"><span
                    class="am-icon-arrows-alt"></span> <span class="admin-fullText">开启全屏</span></a></li>

            <li class="am-dropdown" data-am-dropdown data-am-dropdown-toggle>
                <a class="am-dropdown-toggle tpl-header-list-link" href="javascript:;">
                    <span class="tpl-header-list-user-nick">欢迎您: ${sessionScope.currentUser.nickName}</span><span
                        class="tpl-header-list-user-ico"> <img
                        src="<%=request.getContextPath()%>/static/Ui/assets/img/user01.png"></span>
                </a>
                <ul class="am-dropdown-content">
                    <li><a href="<%=request.getContextPath()%>/login.html"><span class="am-icon-power-off"></span>
                        退出</a></li>
                </ul>
            </li>
            <li><a href="###" class="tpl-header-list-link"><span
                    class="am-icon-sign-out tpl-header-list-ico-out-size"></span></a></li>
        </ul>
    </div>
</header>