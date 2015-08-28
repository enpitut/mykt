package com.enpit.mykt.Fragment;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.enpit.mykt.Global.RecyclerAdapter;
import com.enpit.mykt.R;

import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ScheduleToolsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ScheduleToolsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScheduleToolsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ScheduleToolsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ScheduleToolsFragment newInstance(String param1, String param2) {
        ScheduleToolsFragment fragment = new ScheduleToolsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ScheduleToolsFragment() {
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

    private RecyclerView mRecyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_schedule_tools, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceStaten){

        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),2);
        //LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
       // layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        mRecyclerView.setLayoutManager(layoutManager);

        datasets[] dataset = new datasets[10];
        for(int i = 0; i < dataset.length; i++){
            dataset[i] = new datasets("test" + i, Color.rgb((int) (255*Math.random()), (int) (255*Math.random()),(int) (255*Math.random())));
        }

        RecyclerAdapter mAdapter = new RecyclerAdapter(dataset, getActivity());
        mRecyclerView.setAdapter(mAdapter);
        super.onViewCreated(view, savedInstanceStaten);

    }

    public static class datasets{
        public String item_name;
        public int color;

        public datasets(String item_name, int color){
            this.item_name = item_name;
            this.color = color;
        }

    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
