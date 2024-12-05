# Expense Tracker

Aplicación de consola para la gestión y seguimiento de gastos personales. Diseñada en Java, esta herramienta permite realizar operaciones CRUD sobre los gastos y almacena los datos de manera persistente en una base de datos MySQL.

## Características
- **Gestión de Gastos**: Agrega, lista, actualiza y elimina gastos.
- **Persistencia**: Almacena los datos en una base de datos MySQL para asegurar su disponibilidad entre sesiones.
- **Validación de Entrada**:
  - Verifica que los montos ingresados sean positivos.
  - Valida el formato de la fecha (yyyy-MM-dd).
  - Maneja entradas no válidas para evitar errores.
- **Manejo de Errores**: Captura excepciones SQL y proporciona mensajes claros para el usuario.
- **Interfaz de Consola**: Menú interactivo para realizar las operaciones de manera sencilla.

## Tecnologías Utilizadas
- **Lenguaje**: Java
- **Base de Datos**: MySQL
- **Conexión**: JDBC

## Instalación
1. Clona este repositorio:
   ```bash
   git clone https://github.com/tu-usuario/expense-tracker.git
Configura la base de datos MySQL:

Crea una base de datos y tabla con el siguiente script SQL:
sql
Copiar código
CREATE DATABASE ExpenseTrackerDB;
USE ExpenseTrackerDB;

CREATE TABLE expenses (
    id INT AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(255) NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    date DATE NOT NULL
);
Asegúrate de configurar correctamente el usuario y la contraseña de MySQL en la clase DatabaseConnection.
Abre el proyecto en tu IDE de Java preferido (por ejemplo, IntelliJ IDEA o Eclipse).

Compila y ejecuta el archivo principal (Main.java).

Uso
Ejecuta la aplicación desde la consola.
Navega por las opciones del menú para realizar las diferentes operaciones de gestión de gastos.
Ejemplo de Menú
text
Copiar código
1. Add Expense
2. List Expenses
3. Update Expense
4. Delete Expense
5. Exit
