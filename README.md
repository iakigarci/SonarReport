# SonarReport
Proyecto creado por Iñaki García, para la creación de un plugin en SonarQube. 

Vease la Documentación completa en 
[link](https://github.com/iakigarci/SonarReport/blob/master/Documentaci_n_Plugin_Sonar.pdf)

## Instalación

EL proyecto se ha creado con el IDE de Eclipse. Es necesario tener todas las dependencias necesarias. Todas las dependencias utilizadas están en la Documentación, y se agrupan en el pom.xml. Aún así, es necesario tener instadado principalmente, Java, Apache Maven y SonarQube. Una vez instalado las versiones pertinentes de SonarQube, Java y Maven, se crea el JAR del proyecto y se realizan los siguientes pasos:

Se inicia la base de datos de Postgre :
```
C:\pgsql\bin
"pg_ctl" -D "C:\pgsql\data" -l archivo_de_log start
```
Después, se introduce el jar creado en la carpeta _/extensions/plugins_ de SonarQube. Además, iniciamos Sonar mediante el script que se encuentra en _/bin/"SO"/StartSonar_. Este programa nos tiene que mostrar al final el siguiente mensaje (similar). 
```
2020.11.02 09:55:13 INFO  app[][o.s.a.SchedulerImpl] Process[ce] is up
2020.11.02 09:55:13 INFO  app[][o.s.a.SchedulerImpl] SonarQube is up
```

Para visualizar el resultado, se accede a la página de SonarQube con el puerto que se ha configurado. Ejemplo: localhost: http://localhost:9000/about
