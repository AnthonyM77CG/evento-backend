Write-Host "Estableciendo variables de entorno de usuario para el backend Spring..."

[System.Environment]::SetEnvironmentVariable("DB_URL", "jdbc:mysql://localhost:3306/virella2?createDatabaseIfNotExist=false", "User")
[System.Environment]::SetEnvironmentVariable("DB_USER", "grupo2", "User")
[System.Environment]::SetEnvironmentVariable("DB_PASS", "1234", "User")
[System.Environment]::SetEnvironmentVariable("JWT_SECRET", "supersecretkeythatshouldbelongandcomplexsupersecretkeythatshouldbelongandcomplex", "User")

Write-Host ""
Write-Host "Variables actualizadas correctamente para el entorno de desarrollo Spring Boot"
Write-Host "Reinicia la terminal o Visual Studio Code para que los cambios tengan efecto"
