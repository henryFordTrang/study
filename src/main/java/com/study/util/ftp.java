package com.study.util;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.io.File;
  
import sun.misc.BASE64Decoder;  
import sun.misc.BASE64Encoder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.util.Base64;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.model.ftpParam;
@Controller
@RequestMapping("/ftp")
public class ftp {
	
	String host = "49.233.85.23";
	int port = 21;
	String username = "sniper";
	String password = "173205081";
	Date date = new Date();
	SimpleDateFormat spd=new SimpleDateFormat("yyyy-MM-dd");
	String dateString=spd.format(date);
	String basePath = "/sniper/"+dateString+"/";
	String feedbackurl="http://"+host+basePath;
	private static Logger logger = Logger.getLogger(ftp.class); 
	
	@RequestMapping("/upload")
	@ResponseBody
	public String proceedupload(@RequestBody ftpParam ftpparam){
		Long start = System.currentTimeMillis();
		boolean ifsuccess=false;
		logger.info("到达接口,开始上传:"+System.currentTimeMillis());
		Base64 base64=new Base64();
		byte[]buffer = base64.decode(ftpparam.getStrbase64().split(",")[1]);
		FileOutputStream fos=null;
		FileInputStream fis = null;
		File file = new File(String.valueOf(System.currentTimeMillis()));
		try {
			fos = new FileOutputStream(file);
			fos.write(buffer);
			fis=new FileInputStream(file);
			ifsuccess=uploadFile(ftpparam.getFilename(),fis,true);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.getMessage();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.getMessage();
		}finally{
			try {
				fos.flush();
				fos.close();
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(feedbackurl+ftpparam.getFilename());
		Long duration = start - System.currentTimeMillis();
		logger.info("上传结束,耗时:"+duration+"毫秒");
		if(ifsuccess){
			return feedbackurl+ftpparam.getFilename();
		}else{
			return null;
		}
		
	}
	//private  Log logger = LogFactory.getLog(ftp.class);
	public boolean uploadFile(String filename, InputStream input,boolean close) {
		
		
		
		boolean result = false;
		FTPClient ftp = null;
		BufferedInputStream inStream = null;
		if(ftp==null){
			ftp = new FTPClient();
		}
		try {
			int reply = ftp.getReplyCode();
			if(!FTPReply.isPositiveCompletion(reply)){
				ftp.connect(host, port);// 连接FTP服务器
				// 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器
				ftp.login(username, password);// 登录
				reply = ftp.getReplyCode();
				if (!FTPReply.isPositiveCompletion(reply)) {
					ftp.disconnect();
					return result;
				}
			}
			String path = basePath;
			// 切换到上传目录changeWorkingDirectory
			boolean cdflag =changeDirectory(ftp, path);
			System.err.println("path:"+path+"\t"+cdflag+"----"+cdflag);
			if (path.length() != 0 && cdflag) {
				// 把远程系统上的目录切换到参数path所指定的目录
				ftp.changeWorkingDirectory(path);
				System.err.println(ftp.changeWorkingDirectory(path));
			}			
			inStream = new BufferedInputStream(input);
			ftp.enterLocalPassiveMode();
			// 设置上传文件的类型为二进制类型
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			//logger.info(filename + "ftp开始上传.....");
			// 上传文件
			if (!ftp.storeFile(new String(filename.getBytes("UTF-8"), "ISO-8859-1"), inStream)) {
				return result;
			}
			//logger.info(filename + "ftp上传成功");
			input.close();
			result = true;
		} catch (IOException e) {
			e.printStackTrace();
			//logger.info(e.getMessage());
			try {
				ftp.logout();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
				}
			}
			ftp=null;
		} finally {
			//if(close){
				try {
					ftp.logout();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (ftp.isConnected()) {
					try {
						ftp.disconnect();
					} catch (IOException ioe) {
					}
				}
				ftp=null;
			//}
			if (inStream != null) {
				try {
					inStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	/**
	 * 递归创建远程服务器目录
	 * 
	 * @param remote
	 *            远程服务器文件绝对路径
	 * 
	 * @return 目录创建是否成功
	 * @throws IOException
	 */
	public boolean changeDirectory(FTPClient ftpClient, String remote) {
		boolean success = true;
		try {
			String directory = remote.substring(0, remote.lastIndexOf("/") + 1);
			// 如果远程目录不存在，则递归创建远程服务器目录
			if (!directory.equalsIgnoreCase("/")
					&& !ftpClient.changeWorkingDirectory(directory)) {
				int start = 0;
				int end = 0;
				if (directory.startsWith("/")) {
					start = 1;
				} else {
					start = 0;
				}
				end = directory.indexOf("/", start);
				while (true) {
					String subDirectory = new String(remote.substring(start,
							end).getBytes("UTF-8"), "iso-8859-1");
					if (!ftpClient.changeWorkingDirectory(subDirectory)) {
						if (ftpClient.makeDirectory(subDirectory)) {
							ftpClient.changeWorkingDirectory(subDirectory);
						} else {
							success = false;
							return success;
						}
					}
					start = end + 1;
					end = directory.indexOf("/", start);
					// 检查所有目录是否创建完毕
					if (end <= start) {
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return success;
	}
    
    /** 
     * Description: 从FTP服务器下载文件 
     * @param host FTP服务器hostname 
     * @param port FTP服务器端口 
     * @param username FTP登录账号 
     * @param password FTP登录密码 
     * @param remotePath FTP服务器上的相对路径 
     * @param fileName 要下载的文件名 
     * @param localPath 下载后保存到本地的路径 
     * @return 
     */  
    public boolean downloadFile(String host, int port, String username, String password, String remotePath,
            String fileName, String localPath) {
        boolean result = false;
        FTPClient ftp = new FTPClient();
        try {
            int reply;
            ftp.connect(host, port);
            // 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器
            ftp.login(username, password);// 登录
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return result;
            }
            ftp.changeWorkingDirectory(remotePath);// 转移到FTP服务器目录
            FTPFile[] fs = ftp.listFiles();
            for (FTPFile ff : fs) {
                if (ff.getName().equals(fileName)) {
                    File localFile = new File(localPath + "/" + ff.getName());

                    OutputStream is = new FileOutputStream(localFile);
                    ftp.retrieveFile(ff.getName(), is);
                    is.close();
                }
            }

            ftp.logout();
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return result;
    }
}
