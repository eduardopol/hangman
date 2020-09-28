Write-Host "Building JAVA back-end application"
cd hangman-java
mvn install
Write-Host "Running JAVA back-end application"
java -jar target/hangman-1.0.0.jar