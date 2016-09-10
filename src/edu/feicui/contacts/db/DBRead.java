package edu.feicui.contacts.db;

import java.io.File;
import java.util.ArrayList;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import edu.feicui.contacts.entity.TelclassInfo;
import edu.feicui.contacts.entity.TelnumberInfo;
import edu.feicui.contacts.utils.LogUtil;

/** ���������ݿ��ļ��Ķ�ȡ���� */
public class DBRead{
	/** ͨѶ��ȫFile */
	public static File telFile;
	static {
		// Ĭ��λ��
		String dbFileDir = "data/data/edu.feicui.contacts/";
		// �洢����
		// String sdcardState = Environment.getExternalStorageState();
		// if (sdcardState.equals(Environment.MEDIA_MOUNTED)) {
		// dbFileDir = Environment.getExternalStorageDirectory() +
		// "/azyphone/cache";
		// }
		File fileDir = new File(dbFileDir);
		fileDir.mkdirs(); // �ļ�Ŀ¼�Ĵ���
		telFile = new File(dbFileDir, "commonnum.db");
		LogUtil.d("DBRead", "telFile dir path: " + dbFileDir);
	}

	/** ����Ƿ����ͨѶ��ȫDB�ļ� */
	public static boolean isExistsTeldbFile() {
		// û��ͨѶ��ȫFile
		File toFile = DBRead.telFile;
		if (!toFile.exists() || toFile.length() <= 0) {
			return false;
		}
		return true;
	}

	/** ��ȡtelFile������ݿ��ļ��е�classlist������ڵ����� 
	 * @throws Exception */
	public static ArrayList<TelclassInfo> readTeldbClasslist() throws Exception {
		ArrayList<TelclassInfo> classListInfos = new ArrayList<TelclassInfo>();
		
		SQLiteDatabase db = null;
		
		Cursor cursor = null;
		try {
			// ��DB�ļ�
			db = SQLiteDatabase.openOrCreateDatabase(telFile, null);
			// ִ�в�ѯ��SQL��� select * from classlist
			cursor = db.rawQuery("select * from classlist", null);
			LogUtil.d("DBRead", "read teldb classlist size: "
					+ cursor.getCount());
			if (cursor.moveToFirst()) {
				do {
					String name = cursor
							.getString(cursor
									.getColumnIndex("name"));
					//idxΪclasslist���е绰��ID������idxֵ����ָ��ҳ�����ת
					int idx = cursor.getInt(cursor
							.getColumnIndex("idx"));
					TelclassInfo classListInfo = new TelclassInfo(
							name, idx);
					classListInfos.add(classListInfo);
				} while (cursor.moveToNext());
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			try {
				cursor.close();
				db.close();
			} catch (Exception e2) {
				// TODO: handle exception
				throw e2;
			}
			LogUtil.d("DBRead", "read teldb classlist end [list size]: " + classListInfos.size());
		}
		
		return classListInfos;
	}

	/** ��ȡtelFile������ݿ��ļ��е�tableN������ڵ�����(�̼����ƺ͵绰����) 
	 * @throws Exception */
	public static ArrayList<TelnumberInfo> readTeldbTable(int idx) throws Exception {
		ArrayList<TelnumberInfo> numberInfos = new ArrayList<TelnumberInfo>();
		//idxΪclasslist���е绰��ID������idxֵ����ָ��ҳ�����ת
		String sql = "select * from table" + idx;
		SQLiteDatabase db = null;
		Cursor cursor  = null;
		try {
			// ��DB�ļ�
			db = SQLiteDatabase
					.openOrCreateDatabase(telFile, null);
			// ִ�в�ѯ��SQL��� select * from table +idx
			cursor = db.rawQuery(sql, null);
			LogUtil.d("DBRead", "read teldb number table size: "
					+ cursor.getCount());
			if (cursor.moveToFirst()) {
				do {
					String name = cursor
							.getString(cursor
									.getColumnIndex("name"));
					String number = cursor
							.getString(cursor
									.getColumnIndex("number"));
					TelnumberInfo numberInfo = new TelnumberInfo(
							name, number);
					numberInfos.add(numberInfo);
				} while (cursor.moveToNext());
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			try {
				cursor.close();
				db.close();
			} catch (Exception e2) {
				// TODO: handle exception
				throw e2;
			}
			LogUtil.d("DBRead",
					"read teldb number table end [list size]: "
							+ numberInfos.size());
		}
		return numberInfos;
	}
}
