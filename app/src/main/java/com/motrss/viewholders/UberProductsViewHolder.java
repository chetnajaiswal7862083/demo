package com.motrss.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.motrss.R;

/**
 * @Author Dhananjay Kulkarni.
 */

public class UberProductsViewHolder extends RecyclerView.ViewHolder {

    private ImageView ivProduct;
    private TextView tvProductName;
    private LinearLayout llContainer;

    public UberProductsViewHolder(View itemView) {
        super(itemView);
        ivProduct = (ImageView) itemView.findViewById(R.id.iv_product);
        tvProductName = (TextView) itemView.findViewById(R.id.tv_product_name);
        llContainer = (LinearLayout) itemView.findViewById(R.id.container);
    }

    public LinearLayout getLlContainer() {
        return llContainer;
    }

    public void setLlContainer(LinearLayout llContainer) {
        this.llContainer = llContainer;
    }

    public ImageView getIvProduct() {
        return ivProduct;
    }

    public void setIvProduct(ImageView ivProduct) {
        this.ivProduct = ivProduct;
    }

    public TextView getTvProductName() {
        return tvProductName;
    }

    public void setTvProductName(TextView tvProductName) {
        this.tvProductName = tvProductName;
    }
}
