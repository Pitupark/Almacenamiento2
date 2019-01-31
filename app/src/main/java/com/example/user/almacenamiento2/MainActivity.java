package com.example.user.almacenamiento2;

import android.app.Activity;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    EditText et1,et2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1=(EditText)findViewById(R.id.et1);
        et2=(EditText)findViewById(R.id.et2);
    }
    public void Guardar(View v){

        String nombre=et1.getText().toString();
        String contenido=et2.getText().toString();

        try{

            File tarjetaSD = Environment.getExternalStorageDirectory();

            Toast.makeText(this, tarjetaSD.getPath(), Toast.LENGTH_SHORT).show();

            File rutaArchivo= new File(tarjetaSD.getPath(),nombre);
            OutputStreamWriter crearArchivo= new OutputStreamWriter(openFileOutput(nombre, Activity.MODE_PRIVATE));
            crearArchivo.write(contenido);
            crearArchivo.flush();
            crearArchivo.close();

            Toast.makeText(this, "Guardado correctamente", Toast.LENGTH_SHORT).show();

            et1.setText("");
            et2.setText("");

        }catch (IOException e){

            Toast.makeText(this, "No se pudo guardar", Toast.LENGTH_SHORT).show();
        }

    }
    public void Cargar(View v){
        String nombre=et1.getText().toString();
        try{

            File tarjetaSD = Environment.getExternalStorageDirectory();

            File rutaArchivo=new File(tarjetaSD.getPath(), nombre);

            InputStreamReader abrirArchivo= new InputStreamReader(openFileInput(nombre));

            BufferedReader leerArchivo=new BufferedReader(abrirArchivo);

            String lines= leerArchivo.readLine();

            String contenidoCompleto=" ";

            while(lines != null){
                contenidoCompleto=contenidoCompleto + lines + "\n";

                lines=leerArchivo.readLine();
            }
            leerArchivo.close();
            abrirArchivo.close();
            et2.setText(contenidoCompleto);

        }catch (IOException e){

            Toast.makeText(this, "Error al leer el archivo", Toast.LENGTH_SHORT).show();
        }
    }
    public void Borrar(View v){
        String nombre=et1.getText().toString();
        String contenido=et2.getText().toString();

        File tarjetaSD = Environment.getExternalStorageDirectory();

        File rutaArchivo= new File(tarjetaSD.getPath(),nombre);

        if(rutaArchivo.exists()){
            rutaArchivo.delete();
            tarjetaSD.delete();
        }
        Toast.makeText(this, "Archivo Borrado", Toast.LENGTH_SHORT).show();
            et1.setText(" ");
            et2.setText(" ");

    }
}


