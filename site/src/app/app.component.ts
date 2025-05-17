import { Component } from '@angular/core';
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
import {NgFor, NgForOf} from "@angular/common";

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
export class AppComponent {
    selectedRanking: MetricModelGroup = 'keyboard';
    rankings = [
        {
            name: "Distância percorrida com mouse",
            unit: "metros",
            ranking: [
                { name: 'User A', score: 1200 },
                { name: 'User B', score: 1150 },
                { name: 'User C', score: 1100 },
                { name: 'User D', score: 1000 },
                { name: 'User E', score: 900 },
            ]
        }, {
            name: "Clicks",
            unit: "clicks",
            ranking: [
                { name: 'User A', score: 1200 },
                { name: 'User B', score: 1150 },
                { name: 'User C', score: 1100 },
                { name: 'User D', score: 1000 },
                { name: 'User E', score: 900 },
            ]
        },
    ]
    period: PeriodType = 'week';

    selectRanking(type: MetricModelGroup) {
        this.selectedRanking = type;
    }

    selectPeriod(p: PeriodType) {
        this.period = p;
    }
}