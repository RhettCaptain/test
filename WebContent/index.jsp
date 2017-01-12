<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>测试</title>
</head>
<body>
<s:form action="submit" method="post" enctype="multipart/form-data">
	<s:select name="action" lable="滤镜" list="#{'binarization':'黑白','graying':'灰度','txtCleanWaterMark':'文本去水印'}" 
		listKey="key" listValue="value"
	/>
	<s:file name="upload" label="选择文件" /><br/>
	<s:submit value="上传" />
	
</s:form>
<br/>
<s:if test="isSuccess">
	<div class="imgContainer">
		<img class="imgItem" src=" <s:property value="'upload/'+uploadFileName"/> "/>
		<img class="imgItem" src=" <s:property value="'dealPath/'+uploadFileName"/> "/>
	</div>
	
</s:if>
<style>
.imgContainer{
	width:100%;
} 
.imgItem{
	width:45%;
	margin:0.5em;
}
</style>
</body>
</html>