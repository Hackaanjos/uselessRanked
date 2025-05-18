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
    return this.defaultList((periodType: PeriodType) => {
      return this.getSingleKeyRanking(key, periodType)
    });
  }

  public getAllKeysRanking(periodType: PeriodType): Observable<PaginatedList<UserRanking>> {
    return this.http.get<PaginatedList<UserRanking>>(`ranking/keypressed/${periodType}`);
  }

  public listAllKeysRankings(): Record<PeriodType, UserRanking[]> {
    return this.defaultList((periodType: PeriodType) => {
      return this.getAllKeysRanking(periodType);
    });
  }

  public getMouseClickRanking(periodType: PeriodType): Observable<PaginatedList<UserRanking>> {
    return this.http.get<PaginatedList<UserRanking>>(`ranking/mouseclick/${periodType}`);
  }

  public listMouseClickRankings(): Record<PeriodType, UserRanking[]> {
    return this.defaultList((periodType: PeriodType) => {
      return this.getMouseClickRanking(periodType);
    });
  }

  private defaultList(callback: (periodType: PeriodType) => Observable<PaginatedList<UserRanking>>): Record<PeriodType, UserRanking[]> {
    const rankings: Record<PeriodType, UserRanking[]> = {
      [PeriodType.DAY]: [],
      [PeriodType.WEEK]: [],
      [PeriodType.MONTH]: [],
      [PeriodType.ALL_TIME]: []
    };

    Object.values(PeriodType).forEach((periodType: PeriodType) => {
      callback(periodType).subscribe(data => {
        rankings[periodType] = data.content;
      });
    })

    return rankings;
  }

}
