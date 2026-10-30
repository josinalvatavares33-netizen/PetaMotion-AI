package com.petamotion.ai

import android.app.Activity
import android.os.Bundle
import android.content.Intent
import android.graphics.Color
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

class MainActivity : Activity() {

    private val PICK_PHOTO = 100
    private val PICK_VIDEO = 200

    private var photoSelected = false
    private var videoSelected = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(40, 40, 40, 40)
            setBackgroundColor(Color.rgb(12, 12, 20))
        }

        val title = TextView(this).apply {
            text = "⚡ PetaMotion AI"
            textSize = 28f
            setTextColor(Color.WHITE)
            gravity = Gravity.CENTER
        }

        val subtitle = TextView(this).apply {
            text = "Transforme fotos em vídeos com IA"
            textSize = 16f
            setTextColor(Color.LTGRAY)
            gravity = Gravity.CENTER
            setPadding(0, 20, 0, 50)
        }

        val photoButton = createButton("📸 ADICIONAR FOTO")

        photoButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
                type = "image/*"
            }
            startActivityForResult(intent, PICK_PHOTO)
        }

        val videoButton = createButton("🎬 ADICIONAR VÍDEO")

        videoButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
                type = "video/*"
            }
            startActivityForResult(intent, PICK_VIDEO)
        }

        val generateButton = createButton("✨ GERAR VÍDEO COM IA")

        generateButton.setOnClickListener {
            if (!photoSelected || !videoSelected) {
                Toast.makeText(
                    this,
                    "Selecione uma foto e um vídeo!",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                Toast.makeText(
                    this,
                    "IA ainda não conectada. Próxima etapa!",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        layout.addView(title)
        layout.addView(subtitle)
        layout.addView(photoButton)
        layout.addView(videoButton)
        layout.addView(generateButton)

        setContentView(layout)
    }

    private fun createButton(label: String): Button {
        return Button(this).apply {
            text = label
            textSize = 16f
            setTextColor(Color.WHITE)
            setBackgroundTintList(
                android.content.res.ColorStateList.valueOf(
                    Color.rgb(125, 40, 230)
                )
            )
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                150
            )
            params.setMargins(0, 15, 0, 15)
            layoutParams = params
        }
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK && data?.data != null) {
            when (requestCode) {
                PICK_PHOTO -> {
                    photoSelected = true
                    Toast.makeText(this, "Foto selecionada!", Toast.LENGTH_SHORT).show()
                }
                PICK_VIDEO -> {
                    videoSelected = true
                    Toast.makeText(this, "Vídeo selecionado!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
