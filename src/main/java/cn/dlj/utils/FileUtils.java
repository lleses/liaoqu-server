package cn.dlj.utils;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

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

	/**
	 * basc64转成图片文件,有两种，另外一种是b开头
	 */
	public static String imgbasc64Two(String imgbasc64, String basePath) {
		imgbasc64 = imgbasc64.replaceAll("data:image/jpg;base64,", "");
		imgbasc64 = imgbasc64.replaceAll("data:image/jpeg;base64,", "");
		String fileName = IdUtils.id32() + ".jpg";
		String path = basePath + fileName;
		String bigImgPath = basePath + "big" + fileName;//代表大图，原图的意思
		write(imgbasc64, path);
		write(imgbasc64, bigImgPath);
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

	/**
	 * 对图片裁剪，并把裁剪完的新图片保存
	 * 
	 * @param x
	 *            x 坐标
	 * @param y
	 *            y 坐标
	 * @param width
	 *            宽
	 * @param height
	 *            高
	 * @param path
	 *            源路径
	 * @throws IOException
	 */
	public static void cut(int x, int y, int width, int height, String path) throws IOException {
		FileInputStream is = null;
		ImageInputStream iis = null;
		try {
			is = new FileInputStream(path);
			iis = ImageIO.createImageInputStream(is);
			// 格式
			String format = path.substring(path.indexOf(".") + 1);
			Iterator<ImageReader> it = ImageIO.getImageReadersByFormatName(format);
			ImageReader reader = it.next();
			reader.setInput(iis, true);
			ImageReadParam param = reader.getDefaultReadParam();
			Rectangle rect = new Rectangle(x, y, width, height);
			param.setSourceRegion(rect);
			BufferedImage bi = reader.read(0, param);
			ImageIO.write(bi, format, new File(path));
		} finally {
			if (is != null)
				try {
					is.close();
				} catch (Exception e) {
				}
			if (iis != null)
				try {
					iis.close();
				} catch (Exception e) {
				}
		}

	}

	/**
	 * 图片缩放
	 * 
	 * @param width
	 *            宽
	 * @param height
	 *            高
	 * @param path
	 *            源路径
	 * @throws Exception
	 */
	public static void zoomImage(int width, int height, String path) {
		double wr = 0, hr = 0;
		File srcFile = new File(path);
		File destFile = new File(path);
		try {
			BufferedImage bufImg = ImageIO.read(srcFile);
			Image Itemp = bufImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			wr = width * 1.0 / bufImg.getWidth();
			hr = height * 1.0 / bufImg.getHeight();
			AffineTransformOp ato = new AffineTransformOp(AffineTransform.getScaleInstance(wr, hr), null);
			Itemp = ato.filter(bufImg, null);
			ImageIO.write((BufferedImage) Itemp, path.substring(path.lastIndexOf(".") + 1), destFile);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		String path = "/Users/didi/java/liaoqu/第二版/材料/分类图标/菜单/msg_handle.png";
		zoomImage(320, 568, path);
	}

}
