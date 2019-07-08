<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<div class="tpl-left-nav tpl-left-nav-hover">
    <div class="tpl-left-nav-title">
        日常操作
    </div>
    <div class="tpl-left-nav-list">
        <ul class="tpl-left-nav-menu">
            <li class="tpl-left-nav-item">
                <a href="#" class="nav-link active">
                    <i class="am-icon-home"></i>
                    <span>首页</span>
                </a>
            </li>
            <li class="tpl-left-nav-item">
                <a href="<%=request.getContextPath()%>/core/v1/tUser/show/1"
                   class="nav-link tpl-left-nav-link-list">
                    <i class="am-icon-user-plus"></i>
                    <span>管理员控制台</span>

                </a>
            </li>


            <li class="tpl-left-nav-item">
                <a href="javascript:;" class="nav-link tpl-left-nav-link-list">
                    <i class="am-icon-table"></i>
                    <span>信息化操作</span>
                    <i class="am-icon-angle-right tpl-left-nav-more-ico am-fr am-margin-right"></i>
                </a>
                <ul class="tpl-left-nav-sub-menu">
                    <li>
                        <a href="<%=request.getContextPath()%>/core/v1/tAlgotithmsType/show">
                            <i class="am-icon-angle-right"></i>
                            <span>算法类型</span>
                            <i class="am-icon-star tpl-left-nav-content-ico am-fr am-margin-right"></i>
                        </a>

                        <a href="<%=request.getContextPath()%>/core/v1/tAlgotithmsInfo/show">
                            <i class="am-icon-angle-right"></i>
                            <span>算法建模</span>
                            <i class="am-icon-star tpl-left-nav-content-ico am-fr am-margin-right"></i>
                        </a>


                    </li>
                </ul>
            </li>

            <li class="tpl-left-nav-item">
                <a href="javascript:;" class="nav-link tpl-left-nav-link-list">
                    <i class="am-icon-wpforms"></i>
                    <span>监控操作</span>
                    <i class="am-icon-angle-right tpl-left-nav-more-ico am-fr am-margin-right"></i>
                </a>
                <ul class="tpl-left-nav-sub-menu">
                    <li>


                        <a href="<%=request.getContextPath()%>/core/v1/userOnline/show">
                            <i class="am-icon-angle-right"></i>
                            <span>在线踢人</span>
                            <i class="tpl-left-nav-content tpl-badge-success">
                            </i>
                        </a>

                    </li>

                </ul>
            </li>


            <li class="tpl-left-nav-item">
                <a href="javascript:;" class="nav-link tpl-left-nav-link-list">
                    <i class="am-icon-info-circle"></i>
                    <span>权限操作</span>
                    <i class="am-icon-angle-right tpl-left-nav-more-ico am-fr am-margin-right"></i>
                </a>
                <ul class="tpl-left-nav-sub-menu">
                    <li>


                        <a href="<%=request.getContextPath()%>/core/v1/tRole/show/1">
                            <i class="am-icon-angle-right"></i>
                            <span>修改权限</span>
                            <i class="tpl-left-nav-content tpl-badge-success">

                            </i>
                        </a>

                    </li>
                </ul>
            </li>


            <li class="tpl-left-nav-item">
                <a href="javascript:;" class="nav-link tpl-left-nav-link-list">
                    <i class="am-icon-desktop"></i>
                    <span>博文操作</span>
                    <i class="am-icon-angle-right tpl-left-nav-more-ico am-fr am-margin-right tpl-left-nav-more-ico-rotate"></i>
                </a>
                <ul class="tpl-left-nav-sub-menu" style="display: block;">
                    <li>

                        <a href="<%=request.getContextPath()%>/core/v1/tBlogInfo/sysBlogShow/1">
                            <i class="am-icon-angle-right"></i>
                            <span>帖子管理</span>
                        </a>
                    </li>
                    <li>
                        <a href="<%=request.getContextPath()%>/core/v1/tCommentInfo/sysCommentShow/1">
                            <i class="am-icon-angle-right"></i>
                            <span>评论管理</span>
                        </a>
                    </li>
                    <li>
                        <a href="<%=request.getContextPath()%>/core/v1/tFloorInfo/sysFloorShow/1">
                            <i class="am-icon-angle-right"></i>
                            <span>楼中楼管理</span>
                        </a>
                    </li>
                </ul>
            </li>

            <li class="tpl-left-nav-item">
                <a href="javascript:;" class="nav-link tpl-left-nav-link-list">
                    <i class="am-icon-wpforms"></i>
                    <span>日志管理</span>
                    <i class="am-icon-angle-right tpl-left-nav-more-ico am-fr am-margin-right"></i>
                </a>
                <ul class="tpl-left-nav-sub-menu">
                    <li>


                        <a href="<%=request.getContextPath()%>/core/v1/tLoginLog/show/1">
                            <i class="am-icon-angle-right"></i>
                            <span>日志操作</span>
                            <i class="tpl-left-nav-content tpl-badge-success">

                            </i>

                        </a>
                    </li>
                </ul>
            </li>

            <li class="tpl-left-nav-item">
                <a href="javascript:;" class="nav-link tpl-left-nav-link-list">
                    <i class="am-icon-bluetooth"></i>
                    <span>推送管理</span>
                    <i class="am-icon-angle-right tpl-left-nav-more-ico am-fr am-margin-right"></i>
                </a>
                <ul class="tpl-left-nav-sub-menu">
                    <li>


                        <a href="<%=request.getContextPath()%>/view/dispatch/sys/63efd1c9ea8e487e05ff5956846fc218">
                            <i class="am-icon-angle-right"></i>
                            <span>推送消息</span>
                            <i class="tpl-left-nav-content tpl-badge-success">

                            </i>
                        </a>
                    </li>
                </ul>
            </li>

            <li class="tpl-left-nav-item">
                <a href="<%=request.getContextPath()%>/login.html" class="nav-link tpl-left-nav-link-list">
                    <i class="am-icon-key"></i>
                    <span>登录</span>

                </a>
            </li>
        </ul>
    </div>
</div>