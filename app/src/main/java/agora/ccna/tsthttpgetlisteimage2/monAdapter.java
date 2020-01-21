package agora.ccna.tsthttpgetlisteimage2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
/*
Adapter graphique associé au recyclerView
Contient un seul imageView par item.
Rajouter d'autre widget si nécessaire (textview...)
 */
public class monAdapter extends RecyclerView.Adapter<vieilleVue> {

    //liste des noms de fichiers
    ArrayList<String> lst;
    //constructeur permettant d'initialiser la liste des noms d'images.
    monAdapter(ArrayList<String> l){
        lst = l;
        Log.i("ADAP" , "Constructeur adapteur nb element= " + lst.size());
    }
    //gestionnaire evenement pour une cellule
    private View.OnClickListener onItemClickListener;
    public void setOnItemClickListener(View.OnClickListener c)
    {
        this.onItemClickListener = c;
    }
    //création de la vue à appliquer autant de fois que nécessaire (dépend du nombre d'item et de la taille du smartphone
    @NonNull
    @Override
    public vieilleVue onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //vue "inflatée" (gonflée ou enflée) à partir du fichier malistitem.xml
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.malisteitem,parent,false);
        Log.i("ADAP" , "Creation vue ancienne" );
        return new vieilleVue(view , onItemClickListener);
    }

    //événement déclenché lorsque android doit afficher le recyclerView
    //elle sera donc appelée autant de fois que nécessaire ( = f(getItemCount , heightEcran ) )
    @Override
    public void onBindViewHolder(@NonNull vieilleVue holder, int position) {
        //on récupère le nom de l'image en fonction de sa position passée par le système
        String url = lst.get(position);
        //on appelle la méthode qui gère les données : affecte les valeurs aux composants graphiques
        holder.bind(url);
        Log.i("ADAP" , "bind adapteur position= " + position);
    }

    //retourne le nombre d'item
    @Override
    public int getItemCount() {
        Log.i("ADAP" , "GEt item count = " + lst.size());
        return lst.size();
    }
}
