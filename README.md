# GESTSTOCK — Sistema de Gestión de Productos

### Proyecto Integrador de Programación II

**Equipo:** Code And Coffe  
**Carrera:** Tecnicatura Universitaria en Programación  
**Lenguaje:** Java  
**Modalidad:** Aplicación de consola

---

## Descripción del proyecto

GestStock es un sistema de gestión de productos e inventario desarrollado en Java como parte del Proyecto Integrador de Programación II.

El proyecto consiste en la migración y adaptación de un programa originalmente desarrollado en PSeInt, incorporando conceptos de Programación Orientada a Objetos (POO).

El sistema permite administrar productos, controlar existencias, realizar búsquedas, consultar reportes y gestionar usuarios con distintos niveles de acceso.

## Integrantes

| Integrante | Responsabilidad principal |
|---|---|
| Mateo | Gestión de productos, validaciones, modificaciones y bajas |
| Valentín (Valen) | Gestión de inventario, búsquedas, filtros y reportes |
| Juan Ignacio (Juani) | Gestión de usuarios, roles y permisos |
| Patricio | Menú principal, inicio de sesión, datos iniciales e integración del sistema |

## Funcionalidades principales

### Gestión de productos

- Registrar nuevos productos.
- Consultar el listado de productos.
- Buscar productos por código y nombre.
- Modificar información de productos.
- Dar de baja y reactivar productos.
- Clasificar productos por sección.

### Gestión de stock e inventario

- Registrar ingresos de stock.
- Registrar egresos de stock.
- Consultar las existencias de un producto.
- Detectar productos con stock bajo.
- Consultar productos por sección.
- Visualizar estadísticas generales.
- Calcular el valor total del inventario.

### Gestión de usuarios

- Iniciar sesión con usuario y contraseña.
- Diferenciar los roles de administrador y empleado.
- Registrar y consultar usuarios.
- Dar de baja y reactivar cuentas.
- Administrar permisos y roles.

### Datos iniciales

El sistema incorpora **60 productos precargados**, distribuidos entre seis secciones:

1. Bebidas.
2. Frutas.
3. Verduras.
4. Carnes.
5. Lácteos.
6. Almacén.

Cada producto contiene código, nombre, descripción, sección, precio, stock actual, stock mínimo y estado.

## Tecnologías y conceptos utilizados

- Java.
- Programación Orientada a Objetos.
- Clases, objetos, atributos y métodos.
- Encapsulamiento.
- Constructores y validaciones.
- Estructuras condicionales (`if`, `else`, `switch`).
- Estructuras repetitivas (`for`, `while`, `do-while`).
- Colecciones `ArrayList`.
- Entrada y salida de datos por consola.
- Git y GitHub para control de versiones y trabajo colaborativo.

## Estructura del proyecto

```text
GestStock_Code_And_Coffe/
├── src/
│   ├── Producto.java
│   ├── Inventario.java
│   ├── Usuario.java
│   ├── GestorUsuarios.java
│   ├── Consola.java
│   ├── DatosEjemplo.java
│   └── GestStock.java
├── .gitignore
└── README.md
```

### Descripción de las clases

| Clase | Función |
|---|---|
| `Producto` | Representa un producto y sus operaciones de stock. |
| `Inventario` | Almacena productos y realiza búsquedas, filtros y cálculos. |
| `Usuario` | Representa una cuenta de usuario y su rol. |
| `GestorUsuarios` | Administra usuarios y autenticación. |
| `Consola` | Gestiona la lectura y validación de datos ingresados. |
| `DatosEjemplo` | Incorpora los 60 productos iniciales. |
| `GestStock` | Coordina la ejecución del programa y sus menús. |

## Requisitos para ejecutar el programa

- Tener instalado Java JDK 8 o superior.
- Disponer de una terminal o de Visual Studio Code.

## Instrucciones de ejecución

**1. Clonar el repositorio**

```bash
git clone https://github.com/patovergaraa/GestStock_Code_And_Coffe.git
```

**2. Entrar a la carpeta del proyecto**

```bash
cd GestStock_Code_And_Coffe
```

**3. Compilar las clases Java**

```bash
mkdir -p out
javac -d out src/*.java
```

**4. Ejecutar el programa**

```bash
java -cp out GestStock
```

## Credenciales iniciales

| Rol | Usuario | Contraseña |
|---|---|---|
| Administrador | `admin` | `admin123` |
| Empleado | `empleado` | `1234` |

Estas credenciales son de demostración y permiten probar los diferentes niveles de acceso.

## Funcionamiento

Al iniciar GestStock, el programa solicita las credenciales del usuario.

Una vez autenticado, se muestra un menú interactivo que permite acceder a la gestión de productos, las operaciones de stock, las consultas y la administración de usuarios según los permisos correspondientes.

El listado presenta los productos en formato de tabla, mostrando su código, nombre, sección, precio, stock y stock mínimo.

Los productos que necesitan reposición se identifican mediante una alerta visual.

**Nota:** los datos se almacenan en memoria durante la ejecución. Al cerrar el programa, las modificaciones realizadas no se guardan permanentemente.

## Trabajo colaborativo

El proyecto fue desarrollado de manera grupal utilizando Git y GitHub.

Cada integrante trabajó en distintas ramas, realizando commits con sus aportes. Posteriormente, los cambios fueron integrados mediante Pull Requests a la rama principal `main`.

Esta metodología permitió dividir responsabilidades, registrar el avance del desarrollo e integrar las diferentes funcionalidades del sistema.

## Conclusión

GestStock representa la evolución del proyecto original desarrollado en PSeInt hacia una aplicación Java organizada mediante Programación Orientada a Objetos.

A través del trabajo colaborativo, se implementó un sistema de consola que permite gestionar productos, controlar stock, consultar información del inventario y administrar usuarios.

El proyecto integra los principales conceptos estudiados durante la cursada y constituye una base que puede ampliarse con nuevas funcionalidades.

---

**Code And Coffe — Proyecto Integrador 2026**
