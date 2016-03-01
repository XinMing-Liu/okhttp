package itcast.cn.okhttp117;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "itcast117_MainActivity";
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static final String json = "{\n" +
            "    \"name\": \"hello.android6.0\"\n" +
            "}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestServerData();
    }

    /**
     * 异步访问服务端数据
     */
    private void requestServerData() {
        new Thread() {
            @Override
            public void run() {
                super.run();
//                get();
//                post();
                postParam();
            }
        }.start();
    }

    /**
     * 使用OKHTTP 框架的post 的方式提交一些键值对的参数给服务端
     */
    private void postParam() {
        String url = "http://192.168.0.101:8080/TestProject/ParamServlet";
//        1、新建OkHttpClient 客户端对象
        OkHttpClient okHttpClient = new OkHttpClient();
//        2、新建Request 请求对象
        RequestBody requestBody = new FormEncodingBuilder()
                .add("platform", "Android")
                .add("version", "24")
                .add("SDK", "24")
                .build();
        Request request = new Request.Builder().url(url).post(requestBody).build();
//        3、准备发送请求
        Call call = okHttpClient.newCall(request);
//        4、真实地去发送请求，并拿到服务端返回的数据
        try {
            Response response = call.execute();
            if (response.isSuccessful()) {
                //        5、输出服务端返回的数据
                Log.d(TAG, "postParam: " + response.body().string());
            } else {
                Log.d(TAG, "postParam: fail");
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "postParam: error" + e.getMessage());
        }
    }

    /**
     * 使用OKHTttp 框架进行Post 请求服务数据
     */
    private void post() {
        String url = "http://192.168.0.101:8080/TestProject/JsonServlet";
//        1、新建OkHttpClient 客户端对象
        OkHttpClient okHttpClient = new OkHttpClient();
//        2、新建Request 请求对象
        RequestBody requestBody = RequestBody.create(JSON, json);
        Request request = new Request.Builder().url(url).post(requestBody).build();
//        3、准备发送请求
        Call call = okHttpClient.newCall(request);
//        4、真实地去发送请求，并拿到服务端返回的数据
        try {
            Response response = call.execute();
            if (response.isSuccessful()) {
                //        5、输出服务端返回的数据
                Log.d(TAG, "post: " + response.body().string());
            } else {
                Log.d(TAG, "post: fail");
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "post: error" + e.getMessage());
        }


    }

    /**
     * 使用OKHTTP 框架进行get请求服务端数据
     */
    private void get() {
        String url = "http://192.168.0.101:8080/TestProject/JsonServlet";
//        1、新建一个OKHTTPClient 客户端对象
        OkHttpClient okHttpClient = new OkHttpClient();
//        2、新建一个Request 请求对象
        Request request = new Request.Builder().url(url).get().build();
//        3、准备发送一个请求
        Call call = okHttpClient.newCall(request);
//        4、真实的去发送请求，并拿到服务端返回的数据
        try {
            Response response = call.execute();//去访问服务端，拿到服务端返回的response 对象。
            if (response.isSuccessful()) {//成功
                //        5、输出服务端返回的数据
                Log.d(TAG, "get: " + response.body().string());//输出服务端返回数据
            } else {
                Log.d(TAG, "get: fail");//失败
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "get: error:" + e.getMessage());//失败
        }

    }
}
