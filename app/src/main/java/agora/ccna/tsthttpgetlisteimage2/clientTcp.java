package agora.ccna.tsthttpgetlisteimage2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;



import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
/*
La requête HTTP pour downloader les images est asynchrone
 et doit interagir avec les composants graphiques de l'activité principale.
 C'est pourquoi elle est implémentée dans une classe de type "thread" ou tâche Asynchrone

 les paramètres template sont< String , Integer , Bitmap >:
        =>  String      nom de l'mage à télécharger
        =>  Integer     inutilisé ici
        =>  Bitmap      le bitmap à transmettre à la méthode postExecute pour remplir les imageView
 */

//déclaration de la classe asynchrone
public class clientTcp extends AsyncTask <String , Integer, Bitmap>{

    String url = null;
    //image remplie avec celle téléchérgée via son bitmap
    ImageView image;
    clientTcp(ImageView im ){
        super();
        image = im;
        //tv = t;
    }
    //exécutée lors de l'appel de "execute(String nomImage ) depuis l'activité principale
    @Override
    protected Bitmap doInBackground(String... x) {
        Log.i("ADAP" , "doInBackground : " + x[0].toString() );
        //requete HTTP / GET ......./x[0]
        //retourne le bitmap de l'image téléchargée (plus pratique pour le traitement sous android)
        Bitmap r = doGet(x[0]);
        //test de publish pour déclencher onProgressUpdate
        //for(int i=0; i< 5 ; i++)publishProgress(i , i+2);
        return r;
    }
    //inutilisée. Pour mettre en oeuvre, décommenter la ligne for(... de la méthode doItBackground(
    @Override
    protected void onProgressUpdate(Integer... i){
        super.onProgressUpdate(i);
        Log.i("ADAP" , "Progress : tab[i] = " + i.length);
        for(int j=0;j<i.length;j++)Log.i("ADAP" , "Progress : i = " + i[j] );
    }
    //exécutée à la fin de la méthode doItBackground
    //r est le bitmap retourné par doItBackground(...)
    @Override
    protected void onPostExecute(Bitmap r){
        super.onPostExecute(r);
        //on affecte à l'objet image instancié dans le constructeur le bitmap r
        image.setImageBitmap(r);
        Log.i("ADAP" , "onPostExecute : " );
        //tv.setText(r);
    }
    //***************
    //méthode qui télécharge le fichier image sur le serveur
    //adapter l'url bien évidemment.
    //A réitérer pour chaque fichier à télécharger
    public Bitmap  doGet(String u) {
        StringBuffer response = new StringBuffer();
        Bitmap bitmap = null;
        //urlcomplete += "\r";
        Log.i("ADAP", "url=" + u);
        URL obj;
        try {
            //création de l'Url
            obj = new URL(u);
            //ouverture de la connexion
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            Log.i("ADAP", "Apres connection");
            // choisir la méthode : GET dans notre cas
            con.setRequestMethod("GET");
            //création de l'entête http de la requête : A voir
            con.addRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
            con.setRequestProperty("Accept-Encoding", "gzip, deflate, sdch");
            con.setRequestProperty("Accept-Language", "fr-FR,fr;q=0.8,en-US;q=0.6,en;q=0.4");
            con.setRequestProperty("Upgrade-Insecure-Requests", "1");
            //con.setDoOutput(true);
            con.setReadTimeout(15*1000);
            //réception du code de retour du serveur
            //200 si OK (protocole htpp
            int responseCode = con.getResponseCode();
            Log.i("ADAP", "Rep code=" + responseCode);
            //lecture du flux issu du socket
            InputStream in = con.getInputStream();
            //convertion du fichier en bitmap ( => uri soit un fichier image de type jpg, png...)
            bitmap = BitmapFactory.decodeStream(in);
        } catch (
                MalformedURLException e) {
            // TODO Auto-generated catch block
            Log.i("ADAP", "exception malformed: " + e.getMessage());
            e.printStackTrace();
        } catch (
                IOException e) {
            // TODO Auto-generated catch block
            Log.i("ADAP", "exception generale: " + e.getMessage());
            e.printStackTrace();
        }
        //on retourne le bitmap qui sera ensuite transmis à la méthode postExecute
        return bitmap;
    }
}

