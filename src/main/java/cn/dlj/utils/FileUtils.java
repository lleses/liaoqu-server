package cn.dlj.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.codec.binary.Base64;

public class FileUtils {

	/**
	 * basc64转成图片文件
	 */
	public static String imgbasc64(Integer userId, String imgbasc64, String basePath) {
		imgbasc64 = imgbasc64.replaceAll("data:image/jpg;base64,", "");
		imgbasc64 = imgbasc64.replaceAll("data:image/jpeg;base64,", "");
		String fileName = userId + ".jpg";
		String path = basePath + fileName;
		write(imgbasc64, path);
		return fileName;
	}

	/**
	 * basc64转成图片文件
	 */
	public static String imgbasc64(String imgbasc64, String basePath) {
		imgbasc64 = imgbasc64.replaceAll("data:image/jpg;base64,", "");
		imgbasc64 = imgbasc64.replaceAll("data:image/jpeg;base64,", "");
		String fileName = IdUtils.id32() + ".jpg";
		String path = basePath + fileName;
		write(imgbasc64, path);
		return fileName;
	}

	/** 写文件 **/
	public static boolean write(String img64, String outPath) {
		FileOutputStream out = null;
		boolean succ = false;
		try {
			out = new FileOutputStream(outPath);
			Base64 B64 = new Base64();
			out.write(B64.decode(img64));
			out.flush();
			succ = true;
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
				}
			}
		}
		return succ;
	}

	/** 复制文件 */
	public static void copyFile(InputStream is, File file) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
			byte[] b = new byte[256];
			int k = 0;
			while ((k = is.read(b)) != -1) {
				fos.write(b, 0, k);
			}
			fos.flush();
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
				}
			}
			try {
				is.close();
			} catch (IOException e) {
			}
		}
	}
}
