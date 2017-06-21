<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,java.util.*,com.util.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:useBean id="yzm" scope="page" class="com.util.CheckCode"/>
<jsp:useBean id="sdir" scope="page" class="com.bean.SystemBean"/>
<%System.out.println("执行到此");
List list = sdir.getSiteInfo();
String str = list.get(0).toString();
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>网站后台管理登录---<%=str %></title>
<style type="text/css">
<!--
.font1 {  font-family: "宋体"; font-size: 12px; line-height: 130%}
a {  font-family: "宋体"; font-size: 12px}
a:link {  font-family: "宋体"; font-size: 12px; color: #CFD1E8; text-decoration: underline}
a:hover {  font-family: "宋体"; font-size: 12px; color: #FFCC00; text-decoration: none}
a:visited {  font-family: "宋体"; font-size: 12px; color: #CFD1E8; text-decoration: underline}
.input {  font-family: "宋体"; font-size: 12px; color: #FFFFFF; border: #4047A4; border-style: solid; border-top-width: 1px; border-right-width: 1px; border-bottom-width: 1px; border-left-width: 1px; background-color: #000077}
-->
</style>
</head>
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
 	String code=yzm.getCheckCode();
 	String dir=sdir.getDir();
%>
<body bgcolor="#000077" text="#000000" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
  <tr>
    <td background="<%=basePath %><%=dir %>/images/bg.jpg" valign="middle" align="center"> 
      <table width="360" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr>
          <td align="center"><object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"  codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=5,0,0,0" width="330" height="190" id=ShockwaveFlash1>
              <param name=movie value="<%=basePath %><%=dir %>/images/admin_m.swf">
              <param name=quality value=high>
			   <param name="wmode" value="transparent">
              <embed src="<%=basePath %><%=dir %>/images/admin_m.swf" quality=high pluginspage="http://www.macromedia.com/shockwave/download/index.cgi?P1_Prod_Version=ShockwaveFlash" type="application/x-shockwave-flash" width="330" height="190">
              </embed> 
            </object></td>
        </tr>
      </table>
      <table width="280" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr> 
          <td height="30">&nbsp;</td>
        </tr>
        <tr>
          <td bgcolor="#4047A4" height="1"></td>
        </tr>
      </table>
      <table width="280" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr>
          <td bgcolor="#4047A4" width="1"></td>
          <td> 
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td height="36">&nbsp;</td>
              </tr>
            </table>
            <table width="160" border="0" cellspacing="0" cellpadding="0" align="center">
		      <form action="<%=basePath %>Admin.shtml" name=form1 method=post onSubmit="return checkform(form1)" autocomplete="off">
              <tr> 
                <td colspan="3" class="news" height="5"><input type=hidden name=method value="one" /></td>
              </tr>
			 
              <tr> 
                <td width="5" class="nwes" height="36"></td>
                <td width="56" class="font1" height="36"><font color="#CFD1E8">用户名</font></td>
                <td> 
		            <input type="text" name="username"  size="15" class=input  onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" 
                              onkeyup="value=value.replace(/[\W]/g,'')"/>
                </td>
              </tr>
              <tr> 
                <td class="nwes" height="36">&nbsp; </td>
                <td class="font1" height="36"><font color="#CFD1E8">口　令</font></td>
                <td> 
		            <input type="password" name="password" value="" size="15" class=input onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" 
                              onkeyup="value=value.replace(/[\W]/g,'')">
                </td>
              </tr>
              <tr> 
                <td class="nwes" height="36">&nbsp; </td>
                <td class="font1" height="36"><font color="#CFD1E8">验证码</font></td>
                <td> 
		            <input type="text" name="yzm" value="" size="9" maxlength="4" class=input onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" 
                              onkeyup="value=value.replace(/[\W]/g,'')"><%=code %>
                </td>
              </tr>
              <tr> 
                <td height="5" colspan="3"></td>
              </tr>
              <tr> 
                <td>&nbsp; </td>
                <td align="center">&nbsp; </td>
                <td> 
                  <input type="image" border="0" name="imageField" src="<%=basePath %><%=dir %>/images/bt_login.gif" width="70" height="16">
                </td>
              </tr>
			  </form>	
            </table>
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td height="36">&nbsp;</td>
              </tr>
            </table>
          </td>
          <td bgcolor="#4047A4" width="1"></td>
        </tr>
      </table>
      <table width="280" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr>
          <td bgcolor="#4047A4" height="1"></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
        </tr>
      </table>
    </td>
  </tr>
</table>
</body>
</html>
<script language=Javascript>
	
	form1.username.focus()	

	function checkform(form)
	{
		var flag=true;
		if(form("username").value==""){alert("请输入用户名!");form("username").focus();return false};
		if(form("password").value==""){alert("请输入口令!");form("password").focus();return false};
		if(form("yzm").value==""){alert("请输入验证码!");form("yzm").focus();return false};
		if(isNaN(form("yzm").value)){alert("请正确输入验证码!");form("yzm").focus();return false};
		if(form("yzm").value!=<%=code%>){alert("验证码错误!");form("yzm").focus();return false};
		return flag;
	}
</script>
