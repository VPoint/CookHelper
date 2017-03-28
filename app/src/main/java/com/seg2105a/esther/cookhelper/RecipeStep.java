/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-abedcd4 modeling language!*/

package com.seg2105a.esther.cookhelper;

public class RecipeStep {

    //------------------------
    // MEMBER VARIABLES
    //------------------------

    //RecipeStep Attributes
    private int number;
    private String description;
    private double timeRequired;
    private boolean completed;

    //RecipeStep Associations
    private Recipe partOf;

    //------------------------
    // CONSTRUCTOR
    //------------------------

    public RecipeStep(int aNumber, String aDescription, double aTimeRequired, boolean aCompleted, Recipe aPartOf) {
        number = aNumber;
        description = aDescription;
        timeRequired = aTimeRequired;
        completed = aCompleted;
        boolean didAddPartOf = setPartOf(aPartOf);
        if (!didAddPartOf) {
            throw new RuntimeException("Unable to create recipeStep due to partOf");
        }
    }

    //------------------------
    // INTERFACE
    //------------------------

    public boolean setNumber(int aNumber) {
        boolean wasSet = false;
        number = aNumber;
        wasSet = true;
        return wasSet;
    }

    public boolean setDescription(String aDescription) {
        boolean wasSet = false;
        description = aDescription;
        wasSet = true;
        return wasSet;
    }

    public boolean setTimeRequired(double aTimeRequired) {
        boolean wasSet = false;
        timeRequired = aTimeRequired;
        wasSet = true;
        return wasSet;
    }

    public boolean setCompleted(boolean aCompleted) {
        boolean wasSet = false;
        completed = aCompleted;
        wasSet = true;
        return wasSet;
    }

    public int getNumber() {
        return number;
    }

    public String getDescription() {
        return description;
    }

    public double getTimeRequired() {
        return timeRequired;
    }

    public boolean getCompleted() {
        return completed;
    }

    public Recipe getPartOf() {
        return partOf;
    }

    public boolean setPartOf(Recipe aPartOf) {
        boolean wasSet = false;
        //Must provide partOf to recipeStep
        if (aPartOf == null) {
            return wasSet;
        }

        if (partOf != null && partOf.numberOfRecipeSteps() <= Recipe.minimumNumberOfRecipeSteps()) {
            return wasSet;
        }

        Recipe existingPartOf = partOf;
        partOf = aPartOf;
        if (existingPartOf != null && !existingPartOf.equals(aPartOf)) {
            boolean didRemove = existingPartOf.removeRecipeStep(this);
            if (!didRemove) {
                partOf = existingPartOf;
                return wasSet;
            }
        }
        partOf.addRecipeStep(this);
        wasSet = true;
        return wasSet;
    }

    public void delete() {
        Recipe existingPartOf = partOf;
        partOf = null;
        if (existingPartOf != null) {
            existingPartOf.delete();
        }
    }


    public String toString() {
        return getNumber() + " : " + getDescription();
    }
}