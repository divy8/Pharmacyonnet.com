package workthrough;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


import com.pharmacy.pharmacyonnet.R;

import menu.ShoppyMenuActivity;

public class WorkthroughThreeFragment extends Fragment {

    private LinearLayout next;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.workthrough_3, container, false);


        next = (LinearLayout) rootView.findViewById(R.id.next);


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(getActivity(), ShoppyMenuActivity.class);
                startActivity(it);
            }
        });

        return rootView;


    }
}
