# Home Project Blog

*Purpose:* to share useful information about the technologies used in the project and support this information with a demonstration on the project.

The blog will have a production deployment on heroku. The application will be API based, which means that it will be written separately and consists of UI which will go to the server where the backend with your application will be deployed. 

It will be able to register in the application. It will also need to implement roles, at least ADMIN, MODERATOR, BLOGGER. It will also be useful to tag posts so that you can separate them in some context.

As an additional task, it will be interesting to implement the ability to add attachments to posts (pictures, gifs, videos, ...) to fill posts not only with text but also with more interesting media. 

And of course, there is nothing without comment, I'm sure it's a must-have feature for any modern application.


## Role Matrix
We have the following roles defined in the application:
- __ADMIN__ - The most powerful role which includes possibility to manage users.
- __MODERATOR__ - The role that can be used for managing existing posts and comments.
- __BLOGGER__ - The default role for any registered user.
- __ANY__ - Any possible user of the application including unregistered ones.

Resources in the application must be restricted via the following role matrix.
<details>
  <summary>Click to expand!</summary>
  
| Action                                             | ADMIN | MODERATOR | BLOGGER | ANY |
|:---------------------------------------------------|:-----:|:---------:|:-------:|:---:|
| *__Current User__*                                 |       |           |         |     |
| See information about current User.                | V     | V         | V       |     |
| Update information connected to current User.      | V     | V         | V       |     |
| *__Comments of Current User__*                     |       |           |         |     |
| See all comments assosiated with current user.     | V     | V         | V       |     |
| See specific comment assosiated with current user. | V     | V         | V       |     |
| Update Comment assosiated with current user.       | V     | V         | V       |     |
| Delete Comment assosiated with current user.       | V     | V         | V       |     |
| *__Posts of Current User__*                        |       |           |         |     |
| See all posts assosiated with current user.        | V     | V         | V       |     |
| See specific post assosiated with current user.    | V     | V         | V       |     |
| Update Post assosiated with current user.          | V     | V         | V       |     |
| Delete Post assosiated with current user.          | V     | V         | V       |     |
| *__User management__*                              |       |           |         |     |
| Create new User.                                   | V     | V         | V       | V   |
| See all users.                                     | V     |           |         |     |
| See information about specific User.               | V     |           |         |     |
| Update User information.                           | V     |           |         |     |
| Delete User.                                       | V     |           |         |     |
| See User Role.                                     | V     |           |         |     |
| Update User Role.                                  | V     |           |         |     |
| *__Posts__*                                        |       |           |         |     |
| Create new Post.                                   | V     | V         | V       |     |
| See all posts.                                     | V     | V         | V       | V   |
| See information about specific Post.               | V     | V         | V       | V   |
| Update Post information.                           | V     | V         |         |     |
| Delete Post.                                       | V     | V         |         |     |
| *__Comments__*                                     |       |           |         |     |
| Create new Comment.                                | V     | V         | V       |     |
| See all comments.                                  | V     | V         | V       | V   |
| See information about specific Comment.            | V     | V         | V       | V   |
| Update Comment information.                        | V     | V         |         |     |
| Delete Comment.                                    | V     | V         |         |     |
| *__Tags__*                                         |       |           |         |     |
| See all tags.                                      | V     | V         | V       | V   |
| See information about specific Tag.                | V     | V         | V       | V   |
| Delete Tag.                                        | V     | V         |         |     |
  
</details>
