import { Injectable } from '@angular/core';
import { RankingServiceInterface, UserRanking } from "./ranking.service.interface";
import { HttpRequestManager } from "../../utils/http/http-request-manager";

@Injectable({
    providedIn: 'root',
})
export class RankingService implements RankingServiceInterface {

    constructor(private http: HttpRequestManager) {}

}
