package ssjk.cafein;

/**
 * Created by wqe13 on 2016-11-30.
 */

import kankan.wheel.widget.OnWheelChangedListener;
import kankan.wheel.widget.OnWheelScrollListener;
import kankan.wheel.widget.WheelView;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class RandomFragmentActivityA extends Fragment {
    private RandomAdapter CafeAdapter;
    private AsyncTask<Void, Integer, Void> FragB;
    private boolean btnpressed;

    public RandomFragmentActivityA(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_a, container, false);
        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initWheel(R.id.slot_cafe);

        Button mix = (Button) getActivity().findViewById(R.id.btn_mix_cafe);
        mix.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                FragB = new AsyncTask<Void, Integer, Void>() {
                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();
                    }

                    @Override
                    protected Void doInBackground(Void... params) {
                        mixWheel(R.id.slot_cafe);
                        btnpressed = true;
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException ex) {}
                        return null;
                    }
                    @Override
                    protected void onPostExecute(Void result) {
                        Bundle bundle = new Bundle();
                        String str = updateString();
                        bundle.putString("cafe_name", str);
                        RandomFragmentActivityB RFAB = new RandomFragmentActivityB();
                        RFAB.setArguments(bundle);
                        FragmentManager fm = getActivity().getFragmentManager();
                        FragmentTransaction fragmentTransaction = fm.beginTransaction();
                        fragmentTransaction.replace(R.id.fragmentB, RFAB);
                        fragmentTransaction.commit();
                    }
                    };
                FragB.execute();
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
            int i = getWheel(R.id.slot_cafe).getCurrentItem();
            TextView text = (TextView) getActivity().findViewById(R.id.status_cafe);
            Button mix = (Button) getActivity().findViewById(R.id.btn_mix_cafe);
            text.setText(CafeAdapter.items[i]);
            mix.setText("다시 정하기!");
        }
        else{
            btnpressed = false;
        }
    }
    private String updateString() {
        int i = getWheel(R.id.slot_cafe).getCurrentItem();
        return CafeAdapter.items[i];
    }

    /**
     * Initializes wheel
     * @param id the wheel widget Id
     */
    private void initWheel(int id) {
        WheelView wheel = getWheel(id);
        CafeAdapter = new RandomAdapter(getActivity(), "CAFE");
        wheel.setViewAdapter(CafeAdapter);
        wheel.setCurrentItem((int)(Math.random() * 10));
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
     * Tests wheels
     * @return true
     *//*
    private boolean test() {
        int value = getWheel(R.id.slot_1).getCurrentItem();
        return testWheelValue(R.id.slot_2, value) && testWheelValue(R.id.slot_3, value);
    }
*/

    /**
     * Mixes wheel
     * @param id the wheel id
     */
    private void mixWheel(int id) {
        WheelView wheel = getWheel(id);
        wheel.scroll(-350 + (int)(Math.random() * 50), 2000);
    }
}