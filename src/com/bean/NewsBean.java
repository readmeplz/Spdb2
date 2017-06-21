package com.bean;

/**
 * 新闻管理
 * 
 */
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.util.Constant;
import com.util.DBO;

public class NewsBean {

	private String date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
	private List list;
	private ResultSet rs = null;
	
	
	//不置顶新闻分页
	private int EVERYPAGENUM = 2;
	private int count = -1;
	private int qq = 0;
	private String sql="select count(*) from news where up='0' ";
	private String sql2="select * from news where up='0' order by id desc ";
	private String sql3="select count(*) from news where up='1' ";
	private String sql4="select * from news where up='1' order by id desc ";
    public void setEVERYPAGENUM(int EVERYPAGENUM){
    	this.EVERYPAGENUM=EVERYPAGENUM;
    }
    public int getMessageCount() { //得到信息总数
       DBO dbo=new DBO();
       dbo.open();
        try { 
            rs = dbo.executeQuery(sql);
            rs.next();
            count = rs.getInt(1);
            return count;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        } finally {
            //dbo.close();
        }
    }
    public int getPageCount() { //得到共多少页（根据每页要显示几条信息）
        if (count % EVERYPAGENUM == 0) {
            return count / EVERYPAGENUM;
        } else {
            return count / EVERYPAGENUM + 1;
        }
    }
    public List getMessage(int page) { //得到每页要显示的信息
        DBO dbo=new DBO();
        dbo.open();
        List list = new ArrayList();
        try {
            rs = dbo.executeQuery(sql2);
            for (int i = 0; i < (page - 1) * EVERYPAGENUM; i++) {
                rs.next();
            }
            for (int t = 0; t < EVERYPAGENUM; t++) {
                if (rs.next()) {
                    qq++;
                    List list2=new ArrayList();
                    list2.add(rs.getString(1));
    				list2.add(rs.getString(2));
    				list2.add(rs.getString(3));
    				list2.add(rs.getString(5));
    				list2.add(rs.getString(6));
    				list2.add(rs.getString(7));
    				list2.add(rs.getString(8));
    				list2.add(rs.getString(9));
    				list.add(list2);
                } else {
                    break; //减少空循环的时间
                }
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return list;
        } finally {
            //dbo.close();
        }
    }
    /////////////////////////////////////////
    //置顶新闻分页
    public int getMessageCountUp() { //得到信息总数
        DBO dbo=new DBO();
        dbo.open();
         try { 
             rs = dbo.executeQuery(sql3);
             rs.next();
             count = rs.getInt(1);
             return count;
         } catch (SQLException ex) {
             ex.printStackTrace();
             return -1;
         } finally {
             //dbo.close();
         }
     }
     public List getMessageUp(int page) { //得到每页要显示的信息
         DBO dbo=new DBO();
         dbo.open();
         List list = new ArrayList();
         try {
             rs = dbo.executeQuery(sql4);
             for (int i = 0; i < (page - 1) * EVERYPAGENUM; i++) {
                 rs.next();
             }
             for (int t = 0; t < EVERYPAGENUM; t++) {
                 if (rs.next()) {
                     qq++;
                     List list2=new ArrayList();
                     list2.add(rs.getString(1));
     				list2.add(rs.getString(2));
     				list2.add(rs.getString(3));
     				list2.add(rs.getString(5));
     				list2.add(rs.getString(6));
     				list2.add(rs.getString(7));
     				list2.add(rs.getString(8));
     				list2.add(rs.getString(9));
     				list.add(list2);
                 } else {
                     break; //减少空循环的时间
                 }
             }
             return list;
         } catch (SQLException ex) {
             ex.printStackTrace();
             return list;
         } finally {
             //dbo.close();
         }
     }
	
	//add news
	public int addNews(String title,String pic,String content,String adder,String ifhide){
		String sql = "insert into news (title,pic,content,addtime,adder,ifhide,visit,up) " +
				"values ('"+title+"','"+pic+"','"+content+"','"+date+"','"+adder+"','"+ifhide+"','0','0')";
		String sql2 = "update news set ifhide='0' where pic!='无'";
		DBO dbo = new DBO();
		dbo.open();
		try{
			if(!pic.equals("无")){
				dbo.executeUpdate(sql2);
			}
			int i = dbo.executeUpdate(sql);
			if(i == 1){
				return Constant.SUCCESS;
			}
			else{
				return Constant.SYSTEM_ERROR;
			}
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SYSTEM_ERROR;
		}finally{
			//dbo.close();
		}
	}
	
	//update news no pic
	public int updateNews(int id,String title,String content,String adder,String ifhide){
		String sql = "update news set title = '"+title+"',content='"+content+"',addtime='"+date+"'," +
				"adder='"+adder+"',ifhide='"+ifhide+"' where id = '"+id+"' ";
		DBO dbo = new DBO();
		dbo.open();
		try{
			int i = dbo.executeUpdate(sql);
			if(i == 1){
				return Constant.SUCCESS;
			}
			else{
				return Constant.SYSTEM_ERROR;
			}
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SYSTEM_ERROR;
		}finally{
			//dbo.close();
		}
	}
//	update news with pic
	public int updateNewsWithPic(int id,String title,String pic,String content,String adder,String ifhide){
		String sql = "update news set title = '"+title+"',pic='"+pic+"',content='"+content+"',addtime='"+date+"'," +
				"adder='"+adder+"',ifhide='"+ifhide+"' where id = '"+id+"' ";
		DBO dbo = new DBO();
		dbo.open();
		try{
			int i = dbo.executeUpdate(sql);
			if(i == 1){
				return Constant.SUCCESS;
			}
			else{
				return Constant.SYSTEM_ERROR;
			}
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SYSTEM_ERROR;
		}finally{
			//dbo.close();
		}
	}
	//delete news
	public int delNews(int id[],String dir){
		DBO dbo = new DBO();
		dbo.open();
		try{
			for(int i = 0;i<id.length;i++){
				rs = dbo.executeQuery("select pic from news where id='"+id[i]+"'");
				rs.next();
				String str=rs.getString(1);
				del(dir+str);
				dbo.executeUpdate("delete from  news  where  id = '"+id[i]+"'");				
			}
			return Constant.SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SYSTEM_ERROR;
		}finally{
			//dbo.close();
		}
	}
	public void del(String filepath) {
		try{
			File f = new File(filepath);//定义文件路径        
			if(f.exists()){//判断是文件还是目录
			    f.delete();//递归调用
			}
		}catch(Exception e){
			
		}
	}
	//屏蔽、显示新闻
	public int hideNews(int id){
		String sql = "update news set ifhide='1' where id='"+id+"'";
		String sql2 = "update news set ifhide='0' where id='"+id+"'";
		String sql3 = "select ifhide,pic from news where id='"+id+"'";
		DBO dbo = new DBO();
		dbo.open();
		try{
			rs = dbo.executeQuery(sql3);
			rs.next();
			int i = rs.getInt(1);
			String pic=rs.getString(2);
			if(!pic.trim().equals("无")){
				dbo.executeUpdate("update news set ifhide='0' where pic!='无'");
			}
			if(i == 1){
				int flag = dbo.executeUpdate(sql2);
				if(flag == 1)
					return Constant.SUCCESS;
				else
					return Constant.SYSTEM_ERROR;
			}
			else{
				int flag = dbo.executeUpdate(sql);
				if(flag == 1)
					return Constant.SUCCESS;
				else
					return Constant.SYSTEM_ERROR;
			}
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SYSTEM_ERROR;
		}finally{
			//dbo.close();
		}
	}
   //置顶 取消置顶 新闻
	public int upNews(int id){
		String sql = "update news set up='1' where id='"+id+"'";
		String sql2 = "update news set up='0' where id='"+id+"'";
		String sql3 = "select up from news where id='"+id+"'";
		DBO dbo = new DBO();
		dbo.open();
		try{
			rs = dbo.executeQuery(sql3);
			rs.next();
			int i = rs.getInt(1);
			if(i == 1){
				int flag = dbo.executeUpdate(sql2);
				if(flag == 1)
					return Constant.SUCCESS;
				else
					return Constant.SYSTEM_ERROR;
			}
			else{
				int flag = dbo.executeUpdate(sql);
				if(flag == 1)
					return Constant.SUCCESS;
				else
					return Constant.SYSTEM_ERROR;
			}
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SYSTEM_ERROR;
		}finally{
			//dbo.close();
		}
	}
	//get one news to update
	public List getOneNews(int id){
		String sql = "select * from news where id = '"+id+"'";
		DBO dbo = new DBO();
		list = new ArrayList();
		dbo.open();
		try{
			rs = dbo.executeQuery(sql);
			while(rs.next()){
				list.add(rs.getString(1));//0id
				list.add(rs.getString(2));//1title
				list.add(rs.getString(3));//2pic
				list.add(rs.getString(4));//3content
				list.add(rs.getString(5));//3content
				list.add(rs.getString(6));//5adder
				list.add(rs.getString(7));//6ifhide
				list.add(rs.getString(8));//7visit
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return list;
		}finally{
			//dbo.close();
		}
	}
	
	//get news count
	public int getNewsCount(){
		String sql = "select count (*) from news ";
		DBO dbo = new DBO();
		dbo.open();
		try{
			rs = dbo.executeQuery(sql);
			if(rs.next()){
				return rs.getInt(1);
			}
			else{
				return 0;
			}
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}finally{
			//dbo.close();
		}
	}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	//首页10条新闻
	public List getIndexNews(){
		String sql = "select id,title,addtime from news where  ifhide='1' order by id desc ";
		DBO dbo = new DBO();
		list = new ArrayList();
		dbo.open();
		try{
			rs = dbo.executeQuery(sql);			
			while(rs.next()){
				List list2 = new ArrayList();
				list2.add(rs.getString("id"));
				list2.add(rs.getString("title"));
				list2.add(rs.getString("addtime"));
				list.add(list2);
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return list;
		}finally{
			//dbo.close();
		}
	}
//	首页12条新闻
	public List getIndexNews12(){
		String sql = "select top 13 id,title,addtime from news where  ifhide='1' order by id desc ";
		DBO dbo = new DBO();
		list = new ArrayList();
		dbo.open();
		try{
			rs = dbo.executeQuery(sql);
			for(int i=0;i<6;i++){
				rs.next();
			}
			while(rs.next()){
				List list2 = new ArrayList();
				list2.add(rs.getString("id"));
				list2.add(rs.getString("title"));
				list2.add(rs.getString("addtime"));
				list.add(list2);
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return list;
		}finally{
			//dbo.close();
		}
	}
//	首页图片新闻
	public List getIndexPicNews(){
		String sql = "select  id,pic from news where  ifhide='1' and pic!='无' ";
		DBO dbo = new DBO();
		list = new ArrayList();
		dbo.open();
		try{
			rs = dbo.executeQuery(sql);
			if(rs.next()){
				list.add(rs.getString(1));
				list.add(rs.getString(2));
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return list;
		}finally{
			//dbo.close();
		}
	}
	//前台新闻页新闻列表
	public List getAllNews(){
		String sql = "select id,title,adder,addtime  from news   order by id desc ";
		DBO dbo = new DBO();
		list = new ArrayList();
		dbo.open();
		try{
			rs = dbo.executeQuery(sql);
			while(rs.next()){
				List list2 = new ArrayList();
				list2.add(rs.getString("id"));
				list2.add(rs.getString("title"));
				list2.add(rs.getString("adder"));
				list2.add(rs.getString("addtime"));
				list.add(list2);
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return list;
		}finally{
			//dbo.close();
		}
	}
//	5条热门新闻
	public List get5HotNews(){
		String sql = "select top 10 id,title from news where  ifhide='1' order by visit desc ";
		DBO dbo = new DBO();
		list = new ArrayList();
		dbo.open();
		try{
			rs = dbo.executeQuery(sql);
			while(rs.next()){
				List list2 = new ArrayList();
				list2.add(rs.getString("id"));
				list2.add(rs.getString("title"));
				list.add(list2);
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return list;
		}finally{
			//dbo.close();
		}
	}
//	更新点击率
	public int upVisit(int id){
		DBO dbo = new DBO();
		dbo.open();
		try{
			int i = dbo.executeUpdate("update  news set visit=visit+1  where  id = '"+id+"'");		
			if(i==1){
				return Constant.SUCCESS;
			}
			else{
				return Constant.SYSTEM_ERROR;
			}
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SYSTEM_ERROR;
		}finally{
			//dbo.close();
		}
	}
	//下一条
	public List getNextNews(int id){
		String sql = "select top 1 id,title from news where  ifhide='1' and id>'"+id+"' order by id asc";
		DBO dbo = new DBO();
		list = new ArrayList();
		dbo.open();
		try{
			rs = dbo.executeQuery(sql);
			while(rs.next()){
				list.add(rs.getString("id"));
				list.add(rs.getString("title"));
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return list;
		}finally{
			//dbo.close();
		}
	} 
//	 上一条
	public List getFollowNews(int id){
		String sql = "select top 1 id,title from news where  ifhide='1' and id<'"+id+"' order by id desc";
		DBO dbo = new DBO();
		list = new ArrayList();
		dbo.open();
		try{
			rs = dbo.executeQuery(sql);
			while(rs.next()){
				list.add(rs.getString("id"));
				list.add(rs.getString("title"));
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return list;
		}finally{
			//dbo.close();
		}
	} 
	////////////////////////////////////////////////////////////////////////////视频 增加
	public int addVideo(String title,String picurl,String chandi,String daoyan,String yanyuan,String pianchang,String url,String content,String adder){
		String sql = "insert into video (title,picurl,chandi,daoyan,yanyuan,pianchang,url,content,adder,addtime) " +
				"values ('"+title+"','"+picurl+"','"+chandi+"','"+daoyan+"','"+yanyuan+"','"+pianchang+"','"+url+"','"+content+"','"+adder+"','"+date+"')";
		DBO dbo = new DBO();
		dbo.open();
		try{
			int i = dbo.executeUpdate(sql);
			if(i == 1){
				return Constant.SUCCESS;
			}
			else{
				return Constant.SYSTEM_ERROR;
			}
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SYSTEM_ERROR;
		}finally{
			//dbo.close();
		}
	}
	

//	update 视频 修改
	public int updateVideo(int id,String title,String picurl,String chandi,String daoyan,String yanyuan,String pianchang,String url,String content){
		String sql = "update video set title = '"+title+"',picurl='"+picurl+"',chandi='"+chandi+"',daoyan='"+daoyan+"'," +
				"yanyuan='"+yanyuan+"',pianchang='"+pianchang+"',url='"+url+"',content='"+content+"' where id = '"+id+"' ";
		DBO dbo = new DBO();
		dbo.open();
		try{
			int i = dbo.executeUpdate(sql);
			if(i == 1){
				return Constant.SUCCESS;
			}
			else{
				return Constant.SYSTEM_ERROR;
			}
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SYSTEM_ERROR;
		}finally{
			//dbo.close();
		}
	}
	//delete 删除视频
	public int delVideo(int id){
		String sql="delete from video where id='"+id+"'";
		DBO dbo = new DBO();
		dbo.open();
		try{
			int i=dbo.executeUpdate(sql);
			if(i==1)
				return Constant.SUCCESS;
			else
				return Constant.SYSTEM_ERROR;
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SYSTEM_ERROR;
		}finally{
			//dbo.close();
		}
	}
	//////////////查询全部视频
	public List getAllVideo(){
		String sql="select * from video order by id desc";
		DBO dbo = new DBO();
		list =new ArrayList();
		dbo.open();
		try{
			rs=dbo.executeQuery(sql);
			while(rs.next()){
				List list2=new ArrayList();
				list2.add(rs.getString(1));
				list2.add(rs.getString(2));
				list2.add(rs.getString(3));
				list2.add(rs.getString(4));
				list2.add(rs.getString(5));
				list2.add(rs.getString(6));
				list2.add(rs.getString(7));
				list2.add(rs.getString(8));
				list2.add(rs.getString(9));
				list2.add(rs.getString(10));
				list2.add(rs.getString(11));
				list.add(list2);
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return list;
		}
	}
//////////////查询单个部视频详细信息
	public List getOneVideo(int id){
		String sql="select * from video where id='"+id+"'";
		DBO dbo = new DBO();
		list =new ArrayList();
		dbo.open();
		try{
			rs=dbo.executeQuery(sql);
			while(rs.next()){
				list.add(rs.getString(1));
				list.add(rs.getString(2));
				list.add(rs.getString(3));
				list.add(rs.getString(4));
				list.add(rs.getString(5));
				list.add(rs.getString(6));
				list.add(rs.getString(7));
				list.add(rs.getString(8));
				list.add(rs.getString(9));
				list.add(rs.getString(10));
				list.add(rs.getString(11));
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return list;
		}
	}
	//////////////////////////////////////////////////////////////////////////////////////////会员上传文件
	//增加
	public int addFiles(String title,String url,String intro,String member){
		String sql = "insert into files (title,url,intro,member,addtime,flag) " +
				"values ('"+title+"','"+url+"','"+intro+"','"+member+"','"+date+"','未审核')";
		DBO dbo = new DBO();
		dbo.open();
		try{
			int i = dbo.executeUpdate(sql);
			if(i == 1){
				return Constant.SUCCESS;
			}
			else{
				return Constant.SYSTEM_ERROR;
			}
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SYSTEM_ERROR;
		}finally{
			//dbo.close();
		}
	}
	

//	update  修改
	public int updateFiles(int id,String title,String url,String intro){
		String sql = "update files set title = '"+title+"',url='"+url+"',intro='"+intro+"' where id = '"+id+"' ";
		DBO dbo = new DBO();
		dbo.open();
		try{
			int i = dbo.executeUpdate(sql);
			if(i == 1){
				return Constant.SUCCESS;
			}
			else{
				return Constant.SYSTEM_ERROR;
			}
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SYSTEM_ERROR;
		}finally{
			//dbo.close();
		}
	}
	//delete 删除
	public int delFiles(int id){
		String sql="delete from files where id='"+id+"'";
		DBO dbo = new DBO();
		dbo.open();
		try{
			int i=dbo.executeUpdate(sql);
			if(i==1)
				return Constant.SUCCESS;
			else
				return Constant.SYSTEM_ERROR;
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SYSTEM_ERROR;
		}finally{
			//dbo.close();
		}
	}
	//审核会员上传
	public int shFiles(int id){
		String sql="select flag from files where id='"+id+"'";
		DBO dbo = new DBO();
		dbo.open();
		try{
			rs=dbo.executeQuery(sql);
			rs.next();
			String str=rs.getString(1);
			if(str.trim().equals("未审核")){
				int i=dbo.executeUpdate("update files set flag='已审核' where id='"+id+"'");
				if(i==1)
					return Constant.SUCCESS;
				else
					return Constant.SYSTEM_ERROR;
			}
			else{
				int i=dbo.executeUpdate("update files set flag='未审核' where id='"+id+"'");
				if(i==1)
					return Constant.SUCCESS;
				else
					return Constant.SYSTEM_ERROR;
			}
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SYSTEM_ERROR;
		}finally{
			//dbo.close();
		}
	}
	//////////////查询全部
	public List getAllFiles(){
		String sql="select * from files order by id desc";
		DBO dbo = new DBO();
		list =new ArrayList();
		dbo.open();
		try{
			rs=dbo.executeQuery(sql);
			while(rs.next()){
				List list2=new ArrayList();
				list2.add(rs.getString(1));
				list2.add(rs.getString(2));
				list2.add(rs.getString(3));
				list2.add(rs.getString(4));
				list2.add(rs.getString(5));
				list2.add(rs.getString(6));
				list2.add(rs.getString(7));
				list.add(list2);
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return list;
		}
	}
	//////////////查询全部
	public List getAllShFiles(){
		String sql="select * from files where flag='已审核' order by id desc";
		DBO dbo = new DBO();
		list =new ArrayList();
		dbo.open();
		try{
			rs=dbo.executeQuery(sql);
			while(rs.next()){
				List list2=new ArrayList();
				list2.add(rs.getString(1));
				list2.add(rs.getString(2));
				list2.add(rs.getString(3));
				list2.add(rs.getString(4));
				list2.add(rs.getString(5));
				list2.add(rs.getString(6));
				list2.add(rs.getString(7));
				list.add(list2);
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return list;
		}
	}
	public List getAllFiles(String member){
		String sql="select * from files where member='"+member+"' order by id desc";
		DBO dbo = new DBO();
		list =new ArrayList();
		dbo.open();
		try{
			rs=dbo.executeQuery(sql);
			while(rs.next()){
				List list2=new ArrayList();
				list2.add(rs.getString(1));
				list2.add(rs.getString(2));
				list2.add(rs.getString(3));
				list2.add(rs.getString(4));
				list2.add(rs.getString(5));
				list2.add(rs.getString(6));
				list2.add(rs.getString(7));
				list.add(list2);
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return list;
		}
	}
//////////////查询单个详细信息
	public List getOneFiles(int id){
		String sql="select * from files where id='"+id+"'";
		DBO dbo = new DBO();
		list =new ArrayList();
		dbo.open();
		try{
			rs=dbo.executeQuery(sql);
			while(rs.next()){
				list.add(rs.getString(1));
				list.add(rs.getString(2));
				list.add(rs.getString(3));
				list.add(rs.getString(4));
				list.add(rs.getString(5));
				list.add(rs.getString(6));
				list.add(rs.getString(7));
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return list;
		}
	}
	//////////////////////////////////////////////////////////////////////////////////////
	//////////////查询全部视频
	public List get6Video(){
		String sql="select * from video order by id desc";
		DBO dbo = new DBO();
		list =new ArrayList();
		dbo.open();
		try{
			rs=dbo.executeQuery(sql);
			while(rs.next()){
				List list2=new ArrayList();
				list2.add(rs.getString(1));
				list2.add(rs.getString(2));
				list2.add(rs.getString(3));
				list2.add(rs.getString(4));
				list2.add(rs.getString(5));
				list2.add(rs.getString(6));
				list2.add(rs.getString(7));
				list2.add(rs.getString(8));
				list2.add(rs.getString(9));
				list2.add(rs.getString(10));
				list2.add(rs.getString(11));
				list.add(list2);
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return list;
		}
	}
	public List get12Video(){
		String sql="select * from video order by id desc";
		DBO dbo = new DBO();
		list =new ArrayList();
		dbo.open();
		try{
			rs=dbo.executeQuery(sql);
			for(int i=0;i<7;i++){
				rs.next();
			}
			while(rs.next()){
				List list2=new ArrayList();
				list2.add(rs.getString(1));
				list2.add(rs.getString(2));
				list2.add(rs.getString(3));
				list2.add(rs.getString(4));
				list2.add(rs.getString(5));
				list2.add(rs.getString(6));
				list2.add(rs.getString(7));
				list2.add(rs.getString(8));
				list2.add(rs.getString(9));
				list2.add(rs.getString(10));
				list2.add(rs.getString(11));
				list.add(list2);
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return list;
		}
	}
	public List get21Video(){
		String sql="select * from video order by id desc";
		DBO dbo = new DBO();
		list =new ArrayList();
		dbo.open();
		try{
			rs=dbo.executeQuery(sql);
			for(int i=0;i<14;i++){
				rs.next();
			}
			while(rs.next()){
				List list2=new ArrayList();
				list2.add(rs.getString(1));
				list2.add(rs.getString(2));
				list2.add(rs.getString(3));
				list2.add(rs.getString(4));
				list2.add(rs.getString(5));
				list2.add(rs.getString(6));
				list2.add(rs.getString(7));
				list2.add(rs.getString(8));
				list2.add(rs.getString(9));
				list2.add(rs.getString(10));
				list2.add(rs.getString(11));
				list.add(list2);
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return list;
		}
	}
}

