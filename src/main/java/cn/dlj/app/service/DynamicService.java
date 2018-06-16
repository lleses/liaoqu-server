package cn.dlj.app.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.dlj.app.dao.DynamicDao;
import cn.dlj.app.entity.Dynamic;
import cn.dlj.utils.Config;
import cn.dlj.utils.FileUtils;

@Service
@Transactional(readOnly = true)
public class DynamicService {

	@Autowired
	private DynamicDao dao;

	public static final String UPLOAD_PATH = Config.get("upload.path");

	@Transactional
	public Integer add(Dynamic dynamic) {
		dao.add(dynamic);
		if (dynamic != null && dynamic.getId() != null) {
			return dynamic.getId();
		}
		return null;
	}

	public List<Dynamic> getByUserId(Integer userId) {
		List<Dynamic> list = new ArrayList<Dynamic>();
		if (userId != null) {
			list = dao.getByUserId(userId);
		}
		return list;
	}

	public void handleAdd(Integer userId, Date addTime, String content, Integer stencilType, Integer imgNum, Integer authority, String imgsBasc64) throws IOException {
		Dynamic dynamic = new Dynamic();
		dynamic.setUserId(userId);
		dynamic.setAddTime(addTime);
		dynamic.setContent(content);
		dynamic.setStencilType(stencilType);
		dynamic.setImgNum(imgNum);
		dynamic.setAuthority(authority);
		dynamic.setCommentNum(0);
		dynamic.setPraiseNum(0);
		if (imgNum != null && imgNum > 0) {
			//根据basc64生成图片并set进对象
			dynamic = handleImgs(dynamic, imgsBasc64, imgNum);
		}
		dao.add(dynamic);
	}

	/**
	 * 根据basc64生成图片并set进对象 
	 * 
	 * @param dynamic
	 * @param imgsBasc64
	 * @param imgNum
	 * @return
	 * @throws IOException
	 */
	private Dynamic handleImgs(Dynamic dynamic, String imgsBasc64, Integer imgNum) throws IOException {
		imgsBasc64 = imgsBasc64.replaceAll("data:image/jpg;base64,", "");
		imgsBasc64 = imgsBasc64.replaceAll("data:image/jpeg;base64,", "");
		String path = UPLOAD_PATH + "dynamicImgs/";
		String[] arr = imgsBasc64.split(",");
		if (imgNum == 1) {
			dynamic = handleImg1(dynamic, arr, path);
		} else if (imgNum == 2) {
			dynamic = handleImg2(dynamic, arr, path);
		} else if (imgNum == 3) {
			dynamic = handleImg3(dynamic, arr, path);
		} else if (imgNum == 4) {
			dynamic = handleImg4(dynamic, arr, path);
		} else if (imgNum == 5) {
			dynamic = handleImg5(dynamic, arr, path);
		} else if (imgNum == 6) {
			dynamic = handleImg6(dynamic, arr, path);
		} else if (imgNum == 7) {
			dynamic = handleImg7(dynamic, arr, path);
		}
		return dynamic;
	}

	/** 处理一张图的情况  */
	private Dynamic handleImg1(Dynamic dynamic, String[] arr, String path) throws IOException {
		String img1 = FileUtils.imgbasc64Two(arr[0], path);
		handle32(path + img1, 320, 200);
		dynamic.setImg1(img1);
		return dynamic;
	}

	/** 处理2张图的情况  */
	private Dynamic handleImg2(Dynamic dynamic, String[] arr, String path) throws IOException {
		String img1 = FileUtils.imgbasc64Two(arr[0], path);
		handle23(path + img1, 160, 210);
		dynamic.setImg1(img1);

		String img2 = FileUtils.imgbasc64Two(arr[1], path);
		handle23(path + img2, 160, 210);
		dynamic.setImg2(img2);
		return dynamic;
	}

	/** 处理3张图的情况  */
	private Dynamic handleImg3(Dynamic dynamic, String[] arr, String path) throws IOException {
		String img1 = FileUtils.imgbasc64Two(arr[0], path);
		handle23(path + img1, 160, 210);
		dynamic.setImg1(img1);

		String img2 = FileUtils.imgbasc64Two(arr[1], path);
		handle32(path + img2, 160, 110);
		dynamic.setImg2(img2);

		String img3 = FileUtils.imgbasc64Two(arr[2], path);
		handle32(path + img3, 160, 110);
		dynamic.setImg3(img3);
		return dynamic;
	}

	/** 处理4张图的情况  */
	private Dynamic handleImg4(Dynamic dynamic, String[] arr, String path) throws IOException {
		String img1 = FileUtils.imgbasc64Two(arr[0], path);
		handle32(path + img1, 160, 110);
		dynamic.setImg1(img1);

		String img2 = FileUtils.imgbasc64Two(arr[1], path);
		handle32(path + img2, 160, 110);
		dynamic.setImg2(img2);

		String img3 = FileUtils.imgbasc64Two(arr[2], path);
		handle32(path + img3, 160, 110);
		dynamic.setImg3(img3);

		String img4 = FileUtils.imgbasc64Two(arr[3], path);
		handle32(path + img4, 160, 110);
		dynamic.setImg4(img4);
		return dynamic;
	}

	/** 处理5张图的情况  */
	private Dynamic handleImg5(Dynamic dynamic, String[] arr, String path) throws IOException {
		String img1 = FileUtils.imgbasc64Two(arr[0], path);
		handle32(path + img1, 160, 110);
		dynamic.setImg1(img1);

		String img2 = FileUtils.imgbasc64Two(arr[1], path);
		handle32(path + img2, 160, 110);
		dynamic.setImg2(img2);

		String img3 = FileUtils.imgbasc64Two(arr[2], path);
		handle11(path + img3, 110, 110);
		dynamic.setImg3(img3);

		String img4 = FileUtils.imgbasc64Two(arr[3], path);
		handle11(path + img4, 110, 110);
		dynamic.setImg4(img4);

		String img5 = FileUtils.imgbasc64Two(arr[4], path);
		handle11(path + img5, 110, 110);
		dynamic.setImg5(img5);
		return dynamic;
	}

