package com.sevdindagdelen.sozlukuygulamasi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sevdindagdelen.sozlukuygulamasi.databinding.ActivityDetayBinding
import com.sevdindagdelen.sozlukuygulamasi.databinding.ActivityMainBinding

class DetayActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetayBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDetayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val kelime=intent.getSerializableExtra("nesne") as Kelimeler

        binding.textViewIngilizce.text=kelime.ingilizce
        binding.textviewTurkce.text=kelime.turkce

    }
}