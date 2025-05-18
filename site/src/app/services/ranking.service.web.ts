import { Injectable } from '@angular/core';
import { RankingServiceInterface } from "./ranking.service.interface";
import { HttpRequestManager } from "../../utils/http/http-request-manager";
import { Observable } from 'rxjs';
import { PaginatedList } from "../models/PaginatedList";
import { UserRanking } from "../models/UserRanking";

@Injectable({
    providedIn: 'root',
})
export class RankingServiceWeb implements RankingServiceInterface {

    constructor(private http: HttpRequestManager) {}

    public getSingleKeyRanking(key: string): Observable<PaginatedList<UserRanking>> {
        return this.http.get<PaginatedList<UserRanking>>(`keypressed/${key}`);
    }

    public getAllKeysRanking(): Observable<PaginatedList<UserRanking>> {
        return this.http.get<PaginatedList<UserRanking>>(`keypressed`);
    }

}
