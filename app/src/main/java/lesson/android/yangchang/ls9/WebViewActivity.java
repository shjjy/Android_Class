package lesson.android.yangchang.ls9;

import android.app.ProgressDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import lesson.android.yangchang.demo.R;

public class WebViewActivity extends ActionBarActivity {
    private Button btnGo, btnGo2, btnGo3;
    private WebView webView;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        progressDialog = new ProgressDialog(this);
        btnGo = (Button) findViewById(R.id.webViewBtn);
        btnGo.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView = (WebView) findViewById(R.id.webView);
                webView.loadUrl("https://www.google.com.tw");
            }
        });
        btnGo2 = (Button) findViewById(R.id.webViewBtn2);
        btnGo2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView = (WebView) findViewById(R.id.webView);
                webView.setWebViewClient(new MyWebView());
                progressDialog.setMessage("loading...");
                progressDialog.show();
                webView.loadUrl("https://www.google.com.tw");
            }
        });
        btnGo3 = (Button) findViewById(R.id.webViewBtn3);
        btnGo3.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView = (WebView) findViewById(R.id.webView);
                String htmlStr = "<html><head></head><body><table>" +
                        "<tr><td>1</td><td>2</td></tr>" +
                        "<tr><td>3</td><td>4</td></tr>" +
                        "</table></body></html>";
                webView.loadData(htmlStr, "text/html", null);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_web_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class MyWebView extends WebViewClient {

        public MyWebView() {

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            if(progressDialog.isShowing())progressDialog.dismiss();
            super.onPageFinished(view, url);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//            return super.shouldOverrideUrlLoading(view, url);
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            Toast.makeText(view.getContext(), "load fail", Toast.LENGTH_LONG).show();
            super.onReceivedError(view, errorCode, description, failingUrl);
        }
    }
}
