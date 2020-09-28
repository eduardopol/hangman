Write-Host "Building Angular front-end application"
cd hangman-angular
npm install
ng build --prod 
Write-Host "Starting Angular front-end application"
ng serve --prod