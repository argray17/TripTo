# RedhawkRide Project

RedhawkRide is a ride sharing service that allows students to get rides around 
campus which are fulfilled by other students. Students will sign up for the service 
by using their student id and bank details. Students can make themselves available
to give rides or they can request them. When requesting a ride, students will enter 
their beginning and end locations. The app will provide a cost estimate of the ride.
Then, the student can request the ride once they are content with the price. 
The app shows the student their driver’s name and updated location as they drive.
When picking the student up, the driver starts the ride and drives to the
final location. When the drive is complete, the system will store all information
from the ride and process a bill at the end of the week. 

## What's in the project?
### Packages

The controler package for our project holds the lists and the map and the contorler classes.
Within the list it holds the 'StudentsList.java' and 'TripsList.java' these are responsible for the arrary
lists for what they are named after. The maps files are where the 'TripsMap.java' and 'StudentsMap.java' are.
Finally it also holds the 'RedHawkRideController.java' where it contains the methods that perfrom the operations
of our project.

The data package holds where all the data is stored that would be required for the project to know.
Such as the BankTransactions, Logs, Students, Trips, UnchargedTrips, and ValidStudentIDNums.

The model package holds the location handeling and the money handeling along with the classes 'Student.java'
and 'Trip.java'. Inside the location handeling are the 'Location.java' and 'RouteLog.java'. The Location
file is where the longitude and latitude is held and sent to. The route log is going to hold where the trip
what date it is on and when the trip ended. The money handeling file holds 'AccountBalance.java'
'BankTransaction.java' and 'Money.java'. these files hold what bank information there is and what will be
charged to the student.

The view package holds the 'RedHawkRideUI.java'. This where the user of the program can make decisions on
if they will be the rider or the driver.
