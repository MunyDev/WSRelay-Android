package org.muny.wsrelay;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.FragmentNavigatorDestinationBuilder;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import org.muny.wsrelay.backend.WSRelaySocketServerBackend;
import org.muny.wsrelay.databinding.FragmentStartBinding;

import java.net.InetSocketAddress;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentStart#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentStart extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private FragmentStartBinding fsb;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentStart() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment start.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentStart newInstance(String param1, String param2) {
        FragmentStart fragment = new FragmentStart();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        System.out.println("Fragment Started!");
        //NavHostFragment.findNavController(FragmentStart.this).navigate(R.id.action_fragmentStart_to_connectionListFragment);
        ((Button) fsb.buttonRelay).setOnClickListener((e)->{
            WSRelaySocketServerBackend relay = new WSRelaySocketServerBackend(new InetSocketAddress("127.0.0.1", 8080));
            relay.start();
        });
        ((Button) fsb.connectionlistbtn).setOnClickListener((e)->{

            NavHostFragment.findNavController(FragmentStart.this).navigate(R.id.action_fragmentStart_to_connectionListFragment);
        });
        ((ImageButton) fsb.settings).setOnClickListener((e)->{
            NavHostFragment.findNavController(FragmentStart.this).navigate(R.id.action_fragmentStart_to_settingsFragment);
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fsb = FragmentStartBinding.inflate(inflater, container, false);
        return fsb.getRoot();
    }
}