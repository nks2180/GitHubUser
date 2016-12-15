package com.app.gitevent.retrofit;

import com.app.gitevent.utils.GitUtils;

import java.lang.ref.WeakReference;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by niranjan on 03/11/16.
 */
public class ApiController<T> {

    private final ApiService apiService;

    public ApiController(ApiService apiService) {
        this.apiService = apiService;
    }

    public void hitApi(final RequestBuilder requestBuilder, final ApiDataReceiveCallback callback) {
        Call<String> call = getApiFromApiType(requestBuilder.apiType,requestBuilder.getExtraParameters());
        if(call != null) {
            call.enqueue(new ApiResponseCallBack(callback,requestBuilder.apiType));
        }
    }

    private Call<String> getApiFromApiType(int apiType, Map<String, String> extraParams) {
        String userName = "";
        switch (apiType) {
            case NetworkConstants.API_FETCH_GIT_EVENTS:

                if (extraParams.containsKey(GitUtils.STR_USER_NAME))
                {
                    userName = extraParams.get(GitUtils.STR_USER_NAME);
                    extraParams.remove(GitUtils.STR_USER_NAME);
                }
                return apiService.fetchGitEvents(userName, extraParams);
            case NetworkConstants.API_VALIDATE_USER:
                if (extraParams.containsKey(GitUtils.STR_USER_NAME))
                {
                    userName = extraParams.get(GitUtils.STR_USER_NAME);
                    extraParams.remove(GitUtils.STR_USER_NAME);
                }
                return apiService.validateUser(userName, extraParams);
        }
    return null;
    }

    private static class ApiResponseCallBack implements Callback<String> {

        private final WeakReference<ApiDataReceiveCallback> apiDataReceiveCallbackWeakReference;
        private int type;

        ApiResponseCallBack(ApiDataReceiveCallback apiDataReceiveCallback, int type) {
            this.apiDataReceiveCallbackWeakReference = new WeakReference<>(apiDataReceiveCallback);
            this.type = type;
        }

        @Override
        public void onResponse(Call<String> call, Response<String> response) {
            try {
                if(response.isSuccessful()) {
                    handleApiResponse(response.body());
                }
                else
                    handleApiResponse(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void handleApiResponse(final String response) throws Exception {
            ApiDataReceiveCallback apiDataReceiveCallback = apiDataReceiveCallbackWeakReference.get();
            if (apiDataReceiveCallback != null)
                apiDataReceiveCallback.onDataReceived(response, type);
        }

        @Override
        public void onFailure(Call call, Throwable t) {
            t.printStackTrace();
        }
    }
}
