import { Observable } from 'rxjs';
import { PaginatedList } from "../models/PaginatedList";
import { UserRanking } from "../models/UserRanking";

export interface RankingServiceInterface {
    getKeyRanking(key: string): Observable<PaginatedList<UserRanking>>;
}
