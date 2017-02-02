package com.seg2105a.esther.cookhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Esther on 2016-12-02.
 */

public class SQLiteHelper extends SQLiteOpenHelper {
    // We are creating a java file called SQLiteHelper and extending SQLiteOpenHelper class and It is used to create a bridge between android and SQLite.
    //  To perform basic SQL operations we need to extend SQLiteOpenHelper class.

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "RecipeDatabase";

    // Table Names
    private static final String TABLE_RECIPE = "recipe";
    private static final String TABLE_CATEGORY = "category";
    private static final String TABLE_TYPE = "type";
    private static final String TABLE_INGREDIENT = "type";
    private static final String TABLE_RELATIONSHIP = "relationship";

    // Common column names
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_CREATED_AT = "created_at";

    // RECIPE Table - column names
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_SERVING = "serving";
    private static final String KEY_IMAGE = "image";
    private static final String KEY_RECIPESTEP = "recipe_step";
    private static final String KEY_COOKINGTIME = "cooking_time";

    // RELATIONSHIP Table - column names
    private static final String KEY_RECIPE_ID = "recipe_id";
    private static final String KEY_ATTRIBUTE_ID = "attr_id";

    // Table Create Statements
    // Recipe table create statement
    private static final String CREATE_TABLE_RECIPE = "CREATE TABLE "
            + TABLE_RECIPE + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TITLE + " TEXT, "
            + KEY_DESCRIPTION + " VARCHAR, " + KEY_COOKINGTIME + " VARCHAR, " + KEY_SERVING + "VARCHAR,"
            + KEY_IMAGE +" VARCHAR, " + KEY_RECIPESTEP + " VARCHAR)," + KEY_CREATED_AT
            + " DATETIME" + ")";

    // Category table create statement
    private static final String CREATE_TABLE_CATEGORY = "CREATE TABLE "+ TABLE_CATEGORY+" ( "
            + KEY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_TITLE +" TEXT, " + KEY_CREATED_AT
            + " DATETIME" + ")";

    // Type table create statement
    private static final String CREATE_TABLE_TYPE = "CREATE TABLE " + TABLE_TYPE + "( "
            + KEY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_TITLE +" TEXT, " + KEY_CREATED_AT
            + " DATETIME" + ")";

    // Ingredient table create statement
    private static final String CREATE_TABLE_INGREDIENT = "CREATE TABLE " + TABLE_INGREDIENT + "( "
            + KEY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_TITLE +" TEXT, " + KEY_CREATED_AT
            + " DATETIME" + ")";

