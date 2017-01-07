package com.example.lavanya.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //0=yellow 1=red
        int activeplayer=0;
        int[] gamestate={2,2,2,2,2,2,2,2,2};
    int[][] winningpositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean isgameActive=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void dropin(View view){
        ImageView counter=(ImageView) view;
        int tappednumber=Integer.parseInt(counter.getTag().toString());
        if(gamestate[tappednumber]==2 && isgameActive) {
            gamestate[tappednumber]=activeplayer;
            counter.setTranslationY(-1000f);
            if (activeplayer == 0) {
                counter.setImageResource(R.drawable.circleyel);
                activeplayer = 1;
            } else {
                counter.setImageResource(R.drawable.circlered);
                activeplayer = 0;
            }
            counter.animate().translationYBy(1000f).setDuration(300);
            for(int[] winning: winningpositions){
                if(gamestate[winning[0]] == gamestate[winning[1]] && gamestate[winning[1]]== gamestate[winning[2]] &&
                        gamestate[winning[0]] != 2){
                    //someone has won
                   // Toast.makeText(this,"player " +" "+ gamestate[winning[0]]+ "won",Toast.LENGTH_SHORT).show();
                    isgameActive=false;
                    String winner="Red";
                    if(gamestate[winning[0]]==0){
                        winner="yellow";
                    }
                    TextView textView= (TextView) findViewById(R.id.winnermsg);
                    textView.setText(winner + " has won");
                    LinearLayout linearLayout= (LinearLayout) findViewById(R.id.playagainlayout);
                    linearLayout.setVisibility(View.VISIBLE);
                }
                else{
                    boolean gameover=true;
                    for(int h=0;h<gamestate.length;h++){
                        if(gamestate[h]==2){
                            gameover=false;
                            break;
                        }
                    }
                    if(gameover){
                        TextView textView= (TextView) findViewById(R.id.winnermsg);
                        textView.setText("Its a tie");
                        LinearLayout linearLayout= (LinearLayout) findViewById(R.id.playagainlayout);
                        linearLayout.setVisibility(View.VISIBLE);
                    }
                }
            }
        }

    }

    public void playagain(View view){
        isgameActive=true;
        LinearLayout linearLayout= (LinearLayout) findViewById(R.id.playagainlayout);
        linearLayout.setVisibility(View.INVISIBLE);
        activeplayer=0;
        for(int y=0;y<gamestate.length;y++){
            gamestate[y]=2;
        }
        GridLayout gridLayout= (GridLayout) findViewById(R.id.tictacgrid);
        for(int t=0;t<gridLayout.getChildCount();t++){
            ((ImageView)gridLayout.getChildAt(t)).setImageResource(0);
        }
    }
}
