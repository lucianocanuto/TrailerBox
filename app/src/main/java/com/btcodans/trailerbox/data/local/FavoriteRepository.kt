package com.btcodans.trailerbox.data.local

import android.content.ContentValues
import android.content.Context
import com.btcodans.trailerbox.data.models.Movie

class FavoriteRepository(context: Context) {
    private val db = MovieDatabase(context).writableDatabase

    fun add(movie: Movie) {
        val cv = ContentValues()
        cv.put("id", movie.id)
        cv.put("title", movie.title)
        cv.put("poster_path", movie.poster_path)
        cv.put("overview", movie.overview)
        cv.put("vote_average", movie.vote_average)

        db.insert("favorites", null, cv)
    }

    fun remove(id: Int) {
        db.delete("favorites", "id=?", arrayOf(id.toString()))
    }

    fun isFavorite(id: Int): Boolean {
        val cursor = db.rawQuery(
            "SELECT id FROM favorites WHERE id=?", arrayOf(id.toString())
        )
        val exists = cursor.moveToFirst()
        cursor.close()
        return exists
    }

    fun getAll(): List<Movie> {
        val list = mutableListOf<Movie>()
        val cursor = db.rawQuery("SELECT * FROM favorites", null)

        if (cursor.moveToFirst()) {
            do {
                val movie = Movie(
                    id = cursor.getInt(0),
                    title = cursor.getString(1),
                    poster_path = cursor.getString(2),
                    overview = cursor.getString(3),
                    vote_average = cursor.getString(4)
                )
                list.add(movie)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return list
    }
}