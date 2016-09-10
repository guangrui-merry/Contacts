package edu.feicui.contacts.main;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
import edu.feicui.contacts.R;
import edu.feicui.contacts.adapter.TelclassAdapter;
import edu.feicui.contacts.db.AssetsDBManager;
import edu.feicui.contacts.db.DBRead;
import edu.feicui.contacts.entity.TelclassInfo;
import edu.feicui.contacts.utils.ToastUtil;

/** ͨѶ��ȫҳ�� */
public class TelmsgActivity extends Activity implements OnItemClickListener {
	private ListView listView;
	private TelclassAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_telmsg);
		// ��ʼ�ؼ�
		listView = (ListView) findViewById(R.id.listview);
		adapter = new TelclassAdapter(this);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		// ��������
		initAppDBFile();
		adapter.clearDataTOAdapter();
		adapter.addDataToAdapter(new TelclassInfo("���ص绰", 0));// ���ص绰 ����
		try {
			adapter.addDataToAdapter(DBRead.readTeldbClasslist());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// db���ڵĵ绰����
		adapter.notifyDataSetChanged();
	}

	private void initAppDBFile() {
		// ����Ƿ����ͨѶ��ȫDB�ļ�
		if (!DBRead.isExistsTeldbFile()) {
			try {
				// ��������Ŀ�е�Assets/db/commonnum.db�ļ�����д���� DBRead.telFile�ļ���
				AssetsDBManager.copyAssetsFileToFile(getApplicationContext(),
						"db/commonnum.db", DBRead.telFile);
			} catch (IOException e) {
				ToastUtil.show(this, "��ʼͨѶ��ȫ���ݿ��ļ��쳣", Toast.LENGTH_SHORT);
			}
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// ����ͨ��
		if (position == 0) {
			Intent intent = new Intent();
			intent.setAction(Intent.ACTION_DIAL);
			startActivity(intent);
			return;
		}
		// ȡ����ǰѡ���ѡ��ʵ������
		TelclassInfo classInfo = adapter.getItem(position);
		// ��ת���绰���ҳ��,�Ҵ���idx
		Intent intent = new Intent(this, TellistActivity.class);
		intent.putExtra("idx", classInfo.idx);
		startActivity(intent);
	}

	private long preTime = 0;
	private long curTime = 0;

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			curTime = System.currentTimeMillis();
			if (curTime - preTime <= 800) {
				finish();
			}
			ToastUtil.show(getApplicationContext(), "�ٽ�һ���˳�",
					Toast.LENGTH_SHORT);
			preTime = curTime;
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
