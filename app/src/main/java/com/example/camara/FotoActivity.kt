package com.example.camara

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class FotoActivity : AppCompatActivity() {
    //Instancias
    private lateinit var foto: ImageView
    private lateinit var btnTomar: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_foto)
//Asociar con instancia
        foto = findViewById(R.id.imgFoto)
        btnTomar = findViewById(R.id.btnTomar)
//Mëtodos
        btnTomar.setOnClickListener {
//Intancia para abrir la cámara
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//Lo que sucede cuando la cámara regresa un resultado
            responseLauncher.launch(intent)
        }
    }//onCreate
    //Variable que se ejecuta una vez que tome la foto
    private val responseLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ activityResult ->
            if(activityResult.resultCode == RESULT_OK){
                Toast.makeText(this, "Fotografía tomada!!!",
                    Toast.LENGTH_SHORT).show()
                val extras = activityResult.data!!.extras
                val imgBitmap = extras!!["data"] as Bitmap?
                foto.setImageBitmap(imgBitmap)
            } else {
                Toast.makeText(this, "Proceso cancelado",
                    Toast.LENGTH_SHORT).show()
            }
        }//responseLauncher
}//class