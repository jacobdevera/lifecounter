package edu.washington.gjdevera.lifecounter;

import java.util.List;
import java.util.ArrayList;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    
    // start with four players with twenty lives
    private List<Integer> lives = new ArrayList<>();
    private LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ll = (LinearLayout) findViewById(R.id.activity_main);

        final Button btnAdd = (Button) findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPlayer();
            }
        });

        // create buttons for all new players
        for (int i = 0; i < 4; i++) {
            addPlayer();
        }
    }

    private void initButton(Button button, String str, int player, int modifier, LinearLayout ll) {
        button.setText(str);
        button.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1f));
        button.setId(player);
        button.setOnClickListener(new ButtonListener(player, modifier));
        ll.addView(button);
    }

    public void addPlayer() {
        int players = lives.size();
        if (players > 7) {
            // already have 8 players
        } else {
            lives.add(20);
            TextView tv = new TextView(this);
            tv.setText("Player " + (players + 1) + ": " + lives.get(players));
            tv.setId(players);
            ll.addView(tv);

            LinearLayout buttonLayout = new LinearLayout(this);
            buttonLayout.setOrientation(LinearLayout.HORIZONTAL);
            buttonLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT, 1f));
            initButton(new Button(this), "+", players, 1, buttonLayout);
            initButton(new Button(this), "-", players, -1, buttonLayout);
            initButton(new Button(this), "+5", players, 5, buttonLayout);
            initButton(new Button(this), "-5", players, -5, buttonLayout);
            ll.addView(buttonLayout);
        }
    }

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
            lives.set(player, lives.get(player) + modifier);
            TextView tv = (TextView) findViewById(player);
            tv.setText("Player " + (player + 1) + ": " + lives.get(player));
        }
    }
}
