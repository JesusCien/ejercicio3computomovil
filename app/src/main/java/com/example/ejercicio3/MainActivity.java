package com.example.ejercicio3;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.UrlQuerySanitizer;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity extends AppCompatActivity {

        private static final String LOGTAG = "INFORMACIÓN";

        private Curso curso;
        private Button btnBoton1;
        ArrayList<Curso> datos = new ArrayList<Curso>();
        String idcom;
        String nombrecom;
        String sedecom;
        String fechainiccom;
        String fechafincom;
        ByteArrayInputStream inputStream;
        String arregloid[];

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);



                btnBoton1 = findViewById(R.id.btnBoton1); //enviar


                btnBoton1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {


                                Intent intent = new Intent(MainActivity.this, MainActivity2.class);

                                new ConexionHttp().execute("");

                                intent.putExtra("datos",datos);



                                startActivity(intent);

                                //Log.i(LOGTAG, nombrecom);

                        }
                });


                Log.d(LOGTAG, "dsdsdsds");


        }



        public class ConexionHttp extends AsyncTask<String, Float, String> {

                boolean sinError = true;

                @Override
                protected String doInBackground(String... strings) {
                        try{
                                StringBuffer fileData = new StringBuffer(4096);

                                URL sourceURL = new URL("https://serverbpw.com/cm/2019-2/cursos.php");

                                BufferedReader in = new BufferedReader(new InputStreamReader(sourceURL.openStream()));

                                String inputLine;

                                while((inputLine = in.readLine())!= null){
                                        fileData.append(inputLine);
                                }

                                in.close();

                                inputStream = new ByteArrayInputStream(fileData.toString().getBytes());

                                DocumentBuilderFactory dbFabrica = DocumentBuilderFactory.newInstance();

                                DocumentBuilder dBuilder = dbFabrica.newDocumentBuilder();
                                Document doc = dBuilder.parse(inputStream);

                                Element elemento = doc.getDocumentElement();
                                elemento.normalize();

                                NodeList nList = doc.getElementsByTagName("item");

                                for (int i=0; i<nList.getLength(); i++) {

                                        Node nodo = nList.item(i);
                                        if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                                                Element elemento2 = (Element) nodo;
                                                Log.d("DATO", "id: " + obtenValor("id",elemento2));
                                                Log.d("DATO", "nombre: " + obtenValor("nombre",elemento2));
                                                Log.d("DATO", "tipo: " + obtenValor("sede",elemento2));
                                                Log.d("DATO", "tipo: " + obtenValor("fechainic",elemento2));
                                                Log.d("DATO", "tipo: " + obtenValor("fechafin",elemento2));

                                                Curso animeTmp = new Curso(Integer.parseInt(obtenValor("id",elemento2)),obtenValor("id",elemento2),
                                                        obtenValor("nombre",elemento2),
                                                        obtenValor("sede",elemento2),obtenValor("fechainic",elemento2),obtenValor("fechafin",elemento2));
                                                datos.add(animeTmp);

                                        }

                                }


                        }catch(Exception e){
                                e.printStackTrace();
                                sinError = false;
                        }
                        return null;

                }

                @Override
                protected void onPostExecute(String s) {
                        super.onPostExecute(s);
                        if(sinError){
                                for(int i=0;i<datos.size();i++){
                                        Toast.makeText(MainActivity.this, datos.get(i).getIdd(),Toast.LENGTH_SHORT).show();
                                }
                        }else{
                                new AlertDialog.Builder(MainActivity.this)
                                        .setTitle("Aviso")
                                        .setMessage("Servicio no disponible en estos momentos.")
                                        .setPositiveButton("Reintentar", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int which) {
                                                        new ConexionHttp().execute("");
                                                }
                                        }).setOnCancelListener(new DialogInterface.OnCancelListener() {
                                        @Override
                                        public void onCancel(DialogInterface dialog) {
                                                new ConexionHttp().execute("");
                                        }
                                }).setNegativeButton("Salir", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                                finish();
                                        }
                                })
                                        .show();

                        }
                }
        }

        private static String obtenValor(String tag, Element elemento) {
                NodeList listaNodos = elemento.getElementsByTagName(tag).item(0).getChildNodes();
                Node nodo = listaNodos.item(0);
                return nodo.getNodeValue();
        }
}


