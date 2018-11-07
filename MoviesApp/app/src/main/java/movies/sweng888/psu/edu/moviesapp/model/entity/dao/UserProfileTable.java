package movies.sweng888.psu.edu.moviesapp.model.entity.dao;

import java.util.Date;

public class UserProfileTable {

    private String name;
    private String surname;
    private String username;
    private String phone; // allows null
    private String email;
    private String password;
    private Date birthday; // allows null

    /** Defining the Table Content **/
    public static final String TABLE_NAME = "user_profile";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME_ = "name";
    public static final String COLUMN_SURNAME = "surname";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_BIRTHDAY = "birthday";

    public static String create(){
        return new String ( "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_NAME_ + " TEXT," +
                COLUMN_SURNAME  + " TEXT," +
                COLUMN_USERNAME + " TEXT," +
                COLUMN_PHONE + " TEXT," +
                COLUMN_EMAIL + " TEXT," +
                COLUMN_PASSWORD + " TEXT," +
                COLUMN_BIRTHDAY + " TEXT)" );
    }

    public static String select(){
        return new String("SELECT * FROM "+TABLE_NAME);

    }

    public static final String delete(){
        return "DROP TABLE IF EXISTS " +TABLE_NAME;
    }
}
