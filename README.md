# Task_Management_1.0.2

### Ticket Breakdown: Task Management System with Thymeleaf Integration (Controller Focused)

---

#### **Ticket 1: Set Up Spring Boot Project**

**Tasks:**
1. Create a new Spring Boot project using Spring Initializr or your preferred method.
2. Add the following dependencies:
   - Spring Web (for building REST APIs and serving HTML pages with Thymeleaf)
   - Spring Data JPA (for database interactions)
   - Thymeleaf (for dynamic HTML templating)
   - MySQL Driver (to connect to the MySQL database)
   - Lombok (optional for minimizing boilerplate code)

3. Initialize version control:
   - Initialize a Git repository.
   - Add a `.gitignore` file to exclude target/, .idea/, .mvn/, and *.class files.
   - Commit the initial project setup.

4. Configure MySQL in `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/task_management_db
   spring.datasource.username=root
   spring.datasource.password=your_password
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   ```

5. Create the MySQL database using MySQL Workbench:
   ```sql
   CREATE DATABASE task_management_db;
   ```

**Hints:**
- After creating the database, ensure that the Spring Boot project connects to it successfully by running the application.
  
---

#### **Ticket 2: Create the Task Entity**

**Tasks:**
1. Create a `Task` entity class with the following fields:
   - `id` (Primary key, auto-generated using `@Id` and `@GeneratedValue`)
   - `title` (a String for task titles)
   - `description` (a String for task descriptions)
   - `status` (a String for task status such as "Pending", "In Progress", or "Completed")
   
2. Annotate the class with `@Entity` to map it to the database.

**Hints:**
- Use `@Id` and `@GeneratedValue` for the primary key.
- Ensure you define `@Column` annotations if needed (e.g., if setting length constraints).

---

#### **Ticket 3: Create the TaskRepository Interface**

**Tasks:**
1. Create a repository interface by extending `JpaRepository<Task, Long>`. This will automatically generate CRUD operations for the `Task` entity.

**Hints:**
- This interface will handle operations like saving, updating, finding, and deleting tasks.

---

#### **Ticket 4: Create the TaskService Class**

**Tasks:**
1. Create a `TaskService` class to implement business logic for task management.
2. Implement the following methods:
   - `getAllTasks()` to fetch all tasks.
   - `getTaskById(Long id)` to retrieve a task by its ID.
   - `createTask(Task task)` to create a new task.
   - `updateTask(Long id, Task updatedTask)` to update an existing task.
   - `deleteTask(Long id)` to delete a task by its ID.

**Hints:**
- Use `taskRepository.findById(id)` to retrieve a task.
- Use `taskRepository.save(task)` to save and update tasks.

---

#### **Ticket 5: Create the TaskController Class with Thymeleaf**

In this ticket, you'll implement the core of the Task Management application, focusing on how the controller interacts with Thymeleaf for rendering dynamic pages.

---

**Step 1: Implement the Task List Page (GET /tasks)**

1. In the controller, create a method to handle requests to `/tasks`. This method will display all tasks.

   **Tasks:**
   - Fetch all tasks using the service method `getAllTasks()`.
   - Add the list of tasks to the model using `model.addAttribute()`.
   - Return the Thymeleaf template `task-list.html` (this template will show the list of tasks).

   **Hints:**
   - You'll use `Model.addAttribute()` to add the list of tasks to the model.
   - Inside the Thymeleaf template, loop through the tasks and display them.

---

**Step 2: Implement the Task Creation Form (GET /tasks/new)**

2. In the controller, create a method to display the form for creating a new task.

   **Tasks:**
   - Create a new, empty `Task` object.
   - Add the empty task to the model using `model.addAttribute()`.
   - Return the Thymeleaf template `task-form.html`.

   **Hints:**
   - Use `model.addAttribute("task", task)` to pass the new task object to the form.
   - The Thymeleaf form will bind to this object using `th:object`.

---

**Step 3: Implement the Task Edit Form (GET /tasks/edit/{id})**

