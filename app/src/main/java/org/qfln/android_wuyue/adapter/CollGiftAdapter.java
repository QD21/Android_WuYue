package org.qfln.android_wuyue.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import org.qfln.android_wuyue.R;
import org.qfln.android_wuyue.activity.GridActivity;
import org.qfln.android_wuyue.custom.AlertDialogShow2;
import org.qfln.android_wuyue.util.DBUtil;

import java.util.List;
import java.util.Map;

/**
 * @描述：
 * @创建人： LN
 * @创建时间: 2016/4/9 13:49
 * @备注：
 */
public class CollGiftAdapter extends AbsBaseAdapter<Map<String, Object>> {
    private DBUtil mUtil;
    private Map<String, Object> map;
    private SQLiteDatabase db;
    private AlertDialogShow2 alertDialogShow2;
    private Context context;
    private List<Map<String, Object>> sqlList;
    private GridView mgv;

    public CollGiftAdapter(final Context context, GridView gv) {
        super(context, R.layout.coll_gift_item);
        this.context = context;
        this.mgv = gv;
        mUtil = new DBUtil(context);
        db = mUtil.getDatabase();
        itemdianji();//item点击事件


    }

    @Override
    public void setDatas(List<Map<String, Object>> list) {
        super.setDatas(list);
    }
    //item点击事件
    public void itemdianji() {
        String sql = "select * from gift_like";
        sqlList = mUtil.queryList(sql, null);
        mgv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, GridActivity.class);
                Map<String, Object> m = sqlList.get(position);
                String id1 = (String) m.get("id");
                int int_id1 = Integer.valueOf(id1);
                intent.putExtra("Grid_id", int_id1);
                context.startActivity(intent);
            }
        });
    }

    @Override
    protected void bindData(ViewHolder viewHolder, Map<String, Object> stringObjectMap, int position) {
        viewHolder.bindSimpleImg(R.id.sdv_coll_gift, (String) stringObjectMap.get("icon"))
                .bindTextView(R.id.tv_coll_title, (String) stringObjectMap.get("title"))
                .bindTextView(R.id.tv_coll_price, (String) stringObjectMap.get("price"));

        ImageView iv_delete = (ImageView) viewHolder.getView(R.id.iv_gift_delete);
        final String id = (String) stringObjectMap.get("id");
        iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialogShow2 = new AlertDialogShow2(context, mUtil, id, CollGiftAdapter.this);
                alertDialogShow2.show();
            }
        });


    }
}
