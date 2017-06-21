<%@ page language="java" import="java.util.*"  contentType="text/html;charset=gb2312"%>
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
<SCRIPT language=JavaScript src="<%=basePath %><%=dir %>/images/Common.js"></SCRIPT>
<STYLE type=text/css>
BODY {
	MARGIN-LEFT: 0px; BACKGROUND-COLOR: #ffffff
}
.STYLE1 {color: #ECE9D8}
</STYLE>
</HEAD>
	
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
		
%>
<BODY oncontextmenu="return false;" onselectstart="return false;" leftMargin=0 
background=<%=basePath %><%=dir %>/images/MainBg.gif topMargin=0 scroll=yes 
marginheight="0" marginwidth="0">
<TABLE  cellSpacing=0 cellPadding=0 width="100%" border=0>
  <TBODY>
  <TR>
    <TD align="left" vAlign=top >
<table width='100%' cellspacing='1' cellpadding='3' bgcolor='#CCCCCC' class="tablewidth">
     <tr class="head"> 
      <td width="5%" align="center">ID </td>
      <td  align="center">视频名称</td>
      <td  align="center">产地</td>
      <td  align="center">导演</td>
      <td  align="center">主演</td>
      <td  align="center">片长</td>
      <td  align="center">发布人</td>
      <td  align="center">修改</td>
      <td  align="center">选择</td>
    </tr>
    <%
			List pagelist3=newsb.getAllVideo();
				if(!pagelist3.isEmpty()){
				for(int i=0;i<pagelist3.size();i++){
					List pagelist2 =(ArrayList)pagelist3.get(i);
			%> 
	<tr  class="trA" onMouseOver="this.className='trB'" onMouseOut="this.className='trA'"> 
      <td width='5%' align="center" style="border-bottom:1px dotted #ccc;"><%=i+1 %></td><input type="hidden" name="id<%=i %>" value="<%=pagelist2.get(0).toString()%>" >
      <td align="center" bgcolor="#FFFFFF"  style="border-bottom:1px dotted #ccc;"><a href="<%=basePath %>videoinfo.jsp?id=<%=pagelist2.get(0).toString()%>" target="_blank"><%=pagelist2.get(1).toString() %></a> </td>
      <td align="center" bgcolor="#FFFFFF"  style="border-bottom:1px dotted #ccc;"><%=pagelist2.get(3).toString() %> </td>
      <td align="center" bgcolor="#FFFFFF"  style="border-bottom:1px dotted #ccc;"><%=pagelist2.get(4).toString() %></td>
      <td align="center" bgcolor="#FFFFFF"  style="border-bottom:1px dotted #ccc;"><%=pagelist2.get(5).toString() %></td>
      <td align="center" bgcolor="#FFFFFF"  style="border-bottom:1px dotted #ccc;"><%=pagelist2.get(6).toString() %></td>
      <td align="center" bgcolor="#FFFFFF"  style="border-bottom:1px dotted #ccc;"><%=pagelist2.get(9).toString() %></td>
      <td align="center" bgcolor="#FFFFFF"  style="border-bottom:1px dotted #ccc;"><a href="<%=basePath+dir+"/" %>video/add.jsp?method=editvideo&id=<%=pagelist2.get(0).toString()%>" >修改</a> </td>
      <td align="center" bgcolor="#FFFFFF"  style="border-bottom:1px dotted #ccc;"><a href="<%=basePath %>Common.shtml?method=delvideo&id=<%=pagelist2.get(0).toString()%>" >删除</a> </td>
    </tr>
	
<%
	}}
%>  
  </TBODY>
</TABLE>
</BODY>
<%} %>
</HTML>
