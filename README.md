# Twilio-Autopilot

### 1. Description 
### 2. Restrictions

### 3. Design
This is an application developed using Java 11 and Spring Boot

#### 3.1 Sequence diagram

![Diagram](diagram.png)

#### 3.2 Twilio task
```json
{
   "actions": [
      {
         "collect": {
            "name": "favorite_movie",
            "questions": [
               {
                  "question": "Hello, I'm TriviaMovies! \n You could prove how fan you are. \n What is your favorite movie???",
                  "name": "movie_name",
                  "type": "",
                  "validate": {
                     "on_failure": {
                        "messages": [
                           {
                              "say": "Sorry, that's not a valid movie."
                           },
                           {
                              "say": "Hmm, I'm not understanding."
                           }
                        ],
                        "repeat_question": true
                     },
                     "webhook": {
                        "method": "POST",
                        "url": "https://lmpp-movie-bot.herokuapp.com/v1/autopilot/validateMovie"
                     },
                     "on_success": {
                        "say": "Great, It's time to prove how fan you are!!"
                     },
                     "max_attempts": {
                        "redirect": "task://having-trouble",
                        "num_attempts": 3
                     }
                  }
               }
            ],
            "on_complete": {
               "redirect": {
                  "method": "POST",
                  "uri": "https://lmpp-movie-bot.herokuapp.com/v1/autopilot/questions"
               }
            }
         }
      }
   ]
}
```

### 4. Endpoints

Yo could access to  the endpoint in the swagger page
https://lmpp-movie-bot.herokuapp.com/swagger-ui/index.html


