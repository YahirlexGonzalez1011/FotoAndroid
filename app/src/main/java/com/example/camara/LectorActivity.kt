package com.example.camara

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.integration.android.IntentIntegrator

class LectorActivity : AppCompatActivity() {
    //Instancias
    private lateinit var codigo: EditText
    private lateinit var descripcion: EditText
    private lateinit var btnEscanear: Button
    private lateinit var btnCapturar: Button
    private lateinit var btnLimpiar: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lector)
//Asociar con componente gráfico
        codigo = findViewById(R.id.edtResultado)
        descripcion = findViewById(R.id.edtDescripcion)
        btnEscanear = findViewById(R.id.btnScan)
        btnCapturar = findViewById(R.id.btnRegistrar)
        btnLimpiar = findViewById(R.id.btnLimpiar)
//Eventos
        btnEscanear.setOnClickListener { escanearCodigo() }
        btnCapturar.setOnClickListener {
            if (codigo.text.toString().isNotEmpty() &&
                descripcion.text.toString().isNotEmpty()) {
                Toast.makeText(this, "Datos capturados",
                    Toast.LENGTH_SHORT).show()
                limpiar()
            } else {
                Toast.makeText(this, "Debe registrar datos",
                    Toast.LENGTH_LONG).show()
            }
        }//btnCapturar
        btnLimpiar.setOnClickListener { limpiar() }
    }//onCreate
    private fun escanearCodigo() {
//Instancia para leer códigos
        val intentIntegrator = IntentIntegrator(this@LectorActivity)
//Definir el tipo de codigo a leer cualquier formato de código
        intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
        intentIntegrator.setPrompt("Lector de códigos") //Titulo en cámara
        intentIntegrator.setCameraId(0) //Definir cámara frontal
        intentIntegrator.setBeepEnabled(true) //emitir beep al tomar la foto
        intentIntegrator.setBarcodeImageEnabled(true) //almacenar el código leído

        intentIntegrator.initiateScan() //iniciar escaneo
    } //escanearCodigo
    //Método para determinar que hacer después de leer el código
    override fun onActivityResult(requestCode: Int, resultCode: Int, data:
    Intent?) {
//Instancia para recibir el resultado (lectura de código)
        val intentResult = IntentIntegrator.parseActivityResult(requestCode,
            resultCode, data)
//Validar que no este vacía
        if (intentResult != null) {
//Validar leyo información
            if (intentResult.contents == null) {
//Mensaje informativo - no hubo datos
                Toast.makeText(this, "Lectura cancelada",
                    Toast.LENGTH_SHORT).show()
            } else {
//Mensaje informativo - si hubo datos
                Toast.makeText(this, "Código leído",
                    Toast.LENGTH_SHORT).show()
//Colocar el código en la caja de texto
                codigo.setText(intentResult.contents)
            } //if-else == null
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        } //if-else != null
    } //onActivityResult
    private fun limpiar() {
        codigo.setText("")
        descripcion.setText("")
        codigo.requestFocus()
    } //limpiar
}//class