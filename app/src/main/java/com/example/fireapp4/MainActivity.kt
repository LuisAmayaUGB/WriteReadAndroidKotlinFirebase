package com.example.fireapp4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var dbReference: DatabaseReference
    private lateinit var firebaseDatabase: FirebaseDatabase



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnguardar.setOnClickListener {


            Guardar("Paciente2","ApePaciente2" )


        }



        btnleer.setOnClickListener {
            leer()
        }

    }



    fun Guardar(nombre: String, apellidos: String) {
        val student = HashMap<String, String>()
        student["NombrePaciente"] = nombre
        student["Apellidos"] = apellidos.toString()
        student["Edad"] = nombre.toString()
        student["Sexo"] = "78787500000"
        val rootRef = FirebaseDatabase.getInstance().reference
        val tasksRef = rootRef.child("Citas").push()
        tasksRef.setValue(student)
    }






    fun leer(){

        val myRef = FirebaseDatabase.getInstance().reference
        val query: Query = myRef.child("Citas").orderByChild("NombrePaciente").equalTo("Paciente2")
        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (citi in dataSnapshot.children) {
                        txtver.setText(citi.value.toString())
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })



    }


}