<h1 align="center"> Gestión de Proyectos</h1>

<p align="center">Desarrollar una aplicación web de gestión de proyectos que permita a los usuarios crear y gestionar proyectos, asignar tareas y hacer seguimiento del progreso del trabajo. La aplicación debe incluir un sistema de autenticación y permisos para asegurar que las acciones de los usuarios sean apropiadas según su rol.<p>
<p align="center">Utilizo la encriptacion de contraseñas a traves de JWT, con mapstruct integro el patron DTO</p>
<br/>
<h2> Herramientas: Java | SpringBoot | SpringSecurity | MySql | MapStruct | Postman | XAMPP</h2>
<br/>
<h2>Para el diseño de las clases utilice draw.io</h2>
![Diagrama de clases](https://github.com/user-attachments/assets/b084653d-34be-48b0-b39f-590a7bba337e)
<br/>
<h2 align="center">Endpoints</h2>
<h3>Proyectos:</h3>
<p>GET /projects Obtener todos los proyectos</p>
<p>POST /projects Crear un proyecto</p>
<p>GET /projects/{id} Buscar proyecto por su id</p>
<p>PUT /projects/{id} Actualizar proyecto existente</p>
<p>DELETE /projects/{id} Eliminar un proyecto existente</p>
<h3>Tareas:</h3>
<p>GET /tasks?project_id=3 Obtener todos las tareas de un proyecto</p>
<p>POST /tasks Crear una tarea</p>
<p>PATCH /tasks/{id} Actualizar el estado de una tarea</p>
<h3>Registro/Inicio de Sesion:</h3>
<p>GET /auth/register Registrarse como USER y nos devuelve el token para poder consumir las Endpoints</p>
<p>POST /auth/login Iniciamos Sesion devolviendonos el token para consumir las Endpoints</p>





