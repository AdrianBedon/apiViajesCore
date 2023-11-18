# Admin Core
![Alt text](imagenesReadme/Bed%C3%B3nAdri%C3%A1n-Funcionamiento_Admin.jpg)

## Funcionamiento:
La función principal del usuario de administrador dentro del sistema cores será la del registro y administración de los usuarios que formarán parte del mismo, por ello para garantizar los accesos respectivos se realiza la validación dentro del backend (Spring Boot) a través del JWT Token que se crea en el mismo, este token contiene en su estructura la información referente a el nombre de usuario y los roles que el mismo posee, acompañado de una firma que certifica este token. En palabras generales la estructura del JWT Token sería la siguiente:

### Header:
Aquí se proporciona información referente al algoritmo y tipo de token.
![Alt text](imagenesReadme/Bed%C3%B3nAdri%C3%A1n-HeaderToken.jpg.png) ![Alt text](imagenesReadme/Bed%C3%B3nAdri%C3%A1n-HeaderToken_Significado.jpg.png)

### Payload:
Esta sección corresponde a toda la data que se envía dentro del token.
![Alt text](imagenesReadme/Bed%C3%B3nAdri%C3%A1n-PayloadToken.jpg.png) ![Alt text](imagenesReadme/Bed%C3%B3nAdri%C3%A1n-PayloadToken_Significado.jpg.png)

### Firma:
En este último apartado del token se valida la firma del mismo.
![Alt text](imagenesReadme/Bed%C3%B3nAdri%C3%A1n-FirmaToken.jpg.png) ![Alt text](imagenesReadme/Bed%C3%B3nAdri%C3%A1n-Firma_Significado.jpg.png)

Este token luego es almacenado dentro de 'axios' en el frontend de React para su uso en las operaciones posteriores, es importante mencionar que el tiempo máximo de validez del token es de 60 minutos (1 hora) o hasta que el servidor sea reiniciado.