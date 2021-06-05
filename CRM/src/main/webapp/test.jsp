<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + request.getContextPath() + "/";
%>
<base href="<%=basePath%>">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    $.ajax({
    url:"",
    data:{

    },
    type:"",
    dataType:"json",
    success:function (data) {

    }
    })

    String createTime = DateTimeUtil.getSysTime();
    String createBy =  ((User)request.getSession().getAttribute("user")).getName();
</head>
<body>

</body>
</html>
