package com.jftt.wifi.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.util.Assert;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

public class ConvertUtil {

	private static Logger log = Logger.getLogger(SendMessageUtil.class); 
	
	/**
	 * Method name: convertOfficeToSwf <BR>
	 * Description: 文件转换方法，可以把.doc .docx .ppt .pptx .xls .xlsx 文件转换成.swf文件 <BR>
	 * Remark: <BR>
	 * @param filepath  传入文件的路径及文件名(最前面不要带"/") 示例:   aa/bb.docx<BR>
	 * @return  boolean 发送结果 true:转换成功,false:转换失败<BR>
	 */
	@SuppressWarnings("resource")
	public static boolean convertOfficeToSwf(String filepath) {
		// 定义服务器的地址、用户名、密码
		String hostname = PropertyUtil.getConfigureProperties("SERVER_HOST");
		String username = PropertyUtil.getConfigureProperties("USER_NAME");
		String password = PropertyUtil.getConfigureProperties("PASS_WORD") + PropertyUtil.getDatabaseProperties("keyword");
		// 从property文件中获取路径前辍
		String filepath_prefix = PropertyUtil
				.getConfigureProperties("FILE_PATH");
		filepath = filepath_prefix + filepath;
		String fileName = filepath.substring(0, filepath.lastIndexOf("."));
		String fileExt = filepath.substring(filepath.lastIndexOf("."),filepath.length());
		// 拼出pdf和swf文件的路径
		String pdfFile = fileName + ".pdf";
		String swfFile = fileName + ".swf";
		log.info("pdf file path： " + pdfFile);
		log.info("swf file path： " + swfFile);
		String commadStr = "";
		if(fileExt.equals(".txt")) {
			//txtToPdf()
			commadStr = PropertyUtil.getConfigureProperties("TXT_TO_PDF")
					+ " "
					+ filepath
					+ " "
					+ pdfFile
					+ ";"
					+ PropertyUtil.getConfigureProperties("PDF_TO_SWF")
					+ " "
					+ pdfFile
					+ " -o "
					+ swfFile;
		} else {

			commadStr = PropertyUtil.getConfigureProperties("OFFICEFILE_TO_PDF")
					+ " "
					+ filepath
					+ " "
					+ pdfFile
					+ ";"
					+PropertyUtil.getConfigureProperties("PDF_TO_SWF")
					+ " "
					//+ ";pdf2swf "
					+ pdfFile
					+ " -o "
					+ swfFile;
		}
		log.info("command string： " + commadStr);
		
		// 指明连接主机的IP地址
		Connection conn = new Connection(hostname);
		Session ssh = null;
		try {
			// 连接到主机
			conn.connect();
			// 使用用户名和密码校验
			boolean isconn = conn.authenticateWithPassword(username, password);
			if (!isconn) {
				log.info("ssh connect fail，username or password error");
			} else {
				log.info("ssh connect success");
				ssh = conn.openSession();
				// 使用多个命令用分号隔开
				ssh.execCommand(commadStr.toString());
				// 只允许使用一行命令，即ssh对象只能使用一次execCommand这个方法，多次使用则会出现异常
				// 将屏幕上的文字全部打印出来

				InputStream stdout = new StreamGobbler(ssh.getStdout());  
				InputStream stderr = new StreamGobbler(ssh.getStderr());  
				BufferedReader stdoutReader = new BufferedReader(  
				                    new InputStreamReader(stdout));  
				            BufferedReader stderrReader = new BufferedReader(  
				                    new InputStreamReader(stderr));  

	            log.info("Here is the output from stdout:");
				while (true) {
					String line = stdoutReader.readLine();
					if (line == null)
						break;
					log.info(line);
				}

				log.info("Here is the output from stderr:");
				while (true) {
					String line = stderrReader.readLine();
					if (line == null)
						break;
					log.info(line);
				}

				log.info("ssh finish");
			}
			// 连接的Session和Connection对象都需要关闭
			ssh.close();
			conn.close();
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("file convert fail");
			return false;
		}
	}

	// 视频转码后的文件后缀
	private static final String FLV_FILE_EXT = ".flv";
	// 视频转码开关： on表示开启；off表示关闭
	private static final String VIDEO_CONVERT_SWITCH_ON = "on";
	// 支持的视频类型文件后缀名，暂时只有一种，可以按需添加
	// 注意需要对应修改相关的上传控件的参数：添加对应的后缀名
	private static final Set<String> SUPPORTED_VIDEO_TYPE_SET;
	static {
		SUPPORTED_VIDEO_TYPE_SET = new HashSet<String>();
		SUPPORTED_VIDEO_TYPE_SET.add(".mp4");
	}

	/**
	 * @author JFTT)wangyifeng
	 * Method name: needConvertToFlv <BR>
	 * Description: 判断是否需要进行视频转码 <BR>
	 * Remark: <BR>
	 * @param fileExt 文件后缀名
	 * @return  boolean 返回传入的文件后缀类型是否需要进行视频转码：true需要；false不需要<BR>
	 */
	public static boolean needConvertToFlv(String fileExt) {
		String VIDEO_CONVERT_SWITCH = PropertyUtil
				.getConfigureProperties("VIDEO_CONVERT_SWITCH");
		log.info(String.format("VIDEO_CONVERT_SWITCH value is %s.",
				VIDEO_CONVERT_SWITCH));
		// 视频转码开关不等于VIDEO_CONVERT_SWITCH_ON时，固定返回false，即认为不需要转码
		if (!VIDEO_CONVERT_SWITCH_ON.equals(VIDEO_CONVERT_SWITCH)) {
			return false;
		}
		Assert.hasText(fileExt);
		return SUPPORTED_VIDEO_TYPE_SET.contains(fileExt.toLowerCase());
	}

