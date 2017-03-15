package com.example.elsa.tpapplimobile_bdd_beckerelsa;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends ListActivity {

    private ChapitresBDD bdd_source;

    private final int RESULT_SELECTION = 0;
    private final String EXTRA_1 = "Premiere info";
    private final String EXTRA_2 = "Deuxième info";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bdd_source = new ChapitresBDD(this);

        afficheBDD();

    }

    public void afficheBDD(){
        bdd_source.open();

        List<Chapitres> values = bdd_source.getAllChapters();

        //utiliser le SimpleCursorAdapter pour montrer les elements dans la ListVIew
        ArrayAdapter<Chapitres> adapter = new ArrayAdapter<Chapitres>
                (this, android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);
    }

    //Appelé via attribut onClick des boutons du activity_main.xml
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.add:

                Intent intent = new Intent(MainActivity.this, ConnectedActivity.class);

                startActivityForResult(intent,RESULT_SELECTION);

                break;

            case R.id.delete: // Pas eu le temps de le recoder après l'ajout de la connected activity, avec les changements d'entrée manuelles

                break;
        }
    }

    @Override
    protected void onResume(){
        bdd_source.open();
        super.onResume();
    }

    @Override
    protected void onPause(){
        bdd_source.close();
        super.onPause();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_SELECTION){
            if(resultCode == RESULT_OK){

                afficheBDD();
                Log.v("ActivityResult", "Result_OK");
                Toast.makeText(this, "RESULT_OK", Toast.LENGTH_LONG).show();
            }else{
                Log.v("ActivityResult", "Result_KO");
                Toast.makeText(this, "RESULT_KO", Toast.LENGTH_LONG).show();
            }
        }
    }
}
