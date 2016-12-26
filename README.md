# Play Framework API Recetas MIMO2016

API to store and search recipes. 

Working with Play Framework Activator-1.3.12-minimal (Java) and H2 database.

# API Methods

You can obtain the information returned from calls to the API in XML or JSON format.
To get XML response, you need to specify in header: accept: application/xml
To get JSON response: accept: application/json

### -   Return a list of recipes.
-   Method: **GET**
-   URL /recipes
-   Examlpe: 

### -   Return the  recipe with the given id
-   Method: **GET** 
-   URL /recipe/:id
-   Examlpe: 

### -   Return the recipe with the given name
-   Method: **GET**
-   URL /recipe/name/:name
-   Examlpe: 
### -   Create a new recipe
-   Method: **POST**
-   URL /recipes
-   Examlpe: 
-     Body:

### -   Remove the recipe with the given id
-   Method: **DELETE**
-   URL /recipe/:id
-   Examlpe: 
-       Body:

### -   Update the recipe with the given id
-   Method: **PUT**
-   URL /recipe/:id
-   Examlpe: 

### -   Return a list of recipes with the tag specified in url
-   Method: **GET**
-   URL /recipe/tag/:name
-   Examlpe: