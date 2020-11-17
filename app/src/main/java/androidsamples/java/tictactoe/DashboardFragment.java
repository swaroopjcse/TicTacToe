package androidsamples.java.tictactoe;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

public class DashboardFragment extends Fragment {

    private static final String TAG = "DashboardFragment";

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public DashboardFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");

        // TODO toggle this value to navigate to the LoginFragment
        boolean needAuth = false;
        // Todo You should check if a user is logged in, otherwise show LoginFragment
        if (needAuth) {
            Log.d(TAG, "Authentication needed; navigating to LoginFragment");
            NavDirections action = DashboardFragmentDirections.actionNeedAuth();
            Navigation.findNavController(getActivity(), R.id.nav_host_fragment)
                    .navigate(action);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        view.findViewById(R.id.fab_new_game).setOnClickListener(v -> {
            AlertDialog dialog = new AlertDialog.Builder(requireActivity())
                    .setTitle(getString(R.string.new_game))
                    .setMessage("Which type of game do you want to create?")
                    .setPositiveButton("Two-Player", (d, which) -> {
                        Log.d(TAG, "New Two-Player Game");
                        NavDirections action = DashboardFragmentDirections.actionGame(GameFragment.TWO_PLAYER_GAME);
                        Navigation.findNavController(v).navigate(action);
                    })
                    .setNegativeButton("One-Player", (d, which) -> {
                        Log.d(TAG, "New One-Player Game");
                        NavDirections action = DashboardFragmentDirections.actionGame(GameFragment.ONE_PLAYER_GAME);
                        Navigation.findNavController(v).navigate(action);
                    })
                    .setNeutralButton("Cancel", (d, which) -> d.dismiss())
                    .create();
            dialog.show();
        });
        return view;
    }
}