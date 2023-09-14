package com.sevdindagdelen.sozlukuygulamasi

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class KelimelerAdapter(private val mcontext:Context,private val kelimeler_liste:List<Kelimeler>):
RecyclerView.Adapter<KelimelerAdapter.CardTasarimTutucu>(){

    inner class CardTasarimTutucu(tasarim:View):RecyclerView.ViewHolder(tasarim){

        var kelime_card:CardView=tasarim.findViewById(R.id.kelime_card)
        var kelime_ingilizce:TextView=tasarim.findViewById(R.id.textView_ingilizce)
        var kelime_turkce:TextView=tasarim.findViewById(R.id.textView_turkce)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val tasarim=LayoutInflater.from(mcontext).inflate(R.layout.card_view_tasarim,parent,false)
        return CardTasarimTutucu(tasarim)
    }

    override fun getItemCount(): Int {
        return kelimeler_liste.size
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        val kelime = kelimeler_liste[position]

        holder.kelime_ingilizce.text = kelime.ingilizce
        holder.kelime_turkce.text = kelime.turkce

        holder.kelime_card.setOnClickListener {
            val intent=Intent(mcontext,DetayActivity::class.java)
            intent.putExtra("nesne",kelime)
            mcontext.startActivity(intent)
        }

    }

}