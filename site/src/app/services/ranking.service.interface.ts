import { Observable } from 'rxjs';
import { PaginatedList } from "../models/PaginatedList";
import { UserRanking } from "../models/UserRanking";

export interface RankingServiceInterface {
  getSingleKeyRanking(key: string): Observable<PaginatedList<UserRanking>>;
  getAllKeysRanking(): Observable<PaginatedList<UserRanking>>;
}
