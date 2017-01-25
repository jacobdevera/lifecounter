package edu.washington.gjdevera.lifecounter;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class MainActivity extends Activity {

    private int players = 4;
    private List<Integer> scores = new ArrayList<>(Arrays.asList(0,0,0,0));
    private LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ll = (LinearLayout) findViewById(R.id.activity_main);

        // create buttons for all new players
        for (int i = 0; i < players; i++) {
            TextView tv = new TextView(this);
            tv.setText("Player " + (i + 1) + ": " + scores.get(i));
            tv.setId(i);
            ll.addView(tv);

            LinearLayout buttonLayout = new LinearLayout(this);
            buttonLayout.setOrientation(LinearLayout.HORIZONTAL);
            buttonLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT, 1f));
            initButton(new Button(this), "+", i, 1, buttonLayout);
            initButton(new Button(this), "-", i, -1, buttonLayout);
            initButton(new Button(this), "+5", i, 5, buttonLayout);
            initButton(new Button(this), "-5", i, -5, buttonLayout);
            ll.addView(buttonLayout);
        }
    }

    private void initButton(Button button, String str, int player, int modifier, LinearLayout ll) {
        button.setText(str);
        button.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1f));
        button.setId(player);
        button.setOnClickListener(new ButtonListener(player, modifier));
        ll.addView(button);
    }

    /*public void addPlayer(LinearLayout ll) {
        if (players > 7) {
            // too many players
        } else if (players < 3) {
            // too few players
        } else  {
            players++;
            TextView tv = new TextView(this);
            tv.setText("Player " + players);
            ll.addView(tv);

            Button b = new Button(this);
            b.setText("+");
            b.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            b.setId(players);
            b.setOnClickListener(new ButtonListener(players));
            ll.addView(b);
        }

    }*/

    // custom listener for buttons
    public class ButtonListener implements View.OnClickListener {
        private int player;
        private int modifier;

        public ButtonListener(int player, int modifier) {
            this.player = player;
            this.modifier = modifier;
        }

        @Override
        public void onClick(View v) {
            scores.set(player, scores.get(player) + modifier);
            TextView tv = (TextView) findViewById(player);
            tv.setText("Player " + (player + 1) + ": " + scores.get(player));
        }
    }
}
