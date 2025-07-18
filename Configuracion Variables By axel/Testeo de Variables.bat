@echo off
echo Verificando variables de entorno del backend Spring...
echo --------------------------------------------
echo DB_URL: %DB_URL%
echo DB_USER: %DB_USER%
echo DB_PASS: %DB_PASS%
echo JWT_SECRET: %JWT_SECRET%
echo --------------------------------------------

if defined DB_URL (
    echo [OK] DB_URL definida.
) else (
    echo [X] DB_URL no definida.
)

if defined DB_USER (
    echo [OK] DB_USER definida.
) else (
    echo [X] DB_USER no definida.
)

if defined JWT_SECRET (
    echo [OK] JWT_SECRET definida.
) else (
    echo [X] JWT_SECRET no definida.
)

echo.
echo Si alguna variable aparece vacia, reinicia la terminal o revisa si fue establecida correctamente.
pause
