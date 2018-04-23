package com.example.win10.a4_2laboratorio1_asynctask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText edtn;
    TextView txtv;
    Button btnI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnI=(Button)findViewById(R.id.btnInit);
        edtn=(EditText)findViewById(R.id.editText);
        txtv=(TextView)findViewById(R.id.txtFib);

        btnI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnI.setClickable(false);
                AsyncTask_load hilo1 = new AsyncTask_load(btnI,txtv,Integer.parseInt(edtn.getText().toString()));
                hilo1.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
        });
    }

    private void conteo() {

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public class AsyncTask_load extends AsyncTask<Void, Integer, Void> {

        int num,limit,numAnt,numDes;
        Button btn;
        TextView txt;
        String st = "";

        public AsyncTask_load( Button btn, TextView txt,int limit) {
            this.limit = limit;
            this.btn = btn;
            this.txt = txt;
        }
        @Override
        protected void onPreExecute(){
            num = 0;
            numAnt = 0;
            numDes = 1;
            super.onPreExecute();

        }
        @Override
        protected Void doInBackground(Void... voids) {
            while(this.num < this.limit){
                num++;
                int resultado = numAnt + numDes;
                publishProgress(numAnt);
                numAnt = numDes;
                numDes = resultado;
                conteo();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values){
            super.onProgressUpdate(values);
            this.st += values[0].toString() + "\n";
            this.txt.setText(this.st);
        }

        @Override
        protected void onPostExecute(Void result){
            btn.setClickable(true);
        }
    }
}
