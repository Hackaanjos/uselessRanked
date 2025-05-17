import { Injectable } from '@angular/core';
import { RankingServiceInterface, UserRanking } from "./ranking.service.interface";
import { HttpRequestManager } from "../../utils/http/http-request-manager";
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root',
})
export class RankingServiceWeb implements RankingServiceInterface {

    constructor(private http: HttpRequestManager) {}

    getKeyRanking(key: string): Observable<UserRanking[]> {
        return this.http.get<UserRanking[]>(`rankings/${key}`);
    }

}
