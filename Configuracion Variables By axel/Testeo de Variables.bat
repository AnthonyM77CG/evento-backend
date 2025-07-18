@echo off
echo Mostrando variables de entorno actuales del backend Spring...
echo --------------------------------------------

for %%V in (DB_URL DB_USER DB_PASS JWT_SECRET) do (
    powershell -Command "Write-Host '%%V: ' $Env:%%V"
)

echo --------------------------------------------
echo Si alguna variable esta vacia, reinicia la terminal o revisa si fue creada correctamente.
pause
