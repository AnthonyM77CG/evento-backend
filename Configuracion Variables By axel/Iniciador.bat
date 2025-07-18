@echo off
:: Verificar si se esta ejecutando como administrador
net session >nul 2>&1
if %errorlevel% neq 0 (
    echo Se requieren permisos de administrador. Solicitando...
    powershell -Command "Start-Process '%~f0' -Verb runAs"
    exit /b
)

:: Ejecutar el script de PowerShell
echo Estableciendo variables del sistema para el backend Spring...
powershell -ExecutionPolicy Bypass -File "%~dp0VariablesDeEntornoBackend.ps1"
echo.
echo Variables definidas correctamente.
pause
