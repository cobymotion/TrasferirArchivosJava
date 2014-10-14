TrasferirArchivosJava

Proyecto con Cliente / Servidor que mediante sockets mandara archivos de servidor a cliente

Esta formado por 3 proyectos programados en Netbeans y con JDK 1.8

El primer proyecto tiene el nombre de Archivos Compartidos el cual tiene como finalidad manejar las clases que compartiran los dos proyectos adicionales

El segundo proyecto es Cliente que tiene la interfaz y la programación para que se conecte con un servidor mediante sockets

El tercer proyecto es el Servidor que tiene como Finalidad tener una interfaz para que el server arranque.
Cosas relavantes

El servidor maneja un colección de tipo mapa que tiene como Objetivo guardar en una cache los archivos que ya conviertio en Bits los mantiene en memoria para que puedan ser leído desde ahi y no desde el archivo mismo.

El cliente se conecta con socket por medio del puerto 5001 y mediante ObjectStream

Puede manejar archivos hasta de 60 MB en cache o mayores de forma directa(No recomendable porque se cierra el socket)

No maneja ningun control de calidad de los datos

Las carpetas que tiene el repositorio son

ArchivosCompartidos. Tiene el codigo de las clases que son compartidas por los proyectos Build. Tiene los Jar que ya estan compilados para que puedan ejecutarse directamente con la maquina virtual ClientePeticionArchivos. Contiene el codigo fuente para el proyecto que tenemos Docs. Tenemos todos los Javadocs de los documentos que se tienen ServerTraspasoArchivos. Tenemos el codigo fuente del proyecto del server 