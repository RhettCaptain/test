package test;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.util.UUID;

import com.opensymphony.xwork2.ActionSupport;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Component;

@Component
public class Test extends ActionSupport{
	
	int i=0;
	private File upload;
	private String uploadContentType;
	private String uploadFileName;
	
	private String savePath;
	private String dealPath;
	private ImgTool imgTool;
	private Boolean isSuccess;
	
	private String action;

	public String getAction() {
		return action;
	}
	
	public void setAction(String action) {
		this.action = action;
	}
	public String getDealPath() {
		return ServletActionContext.getServletContext().getRealPath(dealPath);
	}
	public void setDealPath(String dealPath) {
		this.dealPath = dealPath;
	}
	
	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public String getUploadContentType() {
		return uploadContentType;
	}
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		UUID uuid = UUID.randomUUID();  
        String str = uuid.toString();
        int i=uploadFileName.lastIndexOf(".");  
        String ext=uploadFileName.substring(i);
		this.uploadFileName = str+ext;
		
	}
	public String getSavePath() { 
		return ServletActionContext.getServletContext().getRealPath(savePath); 
	}
	public void setSavePath(String savePath) { 
		this.savePath = savePath;    
	}
	public ImgTool getImgTool() {
		return imgTool;
	}
	public void setImgTool(ImgTool imgtool) {
		this.imgTool = imgtool;
	}
	public Boolean getIsSuccess() {
		return isSuccess;
	}
	public void setIsSuccess(Boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	//定义处理用户请求的execute方法  
	public String execute() throws Exception
	{
		Method[] methods = imgTool.getClass().getDeclaredMethods();
		Method method = null;
		boolean isHas=false;
		for (Method item : methods) {
			if(item.getName().equals(getAction()))
			{
				method=item;
				isHas=true;
			}
		}
		if(!isHas)
		{
			 
			return INPUT; 
		}
		
		FileOutputStream fos=new FileOutputStream(getSavePath()+"\\"+getUploadFileName());
		FileInputStream fio=new FileInputStream(getUpload());
		byte[] buffer=new byte[1024];
		int len=0; 
		while((len=fio.read(buffer))>0)  
		{
			fos.write(buffer,0,len);
		}
		
		fos.close();
		fio.close();
		String oriPath = getSavePath()+"\\"+getUploadFileName();
		String dealPath = getDealPath()+"\\"+getUploadFileName();

		method.invoke(imgTool,oriPath,dealPath);	
		setIsSuccess(true);
		
		return SUCCESS;  
	}
	
	
}