    // Relationship table create statement
    private static final String CREATE_TABLE_RELATIONSHIP = "CREATE TABLE " + TABLE_RELATIONSHIP + " ( "+
            KEY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_RECIPE_ID +" INTEGER, "
            + KEY_ATTRIBUTE_ID +" INTEGER, "+ KEY_CREATED_AT
            + " DATETIME" + ")";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_RECIPE);
        db.execSQL(CREATE_TABLE_CATEGORY);
        db.execSQL(CREATE_TABLE_TYPE);
        db.execSQL(CREATE_TABLE_INGREDIENT);
        db.execSQL(CREATE_TABLE_RELATIONSHIP);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPE);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_CATEGORY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TYPE);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_INGREDIENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RELATIONSHIP);

        onCreate(db);
    }

    private SQLiteDatabase database;
    /*
 * Creating a Recipe
 */
    public long createRecipe(Recipe recipe, long[] category_id, long[] type_id, long[] ingredient_id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, recipe.getTitle());
        values.put(KEY_DESCRIPTION, recipe.getDescription());
        values.put(KEY_DESCRIPTION, recipe.getDescription());
        values.put(KEY_COOKINGTIME, recipe.getCookingTime());
        values.put(KEY_SERVING, recipe.getServing());
        values.put(KEY_RECIPESTEP, recipe.getRecipeSteps().toString());
        values.put(KEY_IMAGE, recipe.getImage());
        values.put(KEY_CREATED_AT, getDateTime());

        // insert row
        long recipe_id = db.insert(TABLE_RECIPE, null, values);

        // assigning attr to relationships
        for (long this_id : category_id) {
            createRelationship(recipe_id, this_id);
        }
        for (long this_id : type_id) {
            createRelationship(recipe_id, this_id);
        }
        for (long this_id : ingredient_id) {
            createRelationship(recipe_id, this_id);
        }

        return recipe_id;
    }

    /*
     * Creating todo_tag
     */
    public long createRelationship(long recipe_id, long attr_id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_RECIPE_ID, recipe_id);
        values.put(KEY_ATTRIBUTE_ID, attr_id);
        values.put(KEY_CREATED_AT, getDateTime());

        long id = db.insert(TABLE_RELATIONSHIP, null, values);

        return id;
    }


    public void insertCategory(Category category) {
        database = this.getReadableDatabase();
        database.execSQL("INSERT INTO "+ TABLE_CATEGORY +" ("+ KEY_TITLE +") VALUES('" + category.getName() + "')");
        database.close();
    }

    public void insertType(RecipeType type) {
        database = this.getReadableDatabase();
        database.execSQL("INSERT INTO "+ TABLE_TYPE +" ("+ KEY_TITLE +") VALUES('" + type.getName() + "')");
        database.close();
    }

    public void insertTIngredient(Ingredient ingredient) {
        database = this.getReadableDatabase();
        database.execSQL("INSERT INTO "+ TABLE_TYPE +" ("+ KEY_TITLE +") VALUES('" + ingredient.getName() + "')");
        database.close();
    }

    public void updateRecipe(Recipe recipe) {
        database = this.getReadableDatabase();
        //database.execSQL("update " + TABLE_NAME + " set " + COLUMN_FIRST_NAME + " = '" + contact.getFirstName() + "', " + COLUMN_LAST_NAME + " = '" + contact.getLastName() + "' where " + COLUMN_ID + " = '" + contact.getID() + "'");
        database.close();
    }

    public void deleteRecipe(Recipe recipe) {
        database = this.getReadableDatabase();
        //database.execSQL("delete from " + TABLE_NAME + " where " + COLUMN_ID + " = '" + contact.getID() + "'");
        database.close();
    }

    public ArrayList<Recipe> getAllRecipes() {
        database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT  * FROM " + TABLE_RECIPE, null);

        ArrayList<Recipe> recipes = new ArrayList<Recipe>();

        if (cursor.moveToFirst()) {
            do {
                Recipe r = new Recipe();
                r.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                r.setTitle((cursor.getString(cursor.getColumnIndex(KEY_TITLE))));
                r.setCookingTime(Double.parseDouble(cursor.getString(cursor.getColumnIndex(KEY_COOKINGTIME))));
                r.setDescription((cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION))));
                r.setImage((cursor.getString(cursor.getColumnIndex(KEY_IMAGE))));
                r.setServing(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_SERVING))));
                //r.setRecipeStep((cursor.getString(cursor.getColumnIndex(KEY_RECIPESTEP))));
                //r.setCreatedAt(cursor.getString(cursor.getColumnIndex(KEY_CREATED_AT)));

                // adding to recipe list
                recipes.add(r);
            } while (cursor.moveToNext());
        }

        return recipes;
    }

    /**
     * get single recipe
     */
    public Recipe getRecipe(long recipe_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_RECIPE + " WHERE "
                + KEY_ID + " = " + recipe_id;

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Recipe r = new Recipe();
        r.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        r.setTitle((c.getString(c.getColumnIndex(KEY_TITLE))));
        r.setCookingTime(Double.parseDouble(c.getString(c.getColumnIndex(KEY_COOKINGTIME))));
        r.setDescription((c.getString(c.getColumnIndex(KEY_DESCRIPTION))));
        r.setImage((c.getString(c.getColumnIndex(KEY_IMAGE))));
        r.setServing(Integer.parseInt(c.getString(c.getColumnIndex(KEY_SERVING))));
        //r.setRecipeStep((c.getString(c.getColumnIndex(KEY_RECIPESTEP))));
        //r.setCreatedAt(c.getString(c.getColumnIndex(KEY_CREATED_AT)));

        return r;
    }

    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

    /**
     * get datetime
     * */
    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}