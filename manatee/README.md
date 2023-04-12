# Manatee API

The following API is made for educational purposes only and does not provide any meaningful functionalities.

## Getting started

This project requires Java 17 or a newer version to be installed on the machine.
For developers, Amazon Coretta or Eclipse Termium are recommended JDKs.

For development purposes only, the relational H2 database is initialized in the local runtime.
On the shutdown, the database is torn down. There is no other option to set a persistent database.

### For Linux users (bash)

```bash
./gradlew build # Generates OpenAPI models, builds the application and runs tests.
./gradlew bootRun # Starts the application on a local network. 
```

### For Windows users

```bash
gradlew build # Generates OpenAPI models, builds the application and runs tests.
gradlew bootRun # Starts the application on a local network. 
```


# Summary
| Question                                 | Answer                                                                                                                                                                         |
|------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Time  spent (h)                          | 10h                                                                                                                                                                            |
| Hardest task, (with reasoning)           | I would say Task 1 - I have yet use a .yaml file so getting familiar with it was the toughest part. Other than that, it was just a question of getting into development rhythm |
| Uncompleted tasks, if any                | None                                                                                                                                                                           |
| Additional dependencies (with reasoning) | No additional dependencies                                                                                                                                                     | 


In summary, describe your overall experience with the topic, what you learned,
and any technical challenges you encountered. Your answer should be
between 50-100 words.

SUMMARY:
Overall, the experience was good. I have developed Spring Boot applications and API-s before, so the overall
structure I was familiar with. StateMachines and OpenAI were the newer tools. However,
with a bit studying, I learned it wasn't that hard at all (in this context atleast) and once I got into a good rhythm, the developing
process was fairly smooth. I encountered some problems with Entity dependencies, since it is hard to visualize 
a database and its connections when they're just written as classes with annotated fields. The biggest bug I had 
to fix was saving the interview properly tied to the application when scheduling an interview, but Cascade ALL does the trick.
Altogether, I enjoyed this test exercise thoroughly.

For testing reasons, I added extra functionality by creating an API endpoint to retrieve all interviews separately.
