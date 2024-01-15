# team-altas

Project for LaunchCode's Liftoff Program - Eva Group 1 - Atlas

### **Project Name**

### **- Get Cultured**

### **Project Description** 

### **- The project is dedicated to developing an app that allows users to create their own self-guided tours within their respective communities. It solves the problem of avoiding tours that are oversaturated with gimmicks and trends, and allows locals to create tours based on the neat pockets their communities really have to offer.**

### **Project GitHub Link**

### **- <https://github.com/Eva-Gaivoronski/altas>

- **Project Ideas Worksheet:** https\://docs.google.com/document/d/1WFy51f5M2DUsAQviKfw8elA8t6arZ85Fur3Wk2BRBQo/edit?usp=sharing**

### **Base Feature List**

### **- **User Account Creation & Management:** Users can create an account which gives them additional access to areas of the application and abilities depending on their User Group.

- **Full CRUD Functionality:** Verified users can create and modify Tours they author, leave feedback and rate other users’ authored Tours. Admin users can create and modify any Tour in the application; as well as create and modify User data and Filter Tag data.

- **Relational Database:** Stops, Tours, Filters, and Users will all have their own data tables that relate to each other in a multitude of ways.

- **External API Usage:** Google Maps API to bring in interactive maps with locations represented by markers for accessible direction to Tour Stops. **

### **Full Feature List:**

### **- **User System Features**

- Public users can sign up to become Registered users

- Registered users can become Verified users by authenticating their email

- User Groups to define areas of access on site and CRUD abilities within the application

- Implementation of conditional rendering of on-page elements depending on user authorization

- Implementation of Admin Panel for authorized users to give them access to panel where CRUD functionalities for various components of the application can be managed from centrally

- Users will have a public facing page where their user information can be viewed publicly

- Users will have a private profile page where they can view and edit the profile data associated with their user account

- List Tours a User has authored on their public profile

- **Tour/Stop Features**

- Users can create and manage their own Tours

- User submitted Tours have their own pages with their unique information and itinerary displayed; this is what other users will interact with

- Tours must have a defined based location (city)

- Authoring users can add, remove, and modify Stops in a Tour’s itinerary and give ancillary descriptions of each Stop

- Authoring users can add tags to Stops, which are then targeted by the search and filtering system.

- Verified users can leave a review on a Tour that consists of a commentary and user rating

- Users can bookmark specific Tours for future completion

- Verified users can complete Tour Stops asynchronously and track their individual progress by marking each stop as completed

- A Tour with all Stops completed will complete the Tour for a user

- Authoring users can provide a description of a Stop or events occurring the Stops

- Tour pages have a summary map that displays all of the individual map markers from stops in the the tour’s itinerary

- Users will submit some location information about a Stop so it can be queried by the mapping API

- Users can provide lat/long coordinates to define a physical location as a backup

- Stops must have a defined a physical location before they can be committed to an itinerary

- Tours use interactive maps to plot out routes for the sightseeing tours

### **Projected Tech Stack**

### **- Java and Spring for back end

- MySQL for Database 

- Google Maps API for mapping functionality

- React for front end (Stretch goal)**

### **Group Members and Work Division**

### * All Members

- All members will have some responsibility in building out page templates for their feature set

- All members will likely have some involvement in CRUD of certain components and their commitment to a persistent database

* [Alex](https://github.com/AlexMerchant)

    - **(User Creation & Management Requirement)**

    - Public users can sign up to become Registered users

    - Registered users can become Verified users by authenticating their email

    - User Groups to define areas of access on site and CRUD abilities within the application

    - Implementation of conditional rendering of on-page elements depending on user authorization

    - Implementation of Admin Panel for authorized users to give them access to panel where CRUD functionalities for various components of the application can be managed from centrally

    - Users will have a public facing page where their user information can be viewed publicly

    - Users will have a private profile page where they can view and edit the profile data associated with their user account

    - List Tours a User has authored on their public profile

    * [Madeline](https://github.com/MadGalactic)

    **(Full CRUD Requirement)** 

    - Admin users can create/modify/group filtering tags and tag groups 

    - Implementation of Tag Filter system in search operations application giving users ability to initiate a search for a matching location tag or view all tours

      - Ability for users to filter/sort Tours view by categories (Popular, Editor Choices, For Foodies, Historic, Low-key, High Activity, Family Friendly)

      - Ability for verified users to leave comments/feedback on Tours

      - Tours can be tagged with pre-populated categories used for filtering

      - Admin users can create and modify Filter Tags to be applied to Stops

      - Users initiate a search for Tours by specifying a base location (city)

      - Admin users can create and modify Filter Tags to be applied to Stops

      - Tours can be tagged with pre-populated labels used for filtering

      - Users can use filters to narrow search results for Tours in the application

  * [Landon](https://github.com/lhedrick05)

        **(SQL Database Requirement)**
  
      - Primary deployment of the SQL database 

      - Tour data persistence (overall, outside of Stops)

      - Track and store user progress of tours/completion of stops.

      - Adding tags to filter a specific type of stop

      - Creation and management of a tour

      - User feedback provided about a tour

      - Users can bookmark a particular tour

  * [Devon](https://github.com/Cresence)

    **(External API Requirement)**
  
    - Bring in interactive maps with locations from Google API based on user submitted locations. 

    - Validate location data submitted by user so it can be properly used with the mapping API

    - Committing Stop data into the database \*pulled from external API (Google Places API) filtered, sorted and saved into internal database\*
