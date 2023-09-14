package com.sevdindagdelen.sozlukuygulamasi

class KelimelerDao {

    fun tumKelimeler(vt:Veritabaniyardimcisi):ArrayList<Kelimeler>{
        val kelimelistesi=ArrayList<Kelimeler>()

        val db=vt.writableDatabase
        val c=db.rawQuery("SELECT * FROM kelimeler",null)

        val idIndex=c.getColumnIndex("kelime_id")
        val ingilizceIndex=c.getColumnIndex("ingilizce")
        val turkceIndex=c.getColumnIndex("turkce")

        while (c.moveToNext()){

            val kelime=Kelimeler(c.getInt(idIndex),c.getString(ingilizceIndex),c.getString(turkceIndex))
            kelimelistesi.add(kelime)

        }

        return kelimelistesi
    }

    fun aramayap(vt:Veritabaniyardimcisi,aramakelime:String):ArrayList<Kelimeler>{
        val kelimelistesi=ArrayList<Kelimeler>()

        val db=vt.writableDatabase
        val c=db.rawQuery("SELECT * FROM kelimeler WHERE ingilizce LIKE '%$aramakelime%'",null)

        val idIndex=c.getColumnIndex("kelime_id")
        val ingilizceIndex=c.getColumnIndex("ingilizce")
        val turkceIndex=c.getColumnIndex("turkce")

        while (c.moveToNext()){

            val kelime=Kelimeler(c.getInt(idIndex),c.getString(ingilizceIndex),c.getString(turkceIndex))
            kelimelistesi.add(kelime)

        }

        return kelimelistesi
    }
}