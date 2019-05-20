//package se.su.dsv.pvt.helloworldapp.fragment;
//
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.webkit.WebSettings;
//import android.webkit.WebView;
//
//import se.su.dsv.pvt.helloworldapp.R;
//
//public class ReportWebPageFragment extends Fragment {
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_reportwebpage, container, false);
//
//        WebView reportGymWebView = view.findViewById(R.id.reportWebview);
//        reportGymWebView.loadUrl("https://etjanster.stockholm.se/tycktill/?systemId=synpunktsportalen");
//
//        WebSettings webSettings = reportGymWebView.getSettings();
//        webSettings.setJavaScriptEnabled(true);
//
//        return view;
//    }
//}

// !!!!!!!!!! Denna klass används om webbsidan ska visas som fragment !!!!!!!!!!