package agora.ccna.tsthttpgetlisteimage2;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
/*
Classe de gestion des vues de chaque item
 */
public class vieilleVue extends RecyclerView.ViewHolder {

    //membre : l'imageView qui sera rempli par l'image téléchargée
    private ImageView image;

    //itemView est la vue correspondante à 1 cellule
    public vieilleVue(View v , View.OnClickListener onItemClickListener) {
        super(v);
        //c'est ici que l'on fait nos findView
        image = (ImageView) itemView.findViewById(R.id.itemImage);
        Log.i("ADAP" , "Constructeur vieillevue = " );
        v.setTag(this);
        v.setOnClickListener(onItemClickListener);
    }

    public void bind(String url){
        Log.i("ADAP" , "bind vieillevue = " + url );
        //création d'un client qui se charge de télécharger l'image passée en paramètre
        //il sera appelé pour chaque itm du recyclerView
        clientTcp cli = new clientTcp(image);
        //exécution du thread de la classe : méthode doItBackground
        //avec en paramètre l'url de l'mage à télécharger
        cli.execute(url);
    }
}
