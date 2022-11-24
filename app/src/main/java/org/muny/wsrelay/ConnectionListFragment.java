package org.muny.wsrelay;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.muny.wsrelay.databinding.FragmentConnectionListBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConnectionListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConnectionListFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FragmentConnectionListBinding binding;

    public ConnectionListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ConnectionListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ConnectionListFragment newInstance(String param1, String param2) {
        ConnectionListFragment fragment = new ConnectionListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.button.setOnClickListener((e)->{
            NavHostFragment.findNavController(ConnectionListFragment.this).navigate(R.id.action_connectionListFragment_to_fragmentStart2);

        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentConnectionListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}