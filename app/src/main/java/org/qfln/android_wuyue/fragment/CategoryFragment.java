package org.qfln.android_wuyue.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;

import org.qfln.android_wuyue.R;
import org.qfln.android_wuyue.activity.GridxqActivity;
import org.qfln.android_wuyue.adapter.CategoryAdapter1;
import org.qfln.android_wuyue.adapter.CategoryAdapter2;
import org.qfln.android_wuyue.base.BaseFragment;
import org.qfln.android_wuyue.bean.CategoryEntity;
import org.qfln.android_wuyue.custom.CustomProgressDialog;
import org.qfln.android_wuyue.util.Constant;
import org.qfln.android_wuyue.util.VolleyUtil;

import java.util.List;

/**
 * @描述：
 * @创建人： LN
 * @创建时间: 2016/3/27 14:09
 * @备注：
 */
public class CategoryFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    private TextView tvGridWz;
    private ListView mlv1;
    private GridView gv2;
    private ImageView ivCate_search;
    private List<CategoryEntity.DataEntity.CategoriesEntity> categories;
    private List<CategoryEntity.DataEntity.CategoriesEntity.SubcategoriesEntity> subcategories;
    private CategoryEntity.DataEntity data;
    private CustomProgressDialog progressDialog;
    private LinearLayout ll_category;

    @Override
    protected int getLayoutResId() {
        return R.layout.category_layout;
    }

    public static CategoryFragment newInstance() {
        CategoryFragment categoryFragment = new CategoryFragment();
        return categoryFragment;
    }

    @Override
    protected void init(View view) {
        //在网络请求之前显示；
        progressDialog = new CustomProgressDialog(getActivity(),"正在加载中...", R.drawable.donghua_frame);
        progressDialog.show();

        ivCate_search = (ImageView)view.findViewById(R.id.iv_cate_search);
        tvGridWz = (TextView) view.findViewById(R.id.tv_gridwz);
        mlv1 = (ListView) view.findViewById(R.id.lv1);
        gv2 = (GridView) view.findViewById(R.id.gv2);
        ll_category = (LinearLayout) view.findViewById(R.id.ll_category_iv);

        mlv1.setOnItemClickListener(this);
        //gridView 的点击事件
        gv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int id1 = subcategories.get(position).getId();
                String name1 = subcategories.get(position).getName();
                Intent intent=new Intent(getActivity(),GridxqActivity.class);
                intent.putExtra("id1",id1);
                intent.putExtra("name1",name1);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void loadData() {
        String category_url = Constant.URL.CATEGORY_URL;
        VolleyUtil.requestString(category_url, new VolleyUtil.OnRequestListener() {
            @Override
            public void onResponse(String url, String response) {
                progressDialog.dismiss();
                if (response != null) {
                    ll_category.setVisibility(View.VISIBLE);
                    CategoryEntity category = new Gson().fromJson(response.toString(), CategoryEntity.class);
                    data = category.getData();
                    categories = data.getCategories();
                    CategoryAdapter1 categoryAdapter1=new CategoryAdapter1(getActivity());
                    categoryAdapter1.setDatas(categories);
                    mlv1.setAdapter(categoryAdapter1);
                    mlv1.performItemClick(mlv1.getAdapter().getView(0, null, null),
                            0, mlv1.getItemIdAtPosition(0));
                }
            }

            @Override
            public void onErrorResponse(String url, VolleyError error) {
                Toast.makeText(getActivity(), "数据加载失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * mlv1的点击事件
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mlv1.smoothScrollToPositionFromTop(position, 0);
        subcategories = categories.get(position).getSubcategories();
        String name = categories.get(position).getName();
        tvGridWz.setText(name);
        CategoryAdapter2 gvAdapter=new CategoryAdapter2(getActivity());
        gvAdapter.setDatas(subcategories);
        gv2.setAdapter(gvAdapter);
    }
}
