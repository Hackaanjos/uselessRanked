import { Observable } from 'rxjs';

export interface UserRanking {
    name: string;
    score: number;
}

export interface RankingServiceInterface {
    getRankings(type: string, period: string): Observable<UserRanking[]>;
}
