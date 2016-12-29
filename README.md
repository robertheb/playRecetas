# Play Framework API Recetas MIMO2016

API to store and search recipes. 

Working with Play Framework Activator-1.3.12-minimal (Java) and H2 database.

# API Methods

You can obtain the information returned from calls to the API in XML or JSON format.
To get XML response, you need to specify in header: accept: application/xml
To get JSON response: accept: application/json

### -   Return a list of recipes.
-   Method: **GET**
-   http://ec2-35-156-213-232.eu-central-1.compute.amazonaws.com:9000/recipes

### -   Return the  recipe with the given id
-   Method: **GET** 
-   http://ec2-35-156-213-232.eu-central-1.compute.amazonaws.com:9000/recipe/:id

### -   Return the recipe with the given name
-   Method: **GET**
-   http://ec2-35-156-213-232.eu-central-1.compute.amazonaws.com:9000/recipe/name/:name

### -   Create a new recipe
-   Method: **POST**
-   http://ec2-35-156-213-232.eu-central-1.compute.amazonaws.com:9000/recipes
-     Body: 

         {
        	"title": "Pato a la pequinesa",
        	"description": "Crujiente pato a la pequinesa",
        	"preparedTime": "35",
        	"ingredients": [{
        		"name": "Pato",
        		"description": "Soy un pato"
        	}, {
        		"name": "Verdura",
        		"description": "Para las vacas"
        	}, {
        		"name": "Agua",
        		"description": "Del grifo"
        	}],
        	"tags": [
        		"tag1",
        		"tag2"
        	]
        }

### -   Remove the recipe with the given id
-   Method: **DELETE**
-   http://ec2-35-156-213-232.eu-central-1.compute.amazonaws.com:9000/recipe/:id

### -   Update the recipe with the given id
-   Method: **PUT**
-   http://ec2-35-156-213-232.eu-central-1.compute.amazonaws.com:9000/recipe/:id
-       Body:
        Actualizamos los ingredientes del recipe con id 3:

        http://ec2-35-156-213-232.eu-central-1.compute.amazonaws.com:9000/recipe/3
        {
        	"ingredients": [{
        		"name": "Pato",
        		"description": "Soy un pato"
        	}, {
        		"name": "Grillos",
        		"description": "Campo"
        	}]
        }

### -   Return a list of recipes with the tag specified in url
-   Method: **GET**
-   http://ec2-35-156-213-232.eu-central-1.compute.amazonaws.com:9000/recipe/tag/:name

### Autores

- Francisco Ildefonso Dominguez Losada.
- Roberto Martin Grande.

