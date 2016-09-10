package edu.feicui.contacts.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import edu.feicui.contacts.R;
import edu.feicui.contacts.entity.TelnumberInfo;

public class TellistAdapter extends BaseAdapter {
	private LayoutInflater layoutInflater;
	// ��ǰ�������ڵ����ݼ��� (��ǰ���������乤��ֻ�ϴ˼���)
	private ArrayList<TelnumberInfo> adapterDatas = new ArrayList<TelnumberInfo>();

	public TellistAdapter(Context context) {
		layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	// ������ݵ���ǰ����������
	public void addDataToAdapter(TelnumberInfo e) {
		if (e != null) {
			adapterDatas.add(e);
		}
	}

	// ������ݵ���ǰ����������
	public void addDataToAdapter(List<TelnumberInfo> e) {
		if (e != null) {
			adapterDatas.addAll(e);
		}
	}

	// ������ݵ���ǰ����������
	public void replaceDataToAdapter(List<TelnumberInfo> e) {
		if (e != null) {
			adapterDatas.clear();
			adapterDatas.addAll(e);
		}
	}

	public ArrayList<TelnumberInfo> getDataFromAdapter() {
		return adapterDatas;
	}

	@Override
	public int getCount() {
		return adapterDatas.size();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = layoutInflater.inflate(
					R.layout.inflate_tellist_listitem, null);
		}
		TextView tv_name = (TextView) convertView.findViewById(R.id.tv_name);
		TextView tv_number = (TextView) convertView
				.findViewById(R.id.tv_number);
		tv_name.setText(getItem(position).name);
		tv_number.setText(getItem(position).number + "");
		System.out.println(getItem(position).name+getItem(position).number);
		return convertView;
	}

	@Override
	public TelnumberInfo getItem(int position) {
		return adapterDatas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
}
