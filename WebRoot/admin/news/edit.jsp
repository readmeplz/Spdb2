<%@ page language="java" import="java.util.*"  contentType="text/html;charset=gb2312"%>
<%@ page language="java" import="com.fredck.FCKeditor.*" %> 
<%@ taglib uri="/WEB-INF/FCKeditor.tld" prefix="FCK" %> 
<jsp:useBean id="sn" scope="page" class="com.bean.SystemBean" />
<jsp:useBean id="newsb" scope="page" class="com.bean.NewsBean" />
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String dir=sn.getDir();
%>
<HTML><HEAD><TITLE>后台操作区</TITLE>
<LINK href="<%=basePath %><%=dir %>/images/Admin_Style.css" type=text/css rel=stylesheet>
<LINK href="<%=basePath %><%=dir %>/images/style.css" type=text/css rel=stylesheet>
<script type="text/javascript" src="<%=path %>FCKeditor/fckeditor.js"></script> 
<STYLE type=text/css>
BODY {
	MARGIN-LEFT: 0px; BACKGROUND-COLOR: #ffffff
}
.STYLE1 {color: #ECE9D8}
</STYLE>
</HEAD>
<script type="text/javascript">
function sub()
{
	if(document.form1.title.value.replace(/\s+$|^\s+/g,"").length<=0)
	{
		alert("请输入新闻标题！");
		document.form1.title.focus();
		return false;
	}
	if(document.form1.pic.value.replace(/\s+$|^\s+/g,"").length>0)
	{
		var fileext=form1.pic.value.substring(form1.pic.value.length-4,form1.pic.value.length); 
		fileext=fileext.toLowerCase(); 
		if(!(fileext=='.jpg')&&!(fileext=='.gif')) 
		{
			alert("对不起，文件格式不对,必须为jpg或gif格式文件!"); 
			form1.pic.focus(); 
			return false; 
		} 
		
	}
	 
	form1.submit();
}
</script>
<%
String message = (String)request.getAttribute("message");
	if(message == null){
		message = "";
	}
	if (!message.trim().equals("")){
		out.println("<script language='javascript'>");
		out.println("alert('"+message+"');");
		out.println("</script>");
	}
	request.removeAttribute("message");
%>
<%
	String username=(String)session.getAttribute("user");
	if(username==null){
		response.sendRedirect(path+"/error.jsp");
	}
	else{
		String method=request.getParameter("method");
		String ifhide = "";	
		String title="";
		String content="";
		String id="";
		String s="如无缩略图请保持为空";
		if(method.equals("editnews")){
			id=request.getParameter("id").trim();
			List newsList=newsb.getOneNews(Integer.parseInt(id));
			title=newsList.get(1).toString();
			content=newsList.get(3).toString();
			ifhide=newsList.get(5).toString();
			s="如不修改请保留为空";
		}		
		String str1="";String str2="";
		if(ifhide.trim().equals("0")){
			str2="checked";
		}else{
			str1="checked";
		}
%>
<BODY onload="document.form1.infoContent.value=document.form1.content.value" >
<TABLE height="100%" cellSpacing=0 cellPadding=0 width="100%" border=0>
  <TBODY>
  <TR>
    <TD align="left" vAlign=top >
<table width='100%' cellspacing='1' cellpadding='3' bgcolor='#CCCCCC' class="tablewidth">
<tr class="head"> 
      <td colspan="2">     
<%
	if(method.trim().equals("ADDNEWS")){
%>
        添加新闻 （新闻内容不能超过5000个字符）
<%}else{%>
	   修改新闻 （新闻内容不能超过5000个字符）
<%} %>
      </td>
    </tr>
	<form name="form1" action="<%=basePath %>News.shtml" method="post"  enctype="multipart/form-data" >
  <tr bgcolor='#FFFFFF'> <input type="hidden" name="method" value="<%=method %>"> <input type="hidden" name="id" value="<%=id %>">
    <td width='30%'><div align="right">标&nbsp;&nbsp;&nbsp;&nbsp;题：</div></td>
    <td ><input name="title" type="text" id="title" size="40" maxlength="100" value="<%=title %>"></td>
  </tr>
  <tr bgcolor='#FFFFFF'> 
    <td width='30%'><div align="right">缩&nbsp;略&nbsp;图：</div></td>
    <td ><input name="pic" type="file" contentEditable=false  size="40" maxlength="150" >&nbsp;<font color=red>300K以下gif或jpg格式图片<%=s %></font></td>
  </tr>
  <tr bgcolor='#FFFFFF'> 
      <td width='30%'> 
        <div align="right">是否显示： </div>
      </td>
      <td bgcolor='#FFFFFF' > 
        <input type="radio" name="ifhide" value="1" <%=str1 %>>
        显示 
        <input type="radio" name="ifhide" value="0" <%=str2 %>>
        不显示 </td>
    </tr>
  <tr bgcolor='#FFFFFF'> 
    <td colspan="2" valign="top"><TEXTAREA style="display:none" NAME="content" ROWS="20" COLS="70"><%=content %></TEXTAREA>	
	  <FCK:editor id="infoContent" basePath="/Spdb/FCKeditor/"
              width="100%"
              height="400"
              skinPath="/Spdb/FCKeditor/editor/skins/silver/"
              defaultLanguage="zh-cn"
              tabSpaces="8"
              imageBrowserURL="/Spdb/FCKeditor/editor/filemanager/browser/default/browser.html?Type=Image&Connector=connectors/jsp/connector"
              linkBrowserURL="/Spdb/FCKeditor/editor/filemanager/browser/default/browser.html?Connector=connectors/jsp/connector"
              flashBrowserURL="/Spdb/FCKeditor/editor/filemanager/browser/default/browser.html?Type=Flash&Connector=connectors/jsp/connector"
              imageUploadURL="/Spdb/FCKeditor/editor/filemanager/upload/simpleuploader?Type=Image"
              linkUploadURL="/Spdb/FCKeditor/editor/filemanager/upload/simpleuploader?Type=File"
              flashUploadURL="/Spdb/FCKeditor/editor/filemanager/upload/simpleuploader?Type=Flash">
      </FCK:editor>	
     </td>
    </tr>
  <tr bgcolor='#FFFFFF'> 
      <td colspan="2" align="center"> 
        <input class=mmcinb type='button' name='button' value='提交数据' onclick='sub()'>
      </td>
    </tr>
	</form>
</table>
   </TD>
	</TR>
  </TBODY>
</TABLE>
</BODY>
<%} %>
</HTML>
