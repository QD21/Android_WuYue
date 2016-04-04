package org.qfln.android_wuyue.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;

import org.qfln.android_wuyue.R;
import org.qfln.android_wuyue.activity.GridxqActivity;
import org.qfln.android_wuyue.adapter.CategoryAdapter1;
import org.qfln.android_wuyue.adapter.CategoryAdapter2;
import org.qfln.android_wuyue.base.BaseFragment;
import org.qfln.android_wuyue.bean.CategoryEntity;
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

    private ListView mlv1;
    private GridView gv2;
    private List<CategoryEntity.DataEntity.CategoriesEntity> categories;
    private List<CategoryEntity.DataEntity.CategoriesEntity.SubcategoriesEntity> subcategories;

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
        mlv1 = (ListView) view.findViewById(R.id.lv1);
        gv2 = (GridView) view.findViewById(R.id.gv2);

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
                if (response != null) {
                    CategoryEntity category = new Gson().fromJson(response.toString(), CategoryEntity.class);
                    CategoryEntity.DataEntity data = category.getData();
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
        CategoryAdapter2 gvAdapter=new CategoryAdapter2(getActivity());
        gvAdapter.setDatas(subcategories);
        gv2.setAdapter(gvAdapter);
    }
}
