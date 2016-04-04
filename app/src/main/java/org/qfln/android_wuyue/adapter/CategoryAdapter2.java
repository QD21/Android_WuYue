package org.qfln.android_wuyue.adapter;

import android.content.Context;

import org.qfln.android_wuyue.R;
import org.qfln.android_wuyue.bean.CategoryEntity;

import java.util.List;

/**
 * @描述：
 * @创建人： LN
 * @创建时间: 2016/4/1 23:58
 * @备注：
 */
public class CategoryAdapter2 extends AbsBaseAdapter<CategoryEntity.DataEntity.CategoriesEntity.SubcategoriesEntity> {
    private List<CategoryEntity.DataEntity.CategoriesEntity.SubcategoriesEntity> gvlist;


    public CategoryAdapter2(Context context) {
        super(context, R.layout.category_griditem);
    }


    @Override
    protected void bindData(ViewHolder viewHolder, CategoryEntity.DataEntity.CategoriesEntity.SubcategoriesEntity subcategoriesEntity, int position) {
        viewHolder.bindSimpleImg(R.id.sdv_grid_item,subcategoriesEntity.getIcon_url())
                .bindTextView(R.id.tv_grid_item,subcategoriesEntity.getName());
    }
}
