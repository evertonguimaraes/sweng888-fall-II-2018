package movies.sweng888.psu.edu.moviesapp.model.entity.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import movies.sweng888.psu.edu.moviesapp.model.entity.entity.Movie;

/**
 * @author ezt157
 * It is responsible for implementing the database operations for a given table.
 */
public class PersistenceMovie implements IPersistence{

    public DatabaseAccess databaseAccess;

    public PersistenceMovie(Context context){
        this.databaseAccess = new DatabaseAccess(context);
    }

    @Override
    public void insert(Object o) {

        // Cast the generic object to have access to the movie info.
        Movie movie = (Movie) o;

        SQLiteDatabase sqLiteDatabase = databaseAccess.getWritableDatabase();

        // The ContentValues object create a map of values, where the columns are the keys
        ContentValues contentValues = new ContentValues();
        contentValues.put(MovieTable.COLUMN_NAME_TITLE, movie.getTitle());
        contentValues.put(MovieTable.COLUMN_NAME_CATEGORY, movie.getCategory());
        contentValues.put(MovieTable.COLUMN_NAME_RATING, movie.getRating());
        contentValues.put(MovieTable.COLUMN_NAME_YEAR, movie.getYear());

        // Insert the ContentValues into the Movie table.
        sqLiteDatabase.insert(MovieTable.TABLE_NAME, null, contentValues);

        sqLiteDatabase.close();
    }

    @Override
    public void delete(Object o) {

        Movie movie = (Movie) o;

        // Define which column will be the parameter for deleting the record.
        String selection = MovieTable.COLUMN_NAME_TITLE + "LIKE ? ";

        // Arguments must be identidied in the placehold order
        String [] selectionArgs = { movie.getTitle().trim() };

        // Get database instance
        SQLiteDatabase sqLiteDatabase = databaseAccess.getWritableDatabase();
        sqLiteDatabase.delete(MovieTable.TABLE_NAME, selection, selectionArgs);
    }

    @Override
    public void edit(Object o) {
        // TODO for the students to practice
    }

    @Override
    public ArrayList getDataFromDB() {

        // Create ArrayList of movies
        ArrayList<Movie> movies = null;

        // Instatiate the database.
        SQLiteDatabase sqLiteDatabase = databaseAccess.getWritableDatabase();

        // Gather all the records found for the MOVIE table.
        Cursor cursor = sqLiteDatabase.rawQuery(MovieTable.select(), null);

        // It will iterate since the first record gathered from the database.
        cursor.moveToFirst();

        // Check if there exist other records in the cursor
        movies = new ArrayList<>();

        if(cursor != null && cursor.moveToFirst()){

            do {
                String title = cursor.getString(cursor.getColumnIndex(MovieTable.COLUMN_NAME_TITLE));
                String category = cursor.getString(cursor.getColumnIndex(MovieTable.COLUMN_NAME_CATEGORY));
                String rating = cursor.getString(cursor.getColumnIndex(MovieTable.COLUMN_NAME_RATING));
                String year = cursor.getString(cursor.getColumnIndex(MovieTable.COLUMN_NAME_YEAR));

                // Convert to Movie object.
                Movie movie = new Movie(title, category, rating, year);
                movies.add(movie);

            } while (cursor.moveToNext()) ;
        }

        return movies;
    }
}
