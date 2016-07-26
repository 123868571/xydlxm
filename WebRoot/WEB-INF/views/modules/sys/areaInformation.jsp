<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>区域信息</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {

        });
    </script>
    <style type="text/css">
        a:link {
            color: #000;
            text-decoration: none;
        }

        a:visited {
            color: #000
        }

        a:hover {
            color: #000;
            text-decoration: underline;
        }

        a:active {
            color: #000
        }
    </style>
</head>
<body>
<!--BEGIN HOUSE INFORMATION-->
<div class="row-fluid">
    <div class="row-fluid">
        <!-- 房屋信息 -->
        <dsiv class="span6">
            <div class="portlet">
                <div class="portlet-title" style="border-bottom: #f5f5f5 1px solid;">
                    <div class="caption" style="color: rgb(77, 144, 254);"><i class="icon-edit"></i>区域信息</div>
                </div>
                <div class="portlet-body">
                    <div class="tabbable tabbable-custom tabbable-full-width">
                        <div class="tab-pane profile-classic row-fluid">
                            <ul class="unstyled span12 ">
                                <li class="no-padding no-margin">
                                    <span class="pad">上级区域:</span>
                                    <div class="pad">
                                        ${sysAreaInfo.parent.name}
                                    </div>
                                </li>

                                <li class="no-padding no-margin">
                                    <span class="pad">区域名称:</span>
                                    <div class="pad">
                                        ${sysAreaInfo.name}
                                    </div>
                                </li>
                                <li class="no-padding no-margin">
                                    <span class="pad">区域编码:</span>
                                    <div class="pad">
                                        ${sysAreaInfo.code}
                                    </div>
                                </li>

                                <li class="no-padding no-margin">
                                    <span class="pad">区域类型:</span>
                                    <div class="pad">
                                        ${fns:getDictLabel(sysAreaInfo.type, 'sys_area_type', '')}
                                    </div>
                                </li>

                                <li class="no-padding no-margin">
                                    <span class="pad">区域缩略图:</span>
                                    <div class="pad">
                                        <img class="thumbnail" src="${sysAreaInfo.photo}" alt="区域缩略图"></img></div>
                        </div>
                        </li>

                        <li class="no-padding no-margin">
                            <span class="pad">简介:</span>
                            <div class="pad">${sysAreaInfo.introduction}</div>
                        </li>
                        <li class="no-padding no-margin">
                            <span class="pad">备注信息:</span>
                            <div class="pad">${sysAreaInfo.remarks}</div>
                        </li>

                        <li>
                            <span class="pad padWidth"></span>
                            <input id="btnCancel" class="btn margin-r-20" type="button" value="返 回"
                                   onclick="history.go(-1)"/>
                        </li>

                        </ul>
                    </div>
                </div>
            </div>
        </dsiv>
    </div>
</div>
</body>
</html>