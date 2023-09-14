package com.sevdindagdelen.sozlukuygulamasi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.info.sqlitekullanimihazirveritabani.DatabaseCopyHelper
import com.sevdindagdelen.sozlukuygulamasi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),SearchView.OnQueryTextListener {
    private lateinit var binding: ActivityMainBinding

    private lateinit var kelimelerListe:ArrayList<Kelimeler>
    private lateinit var adapter: KelimelerAdapter
    private lateinit var vt:Veritabaniyardimcisi


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        veritabanikopyalama()

        binding.rv.setHasFixedSize(true)
        binding.rv.layoutManager=LinearLayoutManager(this@MainActivity)

        kelimelerListe=ArrayList()

        /**val k1=Kelimeler(1,"dog","köpek")
        val k2=Kelimeler(1,"fish","balık")
        val k3=Kelimeler(1,"table","masa")

        kelimelerListe.add(k1)
        kelimelerListe.add(k2)
        kelimelerListe.add(k3)*/

        vt= Veritabaniyardimcisi(this@MainActivity)

        kelimelerListe=KelimelerDao().tumKelimeler(vt)

        adapter= KelimelerAdapter(this@MainActivity,kelimelerListe)
        binding.rv.adapter=adapter

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu,menu)
        val item=menu?.findItem(R.id.action_ara)
        val searchview=item?.actionView as SearchView
        searchview.setOnQueryTextListener(this@MainActivity)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        // gönderilen arama
        ara(query)
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        // harf girdikçe yapılan arama
        ara(newText)
        return true
    }

    fun veritabanikopyalama(){
        val copyhelper=DatabaseCopyHelper(this@MainActivity)

        try {
            copyhelper.createDataBase()
            copyhelper.openDataBase()
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    fun ara(arama:String){
        kelimelerListe=KelimelerDao().aramayap(vt,arama)

        adapter= KelimelerAdapter(this@MainActivity,kelimelerListe)
        binding.rv.adapter=adapter
    }
}