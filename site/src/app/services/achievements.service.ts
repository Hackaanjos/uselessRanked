import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Achievement } from '../models/Achievement';
import { HttpRequestManager } from '../../utils/http/http-request-manager';

@Injectable({
    providedIn: 'root'
})
export class AchievementsService {

    constructor(private http: HttpRequestManager) { }

    getUserAchievements(): Observable<Achievement[]> {
        return this.http.get<Achievement[]>(`/achievements`);
    }
}