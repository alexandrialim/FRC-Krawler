package com.team2052.frckrawler.fragments;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Spinner;

import com.team2052.frckrawler.Constants;
import com.team2052.frckrawler.R;
import com.team2052.frckrawler.activities.EventInfoActivity;
import com.team2052.frckrawler.activities.ScoutActivity;
import com.team2052.frckrawler.bluetooth.server.events.ServerStateChangeEvent;
import com.team2052.frckrawler.bluetooth.server.events.ServerStateRequestChangeEvent;
import com.team2052.frckrawler.bluetooth.server.events.ServerStateRequestEvent;
import com.team2052.frckrawler.consumer.DataConsumer;
import com.team2052.frckrawler.consumer.NoDataHandler;
import com.team2052.frckrawler.consumer.SpinnerConsumer;
import com.team2052.frckrawler.db.Event;
import com.team2052.frckrawler.fragments.dialog.ExportDialogFragment;
import com.team2052.frckrawler.subscribers.EventStringSubscriber;

import java.util.List;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import rx.Observable;

public class ServerFragment extends BaseDataFragment<List<Event>, List<String>, EventStringSubscriber, SpinnerConsumer>
        implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, NoDataHandler {
    private static final int REQUEST_BT_ENABLED = 1;

    SwitchCompat mHostToggle;
    Spinner mEventSpinner;
    View mServerEventContainer, mServerEventsError, mScoutServerCard;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_server, null, false);
    }

    @Subscribe
    public void onEvent(ServerStateChangeEvent serverStateChangeEvent) {
        mHostToggle.setOnCheckedChangeListener(null);
        mHostToggle.setChecked(serverStateChangeEvent.getState());
        mHostToggle.setOnCheckedChangeListener(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mHostToggle = (SwitchCompat) view.findViewById(R.id.host_toggle);
        mHostToggle.setOnCheckedChangeListener(this);

        mEventSpinner = (Spinner) view.findViewById(R.id.event_spinner);
        mServerEventContainer = view.findViewById(R.id.server_event_container);
        mServerEventsError = view.findViewById(R.id.server_events_error);
        mScoutServerCard = view.findViewById(R.id.scout_server_card);

        view.findViewById(R.id.view_event).setOnClickListener(this);
        view.findViewById(R.id.scout_match_button).setOnClickListener(this);
        view.findViewById(R.id.scout_pit_button).setOnClickListener(this);
        view.findViewById(R.id.scout_practice_button).setOnClickListener(this);

        binder.mSpinner = mEventSpinner;
        binder.noDataHandler = this;

        EventBus.getDefault().post(new ServerStateRequestEvent());

        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView.getId() == R.id.host_toggle) {
            if (BluetoothAdapter.getDefaultAdapter() == null) {
                Snackbar.make(getView(), "Sorry, your device does not support bluetooth.", Snackbar.LENGTH_LONG).show();
                return;
            } else if (!BluetoothAdapter.getDefaultAdapter().isEnabled()) {
                startActivityForResult(new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE), REQUEST_BT_ENABLED);
                return;
            }
            buttonView.setChecked(!isChecked);
            if (isEventsValid()) {
                Event event = getSelectedEvent();
                EventBus.getDefault().post(new ServerStateRequestChangeEvent(!mHostToggle.isChecked(), event));
            }
        }
    }

    @Override
    public void onClick(View view) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Constants.PREFS_FILE_NAME, 0);
        if (isEventsValid() && getSelectedEvent() != null) {
            switch (view.getId()) {
                case R.id.view_event:
                    startActivity(EventInfoActivity.newInstance(getActivity(), getSelectedEvent().getId()));
                    return;
                case R.id.scout_match_button:
                    startActivity(ScoutActivity.newInstance(getActivity(), getSelectedEvent(), ScoutActivity.MATCH_SCOUT_TYPE));
                    return;
                case R.id.scout_pit_button:
                    startActivity(ScoutActivity.newInstance(getActivity(), getSelectedEvent(), ScoutActivity.PIT_SCOUT_TYPE));
                    return;
                case R.id.scout_practice_button:
                    startActivity(ScoutActivity.newInstance(getActivity(), getSelectedEvent(), ScoutActivity.PRACTICE_MATCH_SCOUT_TYPE));
                    return;
            }
        }
    }

    public void showEventError(boolean shown) {
        if (shown) {
            mServerEventContainer.setVisibility(View.GONE);
            mScoutServerCard.setVisibility(View.GONE);
            mServerEventsError.setVisibility(View.VISIBLE);
        } else {
            mServerEventContainer.setVisibility(View.VISIBLE);
            mScoutServerCard.setVisibility(View.VISIBLE);
            mServerEventsError.setVisibility(View.GONE);
        }
    }

    @Override
    public void noData(DataConsumer consumer) {
        showEventError(true);
    }

    @Override
    public void showData() {
        showEventError(false);
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    public void inject() {
        mComponent.inject(this);
    }

    @Override
    protected Observable<? extends List<Event>> getObservable() {
        return dbManager.allEvents();
    }

    private Event getSelectedEvent() {
        return subscriber.getData().get(mEventSpinner.getSelectedItemPosition());
    }

    private boolean isEventsValid() {
        return subscriber.getData() != null && !subscriber.getData().isEmpty();
    }
}