	/** 处理6张图的情况  */
	private Dynamic handleImg6(Dynamic dynamic, String[] arr, String path) throws IOException {
		String img1 = FileUtils.imgbasc64Two(arr[0], path);
		handle11(path + img1, 110, 110);
		dynamic.setImg1(img1);

		String img2 = FileUtils.imgbasc64Two(arr[1], path);
		handle11(path + img2, 110, 110);
		dynamic.setImg2(img2);

		String img3 = FileUtils.imgbasc64Two(arr[2], path);
		handle11(path + img3, 110, 110);
		dynamic.setImg3(img3);

		String img4 = FileUtils.imgbasc64Two(arr[3], path);
		handle11(path + img4, 110, 110);
		dynamic.setImg4(img4);

		String img5 = FileUtils.imgbasc64Two(arr[4], path);
		handle11(path + img5, 110, 110);
		dynamic.setImg5(img5);

		String img6 = FileUtils.imgbasc64Two(arr[5], path);
		handle11(path + img6, 110, 110);
		dynamic.setImg6(img6);
		return dynamic;
	}

	/** 处理7张图的情况  */
	private Dynamic handleImg7(Dynamic dynamic, String[] arr, String path) throws IOException {
		String img1 = FileUtils.imgbasc64Two(arr[0], path);
		handle11(path + img1, 160, 160);
		dynamic.setImg1(img1);

		String img2 = FileUtils.imgbasc64Two(arr[1], path);
		handle11(path + img2, 80, 80);
		dynamic.setImg2(img2);

		String img3 = FileUtils.imgbasc64Two(arr[2], path);
		handle11(path + img3, 80, 80);
		dynamic.setImg3(img3);

		String img4 = FileUtils.imgbasc64Two(arr[3], path);
		handle11(path + img4, 80, 80);
		dynamic.setImg4(img4);

		String img5 = FileUtils.imgbasc64Two(arr[4], path);
		handle11(path + img5, 80, 80);
		dynamic.setImg5(img5);

		String img6 = FileUtils.imgbasc64Two(arr[5], path);
		handle32(path + img6, 160, 110);
		dynamic.setImg6(img6);

		String img7 = FileUtils.imgbasc64Two(arr[6], path);
		handle32(path + img7, 160, 110);
		dynamic.setImg7(img7);
		return dynamic;
	}

	/**
	 * 处理 宽高1比1 的形状
	 * 
	 * @param path
	 * @param zoomWidth
	 * @param zoomHeight
	 * @throws IOException
	 */
	private void handle11(String path, int zoomWidth, int zoomHeight) throws IOException {
		File f = new File(path);
		BufferedImage read = ImageIO.read(f);
		int sWidth = read.getWidth();//实际宽度
		int sHeight = read.getHeight();//实际高度
		int x = 0;
		int y = 0;
		if (sWidth > sHeight) {//实际宽度>实际高度
			//比例宽度
			int width = sHeight;
			//x = (实际宽度 -比例宽度)/2
			x = (sWidth - width) / 2;
			FileUtils.cut(x, y, width, sHeight, path);
		} else if (sWidth < sHeight) {//实际宽度<实际高度
			//比例高度
			int height = sWidth;
			//y = (实际高度-比例高度)/2
			y = (sHeight - height) / 2;
			FileUtils.cut(x, y, sWidth, height, path);
		}
		FileUtils.zoomImage(zoomWidth, zoomHeight, path);
	}

	/**
	 * 处理 宽高2比3 的形状
	 * 
	 * @param path
	 * @param zoomWidth
	 * @param zoomHeight
	 * @throws IOException
	 */
	private void handle23(String path, int zoomWidth, int zoomHeight) throws IOException {
		File f = new File(path);
		BufferedImage read = ImageIO.read(f);
		int sWidth = read.getWidth();//实际宽度
		int sHeight = read.getHeight();//实际高度
		int x = 0;
		int y = 0;
		//比例宽度
		float width = sHeight * 2 / 3;
		//实际宽度 > 比例宽度
		if (sWidth > width) {
			//(实际高度 - 比例高度)/2
			x = (int) (sWidth - width) / 2;
			System.out.println("x=" + x);

			FileUtils.cut(x, y, (int) width, sHeight, path);
		}
		FileUtils.zoomImage(zoomWidth, zoomHeight, path);
	}

	/**
	 * 处理 宽高3比2 的形状
	 * 
	 * @param path
	 * @param zoomWidth
	 * @param zoomHeight
	 * @throws IOException
	 */
	private void handle32(String path, int zoomWidth, int zoomHeight) throws IOException {
		File f = new File(path);
		// 读取文件宽，高
		BufferedImage read = ImageIO.read(f);
		int sWidth = read.getWidth();//实际宽度
		int sHeight = read.getHeight();//实际高度
		int x = 0;
		int y = 0;

		//比例高度
		float height = sWidth * 2 / 3;

		//实际高度 > 比例高度
		if (sHeight > height) {
			//(实际高度 - 比例高度)/2
			y = (int) (sHeight - height) / 2;
			System.out.println("y=" + y);
			FileUtils.cut(x, y, sWidth, (int) height, path);
		}
		FileUtils.zoomImage(zoomWidth, zoomHeight, path);
	}
}