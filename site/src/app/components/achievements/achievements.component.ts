import { Component, OnInit } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { NgFor } from '@angular/common';
import { AchievementsService } from '../../services/achievements.service';
import { Achievement } from '../../models/Achievement';

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
export class AchievementsComponent implements OnInit {
  achievementsList: Achievement[] = [];

  constructor(private achievementsService: AchievementsService) {}

  ngOnInit() {
    this.loadAchievements();
  }

  private loadAchievements(): void {
    const allAchievements = this.achievementsService.getUserAchievements();
    
    allAchievements.subscribe({
      next: (achievements) => {
        this.achievementsList = achievements;
        console.log('Achievements loaded:', achievements);
      },
      error: (error) => {
        console.error('Error loading achievements:', error);
        this.achievementsList = []; // Limpa a lista em caso de erro
      }
    });
  }
} 