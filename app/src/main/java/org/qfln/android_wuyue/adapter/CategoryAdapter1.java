package org.qfln.android_wuyue.adapter;

import android.content.Context;

import org.qfln.android_wuyue.R;
import org.qfln.android_wuyue.bean.CategoryEntity;

/**
 * @描述：
 * @创建人： LN
 * @创建时间: 2016/4/3 15:22
 * @备注：
 */
public class CategoryAdapter1 extends AbsBaseAdapter<CategoryEntity.DataEntity.CategoriesEntity>{
    public CategoryAdapter1(Context context) {
        super(context, R.layout.category_listitem1);
    }

    @Override
    protected void bindData(ViewHolder viewHolder, CategoryEntity.DataEntity.CategoriesEntity categoriesEntity, int position) {
        viewHolder.bindTextView(R.id.tv_cate_item1,categoriesEntity.getName());

    }
}
