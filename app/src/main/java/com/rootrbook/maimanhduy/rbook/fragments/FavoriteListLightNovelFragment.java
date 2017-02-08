package com.rootrbook.maimanhduy.rbook.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rootrbook.maimanhduy.rbook.R;
import com.rootrbook.maimanhduy.rbook.adapter.ListFavoriteAdapter;
import com.rootrbook.maimanhduy.rbook.database.DatabaseHanderHelper;
import com.rootrbook.maimanhduy.rbook.model.BookInFireBase;

import java.util.ArrayList;

;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FavoriteListLightNovelFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FavoriteListLightNovelFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavoriteListLightNovelFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView recyclerViewFavoriteLightNovel;
    private ArrayList<BookInFireBase> arrListLightNovel = new ArrayList<>();
    private ArrayList<BookInFireBase> arrListComic = new ArrayList<>();
    private ArrayList<BookInFireBase> arrListOther = new ArrayList<>();
    private ListFavoriteAdapter adapter;
    private DatabaseHanderHelper db;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FavoriteListLightNovelFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FavoriteListLightNovelFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FavoriteListLightNovelFragment newInstance(String param1, String param2) {
        FavoriteListLightNovelFragment fragment = new FavoriteListLightNovelFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite_list_light_novel, container, false);
        db = new DatabaseHanderHelper(getActivity());
        ArrayList<BookInFireBase> listGenerel = db.getAllFavoriteBook();
        for (int i = 0; i < listGenerel.size(); i++) {
            if (getString(R.string.lightnovel).equals(listGenerel.get(i).getBookCategory())) {
                arrListLightNovel.add(listGenerel.get(i));
            }
            if (getString(R.string.comic).equals(listGenerel.get(i).getBookCategory())) {
                arrListComic.add(listGenerel.get(i));
            }
            if (getString(R.string.other).equals(listGenerel.get(i).getBookCategory())) {
                arrListOther.add(listGenerel.get(i));
            }
        }
        recyclerViewFavoriteLightNovel = (RecyclerView) view.findViewById(R.id.recycerViewFavoriteLightNovel);
        if (mParam1.equals("0")) {
            adapter = new ListFavoriteAdapter(arrListLightNovel, getActivity());
        }
        if (mParam1.equals("1")) {
            adapter = new ListFavoriteAdapter(arrListComic, getActivity());
        }
        if (mParam1.equals("2")) {
            adapter = new ListFavoriteAdapter(arrListOther, getActivity());
        }
        LinearLayoutManager lln = new LinearLayoutManager(getActivity());
        recyclerViewFavoriteLightNovel.setHasFixedSize(true);
        recyclerViewFavoriteLightNovel.setLayoutManager(lln);
        recyclerViewFavoriteLightNovel.setAdapter(adapter);
        // Toast.makeText(getActivity(), mParam1, Toast.LENGTH_SHORT).show();
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
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
    public void removeBook(int pos, String category) {
        if (getString(R.string.lightnovel).equals(category)) {
            arrListLightNovel.remove(pos);
        }
        if (getString(R.string.comic).equals(category)) {
            arrListComic.remove(pos);
        }
        if (getString(R.string.other).equals(category)) {
            arrListOther.remove(pos);
        }
        adapter.notifyDataSetChanged();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}