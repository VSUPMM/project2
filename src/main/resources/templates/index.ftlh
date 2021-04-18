<#import "spring.ftl" as spring/>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Welcome</title>
    <link rel="stylesheet"
          type="text/css" href="<@spring.url '/css/style.css'/>"/>
</head>

<body>

<ul>
    <#list tasks as item>
    <li>${item.question}</li>
</#list>
</ul>


</body>

</html>