	/**
	 * @author JFTT)wangyifeng
	 * class name:ConvertToFlvFailedException <BR>
	 * class description: 视频转码异常类（运行时异常） <BR>
	 * Remark: <BR>
	 * @version 1.00 2015/09/24
	 */
	public static class ConvertToFlvFailedException extends RuntimeException {
		private static final long serialVersionUID = 1L;

		public ConvertToFlvFailedException(String message) {
			super(message);
		}

		public ConvertToFlvFailedException(String message, Throwable e) {
			super(message, e);
		}
	}

	/**
	 * Method name: convertToFlv <BR>
	 * Description: 视频文件转换方法，把常用的视频文件如：mp4,avi,3gp等转换成flv文件用于网页播放 <BR>
	 * Remark: <BR>
	 * 1.调用该方法前，请先用needToBeConvertedToFlv方法来确认待转换文件是否支持视频转码<BR>
	 * 2.视频转码失败时会抛出运行时异常：ConvertToFlvFailedException<BR>
	 * @param filepath 传入文件的路径及文件名(最前面不要带"/") 示例:   aa/bb.docx<BR>
	 * @return  String 发送结果 转换成功：转换后的文件路径及文件名；转换失败：抛出ConvertToFlvFailedException异常<BR>
	 */
	@SuppressWarnings("resource")
	public static String convertToFlv(String filepath) {
		// 定义服务器的地址、用户名、密码
		String hostname = PropertyUtil.getConfigureProperties("SERVER_HOST");
		String username = PropertyUtil.getConfigureProperties("USER_NAME");
		String password = PropertyUtil.getConfigureProperties("PASS_WORD")
				+ PropertyUtil.getDatabaseProperties("keyword");
		// 从property文件中获取路径前辍
		String filepath_prefix = PropertyUtil
				.getConfigureProperties("FILE_PATH");
		log.info("input video file: " + filepath_prefix + filepath);
		// 拿到文件扩展名
		String fileExt = filepath.substring(filepath.lastIndexOf("."),
				filepath.length());

		// 如果获取到的扩展名经判断无需转码，则扔异常
		if (!needConvertToFlv(fileExt)) {
			throw new ConvertToFlvFailedException(String.format(
					"传入的视频文件不需要转码: %s.", filepath_prefix + filepath));
		}

		String fileName = filepath.substring(0, filepath.lastIndexOf("."));
		// 拼出flv文件的路径
		String flvFile = fileName + FLV_FILE_EXT;
		log.info("flv file path： " + filepath_prefix + flvFile);

		// 拼接命令字符串
		String commadStr = "ffmpeg -i "
				+ filepath_prefix
				+ filepath
				+ " "
				+ PropertyUtil.getConfigureProperties("VIDEO_CONVERT_PARAMETER")
				+ " "
				+ filepath_prefix + flvFile;
		log.info("command string： " + commadStr);

		// 指明连接主机的IP地址
		Connection conn = new Connection(hostname);
		Session ssh = null;
		try {
			// 连接到主机
			conn.connect();
			// 使用用户名和密码校验
			boolean isconn = conn.authenticateWithPassword(username, password);
			if (!isconn) {
				log.info("ssh connect fail，username or password error");
				throw new ConvertToFlvFailedException(
						"ssh connect fail，username or password error");
			}
			log.info("ssh connect success");
			ssh = conn.openSession();
			// 使用多个命令用分号隔开
			ssh.execCommand(commadStr.toString());
			// 只允许使用一行命令，即ssh对象只能使用一次execCommand这个方法，多次使用则会出现异常
			// 将屏幕上的文字全部打印出来

			InputStream stdout = new StreamGobbler(ssh.getStdout());
			InputStream stderr = new StreamGobbler(ssh.getStderr());
			BufferedReader stdoutReader = new BufferedReader(
					new InputStreamReader(stdout));
			BufferedReader stderrReader = new BufferedReader(
					new InputStreamReader(stderr));

			log.info("Here is the output from stdout:");
			while (true) {
				String line = stdoutReader.readLine();
				if (line == null)
					break;
				log.info(line);
			}

			log.info("Here is the output from stderr:");
			while (true) {
				String line = stderrReader.readLine();
				if (line == null)
					break;
				log.info(line);
			}

			log.info(String.format("ssh finished with exit status: %d",
					ssh.getExitStatus()));

			if (ssh.getExitStatus() != 0) {
				throw new ConvertToFlvFailedException(
						"file convert fail: command exit code is not 0!");
			}
			return flvFile;
		} catch (IOException e) {
			log.error("file convert fail", e);
			throw new ConvertToFlvFailedException("file convert fail", e);
		} finally {
			// 连接的Session和Connection对象都需要关闭
			if (ssh != null) {
				ssh.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		convertToFlv("20-2.mp4");
//		convertOfficeToSwf("b2.xlsx");
//		convertOfficeToSwf("c1.ppt");
//		convertOfficeToSwf("c2.pptx");
	}

}
