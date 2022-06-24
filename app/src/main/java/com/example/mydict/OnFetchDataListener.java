package com.example.mydict;

import com.example.mydict.Models.APIResponse;

public interface OnFetchDataListener {
    void onFetchData(APIResponse apiResponse,String message);
    void onError(String message);
}
