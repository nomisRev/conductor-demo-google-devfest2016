package be.vergauwen.simon.traditionalsharedelement;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FirstFragment extends Fragment {

    public static FirstFragment newInstance() {
        FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    private FloatingActionButton floatingActionButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.fab);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SecondFragment secondFragment = SecondFragment.newInstance();
                FragmentTransaction fragmentTransaction = getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Transition transition = TransitionInflater.from(getContext())
                            .inflateTransition(R.transition.arc_shared);
                    secondFragment.setSharedElementEnterTransition(transition);
                    secondFragment.setEnterTransition(new Fade());
                    secondFragment.setReturnTransition(transition);
                    setExitTransition(new Fade());
                    fragmentTransaction.addSharedElement(floatingActionButton,
                            getString(R.string.fab_transition_name));
                } else {
                    fragmentTransaction
                            .setCustomAnimations(android.R.anim.slide_in_left,
                                    android.R.anim.slide_out_right,
                                    android.R.anim.slide_in_left,
                                    android.R.anim.slide_out_right);
                }
                fragmentTransaction
                        .replace(R.id.content_frame, secondFragment)
                        .addToBackStack(SecondFragment.FRAGMENT_TAG)
                        .commit();
            }
        });
    }
}
