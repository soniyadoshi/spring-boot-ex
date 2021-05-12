## Running CARTA Vesting Program Instructions

1. Unzip folder
2. open terminal
3. go to folder /vesting/
5. Run command ->  ./mvnw spring-boot:run
6. Open browser and type http://localhost:8080

### vesting program details
* This program takes csv file as input on the UI and uploads and saves all the data from the file to the DB
and shows uploaded data on UI grid.
* After an upload, you can enter target date and scale and filter the input to see the results on the grid.
* This program is designed to process and filter one file at a time. Uploading new file will result in deletion of previous data file.

### Assumptions
* Upload of csv file will not round up fractional quantities, It will be rounded only in filter request as scale input will be considered in the filter request. 
