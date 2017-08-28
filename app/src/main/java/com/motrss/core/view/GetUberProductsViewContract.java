package com.motrss.core.view;

import com.motrss.core.retrofit.response.Products;

import java.util.ArrayList;

/**
 * @Author Dhananjay Kulkarni.
 */

public interface GetUberProductsViewContract extends BaseContract{

    void onUberProductsSuccess(ArrayList<Products> customResponse);

    void onUberProductsFailure(String cause);
}
