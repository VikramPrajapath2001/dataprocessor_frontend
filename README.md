 Interview Scheduler 

This application is designed to help the Talent Acquisition team to help them screen, interview and shortlist candidates based on their skillset. We will need to design and develop the API’s created using the above-listed technologies and call these API’s using Postman. 

Option for the recruiter to input candidate name, email-id, phone number and able to upload the resume – This will upload all the details into the application and return the candidate id. 

Recruiters should be able to search for the candidate by name, email-id or phone number and display their details. 

Option to schedule the interview and recruiters should be able to enter the details of interview like the date, time, rounds (1st, 2nd …) along with the name, designation, and email id of the interviewer. 

Able to list all the interviews conducted by date, round, or interviewer name. 

Should be able to update the final feedback based on the interview status – Selected, Rejected, On Hold, Recommended Designation & Remarks, if any. 

If the candidate is selected, the recruiter should be able to filter and search for any specific names and update the candidate status of Offer Letter released or Pending. 

The above 6 API’s need to be developed and be able to pass and fetch data accordingly. 

Data processor 

This application will help to parse the complex JSON format into readable and storable data into the database. The input for the application will be a complex JSON like below and the objective is to parse this JSON effectively and help in effective querying of data from the database. 

college : { 

name : "London College" 

address : "Birmingham" 

departments : [ 

{ 

"name" : "CSE" 

"HOD" : "John" 

classes : [ 

{ 

name : "Batch-1" 

staffName : "Jacob" 

capacity : 40 

students  : [ 

{ 

name : "Ganesh" 

dateOfBirth: "10-Apr-1990" 

} 

] 

} 

] 

} 

] 

} 
We will need to design and develop the API’s created using the above-listed technologies and call these API’s using Postman. 

Read and parse the sample data as shared above for storing into database. 

Once the data is parsed, retrieved and effectively stored into the database, we should be able to perform the below actions. 

List all the colleges 

List all the departments 

List all the classes under each department 

List all the students in each class 

Able to search and find for a specific student and display the details. 
