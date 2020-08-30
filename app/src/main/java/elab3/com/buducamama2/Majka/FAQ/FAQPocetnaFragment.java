package elab3.com.buducamama2.Majka.FAQ;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import elab3.com.buducamama2.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FAQPocetnaFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FAQPocetnaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FAQPocetnaFragment extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private OnFragmentInteractionListener mListener;
    ActivityFAQ activityFAQ;


    public FAQPocetnaFragment() {
        // Required empty public constructor
    }

    Button btnIshrana;
    Button btnPorodjaj;
    Button btnNavike;
    Button btnProblemi;

    public static FAQPocetnaFragment newInstance(String param1, String param2) {
        FAQPocetnaFragment fragment = new FAQPocetnaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_faqpocetna, container, false);
        btnIshrana=view.findViewById(R.id.btnFAQIshrana);
        btnPorodjaj=view.findViewById(R.id.btnFAQPorođaj);
        btnNavike=view.findViewById(R.id.btnFAQNavike);
        btnProblemi=view.findViewById(R.id.btnFAQProblemi);

        btnIshrana.setOnClickListener(this);
        btnProblemi.setOnClickListener(this);
        btnNavike.setOnClickListener(this);
        btnPorodjaj.setOnClickListener(this);

        activityFAQ=(ActivityFAQ) getActivity();

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnFAQIshrana:
                activityFAQ.prebaciFragment("Ishrana");
                break;
            case R.id.btnFAQNavike:

                activityFAQ.prebaciFragment("Navike");
                break;
            case R.id.btnFAQPorođaj:

                activityFAQ.prebaciFragment("Porodjaj");
                break;
            case R.id.btnFAQProblemi:

                activityFAQ.prebaciFragment("Problemi");
                break;

        }

    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
