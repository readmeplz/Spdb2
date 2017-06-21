<%@ page language="java" import="java.util.*" contentType="text/html;charset=gb2312" %>
<%@ include file="iframe/head.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
<LINK href="images/default.css" type=text/css rel=stylesheet>
<LINK href="images/css.css" type=text/css rel=stylesheet>
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<STYLE type=text/css>
.ycbt {
	BORDER-RIGHT: #fff 1px solid; BORDER-TOP: #fff 1px solid; PADDING-LEFT: 1.8em; BACKGROUND-COLOR: #EAF2EF; BORDER-LEFT: #fff 1px solid; PADDING-TOP: 7px; BORDER-BOTTOM: #fff 1px solid; HEIGHT: 20px
}
.xsbt {
	BORDER-RIGHT: #fff 1px solid; BORDER-TOP: #fff 1px solid; PADDING-LEFT: 1.8em; BACKGROUND-IMAGE: url(images/head1.gif); BORDER-LEFT: #fff 1px solid; PADDING-TOP: 7px; BORDER-BOTTOM: #fff 1px solid; HEIGHT: 20px
}
.xsnr {
	DISPLAY: block
}
.ycnr {
	DISPLAY: none
}
</STYLE>
<SCRIPT type=text/javascript>
function tb_xs(t,m,n){
for(var i=1;i<=m;i++){
if (i != n){
document.getElementById("tb"+t+ "_bt" + i).className= "ycbt";
document.getElementById("tb"+t+ "_nr" + i).className= "ycnr";}
else{
document.getElementById("tb"+t+ "_bt" + i).className= "xsbt";
document.getElementById("tb"+t+ "_nr" + i).className= "xsnr";}}}
</SCRIPT>
<SCRIPT language=JavaScript>
<!--//屏蔽出错代码
function killErr(){
	return true;
}
window.onerror=killErr;
//-->
</SCRIPT>
<SCRIPT language=JavaScript>
<!--//处理大分类一行两个小分类
function autoTable(div){
	fs=document.getElementById(div).getElementsByTagName("TABLE");
	for(var i=0;i<fs.length;i++){
		fs[i].style.width='49.5%';
		if(i%2==1){
			if (document.all) {
				fs[i].style.styleFloat="right";
			}else{
				fs[i].style.cssFloat="right;";
			}
		}else{
			if (document.all) {
				fs[i].style.styleFloat="left";
			}else{
				fs[i].style.cssFloat="left;";
			}
		}
	}
}
//-->
</SCRIPT>
<SCRIPT language=JavaScript src="images/inc.js"></SCRIPT>
<SCRIPT language=JavaScript src="images/default.js"></SCRIPT>
<SCRIPT language=JavaScript src="images/swfobject.js"></SCRIPT>
<META content="MSHTML 6.00.2900.3268" name=GENERATOR>
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
<BODY text=#000000 bgColor=#ffffff leftMargin=0 topMargin=0>
<SCRIPT language=JavaScript>
<!--//目的是为了做风格方便
document.write('<div class="wrap">');
//-->
</SCRIPT>
<TABLE class=MainTable cellSpacing=0 cellPadding=0 width="100%" align=center border=0>
  <TBODY>
  <TR>
    <TD vAlign=top width="35%" height=200>
<!--*******************************幻灯开始*******************************-->
<TABLE  class=dragTable cellSpacing=0 cellPadding=0 width="100%" align=center border=0>
 <TBODY>
        <TR>
          <TD class=head>
            <SPAN class=TAG>图片资讯</SPAN>
            </TD>
		</TR>
        <TR>
		<TD align="center">
<SCRIPT type=text/javascript>
var imgUrl = new Array();
var imgtext = new Array();
var imgLink = new Array();

imgUrl[0]='images/1.jpg';
imgLink[0]='http://www.163.com';
imgtext[0]='春节快乐';

imgUrl[1]='images/2.jpg';
imgLink[1]='http://www.baidu.com';
imgtext[1]='浑河留守断桥';

imgUrl[2]='images/3.jpg';
imgLink[2]='http://www.sina.com';
imgtext[2]='第一滴血4';

imgUrl[3]='images/4.jpg';
imgLink[3]='http://www.yahoo.com.cn';
imgtext[3]='老城下的小狗';
				

