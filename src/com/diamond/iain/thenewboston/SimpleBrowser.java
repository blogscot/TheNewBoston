package com.diamond.iain.thenewboston;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

import com.diamond.iain.thenewboston.R.id;

public class SimpleBrowser extends Activity implements OnClickListener {

	EditText url;
	WebView ourBrowser;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);		
		setContentView(R.layout.simplebrowser);
		
		ourBrowser = (WebView) findViewById(R.id.wvBrowser);
		ourBrowser.getSettings().setJavaScriptEnabled(true);
		ourBrowser.loadUrl("http://www.google.co.uk");
		ourBrowser.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url){
				return super.shouldOverrideUrlLoading(view, url);
			}
		});
		
		Button go = (Button) findViewById(id.bGo);
		Button back = (Button) findViewById(id.bBack);
		Button refresh = (Button) findViewById(id.bRefresh);
		Button forward = (Button) findViewById(id.bForward);
		Button clearHistory = (Button) findViewById(id.bHistory);
		url = (EditText) findViewById(id.etURL);
		go.setOnClickListener(this);
		back.setOnClickListener(this);
		refresh.setOnClickListener(this);
		forward.setOnClickListener(this);
		clearHistory.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case id.bGo:
			String website = url.getText().toString();
			ourBrowser.loadUrl(website);
			InputMethodManager manager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
			manager.hideSoftInputFromWindow(url.getWindowToken(), 0);
			break;
		case id.bBack:
			if(ourBrowser.canGoBack()) ourBrowser.goBack();
			break;
		case id.bRefresh:
			ourBrowser.reload();
			break;
		case id.bForward:
			if(ourBrowser.canGoForward()) ourBrowser.goForward();
			break;
		case id.bHistory:
			ourBrowser.clearHistory();
			break;
		}
	}
}
