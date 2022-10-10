package MP08.UF1;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

/*
    Abans de començar hem de fer:
    (1) Afegir al layout activity_main una ListView anomenada recordsView
            R.id.recordsView
    (2) Un layout (lineal) per a la ListView anomenat list_item
            R.layout.list_item
        Aquest contindrà 2 TextView amb IDs: nom i intents
            R.id.nom
            R.id.intents
    (3) Un botó amb id=button (per generar entrades al model)

    Referències
        - https://github.com/codepath/android_guides/wiki/Using-an-ArrayAdapter-with-ListView
 */

public class MainActivity extends AppCompatActivity {

    // Model: Record (intents=puntuació, nom)
    class Record {
        public int intents;
        public String nom;

        public Record(int _intents, String _nom ) {
            intents = _intents;
            nom = _nom;
        }

        public int getIntents() {
            return intents;
        }
    }
    // Model = Taula de records: utilitzem ArrayList
    ArrayList<Record> records;

    // ArrayAdapter serà l'intermediari amb la ListView
    ArrayAdapter<Record> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicialitzem model
        records = new ArrayList<Record>();


        ArrayList<String> personitas = new ArrayList<>();
        personitas.add("Juan");
        personitas.add("Ismael");
        personitas.add("Pablo");
        personitas.add("Edu");
        personitas.add("Sergio");
        personitas.add("Irene");
        personitas.add("Rafael");
        personitas.add("Erik");
        personitas.add("Alejandro");
        personitas.add("Borja");
        personitas.add("Ivan");
        personitas.add("Mark");
        personitas.add("David");
        personitas.add("Edgar");
        personitas.add("Albert");

        // Afegim alguns exemples
        records.add( new Record(33,"Manolo") );
        records.add( new Record(12,"Pepe") );
        records.add( new Record(42,"Laura") );

        // Inicialitzem l'ArrayAdapter amb el layout pertinent
        adapter = new ArrayAdapter<Record>( this, R.layout.list_item, records )
        {
            @Override
            public View getView(int pos, View convertView, ViewGroup container)
            {
                // getView ens construeix el layout i hi "pinta" els valors de l'element en la posició pos
                if( convertView==null ) {
                    // inicialitzem l'element la View amb el seu layout
                    convertView = getLayoutInflater().inflate(R.layout.list_item, container, false);
                }
                // "Pintem" valors (també quan es refresca)
                ((TextView) convertView.findViewById(R.id.nom)).setText(getItem(pos).nom);
                ((TextView) convertView.findViewById(R.id.intents)).setText(Integer.toString(getItem(pos).intents));
                return convertView;
            }

        };

        // busquem la ListView i li endollem el ArrayAdapter
        ListView lv = (ListView) findViewById(R.id.recordsView);
        lv.setAdapter(adapter);


        // botó per afegir entrades a la ListView
        Button b = (Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random rd = new Random();

                for (int i=0;i<15;i++) {
                    int randomitem = rd.nextInt(personitas. size());
                    int r = (int) Math.floor(Math.random() * 100);
                    records.add(new Record(r,personitas.get(randomitem)));
                }
                // notificar l'adapter dels canvis al model
                adapter.notifyDataSetChanged();
            }
        });
    }

}