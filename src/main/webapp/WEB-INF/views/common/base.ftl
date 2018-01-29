<#assign globalPath =request.contextPath>
<#macro html title charset="utf-8" lang="zh-CN">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=${charset}" />
    <meta http-equiv="Content-Language" content="${lang}"/>
    <title>${title}</title>
    <link rel="stylesheet" type="text/css" href="${globalPath}/static/bootstrap/bootstrap.min.css"/>
    <script charset="UTF-8" type="text/javascript" src="${globalPath}/static/js/jquery-3.3.1.min.js"></script>
    <script charset="utf-8" type="text/javascript" src="${globalPath}/static/bootstrap/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${globalPath}/static/login.css"/>
    <script>try{Typekit.load({ async: true });}catch(e){}</script>
</head>
<body>
    <#nested>
</body>
</html>
</#macro>