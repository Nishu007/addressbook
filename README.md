# addressbook
*  JSON format to be put in request body for POST

	{
        "house_number": "1012", 
        "street_name": "E univ dr", 
        "city": "Tempe",
        "state": "AZ",
        "zipcode": "85281", 
        "phone": "6506604797"
      }

 Example URL for POST: http://localhost:4567/contact/john56



* JSON format to be put in request body for PUT

	{
        "house_number": "1012", 
        "street_name": "E univ dr", 
        "city": "Tempe",
        "state": "AZ",
        "zipcode": "85281", 
        "phone": "6506604797"
      }

 Example URL for PUT: http://localhost:4567/contact/john56



* Example URL for GET: http://localhost:4567/contact/john56



* Example URL for DELETE: http://localhost:4567/contact/john56



* Example URL for GET with pagewise display of data: 

	http://localhost:4567/contact?page=1&size=5&query=zipcode,85281

	I have made query parameter as shown in url rather than queryStringQuery.


Note: POSTMAN is used for all requests for convenience. Also attribute length contraints are imposed.

*  *   ContactService.java is the file with all REST methods and main function.
