import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { GameComponent } from './game/game.component';
import { NewGameComponent } from './new-game/new-game.component';

const routes: Routes = [
  {
    path: 'game/:id',
    component: GameComponent,
    data: { title: 'Game' }
  },
  {
    path: 'new-game',
    component: NewGameComponent,
    data: { title: 'New Game' }
  },
  { 
    path: '',
    redirectTo: '/new-game',
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
