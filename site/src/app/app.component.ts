import { Component, OnInit } from '@angular/core';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatListModule } from '@angular/material/list';
import { MatButtonModule } from '@angular/material/button';
import { MatButtonToggleModule } from '@angular/material/button-toggle';
import { MatCardModule } from '@angular/material/card';
import { MatTableModule } from '@angular/material/table';
import { FormsModule, ReactiveFormsModule, FormControl } from '@angular/forms';
import { MatTabsModule } from '@angular/material/tabs';
import { MatIconModule } from '@angular/material/icon';
import { PeriodType } from "../utils/enums/PeriodType";
import { MetricModelGroup } from "../utils/enums/MetricModelGroup";
import { NgFor, NgForOf, NgIf, AsyncPipe } from "@angular/common";
import { RankingServiceWeb } from "./services/ranking.service.web";
import { Ranking } from "./models/Ranking";
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { Observable } from 'rxjs';
import { map, startWith } from 'rxjs/operators';
import { AuthService } from './services/auth.service';
import { AchievementsComponent } from './components/achievements/achievements.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    NgForOf, NgFor, NgIf, AsyncPipe,
    MatTabsModule,
    MatSidenavModule,
    MatToolbarModule,
    MatListModule,
    MatButtonModule,
    MatButtonToggleModule,
    MatCardModule,
    MatTableModule,
    MatIconModule,
    FormsModule,
    ReactiveFormsModule,
    MatAutocompleteModule,
    MatInputModule,
    MatFormFieldModule,
    AchievementsComponent
  ],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  metricModelGroup: MetricModelGroup = MetricModelGroup.KEYBOARD;
  rankingList: Array<Ranking> = [];
  isLoggedIn$;
  showAchievements = false;

  searchControl = new FormControl('');
  options: string[] = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'];
  filteredOptions: Observable<string[]>;
  selectedKey: string = 'A'; // Valor padrão

  constructor(
    private rankingService: RankingServiceWeb,
    protected readonly authService: AuthService
  ) {
    this.isLoggedIn$ = this.authService.isLoggedIn$;
    this.filteredOptions = this.searchControl.valueChanges.pipe(
      startWith(''),
      map(value => this._filter(value || '')),
    );
  }

  toggleAchievements() {
    this.showAchievements = !this.showAchievements;
    console.log('showAchievements:', this.showAchievements);
  }

  onOptionSelected(event: any) {
    this.selectedKey = event.option.value;
    if (this.metricModelGroup === MetricModelGroup.KEYBOARD) {
      const singleKeysData = this.rankingService.listSingleKeyRankings(this.selectedKey);
      const specificKeyRanking = this.rankingList.find(r => r.name === 'specificKeyPressed');
      if (specificKeyRanking) {
        specificKeyRanking.description = `Tecla "${this.selectedKey}" pressionada`;
        specificKeyRanking.data = singleKeysData;
      }
    }
  }

  private _filter(value: string): string[] {
    const filterValue = value.toLowerCase();
    return this.options.filter(option => option.toLowerCase().includes(filterValue));
  }

  ngOnInit(): void {
    this.loadRankings();
  }

  selectMetricModelGroup(metricModelGroup: MetricModelGroup) {
    this.metricModelGroup = metricModelGroup;
    this.showAchievements = false;
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
    this.rankingList = []; // Limpa a lista antes de adicionar novos rankings

    if (this.metricModelGroup == MetricModelGroup.KEYBOARD) {
      const singleKeysData = this.rankingService.listSingleKeyRankings(this.selectedKey);
      const singleKeysRanking: Ranking = new Ranking(`Tecla "${this.selectedKey}" pressionada`, "specificKeyPressed", "quantidade", singleKeysData);
      this.rankingList.push(singleKeysRanking)

      const allKeysData = this.rankingService.listAllKeysRankings();
      const allKeysRanking: Ranking = new Ranking("Teclas pressionadas", "allKeysPressed", "quantidade", allKeysData);
      this.rankingList.push(allKeysRanking)

      return;
    }

    if (this.metricModelGroup == MetricModelGroup.MOUSE) {
      const mouseClickData = this.rankingService.listMouseClickRankings();
      const mouseClickRanking: Ranking = new Ranking("Clicks de mouse", "mouseClicks", "clicks", mouseClickData);
      this.rankingList.push(mouseClickRanking)

      const mouseMovementData = this.rankingService.listMouseMovementRankings();
      const mouseMovementRanking: Ranking = new Ranking("Movimento do mouse", "mouseMovement", "metros", mouseMovementData);
      this.rankingList.push(mouseMovementRanking)

      return;
    }
  }

  protected readonly MetricModelGroup = MetricModelGroup;
  protected readonly PeriodType = PeriodType;
  protected readonly Object = Object;
}