3. In the controller, create a method to edit an existing task.

   **Tasks:**
   - Fetch the task by its ID using the service method `getTaskById(id)`.
   - Add the task to the model and return `task-form.html`.
   - If the task is not found, redirect back to the list of tasks.

   **Hints:**
   - Use `@PathVariable` to extract the task ID from the URL.
   - Use `Optional<Task>` for handling tasks that may not exist.

---

**Step 4: Implement Task Creation and Update (POST /tasks/save)**

4. In the controller, create a method to handle form submission for creating or updating a task.

   **Tasks:**
   - Bind the form data to the `Task` object using `@ModelAttribute`.
   - If the task has an ID, update the task; otherwise, create a new task.
   - After saving or updating the task, redirect to `/tasks`.

   **Hints:**
   - Use `@ModelAttribute("task")` to bind the form data to the task object.
   - Use `taskService.updateTask()` for updates and `taskService.createTask()` for new tasks.

---

**Step 5: Implement Task Deletion (GET /tasks/delete/{id})**

5. In the controller, create a method to delete a task by its ID.

   **Tasks:**
   - Use `@PathVariable` to extract the ID from the URL.
   - Use the service method `deleteTask(id)` to delete the task.
   - Redirect to `/tasks` after deletion.

---

**Thymeleaf Template Hints (Fill in the Thymeleaf Expressions):**

For the Thymeleaf templates, you’ll need to fill in certain expressions. Below are the placeholders for the parts you'll need to complete.

**task-list.html:**
```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Task List</title>
    <link rel="stylesheet" th:href="@{/css/styles.css}" />
</head>
<body>
<h1>Task List</h1>
<div th:each="task : ${tasks}">
    <div class="task">
        <h2 th:text="____"></h2> <!-- Fill in to display task title -->
        <p th:text="____"></p>  <!-- Fill in to display task description -->
        <p>Status: <span th:text="____"></span></p>  <!-- Fill in to display task status -->
        <a th:href="____">Edit</a>  <!-- Fill in to generate the edit link -->
        <a th:href="____">Delete</a>  <!-- Fill in to generate the delete link -->
    </div>
</div>
<a href="/tasks/new">Create New Task</a>
</body>
</html>
```

**task-form.html:**
```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Create or Edit Task</title>
    <link rel="stylesheet" th:href="@{/css/styles.css}" />
</head>
<body>
<h1 th:if="____">Edit Task</h1> <!-- Add condition for when the task has an ID -->
<h1 th:if="____">Create Task</h1> <!-- Add condition for when the task is new -->

<form th:action="____" th:object="____" method="post"> <!-- Fill in the form action and object binding -->
    <input type="hidden" th:field="____" /> <!-- Hidden field for the ID -->
    
    <label>Title:</label>
    <input type="text" th:field="____" /><br /> <!-- Field for the task title -->

    <label>Description:</label>
    <input type="text" th:field="____" /><br /> <!-- Field for the task description -->

    <label>Status:</label>
    <select th:field="____"> <!-- Dropdown for task status -->
        <option value="Pending">Pending</option>
        <option value="In Progress">In Progress</option>
        <option value="Completed">Completed</option>
    </select><br />

    <button type="submit">Save</button>
</form>

<a href="/tasks">Back to Task List</a>
</body>
</html>
```

**Hints for Thymeleaf Expressions:**
- Use `${task.title}`, `${task.description}`, `${task.status}`, and `${task.id}` in the placeholders.
- The form’s action should be bound to `/tasks/save`.
- The form’s object should be bound to the `task` object using `th:object="${task}"`.

---

#### **Ticket 6: Test the Thymeleaf Frontend**

**Tasks:**
1. Test the Thymeleaf frontend by running the Spring Boot application and accessing the following pages:
   - `/tasks` to view the list of tasks.
   - `/tasks/new` to create a new task.
   - `/tasks/edit/{id}` to edit an existing task.
   - Test the delete functionality by deleting a task from the list view.

---
