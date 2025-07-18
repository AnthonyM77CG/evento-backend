@echo off
:: Verificar si se esta ejecutando como administrador
net session >nul 2>&1
if %errorlevel% neq 0 (
    echo Requiere privilegios de administrador. Solicitando permisos...
    powershell -Command "Start-Process '%~f0' -Verb runAs"
    exit /b
)

:: Si ya tiene privilegios, ejecutar el script PowerShell
echo Estableciendo variables de entorno para el backend Spring...
powershell -ExecutionPolicy Bypass -File "%~dp0Variables de Entorno Backend.ps1	"
echo.
echo Variables definidas correctamente. Cierra y vuelve a abrir tu terminal o VS Code.
pause
