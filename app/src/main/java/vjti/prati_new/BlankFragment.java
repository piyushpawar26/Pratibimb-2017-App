package vjti.prati_new;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
public class BlankFragment extends Fragment {

    private View fragment;
    private WebView mWebView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragment = inflater.inflate(R.layout.fragment_blank, container, false);
        mWebView = (WebView) fragment.findViewById(R.id.webview);
        mWebView.loadUrl("https://docs.google.com/forms/d/e/1FAIpQLSdenrK2MSZw0iMrpZP0BTdtddlQ4O7Zn470DVqFRE1d4W_DGQ/viewform?c=0&w=1");

        // Enable Javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Force links and redirects to open in the WebView instead of in a browser
        mWebView.setWebViewClient(new WebViewClient());
        return fragment;
    }

}
