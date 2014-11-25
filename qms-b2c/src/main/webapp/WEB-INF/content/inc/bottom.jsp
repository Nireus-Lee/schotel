<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<div class="footer">
<p></p>
<div class="container-fluid">
    <div class="row text-center">
        <div class="col-md-12">
            <ul class="list-unstyled">
                <li><span><a href="/Flight/registerInput.do">免费注册</a> | <a href="/misc/aboutus.shtml">关于我们</a> | <a href="/misc/contactus.shtml">联系我们</a> </span></li>
                <li>Copyright © 2012-2014 SMART TRAVEL Corporation., All Rights Reserved<br />
                    版权所有 智游信息科技 沪ICP备12224102号-2</li>
            </ul>
        </div>
    </div>
</div>
</div>

<div class="hidden" id="loginStatus"></div>
<div class="ac_results" id="suggest"></div>

<!-- Login Modal -->
<div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title" id="myModalLabel">登录</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label>用户名</label>
                    <input type="text" id="ajaxUsername" name="ajaxUsername" class="form-control" />
                </div>
                <div class="form-group">
                    <label>密 码</label>
                    <input type="password" id="ajaxPassword" name="ajaxPassword" class="form-control" />
                </div>
            </div>
            <div class="modal-footer">
                <span class="pull-left"><s:a action="registerInput" cssClass="btn btn-warning btn-sm" target="_blank">注册</s:a></span>
                <button type="button" class="btn btn-primary btn-lg" onclick="javascript:ajaxLogin();">登录</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    <s:if test="#session.logined == null">
        logined = 0;
    </s:if>
    <s:else>
        logined = <s:property value="#session.logined" />;
    </s:else>
</script>


<!- -[if lt IE 9]>
<script type="text/javascript" src="/assets/js/libs/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="/assets/js/libs/jquery-migrate-1.2.1.min.js"></script>
<![endif]- ->
<!--[if gte IE 9]><!-->
<script type="text/javascript" src="/assets/js/libs/jquery-2.1.0.min.js"></script>
<!--<![endif]-->

<script type="text/javascript" src="/assets/js/libs/jquery-ui-1.10.4.custom.min.js"></script>
<script type="text/javascript" src="/assets/js/libs/jquery.ui.datepicker-zh-CN.js"></script>
<script type="text/javascript" src="/assets/js/libs/handlebars-v1.3.0.js"></script>


<script type="text/javascript" src="/assets/js/libs/j.dimensions.js" ></script>
<script type="text/javascript" src="/assets/js/libs/j.suggest.js" ></script>
<script type="text/javascript" src="/assets/js/libs/bootstrap.min.js" ></script>

<script type="text/javascript" src="/assets/js/app.js" ></script>
<script type="text/javascript" src="/assets/js/aircity.js" ></script>
<script type="text/javascript" src="/assets/js/nationality.js"></script>
<script type="text/javascript" src="/assets/js/common.js" ></script>

<script type="text/javascript" src="/assets/js/flight_func.js" ></script>
<script type="text/javascript" src="/assets/js/updateUserInfo.js" ></script>


<script type="text/javascript">
    var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
    document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3F6244282e26ebabaa7d012d77e9eb4581' type='text/javascript'%3E%3C/script%3E"));
</script>

