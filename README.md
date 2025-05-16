# gestion-empleados-app

En este apartado dejo los pasos para levantar el proyecto en SpringBoot:

1- Se clona el repositorio en GitHub: `git clone https://github.com/Cpamela19/gestion-empleados-app.git`

2- Se dirigen al proyecto clonado: `cd gestion-empleados-app`

3- Tienes que crear la red con Docker para que puedan hacer conexion el frontend con el backend: `docker network create app-network`

4- Una vez creada el network ahora debes constuir la aplicacion con Docker con este comando: `docker build -t app-backend .`

5- Y luego lo mandas a correr: `docker run -d --name spring-backend --network app-network -p 8080:8080 app-backend`

Nota: No olvides de configurar el mismo network para el frontend para que se puedan conectar