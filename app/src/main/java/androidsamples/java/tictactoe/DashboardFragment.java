package androidsamples.java.tictactoe;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

public class DashboardFragment extends Fragment {

    private static final String TAG = "DashboardFragment";
    private NavController mNavController;

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

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mNavController = Navigation.findNavController(view);

        // TODO if a user is not logged in, go to LoginFragment

        // Show a dialog when the user clicks the "new game" button
        view.findViewById(R.id.fab_new_game).setOnClickListener(v -> {
            AlertDialog dialog = new AlertDialog.Builder(requireActivity())
                    .setTitle(getString(R.string.new_game))
                    .setMessage(R.string.new_game_dialog_message)
                    .setPositiveButton(R.string.two_player, (d, which) -> {
                        Log.d(TAG, "New Game: " + getString(R.string.two_player));
                        NavDirections action = DashboardFragmentDirections.actionGame(getString(R.string.two_player));
                        mNavController.navigate(action);
                    })
                    .setNegativeButton(R.string.one_player, (d, which) -> {
                        Log.d(TAG, "New Game: " + getString(R.string.one_player));
                        NavDirections action = DashboardFragmentDirections.actionGame(getString(R.string.one_player));
                        mNavController.navigate(action);
                    })
                    .setNeutralButton("Cancel", (d, which) -> d.dismiss())
                    .create();
            dialog.show();
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);
    }
}