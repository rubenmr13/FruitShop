# LABORATORIO DE SISTEMAS MOVILES Y UBICUOS 

#### *Fecha: 23/04/2023*
#### *Autor : Rubén Manzanas Rodríguez*

<br>

>## **FruitShop V2**

Esta aplicación ha sido creada con el propósito de mejorar la antigua aplicacion de la frutería implementando nuevas funcionalidades.

<br>

Al ejecutar la aplicación, lo primero que veremos será la pantalla principal, que nos brindará acceso a diferentes secciones de la tienda, como la frutería, carnicería, pescadería y la tienda de deportes. Al seleccionar la imagen de alguna de estas secciones, la aplicación nos llevará directamente a la pantalla correspondiente.

En la parte de navegación se ha implementado un práctico "Drawer Menu", lo que facilita la navegación en la aplicación y permite al usuario acceder rápidamente a cualquier sección disponible en el "Drawer Menu".

<br>

Al desplegar el "Drawer Menu", en primer lugar aparecerá en la parte superior el logo de la empresa. Debajo de este, se encuentran cuatro opciones de navegación que nos permiten acceder a las diferentes secciones de la aplicación. Cada opción se representa con un icono específico y su respectivo nombre:

1. El primer icono que aparece en el "Drawer Menu" es el "User". Al seleccionar esta opción, la aplicación nos llevará a un formulario en el que se puede iniciar sesión en la cuenta de usuario. Este formulario consta de dos campos: "User" y "Password". Si el usuario ya tiene una cuenta, puede ingresar su información en los campos correspondientes y luego hacer clic en el botón "Sign In" para acceder a la cuenta.
También se puede crear una cuenta de usuario haciendo clic en el botón "Sign Up" y completando los campos necesarios en el formulario. En ambos casos, si los campos de usuario y contraseña no se han completado correctamente, no se podrá iniciar sesión ni registrarse. Una vez que se ha iniciado sesión o registrado una cuenta nueva, la aplicación nos llevará a la misma pantalla del "User" donde se le dará la bienvenida junto con su nombre de usuario y se le brindará la opción de cerrar sesión gracias a un botón. Si el usuario decide cerrar sesión, volverá a la pantalla de inicio de sesión con los formularios de registro disponibles.


2. El segundo icono que aparece en el "Drawer Menu" es el "Chat". Al seleccionar esta opción, la aplicación nos llevará a una pantalla de chat simulado donde el usuario puede interactuar con la tienda. El usuario puede escribir mensajes en el campo de entrada y enviarlos presionando el botón "Enviar". Los mensajes aparecerán en la ventana de chat, debajo del último mensaje enviado.
Si el usuario sale de la pantalla del "Chat" y vuelve a ingresar, los mensajes que haya escrito anteriormente se habrán guardado y aparecerán en el chat. Esta función permite al usuario continuar con la conversación desde donde la dejó anteriormente.  


3. El tercer icono que aparece en el "Drawer Menu" es el "Inbox". Al seleccionar esta opción, la aplicación nos llevará al inbox de la tienda, donde el usuario puede ver todos los mensajes enviados.
Si el usuario ha enviado algún mensaje, se proporcionará un botón que permite al usuario borrar todos los mensajes enviados.


4. El cuarto icono que aparece en el "Drawer Menu" es el "Total Basket". Al seleccionar esta opción, la aplicación llevará al usuario a la pantalla que muestra la lista de todos los productos que han sido seleccionados en la frutería, carnicería, pescadería y tienda de deportes, junto con la cantidad de cada item que se ha seleccionado.
En la parte inferior de la cesta, aparecerá el precio total de la compra. Esta función permite al usuario tener una vista general de todo lo que ha seleccionado y asegurarse de que no haya errores.
Se proporcionarán dos botones, uno para vaciar la cesta y otro para finalizar la compra. El botón "Vaciar cesta" permite al usuario eliminar todos los productos que ha seleccionado. El botón "Comprar cesta" permite al usuario "finalizar la compra".


<br>

Si accedemos a la sección de frutería desde la pantalla principal, seremos llevados a una nueva pantalla que muestra, un spinner con el que podemos seleccionar la fruta deseada, una cesta vacía y un icono que nos llevará a la pantalla de "Total Basket".
Al desplegar el spinner, se mostrarán las frutas disponibles con sus respectivas imágenes. Al seleccionar una fruta, aparecerá un "SeekBar" que nos permitirá seleccionar la cantidad que deseamos de la fruta elegida. Una vez seleccionada la cantidad, se mostrará el precio de la fruta asociado a la cantidad elegida. Si pulsamos el botón "Add", la fruta será añadida a la cesta. La cesta se actualizará y mostrará la foto del producto y la cantidad seleccionada. Además, se actualizará el precio de la cesta de frutas y el precio total de la compra.
La aplicación proporcionará la opción de vaciar la cesta de frutas solo si hay elementos en ella.
Si seleccionamos "Select the fruit" en el spinner (el elemento neutro), los elementos para añadir una fruta como el SeekBar, botón "Add"... desaparecerán de la vista del usuario.
Si seleccionamos el icono "Total Basket", seremos llevados a la cesta total donde aparecerán todos los elementos de cualquier sección que hayamos seleccionado para comprar, junto con el precio total de toda la compra.

<br>

La pescadería, la carnicería y la tienda de deportes, tienen la misma funcionalidad que la frutería pero personalizada para cada sección, ofreciendo los productos correspondientes a cada una de ellas y respetando los precios de la cesta de cada sección.