var pics=imgUrl[0];
var links=imgLink[0];
var texts=imgtext[0];
for(var i=1;i<imgUrl.length;i++){
	pics+='|'+imgUrl[i];
	links+='|'+imgLink[i];
	texts+='|'+imgtext[i];
}

var focus_width=330
var focus_height=188
var text_height=22
var swf_height = focus_height+text_height

document.write('<object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" codebase="http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,0,0" width="'+ focus_width +'" height="'+ swf_height +'">');
document.write('<param name="allowScriptAccess" value="sameDomain" /><param name="movie" value="<%=basePath%>images/rollpic.swf" /><param name="quality" value="high" /><param name="bgcolor" value="#F0F0F0">');
document.write('<param name="menu" value="false"><param name=wmode value="opaque">');
document.write('<param name="FlashVars" value="pics='+pics+'&links='+links+'&texts='+texts+'&borderwidth='+focus_width+'&borderheight='+focus_height+'&textheight='+text_height+'">');
document.write('<embed src="<%=basePath%>images/rollpic.swf" wmode="opaque" FlashVars="pics='+pics+'&links='+links+'&texts='+texts+'&borderwidth='+focus_width+'&borderheight='+focus_height+'&textheight='+text_height+'" menu="false" bgcolor="#F0F0F0" quality="high" width="'+ focus_width +'" height="'+ focus_height +'" allowScriptAccess="sameDomain" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" />');
document.write('</object>');

</SCRIPT>
			</td>
		</TR>
	</TBODY>
</TABLE>
<!--*******************************幻灯结束*******************************-->
	</TD>
     <TD class=Side vAlign=top align=left width="40%" height=200>
<!--*******************************新闻资讯开始*******************************-->
 <TABLE  class=dragTable cellSpacing=0 cellPadding=0 width="100%" align=center border=0>
 <TBODY>
        <TR>
          <TD class=head id=tb1_bt1 onmouseover=tb_xs(1,2,1) width="100%" height="30">
            <SPAN class=TAG>娱乐资讯</SPAN>
		  </TD>
		 
		</TR>
        <TR>
		<TD class=middle  align="left" colspan="2">
<!--*******************************新闻资讯**********************************-->
               <TABLE class=xsnr id=tb1_nr1  cellSpacing=0 cellPadding=0 width="100%" border=0>
                    <TBODY>
                    
                    <%if(!newsList.isEmpty()){
					for(int news11=0;news11<newsList.size();news11++){
						List newsList2=(List)newsList.get(news11);
			%>
					<TR>
                      <TD align="left">
					  <A href=<%=basePath %>newsinfo.jsp?id=<%=newsList2.get(0).toString()%> target=_blank><%=newsList2.get(1).toString()%></A> <BR>
					  </TD>
					  <TD align="right">
					  <%=newsList2.get(2).toString()%><BR>
					 
					  </TD>
					  </TR>
					  <%}}else{} %>
					
				</TBODY>
			</TABLE>			
			</TD>
		</TR>
		</TBODY>
	</TABLE>
<!--*******************************新闻资讯结束*******************************-->
	</TD>
     <TD class=Side vAlign=top align=right width="25%">
<!--*******************************滚动公告开始*******************************-->
      <TABLE width="100%" height="240" border=0 cellPadding=0 cellSpacing=0 class=dragTable>
	  <TBODY>
        <TR>
          <TD class=head>
            <SPAN class=TAG>
            <P>站内公告</P>
            </SPAN>
		  </TD>
		</TR>
        <TR>
          <TD class=middle align=left>
        <MARQUEE onmouseover=if(document.all!=null){this.stop()} onmouseout=if(document.all!=null){this.start()} scrollAmount=1 scrollDelay=1 direction=up height=180>
            <TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
              <TBODY>
              <%if(!affList.isEmpty()){
					for(int aff=0;aff<affList.size();aff++){
						List affList2=(List)affList.get(aff);
			%>
			<TR>
                <TD width="100%">
				<%=affList2.get(1).toString() %>---<%=affList2.get(2).toString() %><BR>
				</TD>
			  </TR>
			<%
					}
				}
			  %>
     
			  </TBODY>
			 </TABLE>
			 </MARQUEE>
		  </TD>
		</TR>
	</TBODY>
<!--*******************************滚动公告结束*******************************-->
	</TABLE>
