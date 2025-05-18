import { Injectable } from '@angular/core';
import { RankingServiceInterface } from "./ranking.service.interface";
import { HttpRequestManager } from "../../utils/http/http-request-manager";
import { Observable } from 'rxjs';
import { PaginatedList } from "../models/PaginatedList";
import { UserRanking } from "../models/UserRanking";
import { PeriodType } from '../../utils/enums/PeriodType';

@Injectable({
  providedIn: 'root',
})
export class RankingServiceWeb implements RankingServiceInterface {

  constructor(private http: HttpRequestManager) {}

  public getSingleKeyRanking(key: string, periodType: PeriodType): Observable<PaginatedList<UserRanking>> {
    return this.http.get<PaginatedList<UserRanking>>(`ranking/keypressed/${key}/findByKey/${periodType}`);
  }

  public listSingleKeyRankings(key: string): Record<PeriodType, UserRanking[]> {
    const rankings: Record<PeriodType, UserRanking[]> = {
      [PeriodType.DAY]: [],
      [PeriodType.WEEK]: [],
      [PeriodType.MONTH]: [],
      [PeriodType.ALL_TIME]: []
    };

    // @ts-ignore
    Object.values(PeriodType).forEach((periodType: PeriodType) => {
      this.getSingleKeyRanking(key, periodType).subscribe(data => {
        rankings[periodType] = data.content;
      });
    })

    return rankings;
  }

  public getAllKeysRanking(periodType: PeriodType): Observable<PaginatedList<UserRanking>> {
    return this.http.get<PaginatedList<UserRanking>>(`ranking/keypressed/${periodType}`);
  }

  public listAllKeysRanking(): Record<PeriodType, UserRanking[]> {
    const rankings: Record<PeriodType, UserRanking[]> = {
      [PeriodType.DAY]: [],
      [PeriodType.WEEK]: [],
      [PeriodType.MONTH]: [],
      [PeriodType.ALL_TIME]: []
    };

    // @ts-ignore
    Object.values(PeriodType).forEach((periodType: PeriodType) => {
      this.getAllKeysRanking(periodType).subscribe(data => {
        rankings[periodType] = data.content;
      });
    })

    return rankings;
  }

}
