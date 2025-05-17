import { Observable } from 'rxjs';

export interface UserRanking {
    name: string;
    score: number;
}

export interface RankingServiceInterface {

    getKeyRanking(key: string): Observable<UserRanking[]>;

}