</TD>
   </TR>
  </TBODY>
</TABLE>
<!--*******************************影院开始*******************************-->
<TABLE class=MainTable style="MARGIN-TOP: 0px" cellSpacing=0 cellPadding=0 width="100%" align=center border=0>
  <TBODY>
  <TR>
    <TD class=Main vAlign=top height=100>
      <TABLE class=dragTable cellSpacing=0 cellPadding=0 width="100%" border=0>
	  <TBODY>
        <TR>
          <TD class=head>
            <SPAN class=TAG>视频点播</SPAN> 
		  </TD>
		</TR>
        <TR>
          <TD class=middle>
            <TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
              <TBODY>
              <TR>
       <%
              	List resList=news.get6Video();
              	if(!resList.isEmpty()){
              		for(int res=0;res<7;res++)
              		{
              			List resList2=(List)resList.get(res);
              %>
               <TD width="14%">
                  <DIV class=listpic>
                  <P class=img><a href="<%=basePath %>videoinfo.jsp?id=<%=resList2.get(0).toString()%>" target="_blank">
				  <IMG height=90 src="<%=basePath+resList2.get(2).toString()%>" width=120 onload=makesmallpic(this,120,90); border=0>
				  </A>
				  </P>
                  <P class=title style="TEXT-ALIGN: center">
                  <a href="<%=basePath %>videoinfo.jsp?id=<%=resList2.get(0).toString()%>" target="_blank">
				  <%=resList2.get(1).toString()%></a>
				  </P>
				  </DIV>
				</TD>
              <%
              		}
              	}
               %>       
              
			</TR>
			 <TR>
     
			</TR>
		</TBODY>
	</TABLE>
	<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
              <TBODY>
              <TR>
      
			 <TR>
       <%
              	List resList12=news.get12Video(); 
              	if(!resList12.isEmpty()){
              		for(int res=0;res<7;res++)
              		{
              			List resList2=(List)resList12.get(res);
              %>
               <TD width="14%">
                  <DIV class=listpic>
                  <P class=img><a href="<%=basePath %>videoinfo.jsp?id=<%=resList2.get(0).toString()%>" target="_blank">
				  <IMG height=90 src="<%=basePath+resList2.get(2).toString()%>" width=120 onload=makesmallpic(this,120,90); border=0>
				  </A>
				  </P>
                  <P class=title style="TEXT-ALIGN: center">
                  <a href="<%=basePath %>videoinfo.jsp?id=<%=resList2.get(0).toString()%>" target="_blank">
				  <%=resList2.get(1).toString()%></a>
				  </P>
				  </DIV>
				</TD>
              <%
              		}
              	}
               %>       
              
			</TR>
		</TBODY>
	</TABLE>
	<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
              <TBODY>
              <TR>
      
			 <TR>
       <%
              	List resList21=news.get21Video(); 
              	if(!resList21.isEmpty()){
              		for(int res=0;res<7;res++)
              		{
              			List resList2=(List)resList21.get(res);
              %>
               <TD width="14%">
                  <DIV class=listpic>
                  <P class=img><a href="<%=basePath %>videoinfo.jsp?id=<%=resList2.get(0).toString()%>" target="_blank">
				  <IMG height=90 src="<%=basePath+resList2.get(2).toString()%>" width=120 onload=makesmallpic(this,120,90); border=0>
				  </A>
				  </P>
                  <P class=title style="TEXT-ALIGN: center">
                  <a href="<%=basePath %>videoinfo.jsp?id=<%=resList2.get(0).toString()%>" target="_blank">
				  <%=resList2.get(1).toString()%></a>
				  </P>
				  </DIV>
				</TD>
              <%
              		}
              	}
               %>       
              
			</TR>
		</TBODY>
	</TABLE>
	</TD>
	</TR>
   </TBODY>
   </TABLE>
   </TD>
  </TR>
  </TBODY>
</TABLE>
<!--*******************************影院结束*******************************-->

<SCRIPT language=JavaScript>
<!--//目的是为了做风格方便
document.write('</div>');
//-->
</SCRIPT>
<SCRIPT language=JavaScript>
<!--
clickEdit.init();
//-->
</SCRIPT>
</BODY>
</HTML>

<%@ include file="iframe/foot.jsp"%>