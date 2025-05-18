import { Component, OnInit } from '@angular/core';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatListModule } from '@angular/material/list';
import { MatButtonModule } from '@angular/material/button';
import { MatButtonToggleModule } from '@angular/material/button-toggle';
import { MatCardModule } from '@angular/material/card';
import { MatTableModule } from '@angular/material/table';
import { FormsModule } from '@angular/forms';
import { MatTabsModule } from '@angular/material/tabs';
import { PeriodType } from "../utils/enums/PeriodType";
import { MetricModelGroup } from "../utils/enums/MetricModelGroup";
import { NgFor, NgForOf, NgIf } from "@angular/common";
import { RankingServiceWeb } from "./services/ranking.service.web";
import { Ranking } from "./models/Ranking";
import { AuthService } from './services/auth.service';

@Component({
  selector: 'app-root',
  imports: [
    NgForOf, NgFor, NgIf,
    MatTabsModule,
    MatSidenavModule,
    MatToolbarModule,
    MatListModule,
    MatButtonModule,
    MatButtonToggleModule,
    MatCardModule,
    MatTableModule,
    FormsModule,
    MatTabsModule
  ],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  metricModelGroup: MetricModelGroup = MetricModelGroup.KEYBOARD;
  rankingList: Array<Ranking> = [];
  isLoggedIn$ = this.authService.isLoggedIn$;

  constructor(
    private rankingService: RankingServiceWeb,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.loadRankings();
  }

  selectMetricModelGroup(metricModelGroup: MetricModelGroup) {
    this.metricModelGroup = metricModelGroup;
    this.rankingList = [];
    this.loadRankings();
  }

  handleAuth(): void {
    this.authService.isLoggedIn$.subscribe(isLoggedIn => {
      if (isLoggedIn) {
        this.authService.logout();
      } else {
        this.authService.login();
      }
    });
  }

  private loadRankings(): void {
    if (this.metricModelGroup == MetricModelGroup.KEYBOARD) {
      const singleKeysData = this.rankingService.listSingleKeyRankings("1");
      const singleKeysRanking: Ranking = new Ranking("Caractere pressionado", "quantidade", singleKeysData);
      this.rankingList.push(singleKeysRanking)

      const allKeysData = this.rankingService.listAllKeysRankings();
      const allKeysRanking: Ranking = new Ranking("Caracteres pressionados", "quantidade", allKeysData);
      this.rankingList.push(allKeysRanking)

      return;
    }

    if (this.metricModelGroup == MetricModelGroup.MOUSE) {
      const mouseClickData = this.rankingService.listMouseClickRankings();
      const mouseClickRanking: Ranking = new Ranking("Clicks de mouse", "clicks", mouseClickData);
      this.rankingList.push(mouseClickRanking)

      const mouseMovementData = this.rankingService.listMouseMovementRankings();
      const mouseMovementRanking: Ranking = new Ranking("Movimento do mouse", "metros", mouseMovementData);
      this.rankingList.push(mouseMovementRanking)

      return;
    }
  }

  protected readonly MetricModelGroup = MetricModelGroup;
  protected readonly PeriodType = PeriodType;
  protected readonly Object = Object;
}
