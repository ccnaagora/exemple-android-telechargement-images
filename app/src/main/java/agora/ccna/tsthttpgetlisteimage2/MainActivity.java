package agora.ccna.tsthttpgetlisteimage2;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
/*
Liste des fichiers images à télécharger.
A adapter sur le serveur
 */
    String[] urls = {"http://192.168.1.13/1.png" , "http://192.168.1.13/2.png", "http://192.168.1.13/3.png"
            , "http://192.168.1.13/4.png", "http://192.168.1.13/5.png", "http://192.168.1.13/6.png"
            , "http://192.168.1.13/7.png", "http://192.168.1.13/1.png" , "http://192.168.1.13/2.png", "http://192.168.1.13/3.png"
            , "http://192.168.1.13/4.png", "http://192.168.1.13/5.png", "http://192.168.1.13/6.png"
            , "http://192.168.1.13/7.png"};
    ArrayList<String> lst;
    //liste qui sera remplie avec les images téléchargées.
    RecyclerView liste;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        //*************************************
        //mise en place du recyclerview
        lst = new ArrayList<String>();
        for(int i=0 ; i< urls.length ; i++){
            lst.add(urls[i]);
        }
        liste = findViewById(R.id.liste);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        liste.setLayoutManager(manager);
        monAdapter adapter = new monAdapter(lst);
        liste.setAdapter(adapter);
        adapter.setOnItemClickListener(onItemClickListener);
    }
    //***gestionnaire evenement : affichage du nom de l'image dans les logs et dans un textView
    //à adapter en fonction des besoins.
    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //TODO: Step 4 of 4: Finally call getTag() on the view.
            // This viewHolder will have all required values.
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            //récupération du nom et de la position de l'image dans le recyclerView
            int position = viewHolder.getAdapterPosition();
            String texte = lst.get(position);
            //affichage de l'url dans le textView
            TextView tv = findViewById(R.id.textView);
            tv.setText(texte);
            Log.i("ADAP" , "1) texte clické : " + texte);
            Log.i("ADAP" , "2) position=" + ((vieilleVue)viewHolder).getPosition());
            /*
            Insérer le code de gestion du click en fonction
            des besoins de l'application
             */
        }
    };
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
