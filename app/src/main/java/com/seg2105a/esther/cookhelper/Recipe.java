    /*PLEASE DO NOT EDIT THIS CODE*/
    /*This code was generated using the UMPLE 1.24.0-abedcd4 modeling language!*/
    package com.seg2105a.esther.cookhelper;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-fcfceb9 modeling language!*/


    import java.util.*;

    // line 31 "model.ump"
// line 79 "model.ump"
    public class Recipe
    {

        //------------------------
        // MEMBER VARIABLES
        //------------------------

        //Recipe Attributes
        private String title;
        private String description;
        private double cookingTime;
        private String image;
        private int serving;
        private int id;
        private int calories;
        private float rating;

        //Recipe Associations
        private RecipeSystem recipeSystem;
        private List<RecipeStep> recipeSteps;
        private List<Category> categories;
        private List<RecipeType> recipeTypes;
        private List<Ingredient> usedIn;

        //------------------------
        // CONSTRUCTOR
        //------------------------

        public Recipe(String aTitle, String aDescription, double aCookingTime, String aImage, int aServing, int aCalories, RecipeSystem aRecipeSystem, Ingredient... allUsedIn)
        {
            title = aTitle;
            description = aDescription;
            cookingTime = aCookingTime;
            image = aImage;
            serving = aServing;
            calories = aCalories;
            rating = 0;
            boolean didAddRecipeSystem = setRecipeSystem(aRecipeSystem);
            if (!didAddRecipeSystem)
            {
                throw new RuntimeException("Unable to create hasA due to recipeSystem");
            }
            recipeSteps = new ArrayList<RecipeStep>();
            categories = new ArrayList<Category>();
            recipeTypes = new ArrayList<RecipeType>();
            usedIn = new ArrayList<Ingredient>();
            boolean didAddUsedIn = setUsedIn(allUsedIn);
            if (!didAddUsedIn)
            {
                throw new RuntimeException("Unable to create Recipe, must have at least 1 usedIn");
            }
        }

        public Recipe(){
            title = "";
            description = "";
            cookingTime = 0;
            image = "";
            serving = 0;
            id = 0;
            calories = 0;
            recipeSystem = null;
            recipeSteps = new ArrayList<RecipeStep>();
            categories = new ArrayList<Category>();
            recipeTypes = new ArrayList<RecipeType>();
            usedIn = new ArrayList<Ingredient>();
        }

        //------------------------
        // INTERFACE
        //------------------------

        public boolean setTitle(String aTitle)
        {
            boolean wasSet = false;
            title = aTitle;
            wasSet = true;
            return wasSet;
        }

        public boolean setDescription(String aDescription)
        {
            boolean wasSet = false;
            description = aDescription;
            wasSet = true;
            return wasSet;
        }

        public boolean setCookingTime(double aCookingTime)
        {
            boolean wasSet = false;
            cookingTime = aCookingTime;
            wasSet = true;
            return wasSet;
        }

        public boolean setImage(String aImage)
        {
            boolean wasSet = false;
            image = aImage;
            wasSet = true;
            return wasSet;
        }

        public boolean setServing(int aServing)
        {
            boolean wasSet = false;
            serving = aServing;
            wasSet = true;
            return wasSet;
        }

        public boolean setId(int aId)
        {
            boolean wasSet = false;
            id = aId;
            wasSet = true;
            return wasSet;
        }

        public boolean setCalories(int aCalories)
        {
            boolean wasSet = false;
            calories = aCalories;
            wasSet = true;
            return wasSet;
        }

        public String getTitle()
        {
            return title;
        }

        public String getDescription()
        {
            return description;
        }

        public double getCookingTime()
        {
            return cookingTime;
        }

        public String getImage()
        {
            return image;
        }

        public int getServing()
        {
            return serving;
        }

        public int getId()
        {
            return id;
        }

        public int getCalories()
        {
            return calories;
        }

        public RecipeSystem getRecipeSystem()
        {
            return recipeSystem;
        }

        public RecipeStep getRecipeStep(int index)
        {
            RecipeStep aRecipeStep = recipeSteps.get(index);
            return aRecipeStep;
        }

        public List<RecipeStep> getRecipeSteps()
        {
            List<RecipeStep> newRecipeSteps = Collections.unmodifiableList(recipeSteps);
            return newRecipeSteps;
        }

        public int numberOfRecipeSteps()
        {
            int number = recipeSteps.size();
            return number;
        }

        public boolean hasRecipeSteps()
        {
            boolean has = recipeSteps.size() > 0;
            return has;
        }

        public int indexOfRecipeStep(RecipeStep aRecipeStep)
        {
            int index = recipeSteps.indexOf(aRecipeStep);
            return index;
        }

        public Category getCategory(int index)
        {
            Category aCategory = categories.get(index);
            return aCategory;
        }

        public List<Category> getCategories()
        {
            List<Category> newCategories = Collections.unmodifiableList(categories);
            return newCategories;
        }

        public int numberOfCategories()
        {
            int number = categories.size();
            return number;
        }

        public boolean hasCategories()
        {
            boolean has = categories.size() > 0;
            return has;
        }

        public int indexOfCategory(Category aCategory)
        {
            int index = categories.indexOf(aCategory);
            return index;
        }

        public RecipeType getRecipeType(int index)
        {
            RecipeType aRecipeType = recipeTypes.get(index);
            return aRecipeType;
        }

        public List<RecipeType> getRecipeTypes()
        {
            List<RecipeType> newRecipeTypes = Collections.unmodifiableList(recipeTypes);
            return newRecipeTypes;
        }

        public int numberOfRecipeTypes()
        {
            int number = recipeTypes.size();
            return number;
        }

        public boolean hasRecipeTypes()
        {
            boolean has = recipeTypes.size() > 0;
            return has;
        }

        public int indexOfRecipeType(RecipeType aRecipeType)
        {
            int index = recipeTypes.indexOf(aRecipeType);
            return index;
        }

        public Ingredient getUsedIn(int index)
        {
            Ingredient aUsedIn = usedIn.get(index);
            return aUsedIn;
        }

        public List<Ingredient> getUsedIn()
        {
            List<Ingredient> newUsedIn = Collections.unmodifiableList(usedIn);
            return newUsedIn;
        }

        public int numberOfUsedIn()
        {
            int number = usedIn.size();
            return number;
        }

        public boolean hasUsedIn()
        {
            boolean has = usedIn.size() > 0;
            return has;
        }

        public int indexOfUsedIn(Ingredient aUsedIn)
        {
            int index = usedIn.indexOf(aUsedIn);
            return index;
        }

        public boolean setRecipeSystem(RecipeSystem aRecipeSystem)
        {
            boolean wasSet = false;
            if (aRecipeSystem == null)
            {
                return wasSet;
            }

            RecipeSystem existingRecipeSystem = recipeSystem;
            recipeSystem = aRecipeSystem;
            if (existingRecipeSystem != null && !existingRecipeSystem.equals(aRecipeSystem))
            {
                existingRecipeSystem.removeRecipe(this);
            }
            recipeSystem.addRecipe(this);
            wasSet = true;
            return wasSet;
        }

        public boolean isNumberOfRecipeStepsValid()
        {
            boolean isValid = numberOfRecipeSteps() >= minimumNumberOfRecipeSteps();
            return isValid;
        }

        public static int minimumNumberOfRecipeSteps()
        {
            return 1;
        }

        public RecipeStep addRecipeStep(int aNumber, String aDescription, double aTimeRequired, boolean aCompleted)
        {
            RecipeStep aNewRecipeStep = new RecipeStep(aNumber, aDescription, aTimeRequired, aCompleted, this);
            return aNewRecipeStep;
        }

        public boolean addRecipeStep(RecipeStep aRecipeStep)
        {
            boolean wasAdded = false;
            if (recipeSteps.contains(aRecipeStep)) { return false; }
            Recipe existingPartOf = aRecipeStep.getPartOf();
            boolean isNewPartOf = existingPartOf != null && !this.equals(existingPartOf);

            if (isNewPartOf && existingPartOf.numberOfRecipeSteps() <= minimumNumberOfRecipeSteps())
            {
                return wasAdded;
            }
            if (isNewPartOf)
            {
                aRecipeStep.setPartOf(this);
            }
            else
            {
                recipeSteps.add(aRecipeStep);
            }
            wasAdded = true;
            return wasAdded;
        }

        public boolean removeRecipeStep(RecipeStep aRecipeStep)
        {
            boolean wasRemoved = false;
            //Unable to remove aRecipeStep, as it must always have a partOf
            if (this.equals(aRecipeStep.getPartOf()))
            {
                return wasRemoved;
            }

            //partOf already at minimum (1)
            if (numberOfRecipeSteps() <= minimumNumberOfRecipeSteps())
            {
                return wasRemoved;
            }

            recipeSteps.remove(aRecipeStep);
            wasRemoved = true;
            return wasRemoved;
        }

        public boolean addRecipeStepAt(RecipeStep aRecipeStep, int index)
        {
            boolean wasAdded = false;
            if(addRecipeStep(aRecipeStep))
            {
                if(index < 0 ) { index = 0; }
                if(index > numberOfRecipeSteps()) { index = numberOfRecipeSteps() - 1; }
                recipeSteps.remove(aRecipeStep);
                recipeSteps.add(index, aRecipeStep);
                wasAdded = true;
            }
            return wasAdded;
        }

        public boolean addOrMoveRecipeStepAt(RecipeStep aRecipeStep, int index)
        {
            boolean wasAdded = false;
            if(recipeSteps.contains(aRecipeStep))
            {
                if(index < 0 ) { index = 0; }
                if(index > numberOfRecipeSteps()) { index = numberOfRecipeSteps() - 1; }
                recipeSteps.remove(aRecipeStep);
                recipeSteps.add(index, aRecipeStep);
                wasAdded = true;
            }
            else
            {
                wasAdded = addRecipeStepAt(aRecipeStep, index);
            }
            return wasAdded;
        }

        public static int minimumNumberOfCategories()
        {
            return 0;
        }

        public boolean addCategory(Category aCategory)
        {
            boolean wasAdded = false;
            if (categories.contains(aCategory)) { return false; }
            categories.add(aCategory);
            if (aCategory.indexOfHasA(this) != -1)
            {
                wasAdded = true;
            }
            else
            {
                wasAdded = aCategory.addHasA(this);
                if (!wasAdded)
                {
                    categories.remove(aCategory);
                }
            }
            return wasAdded;
        }

        public boolean removeCategory(Category aCategory)
        {
            boolean wasRemoved = false;
            if (!categories.contains(aCategory))
            {
                return wasRemoved;
            }

            int oldIndex = categories.indexOf(aCategory);
            categories.remove(oldIndex);
            if (aCategory.indexOfHasA(this) == -1)
            {
                wasRemoved = true;
            }
            else
            {
                wasRemoved = aCategory.removeHasA(this);
                if (!wasRemoved)
                {
                    categories.add(oldIndex,aCategory);
                }
            }
            return wasRemoved;
        }

        public boolean addCategoryAt(Category aCategory, int index)
        {
            boolean wasAdded = false;
            if(addCategory(aCategory))
            {
                if(index < 0 ) { index = 0; }
                if(index > numberOfCategories()) { index = numberOfCategories() - 1; }
                categories.remove(aCategory);
                categories.add(index, aCategory);
                wasAdded = true;
            }
            return wasAdded;
        }

        public boolean addOrMoveCategoryAt(Category aCategory, int index)
        {
            boolean wasAdded = false;
            if(categories.contains(aCategory))
            {
                if(index < 0 ) { index = 0; }
                if(index > numberOfCategories()) { index = numberOfCategories() - 1; }
                categories.remove(aCategory);
                categories.add(index, aCategory);
                wasAdded = true;
            }
            else
            {
                wasAdded = addCategoryAt(aCategory, index);
            }
            return wasAdded;
        }

        public static int minimumNumberOfRecipeTypes()
        {
            return 0;
        }

        public boolean addRecipeType(RecipeType aRecipeType)
        {
            boolean wasAdded = false;
            if (recipeTypes.contains(aRecipeType)) { return false; }
            recipeTypes.add(aRecipeType);
            if (aRecipeType.indexOfHasA(this) != -1)
            {
                wasAdded = true;
            }
            else
            {
                wasAdded = aRecipeType.addHasA(this);
                if (!wasAdded)
                {
                    recipeTypes.remove(aRecipeType);
                }
            }
            return wasAdded;
        }

        public boolean removeRecipeType(RecipeType aRecipeType)
        {
            boolean wasRemoved = false;
            if (!recipeTypes.contains(aRecipeType))
            {
                return wasRemoved;
            }

            int oldIndex = recipeTypes.indexOf(aRecipeType);
            recipeTypes.remove(oldIndex);
            if (aRecipeType.indexOfHasA(this) == -1)
            {
                wasRemoved = true;
            }
            else
            {
                wasRemoved = aRecipeType.removeHasA(this);
                if (!wasRemoved)
                {
                    recipeTypes.add(oldIndex,aRecipeType);
                }
            }
            return wasRemoved;
        }

        public boolean addRecipeTypeAt(RecipeType aRecipeType, int index)
        {
            boolean wasAdded = false;
            if(addRecipeType(aRecipeType))
            {
                if(index < 0 ) { index = 0; }
                if(index > numberOfRecipeTypes()) { index = numberOfRecipeTypes() - 1; }
                recipeTypes.remove(aRecipeType);
                recipeTypes.add(index, aRecipeType);
                wasAdded = true;
            }
            return wasAdded;
        }

        public boolean addOrMoveRecipeTypeAt(RecipeType aRecipeType, int index)
        {
            boolean wasAdded = false;
            if(recipeTypes.contains(aRecipeType))
            {
                if(index < 0 ) { index = 0; }
                if(index > numberOfRecipeTypes()) { index = numberOfRecipeTypes() - 1; }
                recipeTypes.remove(aRecipeType);
                recipeTypes.add(index, aRecipeType);
                wasAdded = true;
            }
            else
            {
                wasAdded = addRecipeTypeAt(aRecipeType, index);
            }
            return wasAdded;
        }

        public boolean isNumberOfUsedInValid()
        {
            boolean isValid = numberOfUsedIn() >= minimumNumberOfUsedIn();
            return isValid;
        }

        public static int minimumNumberOfUsedIn()
        {
            return 1;
        }

        public boolean addUsedIn(Ingredient aUsedIn)
        {
            boolean wasAdded = false;
            if (usedIn.contains(aUsedIn)) { return false; }
            usedIn.add(aUsedIn);
            if (aUsedIn.indexOfRecipe(this) != -1)
            {
                wasAdded = true;
            }
            else
            {
                wasAdded = aUsedIn.addRecipe(this);
                if (!wasAdded)
                {
                    usedIn.remove(aUsedIn);
                }
            }
            return wasAdded;
        }

        public boolean removeUsedIn(Ingredient aUsedIn)
        {
            boolean wasRemoved = false;
            if (!usedIn.contains(aUsedIn))
            {
                return wasRemoved;
            }

            if (numberOfUsedIn() <= minimumNumberOfUsedIn())
            {
                return wasRemoved;
            }

            int oldIndex = usedIn.indexOf(aUsedIn);
            usedIn.remove(oldIndex);
            if (aUsedIn.indexOfRecipe(this) == -1)
            {
                wasRemoved = true;
            }
            else
            {
                wasRemoved = aUsedIn.removeRecipe(this);
                if (!wasRemoved)
                {
                    usedIn.add(oldIndex,aUsedIn);
                }
            }
            return wasRemoved;
        }

        public boolean setUsedIn(Ingredient... newUsedIn)
        {
            boolean wasSet = false;
            ArrayList<Ingredient> verifiedUsedIn = new ArrayList<Ingredient>();
            for (Ingredient aUsedIn : newUsedIn)
            {
                if (verifiedUsedIn.contains(aUsedIn))
                {
                    continue;
                }
                verifiedUsedIn.add(aUsedIn);
            }

            if (verifiedUsedIn.size() != newUsedIn.length || verifiedUsedIn.size() < minimumNumberOfUsedIn())
            {
                return wasSet;
            }

            ArrayList<Ingredient> oldUsedIn = new ArrayList<Ingredient>(usedIn);
            usedIn.clear();
            for (Ingredient aNewUsedIn : verifiedUsedIn)
            {
                usedIn.add(aNewUsedIn);
                if (oldUsedIn.contains(aNewUsedIn))
                {
                    oldUsedIn.remove(aNewUsedIn);
                }
                else
                {
                    aNewUsedIn.addRecipe(this);
                }
            }

            for (Ingredient anOldUsedIn : oldUsedIn)
            {
                anOldUsedIn.removeRecipe(this);
            }
            wasSet = true;
            return wasSet;
        }

        public boolean addUsedInAt(Ingredient aUsedIn, int index)
        {
            boolean wasAdded = false;
            if(addUsedIn(aUsedIn))
            {
                if(index < 0 ) { index = 0; }
                if(index > numberOfUsedIn()) { index = numberOfUsedIn() - 1; }
                usedIn.remove(aUsedIn);
                usedIn.add(index, aUsedIn);
                wasAdded = true;
            }
            return wasAdded;
        }

        public boolean addOrMoveUsedInAt(Ingredient aUsedIn, int index)
        {
            boolean wasAdded = false;
            if(usedIn.contains(aUsedIn))
            {
                if(index < 0 ) { index = 0; }
                if(index > numberOfUsedIn()) { index = numberOfUsedIn() - 1; }
                usedIn.remove(aUsedIn);
                usedIn.add(index, aUsedIn);
                wasAdded = true;
            }
            else
            {
                wasAdded = addUsedInAt(aUsedIn, index);
            }
            return wasAdded;
        }

        public void delete()
        {
            RecipeSystem placeholderRecipeSystem = recipeSystem;
            this.recipeSystem = null;
            placeholderRecipeSystem.removeRecipe(this);
            while (recipeSteps.size() > 0)
            {
                RecipeStep aRecipeStep = recipeSteps.get(recipeSteps.size() - 1);
                aRecipeStep.delete();
                recipeSteps.remove(aRecipeStep);
            }

            ArrayList<Category> copyOfCategories = new ArrayList<Category>(categories);
            categories.clear();
            for(Category aCategory : copyOfCategories)
            {
                aCategory.removeHasA(this);
            }
            ArrayList<RecipeType> copyOfRecipeTypes = new ArrayList<RecipeType>(recipeTypes);
            recipeTypes.clear();
            for(RecipeType aRecipeType : copyOfRecipeTypes)
            {
                aRecipeType.removeHasA(this);
            }
            ArrayList<Ingredient> copyOfUsedIn = new ArrayList<Ingredient>(usedIn);
            usedIn.clear();
            for(Ingredient aUsedIn : copyOfUsedIn)
            {
                aUsedIn.removeRecipe(this);
            }
        }

        public float getRating() {
            return rating;
        }

        public void setRating(float rating) {
            this.rating = rating;
        }

        public String toString()
        {
            String outputString = "";
            return super.toString() + "["+
                    "title" + ":" + getTitle()+ "," +
                    "description" + ":" + getDescription()+ "," +
                    "cookingTime" + ":" + getCookingTime()+ "," +
                    "image" + ":" + getImage()+ "," +
                    "serving" + ":" + getServing()+ "," +
                    "id" + ":" + getId()+ "," +
                    "calories" + ":" + getCalories()+ "]" + System.getProperties().getProperty("line.separator") +
                    "  " + "recipeSystem = "+(getRecipeSystem()!=null?Integer.toHexString(System.identityHashCode(getRecipeSystem())):"null")
                    + outputString;
        }
    }