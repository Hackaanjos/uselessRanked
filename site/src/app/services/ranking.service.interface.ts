import { Observable } from 'rxjs';
import { PaginatedList } from "../models/PaginatedList";
import { UserRanking } from "../models/UserRanking";
import { PeriodType } from '../../utils/enums/PeriodType';

export interface RankingServiceInterface {

  getSingleKeyRanking(key: string, periodType: PeriodType): Observable<PaginatedList<UserRanking>>;
  listSingleKeyRankings(key: string): Record<PeriodType, UserRanking[]>;

  getAllKeysRanking(periodType: PeriodType): Observable<PaginatedList<UserRanking>>;
  listAllKeysRankings(): Record<PeriodType, UserRanking[]>;

  getMouseClickRanking(periodType: PeriodType): Observable<PaginatedList<UserRanking>>;
  listAllKeysRankings(): Record<PeriodType, UserRanking[]>;

  getMouseMovementRanking(periodType: PeriodType): Observable<PaginatedList<UserRanking>>;
  listMouseMovementRankings(): Record<PeriodType, UserRanking[]>;

}
