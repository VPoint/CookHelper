package com.seg2105a.esther.cookhelper;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by eraji on 2/2/2017.
 */

public class RecipeUnitTest {
    static private RecipeSystem rs;

    // assigning the values
    protected void setUp(){
        RecipeSystem rs = new RecipeSystem();
    }

    // test method to add two values
    public void tearDown(){
        //
    }

    @Test
    public void addRecipeTest() throws Exception {
        Recipe r = new Recipe("title", "description", 0.0, "test/img", 0, 0, rs, new Ingredient("test", rs));
        assertEquals(4, 2 + 2);
    }

    @Test
    public void editRecipeTest() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void deleteRecipeTest() throws Exception {
        assertEquals(4, 2 + 2);
    }

    public void addIngredient() throws Exception {
        assertEquals(4, 2 + 2);
    }
}
