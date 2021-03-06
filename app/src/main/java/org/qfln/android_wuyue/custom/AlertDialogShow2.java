package org.qfln.android_wuyue.custom;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import org.qfln.android_wuyue.R;
import org.qfln.android_wuyue.adapter.CollGiftAdapter;
import org.qfln.android_wuyue.util.DBUtil;

import java.util.List;
import java.util.Map;

public class AlertDialogShow2 {
	private LayoutInflater infater;
	private Context context;
	private DBUtil mUtil;
	private String id;
	private CollGiftAdapter giftAdapter;

	public AlertDialogShow2(Context context, DBUtil mUtil, String id,CollGiftAdapter giftAdapter  ) {

		this.context = context;
		this.mUtil = mUtil;
		this.id = id;
		this.giftAdapter=giftAdapter;
	}

	public void show() {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		infater = LayoutInflater.from(context);

		// 自定义的标题
		View customTitleView = infater.inflate(R.layout.dialogtitle_layout, null);
		TextView tvTitle = (TextView) customTitleView.findViewById(R.id.tv_name);
		tvTitle.setText("删除收藏");
		// 设置自定义标题
		builder.setCustomTitle(customTitleView);
		View contentView =infater.inflate(R.layout.dialog_content, null);
		// 设置自定义的布局
		builder.setView(contentView);
		builder.setPositiveButton("确定", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				String sql = "delete from gift_like where id=" + id;
				mUtil.execSQL(sql, null);
				String sql1 = "select * from gift_like";
				List<Map<String,Object>> sqlList = mUtil.queryList(sql1, null);
				giftAdapter.setDatas(sqlList);

			}
		});
		builder.setNegativeButton("取消", null);
		builder.show();
	}
}
