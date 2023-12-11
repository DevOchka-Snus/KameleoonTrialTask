# KameleoonTrialTask
# Project requirements:
- Docker image was published on Dockerhub (gonefladvedotov/kameleoon);
- project should have docker-compose.yaml for local deploy, OR you can deploy it on a public hosting provider (Heroku, etc);
- use H2 database;
- use Java and Spring Boot.

# The API should allow:
- CRUD operations of a user account. User properties are name, email, password and date of creation;
- addition, viewing (including a method to get a random quote), modification, and deletion of quotes (real deletion from the database, not just via an archive flag). Quote properties include content, date of creation / update, link to user who posted it, link to votes;
- voting on quotes (either upvote or downvote);
- view of the top and worse 10 quotes and the details of each quote.
