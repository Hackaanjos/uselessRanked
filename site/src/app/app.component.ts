import {Component, OnInit} from '@angular/core';
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
import { NgFor, NgForOf } from "@angular/common";
import { RankingServiceWeb } from "./services/ranking.service.web";
import { PaginatedList } from "./models/PaginatedList";
import { UserRanking } from "./models/UserRanking";
import { Ranking } from "./models/Ranking";

@Component({
    selector: 'app-root',
    imports: [
        NgForOf, NgFor,
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
    selectedRanking: MetricModelGroup = 'keyboard';
    period: PeriodType = 'week';
    rankingList: Array<Ranking> = [];

    constructor(private rankingService: RankingServiceWeb) {}

    ngOnInit(): void {
        this.rankingService.getKeyRanking("1").subscribe(data => {
            const list: PaginatedList<UserRanking> = data;
            const ranking: Ranking = new Ranking("Caractere precionado", "quantidade", list.content);

            this.rankingList.push(ranking)
        });
    }

    selectRanking(type: MetricModelGroup) {
        this.selectedRanking = type;
    }

    selectPeriod(p: PeriodType) {
        this.period = p;
    }
}
