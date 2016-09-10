package edu.feicui.contacts.db;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import edu.feicui.contacts.utils.LogUtil;

/** 用来操作本地Assets文件夹内db数据的管理类 */
public class AssetsDBManager{
	/**
	 * 复制本地Assets中的db文件到指定目录中
	 * 
	 * @param context
	 * @param path
	 *            Assets内db文件路径
	 * @param toFile
	 *            目标位置
	 * @throws IOException
	 */
	public static void copyAssetsFileToFile(Context context, String path, File toFile) throws IOException {
		LogUtil.d("AssetsDBManager", "copyAssetsFileToFile start");
		LogUtil.d("AssetsDBManager", "file path:" + path);
		LogUtil.d("AssetsDBManager", "toFile path:" + toFile.getAbsolutePath());
		InputStream inStream = null;
		// 输入流(用来读取当前项目的Assets内的db文本)
		BufferedInputStream buffInputStream = null;
		// 输出流(用来将读取到的db信息写到指定目录文件toFile中去)
		BufferedOutputStream buffOutputStream = null;
		try {
			inStream = context.getAssets().open(path);
			buffInputStream = new BufferedInputStream(inStream);
			buffOutputStream = new BufferedOutputStream(
					new FileOutputStream(toFile, false));
			// IO操作
			int len = 0;
			byte[] buff = new byte[2 * 1024];
			while ((len = buffInputStream.read(buff)) != -1) {
				buffOutputStream.write(buff, 0, len);
			}
			buffOutputStream.flush();
		} catch (IOException e) {
			// TODO: handle exception
			throw e;
		}finally{
			// IO关闭
			buffOutputStream.close();
			buffInputStream.close();
			inStream.close();
			LogUtil.d("AssetsDBManager", "copyAssetsFileToFile end");
		}
		
	}
}