import { Component } from '@angular/core';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatListModule } from '@angular/material/list';
import { MatButtonModule } from '@angular/material/button';
import { MatButtonToggleModule } from '@angular/material/button-toggle';
import { MatCardModule } from '@angular/material/card';
import { MatTableModule } from '@angular/material/table';
import { FormsModule } from '@angular/forms';
import { MatTabsModule} from '@angular/material/tabs';

@Component({
    selector: 'app-root',
    imports: [
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
    selectedRanking: 'keyboard' | 'mouse' | 'behavior' = 'keyboard';
    rankings = [
        { name: 'User A', score: 1200 },
        { name: 'User B', score: 1150 },
        { name: 'User C', score: 1100 },
        { name: 'User D', score: 1000 },
        { name: 'User E', score: 900 },
    ];
    period: 'day' | 'week' | 'month' = 'week';

    selectRanking(type: 'keyboard' | 'mouse' | 'behavior') {
        this.selectedRanking = type;
    }

    selectPeriod(p: 'day' | 'week' | 'month') {
        this.period = p;
    }
}