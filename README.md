# Gaming Achievement & Leaderboard API
Students will build a REST API where users can:
* Register game sessions
* Earn achievements (awards)
* View leaderboards
* Admin can manage achievements and see all statistics
* Regular users only see their own sessions and achievements

And add:
* MySQL
* Controller → Service → Repository
* DTO
* Global error handling
* Roles
* JWT
* Access rules based on role + ownership

## Setup
* **Project:** Maven
* **Language:** Java
* **Spring Boot:** 3.5.8
* **Packaging:** Jar
* **Configuration:** Properties
* **Java:** 17
* **Dependencies:**
  * Spring Data JPA
  * Spring Security
  * Spring Web
  * Validation

## System requirements before security
### Theme
A game system where users:
* Log their game sessions (e.g. points, time played, etc.)
* Get achievements when they reach certain goals
* Can see their position on a leaderboard

Admin can:
* Create new possible achievements
* View statistics of all users
* Manage users' achievements as needed

## Database & Entities
* UserAccount
* AchievementType
* GameSession
* UserAchievement

## REST-Endpoints
### AchievementType (ADMIN SHOULD be able to manage)
* List all
* Get by ID
* Create
* Update
* Delete

### GameSession
* List sessions (admin: all, user: their own)
* Get session by ID
* Create session
* Delete session (only own)

### UserAchievement
(Should be created automatically in the service layer when the points are sufficient)
* List the user's achievements
* Admin should be able to list ALL achievements

### Leaderboard
Endpoint e.g.:
/leaderboard/top
Returns e.g.:
* Top 10 users based on total score

## DTO and layer
* All input/output via DTO
* Controllers must not contain logic
* Service layer should do:
  * logic
  * ownership control
  * updates
  * achievement award

## Validation
Example:
* score must be >= 0
* durationInMinutes > 0
* achievement name must be filled in
* minScore >= 0
Error → return correct JSON error and status code.

## Global Error Handling
Requirements:
* Use @ControllerAdvice
* Catch:
  * ResourceNotFound
  * ValidationException
  * AccessDenied
* Return error JSON + correct status code

## Achievement logic
When a GameSession is created:
* The Service layer should retrieve all achievement types
* Check which ones the user earns based on session.score
* Automatically save new UserAchievements
Return which achievements have been unlocked (optional).

## Security
### Security – Step 1
#### Roles
* ROLE_ADMIN
* ROLE_USER

#### Role behavior
* **ADMIN**
    * Can CRUD AchievementType
    * Can see and delete all sessions
    * Can see all achievements
    * Can see global leaderboard

* **USER**
    * Can create own game sessions
    * Can only see their own sessions
    * Can see: 
    * leaderboard
    * their own achievements

#### Rules
* /public/** = open
* Everything else requires login
Test:
* public endpoints work without login
* protected ones require credentials
* Basic Auth works

### Security – Step 2 (Role control & ownership)
When handling sessions:
* Admin → full control
* User → only their sessions
A user tries to:
* Delete someone else's session
* See someone else's details
→ return 403 Forbidden
Same for UserAchievements:
* User only sees theirs
* Admin sees all

### Security – Step 3 (JWT)
When Basic Auth works:
Login endpoint
* Accepts username + password
* Returns JWT
* Token payload should contain role
JWT requirements
* Bearer token is required for all protected endpoints
* /auth/login and /public/** should be open
* Security chain should use JWT filter, not Basic Auth