package com.example.maimanhduy.rbook.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.maimanhduy.rbook.R;

;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SettingFragment.OnCallBackFormSettingFragment} interface
 * to handle interaction events.
 * Use the {@link SettingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private Spinner spinner;
    private ImageView mImgCloseSetting;
    private TextView mTvMarginMinus;
    private TextView mTvMarginPlus;
    private TextView mTvFontSizeMinus;
    private TextView mTvFontSizePlus;
    private Button mBtnLightMode;
    private ImageButton mBtnNormalMode;
    private ImageView mImgAddFavorite;
    private TextView mTvTextSize;
    private TextView mTvTextMargin;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String[] arr = {"Roboto", "ArimalMadurai", "BalooChettan", "Lemonada", "OpenSans"};
    private boolean check;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnCallBackFormSettingFragment mListener;

    public SettingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingFragment newInstance(String param1, String param2) {
        SettingFragment fragment = new SettingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        Log.d("onCreate", "create");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_center_item, arr);
        spinner = (Spinner) view.findViewById(R.id.spinnerSetting);
        spinner.setAdapter(adapter);
        mImgCloseSetting = (ImageView) view.findViewById(R.id.imgCloseFragmentSetting);
        mTvFontSizeMinus = (TextView) view.findViewById(R.id.tvSettingFontSizeMinus);
        mTvFontSizePlus = (TextView) view.findViewById(R.id.tvSettingFontSizePlus);
        mTvMarginMinus = (TextView) view.findViewById(R.id.tvMarginMinus);
        mTvMarginPlus = (TextView) view.findViewById(R.id.tvMarginPlus);
        mBtnLightMode = (Button) view.findViewById(R.id.btnNightMode);
        mBtnNormalMode = (ImageButton) view.findViewById(R.id.btnNormalMode);
        mImgAddFavorite = (ImageView)view.findViewById(R.id.imgFavoriteFragmentSetting);
        mTvTextSize = (TextView) view.findViewById(R.id.tvTextSize);
        mTvTextSize.setText("15.0");
        mTvTextMargin = (TextView)view.findViewById(R.id.tvMarginSize);
        mTvTextMargin.setText("0.0");
        mBtnLightMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onNightMode();
            }
        });
        mBtnNormalMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onNormalMode();
            }
        });
        mTvFontSizeMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onFontSizeMinus();
            }
        });
        mTvFontSizePlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onFontSizePlus();
            }
        });
        mTvMarginMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onMarginMinus();
            }
        });
        mTvMarginPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onMarginPlus();
            }
        });
        mImgCloseSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onCloseFragmentSetting();
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mListener.onChangeFont(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        Log.d("cay","1");
        mImgAddFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               mListener.addFavoriteBook();
                mImgAddFavorite.setVisibility(View.INVISIBLE);
            }
        });
        return view;
    }

    public void setTextSizeS(String i) {
        mTvTextSize.setText(i);
    }

    public void setTextMarginS(String i) {
        mTvTextMargin.setText(i);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnCallBackFormSettingFragment) {
            mListener = (OnCallBackFormSettingFragment) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnCallBackFormSettingFragment");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnCallBackFormSettingFragment {
        // TODO: Update argument type and name
        void onCloseFragmentSetting();

        void onFontSizePlus();

        void onFontSizeMinus();

        void onMarginPlus();

        void onMarginMinus();

        void onNightMode();

        void onNormalMode();

        void onChangeFont(int i);
        void addFavoriteBook();
    }
}
