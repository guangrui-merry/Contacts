package edu.feicui.contacts.main;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import edu.feicui.contacts.R;
import edu.feicui.contacts.adapter.TellistAdapter;
import edu.feicui.contacts.db.DBRead;
import edu.feicui.contacts.utils.LogUtil;

/** ͨѶ��ȫ�绰�������ҳ�� */
public class TellistActivity extends Activity implements OnItemClickListener{
	private ListView listView;
	private TellistAdapter adapter;
	private int idx = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tellist);
		// ��ȡ���������ж�����ʾ��һ�ַ���ĵ绰�����б�
		idx = getIntent().getIntExtra("idx", -1);
		// ��ʼ�ؼ�
		listView = (ListView) findViewById(R.id.listview);
		adapter = new TellistAdapter(this);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		// �������
		try {
			adapter.addDataToAdapter(DBRead.readTeldbTable(idx));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		adapter.notifyDataSetChanged();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		String name = adapter.getItem(position).name;
		String number = adapter.getItem(position).number;
		showCallDialog(name, number);
	}

	private void showCallDialog(final String name, final String number) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("����");
		builder.setMessage("�Ƿ�ʼ����" + name + "�绰 ? \n\nTEL��" + number);
		builder.setNegativeButton("ȡ��", null);
		builder.setPositiveButton("����", new DialogInterface.OnClickListener(){
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// �绰����
				Intent intent = new Intent(Intent.ACTION_CALL);
				intent.setData(Uri.parse("tel://" + number));
				startActivity(intent);
			}
		});
		builder.show();
	}
}
