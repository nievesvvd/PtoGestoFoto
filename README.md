## Práctica 3.
##ANDROID-GYMKANA
### Aplicación Punto Gesto Foto.
### Autores
* Samuel Peregrina Morillas
* Nieves Victoria Velásquez Díaz

### Duración de la práctica.
Desde 12-Ene-2016 hasta 15-Feb-2016

### Breve descripción de la práctica.
Para la realización de esta práctica, se programarán cinco aplicaciones android diferentes de forma que, cada una hace uso de los distintos sensores que posee el dispositivo android.
### Descripción del problema.
Esta aplicación consiste en, una vez se ha realizado un patrón específico, el dispositivo lo reconocerá y se lanzará la cámara de andoird haciendo una foto.
###Clases.
Para la primera parte de la practica, la asociada con el patrón, nos hemos basado en la libreria del usuario de GitHub [DreaminginCodeZH](https://github.com/DreaminginCodeZH/PatternLock).

* **EstablecerPatronAcotivity:** En esta primera clase nos encargamos de establecer un nuevo patrón, ya sea por que no habia uno establecido previamente, o porque queramos cambiarlo por uno nuevo. Una vez establecido, procederemos a llamar a la clase **ConfirmarPatronActivity:** que es la que se encargara de asegurar los patrones.

* **ConfirmarPatronActivity:** En esta clase nos encargamos de comprobar el patrón previamente establecido con el introducido por el usuario y, desde este activity, llamamos a la segunda parte de la práctica, que es la asociada con la cámara. Desde esta clase, también podemos volver a la anterior en caso de que queramos cambiar el patrón.

* **Utility:** Esta clase la usamos como clase puente entre **ConfirmarPatronActivity** y **EstablecerPatronActivity** para poder trabajar con el patrón establecido ya sea para consultarlo como para almacenarlo o modificarlo.

* **MainActivity:** En esta clase nos encargamos de gestionar a qué activity vamos a llamar antes dependiendo de si ya hay o no un patrón seteado, y tras esto, si el patrón es correcto llamamos a la cámara basada en la libreria del usuario de GitHub [Commonsguy](https://github.com/commonsguy/cwac-cam2)

###Métodos.
* El metodo implementado en la clase **EstablecerPatronActivity()** ha sido:
	
	* **protected void onSetPattern(List < PatternView.Cell \> pattern):** En este método pasamos el patrón obtenido y lo tranformamos en string usando el método de la libreria que estamos utilizando: **patternToSha1String()**, tras esto, lo almacenamos y finalmente damos paso al activity **ConfirmarPatronActivity.**

*  Los métodos implementados en la clase **ConfirmarPatronActivity()** han sido:
	*	**protected void onStart():** en este método vemos si  hay un patrón almacenado, si no lo hay, llamamos a **EstablecerPatronActivity()** para crear uno nuevo.

	* **protected boolean isPatternCorrect(List < PatternView.Cell \> pattern):** En este método nos encargamos de cargar el patrón que ya teníamos guardado y vemos si el introducido es igual. Devolveremos *true* si es correcto y *false* si no.
	
	* **protected void onConfirmed():** método con el que, si el patrón es correcto, llamamos a la camara para que tome la foto.

	* **protected void onForgotPassword():** método con el que podemos cambiar el patrón en caso de que lo deseemos.

	* **public void onBackPressed():** método con el que salimos de la aplicacion en caso de que pulsemos el boton de "atrás".

* Los metodos implementados en la clase **Utility()** han sido:
	* **public static void saveToPreferences(Context context, String lockPattern):** a este método le pasamos el contecto actual y el patrón ya tansformado a string. Con esto, creamos una variable de la clase *SharedPreferences*, le indicamos que es de tipo patrón y la editamos guardando  como **KEY_LOCK_PATTERN** el patrón.
	
	* **public static String loadFromPreferences(Context context):** método al que, dado el contexto, obtenemos el patrón almacenado en el metodo anterior mediante el objeto de la clase *SharedPreferences*.
	
	* **public static boolean isPatternSetted(Context context):** con este método vemos si hay algo almacenado en la variable de la clase *SharedPreferences*, de ser asi devolvera *true*, si no, devolvera *false*.

* Los metodos implementados en la clase **MainActivity()** han sido:
	* **protected void onCreate(Bundle savedInstanceState):** contructor del activity, en el una vez establecida la vista, vemos si ya se ha establecido un patrón, en caso afirmativo llamará al método *ConfirmarPatronActivity*, si no, iniciará el activity *EstablecerPatronActivity*, para establecer el patrón deseado.
	
	* **protected void onActivityResult(int requestCode, int resultCode, Intent data):** con este método vemos: si se ha establecido el patrón y el introducido por el usuario es correcto entonces creamos la carpeta donde se van a almacenar las fotos que se tomen y posteriormente creamos el fichero de la foto y por nombre tendra la fecha del dia y la hora en la que se tome de forma que cada foto tiene su propio nombre. Una vez todo listo se llama al *CameraActivity.IntentBuilder* es decir al constructor del builder y le pasamos mediante .skipConfirm().facing(CameraActivity.Facing.BACK).to(uriImage).debug(): como va a funcionar, es decir, donde se va a almacenar la imagen (.to(uriImage)), que cámara vamos a usar,(facing(CameraActivity.Facing.BACK)) en este caso la posterior, si se va a mostrar informacion extra de depuracion (.debug()) y finalmente usaremos .build() para que construya el intent correctamente. Tras esto inicia la actividad para que se muestre la vista de la camara y se pueda proceder a tomar la foto. Finalmente, vemos si la foto ha sido realizada correctamente, en caso de ser así aparece un Toast en la parte inferior de la pantalla indicando que la foto se ha realizado con exito y se llamara al activity de confirmar el patrón de nuevo, reiniciando la aplicación de forma que habra que volver a meter un patrón para poder realizar la foto de nuevo.
	
###Bibliografía.
* [Libreria del patron](https://github.com/DreaminginCodeZH/PatternLock)
* [Como almacenar las fotos en la memoria interna](http://www.jc-mouse.net/android/tomar-fotos-con-la-camara-y-guardar-en-la-sdcard)
* [Almacenamiento en la memoria interna y creacion de carpetas](http://stackoverflow.com/questions/25467007/unable-to-decode-stream-java-io-filenotfoundexception-storage-emulated-0-open-f)
* [Libreria de la camara](https://github.com/commonsguy/cwac-cam2)
