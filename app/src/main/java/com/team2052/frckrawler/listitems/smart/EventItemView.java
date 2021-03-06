package com.team2052.frckrawler.listitems.smart;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.team2052.frckrawler.R;
import com.team2052.frckrawler.db.Event;

import java.text.DateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.nlopez.smartadapters.views.BindableFrameLayout;

public class EventItemView extends BindableFrameLayout<Event> {
    @BindView(R.id.list_view_event_name)
    TextView mName;

    @BindView(R.id.list_view_event_date)
    TextView mDate;

    public EventItemView(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.list_item_event;
    }

    @Override
    public void bind(Event event) {
        setOnClickListener(v -> notifyItemAction(SmartAdapterInteractions.EVENT_CLICKED));
        this.setFocusable(true);
        this.setClickable(true);

        mName.setText(event.getName());
        mDate.setText(DateFormat.getDateInstance().format(event.getDate()));
    }

    @Override
    public void onViewInflated() {
        ButterKnife.bind(this);
        setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }
}
