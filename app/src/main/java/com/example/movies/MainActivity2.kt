package com.example.movies

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        var nameTextView: TextView = findViewById(R.id.nameTextView)
        var anioTextView: TextView = findViewById(R.id.anioTextView)
        var duracionTextView: TextView = findViewById(R.id.duracionTextView)
        var descripcion: TextView = findViewById(R.id.descripcionTextView)
        var imageView: ImageView = findViewById(R.id.imageView)

        var name: String? = intent.getStringExtra("name")
        var release: String? = intent.getStringExtra("release")
        var playtime: String? = intent.getStringExtra("playtime")
        var description: String? = intent.getStringExtra("description")
        var imageUrl: String? = intent.getStringExtra("imageUrl")

        nameTextView.setText(name)
        anioTextView.setText(release)
        duracionTextView.setText(playtime)
        descripcion.setText(description)
        //imageView.setImageURI(imageUrl)
        Picasso.get()
            .load(Uri.parse(imageUrl))
            .resize(200, 200)
            .centerInside()
            .placeholder(R.drawable.camera_image)
            .into(imageView)
    }
}