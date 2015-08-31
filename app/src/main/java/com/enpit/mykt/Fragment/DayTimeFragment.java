package com.enpit.mykt.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.enpit.mykt.Activity.MainActivity;
import com.enpit.mykt.Activity.ScheduleActivity;
import com.enpit.mykt.Activity.ScheduleListActivity;
import com.enpit.mykt.Global.Global;
import com.enpit.mykt.Global.TimeSheet;
import com.enpit.mykt.Model.TimeSet;
import com.enpit.mykt.R;

import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public class DayTimeFragment extends Fragment implements AbsListView.OnItemClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * The fragment's ListView/GridView.
     */
    private AbsListView mListView;

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
//    private ListAdapter mAdapter;

    // TODO: Rename and change types of parameters
    public static DayTimeFragment newInstance(String param1, String param2) {
        DayTimeFragment fragment = new DayTimeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public DayTimeFragment() {
    }
    MyListAdapter myAdapter = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        // TODO: Change Adapter to display your content
//        mAdapter = new ArrayAdapter<DummyContent.DummyItem>(getActivity(),
//                android.R.layout.simple_list_item_1, android.R.id.text1, DummyContent.ITEMS);
        myAdapter = new MyListAdapter(getActivity(), R.layout.fragment_day_time_list);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_day_time, container, false);

        // Set the adapter
        mListView = (AbsListView) view.findViewById(android.R.id.list);
        ((AdapterView<ListAdapter>) mListView).setAdapter(myAdapter);

        // Set OnItemClickListener so we can be notified on item clicks
        mListView.setOnItemClickListener(this);

        return view;
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (null != mListener) {
            Global.getGlobal().setSelectedTime(position);

            // Notify the active callbacks interface (the activity, if the
            // fragment is attached to one) that an item has been selected.
            startActivity(new Intent(getActivity(), ScheduleListActivity.class));
        }
    }

    /**
     * The default content for this Fragment has a TextView that is shown when
     * the list is empty. If you would like to change the text, call this method
     * to supply the text it should use.
     */
    public void setEmptyText(CharSequence emptyText) {
        View emptyView = mListView.getEmptyView();

        if (emptyView instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
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
        public void onFragmentInteraction(String id);
    }
    public class MyListAdapter extends ArrayAdapter<Object> {

        int mTextViewResourceID = 0;
        private Context mContext;
        List<TimeSet> scheduleList = TimeSheet.getTimeSheet();
        int size = scheduleList.size()/2;

        String[] timeListStr = new String[size];

        public MyListAdapter(Context context, int textViewResourceId) {
            super(context, textViewResourceId);
            mTextViewResourceID = textViewResourceId;
            mContext = context;
            for (int i = 0; i < size; i++) {
                timeListStr[i] = scheduleList.get(i*2).getH() + ":" + scheduleList.get(i*2).getM();

            }
        }


        private int[] colors = new int[]{0x00000000, 0x00000000};

        public int getCount() {
            return timeListStr.length;
        }

        @Override
        public boolean areAllItemsEnabled() {
            return false;
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {


            TextView time;


//            if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(
                    mTextViewResourceID, null);
            time = (TextView)convertView. findViewById(R.id.listDayTime);
//            }
            int colorPos = position % colors.length;
            convertView.setBackgroundColor(colors[colorPos]);

            time.setText(timeListStr[position]);
            return convertView;
        }
    }
}
