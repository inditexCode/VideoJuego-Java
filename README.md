# ⚔️ Proyecto Final - Batalla de Ejércitos en Java ⚔️

> Simulación de batalla estratégica entre héroes y bestias en un universo inspirado en *El Señor de los Anillos*.

---

## 📖 Descripción del Proyecto

Este proyecto es una aplicación desarrollada en **Java** que simula una batalla entre dos ejércitos: **héroes** y **bestias**. 
Cada personaje posee atributos como vida, armadura y un sistema de ataque con reglas especiales basadas en su tipo.

La batalla se desarrolla por turnos, enfrentando personajes individuales hasta que uno de los ejércitos queda totalmente eliminado.

---

## 🛠️ Características principales

- **Arquitectura orientada a objetos** con una jerarquía clara de clases:  
  - `Personaje` (abstracta)  
  - Subclases abstractas: `Heroe` y `Bestia`  
  - Clases concretas: `Elfo`, `Hobbit`, `Humano` (héroes), `Orco`, `Trasgo` (bestias)  

- **Sistema de batalla por turnos:**  
  Se enfrenta cada personaje del ejército héroe con uno del ejército bestia. Se calcula el daño mediante tiradas de dados simuladas y se aplican ajustes según la clase del personaje.

- **Manejo de listas dinámicas de personajes:**  
  La clase `Ejercito` contiene y administra las colecciones de personajes, eliminando los que mueren durante la batalla.

- **Persistencia sencilla:**  
  Se guarda un registro de los resultados de cada batalla en un archivo de texto (`resultado_batalla.txt`) para poder consultarlo después.

---

## 🎨 Interfaz gráfica y herramientas utilizadas

- **JavaFX**  
  Se utilizó JavaFX para la creación de la interfaz gráfica, permitiendo una experiencia visual intuitiva para el usuario.  

- **Scene Builder**  
  La interfaz fue diseñada con [Scene Builder](https://gluonhq.com/products/scene-builder/), una herramienta visual que facilita la creación y edición de archivos FXML
  para JavaFX sin necesidad de codificación manual en XML.

---

## 🔧 Tecnologías y herramientas

| Tecnología          | Descripción                              | Enlace                        |
|---------------------|------------------------------------------|------------------------------|
| Java 17+            | Lenguaje principal del proyecto          | [Oracle Java](https://www.oracle.com/java/)      |
| JavaFX              | Framework para GUI                       | [OpenJFX](https://openjfx.io/)                     |
| Scene Builder       | Editor visual para interfaces JavaFX     | [Scene Builder](https://gluonhq.com/products/scene-builder/)   |
| Git & GitHub        | Control de versiones y hospedaje del código | [Git](https://git-scm.com/) / [GitHub](https://github.com/)    |

---

## 🚀 Cómo ejecutar el proyecto

1. Clona el repositorio:

    ```bash
    git clone https://github.com/inditexCode/VideoJuego-Java.git
    ```

2. Abre el proyecto en tu IDE favorito (por ejemplo, [IntelliJ IDEA](https://www.jetbrains.com/idea/), [Eclipse](https://www.eclipse.org/), [NetBeans](https://netbeans.apache.org/)).

3. Configura JavaFX en tu entorno de desarrollo:  
   - Si usas Maven o Gradle, agrega las dependencias de OpenJFX.  
   - Para proyectos sin gestor, descarga el SDK de JavaFX y configúralo manualmente.  

4. Ejecuta la clase principal que inicia la simulación de batalla (ya sea consola o interfaz gráfica).

5. Consulta el archivo `resultado_batalla.txt` para ver el historial de resultados.

---

## 📚 Conceptos clave aprendidos

- **Programación orientada a objetos (POO):** Herencia, abstracción, polimorfismo.  
- **Colecciones en Java:** Uso de `ArrayList` para manejar grupos dinámicos de personajes.  
- **Diseño de patrones de clases:** Separación clara entre lógica de negocio (`Batalla`, `Ejercito`) y modelos (`Personaje` y sus subclases).  
- **Interfaces gráficas:** Creación y diseño con JavaFX y Scene Builder.  
- **Persistencia básica:** Escritura y lectura de archivos de texto para guardar el estado y resultados.  
- **Control de versiones con Git:** Manejo de commits y sincronización con repositorio remoto en GitHub.

---

## 🤝 Contribuciones

Este proyecto fue desarrollado para fines educativos y puede ser mejorado con nuevas funcionalidades, como:

- Más tipos de personajes con habilidades especiales.  
- Implementación completa de interfaz gráfica para controlar la batalla.  
- Guardado y carga de estados de batalla más avanzado.  
- Mejoras en la IA para el comportamiento de las bestias.

Si querés contribuir, abrí un *pull request* o contactame.

---

## 📄 Licencia

Este proyecto está bajo licencia MIT. Consulta el archivo `LICENSE` para más detalles.

---

## 🙋 Autor

**[Yamil Sanchez]**  
Curso de Programación Java – Proyecto Final  
[GitHub Profile](https://github.com/inditexCode)

---

¡Gracias por visitar el repositorio! 🧙‍♂️🛡️🗡️  
