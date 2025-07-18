Write-Host "Eliminando variables de entorno del SISTEMA..."

[System.Environment]::SetEnvironmentVariable("DB_URL", $null, "Machine")
[System.Environment]::SetEnvironmentVariable("DB_USER", $null, "Machine")
[System.Environment]::SetEnvironmentVariable("DB_PASS", $null, "Machine")
[System.Environment]::SetEnvironmentVariable("JWT_SECRET", $null, "Machine")

Write-Host "Variables del sistema eliminadas."
