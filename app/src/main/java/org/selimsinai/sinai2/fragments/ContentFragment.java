package org.selimsinai.sinai2.fragments;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebViewClient;
import android.webkit.WebViewFragment;

import org.selimsinai.sinai2.R;
import org.selimsinai.sinai2.Util;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ContentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContentFragment extends WebViewFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */

    private static ContentFragment contentFragment;
    // TODO: Rename and change types and number of parameters
    public static ContentFragment getInstance() {
        if(contentFragment == null){
             contentFragment = new ContentFragment();
            Bundle args = new Bundle();
           // args.putString(ARG_PARAM1, param1);
          //  args.putString(ARG_PARAM2, param2);
            contentFragment.setArguments(args);
        }

        return contentFragment;
    }

    public ContentFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View result = super.onCreateView(inflater, container, savedInstanceState);
        if(Util.isConnected(getActivity())){
            getWebView().getSettings().setJavaScriptEnabled(true);
            getWebView().getSettings().setSupportZoom(true);
            getWebView().getSettings().setBuiltInZoomControls(true);
            getWebView().setWebViewClient(new WebViewClient());
            getWebView().loadUrl("http://en.wikipedia.org/wiki/Sinai_Peninsula");

        }

        return result;
    }


}
