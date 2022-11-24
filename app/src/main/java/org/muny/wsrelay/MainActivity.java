package org.muny.wsrelay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.transition.Fade;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.ViewGroup;
import android.widget.Button;

import org.java_websocket.server.DefaultSSLWebSocketServerFactory;
import org.muny.wsrelay.backend.WSRelaySocketServerBackend;
import org.muny.wsrelay.databinding.ActivityMainBinding;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

import javax.net.ssl.SSLContext;

public class MainActivity extends AppCompatActivity {
    public static AppCompatActivity instance;
    public enum Progress {
        STARTING_SERVER,
        STOPPING_SERVER,
        RESTARTING_SERVER

    }
    public static Progress serverProgress = Progress.STARTING_SERVER;
    public static Button statusButton;
    public ActivityMainBinding binding;
    public static void setProgress(Progress p){
        Button b = statusButton;
        serverProgress = p;
        switch (p) {

            case STARTING_SERVER:
                b.setText(R.string.wsrelay_progress_startingserver);
                return;
            case STOPPING_SERVER:
                b.setText(R.string.wsrelay_progress_stoppingserver);
                return;
            case RESTARTING_SERVER:
                b.setText(R.string.wsrelay_progress_restartingserver);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        NavController navController = Navigation.findNavController(this, R.id.fragment_container);

        instance = MainActivity.this;


    }

}