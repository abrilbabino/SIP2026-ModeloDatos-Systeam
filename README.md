# Configuración del Modelo de Datos Compartido (SysTeam)

Para mantener una arquitectura limpia, nuestro modelo de datos (entidades como `Proyecto`, `Usuario`, etc.) está alojado en un repositorio separado y se descarga automáticamente como una librería desde **GitHub Packages**.

Para que tu computadora tenga permiso de descargar esta librería y compilar el proyecto localmente, debes configurar un Token de GitHub siguiendo estos 3 pasos:

## Generar tu Token de GitHub (PAT)
Maven necesita saber que eres parte del equipo SysTeam para dejarte descargar el paquete.

1. Inicia sesión en GitHub.
2. Ve a **Settings** (Configuración) > **Developer settings** > **Personal access tokens** > **Tokens (classic)**.
3. Haz clic en **Generate new token (classic)**.
4. Ponle un nombre para acordarte (ej. `systeam-maven`).
5. En la sección "Select scopes", **marca únicamente la casilla `write:packages`**.
6. Genera el token y **CÓPIALO**. (¡Ojo! GitHub no te lo volverá a mostrar. Guárdalo en un bloc de notas por ahora).

## Configurar Maven en tu computadora
Ahora vamos a decirle a tu computadora cómo usar ese token.

1. Ve a la carpeta oculta `.m2` en tu computadora:
   * **Windows:** `C:\Users\TuUsuario\.m2\`
   * **Mac / Linux:** `~/.m2/`
2. Dentro de esa carpeta, crea un archivo llamado exactamente `settings.xml` (si ya existe, ábrelo en un editor de texto).
3. Pega el siguiente código, reemplazando con **TU usuario de GitHub** y **TU token**:
```xml
<settings xmlns="[http://maven.apache.org/SETTINGS/1.0.0](http://maven.apache.org/SETTINGS/1.0.0)">
    <servers>
        <server>
            <id>github</id>
            <username>TU_USUARIO_DE_GITHUB</username>
            <password>TU_TOKEN_AQUI</password>
        </server>
    </servers>
</settings>
```
## Descargar la librería y compilar
Una vez configurado el archivo, abre tu terminal en la carpeta de este proyecto (Gestion_de_proyectos-Systeam) y ejecuta:
```bash
mvn clean compile -U
```
