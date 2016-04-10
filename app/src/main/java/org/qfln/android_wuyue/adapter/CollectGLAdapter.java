package org.qfln.android_wuyue.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import org.qfln.android_wuyue.R;
import org.qfln.android_wuyue.activity.WebXQActivity;
import org.qfln.android_wuyue.custom.AlertDialogShow;
import org.qfln.android_wuyue.util.DBUtil;

import java.util.List;
import java.util.Map;

/**
 * @描述：
 * @创建人： LN
 * @创建时间: 2016/4/9 00:59
 * @备注：
 */
public class CollectGLAdapter extends AbsBaseAdapter<Map<String,Object>>{
    private List<Map<String,Object>> sqlList;
    private DBUtil mUtil;
    private Map<String, Object> map;
    private SQLiteDatabase db;
    private AlertDialogShow  alertDialogShow;
    private Context context;
    private ListView lv;


    public CollectGLAdapter(final Context context, ListView lv) {
        super(context, R.layout.coll_gl_listitem);
        this.context=context;
        this.lv=lv;
        mUtil = new DBUtil(context);
        db = mUtil.getDatabase();
        Listitem();//item点击事件
    }
    @Override
    public void setDatas(List<Map<String, Object>> list) {
        super.setDatas(list);
    }

    public void Listitem() {
        String sql = "select * from GL_Like";
        sqlList = mUtil.queryList(sql, null);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, WebXQActivity.class);
                Map<String, Object> map = sqlList.get(position);
                String id1 = (String) map.get("id");
                int int_id = Integer.valueOf(id1);
                intent.putExtra("item_id",int_id);
                context.startActivity(intent);
            }
        });
    }


    @Override
    protected void bindData(final ViewHolder viewHolder, Map<String, Object> stringObjectMap, final int position) {
        viewHolder.bindSimpleImg(R.id.sdv_coll_gl,(String) stringObjectMap.get("icon"))
                .bindTextView(R.id.tv_coll_Gl, (String) stringObjectMap.get("title"));
        ImageView ivDelete = (ImageView) viewHolder.getView(R.id.iv_gl_delete);

        final String id = (String) stringObjectMap.get("id");
        ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialogShow=new AlertDialogShow(context,mUtil,id,CollectGLAdapter.this);
                alertDialogShow.show();
            }
        });
    }


}
