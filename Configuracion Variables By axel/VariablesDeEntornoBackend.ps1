Write-Host "Estableciendo variables de entorno del sistema para Spring Boot..."

[System.Environment]::SetEnvironmentVariable("DB_URL", "jdbc:mysql://localhost:3306/virella2?createDatabaseIfNotExist=false", "Machine")
[System.Environment]::SetEnvironmentVariable("DB_USER", "grupo2", "Machine")
[System.Environment]::SetEnvironmentVariable("DB_PASS", "1234", "Machine")
[System.Environment]::SetEnvironmentVariable("JWT_SECRET", "supersecretkeythatshouldbelongandcomplexsupersecretkeythatshouldbelongandcomplex", "Machine")

Write-Host ""
Write-Host "Variables del sistema definidas correctamente."
Write-Host "Reinicia la terminal o Visual Studio Code si es necesario."
