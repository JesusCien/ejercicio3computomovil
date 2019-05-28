package com.example.ejercicio3;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ejercicio3.Curso2;
import com.example.ejercicio3.Curso;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity2 extends AppCompatActivity{
    ArrayList<Curso2> cursos2 = new ArrayList<Curso2>();
    Adaptador adaptador;
    private ListView lv;
    ByteArrayInputStream inputStream;
    String idcom,nombrecom,descripcioncom,idcurso;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        /*String valor = getIntent().getStringExtra("id");
        String valor2 = getIntent().getStringExtra("nombre");
        String valor3 = getIntent().getStringExtra("sede");
        String valor4=getIntent().getStringExtra("fechainic");
        String valor5=getIntent().getStringExtra("fechafin");

            Curso cur= new Curso(1,valor,valor2,valor3,valor4,valor5);
            cursos.add(cur);*/
        ArrayList<Curso> cursos = (ArrayList<Curso> ) getIntent().getSerializableExtra("datos");



        adaptador = new Adaptador(this,cursos);

        lv = findViewById(R.id.lv);

        lv.setAdapter(adaptador);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Intent intent1 = new Intent(view.getContext(),MainActivity3.class);
                //EditText tvcursoid.getText(cursos.get(position).getIdd());
                idcurso=Long.toString(id);

                new ConexionHttp().execute("");

                intent1.putExtra("id",idcurso);
                intent1.putExtra("nombre",nombrecom);
                intent1.putExtra("descripcion",descripcioncom);
                startActivity(intent1);
            }
        });



    }
    public class ConexionHttp extends AsyncTask<String, Float, String> {

        boolean sinError = true;

        @Override
        protected String doInBackground(String... strings) {
            try{
                StringBuffer fileData = new StringBuffer(4096);

                URL sourceURL = new URL("https://serverbpw.com/cm/2019-2/curso_desc.php?id="+idcurso);

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
                        Log.d("DATO", "descripcion: " + obtenValor("descripcion",elemento2));
                        idcom=obtenValor("id",elemento2);
                        nombrecom=obtenValor("nombre",elemento2);
                        descripcioncom=obtenValor("descripcion",elemento2);

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
                for(int i=0;i<cursos2.size();i++){
                    Toast.makeText(MainActivity2.this, cursos2.get(i).getIdd(),Toast.LENGTH_SHORT).show();
                }
            }else{
                new AlertDialog.Builder(MainActivity2.this)
                        .setTitle("Aviso")
                        .setMessage("Servicio no disponible en estos momentos.")
                        .setPositiveButton("Reintentar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                new MainActivity2.ConexionHttp().execute("");
                            }
                        }).setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        new MainActivity2.ConexionHttp().execute("");
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




