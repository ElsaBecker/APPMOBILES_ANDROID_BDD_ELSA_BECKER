package com.example.elsa.tpapplimobile_bdd_beckerelsa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ConnectedActivity extends AppCompatActivity {

    private final String EXTRA_1 = "Premiere info";
    private final String EXTRA_2 = "Deuxième info";

    private EditText addtitle;
    private EditText adddesc;
    private Button addchapter;


    ChapitresBDD bdd_add;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connected);

    addtitle = (EditText) findViewById(R.id.editchapter);
    adddesc = (EditText) findViewById(R.id.editdesc);
    addchapter = (Button) findViewById(R.id.addNewEntry);

     bdd_add = new ChapitresBDD(this);



        addchapter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(ConnectedActivity.this, MainActivity.class);

                Chapitres chapter = new Chapitres(addtitle.getText().toString(), adddesc.getText().toString());
                bdd_add.open();
                bdd_add.insertChapter(chapter);
                setResult(RESULT_OK, intent);
                finish() ;
            }
        });




    }
}
