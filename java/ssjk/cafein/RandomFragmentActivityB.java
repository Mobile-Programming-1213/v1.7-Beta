package ssjk.cafein;

/**
 * Created by wqe13 on 2016-11-30.
 */

import kankan.wheel.widget.OnWheelChangedListener;
import kankan.wheel.widget.OnWheelScrollListener;
import kankan.wheel.widget.WheelView;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class RandomFragmentActivityB extends Fragment {
    private RandomAdapter CoffeeAdapter;
    public static String strtext;
    private boolean btnpressed = false;
    public RandomFragmentActivityB(){

    }

    //private String items[];
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_b, container, false);
        strtext = getArguments().getString("cafe_name");
        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initWheel(R.id.slot_coffee);

        Button mix = (Button) getActivity().findViewById(R.id.btn_mix_coffee);
        mix.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                mixWheel(R.id.slot_coffee);
                btnpressed = true;
            }
        });

        updateStatus();
    }
    // Wheel scrolled flag
    private boolean wheelScrolled = false;

    // Wheel scrolled listener
    OnWheelScrollListener scrolledListener = new OnWheelScrollListener() {
        public void onScrollingStarted(WheelView wheel) {
            wheelScrolled = true;
        }
        public void onScrollingFinished(WheelView wheel) {
            wheelScrolled = false;
            updateStatus();
        }
    };

    // Wheel changed listener
    private OnWheelChangedListener changedListener = new OnWheelChangedListener() {
        public void onChanged(WheelView wheel, int oldValue, int newValue) {
            if (!wheelScrolled) {
                updateStatus();
            }
        }
    };

    /**
     * Updates status
     */
    private void updateStatus() {
        if(btnpressed) {
            int i = getWheel(R.id.slot_coffee).getCurrentItem();
            TextView text = (TextView) getActivity().findViewById(R.id.status_coffee);
            Button mix = (Button) getActivity().findViewById(R.id.btn_mix_coffee);
            text.setText(CoffeeAdapter.items[i]);
            mix.setText("다시 정하기!");
        }
        else{
            btnpressed = false;
        }
    }
    /*
    private String updateString() {
        int i = getWheel(R.id.slot_coffee).getCurrentItem();
        return items[i];
    }
*/
    /**
     * Initializes wheel
     * @param id the wheel widget Id
     */
    private void initWheel(int id) {
        WheelView wheel = getWheel(id);
        CoffeeAdapter = new RandomAdapter(getActivity(), strtext);
        wheel.setViewAdapter(CoffeeAdapter);
        wheel.setCurrentItem(0);
        wheel.addChangingListener(changedListener);
        wheel.addScrollingListener(scrolledListener);
        wheel.setCyclic(true);
        wheel.setEnabled(false);
    }

    /**
     * Returns wheel by Id
     * @param id the wheel Id
     * @return the wheel with passed Id
     */
    private WheelView getWheel(int id) {
        return (WheelView) getActivity().findViewById(id);
    }


    /**
     * Mixes wheel
     * @param id the wheel id
     */
    private void mixWheel(int id) {
        WheelView wheel = getWheel(id);
        wheel.scroll(-350 + (int)(Math.random() * 50), 2000);
    }

}
