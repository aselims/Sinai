package org.selimsinai.sinai2.fragments;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.selimsinai.sinai2.R;
import org.selimsinai.sinai2.Util;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.plus.PlusOneButton;

/**
 * A fragment with a Google +1 button.
 * Use the {@link PlusOneFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlusOneFragment extends DialogFragment {


    // The URL to +1.  Must be a valid URL.
    private final String PLUS_ONE_URL = "https://plus.google.com/u/0/b/104932676086370277659/104932676086370277659";

    // The request code must be 0 or greater.
    private static final int PLUS_ONE_REQUEST_CODE = 0;

    private PlusOneButton mPlusOneButton;
    private Tracker t;

    View dialogView;


    // TODO: Rename and change types and number of parameters
    public static PlusOneFragment newInstance() {
        PlusOneFragment fragment = new PlusOneFragment();
        Bundle args = new Bundle();
        /*args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);*/
        fragment.setArguments(args);
        return fragment;
    }

    public PlusOneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


         t = ((Util) getActivity().getApplication()).getTracker(Util.TrackerName.APP_TRACKER);
        t.setScreenName("PlusOne");
        t.send(new HitBuilders.AppViewBuilder().build());
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        dialogView = getActivity().getLayoutInflater().inflate(R.layout.fragment_plus_one, null);


        t.send(new HitBuilders.EventBuilder().setCategory("fragments").setAction("viewed About").build());
        //Find the +1 button
        mPlusOneButton = (PlusOneButton) dialogView.findViewById(R.id.plus_one_button);

        TextView TVmail = (TextView) dialogView.findViewById(R.id.textViewMail);
        TVmail.setMovementMethod(LinkMovementMethod.getInstance());
        TVmail.setText(Html.fromHtml("<a href=\"mailto:selim.2k@gmail.com\">Mail me!"));

        TVmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              t.send(new HitBuilders.EventBuilder().setCategory("plusOne clicks").setAction("clicked mail").build());

            }
        });

        TextView TVDistance = (TextView) dialogView.findViewById(R.id.textViewDistance);
        if(Util.isConnected(getActivity())){
            TVDistance.setText("The distance between here and Sinai is " + MapsFragment.distanceInKm + " Km");

        }


        return new AlertDialog.Builder(getActivity())
                .setView(dialogView)
                //.setIcon(R.drawable.icon)
                .setTitle(R.string.about)
                .setNeutralButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismiss();
                    }
                })
                .create();


    }




    @Override
    public void onResume() {
        super.onResume();

        // Refresh the state of the +1 button each time the activity receives focus.
        mPlusOneButton.initialize(PLUS_ONE_URL, PLUS_ONE_REQUEST_CODE);
    }


}