<br>

La aplicación garantiza la persistencia de la información en todo momento. Lo que significa que los productos seleccionados, las cantidades elegidas, los precios y otros datos relevantes se mantienen guardados incluso en caso de que se produzcan cambios de configuración en el dispositivo móvil. De esta manera, el usuario puede continuar desde donde dejó su actividad en la aplicación sin perder información importante.

<br>

Cuando accedamos a cualquiera de las secciones de la aplicación, se mostrará automáticamente un botón de "Atrás" en lugar del "Drawer Menu". Esto nos permitirá regresar fácilmente a la pantalla anterior.

<br>

>## Extras

Los extras que se han incluido en la aplicación son:

* Soporte multilenguaje Inglés/Español según la configuración del teléfono.
* La aplicacion permite su funcionamiento tanto en horizontal como en vertical
* Las pantallas horizontal y vertical tendrán un layout diferente acorde con el espacio proporcionado
* Fotografía del producto en el spinner.
* Fotografía del producto en la cesta.
* Evitar tener que pulsar el botón para seleccionar una fruta en el Spinner
* Guardar el nombre del usuario que inició la sesión
* Posibilidad de cerrar la sesion si el usuario inició la sesión anteriormente.
* "Flecha Hacia Atrás" que permite volver a la pantalla anterior 
* Cambio automatico del "Drawer Menu" por una "Flecha Hacia Atrás"
* Posibilidad de vaciar la cesta de una sección.
* Posibilidad de vaciar la cesta total.
* No permitir que el usuario inicie sesion o cree una cuenta si todos los campos no estan rellenos.
* Boton dentro del Inbox que permite borrar los mensajes escritos por el usuario
* Logica de negicio del User
* Logica de negocio del Chat
* Logica de negocio del Inbox


* Mejora que haga la pantalla más atractiva desde el punto de vista funcional como:
    * Logo de la empresa
    * Cambio de color de los elementos en función del fragment
    * Delimitación de la cesta entre barras
    * Integracion de iconos en las respectivas secciones
    * Letras de distinto tamaño y colores
    * Botones de diferentes colores en función de su utilidad 
    * Cambio de color del SeekBar 
    * Cambio de nombre del Navigation en función del fragment 
    


>## Explicacion y Ficheros

Para la realizacion de la practica ha sido necesario introducir nuevas librerias y nuevas conceptos.

1. ViewModel y LiveData:

    -ViewModel: El ViewModel es responsable de mantener y administrar los datos relacionados con la interfaz de usuario, se ocupa de la supervivencia a los cambios de configuración

    -LiveData es una clase que se utiliza para almacenar y observar datos. Los objetos LiveData son observados desde el ViewModel y actualizados automáticamente cuando cambian los datos.

    Para implementar el ViewModel y el LiveData es necesario crear una nueva clase que permita separar la lógica de la interfaz de usuario de la actividad o el fragmento de la logica de negocio.
    
    En esta práctica, se ha implementado una clase ViewModel para cada fragmento, a excepción de aquellos fragmentos que compartían datos, los cuales han usado la misma clase ViewModel.

2. Navegacion:

    Para la realización de la navegacion, es necesario crearnos una nueva carpeta que sea de tipo "Navigation", definir un grafo de navegación xml (en nuestro caso se llama "navigation.xml") que será el encargado de realizar la navegación.

3. Imagenes:
    Todas las imagenes que se usan en la aplicación están en la carpeta Drawable




>## Imágenes
<br>


Fotos de la aplicación al ejecutarse:
<br>


* ## Vertical: 


    * ![menu](images/horizontal/menu.png)
    * ![drawerMenu](images/horizontal/drawerMenu.png)
    * ![user](images/horizontal/user.png)
    * ![userLogout](images/horizontal/user_logout.png)
    * ![chat](images/horizontal/chat.png)
    * ![inbox](images/horizontal/inbox.png)
    * ![fishShop](images/horizontal/fish1.png)
    * ![fishShop1](images/horizontal/fish2.png)
    * ![fishShop2](images/horizontal/fish3.png)
    * ![fruitShop1](images/horizontal/fruit.png)
    * ![butcherShop](images/horizontal/meat.png)
    * ![sportShop](images/horizontal/sport.png)
    * ![basketTotal](images/horizontal/basketTotal.png)

<br>

<br>

* ## Horizontal:

    * ![menu](images/vertical/menuLand.png)
    * ![drawerMenu](images/vertical/drawerMenuLand.png)
    * ![drawerMenu1](images/vertical/drawerMenuLand1.png)
    * ![user](images/vertical/userLand.png)
    * ![userLogout](images/vertical/user_logoutLand.png)
    * ![chat](images/vertical/chatLand.png)
    * ![inbox](images/vertical/inboxLand.png)
    * ![fruitShop1](images/vertical/fruitLand.png)
    * ![fruitShop2](images/vertical/fruitLand2.png)
    * ![fruitShop3](images/vertical/fruitLand3.png)
    * ![fishShop](images/vertical/fishLand.png)
    * ![butcherShop](images/vertical/meatLand.png)
    * ![sportShop](images/vertical/sportLand.png)
    * ![basketTotal](images/vertical/basketLand.png)


<br>

<br>

* ## Aplicación en español: 
* En esta sección se mostrarán dos imágenes ya que la aplicación es la misma que en inglés, solo que en español.

   * ![menuEspañolVetical](images/españolVertical.png)
   * ![menuEspañolHorizontal](images/españolHorizontal.png)

   
