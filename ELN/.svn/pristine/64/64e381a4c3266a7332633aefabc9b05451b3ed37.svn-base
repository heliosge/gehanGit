package com.jftt.wifi.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

public  class FileUtil {

	/**  
	 * 功能描述：提供一个公共的向页面写文件的方法
	 * @param response action传过来的一个reponse变量
	 * @param path   需要写回页面的文件的路径
	 * @param isDelete  文件写完是否需要删除源文件
	 */
	public static void writeFile(HttpServletResponse response,String exportFileName,String path,boolean isDelete){
		
		OutputStream toClient = null;
		File file = new File(path);
		InputStream fis;
		try {
			fis = new BufferedInputStream(new FileInputStream(path));
			byte[] buffer = new byte[4096];
			int readByte = 0;
			response.reset();
			//response.setCharacterEncoding("utf-8");
			// 设置response的Header
			response.setHeader("Content-Type", "application/x-download");
			try {
			response.addHeader("Content-Disposition","attachment;filename*=utf-8''" + URLEncoder.encode(exportFileName,"UTF-8")); 
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			response.setContentType("application/octet-stream");
			try {
				// 导出文件
				toClient = response.getOutputStream();
				while ((readByte = fis.read(buffer)) > 0) {
					toClient.write(buffer, 0, readByte);
				}
				toClient.flush();
				fis.close();
				toClient.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				if(isDelete){
					file.delete();
				}
			}
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	}
	
	/**  
	 * 功能描述：删除文件的方法
	 * @param file  需要删除的文件
	 */
	public static void deleteFile(File file){
		
		if(file.isFile()&&file.exists()){
			file.delete();
		}else if(file.isDirectory()){
			
			for(File sfile:file.listFiles()){
				sfile.delete();
			}
			file.delete();
		}
	}
	
	/**  
	 * 功能描述：删除文件的方法
	 * @param path  需要删除的文件的路径
	 */
	public static void deleteFile(String path){
		if(StringUtils.isNotBlank(path)){
			File file = new File(path);
			deleteFile(file);
		}
	}
	
	/**
	 * 写新Utf8文件
	 * @param filePath
	 * @param dataString
	 */
	public static boolean writeUtf8File(String filePath,String dataString) {

		boolean flage = true;
		File file = new File(filePath);
		try {
			FileOutputStream fos = new FileOutputStream(file, true) ;				
			OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");			
			BufferedWriter br = new BufferedWriter(osw);			
			br.write(dataString);
			br.newLine();
			br.flush();
			br.close();
		} catch (Exception e) {
			flage = false;
			e.printStackTrace();
		}
		
		return flage;
	}
	
	  public static void SaveFileFromInputStream(InputStream stream,String filePath) throws IOException {      
	        FileOutputStream fs=new FileOutputStream( filePath );
	        byte[] buffer =new byte[1024*1024];
	        int bytesum = 0;
	        int byteread = 0; 
	        while ((byteread=stream.read(buffer))!=-1)
	        {
	           bytesum+=byteread;
	           fs.write(buffer,0,byteread);
	           fs.flush();
	        } 
	        fs.close();
	        stream.close();      
	    }       
	  public static File toFile(String path){
	        File file = new File(path);
	        return file;
	    }

	    public static boolean isExist(File file){
	        return file.exists();
	    }

	    public static boolean isExist(String path){
	        return isExist(toFile(path));
	    }

	    public static boolean isDir(File file){
	        return file.exists() && file.isDirectory();
	    }

	    public static boolean isDir(String path){
	        return isDir(toFile(path));
	    }

	    public static boolean isFile(File file){
	        return file.exists() && file.isFile();
	    }

	    public static boolean isFile(String path){
	        return isFile(toFile(path));
	    }

	    public static boolean mkdir(File file){
	        try{
	            return file.mkdirs();
	        }
	        catch(Exception e){
	            return false;
	        }
	    }

	    public static boolean mkdir(String path){
	        return mkdir(toFile(path));
	    }

	    public static boolean createFile(File file){
	        try{
	            return file.createNewFile();
	        }
	        catch(Exception e){
	            return false;
	        }
	    }

	    public static boolean createFile(String path){
	        return createFile(toFile(path));
	    }

	    public static boolean copy(File src, File target){
	        if(isFile(src)){
	                return copyFile(src, target);
	        }
	        if(isDir(src) && !isFile(target)){
	            return copyFilePath(src, target);
	        }
	        return false;
	    }

	    public static boolean copy(String src, String target){
	        return copy(toFile(src), toFile(target));
	    }

	    protected static boolean copyFile(File src, File target){
	        try {
//	            System.out.println("src="+src.getPath() + "|" + src.getName());
//	            System.out.println("target="+target.getPath() + "|" + target.getName());
	            String fileName =  src.getName();
	            if(!target.getPath().contains(fileName)){
	                target = new File(target.getPath() + "/" + fileName);

	            }
	            String path = getFilePath(target.getPath());
	            if(!isDir(path)){
	                mkdir(path);
	            }
//	            if(isDir(target)){
//	                target = new File(target.getPath() + "/" + src.getName());
//	            }

//	            System.out.println("target="+target.getPath() + "|" + target.getName());
	            FileChannel in = new FileInputStream(src).getChannel();
	            FileChannel out = new FileOutputStream(target).getChannel();
	            ByteBuffer bb = ByteBuffer.allocate(1024);
	            while(in.read(bb) != -1){
	                bb.flip();
	                out.write(bb);
	                bb.clear();
	            }
	            in.close();
	            out.close();
	            return true;
	        }
	        catch (Exception e) {
	            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
	            return false;
	        }
	    }

	    protected static boolean copyFile(String src, String target){
	        return copyFile(toFile(src), toFile(target));
	    }

	    protected static boolean copyFilePath(File src, File target){
	        if(!isExist(target) && !mkdir(target)){
	            return false;
	        }
	        String[] list = src.list();
	        String srcPath = src.getPath();
	        String targetPath = target.getPath();
	        for(String fileName : list){
	            if(!copy(src + "/" + fileName, targetPath + "/" + fileName)){
	                return false;
	            }
	        }
	        return true;
	    }

	    public static boolean delete(File file){
	        if(!isExist(file)){
	            return false;
	        }
	        if(isFile(file)){
	            return file.delete();
	        }
	        String[] list = file.list();
	        String filePath = file.getPath();
	        for(String fileName : list){
	            if(!delete(filePath + "/" + fileName)){
	                return false;
	            }
	        }
	        return file.delete();
	    }

	    public static boolean delete(String file){
	        return delete(toFile(file));
	    }

	    public static boolean move(File src, File target){
	        if(src.getPath().equals(target.getPath())){
	            return true;
	        }
	        if(copy(src, target)){
	            return delete(src);
	        }
	        return false;
	    }

	    public static boolean move(String src , String target){
	        return move(toFile(src), toFile(target));
	    }

	    public static boolean hasFiles(String src, String...fileNames){
	        return hasFiles(toFile(src), fileNames);
	    }
	    public static boolean hasFiles(File src, String...fileNames){
	        File[] files = src.listFiles();
	        if(files == null){
	            return false;
	        }
	        if(fileNames == null || fileNames.length == 0){
	            return false;
	        }
	        int x = 0;
	        for(File file : files){
	            for(String fileName : fileNames){
	                if(file.getName().equalsIgnoreCase(fileName)){
	                    x++;
	                }
	            }
	        }
	        return x == fileNames.length;
	    }
	    public static File[] getFiles(String src, String...exts){
	        return getFiles(toFile(src), exts);
	    }

	    public static File[] getFiles(File src, String...exts){
	        File[] files = src.listFiles();
	        if(files == null){
	            return new File[0];
	        }
	        if(exts == null || exts.length == 0){
	            return files;
	        }
	        Set<File> set = new HashSet<File>();
	        for(File file : files){
	            for(String ext : exts){
	                if(ext == null){
	                    continue;
	                }
	                if(getExt(file).equalsIgnoreCase(ext)){
	                    set.add(file);
	                }
	            }
	        }
	        return set.toArray(new File[0]);
	    }

	    public static File[] getDirs(String src){
	        return getDirs(toFile(src));
	    }

	    public static File[] getDirs(File src){
	        File[] files = src.listFiles();
	        if(files == null){
	            return null;
	        }
	        Set<File> set = new HashSet<File>();
	        for(File file : files){
	            if(file.isDirectory()){
	                set.add(file);
	            }
	        }
	        return set.toArray(new File[0]);
	    }

	    public static String getName(File file){
	        return file.getName();
	    }

	    public static String getName(String path){
	        return getName(toFile(path));
	    }

	    public static String getPath(File file){
	        if(isFile(file)){
	            String filePath = file.getPath();
	            int pos = filePath.lastIndexOf(47);// "/"
	            if(pos == -1){
	                pos = filePath.lastIndexOf(92);// "\"
	            }
	            if(pos != -1){
	                return filePath.substring(pos + 1);
	            }
	        }
	        return file.getPath();
	    }

	    public static String getPath(String path){
	        return getPath(toFile(path));
	    }

	    public static String getExt(File file){
	        if(isFile(file)){
	            String fileName = getName(file);
	            int pos = fileName.lastIndexOf(".");
	            if(pos != -1){
	                return fileName.substring(pos + 1);
	            }
	        }
	        return "";
	    }

	    public static String getExt(String path){
	        return getExt(toFile(path));
	    }

	    public static long getSize(File file){
	        if(isFile(file)){
	            return file.length();
	        }
	        if(isDir(file)){
	            long len = 0L;
	            String[] list = file.list();
	            String filePath = file.getPath();
	            for(String fileName : list){
	                len += getSize(filePath + "/" + fileName);
	            }
	            return len;
	        }
	        return 0L;
	    }

	    public static long getSize(String path){
	        return getSize(toFile(path));
	    }

	    public static String getSizeStr(File file){
	        long len = getSize(file);
	        if(len == 0L){
	            return "0K";
	        }
	        long k = len/1024;

	        if(k < 1024){
	            return k + "K";
	        }
	        long m = k/1024;
	        if(m < 1024){
	            return m + "M" + (k%1024) + "K";
	        }
	        long g = m/1024;
	        
	        return g + "G" + (m%1024) + "M";


	    }

	    public static String getSizeStr(String path){
	        return getSizeStr(toFile(path));
	    }

	    /**
	     * extract file name form the given file path
	     * @param filePath path to the file, like 'c:/test.jpg', 'c:\\test.jpg'
	     * @param withExtention indicate contain file.extention. true : contain | false : ignore
	     * @return fileName file.name;
	     */
	    public static String getFileName(String filePath,boolean withExtention){
	        filePath = filePath.replaceAll("\\\\", "/");
	        int sep = filePath.lastIndexOf("/");
	        if(withExtention)
	            return filePath.substring( sep + 1 );
	        return filePath.substring( sep + 1  , filePath.lastIndexOf("."));
	    }

	    /**
	     * get path to the given file , e.g. : c:\test\aaa.html -> c:\test\
	     * @param fileFullPath path to file;
	     * @return
	     */
	    public static String getFilePath(String fileFullPath){
	        int sep = fileFullPath.lastIndexOf("\\") == -1 ? fileFullPath.lastIndexOf("/") : fileFullPath.lastIndexOf("\\");
	        return fileFullPath.substring(0, sep + 1);
	    }
	    
	    /**
		 * 
		 * Author:XieGuolong <BR>
		 * Date:2014-7-21 下午2:40:00 <BR>
		 * Method name: downloadFile <BR>
		 * Description: 重命名文件，下载 <BR>
		 * @param request
		 * @param response
		 * @param file
		 * @param filename
		 * @throws Exception void <BR>
		 */
		public static void downloadFile(HttpServletRequest request,HttpServletResponse response,File file,String filename) throws Exception{
			InputStream fis;
		    try {
			fis = new FileInputStream(file);
			byte[] buffer = new byte[1024*8];
			response.reset();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < filename.length(); i++) {
				char c = filename.charAt(i);
				if (c >= 0 && c <= 255) {
					sb.append(c);
				} else {
					byte[] b;
					try {
						b = Character.toString(c).getBytes("utf-8");
					} catch (Exception ex) {
						b = new byte[0];
					}
					for (int j = 0; j < b.length; j++) {
						int k = b[j];
						if (k < 0)
							k += 256;
						sb.append("%" + Integer.toHexString(k).toUpperCase());
					}
				}
			}
			String pathtemp = sb.toString();
			
			response.addHeader("Content-Type","application/x-msdownload;charset=utf-8");
			response.addHeader("Content-Disposition", "attachment;filename="+ pathtemp);
			//设置返回的文件类型  
			response.addHeader("Content-Length", "" + file.length());
			//得到向客户端输出二进制数据的对象 
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
			//输出数据
			int bytes ;
			while ((bytes = fis.read(buffer, 0, buffer.length)) != -1) {
				toClient.write(buffer, 0, bytes);
			}
			toClient.close();
			fis.close();
			} catch (FileNotFoundException e) {
				throw new Exception("文件未找到！");
			} catch (IOException e) {
				throw new Exception("取消下载或文件读写异常！");
			}
		}
}
