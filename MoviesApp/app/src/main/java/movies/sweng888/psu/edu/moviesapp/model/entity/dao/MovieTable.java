package movies.sweng888.psu.edu.moviesapp.model.entity.dao;

/**
 * @author ezt157
 * A contract class is a container for constants that define names for URIs, tables, and columns.
 * The contract class allows you to use the same constants across all the other classes in the same
 * package. Therefore, you can change a column name in one place and have it propagate throughout
 * your code.
 */
public class MovieTable {

    /** Defining the Table Content **/
    public static final String TABLE_NAME = "movie";
    public static final String COLUMN_NAME_ID = "id";
    public static final String COLUMN_NAME_TITLE = "title";
    public static final String COLUMN_NAME_CATEGORY = "rating";
    public static final String COLUMN_NAME_RATING = "category";
    public static final String COLUMN_NAME_YEAR = "year";

    public static String create(){
        return new String ( "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_NAME_TITLE + " TEXT," +
                COLUMN_NAME_CATEGORY  + " TEXT," +
                COLUMN_NAME_RATING + " TEXT," +
                COLUMN_NAME_YEAR + " TEXT)" );
    }

    public static String select(){
        return new String("SELECT * FROM "+TABLE_NAME);

    }

    public static final String delete(){
        return "DROP TABLE IF EXISTS " +TABLE_NAME;
    }
}




