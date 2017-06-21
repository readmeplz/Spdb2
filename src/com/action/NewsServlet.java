package com.action;

/**
 * 新闻管理-上传缩略图
 * 
 */
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.NewsBean;
import com.bean.SystemBean;
import com.util.Constant;
import com.util.Filter;
import com.util.SmartFile;
import com.util.SmartUpload;

public class NewsServlet extends HttpServlet {

	private ServletConfig config;
	/**
	 * Constructor of the object.
	 */
	public NewsServlet() {
		super();
	}

	final public void init(ServletConfig config) throws ServletException
    {
        this.config = config;  
    }

    final public ServletConfig getServletConfig()
    {
        return config;
    }
	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding(Constant.CHARACTERENCODING);
		response.setContentType(Constant.CONTENTTYPE);
		String sysdir = new SystemBean().getDir();
		HttpSession session = request.getSession();
		try{
			String username2 = (String)session.getAttribute("user");
			if(username2 == null){
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
			else{
				 String method = null;
				 NewsBean newsBean = new NewsBean();
				 SmartUpload mySmartUpload = new SmartUpload();//init
				 int count = 0;
				 try{
					 mySmartUpload.initialize(config,request,response);
		             mySmartUpload.upload(); 
		             method = mySmartUpload.getRequest().getParameter("method").trim();
		            if(method.equals("ADDNEWS")){//增加新闻
		            	String title = Filter.escapeHTMLTags(mySmartUpload.getRequest().getParameter("title").trim());
						String ifhide = Filter.escapeHTMLTags(mySmartUpload.getRequest().getParameter("ifhide").trim());
						String content = mySmartUpload.getRequest().getParameter("infoContent");
						if(content.length()>8000){
						request.setAttribute("message", "对不起，新闻内容不能超过8000个字符！");
						request.setAttribute("method", method);
						request.getRequestDispatcher(sysdir+"/news/edit.jsp").forward(request, response);
						}
						else{
							SmartFile file = mySmartUpload.getFiles().getFile(0);
			            	String fileExt=file.getFileExt();	            
			            	String path="/upload_file/news";
		                    count = mySmartUpload.save(path);
		                    if(file.getFilePathName().trim().equals("")){//如果无缩略图
		                    	int flag = newsBean.addNews(title, "无",content, "admin", ifhide);
								if(flag == Constant.SUCCESS){
									request.setAttribute("message", "增加新闻成功！");
									request.getRequestDispatcher(sysdir+"/news/index.jsp").forward(request, response);
								}
								else{
									request.setAttribute("message", "系统维护中，请稍后再试！");
									request.getRequestDispatcher(sysdir+"/news/index.jsp").forward(request, response);
								}
		                    }
		                    else{
		                    	int flag = newsBean.addNews(title, path+"/"+file.getFileName(),content, "admin", ifhide);
								if(flag == Constant.SUCCESS){
									request.setAttribute("message", "增加新闻成功！");
									request.getRequestDispatcher(sysdir+"/news/index.jsp").forward(request, response);
								}
								else{
									request.setAttribute("message", "系统维护中，请稍后再试！");
									request.getRequestDispatcher(sysdir+"/news/index.jsp").forward(request, response);
								}
		                    }
						}							
		            }
		            else if(method.equals("editnews")){//修改新闻
		            	String id = Filter.escapeHTMLTags(mySmartUpload.getRequest().getParameter("id").trim());
		            	String title = Filter.escapeHTMLTags(mySmartUpload.getRequest().getParameter("title").trim());
						String ifhide = Filter.escapeHTMLTags(mySmartUpload.getRequest().getParameter("ifhide").trim());
						String content = mySmartUpload.getRequest().getParameter("infoContent");
						SmartFile file = mySmartUpload.getFiles().getFile(0);
		            	String fileExt=file.getFileExt();	            
		            	String path="/upload_file/news";
	                    count = mySmartUpload.save(path);
	                    if(file.getFilePathName().trim().equals("")){//如果不修改缩略图
	                    	int flag = newsBean.updateNews(Integer.parseInt(id), title, content, "admin", ifhide);
							if(flag == Constant.SUCCESS){
								request.setAttribute("message", "修改新闻成功！");
								request.getRequestDispatcher(sysdir+"/news/index.jsp").forward(request, response);
							}
							else{
								request.setAttribute("message", "系统维护中，请稍后再试！");
								request.getRequestDispatcher(sysdir+"/news/index.jsp").forward(request, response);
							}
	                    }
	                    else{//如果修改缩略图
	                    	int flag = newsBean.updateNewsWithPic(Integer.parseInt(id), title, path+"/"+file.getFileName(), content, "admin", ifhide);
							if(flag == Constant.SUCCESS){
								request.setAttribute("message", "修改新闻成功！");
								request.getRequestDispatcher(sysdir+"/news/index.jsp").forward(request, response);
							}
							else{
								request.setAttribute("message", "系统维护中，请稍后再试！");
								request.getRequestDispatcher(sysdir+"/news/index.jsp").forward(request, response);
							}
	                    }
		            }
		            ///////////////////////////////////////////video
		            else if(method.equals("addvideo")){//增加
		            	String title = Filter.escapeHTMLTags(mySmartUpload.getRequest().getParameter("title").trim());
		            	String chandi = Filter.escapeHTMLTags(mySmartUpload.getRequest().getParameter("chandi").trim());
						String daoyan = Filter.escapeHTMLTags(mySmartUpload.getRequest().getParameter("daoyan").trim());
						String yanyuan = Filter.escapeHTMLTags(mySmartUpload.getRequest().getParameter("yanyuan").trim());
						String pianchang = Filter.escapeHTMLTags(mySmartUpload.getRequest().getParameter("pianchang").trim());
						String url = Filter.escapeHTMLTags(mySmartUpload.getRequest().getParameter("url").trim());
						String content = mySmartUpload.getRequest().getParameter("infoContent");
						if(content.length()>5000){
						request.setAttribute("message", "对不起，影片简介不能超过5000个字符！");
						request.setAttribute("method", method);
						request.getRequestDispatcher(sysdir+"/video/video.jsp").forward(request, response);
						}
						else{
							SmartFile file = mySmartUpload.getFiles().getFile(0);
			            	String path="/upload_file/video";
		                    count = mySmartUpload.save(path);
		                    int flag = newsBean.addVideo(title,path+"/"+file.getFileName(), chandi, daoyan, yanyuan, pianchang,url,content, "admin");
							if(flag == Constant.SUCCESS){
								request.setAttribute("message", "增加成功！");
								request.getRequestDispatcher(sysdir+"/video/video.jsp").forward(request, response);
							}
							else{
								request.setAttribute("message", "系统维护中，请稍后再试！");
								request.getRequestDispatcher(sysdir+"/video/video.jsp").forward(request, response);
							}
	                    }						
		            }
		            else if(method.equals("editvideo")){//修改
		            	String id = Filter.escapeHTMLTags(mySmartUpload.getRequest().getParameter("id").trim());
		            	String title = Filter.escapeHTMLTags(mySmartUpload.getRequest().getParameter("title").trim());
		            	String chandi = Filter.escapeHTMLTags(mySmartUpload.getRequest().getParameter("chandi").trim());
						String daoyan = Filter.escapeHTMLTags(mySmartUpload.getRequest().getParameter("daoyan").trim());
						String yanyuan = Filter.escapeHTMLTags(mySmartUpload.getRequest().getParameter("yanyuan").trim());
						String pianchang = Filter.escapeHTMLTags(mySmartUpload.getRequest().getParameter("pianchang").trim());
						String url = Filter.escapeHTMLTags(mySmartUpload.getRequest().getParameter("url").trim());
						String content = mySmartUpload.getRequest().getParameter("infoContent");
						if(content.length()>5000){
						request.setAttribute("message", "对不起，影片简介不能超过5000个字符！");
						request.setAttribute("method", method);
						request.getRequestDispatcher(sysdir+"/video/video.jsp").forward(request, response);
						}
						else{
							SmartFile file = mySmartUpload.getFiles().getFile(0);
			            	String path="/upload_file/video";
		                    count = mySmartUpload.save(path);
		                    int flag = newsBean.updateVideo(Integer.parseInt(id),title,path+"/"+file.getFileName(), chandi, daoyan, yanyuan, pianchang,url,content);
							if(flag == Constant.SUCCESS){
								request.setAttribute("message", "操作成功！");
								request.getRequestDispatcher(sysdir+"/video/video.jsp").forward(request, response);
							}
							else{
								request.setAttribute("message", "系统维护中，请稍后再试！");
								request.getRequestDispatcher(sysdir+"/video/video.jsp").forward(request, response);
							}
	                    }						
		            }
		            ///////////////////////////////////////////files
		            else if(method.equals("addfiles")){//增加
		            	String title = Filter.escapeHTMLTags(mySmartUpload.getRequest().getParameter("title").trim());
		            	String content = mySmartUpload.getRequest().getParameter("infoContent");
		            	String member = mySmartUpload.getRequest().getParameter("member");
						if(content.length()>5000){
						request.setAttribute("message", "对不起，简介不能超过5000个字符！");
						request.setAttribute("method", method);
						request.getRequestDispatcher("member/info/add.jsp").forward(request, response);
						}
						else{
							SmartFile file = mySmartUpload.getFiles().getFile(0);
			            	String path="/upload_file/video";
		                    count = mySmartUpload.save(path);
		                    int flag = newsBean.addFiles(title, path+"/"+file.getFileName(), content, member);
							if(flag == Constant.SUCCESS){
								request.setAttribute("message", "增加成功，请等待管理员审核！");
								request.getRequestDispatcher("member/info/files.jsp").forward(request, response);
							}
							else{
								request.setAttribute("message", "系统维护中，请稍后再试！");
								request.getRequestDispatcher("member/info/files.jsp").forward(request, response);
							}
	                    }						
		            }
		            else if(method.equals("editfiles")){//修改
		            	String id = Filter.escapeHTMLTags(mySmartUpload.getRequest().getParameter("id").trim());
		            	String title = Filter.escapeHTMLTags(mySmartUpload.getRequest().getParameter("title").trim());
		            	String content = mySmartUpload.getRequest().getParameter("infoContent");
						if(content.length()>5000){
						request.setAttribute("message", "对不起，简介不能超过5000个字符！");
						request.setAttribute("method", method);
						request.getRequestDispatcher("member/info/add.jsp").forward(request, response);
						}
						else{
							SmartFile file = mySmartUpload.getFiles().getFile(0);
			            	String path="/upload_file/video";
		                    count = mySmartUpload.save(path);
		                    int flag = newsBean.updateFiles(Integer.parseInt(id), title, path+"/"+file.getFileName(), content);
							if(flag == Constant.SUCCESS){
								request.setAttribute("message", "操作成功！");
								request.getRequestDispatcher("member/info/files.jsp").forward(request, response);
							}
							else{
								request.setAttribute("message", "系统维护中，请稍后再试！");
								request.getRequestDispatcher("member/info/files.jsp").forward(request, response);
							}
	                    }						
		            }
		            else{
		            	request.getRequestDispatcher("error.jsp").forward(request, response);
		            }
		        }catch(Exception ex){
		        	ex.printStackTrace();
		        	request.getRequestDispatcher("error.jsp").forward(request, response);
		        }
			}
		}catch(Exception e){
			e.printStackTrace();
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occure
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
