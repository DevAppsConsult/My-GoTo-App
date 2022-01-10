package com.mygotoservices.mygotoandroid.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.mygotoservices.mygotoandroid.Adaptors.MultiCustomAdapter;
import com.mygotoservices.mygotoandroid.ArtisanRequestActivity;
import com.mygotoservices.mygotoandroid.CustomerCareActivity;
import com.mygotoservices.mygotoandroid.LoginActivity;
import com.mygotoservices.mygotoandroid.MyAccountActivity;
import com.mygotoservices.mygotoandroid.MyBookingsActivity;
import com.mygotoservices.mygotoandroid.MyFavouriteActivity;
import com.mygotoservices.mygotoandroid.R;
import com.mygotoservices.mygotoandroid.RealmTables.UserTable;
import com.mygotoservices.mygotoandroid.SettingsActivity;

import java.util.List;

import io.realm.Realm;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AccountFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountFragment extends Fragment {
    private RecyclerView recyclerView;
    static public MultiCustomAdapter adapter;
    public static List<Object> albumList,albumList1;
    static SwipeRefreshLayout mSwipeRefreshLayout;
    Realm realm;
    private ShimmerFrameLayout mShimmerViewContainer;
    RelativeLayout cardView, cardView0,cardView1,cardView2,cardView3,cardView4,cardView5;
    ConstraintLayout mainConstraintLogOut,mainConstraintRequestArtisan,myConstraint1,myConstraint2,myConstraint3,myConstraint0;
    TextView profileName,profileEmail;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public AccountFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AccountFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AccountFragment newInstance(String param1, String param2) {
        AccountFragment fragment = new AccountFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        profileEmail = view.findViewById(R.id.profileEmail);
        profileName = view.findViewById(R.id.profileName);
        final Realm realm = Realm.getDefaultInstance();
        UserTable userTable = realm.where(UserTable.class).findFirst();
        profileName.setText(userTable.getName());
        profileEmail.setText(userTable.getEmail());

        mainConstraintRequestArtisan = view.findViewById(R.id.mainConstraintRequestArtisan);
        myConstraint1 = view.findViewById(R.id.myConstraint1);
        myConstraint2 = view.findViewById(R.id.myConstraint2);
        myConstraint3 = view.findViewById(R.id.myConstraint3);
        myConstraint0 = view.findViewById(R.id.myConstraint0);
        cardView = view.findViewById(R.id.card_view);
        cardView0 = view.findViewById(R.id.card_view0);
        cardView1 = view.findViewById(R.id.card_view1);
        cardView2 = view.findViewById(R.id.card_view2);
        mainConstraintLogOut = view.findViewById(R.id.mainConstraintLogOut);
        cardView3 = view.findViewById(R.id.card_view3);
        cardView4 = view.findViewById(R.id.card_view4);

        cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Realm realm1 = Realm.getDefaultInstance();
                realm1.beginTransaction();
                realm1.deleteAll();
                realm1.commitTransaction();
                realm1.close();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        mainConstraintLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Realm realm1 = Realm.getDefaultInstance();
                realm1.beginTransaction();
                realm1.deleteAll();
                realm1.commitTransaction();
                realm1.close();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Bookings
                Intent intent = new Intent(getActivity(), MyBookingsActivity.class);
                startActivity(intent);

            }
        });
        myConstraint0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MyBookingsActivity.class);
                startActivity(intent);
            }
        });


        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Fav
                Intent intent = new Intent(getActivity(), MyFavouriteActivity.class);
                startActivity(intent);
            }
        });
        myConstraint1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MyFavouriteActivity.class);
                startActivity(intent);

            }
        });

        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Customer Care
                customerCareDialog();
            }
        });
        myConstraint3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customerCareDialog();

            }
        });
        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Settings
                Intent intent = new Intent(getActivity(), SettingsActivity.class);
                startActivity(intent);

            }
        });
        myConstraint2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SettingsActivity.class);
                startActivity(intent);

            }
        });

        cardView0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Account
                Intent intent = new Intent(getActivity(), MyAccountActivity.class);
                startActivity(intent);

            }
        });
        mainConstraintRequestArtisan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ArtisanRequestActivity.class);
                startActivity(intent);

            }
        });


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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    void  customerCareDialog()
    {
        Intent intent = new Intent(getActivity(), CustomerCareActivity.class);
        startActivity(intent);

    }
}
