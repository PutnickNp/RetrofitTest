package rs.tafilovic.retrofittest.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import rs.tafilovic.retrofittest.R;
import rs.tafilovic.retrofittest.model.Movies;
import rs.tafilovic.retrofittest.model.Result;
import rs.tafilovic.retrofittest.rest.MovieController;
import rs.tafilovic.retrofittest.rest.RestCallback;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, RestCallback<Movies, Throwable> {

    Button btnGetMovies;
    TextView tvResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGetMovies = findViewById(R.id.btnGetMovies);
        tvResponse = findViewById(R.id.tvResponse);

        btnGetMovies.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnGetMovies:
                MovieController movieController = new MovieController(this);
                movieController.getMovies("1");
                break;
        }
    }

    @Override
    public void onResult(Movies result) {
        if(result!=null && result.getResults()!=null){
            StringBuffer sb=new StringBuffer();
            for(Result r:result.getResults()){
                sb.append("Title: "+r.getOriginalTitle()+"\n\r");
            }
            tvResponse.setText(sb.toString());
        }
    }

    @Override
    public void onError(Throwable error) {
        tvResponse.setText(error.getMessage());
    }
}
