package se.su.dsv.pvt.helloworldapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import se.su.dsv.pvt.helloworldapp.R;

public class ReportWebPageActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportwebpage);

        WebView reportGymWebView = findViewById(R.id.reportWebview);
        reportGymWebView.loadUrl("https://etjanster.stockholm.se/tycktill/?systemId=synpunktsportalen");

        WebSettings webSettings = reportGymWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }
}
