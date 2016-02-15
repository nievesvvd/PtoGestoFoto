## Pactica 3.
##ANDROID-GYMKANA
### Aplicacion Punto Gesto Foto.
### Autores
* Samuel Peregrina Morillas
* Nieves Victoria Velásquez Díaz

### Duración de la práctica.
Desde 12-Ene-2016 hasta 15-Feb-2016

### Breve descripción de la practica.
Para la realizacion de esta práctica, se programarán cinco aplicaciones android diferentes de forma que, cada una hace uso de los distintos sensores que posee el dispositivo android.
### Descripción del problema.
Esta aplicación consiste en, una vez se ha realizado un patrón específico, el dispositivo lo reconocerá y se reproducirá un sonido determinado. En este caso el patrón de movimiento consiste en mover el dispositivo rápidamente en el eje X, como si de un látigo se tratase.
###Clases.
Para la primera parte de la practica, la asociada con el patron, nos hemos basado en la libreria del usuario de GitHub [DreaminginCodeZH](https://github.com/DreaminginCodeZH/PatternLock).

* **EstablecerPatronAcotivity:** En esta primera clase nos encargamos de establecer un nuevo patron, ya sea por que no habia uno establecido previamente, o porque queramos cambiarlo por uno nuevo. Una vez establecido, procederemos a llamar a la clase **ConfirmarPatronActivity**que es la que se encargara de asegurar los patrones.

* **ConfirmarPatronActivity:** En esta clase nos encargamos de comprobar el patron previamente establecido con el introducido por el usuario y, desde este activity, llamamos a la segunda parte de la práctica, que es la asociada con la cámara. Desde esta clase, también podemos volver a la anterior en caso de que queramos cambiar el patron.
* **Utility:** Esta clase la usamos como clase puente entre **ConfirmarPatronActivity** y **EstablecerPatronActivity** para poder trabajar con el patron establecido ya sea para consultarlo como para almacenarlo o modificarlo.

* **TomarFotoActivity:**

###Métodos.
* El metodo implementado en la clase **EstablecerPatronActivity()** ha sido:
	
	* **protected void onSetPattern(List < PatternView.Cell \> pattern):** En este método pasamos el patrón obtenido y lo tranformamos en string usando el metodo de la libreria que estamos utilizando: **patternToSha1String()**, tras esto, lo almacenamos y finalmente damos paso al activity **ConfirmarPatronActivity.**

*  Los metodos implementados en la clase **ConfirmarPatronActivity()** han sido:
	*	**protected void onStart():** en este método vemos: si no hay un patrón almacenado, llamamos a **EstablecerPatronActivity()** para crear uno nuevo, y si no, mostramos un mensaje indicando que ya hay un patrón.

	* **protected boolean isPatternCorrect(List < PatternView.Cell \> pattern):** En este metodo nos encargamos de cargar el patron que ya teniamos guardado y vemos si el introducido es igual. Devolveremos *true* si es correcto y *false* si no.
	
	* **protected void onConfirmed():** metodo con el que, si el patron es correcto, llamamos a la camara para que tome la foto.

	* **protected void onForgotPassword():** metodo con el que podemos cambiar el patron en caso de que lo deseemos.

	* **public void onBackPressed():** metodo con el que salimos de la aplicacion en caso de que pulsemos el boton de "atrás".

* Los metodos implementados en la clase **Utility()** han sido:
	* **public static void saveToPreferences(Context context, String lockPattern):** a este metodo le pasamos el contecto actual y el patron ya tansformado a string. Con esto, creamos una variable de la clase *SharedPreferences*, le indicamos que es de tipo patron y la editamos guardando  como **KEY_LOCK_PATTERN** el patron.
	
	* **public static String loadFromPreferences(Context context):** metodo al que, dado el contexto, obtenemos el patron almacenado en el metodo anterior mediante el objeto de la clase *SharedPreferences*.
	
	* **public static boolean isPatternSetted(Context context):** con este metodo vemos si hay algo almacenado en la variable de la clase *SharedPreferences*, de ser asi devolvera *true*, si no, devolvera *false*.

### Bibliografía.
[Libreria del patron](https://github.com/DreaminginCodeZH/PatternLock)
