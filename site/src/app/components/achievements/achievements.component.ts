import { Component } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { NgFor } from '@angular/common';

@Component({
  selector: 'app-achievements',
  standalone: true,
  imports: [
    MatCardModule,
    MatIconModule,
    NgFor
  ],
  templateUrl: './achievements.component.html',
  styleUrls: ['./achievements.component.scss']
})
export class AchievementsComponent {
  // Placeholder for achievements data
  achievements = [
    {
      title: 'Iniciante',
      description: 'Faça seu primeiro registro',
      completed: false,
      icon: 'star_border'
    },
    {
      title: 'Dedicado',
      description: 'Registre por 7 dias seguidos',
      completed: false,
      icon: 'calendar_today'
    },
    {
      title: 'Mestre do Teclado',
      description: 'Registre mais de 1000 teclas',
      completed: false,
      icon: 'keyboard'
    }
  ];
} 